package com.bcaf.tugasakhir.QUCA_Spring.controller;

import com.bcaf.tugasakhir.QUCA_Spring.dto.req.ReqLoginDTO;
import com.bcaf.tugasakhir.QUCA_Spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody ReqLoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request){
        return userService.logout(request);
    }

}
