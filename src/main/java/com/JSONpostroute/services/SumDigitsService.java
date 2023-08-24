package com.JSONpostroute.services;

import org.springframework.stereotype.Service;

@Service
public class SumDigitsService {
    public static int sumDigits(int totalOrders) {
        if(totalOrders ==0)return 0;
        return totalOrders%10 + sumDigits(totalOrders/10);
    }
}
