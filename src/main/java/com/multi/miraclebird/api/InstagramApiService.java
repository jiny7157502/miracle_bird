package com.multi.miraclebird.api;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.multi.miraclebird.user.UserVO;


@Service
public class InstagramApiService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ResponseEntity<Map> getShortTokenAndUserId(String code) {
		// 단기 엑세스 토큰, user_id
		HashMap<String, Object> shortResult = new HashMap<String, Object>();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
		requestBody.add("client_id", "888699415496342");
		requestBody.add("client_secret", "f06a232c2096fb60125a948990434393");
		requestBody.add("grant_type", "authorization_code");
		requestBody.add("redirect_uri", "https://localhost:8443/miraclebird/auth");
		requestBody.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
		
		String url = "https://api.instagram.com/oauth/access_token";
		UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
		
		ResponseEntity<Map> result = restTemplate.exchange(uri.toString(), HttpMethod.POST, request, Map.class);
		
		return result;
	}
	
	public ResponseEntity<Map> getLongTokenAndExpires(String shortToken) {
		// 장기 엑세스 토큰, expires_in
		String long_url = "https://graph.instagram.com/access_token";
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(long_url)
				.queryParam("grant_type", "ig_exchange_token")
				.queryParam("client_secret", "f06a232c2096fb60125a948990434393")
				.queryParam("access_token", shortToken)
				.build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
		
		ResponseEntity<Map> result = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, request, Map.class);
		
		return result;
	}
	
	public ResponseEntity<Map> getUserProfile(UserVO userVO) {
		// 사용자 프로필
		String profileUrl = "https://graph.instagram.com/me";
		UriComponents profileUrlComp = UriComponentsBuilder.fromHttpUrl(profileUrl)
				.queryParam("fields", new String[]{"id", "username"})
				.queryParam("access_token", userVO.getAccessToken())
				.build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
					
		ResponseEntity<Map> result = restTemplate.exchange(profileUrlComp.toString(), HttpMethod.GET, request, Map.class);
		
		return result;
	}

}