package com.example.appbook.model;

import java.io.Serializable;

public class Books implements Serializable {
    public int ID;
    public String Tensach;
    public Integer gia;
    public String Image;
    public String Gioithieusp;
    public int IDtheloai;

    public Books(int ID, String tensach, Integer gia, String image, String gioithieusp, int IDtheloai) {
        this.ID = ID;
        Tensach = tensach;
        this.gia = gia;
        Image = image;
        Gioithieusp = gioithieusp;
        this.IDtheloai = IDtheloai;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTensach() {
        return Tensach;
    }

    public void setTensach(String tensach) {
        Tensach = tensach;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getGioithieusp() {
        return Gioithieusp;
    }

    public void setGioithieusp(String gioithieusp) {
        Gioithieusp = gioithieusp;
    }

    public int getIDtheloai() {
        return IDtheloai;
    }

    public void setIDtheloai(int IDtheloai) {
        this.IDtheloai = IDtheloai;
    }
}
