package org.example.springboot.system.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.example.springboot.system.common.enums.BizType;
import org.example.springboot.system.domain.dto.FileChunkDto;
import org.example.springboot.system.domain.entity.Attachment;
import org.example.springboot.system.domain.entity.AttachmentChunk;
import org.example.springboot.system.domain.vo.AttachmentCheckVo;
import jakarta.annotation.Resource;
import org.example.springboot.system.service.IAttachmentChunkService;
import org.example.springboot.system.service.IAttachmentService;
import org.example.springboot.system.service.IFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * <p>
 * 文件服务实现类
 * </p>
 */
@Service
public class FileServiceImpl implements IFileService {
    @Value("${upload.path}")
    private String basePath;

    @Resource
    private IAttachmentService attachmentService;
    @Resource
    private IAttachmentChunkService attachmentChunkService;

    @Override
    public AttachmentCheckVo checkFile(String hashCode, Integer chunkTotal) {
        AttachmentCheckVo vo = AttachmentCheckVo.builder().hasUpload(false).indexList(List.of()).build();
        Attachment attachment = attachmentService.getByHashCode(hashCode);
        if (attachment != null && attachment.getStatus()) {
            vo.setHasUpload(true);
            return vo;
        }
        List<AttachmentChunk> attachmentChunkList = attachmentChunkService.listByHashCode(hashCode);
        List<Integer> indexList = attachmentChunkList.stream().map(AttachmentChunk::getChunkIndex).toList();
        List<Integer> missingChunks = Optional.of(IntStream.range(0, chunkTotal)
                        .filter(i -> !indexList.contains(i))
                        .boxed()
                        .toList())
                .orElse(List.of());
        vo.setIndexList(missingChunks);
        return vo;
    }

    @Override
    public String uploadFile(FileChunkDto dto) {
        if (dto.getFile() == null || dto.getFile().isEmpty()) {
            throw new RuntimeException("上传失败！禁止上传空文件");
        }
        String path = basePath + File.separator + dto.getHashCode();
        if (dto.getChunkTotal() == 1) {
            uploadSingleFile(path, dto);
        } else {
            uploadChunkFile(path, dto);
        }
        String filePath = File.separator + "file" + File.separator + dto.getHashCode() + "." + FileUtil.extName(dto.getFileName());
        saveAttachment(filePath, dto);
        return filePath;
    }

    /**
     * 单文件上传
     *
     * @param path 路径
     * @param dto  文件
     */
    private void uploadSingleFile(String path, FileChunkDto dto) {
        path += "." + FileUtil.extName(dto.getFileName());
        Path filePath = Paths.get(path);
        try (InputStream is = dto.getFile().getInputStream()) {
            Files.createDirectories(filePath.getParent());
            if (FileUtil.exist(filePath.toFile())) {
                return;
            }
            FileUtil.copyFile(is, filePath.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件分片上传
     *
     * @param path 路径
     * @param dto  文件分片
     */
    private void uploadChunkFile(String path, FileChunkDto dto) {
        // TODO 性能优化
        Path dirPath = Paths.get(path);
        try {
            Files.createDirectories(dirPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (InputStream is = dto.getFile().getInputStream()) {
            Path filePath = Paths.get(path, dto.getHashCode() + "_" + dto.getChunkIndex());
            if (Files.exists(filePath)) {
                return;
            }
            FileUtil.copyFile(is, filePath.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File[] files = FileUtil.ls(path);
        if (dto.getChunkTotal() == files.length) {
            Path mergePath = Paths.get(path + "." + FileUtil.extName(dto.getFileName()));
            FileUtil.touch(mergePath.toFile());
            try (OutputStream os = FileUtil.getOutputStream(mergePath.toFile())) {
                for (int i = 0; i < dto.getChunkTotal(); i++) {
                    Path chunkPath = Paths.get(path, dto.getHashCode() + "_" + i);
                    try (InputStream is = FileUtil.getInputStream(chunkPath.toFile())) {
                        IoUtil.copy(is, os);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            FileUtil.del(dirPath);
        }
    }

    @Transactional
    protected void saveAttachment(String filePath, FileChunkDto dto) {
        Attachment attachment = attachmentService.getByHashCode(dto.getHashCode());
        if (attachment != null && attachment.getStatus()) {
            return;
        }
        if (dto.getChunkTotal() == 1) {
            if (attachment == null) {
                attachment = Attachment.builder()
                        .hashCode(dto.getHashCode())
                        .bizId(0L)
                        .bizType(BizType.OTHER.getCode())
                        .bucketName("")
                        .filePath(filePath)
                        .fileName(dto.getFileName())
                        .fileSize(dto.getFileSize())
                        .chunkTotal(dto.getChunkTotal())
                        .chunkSize(dto.getChunkSize())
                        .status(true)
                        .build();
            } else {
                attachment.setStatus(true);
            }
            attachmentService.saveOrUpdate(attachment);
        } else {
            if (attachment == null) {
                attachment = Attachment.builder()
                        .hashCode(dto.getHashCode())
                        .bizId(0L)
                        .bizType(BizType.OTHER.getCode())
                        .bucketName("")
                        .filePath(filePath)
                        .fileName(dto.getFileName())
                        .fileSize(dto.getFileSize())
                        .chunkTotal(dto.getChunkTotal())
                        .chunkSize(dto.getChunkSize())
                        .status(false)
                        .build();
                attachmentService.save(attachment);
                AttachmentChunk chunk = AttachmentChunk.builder()
                        .fileId(attachment.getId())
                        .hashCode(dto.getHashCode())
                        .chunkIndex(dto.getChunkIndex())
                        .chunkSize(dto.getFile().getSize())
                        .status(true)
                        .build();
                attachmentChunkService.save(chunk);
            } else {
                AttachmentChunk chunk = AttachmentChunk.builder()
                        .fileId(attachment.getId())
                        .hashCode(dto.getHashCode())
                        .chunkIndex(dto.getChunkIndex())
                        .chunkSize(dto.getFile().getSize())
                        .status(true)
                        .build();
                attachmentChunkService.save(chunk);
                Long count = attachmentChunkService.lambdaQuery()
                        .eq(AttachmentChunk::getFileId, attachment.getId())
                        .count();
                if (Objects.equals(count.intValue(), dto.getChunkTotal())) {
                    attachment.setStatus(true);
                    attachmentService.updateById(attachment);
                }
            }
        }
    }
}
