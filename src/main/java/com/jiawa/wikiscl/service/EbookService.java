package com.jiawa.wikiscl.service;


import com.jiawa.wikiscl.domain.Ebook;
import com.jiawa.wikiscl.domain.EbookExample;
import com.jiawa.wikiscl.mapper.EbookMapper;
import com.jiawa.wikiscl.req.EbookSaveReq;
import com.jiawa.wikiscl.resp.EbookResp;
import com.jiawa.wikiscl.util.CopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookSaveReq req){
        EbookExample ebookExample = new EbookExample();
        // like a where condition
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%"+req.getName()+"%");
        }
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        List<EbookResp> respList = CopyUtil.copyList(ebookList, EbookResp.class);
        return respList;
    }


}
