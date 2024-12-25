package com.example.laboratoryreservationsystem.controller;

import com.example.laboratoryreservationsystem.dox.Teacher;
import com.example.laboratoryreservationsystem.dto.TeacherInfo;
import com.example.laboratoryreservationsystem.service.TeacherService;
import com.example.laboratoryreservationsystem.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class AdminController {
    // 注入teacher组件
    private final TeacherService teacherService;

    // 添加教师
    @PostMapping("addteacher")
    public ResultVO postTeacher(@RequestBody Teacher teacher){
        teacherService.addTeacher(teacher);
        return ResultVO.ok();

    }
    // 超级管理员更新指定教师账号的密码
    @PutMapping("teachers/{account}/password")
    public ResultVO putTeacherPassword(@PathVariable String account){
        teacherService.adminUpdatePassword(account);
        return ResultVO.ok();
    }

    // 获取所有教师
    @GetMapping("teachers")
    public ResultVO getTeachers(){
        List<Teacher> teachers =  teacherService.getAllTeachers();
        return ResultVO.success(teachers);
    }

    // 基于工号删除教师
    @PostMapping("delteacher")
    public ResultVO delTeacher(@RequestBody TeacherInfo teacherInfo){
        teacherService.deleteTeacher(teacherInfo.getAccount());
        return ResultVO.ok();
    }

}
