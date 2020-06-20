<?php
	session_start();
	$user = $_SESSION['users.id'];
	$query = "SELECT * FROM users WHERE dt_id='$user'";
	$data = array();
	require_once "koneksi.php";

	if( $sql = mysqli_query($konek, $query) ){
		while ($row = mysqli_fetch_array($sql)){
			array_push($data, array(
				"id" => $row['dt_id'],
				"email" => $row['dt_email'],
				"password" => $row["dt_password"],
				"nama" => $row["dt_nama"],
				"kota" => $row["kota"],
				"tgllhr" => $row["tgl_lahir"],
				"alamat" => $row["alamat"],
				"jk" => $row["dt_jk"],
				"goldar" => $row["gol_darah"]
			));
		}


		echo json_encode($data);
		
	} else {
		
		$result = array("success"=> 0,"message"=> "error entri");
		echo json_encode($result);

	}
?>