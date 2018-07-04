package com.nil.security.demo.controller;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mvc ;
	
	@Before
	public void setup(){
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void whenQuerySuccess(){
		try {
			String contentAsString = mvc.perform(MockMvcRequestBuilders
					.get("/user")
					.param("userName", "Jerry")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
			.andReturn().getResponse().getContentAsString();
			System.out.println(contentAsString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @PathVariable
	 */
	@Test
	public void whenGenInfoSuccess(){
		try {
			String contentAsString = mvc.perform(MockMvcRequestBuilders
					.get("/userInfo/1")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("tom"))
			.andReturn().getResponse().getContentAsString();
			System.out.println(contentAsString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @PathVariable 参数类型 【正则】
	 */
	@Test
	public void whenGenInfoSuccess2(){
		try {
			mvc.perform(MockMvcRequestBuilders
					.get("/userInfo/2")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void whenCreateSuccess(){
		try {
			Date date = new Date();
			String content = "{\"userName\":\"tom\",\"passWord\":\"123456\",\"brithDay\":"+date.getTime()+"}";
			String contentAsString = mvc.perform(MockMvcRequestBuilders
					.post("/userAdd")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(content))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
			.andReturn().getResponse().getContentAsString();
			System.out.println(contentAsString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件上传
	 * @throws Exception 
	 */
	@Test
	public void whenUplodSuccess() throws Exception{
		String string = mvc.perform(MockMvcRequestBuilders
				.fileUpload("/file")
				.file(new MockMultipartFile("file", "1.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn().getResponse().getContentAsString();
		System.out.println(string);
	}
	
	
}
























