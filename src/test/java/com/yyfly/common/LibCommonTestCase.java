package com.yyfly.common;

import com.yyfly.common.util.OSUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class LibCommonTestCase {
    @Autowired
    private WebApplicationContext webContext;   //注入WebApplicationContext
    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)        //设置MockMvc
                .build();
    }

    @Test
    public void testOSUtils(){
        System.out.println(OSUtils.getOSname());
    }
}
