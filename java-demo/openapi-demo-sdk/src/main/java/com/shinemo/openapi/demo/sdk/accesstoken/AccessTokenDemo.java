package com.shinemo.openapi.demo.sdk.accesstoken;


import com.shinemo.openapi.demo.sdk.factory.OpenapiClientFactory;
import com.shinemo.openapi.java.sdk.client.OpenapiClient;
import com.shinemo.openapi.java.sdk.exception.OpenapiException;
import com.shinemo.openapi.java.sdk.request.AccessTokenRequest;
import com.shinemo.openapi.java.sdk.response.AccessTokenResponse;
import com.shinemo.openapi.java.sdk.response.GetUserInfoByTokenResponse;

/**
 * 获取accessToken demo
 */
public class AccessTokenDemo {

    public static void main(String[] args){
        AccessTokenRequest request = new AccessTokenRequest("43243ewrwerwwerwerewerwerwerew243234","3327d6bf20d1dsfdsfsdfsdfdsfsdfdsfsdfsda08ba6afc294862312fd");
        OpenapiClient client = OpenapiClientFactory.getClient();
        try {

                AccessTokenResponse response = client.execute(request);
                System.out.println(response.toString());
        } catch (OpenapiException e) {
            // TODO handle
            e.printStackTrace();
        } catch (Exception e){
            // TODO handle
            e.printStackTrace();
        }
    }

}
