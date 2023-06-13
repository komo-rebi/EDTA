package com.edta.framework.component.config;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONPath;
import com.edta.framework.component.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @author wly
 * @date 2021/06/09 20:22:22
 */
@Slf4j
public class ConfigFileListener extends FileAlterationListenerAdaptor {

    private final File configFile;
    private String configContent;

    public ConfigFileListener(File configFile) {
        if (!configFile.exists()) {
            throw new SysException("未找到配置文件: " + configFile.getAbsolutePath());
        }
        this.configFile = configFile;
        reload();
        new Monior(this).start();
    }

    private void reload() {
        JSON json = JSONUtil.readJSON(configFile, StandardCharsets.UTF_8);
        setConfigContent(JSONUtil.toJsonStr(json));
        log.info("当前配置: {}", getConfigContent());
    }

    public <T> T getValue(String path, T defaultValue) {
        try {
            if (!path.startsWith("$")) {
                path = "$" + path;
            }
            return (T) JSONPath.extract(getConfigContent(), path);
        } catch (Exception e) {
            log.error("读取配置失败，路径为: {}, {}", path, e.getMessage(), e);
            return defaultValue;
        }
    }

    public String getConfigContent() {
        synchronized (this) {
            return configContent;
        }
    }

    private void setConfigContent(String configContent) {
        synchronized (this) {
            this.configContent = configContent;
        }
    }

    public File getConfigFile() {
        return configFile;
    }

    @Override
    public void onStart(FileAlterationObserver observer) {
    }

    @Override
    public void onDirectoryCreate(File directory) {
        log.info("onDirectoryCreate");
    }

    @Override
    public void onDirectoryChange(File directory) {
        log.info("onDirectoryChange");
    }

    @Override
    public void onDirectoryDelete(File directory) {
        log.info("onDirectoryDelete");
    }

    @Override
    public void onFileCreate(File file) {
        onFileChange(file);
    }

    @Override
    public void onFileChange(File file) {
        log.info("配置文件变更，重新加载配置...");
        reload();
    }

    @Override
    public void onFileDelete(File file) {
        log.error("配置文件被删除");
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
    }

    static class Monior {
        private static final Long DEFAULT_INTERVAL = 5 * 1000L;
        private final Long interval;
        private final File file;
        private final ConfigFileListener listener;

        public Monior(ConfigFileListener listener) {
            this.listener = listener;
            this.interval = DEFAULT_INTERVAL;
            this.file = listener.getConfigFile();
        }

        public void start() {
            log.debug("设置监听器, 监听路径:{}, 监听文件:{}", file.getAbsolutePath(), file.getName());
            FileAlterationObserver observer = new FileAlterationObserver(file.getParentFile().getAbsolutePath(),
                    pathName -> {
                        return pathName.getName().equals(file.getName());
                    });
            observer.addListener(listener);
            FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
            monitor.addObserver(observer);
            try {
                monitor.start();
                log.info("配置文件监听器启动成功");
            } catch (Exception e) {
                log.error("配置文件监听器启动失败: {}", e.getMessage(), e);
                throw new SysException("配置文件监听器启动失败: " + e.getMessage(), e);
            }
        }

    }
}
