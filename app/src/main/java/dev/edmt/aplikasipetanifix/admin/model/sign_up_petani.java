package dev.edmt.aplikasipetanifix.admin.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Herdi_WORK on 18.06.17.
 */

@IgnoreExtraProperties
public class sign_up_petani implements Serializable{


    private String Nama;
    private String Nik;
    private String Nohp;
    private String key;

    public sign_up_petani(){

    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getNik() {
        return Nik;
    }
    public void setNik(String Nik) {
        this.Nik = Nik;
    }

    public String getNohp() {
        return Nohp;
    }

    public void setNohp(String Nohp) {
        this.Nohp = Nohp;
    }

    @Override
    public String toString() {
        return " "+Nama+"\n" +
                " "+Nik +"\n" +
                " "+Nohp;
    }

    public sign_up_petani(String nm, String mrk, String hrg){
        Nama = nm;
        Nik = mrk;
        Nohp = hrg;
    }
}
