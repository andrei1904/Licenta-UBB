<?php

$server = "localhost";
$username = "root";
$password = "";

$conn = new mysqli($server, $username, $password);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "USE labajax";

if ($conn->query($sql) !== TRUE) {
    die("Database failed");
}

if ($_GET["type"] == 0) {
    $sql = "SELECT DISTINCT fromStation FROM stations";
    $result = $conn->query($sql);
    $fromStations = "";

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $fromStations .= $row["fromStation"] . ',';
        }
    } else {
        echo "0 rows";
    }

    $routes = $fromStations;

} else {
    $toStations = "";
    $station = $_GET["station"];

    $sql = "SELECT DISTINCT toStation FROM stations WHERE fromStation = '" . $station . "'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $toStations .= $row["toStation"] . ',';
        }
    } else {
        echo "0 rows";
    }

    $routes = $toStations;
}

echo $routes;
$conn->close();





