package com.telectronics.pojo;

import java.util.List;

public class GitInfo {

	int count;
	String owner;
	List<Project> projects;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((projects == null) ? 0 : projects.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GitInfo other = (GitInfo) obj;
		if (count != other.count) {
			return false;
		}
		if (owner == null) {
			if (other.owner != null) {
				return false;
			}
		} else if (!owner.equals(other.owner)) {
			return false;
		}
		if (projects == null) {
			if (other.projects != null) {
				return false;
			}
		} else if (!projects.equals(other.projects)) {
			return false;
		}
		return true;
	}
	
}
