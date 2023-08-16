package com.example.appbook.model;


public class Theloai {
    public  int Id;
    public String Tentheloai;
    public String Hinhanhtheloai;

    public Theloai(int id, String tentheloai, String hinhanhtheloai) {
        Id = id;
        Tentheloai = tentheloai;
        Hinhanhtheloai = hinhanhtheloai;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTentheloai() {
        return Tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        Tentheloai = tentheloai;
    }

    public String getHinhanhtheloai() {
        return Hinhanhtheloai;
    }

    public void setHinhanhtheloai(String hinhanhtheloai) {
        Hinhanhtheloai = hinhanhtheloai;
    }
}
