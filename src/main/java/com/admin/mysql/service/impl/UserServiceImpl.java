package com.admin.mysql.service.impl;


import com.admin.mysql.entity.TUser;
import com.admin.mysql.mapper.TUserMapper;
import com.admin.mysql.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private TUserMapper tUserMapper;


    @Override
    public List<TUser> findAllT() {
        return tUserMapper.findAll();
    }


}
