package com.example.child1.impl;

import com.example.child1.spi.DemoService;

public class DemoServiceImpl02 implements DemoService {
    public String sayHi(String msg) {
        return "Hi " + msg;
    }
}
