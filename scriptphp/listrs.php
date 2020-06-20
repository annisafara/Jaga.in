<?php 
    session_start();
	if(isset($_POST['cari'])){
		
		require_once "koneksi.php";
		$cari = $_SESSION['users.kota'];

		$query = "SELECT nama_rumahsakit,almt_rumahsakit,tlpn_rumahsakit FROM rumahsakit JOIN users ON kota_rumahsakit=kota WHERE kota_rumahsakit='$cari' GROUP BY nama_rumahsakit,almt_rumahsakit,tlpn_rumahsakit;";
		$data = array();
		
		$sql = mysqli_query($konek,$query);
		
	
	
	if( $sql = mysqli_query($konek, $query) ){
		while($row = mysqli_fetch_array($sql)){
		array_push($data, array(
		    "success"=> 1,
			"hasil" => "found",
			"nama_rumahsakit" => $row['nama_rumahsakit'],
			"almt_rumahsakit" => $row['almt_rumahsakit'],
			"tlpn_rumahsakit" => $row['tlpn_rumahsakit'],
		));
	} 

		echo json_encode($data);
		
	} else {
		
		$result = array("success"=> 0,"message"=> "error entri");
		echo json_encode($result);

	}
}
	?>