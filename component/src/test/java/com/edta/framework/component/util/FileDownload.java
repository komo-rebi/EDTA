package com.edta.framework.component.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;

import java.io.File;
import java.io.InputStream;

/**
 * @author wangluyao
 * @date 2022/10/14 18:34
 * @description
 */
public class FileDownload {

    public static void main(String[] args) {
        FileUtil.mkdir("C:\\Users\\wly32\\Documents\\Virtual Machines");
        File file = FileUtil.createTempFile(new File("C:\\Users\\wly32\\Documents\\Virtual Machines"));
        HttpUtil.downloadFile("https://img.zcool.cn/community/011a5357b64c620000018c1b9e7e67.png@1280w_1l_2o_100sh.png", file);
        InputStream inputStream = FileUtil.getInputStream(file);
        FileUtil.del(file);
    }
}
