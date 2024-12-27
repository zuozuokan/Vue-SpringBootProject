package com.example.laboratoryreservationsystem.controller;

import com.example.laboratoryreservationsystem.dox.Course;
import com.example.laboratoryreservationsystem.dox.Lab;
import com.example.laboratoryreservationsystem.dox.LabManage;
import com.example.laboratoryreservationsystem.dox.Teacher;
import com.example.laboratoryreservationsystem.dto.TeacherInfo;
import com.example.laboratoryreservationsystem.service.TeacherService;
import com.example.laboratoryreservationsystem.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class AdminController {
    // 注入teacher组件
    private final TeacherService teacherService;

    // 添加教师
    @PostMapping("addteacher")
    public ResultVO postTeacher(@RequestBody Teacher teacher){
        log.debug(teacher.toString());
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
        // 删除教师课程表
        teacherService.deleteTeacherCourseByTeacherId(teacherInfo.getAccount());
        // 删除教师的预约表
        teacherService.deleteReservationByTeacherId(teacherInfo.getAccount());
        // 删除教师对应管理的管理表
        teacherService.deleteLabManageByStaffId(teacherInfo.getAccount());
        // 删除教师表
        teacherService.deleteTeacher(teacherInfo.getAccount());
        return ResultVO.ok();
    }

    // 添加实验室
    @PostMapping("addlab")
    public ResultVO addLab(@RequestBody Lab lab){
        teacherService.addLab(lab.getId(),lab.getName(),lab.getStaffId(),lab.getCapacity(),lab.getConfiguration(),lab.getStatus().toString());
        return ResultVO.ok();
    }

    // 删除实验室
    @PostMapping("dellab")
    public ResultVO delLab(@RequestBody Lab lab){
        // 删除实验室对应的预约表
        teacherService.deleteReservationByLabId(lab.getId());
        // 删除实验室
        teacherService.deleteLab(lab.getId());
        return ResultVO.ok();
    }

    //添加实验室管理人员
    @PostMapping("addlm")
    public ResultVO addLM(@RequestBody LabManage labManage){
        teacherService.addLM(labManage.getId(),labManage.getName(),labManage.getEmail(),labManage.getPosition(),labManage.getPhone());
        return ResultVO.ok();
    }
    // 删除实验室管理人员
    @PostMapping("dellm")
    public ResultVO delLM(@RequestBody LabManage labManage){
        teacherService.deleteLabByStaffId(labManage.getId());
        teacherService.deleteLM(labManage.getId());
        return ResultVO.ok();
    }

    // 超级管理员添加课程
    @PostMapping("addcourse")
    public ResultVO addCourse(@RequestBody Course course){
        teacherService.addCourse(course.getId(),course.getHours(),course.getExperimentHours(),course.getName(),course.getDescription());
        return ResultVO.ok();
    }
    // 超级管理员删除课程
    @PostMapping("delcourse")
    public ResultVO delCourse(@RequestBody Course course){
        // 删除对应的教师课程表
        teacherService.deleteTeacherCourseByCourseId(course.getId());
        // 删除对应的预约表
        teacherService.deleteReservationByCourseId(course.getId());
        // 删除课程
        teacherService.deleteCourse(course.getId());
        return ResultVO.ok();
    }
    // 超级管理员获取课程
    @GetMapping("getcourses")
    public ResultVO getCourse(){
        List<Course> courses = teacherService.findAllCourse();
        return ResultVO.success(courses);
    }
    // 超级管理员获取实验室
    @GetMapping("getlabs")
    public ResultVO getLab(){
        List<Lab> labs = teacherService.findAllLab();
        return ResultVO.success(labs);
    }
    // 超级管理员获取实验室负责人
    @GetMapping("getlms")
    public ResultVO getLM(){
        List<LabManage> labManages = teacherService.findAllLM();
        return ResultVO.success(labManages);
    }


}
