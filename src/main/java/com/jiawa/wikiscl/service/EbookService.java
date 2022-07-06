package com.jiawa.wikiscl.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiawa.wikiscl.domain.Ebook;
import com.jiawa.wikiscl.domain.EbookExample;
import com.jiawa.wikiscl.mapper.EbookMapper;
import com.jiawa.wikiscl.req.EbookQueryReq;
import com.jiawa.wikiscl.req.EbookSaveReq;
import com.jiawa.wikiscl.resp.EbookQueryResp;
import com.jiawa.wikiscl.resp.PageResp;
import com.jiawa.wikiscl.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
    @Resource
    private EbookMapper ebookMapper;

    public PageResp<EbookQueryResp> list(EbookQueryReq req){

        EbookExample ebookExample = new EbookExample();
        // like a where condition
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%"+req.getName()+"%");
        }
        // to paginate the first sql statement result under this pagehelper statement (selectByExample in this page)
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);



        List<EbookQueryResp> respList = CopyUtil.copyList(ebookList, EbookQueryResp.class);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("total rows ：{}", pageInfo.getTotal());
        LOG.info("total pages：{}", pageInfo.getPages());


        PageResp<EbookQueryResp> pageResp = new PageResp<>();

        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }

    public void save(EbookSaveReq req) {
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // add
            ebookMapper.insert(ebook);
        } else {
            // update
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }


}
