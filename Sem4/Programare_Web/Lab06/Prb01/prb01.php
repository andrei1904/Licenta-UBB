<?php

echo '<!DOCTYPE html>
<html lang=\"en\">
<head>
    <meta charset=\"UTF-8\">
    <title>Title</title>

    <style>
        table, th, tr, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>';


$from = $to = '';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $from = getInput($_POST['from']);
    $to = getInput($_POST['to']);
}

function getInput($data)
{
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}

getRoutes($from, $to);

echo '</body>
</html>';

function getNoStop($conn, $from, $to)
{
    $stmt = $conn->prepare('SELECT * FROM trains WHERE departure_station = ? AND arrival_station = ?');
    $stmt->bind_param('ss', $from, $to);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        echo "<h3> Direct trains from $from to $to : </h3>";

        $table = "<table> <tr><th>Train Number</th> <th>Train Type</th> <th> Start</th> 
            <th>Destination</th> <th>Departure Hour</th> <th>Arriving Hour</th> </tr>";

        while ($row = $result->fetch_assoc()) {
            $table .= "<tr> <td>" . $row["id"] . "</td>" . "<td>" . $row["type"] . " </td>"
                . "<td>" . $row["departure_station"] . " </td>" . "<td>" . $row["arrival_station"] . " </td>"
                . "<td>" . $row["departure_time"] . " </td>" . "<td>" . $row["arrival_time"] . " </td></tr>";
        }
        $table .= "</table>";

        echo $table;

        return 1;
    }
    return 0;
}

function getWithStop($conn, $from, $to)
{
    $q = $conn->prepare('SELECT T1.type as type1, T1.id as id1, T1.departure_station, T1.arrival_station, T1.departure_time, T1.arrival_time, T2.id as id2, T2.type as type2, T2.arrival_station as station1, T2.departure_time as second_departure_time, T2.arrival_time as final_arrival_time FROM trains T1 INNER JOIN trains T2 ON T1.arrival_station = t2.departure_station WHERE t1.departure_station = ? AND t2.arrival_station = ? AND T1.arrival_time < T2.departure_time');
    $q->bind_param('ss', $from, $to);
    $q->execute();
    $result = $q->get_result();

    if ($result->num_rows > 0) {

        echo "<h3> Trains with one stop from $from to $to : </h3>";

        $table = "<table> <tr><th>Train Number</th> <th>First Train Type</th> <th>Second Train Type</th> <th> Start</th> <th>Stop</th>
            <th>Destination</th> <th>Departure Hour</th> <th>Stop Arriving Hour</th> <th>Stop Departure Hour</th>
            <th>Destination Arriving Hour</th> </tr>";

        while ($row = $result->fetch_assoc()) {
            $table .= "<tr> <td>" . $row["id1"] . " and " . $row["id2"]. "</td>" . "<td>" . $row["type1"] . " </td>"
                . "<td>" . $row["type2"] . " </td>"
                . "<td>" . $row["departure_station"] . " </td>" . "<td>" . $row["arrival_station"] . "</td>"
                . "<td>" . $row["station1"] . " </td>" . "<td>" . $row["departure_time"] . " </td>"
                . "<td>" . $row["arrival_time"] . " </td> " . "<td>" . $row["second_departure_time"] . "</td>"
                . "<td>" . $row["final_arrival_time"] . "</td>" . "</tr>";
        }
        $table .= "</table>";

        echo $table;

        return 1;
    }

    return 0;
}

function getRoutes($from, $to)
{
    $server = "localhost";
    $username = "root";
    $password = "";

    $conn = new mysqli($server, $username, $password);

    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    $sql = "USE labphp";

    if ($conn->query($sql) !== TRUE) {
        die("Database failed");
    }

    if (getNoStop($conn, $from, $to) == 0) {
        if (isset($_POST['allowTrainChange'])) {
            if (getWithStop($conn, $from, $to) == 0) {
                echo 'There are no trains!';
            }
        } else {
            echo 'There are no trains!';
        }
    } else {
        if (isset($_POST['allowTrainChange'])) {
            if (getWithStop($conn, $from, $to) == 0) {
                echo 'There are no trains!';
            }
        }
    }

}