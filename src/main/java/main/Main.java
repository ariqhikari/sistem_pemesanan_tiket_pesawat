/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;
import java.util.InputMismatchException;

import classes.Pelanggan;
import classes.Transaksi;
import classes.DaftarTiket;
import classes.Pelanggan.JENIS_KELAMIN;
import classes.Tiket;
import classes.Tiket.JENIS_TIKET;
import classes.TiketPelanggan;

/**
 *
 * @author ariq
 */
public class Main {
    public DaftarTiket daftarTiket;
    
       public static void main (String[] args) {
        // Inisialisasi kelas Scanner untuk mengambil input dari keyboard
        Scanner input = new Scanner(System.in);
        
        // Pelanggan 
        Pelanggan pelanggan;
        String nama, no_telepon, jenis_kelamin;
        
        // Transaksi
        String no_transaksi, tanggal;
        String transaksi_lagi = "", pesan_lagi = "";
        
        Main app = new Main();
        
        // Ambil data pelanggan
        System.out.println("Nama Pelanggan : ");
        nama = input.nextLine();
        System.out.println("Nomor Telepon : ");
        no_telepon = input.next();
        System.out.println("Jenis Kelamin : [L/P]");
        jenis_kelamin = input.next();
        
        pelanggan = new Pelanggan(nama, no_telepon, jenis_kelamin.equalsIgnoreCase("L") ? JENIS_KELAMIN.L : JENIS_KELAMIN.P);
        
        // Tampilkan daftar tiket
        app.generateDaftarTiket();
        
        // Mulai transaksi
        System.out.println("======== TRANSAKSI ========");
        
        do{
            // Ambil data transaksi
            System.out.println("No Transaksi : ");
            no_transaksi = input.next();
            System.out.println("Tanggal : [dd-mm-yyyy] ");
            tanggal = input.next();

            // Buat transaksi baru
            Transaksi transaksi = new Transaksi(no_transaksi, pelanggan, tanggal);
            System.out.println("======== TRANSAKSI ========");
            do {
                // Ambil tiket berdasarkan nomor urut yang dipilih
                Tiket tiket_yang_dipilih = app.daftarTiket.pilihTiket();
                int jumlah_tiket = (int) app.cekInputNumber("Jumlah : ");
                
                // Buat pesanan
                TiketPelanggan tiketPelanggan = new TiketPelanggan(tiket_yang_dipilih, jumlah_tiket);
                transaksi.tambahTiketPelanggan(tiketPelanggan);

                // Konfirmasi, mau tambah tiket atau tidak
                System.out.println("Pesan tiket lagi? [Y/N] ");
                pesan_lagi = input.next();
            } while (pesan_lagi.equalsIgnoreCase("Y"));

            // Cetak struk
            transaksi.cetakStruk();

            // Hitung total harga
            double total_tiket_pesanan = transaksi.hitungTotalTiketPelanggan();
            System.out.println("============================");
            System.out.println("Total : \t\t\t" + total_tiket_pesanan);

            // Cek uang bayar, apakah > total bayar atau tidak
            double kembalian = 0;
            do{
                // Ambil input uang bayar
                double uang_bayar = app.cekInputNumber("Uang Bayar : \t\t\t");
                kembalian = transaksi.hitungKembalian(uang_bayar);
                    
                if(kembalian < 0){
                    System.out.println("[Err] Uang anda kurang");
                } else{
                    System.out.println("Kembalian \t\t\t" + kembalian);
                }
            } while(kembalian < 0);

            System.out.println("Lakukan Transaksi Lagi? [Y/N] ");
            transaksi_lagi = input.next();
        } while(transaksi_lagi.equalsIgnoreCase("Y"));
        
        System.out.println("====== TERIMA KASIH ========");
    }
    
    public void generateDaftarTiket () {
//        this.nomor_tiket = nomor_tiket;
//        this.nama_tiket = nama_tiket;
//        this.nomor_kursi = nomor_kursi;
//        this.jenis_tiket = jenis_tiket;
//        this.harga_tiket = harga_tiket;
        
        daftarTiket = new DaftarTiket();
        daftarTiket.tambahTiket(new Tiket("T001","JAKARTA - BALI", "A1", JENIS_TIKET.FirstClass, 125000));
        daftarTiket.tambahTiket(new Tiket("T002","JAKARTA - TOKYO", "A2", JENIS_TIKET.EconomyClass, 300000));
        daftarTiket.tambahTiket(new Tiket("T003","JAKARTA - SINGAPURA", "A3", JENIS_TIKET.EconomyClass, 200000));
    
        daftarTiket.tampilDaftarTiket();
    }
    
    public double cekInputNumber(String label){
       try {
           Scanner get_input = new Scanner(System.in);
           System.out.println(label);
           double nilai = get_input.nextDouble();
           
           return nilai;
       } catch(InputMismatchException ex) {
            System.out.println("Harap masukkan angka");
            return cekInputNumber(label);
        }
    }
    
}
