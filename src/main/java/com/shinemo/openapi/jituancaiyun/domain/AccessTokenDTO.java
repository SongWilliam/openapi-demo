package com.shinemo.openapi.jituancaiyun.domain;

public class AccessTokenDTO {

	private String accessToken;
	
	private Integer expiresIn;
	

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}
}
