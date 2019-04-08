<?php
	require 'connect.php';

	if(isset($_GET['iddesk'])){
		$a = $_GET['iddesk'];
		$query = "SELECT tbl_sanpham.ten,tbl_sanpham.gia,tbl_chitiethoadon.soluong,tbl_hoadon.trangthai,tbl_hoadon.iddesk,tbl_hoadon.iduser,tbl_chitiethoadon.idsanpham FROM tbl_sanpham,tbl_hoadon,tbl_chitiethoadon 
				WHERE tbl_chitiethoadon.idsanpham = tbl_sanpham.id 
				AND tbl_hoadon.id = tbl_chitiethoadon.idhoadon AND tbl_hoadon.trangthai=0 AND tbl_hoadon.iddesk=".$a;
			

	
	$data = mysqli_query($connect,$query);


	$mang_sp = array();
	$i=0;
	
	

	while($row =mysqli_fetch_assoc($data)){
		$mang_sp[$i++] = $row;
		
	
	}
			
	echo json_encode($mang_sp);
	}	
?>