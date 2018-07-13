package com.example.child3;

import com.example.child1.spi.DemoService;

public class DemoServiceImpl implements DemoService {

    public String sayHi(String msg) {
        return "你好，" + msg;
    }
}
