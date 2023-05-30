<?php
require_once("text-case.php");
require_once("regular-text-padding.php");
require_once("spacial-text-padding.php");
?>
<?php
function getInputFields() {

    $regularFormattedText = "";
    $spacialFormattedText = "";
    $formattedTextValue = "";
    $indentCountValue = 0;
    $spaceCountValue = 4;
    $characterCaseValue = "upper_case";

    $selectedIndentCount = "";
    $selectedSpaceCount = "";
    $characterCaseCount = "";

    if (!empty($_POST)) {
        $formattedTextValue = "Default text";
        $indentCountValue = 0;
        $spaceCountValue = 4;
        $characterCaseValue = "upper_case";
        //
        $formattedTextValue = $_POST["formatted_text"];
        $indentCountValue = $_POST["indent_count"];
        $spaceCountValue = $_POST["space_count"];
        $characterCaseValue = $_POST["character_case"];
        //
        $textCase = TextCase::UpperCase;
        if($characterCaseValue == "alike_case") {
            $textCase = TextCase::AlikeCase;
        } else if($characterCaseValue == "lower_case") {
            $textCase = TextCase::LowerCase;
        } else if($characterCaseValue == "upper_case") {
            $textCase = TextCase::UpperCase;
        } else if($characterCaseValue == "uc_first_case") {
            $textCase = TextCase::UcFirstCase;
        } else if($characterCaseValue == "uc_words_case") {
            $textCase = TextCase::UcWordsCase;
        }
        //
        $fullPadLength = 64;
        $regularTextPadding = new RegularTextPadding();
        $regularTextPadding->withPad("-")->withPadFullLength($fullPadLength)
        ->withFullLeftText("|")
        ->withFullRightText("|")
        ->withPadLeftText("|")
        ->withPadRightText("|")
        ->withCase($textCase)
        ->withTabCount($indentCountValue)
        ->withTabSpace($spaceCountValue);
        $regularTextPadding->withCase($textCase);
        $regularTextPadding->generate($formattedTextValue);
        $regularFormattedText = $regularTextPadding->getPadString();
        //
        $spacialTextPadding = new SpacialTextPadding();
        $spacialTextPadding->withPad("-")->withPadFullLength($fullPadLength)
        ->withFullLeftText("|")
        ->withFullRightText("|")
        ->withPadLeftText("|")
        ->withPadRightText("|")
        ->withTabCount($indentCountValue)
        ->withTabSpace($spaceCountValue);
        $spacialTextPadding->withCase($textCase);
        $spacialTextPadding->generate($formattedTextValue);
        $spacialFormattedText = $spacialTextPadding->getPadString();
    }

    $optionIndentCount = "\n";
    for($index = 0; $index <= 100; $index++) {
        $selectedIndentCount = "";
        if($indentCountValue == $index) {
            $selectedIndentCount = " selected";
        }
        $optionIndentCount .= '<option value="' . $index . '"' . $selectedIndentCount . '>' . $index . '</option>' . "\n";
    }

    $optionSpaceCount = "\n";
    for($index = 0; $index <= 100; $index++) {
        $selectedSpaceCount = "";
        if($spaceCountValue == $index) {
            $selectedSpaceCount = " selected";
        }
        $optionSpaceCount .= '<option value="' . $index . '"' . $selectedSpaceCount . '>' . $index . '</option>' . "\n";
    }

    $characterCaseArray = array(
        "alike_case" => "Alike Case",
        "lower_case" => "Lower Case",
        "uc_first_case" => "Uc First Case",
        "uc_words_case" => "Uc Words Case",
        "upper_case" => "Upper Case",
    );
    $optionCharacterCase = "\n";
    foreach ($characterCaseArray as $key => $value) {
        $characterCaseCount = "";
        if($characterCaseValue == $key) {
            $characterCaseCount = " selected";
        }
        $optionCharacterCase .= '<option value="' . $key . '"' . $characterCaseCount . '>' . $value . '</option>' . "\n";
    }

    $retVal = "<div>"


    . '<div class="tworow">' . "\n"
        . '<div class="twocolumn80">' . "\n"
        . '<label for="name">Formatted Text:</label>' . "\n"
        . '<input type="text" id="formatted-text" name="formatted_text" value="' . $formattedTextValue . '" required>' . "\n"
        . "</div>" . "\n"


        . '<div class="twocolumn20">' . "\n"
        . '<label for="cars">Indent Count:</label>' . "\n"
        . '<select id="indent-count" name="indent_count">' . "\n"
        . $optionIndentCount
        . "</select>" . "\n"
        . "</div>" . "\n"

    . "</div>" . "\n"

    . '<div class="spacer16"></div>' . "\n"



    . '<div class="threerow">' . "\n"
        . '<div class="threecolumn">&nbsp</div>' . "\n"

        . '<div class="threecolumn">' . "\n"
        . '<label for="cars">Space Count:</label>' . "\n"
        . '<select id="space-count" name="space_count">' . "\n"
        . $optionSpaceCount
        . "</select>" . "\n"
        . "</div>" . "\n"


        . '<div class="threecolumn">' . "\n"
        . '<label for="cars">Character Case:</label>' . "\n"
        . '<select id="character-case" name="character_case">' . "\n"
        . $optionCharacterCase
        /*. '<option value="alike-case">Alike Case</option>' . "\n"
        . '<option value="lower-case">Lower Case</option>' . "\n"
        . '<option value="ucfirst-case">Ucfirst Case</option>' . "\n"
        . '<option value="ucwords-case">Ucwords Case</option>' . "\n"
        . '<option value="upper-case">Upper Case</option>' . "\n"*/
        . "</select>" . "\n"
        . "</div>" . "\n"

    . "</div>" . "\n"


    . '<div class="spacer16"></div>' . "\n"


    . '<div class="threerow">' . "\n"
        . '<div class="threecolumn">&nbsp</div>' . "\n"
        . '<div class="threecolumn">&nbsp</div>' . "\n"
        . '<div class="threecolumn">' . "\n"
            . '<button type="submit" form="form1">Formate Text</button>' . "\n"
            //. '<input type="button" value="Formate Text" />'
        . "</div>" . "\n"
    . "</div>" . "\n"


    . "</div>" . "\n"

    . '<div class="spacer16"></div>' . "\n"

    . '<div class="newthreerow">' . "\n"
    //. "Regular Format:" . $regularFormattedText . "\n"
    . '<div>Regular Format:</div>'
    . '<div>' . "\n"
    . '<input type="text" id="regular-formatted-text" name="regular_formatted_text" value="' . $regularFormattedText . '" onClick="this.select();">' . "\n"
    . '</div>' . "\n"
    . "</div>" . "\n"

    . '<div class="spacer16"></div>' . "\n"

    . '<div class="newthreerow">' . "\n"
    . '<div>Special Format:</div>'
    //. '<div>' . $spacialFormattedText . '</div>' . "\n"
    . '<div>' . "\n"
    . '<input type="text" id="spacial-formatted-text" name="spacial_formatted_text" value="' . $spacialFormattedText . '" onClick="this.select();">' . "\n"
    . '</div>' . "\n"

    . '<div class="spacer16"></div>' . "\n"
    . '<div>' . "\n"
    . date("Y-m-d H:i:s", time()) . "\n"
    . '</div>' . "\n"
    . "</div>" . "\n"
    . "";
    return $retVal;
}
?>
<?php
/*
<!--<div>
    <div class="tworow">
        <div class="twocolumn80">
            <label for="name">Formated Text:</label>
            <input type="text" id="formated-text" name="formated_text" required>
        </div>
        <div class="twocolumn20">
            <label for="cars">Indent Count:</label>
            <select id="indentcount" name="indent_count">
                <option value="volvo">Volvo</option>
                <option value="saab">Saab</option>
                <option value="mercedes">Mercedes</option>
                <option value="audi">Audi</option>
                <?php
                for($index = 0; $index <= 10; $index++) {
                    echo '<option value="volvo">' . ($index + 1) . '</option>';
                }
                ?>
            </select>
        </div>
    </div>
    <div class="spacer16"></div>
    <div class="threerow">
        <div class="threecolumn">&nbsp</div>
        <div class="threecolumn">
            <label for="cars">Space Count:</label>
            <select id="space-count" name="space_count">
                <option value="volvo">Volvo</option>
                <option value="saab">Saab</option>
                <option value="mercedes">Mercedes</option>
                <option value="audi">Audi</option>
            </select>
        </div>
        <div class="threecolumn">
            <label for="cars">Character Case:</label>
            <select id="character-case" name="character_case">
                <option value="volvo">Volvo</option>
                <option value="saab">Saab</option>
                <option value="mercedes">Mercedes</option>
                <option value="audi">Audi</option>
            </select>
        </div>
    </div>
    <div class="spacer16"></div>
    <div class="threerow">
        <div class="threecolumn">&nbsp</div>
        <div class="threecolumn">&nbsp</div>
        <div class="threecolumn">
            <button type="button">Formate Text</button>
        </div>
    </div>
</div>
</div>-->
*/
?>