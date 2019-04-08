<?php
	require_once 'connect.php';
	$data = $_GET['id'];
	$update_desk =mysqli_query($connect,"UPDATE tbl_desk SET trangthai='0' WHERE id=".$data);
	$update_desk =mysqli_query($connect,"UPDATE tbl_hoadon SET trangthai='1' WHERE iddesk=".$data);

	if ($update_desk) {
				echo '[{"thanhcong":"sussec"}]';

	}
?>