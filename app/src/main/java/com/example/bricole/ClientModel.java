package com.example.bricole;

public class ClientModel
{
    private int id;
    private String email;
    private String password;
    private String nomComplet;
    private String numTel;
    private String adresse;
    private String ville;

    public ClientModel(int id, String email, String password, String nomComplet, String numTel, String adresse, String ville) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nomComplet = nomComplet;
        this.numTel = numTel;
        this.adresse = adresse;
        this.ville = ville;
    }

    public ClientModel(String email, String password, String nomComplet, String numTel, String adresse, String ville) {
        this.email = email;
        this.password = password;
        this.nomComplet = nomComplet;
        this.numTel = numTel;
        this.adresse = adresse;
        this.ville = ville;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
