package com.monitoring.dto;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class Environment {

	@Id
	String id;

	@NotNull
	String name;
	
	@NotNull
	String masterIP;

	@NotNull
	String user;

	@NotNull
	String password;
	
	@NotNull
	String defaultNameSpace;

	public String getMasterIP() {
		return masterIP;
	}

	public void setMasterIP(String masterIP) {
		this.masterIP = masterIP;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Environment(@NotNull String name, @NotNull String masterIP, @NotNull String user, @NotNull String password,
			@NotNull String defaultNameSpace) {
		super();
		this.masterIP = masterIP;
		this.user = user;
		this.password = password;
		this.name = name;
		this.defaultNameSpace = defaultNameSpace;
	}

	@Override
	public String toString() {
		return "Environment [id=" + id + ", name=" + name + ", masterIP=" + masterIP + ", user=" + user + ", password="
				+ password + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefaultNameSpace() {
		return defaultNameSpace;
	}

	public void setDefaultNameSpace(String defaultNameSpace) {
		this.defaultNameSpace = defaultNameSpace;
	}

}
