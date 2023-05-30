<?php
$jsonArray = array(
    "id " => "id_value ",
    "name " => "name_value ",
    " description " => "description_value ",
);
//
$keyArray = trimArray($jsonArray);
$sqlArray = getSqlArray($keyArray);
//
$tableName = "test_table";
$key =  implode(", ", array_keys($sqlArray));
$value = implode(", ", array_values($sqlArray));

$sqlQuery = "INSERT INTO $tableName ($key) VALUES ($value);";
//
echo "\n";
echo "\n";
echo $key;
echo "\n";
echo "\n";
//echo "<br />";
//echo "<br />";
echo $value;
echo "\n";
echo "\n";
//echo "<br />";
echo $sqlQuery;
echo "\n";
echo "\n";
echo json_encode(jsonArray($keyArray));
echo "\n";
//echo "<br />";
?>
<?php
function jsonArray($arrayData) {
    return array(
        "name_value" => $arrayData["name"],
        "description" => $arrayData["description"],
    );
}
?>
<?php
function trimArray($arrayData) {
    $indexingArray = array_map('trim', array_keys($arrayData));
    $keyArray = array_map("trim", $arrayData);
    return array_combine($indexingArray, $keyArray);
}
function getSqlArray($arrayData) {
    //$escapedValues = array_map('mysql_real_escape_string', array_values($arrayData));
    $prep = array();
    foreach($arrayData as $key => $value) {
        $prep[$key] = "'" . $value . "'";
    }
    //$value = implode(", ", array_values($keyArray));
    //$sth = $db->prepare("INSERT INTO table ( " . implode(', ',array_keys($insData)) . ") VALUES (" . implode(', ',array_keys($prep)) . ")");
    return $prep;
}
?>
<?php
/*
$my_array = array( 'one 1' => '1', 'two 2' => '2' );
$keys = str_replace( ' ', '', array_keys( $my_array ) );
$results = array_combine( $keys, array_values( $my_array ) );





Satyuga ke kaand | Untold Reality of Vedic period | The Realist Azad
https://youtu.be/st_qcv3Ki1k
LIVE168 | Valmiki Ramayan Ki Bhyanak Sachchai | Dekhkar Udd Jayege Ram/Hanuman Bhakton Ke hosh
https://youtu.be/-5aprNoXSrU
*/
?>

https://mcw19.com/af/Xj9AgKFs/prpllrpu?utm_campaign=paidmed&utm_medium=%7Bzoneid%7D&utm_source=%7Bcost%7D



<?php
$settingsJson = new SettingsJson();
$jsonData = $settingsJson->getJsonArray();
echo $jsonData;
?>
<?php
class SettingsJson {
    private $jsonArray = array();
    public function getJsonArray() {
        //Default Data
        //Current Data
        $arrayData = array(
            "language" => "english",
            "sound" => "sound-on",
        );
        $this->onJsonArray($arrayData);
        return json_encode($this->jsonArray);
    }
    private function onJsonArray($arrayData) {
        if(empty($arrayData)) {
            return;
        }
        /*$this->jsonArray[] = array(
            "language" => $arrayData["language"],
        );*/
        $this->jsonArray[] = $arrayData;
    }
}
?>

232 - Satya Sanatan sent disciple to give proof of Vedic period and temple before Buddha - Science Journey
২৩২ - সত্য সনাতন বুদ্ধের আগে বৈদিক যুগ ও মন্দিরের প্রমাণ দিতে শিষ্যকে পাঠিয়েছিলেন - বিজ্ঞান যাত্রা
Title: 232 | Satya Sanatan चेला भेजा Buddha से पहले वैदिक काल और मंदिर का सबूत देने | Science Journey
Author: Rational World
Link: https://youtu.be/YMETP5HFlYc

222 - Tulsi's Ramcharitmanas hate book? But the flood of evidences - Arjak Sangh, Science Journey Live
২২২ - তুলসীর রামচরিতমানস বিদ্বেষী গ্রন্থ? কিন্তু প্রমাণের বন্যা- আরজক সংঘ, সায়েন্স জার্নি লাইভ
Title: 222 | तुलसी का रामचरितमानस नफ़रती किताब? पर सबूतों की लगी झड़ी | Arjak Sangh, Science Journey Live
Author: Rational World
Link: https://youtu.be/uG6ex9CNFKU

38 - Why did Ram think wrong about women? Reality of Ram Rajya - Science Journey
38 - রাম নারীদের নিয়ে ভুল ভাবলেন কেন? রাম রাজ্যের বাস্তবতা - বিজ্ঞান যাত্রা
Title: 38 | राम स्त्रियों के बारे में ग़लत क्यों सोचते थे? | Ram Rajya ki hakikat | Science Journey
Author: Untold History
Link: https://youtu.be/AmDtpAsHSG0

Tunni had to face indiscriminate thrashing for abusing in live
লাইভে গালিগালাজ করার জন্য নির্বিচারে মারধরের শিকার হতে হয়েছে টুনিকে
Title: Live Me Aakar Gaali Dena Tunni Ko Pada Bhari Hui Andhadhundh Thukai
Author: The Realist Azad
Link: https://youtu.be/5cKMEvQrlGE

42 - By crossing the river of milk, liquor, sugarcane juice, Brahmalok will be reached - Brahmanical Scriptures - Science Journey
42 - দুধ, মদ, আখের রসের নদী পেরিয়ে ব্রহ্মলোক পাওয়া যাবে। ব্রাহ্মণ্য ধর্মগ্রন্থ | বিজ্ঞান যাত্রা
Title: 42 | दूध,दारू,गन्ने रस की दरिया पार कर मिलेगा ब्रह्मलोक | Brahmanical Scriptures | Science Journey
Author: Untold History
Link: https://youtu.be/Y73PXyWQ8RI






Liv107 - Raman with anyone in Brahmin scriptures - Phat. issai newton
Liv107 - ব্রাহ্মণ শাস্ত্রে কারও সাথে রমন - ফাট। ইসাই নিউটন
Title: LIVE107 | Brahman Dharm Granthon Me Kisi Ke Bhi Sath Raman | Ft. Issac Newton
Author: The Realist Azad
Link: https://youtu.be/hrzu3eRcWaU

LIVE72 - Why did Brahmins associate Kalpana Chanakya with the Maurya royal family?
Liv 72 - ব্রাহ্মণরা কেন কাল্পনিক চাণক্যকে মৌর্য রাজবংশের সাথে যুক্ত করেছিল?
Title: LIVE72 | Kalpnik Chanakya Ko Brahmano Ne Maurya Rajgharane Se Kyo Joda?
Author: The Realist Azad
Link: https://youtu.be/sx40y9JKQY4

209 - Untold History of Emperor Harshvardhan - Samrat Harshvardhan, 7th century AD - Science Journey
Title: 209 | Untold History of सम्राट हर्षवर्धन | Samrat Harshvardhan, 7th century AD | Science Journey
Author: Science Journey
Link: https://youtu.be/zFMCxGtlcyI


Product Showcase Liquid UI Voice User Interface to Perform SAP tasks1
https://youtu.be/FwsISelITKY


-