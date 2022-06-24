package com.jiawa.wikiscl.mapper;

import com.jiawa.wikiscl.domain.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;




public interface TestMapper {
    public List<Test> list();
}
