package com.niuke.community;

import com.niuke.community.dao.AlphaDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

//@RunWith(SpringRunner.class)现在的springboot版本已经不再需要runwith
@SpringBootTest
@ContextConfiguration(classes=CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Test
    public void testApplicationContext(){
        AlphaDao alphaDao=applicationContext.getBean(AlphaDao.class);
        System.out.println(alphaDao.select());
    }
    @Test
    public void testBeanConfig(){
        SimpleDateFormat simpleDateFormat=applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));

    }
    @Autowired//我希望能够自动装配
    //Autowired可以修饰成员变量，构造器，set方法。不过通常加在成员变量之前
    @Qualifier("alphaHibernate")//这里可以指定要AlphaDao的哪个实现类
    private AlphaDao alphaDao;
    @Test
    public void testDI(){
        System.out.println(alphaDao);
    }
}
