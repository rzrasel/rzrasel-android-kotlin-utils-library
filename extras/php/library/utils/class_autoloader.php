<?php
class ClassAutoloader {
    private const extensions = ".php";
    private $dirList = array();
    //
    public function __construct($dirList = array()) {
        $this->dirList = $dirList;
        spl_autoload_register(
            array($this, "onLoad")
        );
    }
    public function setDirList($dirList) {
        $this->dirList = $dirList;
    }
    private function onLoad($className) {
        /* foreach($this->dirList as $item) {
            echo $this->onConvertCamelCase($item, "_");
            echo "<br />";
        } */
        $fileNameList = $this->getFileNameList($className);
        /* foreach($fileNameList as $item) {
            echo $item;
            echo "<br />";
        } */
        $filePath = $this->getExistedPath($this->dirList, $fileNameList);
        if(!empty($filePath)) {
            require_once($filePath);
        }
        /* echo $filePath;
        echo "<br />";
        echo 'Trying to load ', $className, ' via ', __METHOD__, "()\n"; */
        //include $className . '.php';
        //echo "EXT-" . self::extensions;
    }
    private function getExistedPath($dirList, $fileList) {
        //file_exists
        foreach($dirList as $dirItem) {
            foreach($fileList as $fileItem) {
                $filePath = trim($dirItem, "/") . "/" . trim($fileItem, "/") . self::extensions;
                /* echo $filePath;
                echo "<br />"; */
                if(file_exists($filePath)) {
                    return $filePath;
                }
            }
        }
        return null;
    }
    private function getFileNameList($fileName) {
        $fileNameList = array();
        $fileNameList[] = $fileName;
        $fileNameList[] = strtolower($fileName);
        $fileNameList[] = strtoupper($fileName);
        //
        $spacedName = $this->onConvertCamelCase($fileName, " ");
        $fileNameList[] = $spacedName;
        $fileNameList[] = strtolower($spacedName);
        $fileNameList[] = strtoupper($spacedName);
        //
        $dashedName = $this->onConvertCamelCase($fileName, "_");
        $fileNameList[] = $dashedName;
        $fileNameList[] = strtolower($dashedName);
        $fileNameList[] = strtoupper($dashedName);
        //
        $underscoredName = $this->onConvertCamelCase($fileName, "_");
        $fileNameList[] = $underscoredName;
        $fileNameList[] = strtolower($underscoredName);
        $fileNameList[] = strtoupper($underscoredName);
        $fileNameList[] = $this->onConvertCamelCase($fileName, "-");
        return $fileNameList;
    }
    private function onConvertCamelCase($strValue, $replaceBy = "") {
        $retVal = preg_replace("/([A-Z])/", $replaceBy . "$1", $strValue);
        //return trim($retVal, $replaceBy);
        return trim($retVal, $replaceBy);
    }
    public function onPrintDirList() {
        echo "<pre>";
        print_r($this->dirList);
        echo "</pre>";
    }
}
//$autoloader = new ClassAutoloader();
?>
<?php
/* $obj = new Class1();
$obj = new Class2(); */
?>
<?php
/* spl_autoload_register('myAutoloader');

function myAutoloader($className)
{
    $path = '/path/to/class/';
    include $path.$className.'.php';
} */
?>
<?php
/* class ClassAutoloader {
    public function __construct() {
        spl_autoload_register(array($this, 'loader'));
    }
    private function loader($className) {
        echo 'Trying to load ', $className, ' via ', __METHOD__, "()\n";
        include $className . '.php';
    }
} */
//$autoloader = new ClassAutoloader();
?>