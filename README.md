# 服务端SDK

## SDK获取

_服务端SDK适用于获取accessToken，获取用户信息，发送消息，获取部门列表，获取不猛成员信息，媒体文件获取和上传等功能。_

***

| 开发语言  | 资源下载 | 说明 |
| --------- | ------- | ----- |
| java  | java-sdk-1.0.2 | 适用于Java语言、jdk版本1.5及以上的开发环境 |
| PHP  | SDK | 适用于php全系列开发环境 |
| .NET  | SDK | 适用于Visual studio 2010及以上版本、Framework3.5及以上版本的开发环境 |



## SDK集成

***

### 1. SDK已经对加签验签逻辑做了封装，使用SDK可直接调用API。
### 2. 确定接口对应的类

例如接口名：/openapi/token/get

在SDK中对应的类为：每个单词首字母大写，并去掉分隔符（“/”），末尾加上Request（或Response）

如上接口名对应的类为：

OpenapiTokenGetRequest（请求类）

OpenapiTokenGetResponse（响应类）

具体调用方式见下方各语言。

## JavaSDK集成示例

***

### SDK包说明

openapi-sdk-java*.jar—————————开放平台SDK编译文件jar

openapi-sdk-java*-source.jar——————开放平台SDK源码文件jar

###  注意

* 集成支付宝接口需要引入的文件是：

openapi-sdk-java*.jar

* 若进一步了解代码实现请引入文件：

openapi-sdk-java*-source.jar
### 普通调用示例

```

		//获取请求实例，实例里面已经封装好了请求地址
		AccessTokenRequest request = new AccessTokenRequest("429796111","7511B4A213A7710C");
		//实例化客户端
        OpenapiClient client = OpenapiClientFactory.getClient();
        try {
        	//直接调用业务逻辑
            AccessTokenResponse response = client.execute(request);
            //获取执行结果并返回
            System.out.println(response.toString());
        } catch (OpenapiException e) {
            // TODO handle
        } catch (Exception e){
            // TODO handle
        }
        

```

### 用户授权接口调用示例

```

		//获取请求实例
		GetUserInfoByTokenRequest request = new GetUserInfoByTokenRequest("3327d6bf20d1a08ba6afc294862312fd","3327d6bf20d1a08ba6afc294862312fd");
		//实例化客户端
        OpenapiClient client = OpenapiClientFactory.getClient();
        try {
        	//直接调用业务逻辑
            GetUserInfoByTokenResponse response = client.execute(request);
            System.out.println(response.toString());
        } catch (OpenapiException e) {
            // TODO handle
        } catch (Exception e){
            // TODO handle
        }


````

## .netSDK集成示例


***

> 敬请期待

## php集成实例

***

> 敬请期待
