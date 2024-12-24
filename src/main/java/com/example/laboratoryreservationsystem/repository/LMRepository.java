package com.example.laboratoryreservationsystem.repository;

import com.example.laboratoryreservationsystem.dox.LabManage;
import com.example.laboratoryreservationsystem.dto.LabReservation;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface LMRepository extends CrudRepository<LabManage,String > {

    //基于实验室id查询对应负责人
    @Query("""
        SELECT lm.name,lm.email,lm.position,lm.phone FROM Lab l join LabManage lm on l.staff_id = lm.id 
        where l.id = :labId
""")
    LabManage findLMByLabId(String labId);
}
