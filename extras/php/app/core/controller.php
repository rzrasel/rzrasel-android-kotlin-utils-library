<?php
class Controller {
    public function model($model) {
        //return new $model;
        $filePath = "app/models/" . $model . ".php";
        if(file_exists($filePath)) {
            require_once($filePath);
            return new $model;
        }
        return null;
    }
    public function view($view, $data = array()) {
        $filePath = "app/views/" . $view . ".php";
        if(file_exists($filePath)) {
            require_once($filePath);
        }
    }
}
?>