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
import dev.edmt.aplikasipetanifix.admin.model.ha_p_ku;


public class ha_admin_c_Petani extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btubahsaldo;
    private TextView tvalamat,tvemail,tvjenisbang,tvname,tvnik,tvnohp,tvnorek;
    private EditText ettotaltarik,etsaldo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ha_activity_saldo_create);

        // inisialisasi fields EditText dan Button
        tvalamat = (TextView) findViewById(R.id.tv_alamat);
        tvemail = (TextView) findViewById(R.id.tv_email);
        tvjenisbang = (TextView) findViewById(R.id.tv_jenisbang);
        tvname = (TextView) findViewById(R.id.tv_name);
        tvnik = (TextView) findViewById(R.id.tv_nik);
        tvnohp = (TextView) findViewById(R.id.tv_nohp);
        tvnorek = (TextView) findViewById(R.id.tv_norek);

        etsaldo = (EditText) findViewById(R.id.et_saldo);
        ettotaltarik = (EditText) findViewById(R.id.et_totaltarik);
        btubahsaldo = (Button) findViewById(R.id.ubahsaldo);

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        final ha_p_ku ha_p_ku = (ha_p_ku) getIntent().getSerializableExtra("data");

        if (ha_p_ku != null) {
            tvalamat.setText(ha_p_ku.getalamat());//mengisi nilai edit text
            tvemail.setText(ha_p_ku.getemail());//mengisi nilai edit text
            tvjenisbang.setText(ha_p_ku.getjenisbang());
            tvname.setText(ha_p_ku.getname());//mengisi nilai edit text
            tvnik.setText(ha_p_ku.getnik());
            tvnohp.setText(ha_p_ku.getnohp());//mengisi nilai edit text
            tvnorek.setText(ha_p_ku.getnorek());
            etsaldo.setText(ha_p_ku.getsaldo());//mengisi nilai edit text
            ettotaltarik.setText(ha_p_ku.gettotaltarik());
            btubahsaldo.setOnClickListener(new View.OnClickListener() { // ketika tombol button diklik
                @Override
                public void onClick(View view) {
                    ha_p_ku.setalamat(tvalamat.getText().toString());
                    ha_p_ku.setemail(tvemail.getText().toString());
                    ha_p_ku.setjenisbang(tvjenisbang.getText().toString());
                    ha_p_ku.setname(tvname.getText().toString());
                    ha_p_ku.setnik(tvnik.getText().toString());
                    ha_p_ku.setnohp(tvnohp.getText().toString());
                    ha_p_ku.setnorek(tvnorek.getText().toString());
                    ha_p_ku.setsaldo(etsaldo.getText().toString());
                    ha_p_ku.settotaltarik(ettotaltarik.getText().toString());

                    updateBarang(ha_p_ku);
                }
            });
        } else {
            btubahsaldo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(!isEmpty(tvalamat.getText().toString()) && !isEmpty(tvemail.getText().toString())&& !isEmpty(tvjenisbang.getText().toString())&& !isEmpty(tvname.getText().toString())&& !isEmpty(tvnik.getText().toString())&& !isEmpty(tvnohp.getText().toString())&& !isEmpty(tvnorek.getText().toString())&& !isEmpty(etsaldo.getText().toString())&& !isEmpty(ettotaltarik.getText().toString()))
                        submitBarang(new ha_p_ku(tvalamat.getText().toString(), tvemail.getText().toString(), tvjenisbang.getText().toString(), tvname.getText().toString(), tvnik.getText().toString(), tvnohp.getText().toString(), tvnorek.getText().toString(), etsaldo.getText().toString(), ettotaltarik.getText().toString()));
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

    private void updateBarang(ha_p_ku ha_p_ku) {
        /**
         * Baris kode yang digunakan untuk mengupdate data ha_p_ku
         * yang sudah dimasukkan di Firebase Realtime Database
         */
        database.child("PETANI") //akses parent index, ibaratnya seperti nama tabel
                .child(ha_p_ku.getKey()) //select ha_p_ku berdasarkan key
                .setValue(ha_p_ku) //set value ha_p_ku yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        /**
                         * Baris kode yang akan dipanggil apabila proses update ha_p_ku sukses
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

    private void submitBarang(ha_p_ku ha_p_ku) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("PETANI").push().setValue(ha_p_ku).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                tvalamat.setText("");
                tvemail.setText("");
                tvjenisbang.setText("");
                tvname.setText("");
                tvnik.setText("");
                tvnohp.setText("");
                tvnorek.setText("");
                etsaldo.setText("");
                ettotaltarik.setText("");
                Snackbar.make(findViewById(R.id.ubahsaldo), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, ha_admin_c_Petani.class);
    }
}
