package com.example.laboratoryreservationsystem.controller;


import com.example.laboratoryreservationsystem.dox.Teacher;
import com.example.laboratoryreservationsystem.dox.TeacherCourse;
import com.example.laboratoryreservationsystem.service.TeacherService;
import com.example.laboratoryreservationsystem.vo.ResultVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    // 更新自己的密码
    @PatchMapping("updatePassword")
    public ResultVO updatePassword(@RequestBody Teacher teacher, @RequestAttribute("tid") String tid) {
        // 基于tid，和发送的请求体里面的密码，更改密码
        teacherService.updatePassword(tid,teacher.getPassword());
        return ResultVO.ok();

    }
    // 添加课程
    @PostMapping("addCourse")
    public ResultVO addCourse(@RequestBody TeacherCourse teacherCourse) {
        teacherService.addCourse(teacherCourse.getTeacherAccount(),teacherCourse.getCourseId(),teacherCourse.getSemester(),teacherCourse.getCourseNums(),teacherCourse.getCurrentClass(),teacherCourse.getCourseName(),teacherCourse.getExperimentHours(),teacherCourse.getHours());
        return ResultVO.ok();
    }

    // 更新自己基本信息
    @PostMapping("updateInfo")
    public ResultVO updateInfo(@RequestBody Teacher teacher) {
        teacherService.updateTeacherInfo(teacher.getWebName(),teacher.getEmail(),teacher.getPhone(),teacher.getAccount());
        return ResultVO.ok();
    }
    // 查看自己的课程
    @PostMapping("getCourses")
    public ResultVO getCourses(@RequestBody TeacherCourse teacherCourse) {
        List<TeacherCourse> teacherCourses = teacherService.getCourseByTeacher(teacherCourse.getTeacherAccount());
        return ResultVO.success(teacherCourses);
    }
    // 基于工号和课程号删除一个课程
    @PostMapping("delCourse")
    public ResultVO deleteCourse(@RequestBody TeacherCourse teacherCourse) {
        teacherService.deleteCourseByTeacherAccountAndCourseId(teacherCourse.getTeacherAccount(),teacherCourse.getCourseId());
        return ResultVO.ok();
    }

}
