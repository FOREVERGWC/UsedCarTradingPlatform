package org.example.springboot.system.service;

import org.example.springboot.system.domain.dto.FileChunkDto;
import org.example.springboot.system.domain.vo.AttachmentCheckVo;

/**
 * <p>
 * 文件服务类
 * </p>
 */
public interface IFileService {
    /**
     * 文件校验
     *
     * @param hashCode   散列值
     * @param chunkTotal 分片数量
     * @return 结果
     */
    AttachmentCheckVo checkFile(String hashCode, Integer chunkTotal);

    /**
     * 文件上传
     *
     * @param dto 文件
     * @return 结果
     */
    String uploadFile(FileChunkDto dto);
}
