<?php
//defined("ROOT_PATH") or define("ROOT_PATH", trim(__DIR__, "/"));
defined("ROOT_PATH") or define("ROOT_PATH", "");
?>
<?php
require_once(ROOT_PATH . "autoloader_config.php");
?>
<?php
$modelObject = new DbMenuTypeModel();
$createTableSqlGenerator = new DbCreateTableSqlGenerator();
$createTableSqlGenerator->onGenerate($modelObject);
?>