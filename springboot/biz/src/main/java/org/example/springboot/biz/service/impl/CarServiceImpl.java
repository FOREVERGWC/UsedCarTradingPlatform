package org.example.springboot.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.springboot.biz.domain.entity.Car;
import org.example.springboot.system.domain.entity.User;
import org.example.springboot.biz.domain.dto.CarDto;
import org.example.springboot.biz.domain.vo.CarVo;
import org.example.springboot.biz.mapper.CarMapper;
import org.example.springboot.biz.service.ICarService;
import org.example.springboot.system.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 二手车服务实现类
 * </p>
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {
    @Resource
    private IUserService userService;

    @Override
    public List<CarVo> getList(CarDto dto) {
        List<Car> carList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(carList)) {
            return List.of();
        }
        // 用户
        List<Long> userIdList = carList.stream().map(Car::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return carList.stream().map(item -> {
            CarVo vo = new CarVo();
            BeanUtils.copyProperties(item, vo);
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            return vo;
        }).toList();
    }

    @Override
    public IPage<CarVo> getPage(CarDto dto) {
        Page<Car> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 用户
        List<Long> userIdList = info.getRecords().stream().map(Car::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return info.convert(item -> {
            CarVo vo = new CarVo();
            BeanUtils.copyProperties(item, vo);
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            return vo;
        });
    }

    @Override
    public CarVo getOne(CarDto dto) {
        Car one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 用户
        User user = Optional.ofNullable(userService.getById(one.getUserId())).orElse(User.builder().name("已删除").build());
        // 组装VO
        CarVo vo = new CarVo();
        BeanUtils.copyProperties(one, vo);
        vo.setUser(user);
        return vo;
    }

    /**
     * 组装查询包装器
     *
     * @param entity 二手车
     * @return 结果
     */
    private LambdaQueryChainWrapper<Car> getWrapper(Car entity) {
        LambdaQueryChainWrapper<Car> wrapper = lambdaQuery()
                .eq(entity.getId() != null, Car::getId, entity.getId())
                .eq(entity.getUserId() != null, Car::getUserId, entity.getUserId())
                .like(StrUtil.isNotBlank(entity.getBrand()), Car::getBrand, entity.getBrand())
                .like(StrUtil.isNotBlank(entity.getModel()), Car::getModel, entity.getModel())
                .eq(entity.getYear() != null, Car::getYear, entity.getYear())
                .eq(entity.getMileage() != null, Car::getMileage, entity.getMileage())
                .eq(entity.getPrice() != null, Car::getPrice, entity.getPrice())
                .like(StrUtil.isNotBlank(entity.getColor()), Car::getColor, entity.getColor())
                .eq(entity.getFuelType() != null, Car::getFuelType, entity.getFuelType())
                .eq(entity.getTransmissionType() != null, Car::getTransmissionType, entity.getTransmissionType())
                .like(StrUtil.isNotBlank(entity.getCondition()), Car::getCondition, entity.getCondition())
                .eq(entity.getHasSold() != null, Car::getHasSold, entity.getHasSold())
                .eq(entity.getHasCheck() != null, Car::getHasCheck, entity.getHasCheck());
        if (entity instanceof CarDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                Car::getCreateTime,
                startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}