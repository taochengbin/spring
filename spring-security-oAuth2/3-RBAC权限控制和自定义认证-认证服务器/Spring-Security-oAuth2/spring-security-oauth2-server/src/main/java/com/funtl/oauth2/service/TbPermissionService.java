package com.funtl.oauth2.service;

import com.funtl.oauth2.domain.TbPermission;

import java.util.List;

public interface TbPermissionService {
    //    default List<TbPermission> selectByUserId(Long userId) {
//        return new TbPermissionServiceImpl().selectByUserId(userId);
//    }
    List<TbPermission> selectByUserId(Long userId);
}
