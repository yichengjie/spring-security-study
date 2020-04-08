package com.yicj.web.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;




public class MockServer {
	
	public static void main(String[] args) throws Exception {
		configureFor(9999);
		removeAllMappings();
		mock("/order/1", "01.txt") ;
	}

	private static void mock(String url, String fileName) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/response/" + fileName) ;
		List<String> lines = FileUtils.readLines(resource.getFile(),"UTF-8");
		String content = StringUtils.join(lines,"\n");
		stubFor(get(urlPathEqualTo(url)).willReturn(aResponse()
					.withBody(content)
					.withStatus(200))) ;
	}

}
