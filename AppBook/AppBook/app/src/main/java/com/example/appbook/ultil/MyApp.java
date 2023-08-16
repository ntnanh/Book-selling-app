package com.example.appbook.ultil;

import com.example.appbook.model.User;

public class MyApp {
    public static User user_current;
    public static final String BASE_URL = "http://192.168.1.43/MyApp/";
    public static String localhost = "192.168.1.43";
    public static String Duongdansanphammoinhat = "http://" + localhost + "/MyApp/getsanphammoinhat.php";
    public static String Duongdanloaisp ="http://"+localhost+"/MyApp/gettheloaisach.php";
    public static String Duongdansachbanchay = "http://" + localhost + "/Myapp/getsanpham.php?page=";
    public static String Duongdandonhang = "http://" + localhost + "/Myapp/donhang.php";
    public static String Duongdanchitietdonhang = "http://" + localhost + "/Myapp/chitietdonhang.php";

}
