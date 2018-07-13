package com.example.child1.impl;

import com.example.child1.spi.DemoService;

public class DemoServiceImpl01 implements DemoService {
    public String sayHi(String msg) {
        return "Hello " + msg;
    }
}
