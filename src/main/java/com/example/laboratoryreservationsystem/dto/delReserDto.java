package com.example.laboratoryreservationsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class delReserDto {

    private String teacherAccount;
    private String week;
    private String labId;
    private String xingQi;
    private String period;
}
