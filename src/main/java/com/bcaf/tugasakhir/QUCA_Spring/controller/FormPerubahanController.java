package com.bcaf.tugasakhir.QUCA_Spring.controller;

import com.bcaf.tugasakhir.QUCA_Spring.model.FormPerubahanBobotScore;
import com.bcaf.tugasakhir.QUCA_Spring.service.BobotScoreService;
import com.bcaf.tugasakhir.QUCA_Spring.service.QUCAService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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





}
