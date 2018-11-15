package com.lijian;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author: Jian
 * @desrciption: 采用mockMvc模拟测试springmvc，无需启动server
 * @date: Created in 0:24 2018/11/4
 * @Modified By:
 */
public class MVCTest extends BaseTest{

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    public MVCTest() {
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
}
