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
import dev.edmt.aplikasipetanifix.admin.model.lap_pet_ku;

public class lap_admin_pet_singgle extends AppCompatActivity {

    private Button btubahsaldo;
    private TextView tvemail,tvslot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lap_activity_pet_create);
        tvemail = (TextView) findViewById(R.id.tv_email);
        tvslot = (TextView) findViewById(R.id.tv_slot);
        btubahsaldo = (Button) findViewById(R.id.ubahsaldo);

        btubahsaldo.setVisibility(View.GONE);

        lap_pet_ku lap_pet_ku = (lap_pet_ku) getIntent().getSerializableExtra("data");
        if(lap_pet_ku !=null){
            tvemail.setText(lap_pet_ku.getemail());//mengisi nilai edit text
            tvslot.setText(lap_pet_ku.getslot());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, lap_admin_pet_singgle.class);
    }
}
