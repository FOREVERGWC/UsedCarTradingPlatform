//package org.example.springboot.biz.common.aspectj;
//
//import jakarta.annotation.Resource;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.example.springboot.biz.domain.dto.ArticleDto;
//import org.example.springboot.biz.service.cache.IArticleCacheService;
//import org.example.springboot.system.utils.UserUtils;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 文章浏览量切面
// */
//@Aspect
//@Component
//public class ViewAspectj {
//    @Resource
//    private IArticleCacheService articleCacheService;
//
//    @Pointcut("execution(public * org.example.springboot.biz.controller.ArticleController.getOne(..))")
//    public void getOneAspect() {
//    }
//
//    @After("getOneAspect()")
//    public void afterGet(JoinPoint point) {
//        Object arg = point.getArgs()[0];
//        if (!(arg instanceof ArticleDto dto)) {
//            return;
//        }
//        // TODO 游客模式有问题
//        Long userId = UserUtils.getLoginUserId();
//        articleCacheService.addViewCount(dto.getId());
//        articleCacheService.addUserHistory(userId, dto.getId());
//        List<Map<Object, Object>> history = articleCacheService.getUserHistory(userId, 1, 100);
//        System.out.println("浏览文章ID：" + dto.getId() + " 用户ID：" + userId);
//        System.out.println("当前浏览量：" + articleCacheService.getViewCount(dto.getId()));
//        history.forEach(System.out::println);
//    }
//}
