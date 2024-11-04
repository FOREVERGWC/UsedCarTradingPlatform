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
import org.example.springboot.generator.gen.domain.dto.GenTableDto;
import org.example.springboot.generator.gen.domain.entity.GenTable;
import org.example.springboot.generator.gen.domain.vo.GenTableVo;
import org.example.springboot.generator.gen.mapper.GenTableMapper;
import org.example.springboot.generator.gen.service.IGenTableService;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成业务服务实现类
 * </p>
 */
@Service
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable> implements IGenTableService, IBaseService<GenTable> {
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public List<GenTableVo> getList(GenTableDto dto) {
        List<GenTable> genTableList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(genTableList)) {
            return List.of();
        }
        // 组装VO
        return genTableList.stream().map(item -> {
            GenTableVo vo = new GenTableVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
    }

    @Override
    public IPage<GenTableVo> getPage(GenTableDto dto) {
        Page<GenTable> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 组装VO
        return info.convert(item -> {
            GenTableVo vo = new GenTableVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        });
    }

    @Override
    public GenTableVo getOne(GenTableDto dto) {
        GenTable one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 组装VO
        GenTableVo vo = new GenTableVo();
        BeanUtils.copyProperties(one, vo);
        return vo;
    }

    @Override
    public void exportExcel(GenTable entity, HttpServletResponse response) {
        ExcelUtils.exportExcel(response, this, entity, GenTable.class, threadPoolTaskExecutor);
    }

    @Override
    public List<GenTable> getPageList(GenTable entity, IPage<GenTable> page) {
        IPage<GenTable> info = getWrapper(entity).page(page);
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return List.of();
        }
        return info.getRecords();
    }

    @Override
    public LambdaQueryChainWrapper<GenTable> getWrapper(GenTable entity) {
        LambdaQueryChainWrapper<GenTable> wrapper = lambdaQuery()
                .eq(entity.getId() != null, GenTable::getId, entity.getId())
                .eq(StrUtil.isNotBlank(entity.getName()), GenTable::getName, entity.getName());
        if (entity instanceof GenTableDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    GenTable::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
