package com.example.laboratoryreservationsystem.service;

import com.example.laboratoryreservationsystem.dox.Teacher;
import com.example.laboratoryreservationsystem.dox.TeacherCourse;
import com.example.laboratoryreservationsystem.exception.XException;
import com.example.laboratoryreservationsystem.repository.TeacherCourseRepository;
import com.example.laboratoryreservationsystem.repository.TeacherRepository;
import com.example.laboratoryreservationsystem.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherService {
    // 注入持久层组件
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final TeacherCourseRepository teacherCourseRepository;

    // 基于教师id查找教师
    public Teacher getTeacherById(String id){

        return teacherRepository.findByTeacherId(id);
    }
    // 基于教师账号查找教师
    public Teacher getTeacherByAccount(String account){
        return teacherRepository.findByTeacherAccount(account);
    }

    // 基于教师id,更改密码
    @Transactional
    public void updatePassword(String teacherId,String password){
       Teacher teacher =  teacherRepository.findByTeacherId(teacherId);
       if (teacher == null) {

           throw XException.builder()
                   .number(XException.ErrorCode)
                   .message("教师不存在")
                   .build();
       }
       teacher.setPassword(passwordEncoder.encode(password));
       teacherRepository.save(teacher);
       log.debug("密码更新成功！");
    }

    // 超级管理员基于教师账号，更新密码，新密码同账号
    @Transactional
    public void adminUpdatePassword(String account) {
        Teacher teacher = teacherRepository.findByTeacherAccount(account);
        if (teacher == null) {
            throw XException.builder()
                    .number(XException.ErrorCode)
                    .message("教师不存在")
                    .build();
        }
        teacher.setPassword(passwordEncoder.encode(account));
        teacherRepository.save(teacher);
        log.debug("管理员更新密码成功！");
    }

    // 基于教师id,查询自己的课表
    @Transactional
    public List<TeacherCourse> getCourseByTeacher(String teacherId){

        return teacherCourseRepository.findCourseByTeacherId(teacherId);
    }

    // 超级管理员添加教师
    @Transactional
    public void addTeacher(Teacher teacher){
        // 基于账号编码
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacherRepository.save(teacher);

    }
    // 找到所有教师
    @Transactional
    public List<Teacher> getAllTeachers(){

        log.debug("数据库返回ok！");
        return teacherRepository.findAllTeachers();

    }



}
