package org.example.springboot.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.springboot.biz.domain.dto.AddressDto;
import org.example.springboot.biz.domain.entity.Address;
import org.example.springboot.biz.domain.vo.AddressVo;
import org.example.springboot.biz.mapper.AddressMapper;
import org.example.springboot.biz.service.IAddressService;
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
 * 用户地址服务实现类
 * </p>
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {
    @Resource
    private IUserService userService;

    @Override
    public List<AddressVo> getList(AddressDto dto) {
        List<Address> addressList = getWrapper(dto).list();
        if (CollectionUtil.isEmpty(addressList)) {
            return List.of();
        }
        // 用户
        List<Long> userIdList = addressList.stream().map(Address::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return addressList.stream().map(item -> {
            AddressVo vo = new AddressVo();
            BeanUtils.copyProperties(item, vo);
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            return vo;
        }).toList();
    }

    @Override
    public IPage<AddressVo> getPage(AddressDto dto) {
        Page<Address> info = getWrapper(dto).page(new Page<>(dto.getPageNo(), dto.getPageSize()));
        if (CollectionUtil.isEmpty(info.getRecords())) {
            return new Page<>(dto.getPageNo(), dto.getPageSize(), 0);
        }
        // 用户
        List<Long> userIdList = info.getRecords().stream().map(Address::getUserId).toList();
        List<User> userList = userService.listByIds(userIdList);
        Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, item -> item));
        // 组装VO
        return info.convert(item -> {
            AddressVo vo = new AddressVo();
            BeanUtils.copyProperties(item, vo);
            vo.setUser(userMap.getOrDefault(item.getUserId(), User.builder().name("已删除").build()));
            return vo;
        });
    }

    @Override
    public AddressVo getOne(AddressDto dto) {
        Address one = getWrapper(dto).one();
        if (one == null) {
            return null;
        }
        // 用户
        User user = Optional.ofNullable(userService.getById(one.getUserId())).orElse(User.builder().name("已删除").build());
        // 组装VO
        AddressVo vo = new AddressVo();
        BeanUtils.copyProperties(one, vo);
        vo.setUser(user);
        return vo;
    }

    /**
     * 组装查询包装器
     *
     * @param entity 用户地址
     * @return 结果
     */
    private LambdaQueryChainWrapper<Address> getWrapper(Address entity) {
        LambdaQueryChainWrapper<Address> wrapper = lambdaQuery()
                .eq(entity.getId() != null, Address::getId, entity.getId())
                .eq(entity.getUserId() != null, Address::getUserId, entity.getUserId())
                .like(StrUtil.isNotBlank(entity.getInfo()), Address::getInfo, entity.getInfo())
                .eq(entity.getDeleted() != null, Address::getDeleted, entity.getDeleted());
        if (entity instanceof AddressDto dto) {
            Map<String, Object> params = dto.getParams();
            // 创建时间
            Object startCreateTime = params == null ? null : params.get("startCreateTime");
            Object endCreateTime = params == null ? null : params.get("endCreateTime");

            wrapper.between(ObjectUtil.isAllNotEmpty(startCreateTime, endCreateTime),
                    Address::getCreateTime,
                    startCreateTime, endCreateTime);
        }
        return wrapper;
    }
}
