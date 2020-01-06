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
import dev.edmt.aplikasipetanifix.admin.model.ha_i_ku;

public class ha_admin_Investor_singgle extends AppCompatActivity {

    private Button btubahsaldo;
    private TextView tvalamat,tvemail,tvjenisbang,tvname,tvnik,tvnohp,tvnorek,tvslotdibeli,tvtotalbayar,ettotaltarik,etsaldo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ha_activity_investor_create);
        tvalamat = (TextView) findViewById(R.id.tv_alamat);
        tvemail = (TextView) findViewById(R.id.tv_email);
        tvjenisbang = (TextView) findViewById(R.id.tv_jenisbang);
        tvname = (TextView) findViewById(R.id.tv_name);
        tvnik = (TextView) findViewById(R.id.tv_nik);
        tvnohp = (TextView) findViewById(R.id.tv_nohp);
        tvnorek = (TextView) findViewById(R.id.tv_norek);
        tvslotdibeli = (TextView) findViewById(R.id.tv_slotdibeli);
        tvtotalbayar = (TextView) findViewById(R.id.tv_totalbayar);

        etsaldo = (TextView) findViewById(R.id.et_saldo);
        ettotaltarik = (TextView) findViewById(R.id.et_totaltarik);
        btubahsaldo = (Button) findViewById(R.id.ubahsaldo);

        etsaldo.setEnabled(false);
        ettotaltarik.setEnabled(false);
        btubahsaldo.setVisibility(View.GONE);

        ha_i_ku ha_i_ku = (ha_i_ku) getIntent().getSerializableExtra("data");
        if(ha_i_ku !=null){
            tvalamat.setText(ha_i_ku.getalamat());//mengisi nilai edit text
            tvemail.setText(ha_i_ku.getemail());//mengisi nilai edit text
            tvjenisbang.setText(ha_i_ku.getjenisbang());
            tvname.setText(ha_i_ku.getname());//mengisi nilai edit text
            tvnik.setText(ha_i_ku.getnik());
            tvnohp.setText(ha_i_ku.getnohp());//mengisi nilai edit text
            tvnorek.setText(ha_i_ku.getnorek());
            tvslotdibeli.setText(ha_i_ku.getslotdibeli());//mengisi nilai edit text
            tvtotalbayar.setText(ha_i_ku.gettotalbayar());
            etsaldo.setText(ha_i_ku.getsaldo());//mengisi nilai edit text
            ettotaltarik.setText(ha_i_ku.gettotaltarik());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, ha_admin_Investor_singgle.class);
    }
}
