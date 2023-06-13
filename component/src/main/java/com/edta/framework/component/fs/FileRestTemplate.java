package com.edta.framework.component.fs;

import java.io.InputStream;

/**
 * @author wangluyao
 * @date 2022/7/9 14:30
 * @description
 */
public interface FileRestTemplate {

    FileInfo upload(InputStream inputStream, String name, Long size);
}
