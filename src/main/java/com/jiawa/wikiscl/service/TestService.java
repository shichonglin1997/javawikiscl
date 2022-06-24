package com.jiawa.wikiscl.service;


import com.jiawa.wikiscl.domain.Test;
import com.jiawa.wikiscl.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    public List<Test> list(){
        return testMapper.list();
    }


}
