<?php

if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $no_hp = $_POST['no_hp'];
	$denom = $_POST['denom'];

    require_once 'connect.php';

    $sql = "INSERT INTO pulsa (no_hp, denom) VALUES ('$no_hp', '$denom')";

    if (mysqli_query($conn, $sql)){
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysqli_close ($conn);
    }
    else {
        $result["success"] = "0";
        $result["message"] = "error";

        echo json_encode($result);
        mysqli_close($conn);
    }
}

?>