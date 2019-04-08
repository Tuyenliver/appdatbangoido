<?php
	require 'connect.php';

	$data_hoadon = $_GET['user'];
// // 	$data_hoadon = '[{"ten":"Do uong","id":0,"iddesk":6,"idsanpham":1,"iduser":1,"soluong":4,"gia":2,"trangthai":0},{"ten":"do an","id":0,"iddesk":6,"idsanpham":2,"iduser":1,"soluong":4,"gia":1,"trangthai":0}]
// // ';[{"pass":"admin","tdn":"admin"}]
	// $data_hoadon='[{"pass":"admin","tdn":"admin"}]';
	$hihi=json_decode($data_hoadon,true);
	$tdn = $hihi[0]['tdn'];
	$mk = $hihi[0]['pass'];
	// $tdn = "admin";
	// $mk = "admin";
	$query = "SELECT * FROM tbl_user WHERE tendangnhap='".$tdn."' AND pass ='".$mk."'";

	$data = mysqli_query($connect,$query);


	$mang_sp = array();
	$i=0;
	
	

	while($row =mysqli_fetch_assoc($data)){
		$mang_sp[$i++] = $row;
	}
			
	echo json_encode($mang_sp);

?>