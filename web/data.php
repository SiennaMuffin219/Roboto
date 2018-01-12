<?php
header("Content-Type: application/json;charset=utf-8");

if (isset($_GET["speedL"], $_GET["speedR"]))
{
	$FromAndroid = fopen("FromAndroidToRobot.json", "w+");

	$data = [
		"success" => true,
		"data" => [
			"speedL" => $_GET["speedL"],
			"speedR" => $_GET["speedR"]
		]
	];
	$json = json_encode($data);
	if ($json === false) {
		$json = json_encode(array("jsonError", json_last_error_msg()));
		if ($json === false) {
			$json = '{"jsonError": "unknown"}';
		}
		http_response_code(500);
	}
	fwrite($FromAndroid, $json);
	echo fread(fopen("FromRobotToAndroid.json", "r"), filesize("FromRobotToAndroid.json"));
}
else
{
	$data = ["success"=>false,"data"=>["error"=>"missing arguments","description"=>"excepted 2 arguments in URL: 'speedL' & 'speedR'"]];
	$json = json_encode($data);
	if ($json === false) {
		$json = json_encode(array("jsonError", json_last_error_msg()));
		if ($json === false) {
			$json = '{"jsonError": "unknown"}';
		}
		http_response_code(500);
	}
	echo($json);
}

?>