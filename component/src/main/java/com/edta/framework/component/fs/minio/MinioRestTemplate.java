package com.edta.framework.component.fs.minio;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import com.edta.framework.component.fs.FileRestTemplate;
import io.minio.*;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangluyao
 * @date 2022/7/9 14:22
 * @description
 */
public class MinioRestTemplate implements FileRestTemplate {

    private final String bucketName;
    private final MinioClient client;

    public MinioRestTemplate(MinioClient minioClient, String bucketName) {
        this.client = minioClient;
        this.bucketName = bucketName;
        createBucket(bucketName);
    }

    private static String getPath() {
        String year, month, day;
        year = new SimpleDateFormat("yyyy").format(new Date());
        month = new SimpleDateFormat("MM").format(new Date());
        day = new SimpleDateFormat("dd").format(new Date());
        return new StringBuilder().append(year).append("/")
                .append(month).append("/")
                .append(day).append("/").toString();
    }

    @SneakyThrows
    @Override
    public MinioFileInfo upload(InputStream inputStream, String name, Long size) {
        validate(inputStream, name, size);
        String md5 = null;
        byte[] bytes = new byte[Math.toIntExact(size)];
        inputStream.read(bytes);
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes)) {
            md5 = SecureUtil.md5(byteArrayInputStream);
        }
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes)) {
            inputStream = byteArrayInputStream;
            ObjectWriteResponse objectWriteResponse = client.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(getPath() + name)
                    .stream(inputStream, size, -1)
                    .build());
            String path = objectWriteResponse.bucket() + "/" + objectWriteResponse.object();
            return MinioFileInfo.builder()
                    .md5sum(md5)
                    .filename(objectWriteResponse.object())
                    .size(size)
                    .path(path)
                    .suffixName(FileUtil.getSuffix(name))
                    .build();
        }
    }

    @SneakyThrows
    private void createBucket(String bucketName) {
        boolean found = client.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
        if (!found) {
            client.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName).build());
        }
        String configTemplate = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Resource\":[\"arn:aws:s3:::%s\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:PutObject\",\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\"],\"Resource\":[\"arn:aws:s3:::%s/*\"]}]}";
        String config = String.format(configTemplate, bucketName, bucketName);
        client.setBucketPolicy(SetBucketPolicyArgs.builder()
                .bucket(bucketName)
                .config(config)
                .build());
    }

    private void validate(InputStream inputStream, String name, Long size) {
        if (inputStream == null) {
            throw new RuntimeException();
        }
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException();
        }
        if (size == null || size <= 0) {
            throw new RuntimeException();
        }
    }

}
