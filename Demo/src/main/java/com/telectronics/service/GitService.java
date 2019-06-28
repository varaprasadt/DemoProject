package com.telectronics.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.telectronics.pojo.Project;
import com.telectronics.pojo.ProjectBasicInfo;

@Service
public class GitService {

	@Value("${git.url.base}")
	private String BASE_URL;

	@Autowired
	private RestTemplate restTemplate;

	public List<ProjectBasicInfo> getGitProjectsInfo(String userName) {

		String url = BASE_URL + "/users/" + userName + "/repos";

		@SuppressWarnings("rawtypes")
		ResponseEntity<List> res = gitApiCall(url, List.class);
		@SuppressWarnings("unchecked")
		List<ProjectBasicInfo> listOfProjects = buildProjectsFromResponse(res.getBody());
		return listOfProjects;
	}

	private ProjectBasicInfo buildProjectFromResponse(Map<String, String> res) {
		return new ProjectBasicInfo(String.valueOf(res.get("id")), res.get("name"), res.get("url"));
	}

	private List<ProjectBasicInfo> buildProjectsFromResponse(List<LinkedHashMap<String, String>> res) {
		List<ProjectBasicInfo> projects = new ArrayList<>();
		for (Map<String, String> map : res) {

			projects.add(buildProjectFromResponse(map));
		}
		return projects;
	}

	public Project getGitProjectInfo(String projectId, String userId) {

		String readMe = getReadMe(projectId, userId);
		List<String> contributors = getContributors(projectId, userId);
		int commitsCount = getCommitsCount(projectId, userId);
		return new Project(readMe, contributors, commitsCount);
	}

	private String getReadMe(String projectId, String userId) {
		String url = BASE_URL + "/repos/" + userId + "/" + projectId + "/readme";
		String readMe = "";
		try {
			@SuppressWarnings("rawtypes")
			ResponseEntity<Map> res = gitApiCall(url, Map.class);
			readMe = decodeReadMe(res.getBody().get("content"));
		} catch (Exception ex) {
			readMe = "";
		}
		return readMe;
	}

	private int getCommitsCount(String projectId, String userId) {
		String url = BASE_URL + "/repos/" + userId + "/" + projectId + "/commits";
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> res = gitApiCall(url, List.class);
		return res.getBody().size();
	}

	private List<String> getContributors(String projectId, String userId) {
		String url = BASE_URL + "/repos/" + userId + "/" + projectId + "/contributors";
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> res = gitApiCall(url, List.class);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = res.getBody();
		List<String> listOfContributors = new ArrayList<>();
		if (null == list) {
			return listOfContributors;
		}
		for (Map<String, String> map : list) {
			listOfContributors.add(map.get("login"));
		}
		return listOfContributors;
	}

	private String decodeReadMe(Object object) {
		String readMe = String.valueOf(object);
		return new String(Base64.getMimeDecoder().decode(readMe));
	}

	private <T> ResponseEntity<T> gitApiCall(String url, Class<T> responseType) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/vnd.github.inertia-preview+json");
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		return restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
	}

}
