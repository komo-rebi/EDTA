package com.edta.framework.component.fs.fastdfs;

import com.edta.framework.component.fs.FileInfo;

import java.io.File;

/**
 * @author wangluyao
 * @date 2022/6/17 23:52
 * @description
 */
public interface FileService {

    FileInfo upload(File file);
}
