package org.example.springboot.generator.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.generator.gen.domain.dto.GenTableDto;
import org.example.springboot.generator.gen.domain.entity.GenTable;
import org.example.springboot.generator.gen.domain.vo.GenTableVo;

import java.util.List;

/**
 * <p>
 * 代码生成业务服务类
 * </p>
 */
public interface IGenTableService extends IService<GenTable> {
    /**
     * 查询代码生成业务表列表
     *
     * @param dto 代码生成业务表
     * @return 结果
     */
    List<GenTableVo> getList(GenTableDto dto);

    /**
     * 查询代码生成业务表分页
     *
     * @param dto 代码生成业务表
     * @return 结果
     */
    IPage<GenTableVo> getPage(GenTableDto dto);

    /**
     * 查询代码生成业务表
     *
     * @param dto 代码生成业务表
     * @return 结果
     */
    GenTableVo getOne(GenTableDto dto);

    /**
     * 导出代码生成业务表
     *
     * @param entity   代码生成业务表
     * @param response 响应对象
     */
    void exportExcel(GenTable entity, HttpServletResponse response);
}
