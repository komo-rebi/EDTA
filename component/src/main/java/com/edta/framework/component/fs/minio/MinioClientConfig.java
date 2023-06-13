package com.edta.framework.component.fs.minio;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangluyao
 * @date 2022/7/9 14:09
 * @description
 */
@Data
@ConditionalOnClass(MinioClient.class)
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioClientConfig {

    private String endpoint;
    private String username;
    private String password;
    private String bucketName;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient;
        minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(username, password)
                .build();
        return minioClient;
    }

    @Bean
    public MinioRestTemplate minioRestTemplate(MinioClient minioClient) {
        return new MinioRestTemplate(minioClient, bucketName);
    }
}
