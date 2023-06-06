<?php
class CodeArrayBeautify {
    //|---------------|CLASS VARIABLE SCOPE START|---------------|
    private $originalArray = array();
    private $beautifiedArray = array();
    private $keyMaxLength = -1;
    //|----------------|CLASS VARIABLE SCOPE END|----------------|

    public function runBeautify($arrayData, $middleChar = "=>", $lastChar = ";", $isKeyQuoted = true, $isValueQuoted = true) {
        $this->originalArray = array();
        $this->originalArray = $arrayData;
        $this->keyMaxLength = $this->getMaxKeyLength();
        $this->getBeautifiedArray($middleChar, $lastChar, $isKeyQuoted, $isValueQuoted);
        $this->onPrintArray();
    }
    public function getBeautifiedArray($middleChar, $lastChar, $isKeyQuoted, $isValueQuoted) {
        $paddingLegth = $this->keyMaxLength + 6;
        $this->beautifiedArray = array();
        foreach($this->originalArray as $key => $value) {
            $itemKey = "\"$key\"";
            $itemValue = ""
                . str_pad($itemKey, $paddingLegth, " ")
                . "$middleChar \"$value\"$lastChar";
            if(!$isKeyQuoted) {
                $paddingLegth = $this->keyMaxLength + 4;
                $itemKey = "$key";
            }
            if(!$isValueQuoted) {
                $itemValue = ""
                    . str_pad($itemKey, $paddingLegth, " ")
                    . "$middleChar $value$lastChar";
            }
            $this->beautifiedArray[] = $itemValue;
        }
    }
    public function getMaxKeyLength() {
        if(empty($this->originalArray)) {
            return -1;
        }
        $keyArray = array_keys($this->originalArray);
        $maxStrLength = -1;
        foreach($keyArray as $value) {
            $strLength = strlen($value);
            if($strLength > $maxStrLength) {
                $maxStrLength = $strLength;
            }
        }
        return $maxStrLength;
    }
    public function onPrintArray() {
        /* echo "<br />";
        echo "<br />";
        echo "\n"; */
        //echo "\n";
        foreach($this->beautifiedArray as $value) {
            echo $value;
            echo "\n";
        }
        //echo "\n";
        /* echo "\n";
        echo "<br />";
        echo "<br />"; */
    }
}
?>
<?php
abstract class CodeBeautify {
    public function getAssociativeArrayValue($arrayData, $index) {
        if(empty($arrayData)) {
            return null;
        }
        if($index < 0 || count($arrayData) >= $index) {
            return null;
        }
        return $arrayData[array_keys($arrayData)[$index]];
    }
}
?>