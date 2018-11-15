package com.lijian;


import com.lijian.entity.User;
import com.lijian.mapper.UserMapper;
import com.lijian.service.impl.UserService;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class AppTest 
{
    // getLogger(Clazz.class)是org.apache.log4j包
    private static final Logger log = LogManager.getLogger(AppTest.class);
    private static UserService userService;
    private static UserMapper userMapper;

    @BeforeClass
    public static void loadConfig(){
        PropertyConfigurator.configure("E:\\idea-workspace\\base\\src\\main\\resources\\log4j2.properties");
        log.info("start load springContext...");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        log.info("end load springContext...");
        log.info("start get bean...");
        userService = (UserService) applicationContext.getBean("userService");
//        userMapper = (UserMapper) applicationContext.getBean("userMapper");
        log.debug("finally get {} bean...");
    }
    @Test
    public void testMapper(){
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }
//        userService.getUserById("1");
    }
}
