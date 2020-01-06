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
import dev.edmt.aplikasipetanifix.admin.model.ha_p_ku;


public class ha_admin_Petani_singgle extends AppCompatActivity {

    private Button btubahsaldo;
    private TextView tvalamat,tvemail,tvjenisbang,tvname,tvnik,tvnohp,tvnorek,ettotaltarik,etsaldo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ha_activity_saldo_create);
        tvalamat = (TextView) findViewById(R.id.tv_alamat);
        tvemail = (TextView) findViewById(R.id.tv_email);
        tvjenisbang = (TextView) findViewById(R.id.tv_jenisbang);
        tvname = (TextView) findViewById(R.id.tv_name);
        tvnik = (TextView) findViewById(R.id.tv_nik);
        tvnohp = (TextView) findViewById(R.id.tv_nohp);
        tvnorek = (TextView) findViewById(R.id.tv_norek);

        etsaldo = (TextView) findViewById(R.id.et_saldo);
        ettotaltarik = (TextView) findViewById(R.id.et_totaltarik);
        btubahsaldo = (Button) findViewById(R.id.ubahsaldo);

        etsaldo.setEnabled(false);
        ettotaltarik.setEnabled(false);
        btubahsaldo.setVisibility(View.GONE);

        ha_p_ku ha_p_ku = (ha_p_ku) getIntent().getSerializableExtra("data");
        if(ha_p_ku !=null){
            tvalamat.setText(ha_p_ku.getalamat());//mengisi nilai edit text
            tvemail.setText(ha_p_ku.getemail());//mengisi nilai edit text
            tvjenisbang.setText(ha_p_ku.getjenisbang());
            tvname.setText(ha_p_ku.getname());//mengisi nilai edit text
            tvnik.setText(ha_p_ku.getnik());
            tvnohp.setText(ha_p_ku.getnohp());//mengisi nilai edit text
            tvnorek.setText(ha_p_ku.getnorek());
            etsaldo.setText(ha_p_ku.getsaldo());//mengisi nilai edit text
            ettotaltarik.setText(ha_p_ku.gettotaltarik());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, ha_admin_Petani_singgle.class);
    }
}
