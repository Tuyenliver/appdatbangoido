<?php
require 'connect.php';
	$data=mysqli_query($connect,"SELECT Max(id) as id FROM tbl_hoadon");
	$mang=[];
	while ($row=mysqli_fetch_assoc($data)) {
	 	$mang[]=$row;
	 } 
	 	$id_hoadon= $mang[0]['id']+1;
	 	 


	$data_hoadon = $_GET['data'];
// 	$data_hoadon = '[{"ten":"Do uong","id":0,"iddesk":6,"idsanpham":1,"iduser":1,"soluong":4,"gia":2,"trangthai":0},{"ten":"do an","id":0,"iddesk":6,"idsanpham":2,"iduser":1,"soluong":4,"gia":1,"trangthai":0}]
// ';
	$hihi=json_decode($data_hoadon,true);
	$i = count($hihi);
	$date = date("Y/m/d");
	$dem=0;
	// $id_hoadon=-1;
	$inser_hoadon = mysqli_query($connect,"INSERT INTO tbl_hoadon(id, iduser, datehoadon, iddesk, tongtien, trangthai) VALUES ('NULL','".$hihi[0]['iduser']."','".$date."','".$hihi[0]['iddesk']."','".$hihi[0]['gia']."','".$hihi[0]['trangthai']."')");

	$update_desk =mysqli_query($connect,"UPDATE tbl_desk SET trangthai='1' WHERE id=".$hihi[0]['iddesk']);

	if($inser_hoadon){
		// GLOBAL $id_hoadon;
		foreach ($hihi as $key) {
			$inser =mysqli_query($connect,"INSERT INTO tbl_chitiethoadon(id, idhoadon, idsanpham, soluong, thanhtien) VALUES ('NULL','".$id_hoadon."','".$key['idsanpham']."','".$key['soluong']."','".$key['soluong']*$key['gia']."')");
			if ($inser) {
				$dem++;
			}
			
		}
	}


	

	if ($dem==$i) {
		echo '[{"thanhcong":"sussec"}]';
	}



?>