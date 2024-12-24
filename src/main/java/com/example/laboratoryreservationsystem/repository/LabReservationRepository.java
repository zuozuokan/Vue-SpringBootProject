package com.example.laboratoryreservationsystem.repository;

import com.example.laboratoryreservationsystem.dto.LabReservation;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LabReservationRepository extends CrudRepository<LabReservation, String> {
    // 查询对应实验室预约情况
    @Query("""
        SELECT * FROM Reservation r WHERE r.lab_id = :labId AND r.week = :week
""")
    List<LabReservation> findLabReservationByIdAndWeek(String labId, String week);
}
