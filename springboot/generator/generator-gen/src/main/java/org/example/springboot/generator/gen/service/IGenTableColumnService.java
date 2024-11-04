package org.example.springboot.generator.gen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.generator.gen.domain.dto.GenTableColumnDto;
import org.example.springboot.generator.gen.domain.entity.GenTableColumn;
import org.example.springboot.generator.gen.domain.vo.GenTableColumnVo;

import java.util.List;

/**
 * <p>
 * 代码生成业务字段服务类
 * </p>
 */
public interface IGenTableColumnService extends IService<GenTableColumn> {
    /**
     * 查询代码生成业务字段列表
     *
     * @param dto 代码生成业务字段
     * @return 结果
     */
    List<GenTableColumnVo> getList(GenTableColumnDto dto);

    /**
     * 查询代码生成业务字段分页
     *
     * @param dto 代码生成业务字段
     * @return 结果
     */
    IPage<GenTableColumnVo> getPage(GenTableColumnDto dto);

    /**
     * 查询代码生成业务字段
     *
     * @param dto 代码生成业务字段
     * @return 结果
     */
    GenTableColumnVo getOne(GenTableColumnDto dto);

    /**
     * 导出代码生成业务字段
     *
     * @param entity   代码生成业务字段
     * @param response 响应对象
     */
    void exportExcel(GenTableColumn entity, HttpServletResponse response);
}
