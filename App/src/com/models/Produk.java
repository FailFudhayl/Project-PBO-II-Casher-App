package com.models;

public class Produk {
    private int id;
    private String name;
    private long price;
    private int stock;

    public Produk(String name, long price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
