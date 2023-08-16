<?php 
$conn = mysqli_connect("localhost","root","","bansach");
$page = $_GET['page'];
$idbooks = $_POST['idtheloai'];
$space = 5;
$limit = ($page - 1) * $space;
$query ="SELECT * FROM books WHERE idtheloai = $idbooks LIMIT $limit,$space";
$data = mysqli_query($conn, $query);
$mangsanpham = array();
while ($row = mysqli_fetch_assoc($data)) {
	array_push($mangsanpham, new Sanpham(
	$row['id'],
	$row['tensach'],
	$row['gia'],
	$row['image'],
	$row['gioithieusp'],
    $row['idtheloai']));
}
echo json_encode($mangsanpham);

class Sanpham{
	function Sanpham($id,$tensach,$gia,$image,$gioithieusp,$idtheloai){
		$this->id = $id;
		$this->tensach = $tensach;
		$this->gia = $gia;
		$this->image = $image;
		$this->gioithieusp = $gioithieusp;
		$this->idtheloai = $idtheloai;

	}
}
?>