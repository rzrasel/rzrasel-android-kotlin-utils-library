<?php
//defined("ROOT_PATH") or define("ROOT_PATH", trim(__DIR__, "/"));
defined("ROOT_PATH") or define("ROOT_PATH", "");
?>
<?php
require_once(ROOT_PATH . "library/utils/class_autoloader.php");
?>
<?php
$dirList = array(
    "library",
    "model",
);
?>
<?php
/* $classAutoloader = new ClassAutoloader($dirList);
//
$libDirList = LibraryLoaderConfig::getDirList($dirList);
$classAutoloader->setDirList($libDirList);
//
$modelDirList = ModelLoaderConfig::getDirList($libDirList);
$classAutoloader->setDirList($modelDirList); */
//
//$classAutoloader->onPrintDirList();
?>