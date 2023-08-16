<?php 
$conn = mysqli_connect("localhost","root","","bansach");
$query ="SELECT * FROM books ORDER BY ID LIMIT 6";
$data = mysqli_query($conn, $query);
$mangspmoinhat = array();
while ($row = mysqli_fetch_assoc($data)) {
	array_push($mangspmoinhat, new Sanphammoinhat(
	$row['id'],
	$row['tensach'],
	$row['gia'],
	$row['image'],
	$row['gioithieusp'],
    $row['idtheloai']));
}
echo json_encode($mangspmoinhat);

class Sanphammoinhat{
	function Sanphammoinhat($id,$tensach,$gia,$image,$gioithieusp,$idtheloai){
		$this->id = $id;
		$this->tensach = $tensach;
		$this->gia = $gia;
		$this->image = $image;
		$this->gioithieusp = $gioithieusp;
		$this->idtheloai = $idtheloai;

	}
}
?>