package dev.edmt.aplikasipetanifix.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dev.edmt.aplikasipetanifix.R;
import dev.edmt.aplikasipetanifix.admin.model.sign_up_petani;


/**
 * Created by Hafizh Herdi on 10/15/2017.
 */

public class admin_read_petani_singgle extends AppCompatActivity {

    private Button btSubmit;
    private EditText etNama;
    private EditText etNik;
    private EditText etNohp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petani_view);
        etNama = (EditText) findViewById(R.id.et_namabarang);
        etNik = (EditText) findViewById(R.id.et_merkbarang);
        etNohp = (EditText) findViewById(R.id.et_hargabarang);
        btSubmit = (Button) findViewById(R.id.bt_submit);

        etNama.setEnabled(false);
        etNik.setEnabled(false);
        etNohp.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        sign_up_petani signuppetani = (sign_up_petani) getIntent().getSerializableExtra("data");
        if(signuppetani !=null){
            etNama.setText(signuppetani.getNama());
            etNik.setText(signuppetani.getNik());
            etNohp.setText(signuppetani.getNohp());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, admin_read_petani_singgle.class);
    }
}
