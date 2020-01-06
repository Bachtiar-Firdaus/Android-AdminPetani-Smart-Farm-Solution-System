package dev.edmt.aplikasipetanifix.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dev.edmt.aplikasipetanifix.R;

public class admin_hapusakun extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_hapusakun);
    }

    public void h_petani(View view) {

        Intent pindah = new Intent(admin_hapusakun.this,ha_admin_petani.class);
        startActivity(pindah);
    }

    public void h_investor(View view) {
        Intent intent = new Intent(admin_hapusakun.this, ha_admin_invesator.class);
        startActivity(intent);
    }
}
