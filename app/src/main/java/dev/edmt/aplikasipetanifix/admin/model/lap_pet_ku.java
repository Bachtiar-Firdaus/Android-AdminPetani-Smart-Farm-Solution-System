package dev.edmt.aplikasipetanifix.admin.model;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by Herdi_WORK on 18.06.17.
 */

@IgnoreExtraProperties
public class lap_pet_ku implements Serializable {


    private String key,email,slot;

    public lap_pet_ku(){

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


    public String getslot() {
        return slot;
    }

    public void setslot(String slot) {
        this.slot = slot;
    }




    @Override
    public String toString() {
        return " "+email+"\n" +
                " "+slot;

    }
    public lap_pet_ku(String a, String b){
            email=a;
            slot = b;
    }

}
