package com.springboot.v1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.v1.dao.VerifyDao;
import com.springboot.v1.service.VerifyService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VerifyServiceImpl implements VerifyService {

    @Autowired
    private VerifyDao verifyDao;

    @Override
    public boolean selectVerify() {
        // TODO Auto-generated method stub
        return false;
    }


}
