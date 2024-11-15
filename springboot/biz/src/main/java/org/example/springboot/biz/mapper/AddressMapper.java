package org.example.springboot.biz.mapper;

import org.example.springboot.biz.domain.entity.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户地址Mapper接口
 * </p>
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

}
