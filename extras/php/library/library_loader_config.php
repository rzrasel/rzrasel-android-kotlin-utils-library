<?php
abstract class LibraryLoaderConfig {
    public const rootDir = "library";
    private const direList = array(
        "helper",
        "libs",
        "utils",
    );
    public static function getDirList($dirList = array()) {
        $dataList = array();
        foreach(self::direList as $key) {
            $dataList[] = self::rootDir . "/" . $key;
        }
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
/* $dirList = array(
    "test",
    "var",
);
$dirList = LibraryLoaderConfig::getDirList($dirList);
print_r($dirList); */
?>