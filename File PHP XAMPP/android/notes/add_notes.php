<?php 

require_once 'connect.php';

$key = $_POST['key'];

$name       = $_POST['name'];
$deskripsi  = $_POST['deskripsi'];
$date       = $_POST['date'];
$picture    = $_POST['picture'];

if ( $key == "insert" ){

    $date_newformat = date('Y-m-d', strtotime($date));

    $query = "INSERT INTO notes (name,deskripsi,date)
    VALUES ('$name', '$deskripsi', '$date_newformat') ";

        if ( mysqli_query($conn, $query) ){

            if ($picture == null) {

                $finalPath = "/android/notes/notes_picture/logo.png"; 
                $result["value"] = "1";
                $result["message"] = "Success";
    
                echo json_encode($result);
                mysqli_close($conn);

            } else {

                $id = mysqli_insert_id($conn);
                $path = "notes/notes_picture/$id.jpeg";
                $finalPath = "/android/".$path;

                $insert_picture = "UPDATE notes SET picture='$finalPath' WHERE id='$id' ";
            
                if (mysqli_query($conn, $insert_picture)) {
            
                    if ( file_put_contents( $path, base64_decode($picture) ) ) {
                        
                        $result["value"] = "1";
                        $result["message"] = "Success!";
            
                        echo json_encode($result);
                        mysqli_close($conn);
            
                    } else {
                        
                        $response["value"] = "0";
                        $response["message"] = "Error! ".mysqli_error($conn);
                        echo json_encode($response);

                        mysqli_close($conn);
                    }

                }
            }

        } 
        else {
            $response["value"] = "0";
            $response["message"] = "Error! ".mysqli_error($conn);
            echo json_encode($response);

            mysqli_close($conn);
        }
}

?>