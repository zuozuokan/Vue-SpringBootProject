package com.example.laboratoryreservationsystem.repository;

import com.example.laboratoryreservationsystem.dto.LabReservation;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LabReservationRepository extends CrudRepository<LabReservation, String> {
    // 查询对应实验室预约情况
    @Query("""
        SELECT * FROM Reservation r WHERE r.lab_id = :labId AND r.week = :week
""")
    List<LabReservation> findLabReservationByIdAndWeek(String labId, String week);

    // 教师新增预约
    @Modifying
    @Transactional
    @Query("""
    INSERT INTO Reservation (teacher_account, course_id, lab_id, teacher_name, course_name, temp_reservation, specifics, week)
    VALUES (:teacher_account, :course_id, :lab_id, :teacher_name, :course_name, :temp_reservation, :specifics, :week)
""")
    void addReservation(String teacher_account, String course_id, String lab_id, String teacher_name, String course_name, String temp_reservation, Object specifics, String week);

    // 查询教师的所有预约
    @Query("""
        SELECT r.*,l.name FROM Reservation r join Lab l on r.lab_id = l.id WHERE r.teacher_account = :teacherAccount
    """)
    List<LabReservation> findReservationByTeacherAccount(String teacherAccount);

    // 删除教师的某个预约
    @Modifying
    @Transactional
    @Query("""
        DELETE FROM Reservation r WHERE r.teacher_account = :teacherAccount and r.week = :week and r.lab_id = :ladId and JSON_EXTRACT(r.Specifics, '$.week') = :xingQi and JSON_EXTRACT(r.Specifics, '$.period') = :period;
""")
    void deleteReservationByAllInfo(String teacherAccount, String week, String ladId, String xingQi,String period);



}
