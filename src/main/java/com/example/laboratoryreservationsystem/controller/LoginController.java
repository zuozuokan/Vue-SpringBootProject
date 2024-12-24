package com.example.laboratoryreservationsystem.controller;

import com.example.laboratoryreservationsystem.component.JWTComponent;
import com.example.laboratoryreservationsystem.dox.Teacher;
import com.example.laboratoryreservationsystem.exception.XException;
import com.example.laboratoryreservationsystem.repository.TeacherRepository;
import com.example.laboratoryreservationsystem.service.TeacherService;
import com.example.laboratoryreservationsystem.vo.ResultVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/")
@RequiredArgsConstructor
public class LoginController {
    // 注入组件
    private final TeacherService teacherService;
    private final PasswordEncoder passwordEncoder;
    private final JWTComponent jwtComponent;


    @PostMapping("login")
    public ResultVO login(@RequestBody Teacher teacher, HttpServletResponse response) {
        // 基于教师账号查询教师
        Teacher t = teacherService.getTeacherByAccount(teacher.getAccount());
        // 验证
        if (t == null || !passwordEncoder.matches(teacher.getPassword(), t.getPassword())) {

            return ResultVO.error(XException.ErrorCode,"账号或密码错误");
        }

        // 将教师id和角色放入token
        String token = jwtComponent.encode(Map.of("tid",t.getId(),"role",t.getRole()));
        // 将token放入响应头
        response.setHeader("token",token);
        response.setHeader("role", t.getRole());

        // 在后端日志中打印 token 和 role，确认它们的值
        log.debug("Generated token: " + token);
        log.debug("Role: " + t.getRole());

        // 将teacher放入data中返回
        return ResultVO.success(t);

    }
}
