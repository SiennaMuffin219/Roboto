<?php
header("Content-Type: application/json;charset=utf-8");

if (isset($_GET["speedL"], $_GET["speedR"], $_GET["orientation"]))
{
	$FromAndroid = fopen("FromRobotToAndroid.json", "w+");

	$data = [
		"success" => true,
		"data" => [
			"speedL" => $_GET["speedL"],
			"speedR" => $_GET["speedR"],
			"orientation" => $_GET["orientation"]
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
	echo fread(fopen("FromAndroidToRobot.json", "r"), filesize("FromAndroidToRobot.json"));
}
else
{
	$data = ["success"=>false,"data"=>["error"=>"missing arguments","description"=>"excepted 3 arguments in URL: 'speedL', 'speedR' & 'orientation'"]];
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