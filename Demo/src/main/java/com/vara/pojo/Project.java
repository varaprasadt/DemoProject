package com.vara.pojo;

import java.util.List;

public class Project  {


	/**
	 * @param id
	 * @param name
	 * @param url
	 * @param readMe
	 * @param contributors
	 * @param commits
	 */
	public Project( String readMe, List<String> contributors, int commits) {
		this.readMe = readMe;
		this.contributors = contributors;
		this.commits = commits;
	}


	String readMe;
	List<String> contributors;
	int commits;

	
	public String getReadMe() {
		return readMe;
	}

	public void setReadMe(String readMe) {
		this.readMe = readMe;
	}

	public List<String> getContributors() {
		return contributors;
	}

	public void setContributors(List<String> contributors) {
		this.contributors = contributors;
	}

	public int getCommits() {
		return commits;
	}

	public void setCommits(int commits) {
		this.commits = commits;
	}

}
