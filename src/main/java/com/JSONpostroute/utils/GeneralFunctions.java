package com.JSONpostroute.utils;

import org.springframework.stereotype.Service;

@Service
public class GeneralFunctions {
    public static int sumDigits(int digits) {
        if (digits == 0) return 0;
        return digits % 10 + sumDigits(digits / 10);
    }
}
