<?php
require_once("utils.php");
require_once("input-field.php");
?>
<!DOCTYPE html>
<html lang="en-US">
<head>
<title>HTML Tutorial</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="Keywords" content="HTML, Python, CSS, SQL, JavaScript, How to, PHP, Java, C, C++, C#, jQuery, Bootstrap, Colors, W3.CSS, XML, MySQL, Icons, NodeJS, React, Graphics, Angular, R, AI, Git, Data Science, Code Game, Tutorials, Programming, Web Development, Training, Learning, Quiz, Exercises, Courses, Lessons, References, Examples, Learn to code, Source code, Demos, Tips, Website">
<meta name="Description" content="Well organized and easy to understand Web building tutorials with lots of examples of how to use HTML, CSS, JavaScript, SQL, Python, PHP, Bootstrap, Java, XML and more.">
<meta property="og:image" content="https://www.w3schools.com/images/w3schools_logo_436_2.png">
<meta property="og:image:type" content="image/png">
<meta property="og:image:width" content="436">
<meta property="og:image:height" content="228">
<meta property="og:description" content="W3Schools offers free online tutorials, references and exercises in all the major languages of the web. Covering popular subjects like HTML, CSS, JavaScript, Python, SQL, Java, and many, many more.">
<link rel="icon" href="/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div>
        <div class="spacer200"></div>
        <div class="maindivcenter">
            <!--<?=($_SERVER["PHP_SELF"])?>-->
            <form action="<?=getCurrentUrl()?>" method="POST" enctype="multipart/form-data" id="form1">
                <?php //echo file_get_contents("https://rzrasel.000webhostapp.com/textpadding/input-field.php"); ?>
                <?php echo getInputFields(); ?>
            </form>
        </div>
    </div>
</body>
</html>