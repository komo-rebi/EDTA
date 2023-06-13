package com.edta.framework.component.fs.fastdfs;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import com.edta.framework.component.fs.FileInfo;
import com.github.tobato.fastdfs.domain.conn.TrackerConnectionManager;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @author wangluyao
 * @date 2022/6/20 11:04
 * @description
 */
@Slf4j
//@Component
public class FileServiceImpl implements FileService {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private TrackerConnectionManager trackerConnectionManager;

    @Override
    public FileInfo upload(File file) {
        String filename = file.getName();
        long size = FileUtil.size(file);
        String suffix = FileUtil.getSuffix(file);
        StorePath storePath = fastFileStorageClient.uploadFile(FileUtil.getInputStream(file),
                size,
                suffix,
                null);
        String md5sum = SecureUtil.md5(file);
        FileInfo fileResult = new FileInfo();
        fileResult.setFilename(filename);
        fileResult.setPath(storePath.getFullPath());
        fileResult.setMd5sum(md5sum);
        return fileResult;
    }
}
