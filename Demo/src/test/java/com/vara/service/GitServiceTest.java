package com.vara.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vara.pojo.Project;
import com.vara.pojo.ProjectBasicInfo;

@RunWith(MockitoJUnitRunner.class)
public class GitServiceTest {
	@InjectMocks
	GitService gitService=new GitService();;
	
	@Mock
	RestTemplate restTemplate;
	
	@Test
	public void testGetGitProjectsInfo() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/vnd.github.inertia-preview+json");
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		String url="null/users/Test/repos";
		List<Map<String,String>> list=new ArrayList<>();
		Map<String,String> map=new LinkedHashMap<>();
		map.put("id", "1");
		map.put("name", "TEST");
		map.put("url", "TEST");
		list.add(map);
		Mockito.when(restTemplate.exchange(url, HttpMethod.GET, entity, List.class)).thenReturn(new ResponseEntity<>(list,HttpStatus.OK));
		List<ProjectBasicInfo> resList=gitService.getGitProjectsInfo("Test");
		Assert.assertEquals(1, resList.size());
	}

	@Test
	public void testGetGitProjectInfo() {
		String baseURL="null/repos/TEST/TEST";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/vnd.github.inertia-preview+json");
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		String urlcommits=baseURL+"/commits";
		String urlreadme=baseURL+"/readme";
		String urlContributors=baseURL+"/contributors";
		Map<String,String> map=new LinkedHashMap<>();
		map.put("content", "TEST");
		Mockito.when(restTemplate.exchange(urlreadme, HttpMethod.GET, entity, Map.class)).thenReturn(new ResponseEntity<>(map,HttpStatus.OK));
		
		List listCommits=new ArrayList<>();
		listCommits.add(new Object());
		Mockito.when(restTemplate.exchange(urlcommits, HttpMethod.GET, entity, List.class)).thenReturn(new ResponseEntity<>(listCommits,HttpStatus.OK));
		
		
		List<Map<String,String>> list=new ArrayList<>();
		Map<String,String> mapContributors=new LinkedHashMap<>();
		mapContributors.put("login", "TEST");
		list.add(mapContributors);
		Mockito.when(restTemplate.exchange(urlContributors, HttpMethod.GET, entity, List.class)).thenReturn(new ResponseEntity<>(list,HttpStatus.OK));
          Project project= gitService.getGitProjectInfo("TEST", "TEST");
          Assert.assertEquals(1, project.getCommits());
          Assert.assertEquals(1, project.getContributors().size());
		
	}

}
