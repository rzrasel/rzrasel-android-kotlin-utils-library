<?php
//defined("ROOT_PATH") or define("ROOT_PATH", trim(__DIR__, "/"));
defined("ROOT_PATH") or define("ROOT_PATH", "");
defined("LIBRARY_PATH") or define("LIBRARY_PATH", "library");
?>
<?php
require_once(ROOT_PATH . LIBRARY_PATH . "/utils/class_autoloader.php");
require_once(ROOT_PATH . "app_loader_config.php");
?>
<?php
$app = new App();
?>