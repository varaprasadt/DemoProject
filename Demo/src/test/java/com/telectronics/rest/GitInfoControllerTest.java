package com.telectronics.rest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GitInfoControllerTest {

	@Autowired
	GitInfoController controller;

	@Test
	public void testWithAvailableProjects() {
		String result = controller.getProjectsList("varaprasadt");
		Assert.assertEquals("<h1>Number of Projects : 2<h1>", result);
	}
	
	@Test
	public void testWithNoProjects() {
		String result = controller.getProjectsList("test");
		Assert.assertEquals("<h1>Number of Projects : 0<h1>", result);
	}

}
