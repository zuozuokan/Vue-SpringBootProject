package com.example.laboratoryreservationsystem.dox;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;



@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Lab")
public class Lab {
    public enum Status {
        Available,    // 可用
        InUse,        // 使用中
        Maintenance   // 维护中
    }

    @Id
    @CreatedBy
    private String id;
    private String name;
    private String staffId;
    private Integer capacity;
    private String configuration;
    private Status status;
}
