package com.funtl.oauth2.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.funtl.oauth2.mapper.TbContentCategoryMapper;
import com.funtl.oauth2.service.TbContentCategoryService;
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService{

    @Resource
    private TbContentCategoryMapper tbContentCategoryMapper;

}
