package com.jiawa.wikiscl.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.wikiscl.domain.Ebook;
import com.jiawa.wikiscl.domain.EbookExample;
import com.jiawa.wikiscl.mapper.EbookMapper;
import com.jiawa.wikiscl.req.EbookSaveReq;
import com.jiawa.wikiscl.resp.EbookResp;
import com.jiawa.wikiscl.resp.PageResp;
import com.jiawa.wikiscl.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
    @Resource
    private EbookMapper ebookMapper;

    public PageResp<EbookResp> list(EbookSaveReq req){

        EbookExample ebookExample = new EbookExample();
        // like a where condition
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%"+req.getName()+"%");
        }
        // to paginate the first sql statement result under this pagehelper statement (selectByExample in this page)
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);



        List<EbookResp> respList = CopyUtil.copyList(ebookList, EbookResp.class);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("total rows ：{}", pageInfo.getTotal());
        LOG.info("total pages：{}", pageInfo.getPages());


        PageResp<EbookResp> pageResp = new PageResp<>();

        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }


}
