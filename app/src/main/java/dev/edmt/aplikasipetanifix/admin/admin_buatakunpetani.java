package dev.edmt.aplikasipetanifix.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dev.edmt.aplikasipetanifix.R;
import dev.edmt.aplikasipetanifix.admin.adapter.AdapterBuatAkunPetani;
import dev.edmt.aplikasipetanifix.admin.model.sign_up_petani;

/**
 * Created by Herdi_WORK on 18.06.17.
 */

public class admin_buatakunpetani extends AppCompatActivity implements AdapterBuatAkunPetani.FirebaseDataListener {


    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<sign_up_petani> daftarSignuppetani;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_petani_read);

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();
         database.child("DAFTAR-LIST-PETANI-MENDAFTAR").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             daftarSignuppetani = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                    sign_up_petani signuppetani = noteDataSnapshot.getValue(sign_up_petani.class);
                    signuppetani.setKey(noteDataSnapshot.getKey());

                    daftarSignuppetani.add(signuppetani);
                }

                adapter = new AdapterBuatAkunPetani(daftarSignuppetani, admin_buatakunpetani.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, admin_buatakunpetani.class);
    }

    @Override
    public void onDeleteData(sign_up_petani signuppetani, final int position) {
        if(database!=null){
            database.child("DAFTAR-LIST-PETANI-MENDAFTAR").child(signuppetani.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(admin_buatakunpetani.this,"success delete", Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    public void btnbuatakunpetani(View view) {

        Intent intent = new Intent(admin_buatakunpetani.this, admin_buatakunadd.class);
        startActivity(intent);
    }


}
