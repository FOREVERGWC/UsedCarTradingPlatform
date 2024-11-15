package org.example.springboot.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.biz.domain.dto.CarDto;
import org.example.springboot.biz.domain.entity.Car;
import org.example.springboot.biz.domain.vo.CarVo;

import java.util.List;

/**
 * <p>
 * 二手车服务类
 * </p>
 */
public interface ICarService extends IService<Car> {
    /**
     * 查询二手车列表
     *
     * @param dto 二手车
     * @return 结果
     */
    List<CarVo> getList(CarDto dto);

    /**
     * 查询二手车分页
     *
     * @param dto 二手车
     * @return 结果
     */
    IPage<CarVo> getPage(CarDto dto);

    /**
     * 查询二手车
     *
     * @param dto 二手车
     * @return 结果
     */
    CarVo getOne(CarDto dto);
}
