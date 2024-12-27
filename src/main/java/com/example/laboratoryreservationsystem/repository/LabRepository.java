package com.example.laboratoryreservationsystem.repository;

import com.example.laboratoryreservationsystem.dox.Lab;
import com.example.laboratoryreservationsystem.dto.LabDto;
import com.example.laboratoryreservationsystem.dto.LabReservation;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
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

    //查询满足条件的实验室
    @Query("""
        SELECT * FROM Lab l WHERE l.capacity >= :capacity
""")
    List<LabDto> findLabByCapacity(int capacity);

    // 超级管理员添加实验室
    @Modifying
    @Transactional
    @Query("""
        insert into Lab (id, name, staff_Id, capacity, configuration, status) 
        values (:id, :name, :staffId, :capacity, :configuration, :status)
""")
    void addLab(String id, String name, String staffId, int capacity, String configuration, String status);

    // 超级管理员删除实验室
    @Modifying
    @Transactional
    @Query("DELETE FROM Lab WHERE id = :id")
    void deleteLab(String id);

    // 超级管理员获取所有实验室
    @Query("SELECT * FROM Lab")
    List<Lab> findAllLab();

    // 删除实验室负责人的所有记录
    @Modifying
    @Transactional
    @Query("DELETE FROM Lab WHERE staff_Id = :id")
    void deleteLabByStaffId(String id);



}
