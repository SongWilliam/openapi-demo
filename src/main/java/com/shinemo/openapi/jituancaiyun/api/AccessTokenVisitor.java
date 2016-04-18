package com.shinemo.openapi.jituancaiyun.api;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.json.JSONObject;

import org.apache.http.HttpStatus;

import com.shinemo.openapi.jituancaiyun.common.HttpClientUtils;
import com.shinemo.openapi.jituancaiyun.common.HttpResult;
import com.shinemo.openapi.jituancaiyun.common.JsonUtils;
import com.shinemo.openapi.jituancaiyun.domain.AccessTokenDTO;
import com.shinemo.openapi.jituancaiyun.domain.Constants;

/**
 * accessToken 访问控制器
 * 
 * （1）需要主动定时刷新和被动刷新结合；
 * （2）如果是机器集群需要保证accessToken	全局唯一；
 *
 */
public class AccessTokenVisitor {

	
	private static final ConcurrentHashMap<String, AccessTokenDTO> ACCESSTOKENCACHE = new ConcurrentHashMap<String, AccessTokenDTO>();
	
	private static final String ACCESSTOKEN_KEY = "openapi_access_token";

	public static String visitAccountToken() {
		Objects.requireNonNull(ACCESSTOKENCACHE.get(ACCESSTOKEN_KEY), "accesstoken is null");
		return ACCESSTOKENCACHE.get(ACCESSTOKEN_KEY).getAccessToken();
	}
	
	public static void initAccessToken(AccessTokenDTO accessToken){
		ACCESSTOKENCACHE.put(ACCESSTOKEN_KEY, accessToken);
	}

	public static Boolean refresh() {
		synchronized (AccessTokenVisitor.class) {
			HttpResult result = HttpClientUtils.get(String.format(Apis.ACCESSTOKEN_URL, Constants.appId, Constants.appSecret));
			if(result.getStatus() == HttpStatus.SC_OK && JSONObject.fromObject(result.getResult()).getInt("status")==0){
				try {
					AccessTokenDTO accessToken = JsonUtils.convertFrom(result.getResult(), AccessTokenDTO.class);
					initAccessToken(accessToken);
					return true;
				} catch (Exception e) {
					handleExceltion(e);
				}
			}else{
				handleStatusError(result);
			}
		}
		return false;
	}
	
	public static void handleExceltion(Exception e){
		//handle exception
	}
	
	public static void handleStatusError(HttpResult result){
		
	}
}
