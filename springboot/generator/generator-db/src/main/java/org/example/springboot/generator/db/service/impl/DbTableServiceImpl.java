package org.example.springboot.generator.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.springboot.generator.db.domain.entity.DbTable;
import org.example.springboot.generator.db.mapper.DbTableMapper;
import org.example.springboot.generator.db.service.IDbTableService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据库表服务实现类
 * </p>
 */
@Service
public class DbTableServiceImpl extends ServiceImpl<DbTableMapper, DbTable> implements IDbTableService {

}
