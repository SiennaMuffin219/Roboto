<?php
$FromAndroid = fopen("../data/speed.txt", "w+");

if (isset($_GET["speedL"], $_GET["speedR"]))
{
	fwrite($FromAndroid, $_GET["speedL"]);
	fwrite($FromAndroid, " ");
	fwrite($FromAndroid, $_GET["speedR"]);
}
?>
