package com.lijian;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author: Jian
 * @desrciption: 使用spring-test+Junit加载配置文件、加载web环境的测试基类
 * @date: Created in 0:00 2018/11/4
 * @Modified By:
 */
// 使用junit4进行测试
@RunWith(SpringJUnit4ClassRunner.class)
// springContext上下文配置
@ContextConfiguration(locations = "classpath:applicationContext.xml")
// webapp环境配置(可以测试request等信息，默认为src/main/webapp)
@WebAppConfiguration("src/main/webapp")
public class BaseTest {


}
