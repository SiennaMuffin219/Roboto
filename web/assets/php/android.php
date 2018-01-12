<?php
$FromAndroid = fopen("../data/speed.txt", "w+");

if (isset($_GET["speedL"], $_GET["speedR"]))
{
	fwrite($FromAndroid, "\n");
	fwrite($FromAndroid, $_GET["speedL"]);
	fwrite($FromAndroid, "\n");
	fwrite($FromAndroid, $_GET["speedR"]);
}
?>