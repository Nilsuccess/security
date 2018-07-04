package com.nil.security.demo.wiremock;


import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import com.github.tomakehurst.wiremock.client.WireMock;

public class WiremockService {

	public static void main(String[] args) throws Exception {
		//连接WireMock服务器
		WireMock.configureFor("127.0.0.1", 8062);
		//清空之前的配置【需要重新发布所有的配置】
		WireMock.removeAllMappings();
		
		mock("/order/1","1");
		mock("/order/2","2");
	}

	/**
	 * 给WireMock服务器添加配置
	 * @param url		访问的路径
	 * @param fileName	对应路径的响应内容所在的文本
	 * @return
	 * @throws IOException
	 */
	private static String mock(String url,String fileName) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/response/"+fileName+".txt");
		String content = StringUtils.join(FileUtils.readLines(resource.getFile(),"UTF-8").toArray(),"\n");
		WireMock.stubFor(WireMock
				.get(WireMock.urlEqualTo(url)).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
		return content;
	}

}
