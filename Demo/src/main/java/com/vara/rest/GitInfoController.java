package com.vara.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vara.pojo.Project;
import com.vara.pojo.ProjectBasicInfo;
import com.vara.service.GitService;

@RestController
public class GitInfoController {

	@Autowired
	GitService gitService;

	@GetMapping(value = "/projects/{username}")
	public ResponseEntity<List<ProjectBasicInfo>> getProjects(@PathVariable("username") String userName) {
		List<ProjectBasicInfo> projects = gitService.getGitProjectsInfo(userName);
		return new ResponseEntity<>(projects, HttpStatus.OK);
	}

	@GetMapping(value = "/projects/{username}/{projectId}")
	public ResponseEntity<Project> getProject(@PathVariable("username") String userName,
			@PathVariable("projectId") String projectId) {
		Project project = gitService.getGitProjectInfo(projectId, userName);
		return new ResponseEntity<>(project, HttpStatus.OK);
	}
}
