<?php
abstract class DbKeyConstraints {
    public const FOREIGN_KEY    = "foreign_key";
    public const NOT_NULL_KEY   = "not_null_key"
    public const PRIMARY_KEY    = "primary_key";
    public const UNIQUE_KEY     = "unique_key";
}
?>
<?php
abstract class DbMenuTypeModelProperty {
    public const tableName      = "db_table_menu_type";
    public const id             = "id";
    public const serial         = "serial";
    public const name           = "name";
    public const slug           = "slug";
    public const isEnabled      = "is_enabled";
    public const createDate     = "create_date";
    public const modifiedDate   = "modified_date";
    //
    public const propId             = array(self::id, "BIGINT");
    public const propSerial         = array(self::serial, "INT");
    public const propName           = array(self::name, "VARCHAR(255)");
    public const propSlug           = array(self::slug, "VARCHAR(255)");
    public const propIsEnabled      = array(self::isEnabled, "BOOLEAN");
    public const propCreateDate     = array(self::createDate, "VARCHAR(64)");
    public const propModifiedDate   = array(self::modifiedDate, "VARCHAR(64)");
}
?>
<?php
class DbMenuTypeModel extends DbMenuTypeModelProperty {
    public $id              = parent::propId;
    public $serial          = parent::propSerial;
    public $name            = parent::propName;
    public $slug            = parent::propSlug;
    public $isEnabled       = parent::propIsEnabled;
    public $createDate      = parent::propCreateDate;
    public $modifiedDate    = parent::propModifiedDate;
}
?>
<?php
$modelObject = new DbMenuTypeModel();
foreach($modelObject as $key => $value) {
    //echo $key . " = " . $value;
    print_r($value);
}
?>
<?php
spl_autoload_register('myAutoloader');

function myAutoloader($className)
{
    $path = '/path/to/class/';

    include $path.$className.'.php';
}
?>
<?php

    class ClassAutoloader {
        public function __construct() {
            spl_autoload_register(array($this, 'loader'));
        }
        private function loader($className) {
            echo 'Trying to load ', $className, ' via ', __METHOD__, "()\n";
            include $className . '.php';
        }
    }

    $autoloader = new ClassAutoloader();

    $obj = new Class1();
    $obj = new Class2();

?>
<?php
spl_autoload_register(function($className)
{
    $namespace=str_replace("\\","/",__NAMESPACE__);
    $className=str_replace("\\","/",$className);
    $class=CORE_PATH."/classes/".(empty($namespace)?"":$namespace."/")."{$className}.class.php";
    include_once($class);
});
?>