package org.example.springboot.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.springboot.biz.domain.entity.Order;
import org.example.springboot.biz.domain.entity.Car;
import org.example.springboot.biz.domain.entity.CarAudite;
import org.example.springboot.biz.domain.entity.Address;
import org.example.springboot.biz.domain.dto.OrderDto;
import org.example.springboot.biz.domain.vo.OrderVo;
import org.example.springboot.biz.mapper.OrderMapper;
import org.example.springboot.biz.service.IOrderService;
import org.example.springboot.biz.service.ICarService;
import org.example.springboot.biz.service.ICarAuditeService;
import org.example.springboot.biz.service.IAddressService;
import jakarta.annotation.Resource;
import org.example.springboot.system.domain.entity.User;
import org.example.springboot.system.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单服务实现类
 * </p>
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Resource
    private ICarService carService;
    @Resource
    private ICarAuditeService carAuditeService;
    @Resource
    private IUserService userService;
    @Resource
    private IAddressService addressService;
    @Override
    public List<OrderVo> getList(OrderDto dto) {
        List<Order> orderList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(orderList)) {
            return List.of();
        }
        // 车辆
        List<Long> carIdList = orderList.stream().map(Order::getCarId).toList();
        List<Car> carList = carService.listByIds(carIdList);
        Map<Long, Car> carMap = carList.stream().collect(Collectors.toMap(Car::getId, item -> item));
        // 审核
        List<Long> carAuditeIdList = orderList.stream().map(Order::getCarAuditeId).toList();
        List<CarAudite> carAuditeList = carAuditeService.listByIds(carAuditeIdList);
        Map<Long, CarAudite> carAuditeMap = carAuditeList.stream().collect(Collectors.toMap(CarAudite::getId, item -> item));
        // 卖方
        List<Long> sellIdList = orderList.stream().map(Order::getSellId).toList();
        List<User> sellList = userService.listByIds(sellIdList);
        Map<Long, User> sellMap = sellList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 买方
        List<Long> buyIdList = orderList.stream().map(Order::getBuyId).toList();
        List<User> buyList = userService.listByIds(buyIdList);
        Map<Long, User> buyMap = buyList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 送货地址
        List<Long> addressIdList = orderList.stream().map(Order::getAddressId).toList();
        List<Address> addressList = addressService.listByIds(addressIdList);
        Map<Long, Address> addressMap = addressList.stream().collect(Collectors.toMap(Address::getId, item -> item));
        // 组装VO
        return orderList.stream().map(item -> {
            OrderVo vo = new OrderVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCar(carMap.getOrDefault(item.getCarId(), Car.builder().brand("已删除").build()));
            vo.setCarAudite(carAuditeMap.getOrDefault(item.getCarAuditeId(), CarAudite.builder().build()));
            vo.setSell(sellMap.getOrDefault(item.getSellId(), User.builder().name("已删除").build()));
            vo.setBuy(buyMap.getOrDefault(item.getBuyId(), User.builder().name("已删除").build()));
            vo.setAddress(addressMap.getOrDefault(item.getAddressId(), Address.builder().info("已删除").build()));
            return vo;
        }).toList();
    }

    @Override
    public IPage<OrderVo> getPage(OrderDto dto) {
        Page<Order> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 车辆
        List<Long> carIdList = info.getRecords().stream().map(Order::getCarId).toList();
        List<Car> carList = carService.listByIds(carIdList);
        Map<Long, Car> carMap = carList.stream().collect(Collectors.toMap(Car::getId, item -> item));
        // 审核
        List<Long> carAuditeIdList = info.getRecords().stream().map(Order::getCarAuditeId).toList();
        List<CarAudite> carAuditeList = carAuditeService.listByIds(carAuditeIdList);
        Map<Long, CarAudite> carAuditeMap = carAuditeList.stream().collect(Collectors.toMap(CarAudite::getId, item -> item));
        // 卖方
        List<Long> sellIdList = info.getRecords().stream().map(Order::getSellId).toList();
        List<User> sellList = userService.listByIds(sellIdList);
        Map<Long, User> sellMap = sellList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 买方
        List<Long> buyIdList = info.getRecords().stream().map(Order::getBuyId).toList();
        List<User> buyList = userService.listByIds(buyIdList);
        Map<Long, User> buyMap = buyList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 送货地址
        List<Long> addressIdList = info.getRecords().stream().map(Order::getAddressId).toList();
        List<Address> addressList = addressService.listByIds(addressIdList);
        Map<Long, Address> addressMap = addressList.stream().collect(Collectors.toMap(Address::getId, item -> item));
        // 组装VO
        return info.convert(item -> {
            OrderVo vo = new OrderVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCar(carMap.getOrDefault(item.getCarId(), Car.builder().brand("已删除").build()));
            vo.setCarAudite(carAuditeMap.getOrDefault(item.getCarAuditeId(), CarAudite.builder().build()));
            vo.setSell(sellMap.getOrDefault(item.getSellId(), User.builder().name("已删除").build()));
            vo.setBuy(buyMap.getOrDefault(item.getBuyId(), User.builder().name("已删除").build()));
            vo.setAddress(addressMap.getOrDefault(item.getAddressId(), Address.builder().info("已删除").build()));
            return vo;
        });
    }

    @Override
    public OrderVo getOne(OrderDto dto) {
        Order one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 车辆
        Car car = Optional.ofNullable(carService.getById(one.getCarId())).orElse(Car.builder().brand("已删除").build());
        // 审核
        CarAudite carAudite = Optional.ofNullable(carAuditeService.getById(one.getCarAuditeId())).orElse(CarAudite.builder().build());
        // 卖方
        User sell = Optional.ofNullable(userService.getById(one.getSellId())).orElse(User.builder().name("已删除").build());
        // 买方
        User buy = Optional.ofNullable(userService.getById(one.getBuyId())).orElse(User.builder().name("已删除").build());
        // 送货地址
        Address address = Optional.ofNullable(addressService.getById(one.getAddressId())).orElse(Address.builder().info("已删除").build());
        // 组装VO
        OrderVo vo = new OrderVo();
        BeanUtils.copyProperties(one, vo);
        vo.setCar(car);
        vo.setCarAudite(carAudite);
        vo.setSell(sell);
        vo.setBuy(buy);
        vo.setAddress(address);
        return vo;
    }

    /**
     * 组装查询包装器
     *
     * @param entity 订单
     * @return 结果
     */
    private LambdaQueryChainWrapper<Order> getWrapper(Order entity) {
        LambdaQueryChainWrapper<Order> wrapper = lambdaQuery()
                .eq(entity.getId() != null, Order::getId, entity.getId())
                .eq(entity.getCarId() != null, Order::getCarId, entity.getCarId())
                .eq(entity.getCarAuditeId() != null, Order::getCarAuditeId, entity.getCarAuditeId())
                .eq(entity.getSellId() != null, Order::getSellId, entity.getSellId())
                .eq(entity.getBuyId() != null, Order::getBuyId, entity.getBuyId())
                .eq(entity.getAddressId() != null, Order::getAddressId, entity.getAddressId())
                .eq(entity.getPayPrice() != null, Order::getPayPrice, entity.getPayPrice())
                .eq(entity.getPayStatus() != null, Order::getPayStatus, entity.getPayStatus())
                .like(StrUtil.isNotBlank(entity.getRefundReason()), Order::getRefundReason, entity.getRefundReason())
                .eq(entity.getRefundStatus() != null, Order::getRefundStatus, entity.getRefundStatus());
        if (entity instanceof OrderDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                Order::getCreateTime,
                startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
