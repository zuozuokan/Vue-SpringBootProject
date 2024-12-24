package com.example.laboratoryreservationsystem.service;

import com.example.laboratoryreservationsystem.dox.Teacher;
import com.example.laboratoryreservationsystem.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InitService {
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        String account = "admin";
        String password = "admin";
        long count = teacherRepository.count();
        if (count > 0) {
            return;
        }
        Teacher t = Teacher.builder()
                .name("超级管理员")
                .email("admin@163.com")
                .account(account)
                .password(passwordEncoder.encode(password))
                .role(Teacher.ADMIN)
                .build();

        teacherRepository.save(t);
    }
}
