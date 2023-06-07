<?php
namespace RzSdk\Autoloader;
//|----------------|IS DEFINED - RZ SDK BASEPATH|----------------|
defined("RZ_SDK_BASEPATH") OR exit("No direct script access allowed");
defined("RZ_SDK_WRAPPER") OR exit("No direct script access allowed");
//|--------------|CLASS - AUTOLOADER CLASS HELPER|---------------|
class AutoloaderClassHelper {
    //|------------|METHOD - NAMESPACE TO CLASS NAME|------------|
    public static function getNamespaceToClassName($className) {
        $classParts = explode("\\", $className);

        //|RETURN VALUE|-----------------------------------------|
        return end($classParts);
    }

    public static function isPathExists($basePath, $dirList, $fileList, $extension = ".php") {
        //file_exists
        /* echo "<pre>";
        print_r($basePath);
        print_r($dirList);
        print_r($fileList);
        print_r($extension);
        echo "</pre>"; */
        //
        if(empty($dirList)) {
            $filePath = self::getExistsFilePath($basePath, "", $fileList, $extension);
            //return $filePath;
            if(file_exists($filePath)) {
                //echo $filePath . " file exists";
                return $filePath;
            }
            /* foreach($fileList as $fileItem) {
                $filePath = self::getClassPath($basePath, "", $fileItem, $extension);
                /=* echo $filePath;
                echo "<br />"; *=/
                if(file_exists($filePath)) {
                    //echo $filePath . " file exists";
                    return $filePath;
                }
            } */
        }
        foreach($dirList as $dirItem) {
            $filePath = self::getExistsFilePath($basePath, $dirItem, $fileList, $extension);
            //return $filePath;
            if(file_exists($filePath)) {
                //echo $filePath . " file exists";
                return $filePath;
            }
            /* foreach($fileList as $fileItem) {
                $filePath = self::getClassPath($basePath, $dirItem, $fileItem, $extension);
                /=* echo $filePath;
                echo "<br />"; *=/
                if(file_exists($filePath)) {
                    //echo $filePath . " file exists";
                    return $filePath;
                }
            } */
        }
        return null;
    }
    public static function getExistsFilePath($basePath, $dirPath, $fileList, $extension) {
        /* echo "Directory Path: base path " . $basePath . " directory path " . $dirPath;
        echo "<br />"; */
        foreach($fileList as $fileItem) {
            $filePath = self::getClassPath($basePath, $dirPath, $fileItem, $extension);
            /* echo "File Path: " . $filePath;
            echo "<br />"; */
            if(file_exists($filePath)) {
                //echo "File Exists: " . $filePath;
                return $filePath;
            }
        }
        return null;
    }
    public static function getClassPath($basePath, $dirPath, $fileName, $extension) {
        $bashPath = trim(trim($basePath, "/"));
        $dirPath = trim(trim($dirPath, "/"));
        $fileName = trim(trim($fileName, "/"));
        //
        /* $sdkDirPath = str_replace(dirname($basePath), "", $basePath);
        $baseDirPath = $sdkDirPath . "/" . $dirPath; */
        $baseDirPath = $basePath . "/" . $dirPath;
        if(empty($bashPath)) {
            $baseDirPath = $dirPath;
        }
        //
        $baseDirPath = trim(trim($baseDirPath, "/"));
        $fullPath = $baseDirPath . "/" . $fileName;
        //
        if(empty($baseDirPath)) {
            $fullPath = $fileName;
        }
        return $fullPath . $extension;
    }

    //|------------|METHOD - CLASS NAME COMBINATION|-------------|
    public static function getClassNameCombination($className) {
        $fileNameList = array();
        //|STR TO LOWER - CLASS NAME|----------------------------|
        $fileNameList[] = $className;
        $fileNameList[] = strtolower($className);
        $fileNameList[] = strtoupper($className);
        
        //|STR TO CAMEL CASE WITH SPACE - CLASS NAME|------------|
        $spacedName = self::strToCamelCase($className, " ");
        $fileNameList[] = $spacedName;
        $fileNameList[] = strtolower($spacedName);
        $fileNameList[] = strtoupper($spacedName);
        
        //|STR TO CAMEL CASE WITH DASH - CLASS NAME|-------------|
        $dashedName = self::strToCamelCase($className, "-");
        $fileNameList[] = $dashedName;
        $fileNameList[] = strtolower($dashedName);
        $fileNameList[] = strtoupper($dashedName);
        
        //|STR TO CAMEL CASE WITH UNDERSCORE - CLASS NAME|-------|
        $underscoredName = self::strToCamelCase($className, "_");
        $fileNameList[] = $underscoredName;
        $fileNameList[] = strtolower($underscoredName);
        $fileNameList[] = strtoupper($underscoredName);
        $fileNameList[] = self::strToCamelCase($className, "-");

        //|RETURN VALUE|-----------------------------------------|
        return $fileNameList;
    }

    //|---------------|METHOD - STR TO CAMEL CASE|---------------|
    public static function strToCamelCase($strValue, $replaceBy = "") {
        //|Preg Replace Upper Case Character|--------------------|
        $retVal = preg_replace("/([A-Z])/", $replaceBy . "$1", $strValue);

        //|RETURN VALUE|-----------------------------------------|
        //return trim($retVal, $replaceBy);
        return trim($retVal, $replaceBy);
    }
}
?>