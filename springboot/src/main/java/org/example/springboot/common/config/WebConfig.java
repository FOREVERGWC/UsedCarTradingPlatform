package org.example.springboot.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 服务配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Resource
//    private JwtInterceptor jwtInterceptor;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry
//                .addInterceptor(jwtInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/")
//                .excludePathPatterns("/captcha", "/register/code")
//                .excludePathPatterns("/login", "/login/wechat", "/register", "/password/reset")
//                .excludePathPatterns("/file/**")
//                .excludePathPatterns("/static/**")
//                .excludePathPatterns("/doc.html", "/favicon.ico", "/webjars/**", "/swagger-resources", "/v3/api-docs/**");
//    }

    /**
     * 静态资源映射
     *
     * @param registry 注册器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
