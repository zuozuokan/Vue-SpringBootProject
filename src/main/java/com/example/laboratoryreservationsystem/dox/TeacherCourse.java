package com.example.laboratoryreservationsystem.dox;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TeacherCourse")  // 映射数据库中的 TeacherCourse 表
public class TeacherCourse {
    private String teacherId;
    private String courseId;
    private String semester;
    private String major;
    private Integer courseNums;
    private String currentClass;

}