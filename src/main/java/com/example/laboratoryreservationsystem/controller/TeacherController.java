package com.example.laboratoryreservationsystem.controller;


import com.example.laboratoryreservationsystem.dox.Teacher;
import com.example.laboratoryreservationsystem.service.TeacherService;
import com.example.laboratoryreservationsystem.vo.ResultVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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


}
