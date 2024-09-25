package org.example.springboot.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.system.domain.entity.DictType;
import org.example.springboot.system.domain.dto.DictTypeDto;
import org.example.springboot.system.domain.vo.DictTypeVo;
import org.example.springboot.system.mapper.DictTypeMapper;
import org.example.springboot.system.service.IBaseService;
import org.example.springboot.system.service.IDictTypeService;
import jakarta.annotation.Resource;
import org.example.springboot.system.utils.ExcelUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典类型服务实现类
 * </p>
 */
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements IDictTypeService, IBaseService<DictType> {
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public List<DictTypeVo> getList(DictTypeDto dto) {
        List<DictType> dictTypeList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(dictTypeList)) {
            return List.of();
        }
        // 组装VO
        return dictTypeList.stream().map(item -> {
            DictTypeVo vo = new DictTypeVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
    }

    @Override
    public IPage<DictTypeVo> getPage(DictTypeDto dto) {
        Page<DictType> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 组装VO
        return info.convert(item -> {
            DictTypeVo vo = new DictTypeVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        });
    }

    @Override
    public DictTypeVo getOne(DictTypeDto dto) {
        DictType one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 组装VO
        DictTypeVo vo = new DictTypeVo();
        BeanUtils.copyProperties(one, vo);
        return vo;
    }

    @Override
    public void exportExcel(DictType dictType, HttpServletResponse response) {
        ExcelUtils.exportExcel(response, this, dictType, DictType.class, threadPoolTaskExecutor);
    }

    @Override
    public List<DictType> getPageList(DictType entity, IPage<DictType> page) {
        IPage<DictType> info = getWrapper(entity).page(page);
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return List.of();
        }
        return info.getRecords();
    }

    @Override
    public LambdaQueryChainWrapper<DictType> getWrapper(DictType entity) {
        LambdaQueryChainWrapper<DictType> wrapper = lambdaQuery()
                .eq(entity.getId() != null, DictType::getId, entity.getId())
                .like(StrUtil.isNotBlank(entity.getName()), DictType::getName, entity.getName())
                .like(StrUtil.isNotBlank(entity.getType()), DictType::getType, entity.getType())
                .eq(entity.getStatus() != null, DictType::getStatus, entity.getStatus());
        if (entity instanceof DictTypeDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    DictType::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
