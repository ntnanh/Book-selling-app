<?php 
$conn = mysqli_connect("localhost","root","","bansach");
$query ="SELECT * FROM theloai";
$data = mysqli_query($conn, $query);
$mangtheloai = array();
while ($row = mysqli_fetch_assoc($data)) {
	array_push($mangtheloai, new Theloai(
	$row['id'],
	$row['tentheloai'],
    $row['hinhanhtheloai']));
}
echo json_encode($mangtheloai);

class Theloai{
	function Theloai($id,$tentheloai,$hinhanhtheloai){
		$this->id = $id;
		$this->tentheloai = $tentheloai;
		$this->hinhanhtheloai = $hinhanhtheloai;

	}
}
?>