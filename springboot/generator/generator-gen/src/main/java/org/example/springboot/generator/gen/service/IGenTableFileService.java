package org.example.springboot.generator.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.generator.gen.domain.dto.GenTableFileDto;
import org.example.springboot.generator.gen.domain.entity.GenTableFile;
import org.example.springboot.generator.gen.domain.vo.GenTableFileVo;

import java.util.List;

/**
 * <p>
 * 代码生成业务文件服务类
 * </p>
 */
public interface IGenTableFileService extends IService<GenTableFile> {
    /**
     * 查询代码生成业务文件列表
     *
     * @param dto 代码生成业务文件
     * @return 结果
     */
    List<GenTableFileVo> getList(GenTableFileDto dto);

    /**
     * 查询代码生成业务文件分页
     *
     * @param dto 代码生成业务文件
     * @return 结果
     */
    IPage<GenTableFileVo> getPage(GenTableFileDto dto);

    /**
     * 查询代码生成业务文件
     *
     * @param dto 代码生成业务文件
     * @return 结果
     */
    GenTableFileVo getOne(GenTableFileDto dto);

    /**
     * 导出代码生成业务文件
     *
     * @param entity   代码生成业务文件
     * @param response 响应对象
     */
    void exportExcel(GenTableFile entity, HttpServletResponse response);
}
