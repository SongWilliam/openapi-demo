package com.shinemo.openapi.jituancaiyun;

import java.util.Timer;

import net.sf.json.JSONObject;

import org.apache.http.HttpStatus;

import com.shinemo.openapi.jituancaiyun.api.AccessTokenVisitor;
import com.shinemo.openapi.jituancaiyun.api.Apis;
import com.shinemo.openapi.jituancaiyun.common.HttpClientUtils;
import com.shinemo.openapi.jituancaiyun.common.HttpResult;
import com.shinemo.openapi.jituancaiyun.common.JsonUtils;
import com.shinemo.openapi.jituancaiyun.domain.UserInfoDTO;
import com.shinemo.openapi.jituancaiyun.shedule.AccessTokenRefreshTask;
/**
 * 集团彩云开放平台demo
 *
 */
public class App {

	public static void main(String[] args) {
		//accessToken定时刷新器
		startRefreshAccessToken();
		//集团彩云客户端登录token（h5应用集团彩云客户端会在业务url后拼接上token参数）
		//例如：应用的url为 http://www.jituancaiyun.com 那么从集团彩云客户端点击上url后url会变成http://www.jituancaiyun.com?token=woshijituancaiyuntoken
		String jituancaiyunToken = "";
		try {
			UserInfoDTO userInfo = getUseInfoByToken(jituancaiyunToken);
			//do somethime by userInfo
		} catch (Exception e) {
			
		}
		
	}
	
	//例子1：根据集团彩云的登录token获取登录用户的uid和name;
	public static UserInfoDTO getUseInfoByToken(String jituancaiyunToken) throws Exception{
		HttpResult httpResult = HttpClientUtils.get(String.format(Apis.USERINFO_URL, jituancaiyunToken, AccessTokenVisitor.visitAccountToken()));
		JSONObject jsonResult = JSONObject.fromObject(httpResult.getResult());
		if(httpResult.getStatus() == HttpStatus.SC_OK && jsonResult.getInt("status")==0){
			UserInfoDTO userInfo = JsonUtils.convertFrom(jsonResult.getString("data"), UserInfoDTO.class);
			return userInfo;
		}else if(4003 == jsonResult.getInt("status")){
			//token失效，被动刷新token
			AccessTokenVisitor.refresh();
		}else{
			//处理其他异常
		}
		throw new RuntimeException("get userinfo failed!");
	}

	//accessToken 定时刷新器
	public static void startRefreshAccessToken() {
		Timer timer = new Timer();
		long delay1 = 1 * 1000;
		long period1 = 2*60*60*1000;
		// 从现在开始 1 秒钟之后，每隔 2小时 执行一次
		timer.schedule(new AccessTokenRefreshTask(), delay1, period1);
	}
}
