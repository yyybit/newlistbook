package com.example.newlistbook.utils;

/**
 * @Author: 李圣
 * @Date:2023/4/16 16:29
 * @Version: 1.0
 */
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.Date;
import java.util.UUID;

/**

 * 阿里云 OSS 工具类
 */
@Component
public class AliOSSUtils {

//    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//    @Value("${aliyun.oss.endpoint}")
//    String endpoint;
////
//    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//    @Value("${aliyun.oss.accessKeyId}")
//    String accessKeyId;
//    @Value("${aliyun.oss.accessKeySecret}")
//    String accessKeySecret;
//    // 填写Bucket名称，例如examplebucket。
//    @Value("${aliyun.oss.bucketName}")
//    String bucketName;
    @Autowired
    private AliOSSProperties aliOSSProperties;



//        private String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
//        private String accessKeyId = "LTAI4GCH1vX6DKqJWxd6nEuW";
//        private String accessKeySecret = "yBshYweHOpqDuhCArrVHwIiBKpyqSL";
//        private String bucketName = "web-tlias";

/**
     * 实现上传图片到OSS
     */
    public String upload(MultipartFile file) throws IOException {

        String endpoint = aliOSSProperties.getEndpoint();
        String accessKeyId = aliOSSProperties.getAccessKeyId();
        String accessKeySecret = aliOSSProperties.getAccessKeySecret();
        String bucketName = aliOSSProperties.getBucketName();


        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        //文件访问路径
        Date expiration = new Date(System.currentTimeMillis() + 946080000 * 1000);
        String url = ossClient.generatePresignedUrl(bucketName, fileName, expiration).toString();        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

}