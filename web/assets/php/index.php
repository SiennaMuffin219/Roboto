
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Roboto</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <h1>Roboto</h1>

        <table>
        <tr>
            <th>Vitesse gauche : </th>
            <td>
                <?php
                $data = file("../data/speed.txt");
                echo $data[1];
                ?>
            </td>
        </tr>

        <tr>
            <th>Vitesse droite : </th>
            <td>
                <?php
                $data = file("../data/speed.txt");
                echo  $data[2];
                ?>
            </td>
        </tr>


        </table>


</body>
</html>
