package com.funtl.oauth2.mapper;

import com.funtl.oauth2.domain.TbUser;
import tk.mybatis.mapper.MyMapper;
import tk.mybatis.mapper.entity.Example;

public interface TbUserMapper extends MyMapper<TbUser> {
    TbUser selectOneByExample(Example example);
}
