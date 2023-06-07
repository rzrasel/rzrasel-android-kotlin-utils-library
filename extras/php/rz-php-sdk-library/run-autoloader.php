<?php
//|-----------------|NAMESPACE - RZ AUTOLOADER|------------------|
namespace RzSdk;
use RzSdk\Autoloader\Autoloader;
//|----------------------|VERSION COMPARE|-----------------------|
if(version_compare(PHP_VERSION, "5.4.0", "<")) {
    exit("The Rz SDK requires PHP version 5.4 or higher.");
}
//|--------------------|DEFINED RZ BASEPATH|---------------------|
defined("RZ_SDK_BASEPATH") or define("RZ_SDK_BASEPATH", trim(trim(__DIR__, "/")));
class RunAutoloader {
    private const dirList = array(
        "",
        "autoloader",
        "library",
        "library/db-connection",
    );
    public function __construct($rootPath) {
        $rootPath = trim(trim($rootPath, "/"));
        $rzSdkFolder = str_replace($rootPath, "", RZ_SDK_BASEPATH);
        $rzSdkFolder = trim(trim($rzSdkFolder, "/"));
        //RZ_SDK_WRAPPER
        defined("RZ_SDK_WRAPPER") or define("RZ_SDK_WRAPPER", $rzSdkFolder);
        require_once(RZ_SDK_WRAPPER . "/autoloader/autoloader.php");
        //global $autoloader;
        $autoloader = new Autoloader($rootPath, self::dirList);
    }
}
//new RunAutoloader();
?>