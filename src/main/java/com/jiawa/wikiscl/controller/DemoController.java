package com.jiawa.wikiscl.controller;

import com.jiawa.wikiscl.domain.Demo;
import com.jiawa.wikiscl.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DemoService demoService;


    @GetMapping("/list1")
    public List<Demo> demo(){
        return demoService.list();
    }









}
