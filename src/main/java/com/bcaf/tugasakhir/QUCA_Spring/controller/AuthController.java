package com.bcaf.tugasakhir.QUCA_Spring.controller;

import com.bcaf.tugasakhir.QUCA_Spring.dto.req.ReqLoginDTO;
import com.bcaf.tugasakhir.QUCA_Spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@RequestBody ReqLoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request){
        return userService.logout(request);
    }

    @GetMapping("/get-list-cabang")
    public ResponseEntity<Object> getListCabang(HttpServletRequest request){
        return userService.getListCabang(request);
    }
}
