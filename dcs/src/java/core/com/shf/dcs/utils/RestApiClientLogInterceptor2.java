package com.shf.dcs.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.Date;

@Component
public class RestApiClientLogInterceptor2 implements ClientHttpRequestInterceptor {
	private final Logger log = Logger.getLogger(this.getClass());

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		//logRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		//logResponse(response);
		// logToDBRequest(request,response, body);
		return response;
	}

	private void logRequest(HttpRequest request, byte[] body) throws IOException {

		log.info("===========================request begin================================================");
		log.info("URI         : " + request.getURI());
		log.info("Method      : " + request.getMethod());
		log.info("Headers     : " + request.getHeaders());
		// log.info("Request body: " + new String(body, "UTF-8"));
		log.info("==========================request end================================================");

	}

	private void logResponse(ClientHttpResponse response) throws IOException {

		log.info("============================response begin==========================================");
		log.info("Status code  : " + response.getStatusCode());
		log.info("Status text  : " + response.getStatusText());
		log.info("Headers      : " + response.getHeaders());
		//log.info("Response body: " + StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
		log.info("=======================response end=================================================");

	}
}
