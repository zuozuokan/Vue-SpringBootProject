package com.example.laboratoryreservationsystem.controller;


import com.example.laboratoryreservationsystem.dox.Lab;
import com.example.laboratoryreservationsystem.dox.Teacher;
import com.example.laboratoryreservationsystem.dox.TeacherCourse;
import com.example.laboratoryreservationsystem.dto.LabDto;
import com.example.laboratoryreservationsystem.dto.LabReservation;
import com.example.laboratoryreservationsystem.dto.delReserDto;
import com.example.laboratoryreservationsystem.service.LabService;
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
    private final LabService labService;

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

    // 基于容量查询实验室
    @PostMapping("getLabByCapacity")
    public ResultVO getLabByCapacity(@RequestBody LabDto lab) {
        List<LabDto> labs = labService.getLabByCapacity(lab.getCapacity());
        return ResultVO.success(labs);
    }

    // 基于工号和课程号查询课程
    @PostMapping("checkCourse")
    public ResultVO getCourseByTeacherAccountAndCourseId(@RequestBody TeacherCourse teacherCourse) {
        TeacherCourse tc = teacherService.getCourseByTeacherAccountAndCourseId(teacherCourse.getTeacherAccount(),teacherCourse.getCourseId());
        if(tc != null) {
            return ResultVO.success("课程存在");
        }
        return ResultVO.error(404,"课程不存在");
    }

    // 教师添加预约
    @PostMapping("addReservation")
    public ResultVO addReservation(@RequestBody LabReservation lr) {
        teacherService.addReservation(lr.getTeacherAccount(),lr.getCourseId(),lr.getLabId(),lr.getTeacherName(),lr.getCourseName(),lr.getTempReservation(),lr.getSpecifics(),lr.getWeek());
        return ResultVO.ok();
    }

    // 教师查看预约
    @PostMapping("getReservation")
    public ResultVO getReservation(@RequestBody LabReservation tr) {
        List<LabReservation> labReservations = teacherService.getReservationByTeacherAccount(tr.getTeacherAccount());
        return ResultVO.success(labReservations);
    }

    // 教师删除某个预约
    @PostMapping("deleteReservation")
    public ResultVO deleteReservation(@RequestBody delReserDto dr) {
        teacherService.deleteReservationById(dr.getTeacherAccount(),dr.getWeek(),dr.getLabId(),dr.getXingQi(),dr.getPeriod());
        return ResultVO.ok();
    }

}
