package com.springboot.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.v1.service.VerifyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/verify")
@Slf4j
public class VerifyController {

    @Autowired
    private VerifyService verifyService;
    
    @GetMapping("/v1")
    public void test() {
        log.info("v1");
        System.out.println("调通了"+verifyService.selectVerify());
    }
}
