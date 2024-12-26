package com.example.laboratoryreservationsystem.dto;


import com.example.laboratoryreservationsystem.dox.Lab;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LabDto {

    public enum Status {
        Available,    // 可用
        Maintenance   // 维护中
    }
        private String id;
        private String name;
        private Integer capacity;
        private String configuration;
        private Status status;
}

