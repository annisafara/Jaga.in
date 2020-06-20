<?php 
    session_start();
	if(isset($_POST['cari'])){
		
		require_once "koneksi.php";
		$cari = $_SESSION['users.kota'];

		$query = "SELECT nama_rumahsakit,plat_ambulance,tlpn_ambulance FROM rumahsakit JOIN ambulance ON id_ambulance=rs_ambulance JOIN users ON kota_rumahsakit=kota WHERE statustersedia=1 and kota_rumahsakit='$cari' GROUP BY nama_rumahsakit,plat_ambulance,tlpn_ambulance;";
		$data = array();
		
		$sql = mysqli_query($konek,$query);
		
	
	
	if( $sql = mysqli_query($konek, $query) ){
		while($row = mysqli_fetch_array($sql)){
		array_push($data, array(
		    "success"=> 1,
			"hasil" => "found",
			"nama_rumahsakit" => $row['nama_rumahsakit'],
			"plat_ambulance" => $row['plat_ambulance'],
			"tlpn_ambulance" => $row['tlpn_ambulance'],
			"<br>",
		));
	} 

		echo json_encode($data);
		
	} else {
		
		$result = array("success"=> 0,"message"=> "error entri");
		echo json_encode($result);

	}
}
	?>