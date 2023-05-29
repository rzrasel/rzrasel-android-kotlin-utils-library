<?php
function getHostUrl() {
    $base_url = ( isset($_SERVER['HTTPS']) && $_SERVER['HTTPS']=='on' ? 'https' : 'http' ) . '://' .  $_SERVER['HTTP_HOST'];
    return $base_url;
}
function getBaseUrl() {
    return $_SERVER['REQUEST_URI'];
}
function getBaseDirectory() {
    return dirname($_SERVER['REQUEST_URI']);
}
?>

<?php
function curPageURL() {
    $pageURL = 'http';
    if(isset($_SERVER["HTTPS"]))
    if ($_SERVER["HTTPS"] == "on") {
        $pageURL .= "s";
    }
    $pageURL .= "://";
    if ($_SERVER["SERVER_PORT"] != "80") {
        $pageURL .= $_SERVER["SERVER_NAME"] . ":" . $_SERVER["SERVER_PORT"] . $_SERVER["REQUEST_URI"];
    } else {
        $pageURL .= $_SERVER["SERVER_NAME"] . $_SERVER["REQUEST_URI"];
    }
    return $pageURL;
}

function getCurrentUrl() {
    return (empty($_SERVER['HTTPS']) ? 'http' : 'https') . "://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";
}
?>