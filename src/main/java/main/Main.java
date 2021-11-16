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
import classes.Tiket;

/**
 *
 * @author ariq
 */
public class Main {
    public daftarTiket daftarTiket;
    
       public static void main (String[] args) {
        // Inisialisasi kelas Scanner untuk mengambil input dari keyboard
        Scanner input = new Scanner(System.in);
        // Tambahkan 
        String no_transaksi, nama_pemesan, tanggal, no_meja = "";
        String transaksi_lagi = "", pesan_lagi = "", keterangan = "", makan_ditempat; 
        // End Of Tambahkan
        MainAplikasiKasir app = new MainAplikasiKasir();
        // Tampilkan daftar tiket
        app.generateDaftarTiket();
        
        // Mulai transaksi
        System.out.println("======== TRANSAKSI ========");
        
        do{
            // Ambil data transaksi
            System.out.println("No Transaksi : ");
            no_transaksi = input.next();
            System.out.println("Pemesan : ");
            nama_pemesan = input.next();
            System.out.println("Tanggal : [dd-mm-yyyy] ");
            tanggal = input.next();
            System.out.println("Makan ditempat? [Y/N] ");
            makan_ditempat = input.next();

            if(makan_ditempat.equalsIgnoreCase("Y")) {
                System.out.println("Nomor Meja : ");
                no_meja = input.next();
            }

            // Buat transaksi baru
            Transaksi trans = new Transaksi(no_transaksi, nama_pemesan, tanggal, no_meja);
            System.out.println("======== TRANSAKSI ========");
            do {
                // Ambil menu berdasarkan nomor urut yang dipilih
                Menu menu_yang_dipilih = app.daftarMenu.pilihMenu();
                int jumlah_pesanan = (int) app.cekInputNumber("Jumlah : ");

                // Buat pesanan
                Pesanan pesanan = new Pesanan(menu_yang_dipilih, jumlah_pesanan);
                trans.tambahPesanan(pesanan);

                // Khusus untuk menu ramen, pesanan kuahnya langsung diinput juga
                if(menu_yang_dipilih.getKategori().equalsIgnoreCase("Ramen")){
                    // Looping sesuai jumlah pesanan ramen
                    int jumlah_ramen = jumlah_pesanan;
                    do{
                        // Ambil objek menu berdasarkan nomor yang dipilih
                        Menu kuah_yang_dipilih = app.daftarMenu.pilihKuah();

                        System.out.println("Level : [0-5] ");
                        String level = input.next();

                        // Validasi jumlah kuah tidak boleh lebih besar dari ramen
                        int jumlah_kuah = 0;
                        do{
                            jumlah_kuah = (int) app.cekInputNumber("Jumlah : ");

                            if(jumlah_kuah > jumlah_ramen) {
                                System.out.println("[Err] Jumlah kuah melebih jumlah ramen yang sudah dipesan");
                            } else {
                                break;
                            }
                        } while(jumlah_kuah > jumlah_ramen);

                        // Set pesanan kuah
                        Pesanan pesanan_kuah = new Pesanan(kuah_yang_dipilih, jumlah_kuah);
                        pesanan_kuah.setKeterangan("Level " + level);

                        // Tambahkan pesanan kuah ke transaksi
                        trans.tambahPesanan(pesanan_kuah);

                        // Hitung jumlah ramen yang belum dipesan kuah nya
                        jumlah_ramen -= jumlah_kuah;  
                    } while(jumlah_ramen > 0);
                } else {
                    // Jika keterangan tidak diisi tulis -
                    System.out.println("Keterangan : [- jika kosong] ");
                    keterangan = input.next();
                }

                // Cek jika keterangan diisi selain "-" set ke pesanan
                if(!keterangan.equalsIgnoreCase("-")){
                    pesanan.setKeterangan(keterangan);
                }

                // Konfirmasi, mau tambah pesanan atau tidak
                System.out.println("Tambah pesanan lagi? [Y/N] ");
                pesan_lagi = input.next();
            } while (pesan_lagi.equalsIgnoreCase("Y"));

            // Cetak struk
            trans.cetakStruk();

            // Hitung total harga
            double total_pesanan = trans.hitungTotalPesanan();
            System.out.println("============================");
            System.out.println("Total : \t\t" + total_pesanan);

            // Hitung pajak
            trans.setPajak(PAJAK_PPN);
            double ppn = trans.hitungPajak();
            System.out.println("Pajak 10% : \t\t" + ppn);

            // Hitung biaya service
            // Jika makan ditempat, biaya pajak = 10% + 5% service
            double biaya_service = 0;
            if(makan_ditempat.equalsIgnoreCase("Y")){
                trans.setBiayaService(BIAYA_SERVICE);
                biaya_service = trans.hitungBiayaService();
                System.out.println("Biaya Service 5% : \t" + biaya_service);
            }

            // Tampilkan total bayar
            System.out.println("Total : \t\t" + trans.hitungTotalBayar(ppn, biaya_service));

            // Cek uang bayar, apakah > total bayar atau tidak
            double kembalian = 0;
            do{
                // Ambil input uang bayar
                double uang_bayar = app.cekInputNumber("Uang Bayar : \t\t");

                kembalian = trans.hitungKembalian(uang_bayar);
                if(kembalian < 0){
                    System.out.println("[Err] Uang anda kurang");
                } else{
                    System.out.println("Kembalian \t\t" + kembalian);
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
        daftarTiket.tambahTiket(new Tiket("T001","JAKARTA - BALI", "A1", JENIS_TIKET.FirstClass, 150000));
    
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
