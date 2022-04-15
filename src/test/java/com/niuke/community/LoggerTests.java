package com.niuke.community;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes=CommunityApplication.class)
public class LoggerTests {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoggerTests.class);
    //这里通常传入的是当前类。一个类设置一个自己的logger
    //方便所有地方都能调用，设置为静态的

    @Test
    public void testLogger(){
        System.out.println(logger.getName());//logger的名字就是类的名字

        logger.debug("debug log");//一般来说用的最低级别的就是debug，一般不会用trace级别
        logger.info("info log");//有线程池时
        logger.warn("warn log");
        logger.error("error log");

    }
}
