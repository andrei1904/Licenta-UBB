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

$sql = "SELECT * FROM persons";
$result = $conn->query($sql);
$persons = "";

if($result->num_rows > 0){
    while($row = $result->fetch_assoc()){
        $persons .= $row["nume"] . "," . $row["prenume"]
            . "," . $row["telefon"] . "," . $row["email"] .";";
    }
}

echo $persons;
