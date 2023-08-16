package com.example.appbook.model;

import java.io.Serializable;

public class Book implements Serializable {
    public int ID;
    public String Tensach;
    public String Tacgia;
    public Integer IDtheloai;
    public int Gia;
    public String Image;
    public String Congtyphathanh;
    public String ngaysx;
    public String Kichthuoc;
    public String Dichgia;
    public String loaibia;
    public String nsx;
    public String Gioithieusp;
    public int Sotrang;

    public Book(int ID, String tensach, String tacgia, Integer IDtheloai,
                int gia, String image, String congtyphathanh, String ngaysx, String kichthuoc,
                String dichgia, String loaibia, String nsx, String gioithieusp, int sotrang) {
        this.ID = ID;
        Tensach = tensach;
        Tacgia = tacgia;
        this.IDtheloai = IDtheloai;
        Gia = gia;
        Image = image;
        Congtyphathanh = congtyphathanh;
        this.ngaysx = ngaysx;
        Kichthuoc = kichthuoc;
        Dichgia = dichgia;
        this.loaibia = loaibia;
        this.nsx = nsx;
        Gioithieusp = gioithieusp;
        Sotrang = sotrang;
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

    public String getTacgia() {
        return Tacgia;
    }

    public void setTacgia(String tacgia) {
        Tacgia = tacgia;
    }

    public Integer getIDtheloai() {
        return IDtheloai;
    }

    public void setIDtheloai(Integer IDtheloai) {
        this.IDtheloai = IDtheloai;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCongtyphathanh() {
        return Congtyphathanh;
    }

    public void setCongtyphathanh(String congtyphathanh) {
        Congtyphathanh = congtyphathanh;
    }

    public String getNgaysx() {
        return ngaysx;
    }

    public void setNgaysx(String ngaysx) {
        this.ngaysx = ngaysx;
    }

    public String getKichthuoc() {
        return Kichthuoc;
    }

    public void setKichthuoc(String kichthuoc) {
        Kichthuoc = kichthuoc;
    }

    public String getDichgia() {
        return Dichgia;
    }

    public void setDichgia(String dichgia) {
        Dichgia = dichgia;
    }

    public String getLoaibia() {
        return loaibia;
    }

    public void setLoaibia(String loaibia) {
        this.loaibia = loaibia;
    }

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }

    public String getGioithieusp() {
        return Gioithieusp;
    }

    public void setGioithieusp(String gioithieusp) {
        Gioithieusp = gioithieusp;
    }

    public int getSotrang() {
        return Sotrang;
    }

    public void setSotrang(int sotrang) {
        Sotrang = sotrang;
    }
}
