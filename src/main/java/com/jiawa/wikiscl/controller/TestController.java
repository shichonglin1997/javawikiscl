package com.jiawa.wikiscl.controller;

import com.jiawa.wikiscl.domain.Test;
import com.jiawa.wikiscl.service.TestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController
public class TestController {

    @Resource
    private TestService testService;


    @RequestMapping("/hello")
    public String hello(){
        return "HELLO WORLD";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "HELLO WORLD POST!"+name;
    }




    @RequestMapping("/test/list")
    public List<Test> test(){
        return testService.list();
    }









}
