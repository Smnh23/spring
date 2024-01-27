package com.holitor.microserviceproduct.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int prix;

    //constructeur par défaut
    public Product() {
    }

    //constructeur pour nos tests

    public Product(long id, String name, int prix) {
        this.id=id;
        this.name=name;
        this.prix=prix;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix=prix;
    }
    
    @Override
    public String toString(){  
        return "Product {" + id + "," + name + "," + prix + "}";
    }
}


