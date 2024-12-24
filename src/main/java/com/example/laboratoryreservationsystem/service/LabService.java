package com.example.laboratoryreservationsystem.service;

import com.example.laboratoryreservationsystem.dox.Lab;
import com.example.laboratoryreservationsystem.dox.LabManage;
import com.example.laboratoryreservationsystem.dto.LabReservation;
import com.example.laboratoryreservationsystem.repository.LMRepository;
import com.example.laboratoryreservationsystem.repository.LabRepository;
import com.example.laboratoryreservationsystem.repository.LabReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LabService {
    //  注入持久化组件
    private final LabRepository labRepository;
    private final LabReservationRepository labReservationRepository;
    private final LMRepository lmRepository;
    //基于实验室id和预约周次，查询实验室的预约信息列表
    public List<LabReservation> getLabReservation(String labId, String week) {
        List<LabReservation> labReservations = labReservationRepository.findLabReservationByIdAndWeek(labId, week);
        return labReservations;
    }

    // 基于实验室id查询实验室具体信息
    public Lab getLabInfo(String labId) {
        return labRepository.findLabById(labId);
    }

    // 基于实验室id查询实验室管理员信息
    public LabManage getLabManager(String labId) {
        return lmRepository.findLMByLabId(labId);
    }

    //获取所有实验室的id和name
    public List<Lab> getAllLabs() {
        return labRepository.findAllLabIdAndName();

    }


}
