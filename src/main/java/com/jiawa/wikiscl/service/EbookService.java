package com.jiawa.wikiscl.service;


import com.jiawa.wikiscl.domain.Ebook;
import com.jiawa.wikiscl.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list(){
        return ebookMapper.selectByExample(null);
    }


}
