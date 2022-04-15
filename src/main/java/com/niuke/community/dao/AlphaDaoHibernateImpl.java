package com.niuke.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaHibernate")//自定义类名，这样就可以在getBean时直接调用
public class AlphaDaoHibernateImpl implements AlphaDao{

    @Override
    public String select() {
        return "Hibernate";
    }
}
