package com.example.controller;


import com.example.dto.CommonDto;
import com.example.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/oss/file")
public class OSSController {

    @Resource
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     * @param module
     * @return
     */
    @PostMapping("/upload")
    public CommonDto upload(MultipartFile file, String module){
        //返回上传到oss的路径
        CommonDto<Object> dto = new CommonDto<>();
        String url = fileService.upload(file,module);
        dto.setContent(url);
        dto.setMessage("文件上传成功");
        return dto;
    }

}