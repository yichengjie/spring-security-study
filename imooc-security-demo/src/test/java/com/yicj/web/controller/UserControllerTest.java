package com.yicj.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac ;

    private MockMvc mockMvc ;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build() ;
    }


    @Test
    public void whenQuerySuccess() throws Exception {
        ResultActions perform = mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("username","yicj")
                .param("age","18")
                .param("ageTo","60")
                .param("xxx","yyy")
//                .param("size","15")
//                .param("page","3")
//                .param("sort","age, desc")

        );
        //期望返回状态码是成功
        perform.andExpect(status().isOk()) ;
        //期望返回数据长度为3
        perform.andExpect(jsonPath("$.length()").value(3)) ;
    }


    @Test
    public void whenGetInfoSuccess() throws Exception {
        ResultActions perform = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        );
        perform.andExpect(status().isOk()) ;
        perform.andExpect(jsonPath("$.username").value("tom")) ;
    }


    @Test
    public void whenGetInfoFail() throws Exception {
        ResultActions perform = mockMvc.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        );
        perform.andExpect(status().is4xxClientError()) ;
    }

}
