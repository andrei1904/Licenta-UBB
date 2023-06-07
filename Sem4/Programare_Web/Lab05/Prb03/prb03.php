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

$type = $_GET["type"];

if ($type == 1) {
    $sql = "SELECT id FROM shoes";
    $result = $conn->query($sql);

    $ids = "";
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $ids .= $row["id"] . ",";
        }
    }

    echo $ids;
}

if ($type == 2) {
    $id = $_GET["id"];

    $sql = "SELECT type, color, size FROM shoes WHERE id = '" . $id . "'";
    $result = $conn->query($sql);

    $shoe = "";
    if ($result->num_rows > 0) {
        if ($row = $result->fetch_assoc()) {
            $shoe = $row["type"] . "," . $row["color"] . "," . $row["size"];
        }
    }

    echo $shoe;
}

if ($type == 3) {
    $id = $_GET["id"];
    $stype = $_GET["stype"];
    $color = $_GET["color"];
    $size = $_GET["size"];

    $sql = "UPDATE shoes SET type = '". $stype ."', color = '". $color ."', size = 
            '". $size ."' WHERE id = '". $id ."'";
    $result = $conn->query($sql);
}

$conn->close();
