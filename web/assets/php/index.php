
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- <meta http-equiv="refresh" content="0.5" >  -->
    <title>Roboto</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

    <h1>Roboto</h1>
    <h2>Commands</h2>
    <table>
        <tr>
            <th>Left Speed</th>
            <td>
                <?php $data = file("../data/speed.txt"); echo  $data[1]; ?>
            </td>
        </tr>

        <tr>
            <th>Right Speed</th>
            <td>
                <?php $data = file("../data/speed.txt"); echo  $data[2]; ?>
            </td>
        </tr>
    </table>

    <h2>Sensors</h2>
    <table>
        <tr>
            <th>Contact</th>
            <td></td>
        </tr>

        <tr>
            <th></th>
            <td></td>
        </tr>

        <tr>
            <th></th>
            <td></td>
        </tr>

        <tr>
            <th></th>
            <td></td>
        </tr>
            </table>

        </body>
        </html>
