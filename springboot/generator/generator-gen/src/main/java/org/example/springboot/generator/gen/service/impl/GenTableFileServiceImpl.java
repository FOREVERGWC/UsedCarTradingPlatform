package org.example.springboot.generator.gen.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.common.service.IBaseService;
import org.example.springboot.common.utils.ExcelUtils;
import org.example.springboot.generator.gen.domain.dto.GenTableFileDto;
import org.example.springboot.generator.gen.domain.entity.GenTableFile;
import org.example.springboot.generator.gen.domain.vo.GenTableFileVo;
import org.example.springboot.generator.gen.mapper.GenTableFileMapper;
import org.example.springboot.generator.gen.service.IGenTableFileService;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成业务文件服务实现类
 * </p>
 */
@Service
public class GenTableFileServiceImpl extends ServiceImpl<GenTableFileMapper, GenTableFile> implements IGenTableFileService, IBaseService<GenTableFile> {
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public List<GenTableFileVo> getList(GenTableFileDto dto) {
        List<GenTableFile> genTableFileList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(genTableFileList)) {
            return List.of();
        }
        // 组装VO
        return genTableFileList.stream().map(item -> {
            GenTableFileVo vo = new GenTableFileVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).toList();
    }

    @Override
    public IPage<GenTableFileVo> getPage(GenTableFileDto dto) {
        Page<GenTableFile> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 组装VO
        return info.convert(item -> {
            GenTableFileVo vo = new GenTableFileVo();
            BeanUtils.copyProperties(item, vo);
            return vo;
        });
    }

    @Override
    public GenTableFileVo getOne(GenTableFileDto dto) {
        GenTableFile one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 组装VO
        GenTableFileVo vo = new GenTableFileVo();
        BeanUtils.copyProperties(one, vo);
        return vo;
    }

    @Override
    public void exportExcel(GenTableFile entity, HttpServletResponse response) {
        ExcelUtils.exportExcel(response, this, entity, GenTableFile.class, threadPoolTaskExecutor);
    }

    @Override
    public List<GenTableFile> getPageList(GenTableFile entity, IPage<GenTableFile> page) {
        IPage<GenTableFile> info = getWrapper(entity).page(page);
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return List.of();
        }
        return info.getRecords();
    }

    @Override
    public LambdaQueryChainWrapper<GenTableFile> getWrapper(GenTableFile entity) {
        LambdaQueryChainWrapper<GenTableFile> wrapper = lambdaQuery()
                .eq(entity.getId() != null, GenTableFile::getId, entity.getId())
                .eq(entity.getTableId() != null, GenTableFile::getTableId, entity.getTableId());
        if (entity instanceof GenTableFileDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    GenTableFile::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
