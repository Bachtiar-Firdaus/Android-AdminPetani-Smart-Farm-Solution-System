package dev.edmt.aplikasipetanifix.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dev.edmt.aplikasipetanifix.R;
import dev.edmt.aplikasipetanifix.admin.model.cs_ku;


public class bs_Read_saldo_Petani_singgle extends AppCompatActivity {

    private Button btubahsaldo;
    private TextView tvalamat,tvemail,tvjenisbang,tvname,tvnik,tvnohp,tvnorek;
    private EditText ettotaltarik,etsaldo,tvslot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bs_activity_saldo_create);
        tvalamat = (TextView) findViewById(R.id.tv_alamat);
        tvemail = (TextView) findViewById(R.id.tv_email);
        tvjenisbang = (TextView) findViewById(R.id.tv_jenisbang);
        tvname = (TextView) findViewById(R.id.tv_name);
        tvnik = (TextView) findViewById(R.id.tv_nik);
        tvnohp = (TextView) findViewById(R.id.tv_nohp);
        tvnorek = (TextView) findViewById(R.id.tv_norek);
        tvslot = (EditText) findViewById(R.id.tv_slota);

        etsaldo = (EditText) findViewById(R.id.et_saldo);
        ettotaltarik = (EditText) findViewById(R.id.et_totaltarik);
        btubahsaldo = (Button) findViewById(R.id.ubahsaldo);

        tvslot.setEnabled(false);
        etsaldo.setEnabled(false);
        ettotaltarik.setEnabled(false);
        btubahsaldo.setVisibility(View.GONE);

        cs_ku cs_ku = (cs_ku) getIntent().getSerializableExtra("data");
        if(cs_ku !=null){
            tvalamat.setText(cs_ku.getalamat());//mengisi nilai edit text
            tvemail.setText(cs_ku.getemail());//mengisi nilai edit text
            tvjenisbang.setText(cs_ku.getjenisbang());
            tvname.setText(cs_ku.getname());//mengisi nilai edit text
            tvnik.setText(cs_ku.getnik());
            tvnohp.setText(cs_ku.getnohp());//mengisi nilai edit text
            tvnorek.setText(cs_ku.getnorek());
            etsaldo.setText(cs_ku.getsaldo());//mengisi nilai edit text
            ettotaltarik.setText(cs_ku.gettotaltarik());
            tvslot.setText(cs_ku.getslot());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, bs_Read_saldo_Petani_singgle.class);
    }
}
