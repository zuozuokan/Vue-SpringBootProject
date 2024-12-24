package com.example.laboratoryreservationsystem.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private String teacherId;
    private String courseId;
    private String labId;
    private String teacherName;
    private String courseName;
    // 是否临时预约
    private boolean tempReservation;
    private LocalDateTime reservationDate;
    private String week;
    // json格式
//    private Map<String, Object> specifics;
    private String specifics;






}

