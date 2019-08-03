package com.vara.rest;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.vara.pojo.Project;
import com.vara.pojo.ProjectBasicInfo;



/**
 * @author Vara
 *
 *This Test cases covers end to end testing and integration testing
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GitInfoControllerTest {

	@Autowired
	GitInfoController controller;

	@Mock
	RestTemplate restTemplate;

	@Test
	public void testGetProjects() {
		ResponseEntity<List<ProjectBasicInfo>> res = controller.getProjects("TEST");
		Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	public void testGetProject() {
		ResponseEntity<Project> res = controller.getProject("TEST", "HelloWorld");
		Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
	}
}
