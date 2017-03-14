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
        GetUserInfoByTokenRequest request = new GetUserInfoByTokenRequest("这里填上客户端传来的token"
                ,"这里填上accessToken");
        OpenapiClient client = OpenapiClientFactory.getClient();
        try {
            for(int i=0; i<10; ++i){
                GetUserInfoByTokenResponse response = client.execute(request);
                System.out.println("i"+i+":"+response.toString());
            }

        } catch (OpenapiException e) {
            // TODO handle
        } catch (Exception e){
            // TODO handle
            e.printStackTrace();
        }
    }

}
