package com.example.laboratoryreservationsystem.dox;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Teacher")
public class Teacher {
    public static final String ADMIN = "admin";
    public static final String USER = "o4j3";
    @Id
    @CreatedBy
    private String id;
    private String name;
    private String email;
    private String phone;
    private String major;
    private String account;
    private String webName;
    // 序列号的时候忽略
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String role;
    @ReadOnlyProperty
    private LocalDateTime createTime;
    @ReadOnlyProperty
    private LocalDateTime updateTime;

}