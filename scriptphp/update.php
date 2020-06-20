<?php
	session_start();
	$user = $_SESSION['users.id'];
	
if($_SERVER['REQUEST_METHOD'] == 'POST'){
	require_once "koneksi.php";
	$id = $_POST["id"];
	$nama = $_POST["nama"];
	$kota = $_POST["kota"];
	$tgllhr = $_POST["tanggallahir"];
	$alamat = $_POST["alamat"];
	$jenkel = $_POST["jeniskelamin"];
	$goldar = $_POST["golongandarah"];


	$query = "UPDATE users SET dt_nama = '$nama', kota = '$kota', tgl_lahir = '$tgllhr', alamat = '$alamat', dt_jk = '$jenkel', gol_darah = '$goldar'  WHERE dt_id = '$id';";
	$quer2 = "SELECT * FROM users WHERE dt_id = '$id';";
	$sql = mysqli_query($konek, $query);
	$ambil = mysqli_query($konek, $quer2);
	if(mysqli_query($konek, $query)){
	 
	    while ($row = mysqli_fetch_assoc($ambil)){
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
    	}
    	echo json_encode($result);

    }else{

	    	$result = array( "success"=> 0,"message"=> "error update");
	    	echo json_encode($result);
	    }   
	}
?>