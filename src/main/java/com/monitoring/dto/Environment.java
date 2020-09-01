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

	String user;

	String password;
	
	@NotNull
	String defaultNameSpace;
	
	String saToken;
	

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Environment(@NotNull String name, @NotNull String masterIP, String user, String password,
			@NotNull String defaultNameSpace, String saToken) {
		super();
		this.id = id;
		this.name = name;
		this.masterIP = masterIP;
		this.user = user;
		this.password = password;
		this.defaultNameSpace = defaultNameSpace;
		this.saToken = saToken;
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

	public String getSaToken() {
		return saToken;
	}

	public void setSaToken(String saToken) {
		this.saToken = saToken;
	}

	@Override
	public String toString() {
		return "Environment [id=" + id + ", name=" + name + ", masterIP=" + masterIP + ", user=" + user + ", password="
				+ password + ", defaultNameSpace=" + defaultNameSpace + ", saToken=" + saToken + "]";
	}

}
