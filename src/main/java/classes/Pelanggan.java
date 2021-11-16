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
public class Pelanggan {
    // Property
    String nama, email, no_telepon;
    
    public enum JENIS_KELAMIN {
        L, 
        P
    }

    // Constructor
    public Pelanggan(String nama, String email, String no_telepon, JENIS_KELAMIN jenis_kelamin)
    {
        this.id_pelanggan = id_pelanggan;
        this.nama = nama;
        this.email = email;
        this.no_telepon = no_telepon;
        this.jenis_kelamin = jenis_kelamin;
    }
    
    // Method
    public String getNama () {
        return nama;
    }
    
    public String getEmail () {
        return email;
    }
    
    public String getNoTelepon () {
        return no_telepon;
    }
    
    public JENIS_KELAMIN getJenisKelamin () {
        return jenis_kelamin;
    }
}
