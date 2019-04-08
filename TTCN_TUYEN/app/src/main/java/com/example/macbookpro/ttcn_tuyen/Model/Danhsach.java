package com.example.macbookpro.ttcn_tuyen.Model;

public class Danhsach {
    private String id ;
    private String ten ;
    private String sl ;
    private String loai ;
    private String img ;
    private String gia ;


    public Danhsach(String id, String ten, String sl, String loai, String img, String gia) {
        this.id = id;
        this.ten = ten;
        this.sl = sl;
        this.loai = loai;
        this.img = img;
        this.gia = gia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
