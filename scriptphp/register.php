<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	$email = $_POST["email"];
	$password = $_POST["password"];
	$nama = $_POST["nama"];
	$kota = null;
	$tgllhr = null;
	$alamat = null;
	$jk = null;
	$goldar = null;

	$password = password_hash($password, PASSWORD_DEFAULT);

	require_once "koneksi.php";

	$query = "INSERT INTO users (dt_email, dt_nama, dt_password) VALUES ('$email', '$nama', '$password');";
    $cek ="SELECT dt_email FROM users WHERE dt_email='$email'";
	$cekem = mysqli_query($konek, $cek) or die(mysql_error());
	$reddun = mysqli_num_rows($cekem);

	if( $reddun < 1 ){
		mysqli_query($konek, $query);
		$result = array("success"=> 1,"message"=> "berhasil" );

		echo json_encode($result);
		
	} else {
		
		$result = array("success"=> 040,"message"=> "email telah digunakan");
		echo json_encode($result);

	}
	} else{

		$result = array( "success"=> 0,"message"=> "error post");
		echo json_encode($result);
	}
?>