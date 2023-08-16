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
	$row['tacgia'],
	$row['idtheloai'],
	$row['gia'],
    $row['image'],
	$row['congtyphathanh'],$row['ngaysx'],$row['kichthuoc'],
	$row['dichgia'],$row['loaibia'],$row['sotrang'],$row['nsx'],$row['gioithieusp']));
}
echo json_encode($mangsanpham);

class Sanpham{
	function Sanpham($id,$tensach,$tacgia,$idtheloai,$gia,$image,$congtyphathanh,$ngaysx,$kichthuoc,$dichgia,$loaibia,$sotrang,$nsx,$gioithieusp){
		$this->id = $id;
		$this->tensach = $tensach;
		$this->tacgia = $tacgia;
		$this->idtheloai = $idtheloai;
		$this->gia = $gia;
		$this->image = $image;
		$this->congtyphathanh = $congtyphathanh;
		$this->ngaysx = $ngaysx;
		$this->kichthuoc = $kichthuoc;
		$this->dichgia = $dichgia;
		$this->sotrang = $sotrang;
		$this->nsx = $nsx;
		$this->gioithieusp = $gioithieusp;

	}
}
?>