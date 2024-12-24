package com.example.laboratoryreservationsystem.repository;

import com.example.laboratoryreservationsystem.dox.Course;
import com.example.laboratoryreservationsystem.dox.TeacherCourse;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherCourseRepository extends CrudRepository<TeacherCourse, String> {
    //根据教师工号查询教师课程
    @Query("""
        SELECT * FROM TeacherCourse WHERE teacher_id = :teacherId
""")
    List<TeacherCourse> findCourseByTeacherId(String teacherId);


}
