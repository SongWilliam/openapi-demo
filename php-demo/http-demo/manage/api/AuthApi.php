<?php

require_once(__DIR__ . "/../util/HttpUtil.php");
require_once(__DIR__ . "/../util/CacheUtil.php");
require_once(__DIR__ . "/../util/LogUtil.php");
require_once(__DIR__ . "/../../config.php");
/**
 * Created by PhpStorm.
 * User: david
 * Date: 16/5/14
 * Time: 16:28
 */
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
        $response = HttpUtil::post("/auth/getUserInfoByToken",
            array("accessToken" => $accessToken, "token" => $token));
        return $response;
    }

}