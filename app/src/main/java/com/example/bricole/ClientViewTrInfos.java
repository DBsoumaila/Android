package com.example.bricole;

import android.widget.Button;

public class ClientViewTrInfos {

    Integer id;
    byte[] image;
    String name;
    String profession;
    String num;

    public ClientViewTrInfos (Integer id, byte[] image, String name, String profession, String num) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.profession = profession;
        this.num = num;
    }

    public ClientViewTrInfos (byte[] image, String name, String profession, String num) {
        this.image = image;
        this.name = name;
        this.profession = profession;
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    Button viewTrProfile, callTr;

    public ClientViewTrInfos ( byte[] image, String name, String profession) {
        this.image = image;
        this.name = name;
        this.profession = profession;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Button getViewTrProfile() {
        return viewTrProfile;
    }

    public void setViewTrProfile(Button viewTrProfile) {
        this.viewTrProfile = viewTrProfile;
    }

    public Button getCallTr() {
        return callTr;
    }

    public void setCallTr(Button callTr) {
        this.callTr = callTr;
    }
}
