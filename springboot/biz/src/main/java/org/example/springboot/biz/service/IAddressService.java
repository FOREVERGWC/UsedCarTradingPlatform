package org.example.springboot.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.springboot.biz.domain.dto.AddressDto;
import org.example.springboot.biz.domain.entity.Address;
import org.example.springboot.biz.domain.vo.AddressVo;

import java.util.List;

/**
 * <p>
 * 用户地址服务类
 * </p>
 */
public interface IAddressService extends IService<Address> {
    /**
     * 查询用户地址列表
     *
     * @param dto 用户地址
     * @return 结果
     */
    List<AddressVo> getList(AddressDto dto);

    /**
     * 查询用户地址分页
     *
     * @param dto 用户地址
     * @return 结果
     */
    IPage<AddressVo> getPage(AddressDto dto);

    /**
     * 查询用户地址
     *
     * @param dto 用户地址
     * @return 结果
     */
    AddressVo getOne(AddressDto dto);
}
