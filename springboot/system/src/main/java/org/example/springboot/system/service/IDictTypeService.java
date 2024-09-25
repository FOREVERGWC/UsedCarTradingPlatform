package org.example.springboot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.system.domain.dto.DictTypeDto;
import org.example.springboot.system.domain.entity.DictType;
import org.example.springboot.system.domain.vo.DictTypeVo;

import java.util.List;

/**
 * <p>
 * 字典类型服务类
 * </p>
 */
public interface IDictTypeService extends IService<DictType> {
    /**
     * 查询字典类型列表
     *
     * @param dto 字典类型
     * @return 结果
     */
    List<DictTypeVo> getList(DictTypeDto dto);

    /**
     * 查询字典类型分页
     *
     * @param dto 字典类型
     * @return 结果
     */
    IPage<DictTypeVo> getPage(DictTypeDto dto);

    /**
     * 查询字典类型
     *
     * @param dto 字典类型
     * @return 结果
     */
    DictTypeVo getOne(DictTypeDto dto);

    /**
     * 导出字典类型
     *
     * @param dictType 字典类型
     * @param response 响应对象
     */
    void exportExcel(DictType dictType, HttpServletResponse response);
}
