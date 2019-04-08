<?php
	require_once 'connect.php';
$data_hoadon = $_GET['data'];
// 	$data_hoadon = '[{"ten":"Do uong","id":0,"iddesk":6,"idsanpham":1,"iduser":1,"soluong":4,"gia":2,"trangthai":0},{"ten":"do an","id":0,"iddesk":6,"idsanpham":2,"iduser":1,"soluong":4,"gia":1,"trangthai":0}]
// ';
	$hihi=json_decode($data_hoadon,true);
	$i = count($hihi);
	$date = date("Y/m/d");
	$dem=0;
	// $id_hoadon=-1;
	$inser_user = mysqli_query($connect,"INSERT INTO tbl_user(id, tendangnhap, pass, hoten, level) VALUES ('NULL','".$hihi[0]['tendangnhap']."','".$hihi[0]['pass']."','".$hihi[0]['hoten']."','2')");
	if ($inser_user) {
		echo '[{"thanhcong":"sussec"}]';
	}

?>