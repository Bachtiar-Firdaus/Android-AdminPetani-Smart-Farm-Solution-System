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
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dev.edmt.aplikasipetanifix.R;
import dev.edmt.aplikasipetanifix.admin.model.lap_inv_ku;

public class lap_admin_c_investor extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btubahsaldo;
    private TextView tvemail,tvslot,tvharga,tvtanggal;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lap_activity_saldo_create);

        // inisialisasi fields EditText dan Button
        tvemail = (TextView) findViewById(R.id.tv_email);
        tvslot = (TextView) findViewById(R.id.tv_slot);
        tvharga = (TextView) findViewById(R.id.tv_harga);
        tvtanggal = (TextView) findViewById(R.id.tv_tanggal);
        btubahsaldo = (Button) findViewById(R.id.ubahsaldo);

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        final lap_inv_ku lap_inv_ku = (lap_inv_ku) getIntent().getSerializableExtra("data");

        if (lap_inv_ku != null) {
            tvemail.setText(lap_inv_ku.getemail());//mengisi nilai edit text
            tvslot.setText(lap_inv_ku.getslotdibeli());//mengisi nilai edit text
            tvharga.setText(lap_inv_ku.gethargaperslotdiwaktuitu());
            tvtanggal.setText(lap_inv_ku.gettanggalpembelian());
            btubahsaldo.setOnClickListener(new View.OnClickListener() { // ketika tombol button diklik
                @Override
                public void onClick(View view) {
                    lap_inv_ku.setemail(tvemail.getText().toString());
                    lap_inv_ku.setslotdibeli(tvslot.getText().toString());
                    lap_inv_ku.sethargaperslotdiwaktuitu(tvharga.getText().toString());
                    lap_inv_ku.settanggalpembelian(tvtanggal.getText().toString());

                    updateBarang(lap_inv_ku);
                }
            });
        } else {
            btubahsaldo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(!isEmpty(tvemail.getText().toString()) && !isEmpty(tvslot.getText().toString())&& !isEmpty(tvharga.getText().toString())&& !isEmpty(tvtanggal.getText().toString()))
                        submitBarang(new lap_inv_ku(tvemail.getText().toString(), tvslot.getText().toString(), tvharga.getText().toString(), tvtanggal.getText().toString()));
                    else
                        Snackbar.make(findViewById(R.id.ubahsaldo), "Data ha_i_ku tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            tvtanggal.getWindowToken(), 0);
                }
            });
        }
    }

    private boolean isEmpty(String s){
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void updateBarang(lap_inv_ku lap_inv_ku) {
        /**
         * Baris kode yang digunakan untuk mengupdate data lap_inv_ku
         * yang sudah dimasukkan di Firebase Realtime Database
         */
        database.child("INVESTOR-LAPORAN") //akses parent index, ibaratnya seperti nama tabel
                .child(lap_inv_ku.getKey()) //select lap_inv_ku berdasarkan key
                .setValue(lap_inv_ku) //set value lap_inv_ku yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        /**
                         * Baris kode yang akan dipanggil apabila proses update lap_inv_ku sukses
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

    private void submitBarang(lap_inv_ku lap_inv_ku) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("INVESTOR-LAPORAN").push().setValue(lap_inv_ku).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                tvemail.setText("");
                tvslot.setText("");
                tvharga.setText("");
                tvtanggal.setText("");
                Snackbar.make(findViewById(R.id.ubahsaldo), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, lap_admin_c_investor.class);
    }
}
