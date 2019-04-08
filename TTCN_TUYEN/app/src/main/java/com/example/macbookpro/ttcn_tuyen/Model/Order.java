package com.example.macbookpro.ttcn_tuyen.Model;

public class Order {
    private String ten;
    private int id,gia,soluong;
    int trangthai;
    int iddesk;
    int iduser;

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    int idsanpham;



    public int getIddesk() {
        return iddesk;
    }

    public void setIddesk(int iddesk) {
        this.iddesk = iddesk;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }


    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public Order(String ten, int gia, int soluong, int trangthai, int iddesk, int iduser, int idsanpham) {
        this.ten = ten;
        this.gia = gia;
        this.soluong = soluong;
        this.trangthai = trangthai;
        this.iddesk = iddesk;
        this.iduser = iduser;
        this.idsanpham = idsanpham;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
