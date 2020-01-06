package dev.edmt.aplikasipetanifix.admin;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import dev.edmt.aplikasipetanifix.R;

public class nav_profile extends AppCompatActivity {

    private EditText alamat,email,name,nik,nohp;
    private Button ubah;
    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_profile);
        alamat = (EditText)findViewById(R.id.p_alamat);
        email = (EditText)findViewById(R.id.p_email);
        name= (EditText)findViewById(R.id.p_nama);
        nik= (EditText)findViewById(R.id.p_nik);
        nohp= (EditText)findViewById(R.id.p_nohp);
        ubah= (Button) findViewById(R.id.p_ubah);


        email.setEnabled(false);
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String current_uid = mCurrentUser.getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("ADMIN").child(current_uid);
        mUserDatabase.keepSynced(true);
        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String galamat = dataSnapshot.child("alamat").getValue().toString();
                alamat.setText(galamat);
                String gemail = dataSnapshot.child("email").getValue().toString();
                email.setText(gemail);
                String gname = dataSnapshot.child("name").getValue().toString();
                name.setText(gname);
                String gnik = dataSnapshot.child("nik").getValue().toString();
                nik.setText(gnik);
                String gnohp = dataSnapshot.child("nohp").getValue().toString();
                nohp.setText(gnohp);
            }
            @Override

            public void onCancelled(DatabaseError databaseError) {

            }
        });




        ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String salamat = alamat.getText().toString();
                String semail = email.getText().toString();
                String sname = name.getText().toString();
                String snik = nik.getText().toString();
                String snohp = nohp.getText().toString();

                mProgress = new ProgressDialog(nav_profile.this);
                mProgress.setTitle("Saving Changes");
                mProgress.setMessage("Please wait while we save the changes");
                mProgress.show();
                mUserDatabase.child("alamat").setValue(salamat).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            mProgress.dismiss();

                        } else {

                            Toast.makeText(getApplicationContext(), "There was some error in saving Changes.", Toast.LENGTH_LONG).show();

                        }
                    }
                });


                mUserDatabase.child("email").setValue(semail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            mProgress.dismiss();

                        } else {

                            Toast.makeText(getApplicationContext(), "There was some error in saving Changes.", Toast.LENGTH_LONG).show();

                        }
                    }
                });






                mUserDatabase.child("name").setValue(sname).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            mProgress.dismiss();

                        } else {

                            Toast.makeText(getApplicationContext(), "There was some error in saving Changes.", Toast.LENGTH_LONG).show();

                        }
                    }
                });



                mUserDatabase.child("nik").setValue(snik).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            mProgress.dismiss();

                        } else {

                            Toast.makeText(getApplicationContext(), "There was some error in saving Changes.", Toast.LENGTH_LONG).show();

                        }
                    }
                });




                mUserDatabase.child("nohp").setValue(snohp).addOnCompleteListener(new OnCompleteListener<Void>() {
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
