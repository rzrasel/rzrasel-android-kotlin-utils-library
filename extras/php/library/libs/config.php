<?php
//define("DB_PATH", "db/bangla-to-engilsh-word.sqlite3");
abstract class DbConfig {
    public const dbPath = "db/bangla-to-engilsh-word.sqlite3";
}
?>
<?php
abstract class DbTable {
    public const dbTableMetaProperty  = "db_table_meta_property";
    public const dashboardMainMenu  = "bn_dashboard_main_menu";
    public const humanBodyPart  = "bn_human_body_parts";
    public const sevenDay       = "bn_seven_days";
}
?>
<?php
abstract class DbTableRow {
    public const dashboardMainMenuId     = "dashboard_menu_id";
    public const bodyPartId     = "body_part_id";
    public const sevenDayId     = "seven_day_id";
    public const serial         = "serial";
    public const bengali        = "bengali";
    public const english        = "english";
    public const slug           = "slug";
    public const imagePath      = "image_path";
    public const audioPath      = "audio_path";
    public const bnSevenDays = array(
        "seven_day_id"        => "BIGINT",
        "serial"              => "INT",
        "bengali"             => "TEXT",
        "english"             => "TEXT",
        "slug"                => "VARCHAR(255)",
        "big_image_path"      => "TEXT",
        "small_image_path"    => "TEXT",
        "audio_path"          => "TEXT",
        "is_enabled"          => "BOOLEAN",
        "create_date"         => "VARCHAR(64)",
        "modified_date"       => "VARCHAR(64)",
    );
}
?>
<?php
abstract class DbTableBnDboardMainMenuOld01 {
    public const tableName      = "bn_dashboard_main_menu";
    public const id             = "dashboard_menu_id";
    public const serial         = "serial";
    public const bnTitle        = "bn_title";
    public const enTitle        = "en_title";
    public const bnDescription  = "bn_description";
    public const enDescription  = "en_description";
    public const slug           = "slug";
    public const bnImagePath    = "bn_image_path";
    public const enImagePath    = "en_image_path";
    public const isEnabled      = "is_enabled";
    public const createDate     = "create_date";
    public const modifiedDate   = "modified_date";
    public const tableRowList = array(
        DbTableBnDboardMainMenuOld01::id             => "BIGINT",
        DbTableBnDboardMainMenuOld01::serial         => "INT",
        DbTableBnDboardMainMenuOld01::bnTitle        => "TEXT",
        DbTableBnDboardMainMenuOld01::enTitle        => "TEXT",
        DbTableBnDboardMainMenuOld01::bnDescription  => "TEXT",
        DbTableBnDboardMainMenuOld01::enDescription  => "TEXT",
        DbTableBnDboardMainMenuOld01::slug           => "VARCHAR(255)",
        DbTableBnDboardMainMenuOld01::bnImagePath    => "TEXT",
        DbTableBnDboardMainMenuOld01::enImagePath    => "TEXT",
        DbTableBnDboardMainMenuOld01::isEnabled      => "BOOLEAN",
        DbTableBnDboardMainMenuOld01::createDate     => "VARCHAR(64)",
        DbTableBnDboardMainMenuOld01::modifiedDate   => "VARCHAR(64)",
    );
    //echo "array indext 0 " . $bnDashboardMainMenu[array_keys($bnDashboardMainMenu)[0]];
    public const jsonArray = array(
        "bn_title"          => DbTableBnDboardMainMenuOld01::bnTitle,
        "en_title"          => DbTableBnDboardMainMenuOld01::enTitle,
        "bn_description"    => DbTableBnDboardMainMenuOld01::bnDescription,
        "en_description"    => DbTableBnDboardMainMenuOld01::enDescription,
        "slug"              => DbTableBnDboardMainMenuOld01::slug,
        "bn_image_path"     => DbTableBnDboardMainMenuOld01::bnImagePath,
        "en_image_path"     => DbTableBnDboardMainMenuOld01::enImagePath,
    );

    public $dropTable         = "DROP TABLE IF EXISTS " . DbTableBnDboardMainMenuOld01::tableName;
    public $createTable         = "CREATE TABLE IF NOT EXISTS " . DbTableBnDboardMainMenuOld01::tableName;
    public static $dashboardMenuId  = "BIGINT";
    //public $dashboard_menu_id   = self::$dashboardMenuId;
    public $dashboard_menu_id   = "BIGINT";
    public $serial              = "INT";
    public $bn_title            = "TEXT";
    public $en_title            = "TEXT";
    public $bn_description      = "TEXT";
    public $en_description      = "TEXT";
    public $slug                = "VARCHAR(255)";
    public $bn_image_path       = "TEXT";
    public $en_image_path       = "TEXT";
    public $is_enabled          = "BOOLEAN";
    public $create_date         = "VARCHAR(64)";
    public $modified_date       = "VARCHAR(64)";
    public $pkId                = "CONSTRAINT pk_"
        . DbTableBnDboardMainMenuOld01::tableName . "_" . DbTableBnDboardMainMenuOld01::id
        . " PRIMARY KEY (" . DbTableBnDboardMainMenuOld01::id . ")";
}
//https://itecnote.com/tecnote/php-abstract-constants-in-php-force-a-child-class-to-define-a-constant/
?>
<?php
/* abstract class AbstractClass{
    public const id = "seven_day_id";
    public const prop2 = 'property 2';
    public $prop3 = 'property 3';
    public $seven_day_id = "BIGINT";
    public $prop5 = AbstractClass::prop2;
 }
class child extends AbstractClass {
    public $prop6 = 'protected property';
    public $prop7 = 'private property';
}

$object = new child();

foreach($object as $key => $value) {
    print "$key => $value\n";
    echo "<br />";
}
echo "<br />";
echo "<br />";
echo "<br />"; */
?>
<?php
abstract class UrlQuery {
    public const allData            = "all-data";
    public const dashboardMainMenu  = "dashboard-main-menu";
    public const humanBodyPart      = "human-body";
    public const sevenDay           = "seven-day";
}
?>