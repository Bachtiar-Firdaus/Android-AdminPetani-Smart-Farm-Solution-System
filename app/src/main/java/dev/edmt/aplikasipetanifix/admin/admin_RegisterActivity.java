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

public class admin_RegisterActivity extends AppCompatActivity {

    private EditText et_nik,et_nama,et_alamat,et_nohp,bbb,et_email,et_password;
    private Button klikdaftar;


    private Toolbar mToolbar;

    private DatabaseReference mDatabase;

    //ProgressDialog
    private ProgressDialog mRegProgress;

    //Firebase Auth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_register);



        mRegProgress = new ProgressDialog(this);



        // Firebase Auth

        mAuth = FirebaseAuth.getInstance();


        // Android Fields

        et_nik = (EditText) findViewById(R.id.et_nik);
        et_nama = (EditText) findViewById(R.id.et_nama);
        et_alamat = (EditText) findViewById(R.id.et_alamat);
        et_nohp = (EditText) findViewById(R.id.et_nohp);
        et_email = (EditText) findViewById(R.id.et_email);
        bbb = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        klikdaftar = (Button) findViewById(R.id.klikdaftar);


        klikdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String etnik = et_nik.getText().toString();
                String etnama = et_nama.getText().toString();
                String etalamat = et_alamat.getText().toString();
                String etnohp = et_nohp.getText().toString();
                String etemail = et_email.getText().toString();
                String bbb = et_email.getText().toString();
                String etpassword = et_password.getText().toString();

                if(!TextUtils.isEmpty(etnik) || !TextUtils.isEmpty(etnama) || !TextUtils.isEmpty(etalamat)|| !TextUtils.isEmpty(etnohp)|| !TextUtils.isEmpty(bbb)|| !TextUtils.isEmpty(etemail)|| !TextUtils.isEmpty(etpassword)){

                    mRegProgress.setTitle("Registering User");
                    mRegProgress.setMessage("Please wait while we create your account !");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();

                    register_user(etnik, etnama, etalamat,etnohp,bbb,etemail,etpassword);

                }



            }
        });


    }

    private void register_user(final String etnik, final String etnama, final String etalamat, final String etnohp,final String bbb, String etemail, String etpassword) {

        mAuth.createUserWithEmailAndPassword(etemail,etpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){


                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = current_user.getUid();

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("ADMIN").child(uid);

                    String device_token = FirebaseInstanceId.getInstance().getToken();

                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("alamat", etalamat);
                    userMap.put("device_token", device_token);
                    userMap.put("name", etnama);
                    userMap.put("nik", etnik);
                    userMap.put("nohp", etnohp);
                    userMap.put("email", bbb);

                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                mRegProgress.dismiss();

                                Intent mainIntent = new Intent(admin_RegisterActivity.this, dev.edmt.aplikasipetanifix.admin.admin_MainActivity.class);
                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainIntent);
                                finish();

                            }

                        }
                    });


                } else {

                    mRegProgress.hide();
                    Toast.makeText(admin_RegisterActivity.this, "Cannot Sign in. Please check the form and try again.", Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}
