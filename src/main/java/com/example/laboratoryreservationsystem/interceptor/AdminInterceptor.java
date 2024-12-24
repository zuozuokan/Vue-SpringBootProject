package com.example.laboratoryreservationsystem.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.laboratoryreservationsystem.dox.Teacher;
import com.example.laboratoryreservationsystem.exception.Code;
import com.example.laboratoryreservationsystem.exception.XException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 处理预检请求，避免OPTIONS请求被拦截
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 获取请求中的角色信息
        String role = (String) request.getAttribute("role");

        if (role == null) {
            logger.warn("Role is missing in request attributes. User is not authenticated.");
            throw XException.builder().code(Code.UNAUTHORIZED).message("未认证，无法获取角色").build();
        }

        // 检查角色是否为管理员
        if (Teacher.ADMIN.equals(role)) {
            return true; // 如果是管理员，继续处理请求
        }

        // 如果角色不是管理员，则抛出403 Forbidden错误
        logger.warn("Access denied for non-admin user. Role: {}", role);
        throw XException.builder().code(Code.FORBIDDEN).message("权限不足").build();
    }
}
