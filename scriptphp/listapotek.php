<?php 
    session_start();
	if(isset($_POST['cari'])){
		
		require_once "koneksi.php";
		$cari = $_SESSION['users.kota'];
		$query = "SELECT nama_apotek,almt_apotek,tlpn_apotek FROM apotek JOIN users ON apotek_kota=kota WHERE apotek_kota='$cari' GROUP BY nama_apotek,almt_apotek,tlpn_apotek;";
		$data = array();
		
		$sql = mysqli_query($konek,$query);
		
	
	
	if( $sql = mysqli_query($konek, $query) ){
		while($row = mysqli_fetch_array($sql)){
		array_push($data, array(
		    "success"=> 1,
			"hasil" => "found",
			"nama_apotek" => $row['nama_apotek'],
			"almt_apotek" => $row['almt_apotek'],
			"tlpn_apotek" => $row['tlpn_apotek'],
		));
	} 

		echo json_encode($data);
		
	} else {
		
		$result = array("success"=> 0,"message"=> "error entri");
		echo json_encode($result);

	}
}
	?>