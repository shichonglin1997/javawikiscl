package com.jiawa.wikiscl.controller;

import com.jiawa.wikiscl.req.EbookQueryReq;
import com.jiawa.wikiscl.req.EbookSaveReq;
import com.jiawa.wikiscl.resp.CommonResp;
import com.jiawa.wikiscl.resp.EbookQueryResp;
import com.jiawa.wikiscl.resp.PageResp;
import com.jiawa.wikiscl.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;


    @GetMapping("/list")
    public CommonResp ebook(EbookQueryReq req){
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list= ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }









}
