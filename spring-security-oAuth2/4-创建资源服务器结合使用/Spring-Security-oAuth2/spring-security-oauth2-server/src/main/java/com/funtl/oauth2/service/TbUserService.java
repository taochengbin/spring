package com.funtl.oauth2.service;

import com.funtl.oauth2.domain.TbUser;

public interface TbUserService {
//    default TbUser getByUsername(String username) {
//        return new TbUserServiceImpl().getByUsername(username);
//    }

    TbUser getByUsername(String username);
}
