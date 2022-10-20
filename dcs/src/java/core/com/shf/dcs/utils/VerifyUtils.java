package com.shf.dcs.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class VerifyUtils {

	private static Logger logger = Logger.getLogger(VerifyUtils.class);
	
	public static Boolean validateCaptcha(String captchaToken) {

		String url = "https://www.google.com/recaptcha/api/siteverify";
		String secret = Constants.SECRET_KEY;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("secret", secret)
					.queryParam("response", captchaToken);
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			ResponseEntity<RecaptchaResponse> response = restTemplate.exchange(builder.build().encode().toUri(),
					HttpMethod.GET, entity, RecaptchaResponse.class);

			RecaptchaResponse rs = response.getBody();

			if (response.getStatusCode().value() == 200 && rs.isSuccess()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		}

	}
}