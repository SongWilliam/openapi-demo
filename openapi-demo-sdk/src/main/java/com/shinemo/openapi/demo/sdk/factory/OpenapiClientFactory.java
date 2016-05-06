package com.shinemo.openapi.demo.sdk.factory;


import com.shinemo.openapi.java.sdk.client.DefaultOpenapiClient;
import com.shinemo.openapi.java.sdk.client.OpenapiClient;

public abstract class OpenapiClientFactory {

    private static OpenapiClient client;

    public static OpenapiClient getClient(){
        if(client==null){
            //这里把地址替换成相应开放平台地址即可
            //集团彩云：https://api.open.jituancaiyun.com
            //麻绳：
            client = DefaultOpenapiClient.builder().serverUrl("https://api.open.jituancaiyun.com").build();
        }
        return client;
    }

}
