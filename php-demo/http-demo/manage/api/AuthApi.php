<?php

require_once(__DIR__ . "/../util/HttpUtil.php");
require_once(__DIR__ . "/../util/CacheUtil.php");
require_once(__DIR__ . "/../util/LogUtil.php");
require_once(__DIR__ . "/../../config.php");

class AuthApi
{

    /**
     * 根据登录token获取用户信息
     * @param $token
     * @return mixed
     */
    public static function getUserInfoByToken($token)
    {
        $accessToken = AccessTokenApi::getAccessToken();
        $response = HttpUtil::get("/auth/getUserInfoByToken", array("accessToken" => $accessToken, "token" => $token));
        return $response;
    }

}