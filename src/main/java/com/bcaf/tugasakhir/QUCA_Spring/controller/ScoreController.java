package com.bcaf.tugasakhir.QUCA_Spring.controller;

import com.bcaf.tugasakhir.QUCA_Spring.service.BobotScoreService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/get-branch-score")
public class ScoreController {

    @Autowired
    private BobotScoreService bobotScoreService;

    @GetMapping("/{idCabang}")
    public ResponseEntity<Object> getScoreForBranch(@PathVariable( value = "idCabang") Long idCabang, HttpServletRequest request){
        return bobotScoreService.getScoreForBranch(idCabang, request);
    }
}
