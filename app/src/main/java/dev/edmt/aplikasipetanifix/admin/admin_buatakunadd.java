package dev.edmt.aplikasipetanifix.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

import dev.edmt.aplikasipetanifix.R;

public class admin_buatakunadd extends AppCompatActivity {

    private EditText et_nik,et_nama,et_alamat,et_nohp,et_norekbank,et_jenisbank,tarik,et_email,et_password,slot;
    private Button klikdaftar;


    private Toolbar mToolbar;

    private DatabaseReference mDatabase,mDatabase2;

    //ProgressDialog
    private ProgressDialog mRegProgress,mProgress;

    //Firebase Auth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_buatakunadd);



        mRegProgress = new ProgressDialog(this);

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = current_user.getUid();

        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("PETANI-LAPORAN").child(uid);

        // Firebase Auth

        mAuth = FirebaseAuth.getInstance();


        // Android Fields

        et_nik = (EditText) findViewById(R.id.et_nik);
        et_nama = (EditText) findViewById(R.id.et_nama);
        et_alamat = (EditText) findViewById(R.id.et_alamat);
        et_nohp = (EditText) findViewById(R.id.et_nohp);
        et_norekbank = (EditText) findViewById(R.id.et_norekbank);
        et_jenisbank = (EditText) findViewById(R.id.et_jenisbank);
        slot = (EditText) findViewById(R.id.et_slotp);
        et_email = (EditText) findViewById(R.id.et_email);
        tarik = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        klikdaftar = (Button) findViewById(R.id.klikdaftar);


        klikdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String etnik = et_nik.getText().toString();
                String etnama = et_nama.getText().toString();
                String etalamat = et_alamat.getText().toString();
                String etnohp = et_nohp.getText().toString();
                String sslot = slot.getText().toString();
                String etnorekbank = et_norekbank.getText().toString();
                String etjenisbank = et_jenisbank.getText().toString();
                String etemail = et_email.getText().toString();
                String bbb = tarik.getText().toString();
                String etpassword = et_password.getText().toString();


                mProgress = new ProgressDialog(admin_buatakunadd.this);
                mProgress.setTitle("Saving Changes");
                mProgress.setMessage("Please wait while we save the changes");
                mProgress.show();
                mDatabase2.child("email").setValue(etemail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            mProgress.dismiss();

                        } else {

                            Toast.makeText(getApplicationContext(), "There was some error in saving Changes.", Toast.LENGTH_LONG).show();

                        }

                    }
                });
                mDatabase2.child("slot").setValue(sslot).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            mProgress.dismiss();

                        } else {

                            Toast.makeText(getApplicationContext(), "There was some error in saving Changes.", Toast.LENGTH_LONG).show();

                        }

                    }
                });


                if(!TextUtils.isEmpty(sslot) || !TextUtils.isEmpty(etnik) || !TextUtils.isEmpty(etnama) || !TextUtils.isEmpty(etalamat)|| !TextUtils.isEmpty(etnohp)|| !TextUtils.isEmpty(etnorekbank)|| !TextUtils.isEmpty(etjenisbank)|| !TextUtils.isEmpty(bbb)|| !TextUtils.isEmpty(etemail)|| !TextUtils.isEmpty(etpassword)){

                    mRegProgress.setTitle("Registering User");
                    mRegProgress.setMessage("Please wait while we create your account !");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();

                    register_user(sslot,etnik, etnama, etalamat,etnohp,etnorekbank,etjenisbank, bbb,etemail,etpassword);

                }



            }
        });


    }

    private void register_user(final String sslot,final String etnik, final String etnama, final String etalamat, final String etnohp, final String etnorekbank, final String etjenisbank, final String bbb, String etemail, String etpassword) {

        mAuth.createUserWithEmailAndPassword(etemail,etpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){


                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = current_user.getUid();

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("PETANI").child(uid);

                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("alamat", etalamat);
                    userMap.put("jenisbang", etjenisbank);
                    userMap.put("name", etnama);
                    userMap.put("nik", etnik);
                    userMap.put("nohp", etnohp);
                    userMap.put("norek", etnorekbank);
                    userMap.put("email", bbb);
                    userMap.put("slot", sslot);
                    userMap.put("saldo", "0");
                    userMap.put("totaltarik", "0");

                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                mRegProgress.dismiss();

                                Intent mainIntent = new Intent(admin_buatakunadd.this, dev.edmt.aplikasipetanifix.admin.admin_MainActivity.class);
                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainIntent);
                                finish();

                            }

                        }
                    });


                } else {

                    mRegProgress.hide();
                    Toast.makeText(admin_buatakunadd.this, "Cannot Sign in. Please check the form and try again.", Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}
