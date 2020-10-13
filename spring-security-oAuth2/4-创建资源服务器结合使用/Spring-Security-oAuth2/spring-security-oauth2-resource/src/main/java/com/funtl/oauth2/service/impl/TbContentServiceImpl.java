package com.funtl.oauth2.service.impl;

import com.funtl.oauth2.domain.TbContent;
import com.funtl.oauth2.mapper.TbContentMapper;
import com.funtl.oauth2.service.TbContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbContentServiceImpl implements TbContentService {

    @Resource
    private TbContentMapper tbContentMapper;

    @Override
    public TbContent getById(Long id) {
        return tbContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbContent> selectAll() {
        return tbContentMapper.selectAll();
    }

    @Override
    public int insert(TbContent tbContent) {
        return tbContentMapper.insert(tbContent);
    }

    @Override
    public int update(TbContent tbContent) {
        return tbContentMapper.updateByPrimaryKey(tbContent);
    }

    @Override
    public int delete(Long id) {
        return tbContentMapper.deleteByPrimaryKey(id);
    }
}
