package org.example.springboot.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.biz.domain.dto.CarAuditeDto;
import org.example.springboot.biz.domain.entity.CarAudite;
import org.example.springboot.biz.domain.vo.CarAuditeVo;

import java.util.List;

/**
 * <p>
 * 二手车审核服务类
 * </p>
 */
public interface ICarAuditeService extends IService<CarAudite> {
    /**
     * 查询二手车审核列表
     *
     * @param dto 二手车审核
     * @return 结果
     */
    List<CarAuditeVo> getList(CarAuditeDto dto);

    /**
     * 查询二手车审核分页
     *
     * @param dto 二手车审核
     * @return 结果
     */
    IPage<CarAuditeVo> getPage(CarAuditeDto dto);

    /**
     * 查询二手车审核
     *
     * @param dto 二手车审核
     * @return 结果
     */
    CarAuditeVo getOne(CarAuditeDto dto);
}
