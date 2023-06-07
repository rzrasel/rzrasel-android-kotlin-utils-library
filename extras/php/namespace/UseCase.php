<?php
use Rz\Autoloader\Autoloader;
if(version_compare(PHP_VERSION, "5.4.0", "<")) {
    exit("This SDK requires PHP version 5.4 or higher.");
}
//version_compare(PHP_VERSION, "9.4.0", "<") or exit('No direct script access allowed');
//defined("BASEPATH") OR exit("No direct script access allowed");
/* if(version_compare(PHP_VERSION, "9.4.0", "<")) {
    throw new Exception("The Facebook SDK requires PHP version 5.4 or higher.");
} */
require_once __DIR__ . "/autoloader.php";
//require_once("TestMe.php");
//require_once __DIR__ . "/TestMe.php";
?>
<?php
function onAutoload($className) {
    $classParts = explode("\\", $className);
    $className = end($classParts) . '.php';
    $classPath = __DIR__ . "/" . $className;
    //echo $classPath;
    require_once $classPath;
}
//spl_autoload_register("onAutoload");
?>
<?php

class UseCase {}
?>
<?php
$autoloader = new Autoloader(BASEPATH);
$testMe = new Rz\Library\TestMe();
echo $testMe->onPrint();
?>