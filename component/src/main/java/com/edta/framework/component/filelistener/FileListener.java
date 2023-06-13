package com.edta.framework.component.filelistener;

import com.edta.framework.component.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * @author wangluyao
 * @date 2022/8/9 18:09
 * @description
 */
@Slf4j
public class FileListener {

    private final Long DEFAULT_INTERVAL = 5 * 1000L;
    private final FileAlterationMonitor monitor = new FileAlterationMonitor(DEFAULT_INTERVAL);

    public FileListener() {
        this.start();
    }

    public void addFile(File file, FileListenerCallback fileListenerCallback) {
        if (!file.exists()) {
            log.error("文件不存在: {}", file.getAbsolutePath());
            return;
        }
        FileAlterationObserver observer = new FileAlterationObserver(file.getParentFile().getAbsolutePath(),
                pathName -> pathName.getName().equals(file.getName()));
        observer.addListener(fileListenerCallback);
        monitor.addObserver(observer);
        log.info("已添加[{}]文件到监听器", file.getAbsolutePath());
    }

    public void start() {
        try {
            monitor.start();
            log.info("文件监听[{}]启动!", this);
        } catch (Exception e) {
            log.error("文件监听器[{}]启动失败: {}", this, e.getMessage(), e);
            throw new SysException("文件监听器启动失败: " + e.getMessage(), e);
        }
    }
}
