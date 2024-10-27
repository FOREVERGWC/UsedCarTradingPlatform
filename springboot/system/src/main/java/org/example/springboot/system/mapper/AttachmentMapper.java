package org.example.springboot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.springboot.system.domain.entity.Attachment;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 附件Mapper接口
 * </p>
 */
@Mapper
public interface AttachmentMapper extends BaseMapper<Attachment> {

}
