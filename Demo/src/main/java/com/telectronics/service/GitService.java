package com.telectronics.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitService {

	@Value("${git.url.projects}")
	private String baseUrl;

	public int getGitInfoByName(String userName) {

		String url = baseUrl + "/" + userName + "/projects";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/vnd.github.inertia-preview+json");

		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		
		ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);
		List<LinkedHashMap<String, String>> res = response.getBody();
		int count = null != res ? res.size() : 0;
		return count;
	}

}
