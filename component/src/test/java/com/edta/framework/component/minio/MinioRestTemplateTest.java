package com.edta.framework.component.minio;

import com.alibaba.fastjson.JSON;
import com.edta.framework.component.Starter;
import com.edta.framework.component.fs.minio.MinioFileInfo;
import com.edta.framework.component.fs.minio.MinioRestTemplate;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author wangluyao
 * @date 2022/7/9 15:23
 * @description
 */
@SpringBootTest(classes = Starter.class)
@ActiveProfiles(profiles = {"redis", "db", "es", "minio"})
class MinioRestTemplateTest {

    @Autowired
    private MinioRestTemplate minioRestTemplate;

    @SneakyThrows
    @Test
    void uploadSpringTest() {
        File file = new File("C:\\Users\\wly32\\Documents\\Work\\Cloud\\cloud_test\\backend\\trunk\\V1.1.0\\config\\src\\test\\java\\com\\wly\\framework\\config\\rB6aL2HqXxOANfdFAAC59qTtY7I68.jpeg");
        FileInputStream fileInputStream = new FileInputStream(file);
        MinioFileInfo minioFileInfo = minioRestTemplate.upload(fileInputStream,
                file.getName(),
                file.length());
        System.out.println(JSON.toJSONString(minioFileInfo));
    }

    @SneakyThrows
    @Test
    void uploadTest() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application-minio.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        properties.list(System.out);
        System.out.println("==============================================");
        String endpoint = properties.getProperty("minio.endpoint");
        String username = properties.getProperty("minio.username");
        String password = properties.getProperty("minio.password");

        MinioClient client = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(username, password)
                .build();
        minioRestTemplate = new MinioRestTemplate(client, "wangluyao-test");
        File file = new File("C:\\Users\\wly32\\Documents\\Work\\Cloud\\cloud_test\\backend\\trunk\\V1.1.0\\config\\src\\test\\java\\com\\wly\\framework\\config\\rB6aL2HqXxOANfdFAAC59qTtY7I68.jpeg");
        FileInputStream fileInputStream = new FileInputStream(file);
        MinioFileInfo minioFileInfo = minioRestTemplate.upload(fileInputStream,
                file.getName(),
                file.length());
        System.out.println(JSON.toJSONString(minioFileInfo));
    }

}