<?php
class CharacterHelper{
    public static function getExtractUppercaseWord($strValue) {
        preg_match_all("/[A-Z][a-z]*/", $strValue, $option);
        return $option[0];
    }
    public static function getExtractUppercaseCharacter($strValue) {
        preg_match_all("/[A-Z]*/", $strValue, $option);
        return $option[0];
    }
    public static function onReplaceUppercaseOnly($strValue, $replaceBy = "") {
        $retVal = preg_replace("/([A-Z])/", $replaceBy . "$1", $strValue);
        return trim($retVal, $replaceBy);
    }
}
?>