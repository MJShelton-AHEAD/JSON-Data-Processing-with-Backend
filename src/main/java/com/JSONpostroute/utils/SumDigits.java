package com.JSONpostroute.utils;

import org.springframework.stereotype.Service;

@Service
public class SumDigits {
    public static int sumDigits(int orderDigits) {
        if (orderDigits == 0) return 0;
        return orderDigits % 10 + sumDigits(orderDigits / 10);
    }
}
