
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Roboto</title>
</head>
<body>
    <h1>Roboto</h1>
    <p>
        <?php
        $dataFile = fopen('../data/speed.txt', 'r+');
        $dataString = fgets($dataFile);
        echo 'Le text : '. $dataString;
        ?>

    </p>
</body>
</html>
