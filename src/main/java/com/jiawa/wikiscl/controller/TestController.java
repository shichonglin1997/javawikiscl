package com.jiawa.wikiscl.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class TestController {


    @RequestMapping("/hello")
    public String hello(){
        return "HELLO WORLD";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "HELLO WORLD POST!"+name;
    }
}
