package com.example.laboratoryreservationsystem.repository;

import com.example.laboratoryreservationsystem.dox.LabManage;
import com.example.laboratoryreservationsystem.dto.LabReservation;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LMRepository extends CrudRepository<LabManage,String > {

    //基于实验室id查询对应负责人
    @Query("""
        SELECT lm.name,lm.email,lm.position,lm.phone FROM Lab l join LabManage lm on l.staff_id = lm.id 
        where l.id = :labId
""")
    LabManage findLMByLabId(String labId);

    // 添加实验室负责人
    @Modifying
    @Transactional
    @Query("""
        INSERT INTO LabManage (id,name,email,position,phone) VALUES (:id,:name,:email,:position,:phone)
""")
    void addLM(String id, String name, String email, String position, String phone);


    // 删除实验室负责人
    @Modifying
    @Transactional
    @Query("""
       delete from LabManage where id = :id
""")
    void delLM(String id);

    // 获取所有实验室负责人
    @Query("""
        SELECT * FROM LabManage
""")
    List<LabManage> findAllLM();

    //基于id删除
    @Modifying
    @Transactional
    @Query("""
        DELETE FROM LabManage WHERE id = :id
""")
    void deleteLMById(String id);

}
