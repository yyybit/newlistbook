package com.example.newlistbook.controller;

import com.example.newlistbook.pojo.Result;
import com.example.newlistbook.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: 李圣
 * @Date:2023/4/14 17:32
 * @Version: 1.0
 */

@Slf4j
@RequestMapping("/upload")
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    //本地上传
//    @PostMapping
//    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
//        log.info("文件上传：{}，{},{}",username,age,image);
//        //获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//
//        //构建唯一的文件名（不能重复）  --uuid 通用唯一识别码
//        int index = originalFilename.lastIndexOf(".");
//        String extname = originalFilename.substring(index);
//        String newFileName = UUID.randomUUID().toString() + extname;
//        log.info("新的文件名：{}",newFileName);
//
//        //将文件存储在服务器的磁盘目录中E：\
//        image.transferTo(new File("D:\\images\\"+newFileName));
//        return Result.success();
//    }

    @PostMapping
    public Result upload(MultipartFile image) throws Exception {
        //getOriginalFilename:是获取到图片的原始名字
        log.info("文件上传，文件名：",image.getOriginalFilename());
        //调用阿里云OSS类
        String url = aliOSSUtils.upload(image);
        log.info("文件上传，文件访问的url：（）",url);
        return Result.success(url);
    }

}
