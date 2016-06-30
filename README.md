
# 开始step by step

## 1.注册成为彩云开发者([点我注册](https://open.jituancaiyun.com/developer/login.html))

_如下图所示，填写一个手机号设置一个密码即可立即成为彩云开发者_

![image](http://7xnmmr.com1.z0.glb.clouddn.com/Snip20160630_7.png)

_注册完成后就可以[点我登录](https://open.jituancaiyun.com)_

![image](http://7xnmmr.com1.z0.glb.clouddn.com/Snip20160630_14.png)

## 2.开发者认证

_目前彩云支持（个人认证）和（企业认证），目前个人认证只能使用免登接口，建议做企业认证可享受目前所有接口服务_

![image](http://7xnmmr.com1.z0.glb.clouddn.com/Snip20160630_11.png)


_点击上图的认证按钮后跳转到下图的认证页面_

![image](http://7xnmmr.com1.z0.glb.clouddn.com/Snip20160630_13.png)
_填写个人开发者和企业的相关信息后点击提交，一般材料真实有效的话，会在1个小时内审核通过_
_审核通过后就可以创建应用了_

####测试应用

_点击测试应用->新建测试应用_
![image](http://7xnmmr.com1.z0.glb.clouddn.com/Snip20160630_15.png)

_按照提示填写完应用的信息_

![image](http://7xnmmr.com1.z0.glb.clouddn.com/Snip20160630_16.png)

_添加应用的测试人（添加完后，测试人的彩云应用中心可见该应用）_

![image](http://7xnmmr.com1.z0.glb.clouddn.com/Snip20160630_17.png)
![image](http://7xnmmr.com1.z0.glb.clouddn.com/Snip20160630_18.png)

_好了，应用都可见了，还有什么搞不定的！_

####正式应用


# 开始开发

开始开发之前首先仔细阅读 [开放平台接口文档](http://uban360.github.io/)

## 服务端SDK

### SDK获取

_服务端SDK适用于获取accessToken，获取用户信息，发送消息，获取部门列表，获取不猛成员信息，媒体文件获取和上传等功能。_

***

| 开发语言  | 资源下载 | 说明 |
| --------- | ------- | ----- |
| java  | java-sdk-1.0.2 | 适用于Java语言、jdk版本1.5及以上的开发环境 |
| PHP  | SDK | 适用于php全系列开发环境 |
| .NET  | SDK | 适用于Visual studio 2010及以上版本、Framework3.5及以上版本的开发环境 |



### SDK集成

***

#### 1. SDK已经对加签验签逻辑做了封装，使用SDK可直接调用API。
#### 2. 确定接口对应的类

例如接口名：/openapi/token/get

在SDK中对应的类为：每个单词首字母大写，并去掉分隔符（“/”），末尾加上Request（或Response）

如上接口名对应的类为：

OpenapiTokenGetRequest（请求类）

OpenapiTokenGetResponse（响应类）

具体调用方式见下方各语言。

### JavaSDK集成示例

***

#### SDK包说明

openapi-sdk-java*.jar—————————开放平台SDK编译文件jar

openapi-sdk-java*-source.jar——————开放平台SDK源码文件jar

####  注意

* 集成支付宝接口需要引入的文件是：

openapi-sdk-java*.jar

* 若进一步了解代码实现请引入文件：

openapi-sdk-java*-source.jar
#### 普通调用示例

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

#### 用户授权接口调用示例

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

### .netSDK集成示例


***

> 敬请期待

### php集成实例

***

> 敬请期待
