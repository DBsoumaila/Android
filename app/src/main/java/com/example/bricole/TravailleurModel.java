package com.example.bricole;

public class TravailleurModel {

    private int id;
    private byte[] profil_pic;
    private String fullName;
    private String proffesion;
    private String num;
    private String ville;
    private String signUpEmail;
    private String signUpPassword;
    private byte[] cin;
    private  byte[] ce;

    private String description;
    private byte[] workImg1;
    private byte[] workImg2;
    private byte[] workImg3;
    private byte[] workImg4;


   /* public TravailleurModel(int id, byte[] profil_pic, String fullName, String proffesion,
                            String num, String signUpEmail, String signUpPassword, byte[] cin, byte[] ce) {

        this.id = id;
        this.profil_pic = profil_pic;
        this.fullName = fullName;
        this.proffesion = proffesion;
        this.num = num;
        this.signUpEmail = signUpEmail;
        this.signUpPassword = signUpPassword;
        this.cin = cin;
        this.ce = ce;
    }

    public TravailleurModel(byte[] profil_pic, String fullName, String proffesion,
                            String num, String signUpEmail, String signUpPassword, byte[] cin, byte[] ce) {

        this.profil_pic = profil_pic;
        this.fullName = fullName;
        this.proffesion = proffesion;
        this.num = num;
        this.signUpEmail = signUpEmail;
        this.signUpPassword = signUpPassword;
        this.cin = cin;
        this.ce = ce;
    } */

    public TravailleurModel(int id, byte[] profil_pic, String fullName, String proffesion,
                            String num, String ville ,String signUpEmail, String signUpPassword, byte[] cin, byte[] ce,
                            String description, byte[] workImg1, byte[] workImg2, byte[] workImg3, byte[] workImg4) {

        this.id = id;
        this.profil_pic = profil_pic;
        this.fullName = fullName;
        this.proffesion = proffesion;
        this.num = num;
        this.ville = ville;
        this.signUpEmail = signUpEmail;
        this.signUpPassword = signUpPassword;
        this.cin = cin;
        this.ce = ce;

        this.description = description;
        this.workImg1 = workImg1;
        this.workImg2 = workImg2;
        this.workImg3 = workImg3;
        this.workImg4 = workImg4;
    }

    public TravailleurModel(byte[] profil_pic, String fullName, String proffesion,
                            String num, String ville, String signUpEmail, String signUpPassword, byte[] cin, byte[] ce,
                            String description, byte[] workImg1, byte[] workImg2, byte[] workImg3, byte[] workImg4) {

        this.profil_pic = profil_pic;
        this.fullName = fullName;
        this.proffesion = proffesion;
        this.num = num;
        this.ville = ville;
        this.signUpEmail = signUpEmail;
        this.signUpPassword = signUpPassword;
        this.cin = cin;
        this.ce = ce;

        this.description = description;
        this.workImg1 = workImg1;
        this.workImg2 = workImg2;
        this.workImg3 = workImg3;
        this.workImg4 = workImg4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getProfil_pic() {
        return profil_pic;
    }

    public void setProfil_pic(byte[] profil_pic) {
        this.profil_pic = profil_pic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProffesion() {
        return proffesion;
    }

    public void setProffesion(String proffesion) {
        this.proffesion = proffesion;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSignUpEmail() {
        return signUpEmail;
    }

    public void setSignUpEmail(String signUpEmail) {
        this.signUpEmail = signUpEmail;
    }

    public String getSignUpPassword() {
        return signUpPassword;
    }

    public void setSignUpPassword(String signUpPassword) {
        this.signUpPassword = signUpPassword;
    }

    public byte[] getCin() {
        return cin;
    }

    public void setCin(byte[] cin) {
        this.cin = cin;
    }

    public byte[] getCe() {
        return ce;
    }

    public void setCe(byte[] ce) {
        this.ce = ce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getWorkImg1() {
        return workImg1;
    }

    public void setWorkImg1(byte[] workImg1) {
        this.workImg1 = workImg1;
    }

    public byte[] getWorkImg2() {
        return workImg2;
    }

    public void setWorkImg2(byte[] workImg2) {
        this.workImg2 = workImg2;
    }

    public byte[] getWorkImg3() {
        return workImg3;
    }

    public void setWorkImg3(byte[] workImg3) {
        this.workImg3 = workImg3;
    }

    public byte[] getWorkImg4() {
        return workImg4;
    }

    public void setWorkImg4(byte[] workImg4) {
        this.workImg4 = workImg4;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
