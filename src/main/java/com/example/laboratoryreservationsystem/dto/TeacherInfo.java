package com.example.laboratoryreservationsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.mapping.Table;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherInfo {
    String name;
    String account;
    String password;
    String role;

}
