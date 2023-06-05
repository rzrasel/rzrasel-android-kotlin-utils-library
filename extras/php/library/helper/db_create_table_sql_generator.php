<?php
class DbCreateTableSqlGenerator {
    public function onGenerate($dataModelObject) {
        foreach($dataModelObject as $key => $value) {
            //echo $key . " = " . $value;
            print_r($value);
        }
    }
}
?>