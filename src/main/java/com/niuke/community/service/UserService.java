package com.niuke.community.service;

import com.niuke.community.dao.UserMapper;
import com.niuke.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }
    //这个这个功能是为了在DiscussPostService中能够通过userId得到username
}
