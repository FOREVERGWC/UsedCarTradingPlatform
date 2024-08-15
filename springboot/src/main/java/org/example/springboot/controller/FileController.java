package org.example.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.domain.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * <p>
 * 文件前端控制器
 * </p>
 */
@RestController
@RequestMapping("/files")
@Tag(name = "文件", description = "文件")
public class FileController {
    @Value("${upload.path}")
    private String basePath;

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 结果
     */
    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "上传文件", method = "POST")
    public Result<String> upload(MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("禁止上传空文件！");
        }
        String originalFilename = file.getOriginalFilename();
        String suffixName = "." + FileUtil.extName(originalFilename);
        String fileName = UUID.randomUUID() + suffixName;
        Path filePath = Paths.get(basePath, fileName);
        String path = "/files/" + fileName;
        try (InputStream inputStream = file.getInputStream()) {
            Files.createDirectories(filePath.getParent());
            FileUtil.copyFile(inputStream, filePath.toFile());
        } catch (IOException e) {
            return Result.error("文件上传失败");
        }
        return Result.success("上传成功！", path);
    }

    /**
     * 获取文件
     *
     * @param name     文件名称
     * @param response 响应对象
     */
    @GetMapping("/{name}")
    @Operation(summary = "获取文件", description = "获取文件", method = "GET")
    public void avatarPath(@PathVariable String name, HttpServletResponse response) {
        if (StrUtil.isNotBlank(name)) {
            try (OutputStream outputStream = response.getOutputStream(); FileInputStream fileInputStream = new FileInputStream(Paths.get(basePath, name).toFile())) {
                IoUtil.copy(fileInputStream, outputStream, IoUtil.DEFAULT_BUFFER_SIZE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
