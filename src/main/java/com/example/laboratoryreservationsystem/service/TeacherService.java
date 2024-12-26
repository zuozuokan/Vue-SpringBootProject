package com.example.laboratoryreservationsystem.service;

import com.example.laboratoryreservationsystem.dox.Teacher;
import com.example.laboratoryreservationsystem.dox.TeacherCourse;
import com.example.laboratoryreservationsystem.dto.LabReservation;
import com.example.laboratoryreservationsystem.exception.XException;
import com.example.laboratoryreservationsystem.repository.LabRepository;
import com.example.laboratoryreservationsystem.repository.LabReservationRepository;
import com.example.laboratoryreservationsystem.repository.TeacherCourseRepository;
import com.example.laboratoryreservationsystem.repository.TeacherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherService {
    // 注入持久层组件
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final TeacherCourseRepository teacherCourseRepository;
    private final LabRepository labRepository;
    private final LabReservationRepository labReservationRepository;

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

    // 基于工号和课程号查询课程
    public TeacherCourse getCourseByTeacherAccountAndCourseId(String teacherAccount, String courseId) {
         TeacherCourse teacherCourse = teacherCourseRepository.findCourseByTeacherAccountAndCourseId(teacherAccount, courseId);
         if(teacherCourse != null){
             return teacherCourse;
         }else{
             return null;
         }
    }

    // 教师添加预约
    public void addReservation(String teacherAccount, String courseId, String labId, String teacherName, String courseName, String tempReservation, String specifics, String week) {
        try {
            // 使用 ObjectMapper 将字符串 specifics 转换为 JSON 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 这里转为 Map，或者可以选择转为 JSONObject 等
            Map<String, Object> specificsMap = objectMapper.readValue(specifics, Map.class);
            String jsonString = objectMapper.writeValueAsString(specificsMap);
//            log.debug("specificsMap：" + jsonString);
//            log.debug("未执行");
            // 调用 repository 层插入数据
            labReservationRepository.addReservation(teacherAccount, courseId, labId, teacherName, courseName, tempReservation, jsonString, week);
//            log.debug("已执行");
        } catch (Exception e) {
            // 处理 JSON 转换异常
            e.printStackTrace();
        }
    }
    // 基于教师工号查询对应的预约信息
    public List<LabReservation> getReservationByTeacherAccount(String teacherAccount) {
        return labReservationRepository.findReservationByTeacherAccount(teacherAccount);
    }

    // 删除某个预约
    public void deleteReservationById(String tA,String week,String labId,String xingQi,String period) {
        labReservationRepository.deleteReservationByAllInfo(tA,week,labId,xingQi,period);
    }







}
