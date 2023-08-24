package com.JSONpostroute.services;

import org.springframework.stereotype.Service;

@Service
public class SumDigitsService {
    public static int sumDigits(int orderDigits) {
        if(orderDigits ==0)return 0;
        return orderDigits%10 + sumDigits(orderDigits/10);
    }
}
