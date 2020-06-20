<?php
session_start();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	require_once "koneksi.php";
	$email = $_POST["email"];
	$password = $_POST["password"];

	//$password = password_hash($password, PASSWORD_DEFAULT);

	$query = "SELECT * FROM users WHERE dt_email = '$email';";
	$sql = mysqli_query($konek, $query);
	

	if( mysqli_num_rows($sql) > 0 ){
		while ($row = mysqli_fetch_assoc($sql)){
			
			if(password_verify($password, $row["dt_password"])){
				$result = array(
					"success" => 1,
					"message" => "berhasil",
					"id" => $row['dt_id'],
					"email" => $row['dt_email'],
					"nama" => $row["dt_nama"],
					"kota" => $row["kota"],
					"tgllhr" => $row["tgl_lahir"],
					"alamat" => $row["alamat"],
					"jk" => $row["dt_jk"],
					"goldar" => $row["gol_darah"]
				);
					//untuk test yang di web
					    $_SESSION['users.id'] = $row['dt_id'];
					    $_SESSION['users.email'] = $row['dt_email'];
                        $_SESSION['users.nama'] = $row["dt_nama"];
                        $_SESSION['users.kota'] =  $row["kota"];
                        $_SESSION['users.tgllhr'] = $row['tgl_lahir'];
                        $_SESSION['users.alamat'] = $row['alamat'];
                        $_SESSION['users.jk'] = $row['dt_jk'];
                        $_SESSION['users.goldar'] = $row['gol_darah'];
			}
			else{
				$result = array("success"=> 2,"message"=> "salah password");
			}
		}

		echo json_encode($result);
		
	} else {
		
		$result = array("success"=> 404,"message"=> "email gk ada");
		echo json_encode($result);
	
	} 
	}else{

		$result = array( "success"=> 0,"message"=> "error post");
		echo json_encode($result);
	}
?>