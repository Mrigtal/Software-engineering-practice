package com.example.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.example.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
@Slf4j
public class LocalFIleController {

    private String FILE_DIR;

    public String getFILE_DIR() {
        if (FILE_DIR == null){
            FILE_DIR = System.getProperty("user.dir") + "\\\\upload\\\\";
        }
        return FILE_DIR;
    }

    /**
     * upload file api
     *
     * @param file uploaded file
     * @return file name or exception
     */
    @PostMapping()
    public Result upload(MultipartFile file) {
        String fileType = file.getOriginalFilename();
        if (fileType != null && fileType.contains(".")) {
            String[] split = fileType.split("\\.");
            fileType = "." + split[split.length - 1];
        }

        if (!new File(getFILE_DIR()).exists()) {
            new File(getFILE_DIR()).mkdirs();
        }
        String fileName = IdUtil.simpleUUID() + fileType;
        String filePath = getFILE_DIR() + fileName;

        try {
            //  file.transferTo(new File(filePath));
            FileUtil.writeBytes(file.getBytes(), filePath);
        } catch (IOException e) {
            log.error(e.getMessage());
            return Result.error("error: " + e.getMessage());
        }
        return Result.success("upload success!").put("data", fileName);
    }

    /**
     * download file api
     *
     * @param fileName download file name
     * @return file
     */
    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        File file = new File(getFILE_DIR() + fileName);
        log.info("文件被访问: " + file.getPath());
        // 创建一个Resource对象，将文件包装起来
        if (!file.exists()) {
            log.error("请求的文件不存在：" + file.getPath());
            return null;
        }
        Resource resource = new FileSystemResource(file);

        // 设置Content-Disposition响应头，用于指定文件名
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        // 返回ResponseEntity，包含文件和响应头
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length());

        String upperCase = fileName.toUpperCase();
        if (upperCase.endsWith(".PNG")) {
            bodyBuilder.contentType(MediaType.IMAGE_PNG);
        } else if (upperCase.endsWith(".JPG") || (upperCase.endsWith(".JPEG"))) {
            bodyBuilder.contentType(MediaType.IMAGE_JPEG);
        } else {
            bodyBuilder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        }
        return bodyBuilder.body(resource);
    }
}
