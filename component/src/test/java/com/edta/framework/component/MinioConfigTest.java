package com.edta.framework.component;

import io.minio.*;
import io.minio.http.Method;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author wangluyao
 * @date 2022/7/9 12:37
 * @description
 */
public class MinioConfigTest {
    MinioClient client;

    {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application-minio.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            properties.list(System.out);
            System.out.println("==============================================");
            String endpoint = properties.getProperty("minio.endpoint");
            String username = properties.getProperty("minio.accesskey");
            String password = properties.getProperty("minio.secretKey");

            client = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(username, password)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SneakyThrows
    @Test
    void testUpload() {

        boolean found = client.bucketExists(BucketExistsArgs.builder().bucket("bucket-test").build());
        if (!found) {
            client.makeBucket(MakeBucketArgs.builder().bucket("bucket-test").build());
        }
        File file = new File("C:\\Users\\wly32\\Documents\\Work\\Cloud\\cloud_test\\backend\\trunk\\V1.1.0\\config\\src\\test\\java\\com\\wly\\framework\\config\\rB6aL2HqXxOANfdFAAC59qTtY7I68.jpeg");
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectWriteResponse objectWriteResponse = client.putObject(PutObjectArgs.builder()
                .bucket("bucket-test")
                .object(file.getName())
                .stream(fileInputStream, file.length(), -1)
                .build());
        System.out.println(objectWriteResponse);
        String name = objectWriteResponse.object();
        String url = getObjectUrl("bucket-test", name);
        System.out.println(url);
    }


    @SneakyThrows
    public String getObjectUrl(String bucketName, String objectName) {
        return client.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .build());
    }


}
