/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;

/**
 *
 * @author ariq
 */
public class Transaksi {
    // Property
    private String nomor_transaksi;
    private Pelanggan pelanggan;
    private String tanggal;
    private ArrayList<TiketPelanggan> tiketPelanggan;
    private double total_bayar;

    // Constructor
    public Transaksi(String nomor_transaksi, Pelanggan pelanggan, String tanggal)
    {
        this.nomor_transaksi = nomor_transaksi;
        this.pelanggan = pelanggan;
        this.tanggal = tanggal;
        
        tiketPelanggan = new ArrayList<>();
    }
    
    // Method
    public void tambahTiketPelanggan (TiketPelanggan tiketPelanggan) {
        this.tiketPelanggan.add(tiketPelanggan);
    }
    
    public ArrayList<TiketPelanggan> getSemuaTiketPelanggan () {
        return this.tiketPelanggan;
    }
    
    public void cetakStruk () {
        System.out.println("\n======== Traveloka ========");
        System.out.println("No Transaksi : " + nomor_transaksi);
        System.out.println("Pemesan : " + pelanggan.getNama());
        System.out.println("Nomor Telepon : " + pelanggan.getNoTelepon());
        System.out.println("Tanggal : " + tanggal);
        
        System.out.println("============================");
        
        for (int i = 0; i < tiketPelanggan.size(); i++) {
            TiketPelanggan tp = tiketPelanggan.get(i);
            Tiket t = tp.getTiket();
            String struk = tp.getJumlahTiket() + " " + t.getNamaTiket() + " (" + t.getJenisTiket() +")" + "\t" + (t.getHargaTiket() * tp.getJumlahTiket());
            
            // Tampilkan Struk
            System.out.println(struk);
        }
    }
    
    public double hitungTotalTiketPelanggan(){
        for (int i = 0; i < tiketPelanggan.size(); i++) {
            TiketPelanggan tp = tiketPelanggan.get(i);
            double hargaTiket = tp.getTiket().getHargaTiket();
            this.total_bayar += (hargaTiket * tp.getJumlahTiket());
        }
        
        return this.total_bayar;
    }
    
    public double hitungKembalian (double uang_bayar) {
        return uang_bayar - this.total_bayar;
    }
}
