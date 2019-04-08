<?php
	require 'connect.php';

	if(isset($_GET['loaisp'])){
		$a = $_GET['loaisp'];
		$query = "SELECT * FROM tbl_sanpham WHERE loai='$a' AND sl!=0";
	}elseif (isset($_GET['search'])) {
		$a = $_GET['search'];
		$query = "SELECT * FROM tbl_sanpham WHERE id='$a'";
	}else {
		// $query = "SELECT * FROM tbl_sp WHERE sl_sp!=0";
		$query = "SELECT * FROM tbl_sanpham";
	}
	
	$data = mysqli_query($connect,$query);


	$mang_sp = array();
	$i=0;
	
	

	while($row =mysqli_fetch_assoc($data)){
		$mang_sp[$i++] = $row;
		
	
	}
			
	echo json_encode($mang_sp);
	
?>