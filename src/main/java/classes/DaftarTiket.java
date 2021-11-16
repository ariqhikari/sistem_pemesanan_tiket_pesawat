/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ariq
 */
public class DaftarTiket {
    // Property   
    private ArrayList<Tiket> daftarTiket;
    
    // Constructor
    public DaftarTiket () {
        daftarTiket = new ArrayList<Tiket>();
    }
    
    // Method
    public void tambahTiket (Tiket tiket) {
        daftarTiket.add(tiket);
    }
    
    public void tampilDaftarTiket () {
        System.out.println("======== Traveloka ========");
        
        for (int i = 0; i < daftarTiket.size(); i++) {
            Tiket t = daftarTiket.get(i);
            System.out.println((i + 1) + ". " + t.getNamaTiket + "\t" + m.getHargaTiket());
        }
    }
    
     public Tiket pilihTiket(){
        try{
            Scanner input = new Scanner(System.in);
            
            System.out.println("Nomor Tiket yang dipesan : ");
            int no_tiket = input.nextInt();
            
            // Get tiket berdasarkan no_tiket, di -1 karena arrayList mulai dari 0
            Tiket t = daftarTiket.get(no_tiket - 1);
        } catch(IndexOutOfBoundsException err) {
            // Jika no_tiket tidak ada, maka akan masuk kesini
            // no_tiket dianggap tidak ada ketika no_tiket diluar dari index pada arrayList
            System.out.println("[Err] Tiket tidak tersedia");
            return pilihTiket();
        } catch(InputMismatchException err) {
            // Jika input bukan berupa angka akan masuk kesini
            System.out.println("[Err] Mohon masukkan nomor tiket");
            return pilihTiket();
        }
    }
}
