package com.shinemo.openapi.jituancaiyun.api;

public interface Apis {
	//access token 
	public static final String ACCESSTOKEN_URL = "https://api.open.jituancaiyun.com/openapi/token/get?appId=%s&appSecret=%s";
	//根据集团彩云登录token获取用户信息
	public static final String USERINFO_URL    = "https://api.open.jituancaiyun.com/openapi/auth/getUserInfoByToken?token=%s&accessToken=%s";
}
 