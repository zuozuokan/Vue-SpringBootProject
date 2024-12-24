package com.example.laboratoryreservationsystem.component;

import lombok.RequiredArgsConstructor;
import com.example.laboratoryreservationsystem.interceptor.AdminInterceptor;
import com.example.laboratoryreservationsystem.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;
    private final AdminInterceptor adminIntercesptor;

    // 跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 配置所有接口允许跨域
                .allowedOrigins("http://localhost:5173")  // 允许来自前端的请求
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许的请求方法
                .allowedHeaders("Content-Type", "Authorization","*")  // 允许的请求头
                .exposedHeaders("token", "role")  // 允许前端访问的响应头
                .allowCredentials(true);  // 是否允许发送 Cookies 等凭证信息
    }

    // 拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login");
        registry.addInterceptor(adminIntercesptor)
                .addPathPatterns("/api/admin/**");

    }
}
