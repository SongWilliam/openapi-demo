package com.shinemo.openapi.demo.sdk.userinfo;


import com.shinemo.openapi.demo.sdk.factory.OpenapiClientFactory;
import com.shinemo.openapi.java.sdk.client.OpenapiClient;
import com.shinemo.openapi.java.sdk.exception.OpenapiException;
import com.shinemo.openapi.java.sdk.request.GetUserInfoByTokenRequest;
import com.shinemo.openapi.java.sdk.response.GetUserInfoByTokenResponse;

/**
 * 根据登录token获取用户信息demo
 */
public class UserInfoDemo {

    public static void main(String[] args){
        GetUserInfoByTokenRequest request = new GetUserInfoByTokenRequest("3327d6bf20d1a08ba6afc294862312fd","3327d6bf20d1a08ba6afc294862312fd");
        OpenapiClient client = OpenapiClientFactory.getClient();
        try {
            GetUserInfoByTokenResponse response = client.execute(request);
            System.out.println(response.toString());
        } catch (OpenapiException e) {
            // TODO handle
        } catch (Exception e){
            // TODO handle
        }
    }

}
