package com.niuke.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.text.SimpleDateFormat;
@Configuration
public class AlphaConfig {
    @Bean//这个方法返回的对象将被装配到容器中   用这种方法可以装配第三方的jar包
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
