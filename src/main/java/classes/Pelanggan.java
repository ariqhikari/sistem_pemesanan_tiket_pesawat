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
    String nama, no_telepon;
    private final JENIS_KELAMIN jenis_kelamin;
    
    public enum JENIS_KELAMIN {
        L, 
        P
    }

    // Constructor
    public Pelanggan(String nama, String no_telepon, JENIS_KELAMIN jenis_kelamin)
    {
        this.nama = nama;
        this.no_telepon = no_telepon;
        this.jenis_kelamin = jenis_kelamin;
    }
    
    // Method
    public String getNama () {
        return nama;
    }
    
    public String getNoTelepon () {
        return no_telepon;
    }
    
    public JENIS_KELAMIN getJenisKelamin () {
        return jenis_kelamin;
    }
}
