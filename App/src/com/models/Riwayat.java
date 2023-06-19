package com.models;

public class Riwayat {
    private String timestamp;
    private String produk;
    private int jumlah;
    private long harga_dibeli;
    private long uang_tunai;
    private long uang_kembali;
    
    public Riwayat(String timestamp, String produk, int jumlah, long harga_dibeli, long uang_tunai, long uang_kembali) {
        this.timestamp = timestamp;
        this.produk = produk;
        this.jumlah = jumlah;
        this.harga_dibeli = harga_dibeli;
        this.uang_tunai = uang_tunai;
        this.uang_kembali = uang_kembali;
    }

    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getProduk() {
        return produk;
    }
    public void setProduk(String produk) {
        this.produk = produk;
    }

    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public long getHarga_dibeli() {
        return harga_dibeli;
    }
    public void setHarga_dibeli(long harga_dibeli) {
        this.harga_dibeli = harga_dibeli;
    }

    public long getUang_tunai() {
        return uang_tunai;
    }
    public void setUang_tunai(long uang_tunai) {
        this.uang_tunai = uang_tunai;
    }
    
    public long getUang_kembali() {
        return uang_kembali;
    }
    public void setUang_kembali(long uang_kembali) {
        this.uang_kembali = uang_kembali;
    }
}
