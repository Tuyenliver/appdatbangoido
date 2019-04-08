package com.example.macbookpro.ttcn_tuyen.Model;

public class User {
    private String id ;
    private String tendangnhap;
    private String pass;
    private String level;
    private String hoten;

    public User(String id, String tendangnhap, String pass, String level, String hoten) {
        this.id = id;
        this.tendangnhap = tendangnhap;
        this.pass = pass;
        this.level = level;
        this.hoten = hoten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getLevel() {
        return level;

    }

    public String getHoten() {
        return hoten;
    }
}
