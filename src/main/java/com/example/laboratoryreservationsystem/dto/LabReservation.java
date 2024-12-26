package com.example.laboratoryreservationsystem.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.lang.ref.PhantomReference;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Reservation")
public class LabReservation {
    @Id
    private int id;
    private String teacherAccount;
    private String name;
    private String courseId;
    private String labId;
    private String teacherName;
    private String courseName;
    // 是否临时预约
    private String tempReservation;
    private LocalDateTime reservationDate;
    private String specifics;
    private String week;








}

