package com.jiawa.wikiscl.controller;

import com.jiawa.wikiscl.domain.Ebook;
import com.jiawa.wikiscl.resp.CommonResp;
import com.jiawa.wikiscl.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;


    @GetMapping("/list")
    public CommonResp ebook(){
        CommonResp<List<Ebook>> resp = new CommonResp<>();
        List<Ebook> list= ebookService.list();
        resp.setContent(list);
        return resp;
    }









}
