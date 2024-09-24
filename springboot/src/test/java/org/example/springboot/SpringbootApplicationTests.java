package org.example.springboot;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.springboot.common.Constants;
import org.example.springboot.domain.entity.system.LogLogin;
import org.example.springboot.mapper.LogLoginMapper;
import org.example.springboot.service.ILogLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class SpringbootApplicationTests {
    @Resource
    private LogLoginMapper logLoginMapper;

    @Test
    void contextLoads() {
        Page<LogLogin> page = new Page<>(10, Constants.PER_WRITE_ROW_COUNT);
        List<LogLogin> list = logLoginMapper.getPageList((page.getCurrent() - 1) * page.getSize(), page.getSize(), Wrappers.emptyWrapper());
        System.out.println(list.size());
    }
}
