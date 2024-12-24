package com.example.laboratoryreservationsystem.dox;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Map;


@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Reservation")
public class Reservation {
    @Id
    @CreatedBy
    private String id;
    private String teacherId;
    private String courseId;
    private String labId;
    private boolean tempReservation;
    private LocalDateTime reservationDate;
    private Map<String, Object> specifics;

}
