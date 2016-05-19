<?php

require_once(__DIR__ . "/../util/HttpUtil.php");
require_once(__DIR__ . "/../util/CacheUtil.php");
require_once(__DIR__ . "/../util/LogUtil.php");
require_once(__DIR__ . "/../../config.php");

class AccessTokenApi
{

    public static function getAccessToken()
    {
        /**
         * 缓存accessToken。accessToken有效期为两小时，需要在失效前请求新的accessToken（注意：以下代码没有在失效前刷新缓存的accessToken）。
         */
        $accessToken = CacheUtil::getAccessToken();
        if (!$accessToken)
        {
            $response = HttpUtil::get('/gettoken', array('appId' => APPID, 'appSecret' => APPSECRET));
            $accessToken = $response->access_token;
            CacheUtil::setAccessToken($accessToken);
        }
        return $accessToken;
    }

}