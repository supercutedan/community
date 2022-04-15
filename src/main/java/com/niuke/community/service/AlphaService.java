package com.niuke.community.service;

import com.niuke.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;
    public String find(){
        return alphaDao.select();
    }
    //这样就是在service层调用dao层，这是service依赖于dao的方式



    @PostConstruct//表明这个方法将在构造器后调用
    public void init(){
        System.out.println("初始化");
    }
    @PreDestroy
    public void destory(){
        System.out.println("销毁");
    }
}
