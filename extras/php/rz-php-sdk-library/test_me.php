<?php
namespace RzSdk\Library;
//|----------------|IS DEFINED - RZ SDK BASEPATH|----------------|
defined("RZ_SDK_BASEPATH") OR exit("No direct script access allowed");
defined("RZ_SDK_WRAPPER") OR exit("No direct script access allowed");
class TestMe {
    public function __construct() {
        echo "Hi this is " . __CLASS__;
    }
}
?>