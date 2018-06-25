<?php 
include('connexion.php')
?>

<?php 
//Modif vitesse base de donnée par android
$sql = 'UPDATE roboto SET LeftSpeed='.$LSA.', RightSpeed='.$RSA);
mysql_query($sql) or die('Erreur SQL !'.$sql.mysql_error());
?>

<?php 
//Recup vitesse pour roboto
$sql = 'SELECT LeftSpeed, RightSpeed FROM roboto';
$req = mysql_query($sql) or die('Erreur SQL !'.$sql.mysql_error());
$data = mysql_fetch_array($req);
$LSR = $data['LeftSpeed'];
$RSR = $data['RightSpeed'];
?>

<?php
//Modif autre donnée par roboto
$sql = 'UPDATE roboto SET Contact='.$contact.', Proximity='.$proxim.', Luminosity='.$lumin.', Gyroscope='.$gyro);
mysql_query($sql) or die('Erreur SQL !'.$sql.mysql_error());
?>

<?php 
include('deconnexion.php')
?>