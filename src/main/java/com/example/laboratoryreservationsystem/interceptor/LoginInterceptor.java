package com.example.laboratoryreservationsystem.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import com.example.laboratoryreservationsystem.component.JWTComponent;
import com.example.laboratoryreservationsystem.exception.Code;
import com.example.laboratoryreservationsystem.exception.XException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    private final JWTComponent jwtComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 处理预检请求，避免OPTIONS请求被拦截
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 获取请求头中的Authorization
        String token = request.getHeader("Authorization");

        // 判断Authorization头是否为空或者没有以 "Bearer " 开头
        if (token == null || !token.startsWith("Bearer ")) {
            logger.warn("Authorization token is missing or invalid.");
            throw XException.builder().code(Code.UNAUTHORIZED).message("无法接收到token,请先登录").build();
        }

        // 提取实际的token（去掉"Bearer "）
        token = token.substring(7);

        try {
            // 解码Token
            DecodedJWT decode = jwtComponent.decode(token);
            // 获取用户ID和角色信息
            String uid = decode.getClaim("tid").asString();
            String role = decode.getClaim("role").asString();

            // 将用户ID和角色信息存入请求属性中，后续可以在Controller中使用
            request.setAttribute("tid", uid);
            request.setAttribute("role", role);

            logger.info("User {} with role {} is authenticated.", uid, role);

        } catch (Exception e) {
            logger.error("Token validation failed.", e);
            throw XException.builder().code(Code.UNAUTHORIZED).message("Token验证失败").build();
        }

        return true;
    }

}
