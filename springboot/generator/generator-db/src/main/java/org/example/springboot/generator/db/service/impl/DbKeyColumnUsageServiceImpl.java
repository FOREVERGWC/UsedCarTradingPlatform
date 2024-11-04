package org.example.springboot.generator.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.springboot.generator.db.domain.entity.DbKeyColumnUsage;
import org.example.springboot.generator.db.mapper.DbKeyColumnUsageMapper;
import org.example.springboot.generator.db.service.IDbKeyColumnUsageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 键列使用情况服务实现类
 * </p>
 */
@Service
public class DbKeyColumnUsageServiceImpl extends ServiceImpl<DbKeyColumnUsageMapper, DbKeyColumnUsage> implements IDbKeyColumnUsageService {

}
