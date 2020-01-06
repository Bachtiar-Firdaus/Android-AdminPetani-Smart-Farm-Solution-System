package dev.edmt.aplikasipetanifix.admin.model;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Herdi_WORK on 18.06.17.
 */

@IgnoreExtraProperties
public class lap_inv_ku implements Serializable {


    private String key,email,hargaperslotdiwaktuitu,slotdibeli,tanggalpembelian;

    public lap_inv_ku(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }


    public String gethargaperslotdiwaktuitu() {
        return hargaperslotdiwaktuitu;
    }

    public void sethargaperslotdiwaktuitu(String hargaperslotdiwaktuitu) {
        this.hargaperslotdiwaktuitu = hargaperslotdiwaktuitu;
    }

    public String getslotdibeli() {
        return slotdibeli;
    }

    public void setslotdibeli(String slotdibeli) {
        this.slotdibeli = slotdibeli;
    }


    public String gettanggalpembelian() {
        return tanggalpembelian;
    }

    public void settanggalpembelian(String tanggalpembelian) {
        this.tanggalpembelian = tanggalpembelian;
    }



    @Override
    public String toString() {
        return " "+email+"\n" +
                " "+hargaperslotdiwaktuitu+"\n" +
                " "+slotdibeli+"\n" +
                " "+tanggalpembelian;

    }
    public lap_inv_ku(String a, String b, String c, String f){
            email=a;
        hargaperslotdiwaktuitu = b;
        slotdibeli= c;
        tanggalpembelian= f;
    }

}
