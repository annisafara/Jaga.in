<?php 
    session_start();
	if(isset($_POST['cari'])){
		
		require_once "koneksi.php";
		$cari = $_SESSION['users.kota'];

		$query = "SELECT nama_puskesmas,almt_puskesmas,tlpn_puskesmas FROM puskesmas JOIN users ON kota_puskesmas=kota WHERE kota_puskesmas='$cari' GROUP BY nama_puskesmas,almt_puskesmas,tlpn_puskesmas;";
		$data = array();
		
		$sql = mysqli_query($konek,$query);
		
	
	
	if( $sql = mysqli_query($konek, $query) ){
		while($row = mysqli_fetch_array($sql)){
		array_push($data, array(
			"nama_puskesmas" => $row['nama_puskesmas'],
			"almt_puskesmas" => $row['almt_puskesmas'],
			"tlpn_puskesmas" => $row['tlpn_puskesmas'],
			"<br>"
		));
	} 

		echo json_encode($data);
		
	} else {
		
		$result = array("success"=> 0,"message"=> "error entri");
		echo json_encode($result);

	}
}
	?>