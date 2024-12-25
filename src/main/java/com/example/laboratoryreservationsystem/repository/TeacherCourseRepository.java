package com.example.laboratoryreservationsystem.repository;

import com.example.laboratoryreservationsystem.dox.Course;
import com.example.laboratoryreservationsystem.dox.TeacherCourse;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TeacherCourseRepository extends CrudRepository<TeacherCourse, String> {
    //根据教师工号查询教师课程
    @Query("""
        SELECT * FROM TeacherCourse WHERE teacher_account = :teacherAccount
""")
    List<TeacherCourse> findCourseByTeacherAccount(String teacherAccount);

    // 根据教师工号添加课程
    @Modifying
    @Transactional
    @Query("""
       INSERT INTO TeacherCourse(teacher_account,course_id,semester,course_nums,current_class,course_name,experiment_hours,hours)
       VALUES (:teacherAccount,:courseId,:semester,:courseNums,:currentClass,:courseName,:experimentHours,:hours)
""")
    void addCourseByTeacherAccount(String teacherAccount, String courseId, String semester, int courseNums, String currentClass,String courseName,int experimentHours,int hours);

    // 根据教师工号和课程号删除课程
    @Modifying
    @Transactional
    @Query("""
       DELETE FROM TeacherCourse WHERE teacher_account = :teacherAccount AND course_id = :courseId
""")
    void deleteCourseByTeacherAccountAndCourseId(String teacherAccount, String courseId);
}
