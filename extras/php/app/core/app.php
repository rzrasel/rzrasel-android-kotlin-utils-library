<?php
class App {
    private const controllerPathPrifix = "app/controllers/";
    protected $controller = "home";
    protected $method = "index";
    protected $params = array();
    public function __construct() {
        $controller = $this->controller;
        $getTextToClassName = new GetTextToClassName();
        $className = $getTextToClassName->getName($controller);
        //
        $controllerPath = self::controllerPathPrifix . $this->controller . ".php";
        $url = $this->parseUrl();
        if(!empty($url)) {
            if(isset($url[0])) {
                $controller = $url[0];
                unset($url[0]);
                $className = $getTextToClassName->getName($controller);
                //echo "Class name: " . $className . " found";
                //echo $this->controller;
                /* $filePath = self::controllerPathPrifix . $controller . ".php";
                if(file_exists($filePath)) {
                    $this->controller = $controller;
                    $controllerPath = $filePath;
                } else {
                    //echo $controller . " controller not exists";
                } */
                if(!empty($className)) {
                    $this->controller = $className;
                }
            }
            if(!empty($this->controller)) {
                $this->controller = new $this->controller;
                if(isset($url[1])) {
                    $method = $url[1];
                    unset($url[1]);
                    if(method_exists($this->controller, $method)) {
                        $this->method = $method;
                    }
                }
                $this->params = $url ? array_values($url) : [];
                call_user_func_array([$this->controller, $this->method], $this->params);
                return;
            }
        }
        //echo $controllerPath;
        //require_once("../controllers");
        $this->controller = new $this->controller;
    }
    public function parseUrl() {
        if(!empty($_GET["url"])) {
            if(isset($_GET["url"])) {
                $url = trim(trim($_GET["url"], "/"));
                $url = filter_var($url, FILTER_SANITIZE_URL);
                $url = explode("/", $url);
                return $url;
            }
        }
        return null;
    }
}
?>