package com.yicj.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        String respContent = perform.andReturn().getResponse().getContentAsString();
        System.out.println(respContent);
    }


    @Test
    public void whenGetInfoSuccess() throws Exception {
        ResultActions perform = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        );
        perform.andExpect(status().isOk()) ;
        perform.andExpect(jsonPath("$.username").value("tom")) ;
        String respContent = perform.andReturn().getResponse().getContentAsString();
        System.out.println(respContent);
    }


    @Test
    public void whenGetInfoFail() throws Exception {
        ResultActions perform = mockMvc.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        );
        perform.andExpect(status().is4xxClientError()) ;
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Date date = new Date() ;
        String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        ResultActions perform = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
        );
        perform.andExpect(status().isOk()) ;
        perform.andExpect(jsonPath("$.id").value("1")) ;
        String respContent = perform.andReturn().getResponse().getContentAsString();
        System.out.println(respContent);
    }
    
    @Test
	public void whenUpdateSuccess() throws Exception {
		Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		System.out.println(date.getTime());
		String content = "{\"id\":\"1\", \"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String reuslt = mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(reuslt);
	}
    
    
	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(delete("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}
	
	@Test
	public void whenUploadSuccess() throws Exception {
		String result = mockMvc.perform(fileUpload("/file")
				.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	

}
