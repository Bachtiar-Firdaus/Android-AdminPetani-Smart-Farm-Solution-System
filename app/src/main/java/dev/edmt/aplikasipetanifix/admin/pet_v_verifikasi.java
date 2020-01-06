package dev.edmt.aplikasipetanifix.admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import dev.edmt.aplikasipetanifix.R;

public class pet_v_verifikasi extends AppCompatActivity {
    ListView listView;
    List<pet_m_verifikasi> list;
    ProgressDialog progressDialog;
    pet_a_verifikasi averifikasi;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_v_verifikasi);

        listView = (ListView)findViewById(R.id.list1);

        list = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fecthing Please wait");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference("verifikasi_petani");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                list.clear();

                for (DataSnapshot snap : dataSnapshot.getChildren()){
                    pet_m_verifikasi mverifikasi = snap.getValue(pet_m_verifikasi.class);
                    list.add(mverifikasi);
                }

                averifikasi = new pet_a_verifikasi(pet_v_verifikasi.this, R.layout.pet_i_verifikasi, list);

                listView.setAdapter(averifikasi);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
