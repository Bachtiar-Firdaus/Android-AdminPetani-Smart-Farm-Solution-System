package dev.edmt.aplikasipetanifix.admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dev.edmt.aplikasipetanifix.R;


/**
 * Created by Herdi_WORK on 18.06.17.
 */

public class admin_slot extends AppCompatActivity {

    private EditText ethargaperslot,etslotaktiv,etpkterendah,etpktertitnggi;
    private Button btubah;

    private DatabaseReference mUserDatabase;

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_read);
        ethargaperslot = (EditText)findViewById(R.id.et_hargaperslot);
        etslotaktiv = (EditText)findViewById(R.id.et_slotaktiv);
        etpkterendah = (EditText)findViewById(R.id.et_pkterendah);
        etpktertitnggi = (EditText)findViewById(R.id.et_pktertinggi);

        btubah = (Button)findViewById(R.id.bt_ubah);
        String key = "-L6n-aado-cZYmhyoM7h";
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("SLOT").child(key);
        mUserDatabase.keepSynced(true);
        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String simpan = dataSnapshot.child("harga").getValue().toString();
                ethargaperslot.setText(simpan);
                String simpan1 = dataSnapshot.child("slot").getValue().toString();
                etslotaktiv.setText(simpan1);
                String simpan2 = dataSnapshot.child("pkrendah").getValue().toString();
                etpkterendah.setText(simpan2);
                String simpan3 = dataSnapshot.child("pktinggi").getValue().toString();
                etpktertitnggi.setText(simpan3);


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        btubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mProgress = new ProgressDialog(admin_slot.this);
                mProgress.setTitle("Saving Changes");
                mProgress.setMessage("Please wait while we save the changes");
                mProgress.show();
                String daus = ethargaperslot.getText().toString();
                mUserDatabase.child("harga").setValue(daus).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            mProgress.dismiss();

                        } else {

                            Toast.makeText(getApplicationContext(), "There was some error in saving Changes.", Toast.LENGTH_LONG).show();

                        }

                    }
                });

                String ganteng = etslotaktiv.getText().toString();
                mUserDatabase.child("slot").setValue(ganteng).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            mProgress.dismiss();

                        } else {

                            Toast.makeText(getApplicationContext(), "There was some error in saving Changes.", Toast.LENGTH_LONG).show();

                        }

                    }
                });
                String banget = etpkterendah.getText().toString();
                mUserDatabase.child("pkrendah").setValue(banget).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            mProgress.dismiss();

                        } else {

                            Toast.makeText(getApplicationContext(), "There was some error in saving Changes.", Toast.LENGTH_LONG).show();

                        }

                    }
                });
                String kan = etpktertitnggi.getText().toString();
                mUserDatabase.child("pktinggi").setValue(kan).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            mProgress.dismiss();

                        } else {

                            Toast.makeText(getApplicationContext(), "There was some error in saving Changes.", Toast.LENGTH_LONG).show();

                        }

                    }
                });







            }
        });


    }

}