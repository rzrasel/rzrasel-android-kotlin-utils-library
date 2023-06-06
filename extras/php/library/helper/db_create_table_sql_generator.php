<?php
class DbCreateTableSqlGenerator {
    private $tableName;
    private $keyConstraints = array();
    public function setTableName($tableName) {
        $this->tableName = $tableName;
    }
    public function setPrimaryKey($columnName, $keyConstraint, $constraintName = null) {}
    public function onGenerate($dataModelObject, $tableName) {
        $this->tableName = $tableName;
        foreach($dataModelObject as $key => $value) {
            //echo $key . " = " . $value;
            print_r($value);
        }
    }
    public function setKeyConstraint($columnName, $keyConstraint, $constraintName = null) {
        $keyConstraints = "";
        $tableName = str_replace($this->tableName, "db_table_", "");
        $keyColumn = $columnName;
        $constraintTag = $constraintName;
        if(empty($constraintName)) {
            $constraintTag = $columnName;
            if(is_array($columnName)) {
                $constraintTag = trim(implode("_", $columnName));
            }
        }
        $constraintTag = $tableName . "_" . $constraintTag;
        if(strlen($constraintTag) > 29) {
            $constraintTag = substr($constraintTag, 0, 29);
        }
        if(is_array($columnName)) {
            $keyColumn = trim(implode(", ", $columnName));
        }
        if($keyConstraint == DbKeyConstraints::PRIMARY_KEY) {
            $keyConstraint = "CONSTRAINT pk_" . $constraintTag
                . " PRIMARY KEY (" . $keyColumn . ")";
        } else if($keyConstraint == DbKeyConstraints::FOREIGN_KEY) {
            $keyConstraint = "CONSTRAINT pk_" . $constraintTag
                . " PRIMARY KEY (" . $keyColumn . ")";
        }
        $this->keyConstraints[] = $keyConstraints;
    }
}
?>