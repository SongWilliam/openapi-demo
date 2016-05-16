<?php


class RequestUtil
{
    function parseUrlParam($nparam, $query){
        $queryArr = explode('&', $query);
        if($queryArr[0] !== ''){
            foreach( $queryArr as $param ){
                list($name, $value) = explode('=', $param);
                if($nparam == $name){
                    return $value;
                }
            }
        }
        return '';
    }
}