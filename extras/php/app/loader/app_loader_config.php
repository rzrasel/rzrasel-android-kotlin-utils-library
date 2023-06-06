<?php
class AppLoaderConfig {
    public const rootDir = "app";
    private const direList = array(
        "controllers",
        "core",
        "loader",
        "models",
        "views",
    );
    public static function getDirList($dirList = array()) {
        $dataList = array();
        foreach(self::direList as $key) {
            $dataList[] = trim(trim(self::rootDir, "/")) . "/" . trim(trim($key, "/"));
        }
        $dataList[] = trim(trim(LIBRARY_PATH, "/"));
        if(empty($dirList)) {
            return $dataList;
        }
        if(!is_array($dirList)) {
            return $dataList;
        }
        return array_merge($dirList, $dataList);
    }
}
?>
<?php
class AppLoaderRun {
    public function onRun() {
        $appLoaderConfig = new AppLoaderConfig();
        $dirList = $appLoaderConfig->getDirList();
        $classAutoloader = new ClassAutoloader($dirList);
        //
        $libDirList = LibraryLoaderConfig::getDirList($dirList);
        $classAutoloader->setDirList($libDirList);
        //
        $modelDirList = ModelLoaderConfig::getDirList($libDirList);
        $classAutoloader->setDirList($modelDirList);
        //
        /* echo "RUN_FROM: class " . __CLASS__ . " method " . __METHOD__;
        echo "<br />";
        $classAutoloader->onPrintDirList(); */
    }
}
$appLoaderRun = new AppLoaderRun();
$appLoaderRun->onRun();
?>