<?php
//|-----------------|NAMESPACE - RZ AUTOLOADER|------------------|
namespace Rz\Autoloader;
use Rz\Autoloader\Helper\AutoloaderClassHelper;
//|----------------------|VERSION COMPARE|-----------------------|
if(version_compare(PHP_VERSION, "5.4.0", "<")) {
    exit("The Rz SDK requires PHP version 5.4 or higher.");
}
//|--------------------|DEFINED RZ BASEPATH|---------------------|
defined("RZ_SDK_BASEPATH") or define("RZ_SDK_BASEPATH", trim(trim(__DIR__, "/")));
//|----------------------|INCLUDE SECTION|-----------------------|
//|Autoload Class Helper|----------------------------------------|
//require_once(RZ_SDK_BASEPATH . "/autoloader/autoloader_class_helper.php");
require_once("autoloader/autoloader_class_helper.php");
//|---------------------|CLASS - AUTOLOADER|---------------------|
class Autoloader {
    //|--------------|CLASS - VARIABLE SCOPE START|--------------|
    private $basePath = __DIR__;
    private $sdkDirPath = __DIR__;
    private const extension = ".php";
    private $dirList = array();
    //|---------------|CLASS - VARIABLE SCOPE END|---------------|
    
    //|------------------|CLASS - CONSTRUCTOR|-------------------|
    public function __construct($basePath, $dirList = array()) {
        $basePath = trim(trim($basePath, "/"));
        //$sdkDirPath = str_replace(dirname($basePath), "", $basePath);
        $sdkDirPath = str_replace($basePath, "", RZ_SDK_BASEPATH);
        $sdkDirPath = trim(trim($sdkDirPath, "/"));
        /* echo "<br />";
        echo "<br />";
        echo "sdkDirPath $sdkDirPath";
        echo "<br />";
        echo "<br />"; */
        $this->basePath = $basePath;
        $this->sdkDirPath = $sdkDirPath;
        $this->dirList = $dirList;
        //|SPL AUTOLOAD REGISTER|--------------------------------|
        spl_autoload_register(
            array($this, "onLoadClass")
        );
    }

    public function setDirList($dirList) {
        $this->dirList = $dirList;
    }

    //|-----------------|METHOD - ON LOAD CLASS|-----------------|
    private function onLoadClass($loadedClassName) {
        $loadedClassName = AutoloaderClassHelper::getNamespaceToClassName($loadedClassName);
        $fileNameList = AutoloaderClassHelper::getClassNameCombination($loadedClassName);
        $filePath = AutoloaderClassHelper::isPathExists($this->sdkDirPath, $this->dirList, $fileNameList, self::extension);
        //echo $filePath;
        if(!empty($filePath)) {
            //echo $filePath;
            require_once($filePath);
        }
    }
}
?>