package com.example.laboratoryreservationsystem.controller;


import com.example.laboratoryreservationsystem.dox.Lab;
import com.example.laboratoryreservationsystem.dox.LabManage;
import com.example.laboratoryreservationsystem.dto.LabReservation;
import com.example.laboratoryreservationsystem.service.LabService;
import com.example.laboratoryreservationsystem.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab/")
@RequiredArgsConstructor
@Slf4j
public class LabController {

    private final LabService labService;


    //获取实验室信息,默认L001实验室
    @GetMapping("info")
    public ResultVO getLabInfo() {
        return ResultVO.success(labService.getLabInfo("L001"));
    }

    //获取实验室预约信息
    @PostMapping("info")
    public ResultVO LabInfo(@RequestBody Lab lab) {
        return ResultVO.success(labService.getLabInfo(lab.getId()));
    }

    // 默认获取L001实验室第一周信息
    @GetMapping("schedule")
    public ResultVO getLabschedule() {
        List<LabReservation> labReservations = labService.getLabReservation("L001","1");
        return ResultVO.success(labReservations);
    }

    //基于实验室id和预约周次，查询实验室的预约信息
    @PostMapping("schedule")
    public ResultVO labschedule(@RequestBody LabReservation labReservation) {
        List<LabReservation> labReservations = labService.getLabReservation(labReservation.getLabId(), labReservation.getWeek());
        return ResultVO.success(labReservations);

    }

    //获取实验室管理员信息，默认获取L001实验室
    @GetMapping("manager")
    public ResultVO getLabManage() {
        LabManage labManage = labService.getLabManager("L001");
        return ResultVO.success(labManage);

    }

    //根据实验室id获取实验室管理员信息
    @PostMapping("manager")
    public ResultVO getLabManage(@RequestBody Lab lab) {
        LabManage labManage = labService.getLabManager(lab.getId());
        return ResultVO.success(labManage);

    }

    //获取实验室列表
    @GetMapping("labs")
    public ResultVO getLabList() {
        List<Lab> labs = labService.getAllLabs();
        return ResultVO.success(labs);
    }

}
