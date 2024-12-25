package com.example.laboratoryreservationsystem.repository;

import com.example.laboratoryreservationsystem.dox.Teacher;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, String> {

    //根据教师id查询教师
    @Query("""
        SELECT * FROM Teacher t WHERE t.id = :teacherId
    """)
    Teacher findByTeacherId(String teacherId);

    //根据教师账号/工号查询教师
    @Query("""
        SELECT * FROM Teacher t WHERE t.account = :account
    """)
    Teacher findByTeacherAccount(String account);

    // 找到所有教师
    @Query("""
        SELECT * FROM Teacher t where t.account != 'admin'
    """)
    List<Teacher> findAllTeachers();

    // 根据教师工号删除教师对应的课程
    @Modifying
    @Transactional
    @Query("""
        delete from TeacherCourse tc where tc.teacher_account = :account
    """)
    void deleteByTeacherCourseAccount(String account);

    // 根据教师工号删除教师
    @Modifying
    @Transactional
    @Query("""
        delete from Teacher t where t.account = :account
    """)
    void deleteByTeacherAccount(String account);

    // 基于工号更新教师基本信息
    @Modifying
    @Transactional
    @Query("""
        update Teacher t set t.web_name = :webName, t.email = :email, t.phone = :phone where t.account = :account
""")
    void updateTeacherInfo( String webName, String email, String phone,String account);


}
