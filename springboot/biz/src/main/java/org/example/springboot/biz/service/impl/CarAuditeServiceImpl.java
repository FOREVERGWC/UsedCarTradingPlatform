package org.example.springboot.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.springboot.biz.domain.dto.CarAuditeDto;
import org.example.springboot.biz.domain.entity.Car;
import org.example.springboot.biz.domain.entity.CarAudite;
import org.example.springboot.biz.domain.vo.CarAuditeVo;
import org.example.springboot.biz.mapper.CarAuditeMapper;
import org.example.springboot.biz.service.ICarAuditeService;
import org.example.springboot.biz.service.ICarService;
import org.example.springboot.system.domain.entity.User;
import org.example.springboot.system.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 二手车审核服务实现类
 * </p>
 */
@Service
public class CarAuditeServiceImpl extends ServiceImpl<CarAuditeMapper, CarAudite> implements ICarAuditeService {
    @Resource
    private ICarService carService;
    @Resource
    private IUserService userService;

    @Transactional
    @Override
    public boolean save(CarAudite entity) {
        Car car = carService.getById(entity.getCarId());
        car.setHasCheck(true);
        carService.updateById(car);
        return super.save(entity);
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(CarAudite entity) {
        if (entity.getId() == null) {
            return save(entity);
        }
        return super.updateById(entity);
    }

    @Override
    public List<CarAuditeVo> getList(CarAuditeDto dto) {
        List<CarAudite> carAuditeList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(carAuditeList)) {
            return List.of();
        }
        // 车辆
        List<Long> carIdList = carAuditeList.stream().map(CarAudite::getCarId).toList();
        List<Car> carList = carService.listByIds(carIdList);
        Map<Long, Car> carMap = carList.stream().collect(Collectors.toMap(Car::getId, item -> item));
        // 卖方
        List<Long> userIdList = carAuditeList.stream().map(CarAudite::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 审核员
        List<Long> auditorIdList = carAuditeList.stream().map(CarAudite::getAuditorId).toList();
        List<User> auditorList = userService.listByIds(auditorIdList);
        Map<Long, User> auditorMap = auditorList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return carAuditeList.stream().map(item -> {
            CarAuditeVo vo = new CarAuditeVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCar(carMap.getOrDefault(item.getCarId(), Car.builder().brand("已删除").build()));
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            vo.setAuditor(auditorMap.getOrDefault(item.getAuditorId(), User.builder().name("已删除").build()));
            return vo;
        }).toList();
    }

    @Override
    public IPage<CarAuditeVo> getPage(CarAuditeDto dto) {
        Page<CarAudite> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 车辆
        List<Long> carIdList = info.getRecords().stream().map(CarAudite::getCarId).toList();
        List<Car> carList = carService.listByIds(carIdList);
        Map<Long, Car> carMap = carList.stream().collect(Collectors.toMap(Car::getId, item -> item));
        // 卖方
        List<Long> userIdList = info.getRecords().stream().map(CarAudite::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 审核员
        List<Long> auditorIdList = info.getRecords().stream().map(CarAudite::getAuditorId).toList();
        List<User> auditorList = userService.listByIds(auditorIdList);
        Map<Long, User> auditorMap = auditorList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return info.convert(item -> {
            CarAuditeVo vo = new CarAuditeVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCar(carMap.getOrDefault(item.getCarId(), Car.builder().brand("已删除").build()));
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            vo.setAuditor(auditorMap.getOrDefault(item.getAuditorId(), User.builder().name("已删除").build()));
            return vo;
        });
    }

    @Override
    public CarAuditeVo getOne(CarAuditeDto dto) {
        CarAudite one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 车辆
        Car car = Optional.ofNullable(carService.getById(one.getCarId())).orElse(Car.builder().brand("已删除").build());
        // 卖方
        User user = Optional.ofNullable(userService.getById(one.getUserId())).orElse(User.builder().name("已删除").build());
        // 审核员
        User auditor = Optional.ofNullable(userService.getById(one.getAuditorId())).orElse(User.builder().name("已删除").build());
        // 组装VO
        CarAuditeVo vo = new CarAuditeVo();
        BeanUtils.copyProperties(one, vo);
        vo.setCar(car);
        vo.setUser(user);
        vo.setAuditor(auditor);
        return vo;
    }

    /**
     * 组装查询包装器
     *
     * @param entity 二手车审核
     * @return 结果
     */
    private LambdaQueryChainWrapper<CarAudite> getWrapper(CarAudite entity) {
        LambdaQueryChainWrapper<CarAudite> wrapper = lambdaQuery()
                .eq(entity.getId() != null, CarAudite::getId, entity.getId())
                .eq(entity.getCarId() != null, CarAudite::getCarId, entity.getCarId())
                .eq(entity.getUserId() != null, CarAudite::getUserId, entity.getUserId())
                .eq(entity.getAuditorId() != null, CarAudite::getAuditorId, entity.getAuditorId())
                .like(StrUtil.isNotBlank(entity.getInfo()), CarAudite::getInfo, entity.getInfo());
        if (entity instanceof CarAuditeDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    CarAudite::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
