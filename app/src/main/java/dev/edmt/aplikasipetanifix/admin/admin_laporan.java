package dev.edmt.aplikasipetanifix.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import dev.edmt.aplikasipetanifix.R;

public class admin_laporan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_laporan);
    }

    public void lap_investor(View view) {
        Intent intent = new Intent(admin_laporan.this,lap_admin_investor.class);
        startActivity(intent);
    }

    public void lap_petani(View view) {
        Intent intent = new Intent(admin_laporan.this,lap_admin_pet.class);
        startActivity(intent);
    }
}
