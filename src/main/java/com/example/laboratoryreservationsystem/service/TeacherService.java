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

    // 基于教师 account,查询自己的课表
    @Transactional
    public List<TeacherCourse> getCourseByTeacher(String account){

        return teacherCourseRepository.findCourseByTeacherAccount(account);
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

    // 超级管理员删除教师
    public void deleteTeacher(String account) {
        // 先删除课程
        teacherRepository.deleteByTeacherCourseAccount(account);
        // 再删除教师
        teacherRepository.deleteByTeacherAccount(account);

    }

    // 教师添加课程
    public void addCourse(String teacherAccount, String courseId, String semester, int courseNums, String currentClass,String courseName,int experimentHours,int hours) {
        teacherCourseRepository.addCourseByTeacherAccount(teacherAccount, courseId, semester, courseNums, currentClass,courseName,experimentHours,hours);

    }
    // 教师更新基本信息
    public void updateTeacherInfo(String teacherwebName, String teacherEmail, String teacherPhone,String teacherAccount) {

        teacherRepository.updateTeacherInfo(teacherwebName, teacherEmail, teacherPhone,teacherAccount);
    }

    // 基于工号和课程号删除课程
    public void deleteCourseByTeacherAccountAndCourseId(String teacherAccount, String courseId) {
        teacherCourseRepository.deleteCourseByTeacherAccountAndCourseId(teacherAccount, courseId);
    }



}
