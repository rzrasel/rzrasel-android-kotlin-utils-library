<?php
//|---------------------|CLASS ARRAY TO SQL|---------------------|
class ArrayToSql {
    //|------------------|METHOD - TRIM ARRAY|-------------------|
    //|Calling - ArrayToSql::trimArray();|-----------------------|
    public static function trimArray($arrayData) {
        $indexingArray = array_map('trim', array_keys($arrayData));
        $keyArray = array_map("trim", $arrayData);
        return array_combine($indexingArray, $keyArray);
    }

    //|-----------------|METHOD - GET SQL ARRAY|-----------------|
    //|Calling - ArrayToSql::getSqlArray();|---------------------|
    public static function getSqlArray($arrayData) {
        //$escapedValues = array_map('mysql_real_escape_string', array_values($arrayData));
        $prep = array();
        foreach($arrayData as $key => $value) {
            $prep[$key] = "'" . $value . "'";
        }
        //$value = implode(", ", array_values($keyArray));
        //$sth = $db->prepare("INSERT INTO table ( " . implode(', ',array_keys($insData)) . ") VALUES (" . implode(', ',array_keys($prep)) . ")");
        return $prep;
    }

    //|---------------|METHOD - GET INSERT QUERY|----------------|
    public static function getInsertQuery($arrayData, $tableName) {
        $arrayData = ArrayToSql::trimArray($arrayData);
        $arrayData = ArrayToSql::getSqlArray($arrayData);
        $key =  implode(", ", array_keys($arrayData));
        $value = implode(", ", array_values($arrayData));
        //return "INSERT INTO $tableName ($key) VALUES ($value);";
        return "INSERT INTO $tableName VALUES ($value);";
    }
}
?>