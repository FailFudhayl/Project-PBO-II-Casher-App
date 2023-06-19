/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controller;

import java.sql.SQLException;

import com.config.MyConfig;
import com.models.Produk;
import com.models.Riwayat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControllerDB extends MyConfig{
    public static void readDB() {
        connection();
        try {
            query = "SELECT NAMA_PRODUK, HARGA, JUMLAH FROM produk_tabel";
            preparedStatement= connect.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                    String.format("%s - Rp.%d - Stok %d", resultSet.getString("NAMA_PRODUK"), resultSet.getInt("HARGA"), resultSet.getInt("JUMLAH"))
                );
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Produk> getDBProduk() {
        connection();

        ArrayList<Produk> listProduk = new ArrayList<>();

        try {
            query = "SELECT NAMA_PRODUK, HARGA, JUMLAH FROM produk_tabel";

            preparedStatement = connect.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                listProduk.add(new Produk(resultSet.getString("NAMA_PRODUK"), resultSet.getLong("HARGA"), resultSet.getInt("JUMLAH")));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProduk;
    }
    
        public static ArrayList<Riwayat> getDBPRiwayat() {
        connection();

        ArrayList<Riwayat> listRiwayat = new ArrayList<>();

        try {
            query = "SELECT timestamp, produk, jumlah, harga_dibeli, uang_tunai, uang_kembali FROM riwayattb";

            preparedStatement = connect.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                listRiwayat.add(new Riwayat(resultSet.getString("timestamp"), resultSet.getString("produk"), resultSet.getInt("jumlah"),resultSet.getLong("harga_dibeli"), resultSet.getLong("uang_tunai"), resultSet.getLong("uang_kembali")));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listRiwayat;
    }
    
    public static void fetchProductDataFromDatabase(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Menghapus semua data dari tabel sebelum mengisi data baru
        connection();
        try {
            query = "SELECT NAMA_PRODUK, HARGA, JUMLAH FROM produk_tabel";
            preparedStatement = connect.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String namaProduk = resultSet.getString("NAMA_PRODUK");
                Long harga = resultSet.getLong("HARGA");
                int stok = resultSet.getInt("JUMLAH");
                model.addRow(new Object[]{namaProduk, harga, stok});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        // Tangani kesalahan penghubungan atau query SQL
        }
    }

    public static void insertDB(String produk,  Long harga, Integer stok) {
        connection();
        query= "INSERT INTO produk_tabel (NAMA_PRODUK, HARGA, JUMLAH) VALUES (?, ?, ?)";
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, produk);
            preparedStatement.setLong(2, harga);
            preparedStatement.setInt(3, stok);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertHistory(String produk, String jumlah, String harga, String uangTunai, String uangKembali) {
        connection();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        query = "INSERT INTO riwayattb (timestamp, produk, jumlah, harga_dibeli, uang_tunai, uang_kembali) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, dtf.format(now));
            preparedStatement.setString(2, produk);
            preparedStatement.setInt(3, Integer.parseInt(jumlah));
            preparedStatement.setLong(4, Integer.parseInt(harga));
            preparedStatement.setLong(5, Integer.parseInt(uangTunai));
            preparedStatement.setLong(6, Integer.parseInt(uangKembali));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateProdukDB(String nama, Long harga, int jumlah) {
        connection();
        query = "UPDATE produk_tabel SET HARGA=?, JUMLAH=? WHERE NAMA_PRODUK=?";
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(3, nama);
            preparedStatement.setLong(1, harga);
            preparedStatement.setInt(2, jumlah);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateNamaDB(int id, String name) {
        connection();
        query= "UPDATE produk_tabel SET NAMA_PRODUK=? WHERE ID=?";
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateHargaDB(int id, Long harga) {
        connection();
        query= "UPDATE produk_tabel SET HARGA=? WHERE ID=?";
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setLong(1, harga);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStokDB(String name, Integer stok) {
        connection();
        query= "UPDATE produk_tabel SET JUMLAH=? WHERE NAMA_PRODUK=?";
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, stok);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deletDB(String nama) {
        connection();
        query= "DELETE FROM produk_tabel WHERE NAMA_PRODUK=?";
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, nama);
            int rowDelete = preparedStatement.executeUpdate();
            if (rowDelete>0) {
                return true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Produk getProdukbyNama(String nama){
        Produk produk = null;
        connection();
        query = "SELECT * FROM produk_tabel WHERE NAMA_PRODUK=?";
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, nama);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                produk = new Produk(resultSet.getString("NAMA_PRODUK"), resultSet.getLong("HARGA"), resultSet.getInt("JUMLAH"));
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produk;
    }
}
