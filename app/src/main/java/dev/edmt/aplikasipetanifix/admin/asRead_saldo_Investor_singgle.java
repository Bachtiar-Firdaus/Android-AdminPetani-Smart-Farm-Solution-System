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
import dev.edmt.aplikasipetanifix.admin.model.saldo;


/**
 * Created by Hafizh Herdi on 10/15/2017.
 */

public class asRead_saldo_Investor_singgle extends AppCompatActivity {

    private Button btSubmit;
    private EditText etEmail;
    private EditText etSaldo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.as_activity_investor_create);
        etEmail = (EditText) findViewById(R.id.et_email_petani);
        etSaldo = (EditText) findViewById(R.id.et_saldo_petani);
        btSubmit = (Button) findViewById(R.id.bt_submit);

        etEmail.setEnabled(false);
        etSaldo.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        saldo saldo = (saldo) getIntent().getSerializableExtra("data");
        if(saldo !=null){
            etEmail.setText(saldo.getemail());
            etSaldo.setText(saldo.gettotaltarik());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, asRead_saldo_Investor_singgle.class);
    }
}
