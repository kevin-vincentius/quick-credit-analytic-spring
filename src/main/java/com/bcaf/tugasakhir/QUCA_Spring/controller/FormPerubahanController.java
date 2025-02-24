package com.bcaf.tugasakhir.QUCA_Spring.controller;

import com.bcaf.tugasakhir.QUCA_Spring.dto.req.ReqFormPerubahan;
import com.bcaf.tugasakhir.QUCA_Spring.service.BobotScoreService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/form-perubahan")
public class FormPerubahanController {

    @Autowired
    private BobotScoreService bobotScoreService;

    @GetMapping
    public ResponseEntity<Object> getListFormPerubahanBobotScore(HttpServletRequest request){
        return bobotScoreService.getListFormPerubahanBobotScore(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDetailFormPerubahanBobotScore(@PathVariable( value = "id") Long idFormPerubahanBobotScore, HttpServletRequest request){
        return bobotScoreService.getDetailFormPerubahanBobotScore(idFormPerubahanBobotScore, request);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addNewFormPerubahan(@RequestBody ReqFormPerubahan reqFormPerubahanBobotScoreDTO, HttpServletRequest request){
        return bobotScoreService.addNewFormPerubahan(reqFormPerubahanBobotScoreDTO, request);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Object> rejectFormPerubahan(@PathVariable(value = "id") Long idFormPerubahan, HttpServletRequest request){
        return bobotScoreService.rejectFormPerubahan(idFormPerubahan, request);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Object> approveFormPerubahan(@PathVariable(value = "id") Long idFormPerubahan, HttpServletRequest request){
        return bobotScoreService.approveFormPerubahan(idFormPerubahan, request);
    }
}
