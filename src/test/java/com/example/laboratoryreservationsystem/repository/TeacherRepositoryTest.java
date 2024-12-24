package com.example.laboratoryreservationsystem.repository;

import com.example.laboratoryreservationsystem.dox.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void save() {
        var teacher = Teacher.builder()
                .name("张三")
                .email("zhangsan@163.com")
                .account("111")
                .password("123456")
                .role("abc")
                .build();
        teacherRepository.save(teacher);
    }
    @Test
    void findById() {

    }

//    @Test
//    void findByTeacherId() {
//    }
//
//    @Test
//    void findByTeacherAccount() {
//    }
//
//    @Test
//    void findAllTeachers() {
//    }
}