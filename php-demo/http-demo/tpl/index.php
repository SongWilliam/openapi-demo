<!DOCTYPE html>
<?php
    require_once(__DIR__ . "/../manage/api/AuthApi.php");
    require_once(__DIR__ . "/../manage/util/RequestUtil.php");
?>
<html>
<head>
    <title>开放平台免登手机客户端demo</title>
</head>
<body>
<?php
    $_REQUEST = $_SERVER["QUERY_STRING"];
    $_token = parseUrlParam("token", $_REQUEST);
    $response = AuthApi.getUserInfoByToken($_token);
    echo "<br>GET USER INFO: ".$response;
?>
</body>
</html>
