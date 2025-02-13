package com.bcaf.tugasakhir.QUCA_Spring.controller;

import com.bcaf.tugasakhir.QUCA_Spring.dto.req.ReqNewFormQUCA;
import com.bcaf.tugasakhir.QUCA_Spring.dto.req.ReqRejectFormQUCA;
import com.bcaf.tugasakhir.QUCA_Spring.dto.req.ReqUpdateFormQUCA;
import com.bcaf.tugasakhir.QUCA_Spring.service.QUCAService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/quick-credit-analytic")
public class FormQUCAController {

    @Autowired
    private QUCAService qucaService;

    @GetMapping("/get-list-form")
    public ResponseEntity<Object> getListFormQUCA(HttpServletRequest request){
        return qucaService.getListFormQUCA(request);
    }

    @GetMapping("/get-form-detail/{mid}")
    public ResponseEntity<Object> getFormDetail(@PathVariable(value = "mid") Long MID, HttpServletRequest request){
        return qucaService.getFormDetail(MID, request);
    }

    @GetMapping("/get-mid/{mid}")
    public ResponseEntity<Object> getMID(@PathVariable(value = "mid") Long MID, HttpServletRequest request){
        return qucaService.getKonsumenByMID(MID, request);
    }

    @PostMapping("/new-form")
    public ResponseEntity<Object> submitFormQUCA(@RequestBody ReqNewFormQUCA reqNewFormQUCA, HttpServletRequest request){
        return qucaService.submitFormQUCA(reqNewFormQUCA, request);
    }

    @PutMapping("/update-form")
    public ResponseEntity<Object> updateFormQUCA(@RequestBody ReqUpdateFormQUCA reqUpdateFormQUCA, HttpServletRequest request){
        return qucaService.updateFormQUCA(reqUpdateFormQUCA, request);
    }

    @PutMapping("/reject-form")
    public ResponseEntity<Object> rejectFormQUCA(@RequestBody ReqRejectFormQUCA reqRejectFormQUCA, HttpServletRequest request){
        return qucaService.rejectFormQUCA(reqRejectFormQUCA, request);
    }

    @PutMapping("/approve-form/{mid}")
    public ResponseEntity<Object> approveFormQUCA(@PathVariable(value = "mid") Long MID, HttpServletRequest request){
        return qucaService.approveFormQUCA(MID, request);
    }
}
