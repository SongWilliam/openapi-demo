<?php

/**
 * Created by PhpStorm.
 * User: david
 * Date: 16/5/14
 * Time: 16:17
 */
class FileCache
{
    function set($key, $value)
    {
        if($key&&$value){
            $data = json_decode($this->get_file(DIR_ROOT ."filecache.php"),true);
            $item = array();
            $item["$key"] = $value;
            $keyList = array('accessToken');
            if(in_array($key,$keyList)){
                $item['expireIn'] = time() + 7000;
            }else{
                $item['expireIn'] = 0;
            }
            $item['create_time'] = time();
            $data["$key"] = $item;
            $this->set_file("filecache.php",json_encode($data));
        }
    }
    function get($key)
    {
        if($key){
            $data = json_decode($this->get_file(DIR_ROOT ."filecache.php"),true);
            if($data&&array_key_exists($key,$data)){
                $item = $data["$key"];
                if(!$item){
                    return false;
                }
                if($item['expireIn']>0&&$item['expireIn'] < time()){
                    return false;
                }
                return $item["$key"];
            }else{
                return false;
            }
        }
    }
    function get_file($filename) {
        if (!file_exists($filename)) {
            $fp = fopen($filename, "w");
            fwrite($fp, "<?php exit();?>" . '');
            fclose($fp);
            return false;
        }else{
            $content = trim(substr(file_get_contents($filename), 15));
        }
        return $content;
    }
    function set_file($filename, $content) {
        $fp = fopen($filename, "w");
        fwrite($fp, "<?php exit();?>" . $content);
        fclose($fp);
    }
}