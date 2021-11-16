/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author ariq
 */
public class Tiket {
    // Property
    private String nomor_tiket, nama_tiket, nomor_kursi;
    private double harga_tiket;
    private JENIS_TIKET jenis_tiket;
    
    public enum JENIS_TIKET {
        FirstClass, 
        BusinessClass, 
        EconomyClass
    }

    // Constructor
    public Tiket(String nomor_tiket, String nama_tiket, String nomor_kursi, JENIS_TIKET jenis_tiket, double harga_tiket)
    {
        this.nomor_tiket = nomor_tiket;
        this.nama_tiket = nama_tiket;
        this.nomor_kursi = nomor_kursi;
        this.jenis_tiket = jenis_tiket;
        this.harga_tiket = harga_tiket;
    }
    
    // Method
    public String getNomorTiket () {
        return nomor_tiket;
    }
    
    public String getNamaTiket () {
        return nama_tiket;
    }
    
    public JENIS_TIKET getJenisTiket () {
        return jenis_tiket;
    }
    
    public double getHargaTiket () {
        return hargaTiket;
    }
}
