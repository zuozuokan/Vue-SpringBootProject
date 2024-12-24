package com.example.laboratoryreservationsystem.repository;

import com.example.laboratoryreservationsystem.dox.Lab;
import com.example.laboratoryreservationsystem.dto.LabReservation;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LabRepository extends CrudRepository<Lab, String> {

    // 查询对应实验室信息
    @Query("""
        SELECT * FROM Lab WHERE id = :labId
""")
    Lab findLabById(String labId);

    //获取所有实验室的id和name
    @Query("""
        SELECT id, name FROM Lab
""")
    List<Lab> findAllLabIdAndName();



}
