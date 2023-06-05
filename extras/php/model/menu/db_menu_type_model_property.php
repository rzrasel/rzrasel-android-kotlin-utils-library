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