<?php
$conn = mysqli_connect("localhost","root","","bansach");
$nguoinhan = $_POST['nguoinhan'];
$email = $_POST['email'];
$dienthoai = $_POST['dienthoai'];
// $tongtien = $_POST['tongtien'];
$diachi = $_POST['diachi'];
if(strlen($nguoinhan) > 0 && strlen($email) > 0 && strlen($dienthoai) > 0 && strlen($diachi) > 0){
    $query = "INSERT INTO `hoadon`(`iduser`, `nguoinhan`, `email`, `dienthoai`, `tongtien`, `ngay`, `diachi`, `ghichu`) 
    VALUES ('4','$nguoinhan','$email','$dienthoai','370','','diachi','')";
    if(mysqli_query($conn, $query)){
        $iddonhang = $conn->insert_id;
        echo $iddonhang;
    }else{
        echo "Thất bại";
    }
}else{
    echo "Bạn hãy kiểm tra lại các dữ liệu";
}

?>