package dev.edmt.aplikasipetanifix.admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dev.edmt.aplikasipetanifix.R;
import dev.edmt.aplikasipetanifix.admin.model.lap_pet_ku;

public class lap_admin_c_pet extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btubahsaldo;
    private TextView tvslot,tvemail;
    private EditText ettotaltarik,etsaldo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lap_activity_saldo_create);

        // inisialisasi fields EditText dan Button
        tvemail = (TextView) findViewById(R.id.tv_email);
        tvslot = (TextView) findViewById(R.id.tv_slot);

        etsaldo = (EditText) findViewById(R.id.et_saldo);
        btubahsaldo = (Button) findViewById(R.id.ubahsaldo);

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        final lap_pet_ku lap_pet_ku = (lap_pet_ku) getIntent().getSerializableExtra("data");

        if (lap_pet_ku != null) {
            tvemail.setText(lap_pet_ku.getemail());//mengisi nilai edit text
            tvslot.setText(lap_pet_ku.getslot());
            btubahsaldo.setOnClickListener(new View.OnClickListener() { // ketika tombol button diklik
                @Override
                public void onClick(View view) {
                    lap_pet_ku.setemail(tvemail.getText().toString());
                    lap_pet_ku.setslot(tvslot.getText().toString());

                    updateBarang(lap_pet_ku);
                }
            });
        } else {
            btubahsaldo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(!isEmpty(tvemail.getText().toString()) && !isEmpty(tvslot.getText().toString()))
                        submitBarang(new lap_pet_ku(tvemail.getText().toString(), tvslot.getText().toString()));
                    else
                        Snackbar.make(findViewById(R.id.ubahsaldo), "Data ha_i_ku tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            tvemail.getWindowToken(), 0);
                }
            });
        }
    }

    private boolean isEmpty(String s){
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void updateBarang(lap_pet_ku lap_pet_ku) {
        /**
         * Baris kode yang digunakan untuk mengupdate data lap_pet_ku
         * yang sudah dimasukkan di Firebase Realtime Database
         */
        database.child("PETANI-LAPORAN") //akses parent index, ibaratnya seperti nama tabel
                .child(lap_pet_ku.getKey()) //select lap_pet_ku berdasarkan key
                .setValue(lap_pet_ku) //set value lap_pet_ku yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        /**
                         * Baris kode yang akan dipanggil apabila proses update lap_pet_ku sukses
                         */
                        Snackbar.make(findViewById(R.id.ubahsaldo), "Data berhasil diupdatekan", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
    }

    private void submitBarang(lap_pet_ku lap_pet_ku) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("PETANI-LAPORAN").push().setValue(lap_pet_ku).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                tvemail.setText("");
                tvslot.setText("");
                Snackbar.make(findViewById(R.id.ubahsaldo), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, lap_admin_c_pet.class);
    }
}
