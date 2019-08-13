<?php

if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $games = $_POST['games'];
	$voucher = $_POST['voucher'];

    require_once 'connect.php';

    $sql = "INSERT INTO games (games, voucher) VALUES ('$games', '$voucher')";

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