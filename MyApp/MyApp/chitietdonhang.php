<?php 
$conn = mysqli_connect("localhost","root","","bansach");
$json = $_POST['json'];
$data = json_decode($json,true);
foreach ($data as $value) {
	$idhoadon = $value ['idhoadon'];
	$idsanpham = $value['idbooks'];
	$soluong = $value['soluong'];
	$giasanpham = $value['gia'];
	$query ="INSERT INTO `chitiethd`( `idHD`, `idbooks`, `soluong`, `gia`, `ngay`, `tinhtrangdonhang`)
	 VALUES ('$idhoadon','$idsanpham','$soluong','$giasanpham','','Đang chờ xử lí')";
	$Dta = mysqli_query($conn,$query);
}
if ($Dta) {
	echo "1";
}else{
	echo "0";
}
?>