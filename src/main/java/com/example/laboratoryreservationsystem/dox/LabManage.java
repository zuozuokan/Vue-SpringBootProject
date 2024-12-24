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
@Table(name = "LabManage")
public class LabManage {
    @Id
    @CreatedBy
    private String id;
    private String name;
    private String position;
    private String email;
    private String phone;
}
