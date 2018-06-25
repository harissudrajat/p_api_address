package com.psdkp.api.address.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class Welcome {

    @GetMapping(value = "")
    public Map home(){
        Map map = new HashMap();
        map.put("message","Welcome to microservice address PSDKP");
        map.put("version","1.0");
        return map;
    }
}
