package dev.edmt.aplikasipetanifix.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dev.edmt.aplikasipetanifix.R;
import dev.edmt.aplikasipetanifix.admin.model.lap_inv_ku;

public class lap_admin_investor_singgle extends AppCompatActivity {

    private Button btubahsaldo;
    private TextView tvemail,tvslot,tvharga,tvtanggal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lap_activity_saldo_create);
        tvemail = (TextView) findViewById(R.id.tv_email);
        tvslot = (TextView) findViewById(R.id.tv_slot);
        tvharga = (TextView) findViewById(R.id.tv_harga);
        tvtanggal = (TextView) findViewById(R.id.tv_tanggal);
        btubahsaldo = (Button) findViewById(R.id.ubahsaldo);


        btubahsaldo.setVisibility(View.GONE);

        lap_inv_ku lap_inv_ku = (lap_inv_ku) getIntent().getSerializableExtra("data");
        if(lap_inv_ku !=null){
            tvemail.setText(lap_inv_ku.getemail());//mengisi nilai edit text
            tvslot.setText(lap_inv_ku.gethargaperslotdiwaktuitu());//mengisi nilai edit text
            tvharga.setText(lap_inv_ku.getslotdibeli());
            tvtanggal.setText(lap_inv_ku.gettanggalpembelian());//mengisi nilai edit text
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, lap_admin_investor_singgle.class);
    }
}
