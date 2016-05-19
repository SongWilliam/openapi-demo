<?php


class CacheUtil
{
    /**
     * 设置accessToken
     * @param $tmpAccessToken
     */
    public static function setAccessToken($tmpAccessToken){
        $memcache = self::getMemcache();
        $memcache->set("tmpAccessToken", $tmpAccessToken, 0, time() + 7000);//accessToken有效期为7200 (s)
    }

    /**
     * 获取accessToken cache
     */
    public static function getAccessToken(){
        $memcache = self::getMemcache();
        $memcache->get("tmpAccessToken");
    }

    private static function getMemcache()
    {
        /*if (class_exists("Memcache"))
        {
            $memcache = new Memcache;
            if ($memcache->connect('localhost', 11211))
            {
                return $memcache;
            }
        }*/
        return new FileCache;
    }

    public static function get($key)
    {
        return self::getMemcache()->get($key);
    }

    public static function set($key, $value)
    {
        self::getMemcache()->set($key, $value);
    }

}