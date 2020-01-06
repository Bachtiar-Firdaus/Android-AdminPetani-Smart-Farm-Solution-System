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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dev.edmt.aplikasipetanifix.R;
import dev.edmt.aplikasipetanifix.admin.model.saldo;

/**
 * Created by Herdi_WORK on 08.08.17.
 */

public class asCreate_saldo_Petani extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btSubmit;
    private EditText etEmail;
    private EditText etSaldo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.as_activity_saldo_create);

        // inisialisasi fields EditText dan Button
        etEmail = (EditText) findViewById(R.id.et_email_petani);
        etSaldo = (EditText) findViewById(R.id.et_saldo_petani);
        btSubmit = (Button) findViewById(R.id.bt_submit);

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        final saldo saldo = (saldo) getIntent().getSerializableExtra("data");

        if (saldo != null) {
            etEmail.setText(saldo.getemail());//mengisi nilai edit text
            etSaldo.setText(saldo.gettotaltarik());
            btSubmit.setOnClickListener(new View.OnClickListener() { // ketika tombol button diklik
                @Override
                public void onClick(View view) {
                    saldo.setemail(etEmail.getText().toString());
                    saldo.settotaltarik(etSaldo.getText().toString());

                    updateBarang(saldo);
                }
            });
        } else {
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isEmpty(etEmail.getText().toString()) && !isEmpty(etSaldo.getText().toString()))
                        submitBarang(new saldo(etEmail.getText().toString(), etSaldo.getText().toString()));
                    else
                        Snackbar.make(findViewById(R.id.bt_submit), "Data saldo tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                    InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            etEmail.getWindowToken(), 0);
                }
            });
        }
    }

    private boolean isEmpty(String s){
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void updateBarang(saldo saldo) {
        /**
         * Baris kode yang digunakan untuk mengupdate data saldo
         * yang sudah dimasukkan di Firebase Realtime Database
         */
        database.child("PETANI-req") //akses parent index, ibaratnya seperti nama tabel
                .child(saldo.getKey()) //select saldo berdasarkan key
                .setValue(saldo) //set value saldo yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        /**
                         * Baris kode yang akan dipanggil apabila proses update saldo sukses
                         */
                        Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil diupdatekan", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
    }

    private void submitBarang(saldo saldo) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("PETANI-req").push().setValue(saldo).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etEmail.setText("");
                etSaldo.setText("");
                Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, asCreate_saldo_Petani.class);
    }
}
