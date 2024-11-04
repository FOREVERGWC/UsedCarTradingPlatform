package org.example.springboot.generator.gen.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.common.service.IBaseService;
import org.example.springboot.common.utils.ExcelUtils;
import org.example.springboot.generator.gen.domain.dto.GenTableColumnDto;
import org.example.springboot.generator.gen.domain.entity.GenTableColumn;
import org.example.springboot.generator.gen.domain.vo.GenTableColumnVo;
import org.example.springboot.generator.gen.mapper.GenTableColumnMapper;
import org.example.springboot.generator.gen.service.IGenTableColumnService;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成业务字段服务实现类
 * </p>
 */
@Service
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, GenTableColumn> implements IGenTableColumnService, IBaseService<GenTableColumn> {
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public List<GenTableColumnVo> getList(GenTableColumnDto dto) {
        List<GenTableColumn> genTableColumnList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(genTableColumnList)) {
            return List.of();
        }
        // 组装VO
        return genTableColumnList.stream().map(item -> {
            GenTableColumnVo vo = new GenTableColumnVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
    }

    @Override
    public IPage<GenTableColumnVo> getPage(GenTableColumnDto dto) {
        Page<GenTableColumn> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 组装VO
        return info.convert(item -> {
            GenTableColumnVo vo = new GenTableColumnVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        });
    }

    @Override
    public GenTableColumnVo getOne(GenTableColumnDto dto) {
        GenTableColumn one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 组装VO
        GenTableColumnVo vo = new GenTableColumnVo();
        BeanUtils.copyProperties(one, vo);
        return vo;
    }

    @Override
    public void exportExcel(GenTableColumn entity, HttpServletResponse response) {
        ExcelUtils.exportExcel(response, this, entity, GenTableColumn.class, threadPoolTaskExecutor);
    }

    @Override
    public List<GenTableColumn> getPageList(GenTableColumn entity, IPage<GenTableColumn> page) {
        IPage<GenTableColumn> info = getWrapper(entity).page(page);
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return List.of();
        }
        return info.getRecords();
    }

    @Override
    public LambdaQueryChainWrapper<GenTableColumn> getWrapper(GenTableColumn entity) {
        LambdaQueryChainWrapper<GenTableColumn> wrapper = lambdaQuery()
                .eq(entity.getId() != null, GenTableColumn::getId, entity.getId())
                .eq(entity.getTableId() != null, GenTableColumn::getTableId, entity.getTableId())
                .like(StrUtil.isNotBlank(entity.getName()), GenTableColumn::getName, entity.getName())
                .eq(entity.getSort() != null, GenTableColumn::getSort, entity.getSort())
                .eq(StrUtil.isNotBlank(entity.getType()), GenTableColumn::getType, entity.getType())
                .eq(StrUtil.isNotBlank(entity.getJavaType()), GenTableColumn::getJavaType, entity.getJavaType())
                .like(StrUtil.isNotBlank(entity.getJavaField()), GenTableColumn::getJavaField, entity.getJavaField())
                .eq(entity.getIsPk() != null, GenTableColumn::getIsPk, entity.getIsPk())
                .eq(entity.getIsIncrement() != null, GenTableColumn::getIsIncrement, entity.getIsIncrement())
                .eq(entity.getIsRequired() != null, GenTableColumn::getIsRequired, entity.getIsRequired())
                .eq(entity.getIsInsert() != null, GenTableColumn::getIsInsert, entity.getIsInsert())
                .eq(entity.getIsEdit() != null, GenTableColumn::getIsEdit, entity.getIsEdit())
                .eq(entity.getIsList() != null, GenTableColumn::getIsList, entity.getIsList())
                .eq(entity.getIsQuery() != null, GenTableColumn::getIsQuery, entity.getIsQuery())
                .eq(StrUtil.isNotBlank(entity.getQueryType()), GenTableColumn::getQueryType, entity.getQueryType())
                .eq(StrUtil.isNotBlank(entity.getHtmlType()), GenTableColumn::getHtmlType, entity.getHtmlType())
                .eq(StrUtil.isNotBlank(entity.getDictType()), GenTableColumn::getDictType, entity.getDictType());
        if (entity instanceof GenTableColumnDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    GenTableColumn::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
