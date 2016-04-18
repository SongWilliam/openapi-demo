package com.shinemo.openapi.jituancaiyun.api;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import org.apache.http.HttpStatus;

import com.shinemo.openapi.jituancaiyun.common.HttpRequestHelper;
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

	
	private static ConcurrentHashMap<String, AccessTokenDTO> ACCESSTOKENCACHE = new ConcurrentHashMap<String, AccessTokenDTO>();
	
	private static final String ACCESSTOKEN_KEY = "openapi_access_token";

	public static String visitAccountToken() {
		try{
			Objects.requireNonNull(ACCESSTOKENCACHE.get(ACCESSTOKEN_KEY), "accesstoken is null");
		}catch(NullPointerException ne){
			refresh();
		}
		return ACCESSTOKENCACHE.get(ACCESSTOKEN_KEY).getAccessToken();
	}
	
	public static void initAccessToken(AccessTokenDTO accessToken){
		ACCESSTOKENCACHE.put(ACCESSTOKEN_KEY, accessToken);
	}

	public static Boolean refresh() {
		synchronized (AccessTokenVisitor.class) {
			ResponseBody body=null;
			try {
				Request httpRequest = new Request.Builder().url(String.format(Apis.ACCESSTOKEN_URL, Constants.appId, Constants.appSecret)).build();
				OkHttpClient client = HttpRequestHelper.getUnsafeOkHttpClient();
				Response response = client.newCall(httpRequest).execute();
				body=response.body();
				JSONObject jr = JSONObject.fromObject(body.string());
				if(response.isSuccessful() && jr.getInt("status")==0){
					AccessTokenDTO accessToken = JsonUtils.convertFrom(jr.getString("data"), AccessTokenDTO.class);
					initAccessToken(accessToken);
					System.out.println(visitAccountToken());
					return true;
				}else{
					//handle
				}
	        } catch (IOException e) {
	           //handle
	        } catch (Exception e) {
	        	//handle
			} finally{
	            if(body!=null){
	                body.close();
	            }
	        }
		}
		return false;
	}
	
	public static void handleExceltion(Exception e){
		//handle exception
	}
	
}
