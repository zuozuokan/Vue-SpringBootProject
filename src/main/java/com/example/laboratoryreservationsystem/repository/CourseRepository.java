package com.example.laboratoryreservationsystem.repository;

import com.example.laboratoryreservationsystem.dox.Course;
import com.example.laboratoryreservationsystem.dox.Lab;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository  extends CrudRepository<Course, String> {

    // 超级管理员添加课程
    @Modifying
    @Transactional
    @Query("""
        INSERT INTO Course (id,hours,experiment_hours,name,description) 
        VALUES (:id,:hours,:experiment_hours,:name,:description)
""")
    void addCourse(String id, int hours, int experiment_hours, String name, String description);

    // 超级管理员删除课程
    @Modifying
    @Transactional
    @Query("DELETE FROM Course WHERE id = :id")
    void deleteCourse(String id);

    // 超级管理员获取课程
    @Query("SELECT * FROM Course")
    List<Course> findAllCourse();




}
