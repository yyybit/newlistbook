package com.example.newlistbook.utils;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 李圣
 * @Date:2023/4/16 17:13
 * @Version: 1.0
 */

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSProperties {
    private String endpoint;
    private String accessKeySecret;
    private String bucketName;
    private String accessKeyId;
    }
