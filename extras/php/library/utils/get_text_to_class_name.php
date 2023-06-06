<?php
class GetTextToClassName {
    private $replaceList = array(
        ".",
        "-",
        "_",
    );
    public function getName($className, $replacedBy = "") {
        $classList = $this->getClassList(trim($className));
        //
        /* if($this->isClassExists($classItem)) {
            echo $classItem . " class exists " . __CLASS__;
        } else {
            echo $classItem . " class not exists " . __CLASS__;
        } */
        //
        foreach($classList as $item) {
            if(class_exists($item)) {
                return $item;
            }
        }
        //
        return null;
    }
    public function getClassList($className) {
        $classList = array();
        $item = str_replace($this->replaceList, " ", trim($className));
        $item = strtolower($this->replaceCamelCase($item, " "));
        //
        $classList[] = str_replace(" ", "", $item);
        $classList[] = str_replace(" ", "", strtoupper($item));
        $classList[] = str_replace(" ", "", ucwords($item));
        //
        $classList[] = str_replace(" ", "_", $item);
        $classList[] = str_replace(" ", "_", strtoupper($item));
        $classList[] = str_replace(" ", "_", ucwords($item));
        //
        /* echo "<br />";
        echo "class name " . $item;
        echo "<pre>";
        print_r($classList);
        echo "</pre>";
        echo "<br />"; */
        //
        return $classList;
    }
    public function isClassExists($className) {
        return class_exists($className);
    }
    public function replaceCamelCase($strValue, $replaceBy = "") {
        $retVal = preg_replace("/([A-Z])/", $replaceBy . "$1", $strValue);
        //return trim($retVal, $replaceBy);
        return trim($retVal, $replaceBy);
    }
}
?>