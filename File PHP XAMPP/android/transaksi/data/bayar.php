<?php

if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $no_hp = $_POST['no_hp'];
	$kuota = $_POST['kuota'];

    require_once 'connect.php';

    $sql = "INSERT INTO data (no_hp, kuota) VALUES ('$no_hp', '$kuota')";

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