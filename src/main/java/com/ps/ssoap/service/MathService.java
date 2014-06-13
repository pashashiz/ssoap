package com.ps.ssoap.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class MathService {

    public boolean isEven(BigInteger value) {
        return value.mod(new BigInteger("2")).equals(BigInteger.ZERO);
    }

}
