package com.edta.framework.component.filelistener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author wangluyao
 * @date 2022/8/31 17:19
 * @description
 */
@Slf4j
class FileListenerTest {

    private final File file1 = new File("C:\\Users\\wly32\\Documents\\Work\\Cloud\\xx7hh\\01trunk\\component\\src\\test\\resources\\file1.txt");
    private final File file2 = new File("C:\\Users\\wly32\\Documents\\Work\\Cloud\\xx7hh\\01trunk\\component\\src\\test\\resources\\file2.txt");

    @Test
    void start() {
        FileListener fileListener = new FileListener();
        FileListenerCallback callBack = new FileListenerCallback() {
            @Override
            public void onStart(FileAlterationObserver observer) {

            }

            @Override
            public void onDirectoryCreate(File directory) {
                log.info("onDirectoryCreate == {}", directory.getName());
            }

            @Override
            public void onDirectoryChange(File directory) {
                log.info("onDirectoryChange == {}", directory.getName());
            }

            @Override
            public void onDirectoryDelete(File directory) {
                log.info("onDirectoryDelete == {}", directory.getName());
            }

            @Override
            public void onFileCreate(File file) {
                log.info("onFileCreate == {}", file.getName());
            }

            @Override
            public void onFileChange(File file) {
                log.info("onFileChange == {}", file.getName());
            }

            @Override
            public void onFileDelete(File file) {
                log.info("onFileDelete == {}", file.getName());
            }

            @Override
            public void onStop(FileAlterationObserver observer) {

            }
        };
        fileListener.start();
        fileListener.addFile(file1, callBack);
        while (true) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}