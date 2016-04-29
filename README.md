# 一，	开始前必读
## 一张图看懂开放平台
![开放平台时序图](http://7xnmmr.com2.z0.glb.qiniucdn.com/openapipink.png)
## 1，	更新日志
* 2016年4月15日  开放平台上线，提供获取当前登录用户信息接口，和管理员后台
* 2016年4月20日 提供接口权限验证，不同接口享有不同权限

## 2，	开发者规范
开发者进行开放平台开发时，除了要满足接口的规范限制和接口调用频率限制外，还需特别注意用户敏感信息的使用规范。
涉及用户数据时：
* 您收集用户的数据后，必须采取必要的保护措施，防止用户数据被盗、泄漏等。
* 如果我们认为您收集、使用用户数据的方式，可能损害用户体验，我们有权要求您删除相关数据并不得再以该方式收集、使用用户数据。
* 一旦您停止使用本服务，或 基于任何原因终止您使用本服务，您必须立即删除全部因使用本服务而获得的数据（包括各种备份）， 且不得再以任何方式进行使用。

## 3，	开放平台接口权限说明
不同类型的开放平台账号具有不同的接口权限，见下表

接口名称 | 未认证开放平台账号 |  认证开放平台账号
------------ | ------------- | -------------
获取accessToken | 有 | 有
获取用户基本信息 | 无 | 有
获取部门列表 | 无 | 有
获取部门员工列表 | 无 | 有
获取部门员工详情 | 无 | 有
发送聊天消息 | 无 | 有
其他接口完善中 | ... | ...

## 4，	开放平台接口调用频率说明
* 开放平台的调用接口并不是无限制的。为了防止公众号的程序错误而引发 服务器负载异常，默认情况下，每个开发者应用调用接口都不能超过一定限制，当超过一定限制时，调用对应接口会收到如下错误返回码


``` 

{"status":4006,"message":"接口调用频率超过限制"} 

```


* 认证账号的调用量清零。针对认证的开发者账号，每月提供10次清零操作机会，可以针对同一接口，也可以针对不同接口清零。新注册账号接口调用频率见下表

接口名称 | 每日限额
------------ | -------------
获取accessToken | 2000
获取用户基本信息 | 5000
获取部门列表 | 5000
获取部门员工列表 | 5000
获取部门员工详情 | 5000
发送聊天消息 | 5000
其他接口完善中 | ...



## 5，	开放平台接口返回说明
应用每次调用接口时，可能获得正确或错误的返回码，开发者可以根据返回码信息调试接口，排查错误。见下表

返回码 | 说明
------------ | -------------
0 | 调用成功
-1 | 开放平台系统错误
4000 | 用户登录token错误
4001 | 用户登录token和accessToken错误，重新获取
4002 | accessToken错误
4003 | AccessToken超时
4004 | AccessToken刷新过于频繁，注意每天的接口调用次数
4005 | accessToken错误，一般是系统原因，重新获取或者联系客服
4006 | 接口调用频率超过限制
4007 | 获取accessToken时appId或者appSecret错误
4008 | 缺少accessToken参数
4009 | 需要get请求
4010 | 需要post请求
4011 | 需要httpss请求
4012 | Post数据包为空
4013 | Api无权限
4014 | 调用来源ip不在ip白名单
4015 | 不合法的callback url
4016 | 企业未对该应用授权
4500 | 用户uid不存在或者错误
4501 | 缺少uid参数


# 二，	开始开发
## 1，	接入指南
### 概述
> 接入开放平台，开发者需要完成以下3步：
* 1.1 提交服务器配置
由于目前开发者后台还在公测中，还未对正式开发者开放，所有需要开发者把以下信息通过以下格式，以邮件（*luohj@shinemo.com*）发送给 后台超级管理员，暂时由超级管理员负责录入,录入成功后会返回应用的**appId和appSecret** 。

名称 | 说明
------------ | -------------
开发者名字 | 张三
开发者地址 | 浙江省杭州市西湖区文二西路888号我的公司
开发者联系手机号 | 18888888888
开发者身份证号 | 370686199904440000
开发者照片 | 照片形式放到附件中
开发者企业名称 | ...
开发者企业地址 | ...
开发者企业法人代表名字 | ...
开发者企业法人代表身份证号 | ...
开发者企业营业执照 | 照片形式放到附件
开发者企业类型 | ...
应用名称 | ...
应用icon | 照片形式放到附件
应用描述 | 256字以内
服务器白名单ip（开放平台服务端只允许白名单内的ip访问，逗号分隔） | ...
H5地址（应用如果是h5须填写h5地址） | ...
Android_feature(应用如果是native，填写android的应用信息) | ...
Ios_feature(应用如果是native，填写ios的应用信息) | ...
应用的语言版本（中文 ，英文…） | ...
应用发布到的客户端（ ，集团彩云，联通客户端，全部） | ...
应用需要使用的接口列表（目前只有获取用户信息） | ...
应用的可见范围（企业名称或者全部） | ...

* 1.2	验证服务器地址有效性（暂时略过）
* 1.3	依据接口文档实现业务逻辑
调用各接口时，一般会获得正确的结果，具体结果可见对应接口的说明。返回错误时，可根据返回码来查询错误原因。
另请注意，开放平台接口只支持80接口

## 2，	获取接口调用凭据（accessToken）
> accessToken是开放平台的全局唯一票据，开放平台各接口时都需使用accessToken。开发者需要进行妥善保存。accessToken的存储至少要保留512个字符空间。accessToken的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的accessToken失效。

* 2.1 为了保密appSecrect，第三方需要一个accessToken获取和刷新的中控服务器。而其他业务逻辑服务器所使用的accessToken均来自于该中控服务器，不应该各自去刷新，否则会造成accessToken覆盖而影响业务；
* 2.2 目前accessToken的有效期通过返回的expireIn来传达，目前是7200秒之内的值。中控服务器需要根据这个有效时间提前去刷新新accessToken。在刷新过程中，中控服务器对外输出的依然是老accessToken，此时公众平台后台会保证在刷新短时间内，新老accessToken都可用，这保证了第三方业务的平滑过渡；
* 2.3 accessToken的有效时间可能会在未来有调整，所以中控服务器不仅需要内部定时主动刷新，还需要提供被动刷新accessToken的接口，这样便于业务服务器在API调用获知accessToken已超时的情况下，可以触发accessToken的刷新流程。
使用AppID和AppSecret调用本接口来获取accessToken。AppID和AppSecret可在邮件回复中获得（需要提供上述的开发者信息邮件给超级管理员，且帐号没有异常状态）

**接口调用请求说明**


* https请求方式: GET


` https://api.open.jituancaiyun.com/openapi/token/get?appId=APPID&appSecret=APPSECRET`

* 参数说明：

参数 | 是否必须 | 描述
------------ | ------------- | -------------
appId | 是 | 应用注册成功后返回
appSecret | 是 | 应用注册成功后返回

* 返回说明：
正常情况下开放平台会返回下述JSON数据包给用户


```

{"status":0,"data":{"accessToken":"MDAwMjM0M0RBNjIwMURENDAxOTJDRkE0MkVGMDlERjQ5MjVFODAzQTlFMThERDBCMzNBNzFEMjBFMkRCNjExRDgxQjM3RkU0QzM1NkMwQzM5NkNCODUyN0JFQTE1OUE5OUUzMg==","expiresIn":7200}}

```

参数 | 描述
------------ | -------------
status | 开放平台处理状态
data | 处理成功后会把数据字段放到这里
accessToken | 访问凭据
expiresIn | 凭证有效时间，单位：秒

错误时开放平台会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）

```
{"status":4007,"message":"获取accessToken时appId或者appSecret错误"}

```

## 3，	测试号申请
> 开放平台为每个开发者账号最多提供10个测试应用账号，测试应用不需要企业认证，默认拥有所有接口权限，访问频率控制与正式应用相同。

* 3.1 测试号申请暂时通过跟正式应用申请相同方式，邮件给指定人申请，后续会陆续开放后台自助申请。
* 3.2 测试号申请完毕后，需要加入该测试号的测试人，加入方式同上。

## 4，	接口在线调试

## 5，	常见问题
* 5.1 accessToken失效：请检查accessToken全局唯一性，重复获取将导致上一次获取的accessToken失效；

# 三，	服务端开发文档
## 1，	获取用户信息（登录token机制）

* https请求方式: GET


` https://api.open.jituancaiyun.com/openapi/auth/getUserInfoByToken?token=TOKEN&accessToken=ACCESSTOKEN`

* 参数说明：

参数 | 是否必须 | 描述
------------ | ------------- | -------------
token | 是 |  客户端拼接上的token
accessToken | 是 | 接口访问凭据

* 返回说明：
正常情况下开放平台会返回下述JSON数据包给用户


```

{"status":0,"data":{"uid":"REAM123","name":"张三", "orgList":[{"orgName":"杭州讯盟科技",orgId:1}, {"orgName":"芒果医生",orgId:2}, {"orgName":"阿里巴巴",orgId:3}...]}}

```

参数 | 描述
------------ | -------------
status | 开放平台处理状态
data | 处理成功后会把数据字段放到这里
uid | 用户在开放平台的id（经过加密），后续获取用户信息需要通过此uid
orgList | 用户所在机构列表
orgName | 用户所在机构名字
orgId | 用户所在机构id

错误时开放平台会返回错误码等信息，JSON数据包示例如下（该示例为accessToken超时）

```
{"status":4003,"message":"accessToken超时，请重新获取"}

```

## 2，	获取用户信息（用户uid机制）

* https请求方式: GET


` https://api.open.jituancaiyun.com/openapi/user/getUserInfoById?uid=UID&accessToken=ACCESSTOKEN`

* 参数说明：

参数 | 是否必须 | 描述
------------ | ------------- | -------------
uid | 是 | 用户uid
accessToken | 是 | 接口访问凭据

* 返回说明：
正常情况下开放平台会返回下述JSON数据包给用户


```

{"status":0,"data":{"uid":"REAM123","name":"张三", "orgList":[{"orgName":"杭州讯盟科技",orgId:1}, {"orgName":"芒果医生",orgId:2}, {"orgName":"阿里巴巴",orgId:3}...]}}

```

参数 | 描述
------------ | -------------
status | 开放平台处理状态
data | 处理成功后会把数据字段放到这里
uid | 用户在开放平台的id（经过加密），后续获取用户信息需要通过此uid
orgList | 用户所在机构列表
orgName | 用户所在机构名字
orgId | 用户所在机构id

错误时开放平台会返回错误码等信息，JSON数据包示例如下（该示例为accessToken超时）

```
{"status":4003,"message":"accessToken超时，请重新获取"}

```

## 3，	获取部门列表

* https请求方式: GET


` https://api.open.jituancaiyun.com/openapi/department/list?accessToken=ACCESSTOKEN`

* 参数说明：

参数 | 是否必须 | 描述
------------ | ------------- | -------------
accessToken | 是 | 接口访问凭据

* 返回说明：
正常情况下开放平台会返回下述JSON数据包给用户


```

{"status":0,"data":{"departments":[{"id":1,name:"开发部","parentid":1}, {"id":2,name:"测试部","parentid":1}...]}}

```

参数 | 描述
------------ | -------------
status | 开放平台处理状态
data | 处理成功后会把数据字段放到这里
departments | 部门列表数据
id | 部门id
name | 部门名称
parentid | 父部门id

错误时开放平台会返回错误码等信息，JSON数据包示例如下（该示例为accessToken超时）

```
{"status":4003,"message":"accessToken超时，请重新获取"}

```
## 4，	获取部门成员列表

* https请求方式: GET


` https://api.open.jituancaiyun.com/openapi/user/list?accessToken=ACCESSTOKEN&deptId=DEPTID&offset=0&size=100`

* 参数说明：

参数 | 是否必须 | 描述
------------ | ------------- | -------------
accessToken | 是 | 接口访问凭据
deptId | 是 | 部门id
offset | 是 | 页偏移量
size | 是 | 每页的数量
order | 否 | 排序

* 返回说明：
正常情况下开放平台会返回下述JSON数据包给用户


```

{"status":0,"data":{"hasMore":true, "users":[{"uid":123, "name":"张三"},{"uid":124, "name":"李四"},...]}}

```

参数 | 描述
------------ | -------------
status | 开放平台处理状态
data | 处理成功后会把数据字段放到这里
hasMore | 是否还有更多
uid | 用户id
name | 用户名字

错误时开放平台会返回错误码等信息，JSON数据包示例如下（该示例为accessToken超时）

```
{"status":4003,"message":"accessToken超时，请重新获取"}

```

## 5，	获取成员详情

* https请求方式: GET


` https://api.open.jituancaiyun.com/openapi/user/detail?accessToken=ACCESSTOKEN&uid=UID`

* 参数说明：

参数 | 是否必须 | 描述
------------ | ------------- | -------------
accessToken | 是 | 接口访问凭据
uid | 是 | 用户id

* 返回说明：
正常情况下开放平台会返回下述JSON数据包给用户


```

{"status":0,"data":{"avatar":"https://", "name":"张三", "deptId":1}}

```

参数 | 描述
------------ | -------------
status | 开放平台处理状态
data | 处理成功后会把数据字段放到这里
avatar | 头像
deptId | 所属部门id
name | 用户名字

错误时开放平台会返回错误码等信息，JSON数据包示例如下（该示例为accessToken超时）

```
{"status":4003,"message":"accessToken超时，请重新获取"}

```

## 6，	发送聊天消息

* https请求方式: POST


` https://api.open.jituancaiyun.com/openapi/message/chat/push`

* 参数说明：

参数 | 是否必须 | 描述
------------ | ------------- | -------------
accessToken | 是 | 接口访问凭据
uid | 是 | 发送者id
targetId | 是 | 接受者id
msgType | 是 | 消息类型

** msgType(消息类型)及数据格式 **

* text消息
* 参数说明

参数 | 是否必须 | 描述
------------ | ------------- | -------------
msgType | 是 | 消息类型，此时固定为：text
content | 是 | 消息内容

```


{
    "msgType": "text",
    "text": {
        "content": "张三的请假申请"
    }
  }


```

* image消息
* 参数说明

参数 | 是否必须 | 描述
------------ | ------------- | -------------
msgType | 是 | 消息类型，此时固定为：image
mediaId | 是 | 图片媒体文件id，可以调用上传媒体文件接口获取

```


{
    "msgType": "image",
    "image": {
        "mediaId": "MEDIA_ID"
    }
}

```

* voice消息
* 参数说明

参数 | 是否必须 | 描述
------------ | ------------- | -------------
msgType | 是 | 消息类型，此时固定为：image
mediaId | 是 | 图片媒体文件id，可以调用上传媒体文件接口获取


```
{
    "msgType": "voice",
    "voice": {
       "mediaId": "MEDIA_ID",
       "duration": "10"
    }
}

```

* link消息
* 参数说明

参数 | 是否必须 | 描述
------------ | ------------- | -------------
msgType | 是 | 消息类型，此时固定为：link
messageUrl | 是 | 消息点击链接地址
mediaId | 是 | 图片媒体文件id，可以调用上传媒体文件接口获取
title | 是 | 消息标题
text | 是 | 消息描述

```

{
    "msgType": "link",
    "link": {
        "messageUrl": "https://www.baidu.com",
        "mediaId":"@lALOACZwe2Rk",
        "title": "测试",
        "text": "测试"
    }
}

```

* 返回说明：
正常情况下开放平台会返回下述JSON数据包给用户


```

{"status":0,"success":true}

```

参数 | 描述
------------ | -------------
status | 开放平台处理状态

错误时开放平台会返回错误码等信息，JSON数据包示例如下（该示例为accessToken超时）

```
{"status":4003,"message":"accessToken超时，请重新获取"}

```

## 7，	上传媒体文件

* https请求方式: POST


` https://api.open.jituancaiyun.com/openapi/media/upload`

* 参数说明：

参数 | 是否必须 | 描述
------------ | ------------- | -------------
accessToken | 是 | 接口访问凭据
type | 是 | 用户id
media | 是 | form-data中媒体文件标识，有filename、filelength、content-type等信息


** 上传的媒体文件限制 **


` 图片（image）:1MB，支持JPG格式 `

` 语音（voice）：2MB，播放长度不超过60s，AMR格式 `

* 返回说明：
正常情况下开放平台会返回下述JSON数据包给用户


```

{"status":0,"data":{"type":"image", "mediaId":"dsa8d87y7c8d8c"}}

```


参数 | 描述
------------ | -------------
status | 开放平台处理状态
data | 处理成功后会把数据字段放到这里
type | 媒体文件类型，分别有图片（image）、语音（voice）等
mediaId | 媒体文件上传后获取的唯一标识


错误时开放平台会返回错误码等信息，JSON数据包示例如下（该示例为accessToken超时）

```
{"status":4003,"message":"accessToken超时，请重新获取"}

```

## 8，	获取媒体文件

* https请求方式: GET


` https://api.open.jituancaiyun.com/openapi/media/get?accessToken=ACCESSTOKEN&mediaId=MEDIAID`

* 参数说明：

参数 | 是否必须 | 描述
------------ | ------------- | -------------
accessToken | 是 | 接口访问凭据
mediaId | 是 | 媒体文件的唯一标示

* 返回说明：
和普通的https下载相同，请根据https头做相应的处理


## 9，	通讯录事件回调
开放平台会向回调url推送事件变更，假设您提供的回调URL是:

```
https://127.0.0.1/demo/receive
```

那么在开放平台服务器每一次访问回调URL的时候 将请求(下面链接中的signature,timestamp,nonce的值只是示例，并不代表真实返回的值):

```
https://127.0.0.1/demo/receive?signature=111108bb8e6dbce3c9671d6fdb69d15066227608 &timestamp=1783610513&nonce=380320111
```


```
{"encrypt":"1ojQf0NSvw2WPvW7LijxS8UvISr8pdDP+rXpPbcLGOmIBNbWetRg7IP0vdhVgkVwSoZBJeQwY2zhROsJq/HJ+q6tp1qhl9L1+ccC9ZjKs1wV5bmA9NoAWQiZ+7MpzQVq+j74rJQljdVyBdI/dGOvsnBSCxCVW0ISWX0vn9lYTuuHSoaxwCGylH9xRhYHL9bRDskBc7bO0FseHQQasdfghjkl"
}
```

其中的encrypt字段是经过加密的消息体，encrypt经过一系列解密步骤后，才能产生下面所说的“POST数据解密后示例”，您可以直接使用开放平台提供的库进行解密的处理。
除此之外，在接收到推送之后，需要返回字符串success（代表了你收到了推送），返回的数据也需要做加密处理，如果不返回，开放平台服务器将持续推送下去，达到一定阈值后将不再推送。

**  目前可以监听的事件类型分别为:  ** 

* user_add_org : 通讯录用户增加
* user_modify_org : 通讯录用户更改
* user_leave_org : 通讯录用户离职
* org_dept_create ： 通讯录企业部门创建
* org_dept_modify ： 通讯录企业部门修改
* org_dept_remove ： 通讯录企业部门删除
* org_remove ： 企业被解散

**  参数说明： **

参数 | 描述
------------- | -------------
EventType | 事件类型，有八种，"user_add_org”, “user_modify_org”, “user_leave_org”,“org_admin_add”, “org_admin_remove”, “org_dept_create”, “org_dept_modify”, “org_dept_remove”, “org_remove”
TimeStamp | 时间戳
nonce | 随机串
signature | 签名
UserId | 	用户发生变更的userid列表
DeptId | 部门发生变更的userid列表
CorpId | 发生通讯录变更的企业

**  返回说明   **

服务提供商在收到此事件推送后务必返回包含经过加密的字符串"success"的json数据

参数 | 描述
------------- | -------------
signature | 消息体签名
TimeStamp | 时间戳
nonce | 随机串
encrypt | “success"加密字符串


# 四，	开发demo

[Java Common Github DEMO](http://https://github.com/jituancaiyun/openapi-demo)

[Java SDK Github DEMO](http://https://github.com/jituancaiyun/openapi-demo)
