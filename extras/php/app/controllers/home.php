<?php
class Home extends Controller {
    public function __construct() {
        //echo "CLASS: " . __CLASS__;
    }
    public function index() {
        $user = $this->model("user");

        $this->view("home/index", ["name" => $user->name]);
    }
    public function test() {
        echo "Test called";
    }
}
?>