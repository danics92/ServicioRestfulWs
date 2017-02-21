package Pojo;

import java.util.List;

/**
 * Created by dcatalans on 31/01/17.
 */
public class Persona {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public List<Fitxatge> getFitxatges() {
        return fitxatges;
    }

    public void setFitxatges(List<Fitxatge> fitxatges) {
        this.fitxatges = fitxatges;
    }

    private String nom;
    private String cognom;
    private List<Fitxatge> fitxatges;


}
