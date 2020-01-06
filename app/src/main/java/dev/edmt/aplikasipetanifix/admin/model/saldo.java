package dev.edmt.aplikasipetanifix.admin.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Herdi_WORK on 18.06.17.
 */

@IgnoreExtraProperties
public class saldo implements Serializable{


    private String key,email,totaltarik;

    public saldo(){

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

    public String gettotaltarik() {
        return totaltarik;
    }

    public void settotaltarik(String totaltarik) {
        this.totaltarik = totaltarik;
    }



    @Override
    public String toString() {
        return " "+email+"\n" +
                " "+totaltarik;
    }

    public saldo(String a, String b){
            email=a;
            totaltarik = b;
    }
}
