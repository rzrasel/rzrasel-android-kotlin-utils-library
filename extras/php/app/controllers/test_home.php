<?php
class TestHome extends Controller {
    public function __construct() {
        echo "CLASS: --" . __CLASS__;
    }
    public function index() {
    }
    public function test() {
        echo "Test called";
    }
}
?>