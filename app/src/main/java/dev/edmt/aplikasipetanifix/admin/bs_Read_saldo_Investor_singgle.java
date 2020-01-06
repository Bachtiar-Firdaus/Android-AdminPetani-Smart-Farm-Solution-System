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
import dev.edmt.aplikasipetanifix.admin.model.bs_ku;

public class bs_Read_saldo_Investor_singgle extends AppCompatActivity {

    private Button btubahsaldo;
    private TextView tvalamat,tvemail,tvjenisbang,tvname,tvnik,tvnohp,tvnorek,tvslotdibeli,tvtotalbayar;
    private EditText ettotaltarik,etsaldo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bs_activity_investor_create);
        tvalamat = (TextView) findViewById(R.id.tv_alamat);
        tvemail = (TextView) findViewById(R.id.tv_email);
        tvjenisbang = (TextView) findViewById(R.id.tv_jenisbang);
        tvname = (TextView) findViewById(R.id.tv_name);
        tvnik = (TextView) findViewById(R.id.tv_nik);
        tvnohp = (TextView) findViewById(R.id.tv_nohp);
        tvnorek = (TextView) findViewById(R.id.tv_norek);
        tvslotdibeli = (TextView) findViewById(R.id.tv_slotdibeli);
        tvtotalbayar = (TextView) findViewById(R.id.tv_totalbayar);

        etsaldo = (EditText) findViewById(R.id.et_saldo);
        ettotaltarik = (EditText) findViewById(R.id.et_totaltarik);
        btubahsaldo = (Button) findViewById(R.id.ubahsaldo);

        etsaldo.setEnabled(false);
        ettotaltarik.setEnabled(false);
        btubahsaldo.setVisibility(View.GONE);

        bs_ku bs_ku = (bs_ku) getIntent().getSerializableExtra("data");
        if(bs_ku !=null){
            tvalamat.setText(bs_ku.getalamat());//mengisi nilai edit text
            tvemail.setText(bs_ku.getemail());//mengisi nilai edit text
            tvjenisbang.setText(bs_ku.getjenisbang());
            tvname.setText(bs_ku.getname());//mengisi nilai edit text
            tvnik.setText(bs_ku.getnik());
            tvnohp.setText(bs_ku.getnohp());//mengisi nilai edit text
            tvnorek.setText(bs_ku.getnorek());
            tvslotdibeli.setText(bs_ku.getslotdibeli());//mengisi nilai edit text
            tvtotalbayar.setText(bs_ku.gettotalbayar());
            etsaldo.setText(bs_ku.getsaldo());//mengisi nilai edit text
            ettotaltarik.setText(bs_ku.gettotaltarik());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, bs_Read_saldo_Investor_singgle.class);
    }
}
