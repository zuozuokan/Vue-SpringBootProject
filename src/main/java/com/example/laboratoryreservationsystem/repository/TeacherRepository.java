package com.example.laboratoryreservationsystem.repository;

import com.example.laboratoryreservationsystem.dox.Teacher;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

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
        SELECT * FROM Teacher
    """)
    List<Teacher> findAllTeachers();



}
