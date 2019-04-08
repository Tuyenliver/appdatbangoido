package com.example.macbookpro.ttcn_tuyen.Model;

public class TableList {
    private int id,soban,soghe;

    private int trangthai;

    public TableList(int id, int soban, int soghe, int trangthai) {
        this.id = id;
        this.soban = soban;
        this.soghe = soghe;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoban() {
        return soban;
    }

    public void setSoban(int soban) {
        this.soban = soban;
    }

    public int getSoghe() {
        return soghe;
    }

    public void setSoghe(int soghe) {
        this.soghe = soghe;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
