<?php
	require 'connect.php';
	$query = "SELECT * FROM tbl_desk";

	$data = mysqli_query($connect,$query);


	$mang_sp = array();
	$i=0;
	
	

	while($row =mysqli_fetch_assoc($data)){
		$mang_sp[$i++] = $row;
	}
			
	echo json_encode($mang_sp);

?>