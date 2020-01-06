package dev.edmt.aplikasipetanifix.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dev.edmt.aplikasipetanifix.R;

public class admin_verifikasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verifikasi);
    }

    public void v_investor(View view) {

        Intent intent = new Intent(admin_verifikasi.this, inv_v_verifikasi.class);
        startActivity(intent);

    }

    public void v_petani(View view) {

        Intent intent = new Intent(admin_verifikasi.this, pet_v_verifikasi.class);
        startActivity(intent);

    }
}
