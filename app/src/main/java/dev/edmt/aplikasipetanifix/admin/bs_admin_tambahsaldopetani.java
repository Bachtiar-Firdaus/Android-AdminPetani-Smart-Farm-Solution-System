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
import dev.edmt.aplikasipetanifix.admin.adapter.bs_petani_rv;
import dev.edmt.aplikasipetanifix.admin.model.cs_ku;


public class bs_admin_tambahsaldopetani extends AppCompatActivity implements bs_petani_rv.FirebaseDataListener {

    /**
     * Mendefinisikan variable yang akan dipakai
     */
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<cs_ku> daftarBsSaldo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Mengeset layout
         */
        setContentView(R.layout.bs_activity_saldo_read);

        /**
         * Inisialisasi RecyclerView & komponennya
         */

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);


        /**
         * Inisialisasi dan mengambil Firebase Database Reference
         */
        database = FirebaseDatabase.getInstance().getReference();

        /**
         * Mengambil data barang dari Firebase Realtime DB
         */
        database.child("PETANI").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                /**
                 * Saat ada data baru, masukkan datanya ke ArrayList
                 */
                daftarBsSaldo = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /**
                     * Mapping data pada DataSnapshot ke dalam object cs_ku
                     * Dan juga menyimpan primary key pada object cs_ku
                     * untuk keperluan Edit dan Delete data
                     */
                    cs_ku cs_ku = noteDataSnapshot.getValue(cs_ku.class);
                    cs_ku.setKey(noteDataSnapshot.getKey());

                    /**
                     * Menambahkan object cs_ku yang sudah dimapping
                     * ke dalam ArrayList
                     */
                    daftarBsSaldo.add(cs_ku);
                }

                /**
                 * Inisialisasi adapter dan data barang dalam bentuk ArrayList
                 * dan mengeset Adapter ke dalam RecyclerView
                 */
                adapter = new bs_petani_rv(daftarBsSaldo, bs_admin_tambahsaldopetani.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                /**
                 * Kode ini akan dipanggil ketika ada error dan
                 * pengambilan data gagal dan memprint error nya
                 * ke LogCat
                 */
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, bs_admin_tambahsaldopetani.class);
    }

    @Override
    public void onDeleteData(cs_ku cs_ku, final int position) {
        /**
         * Kode ini akan dipanggil ketika method onDeleteData
         * dipanggil dari adapter lewat interface.
         * Yang kemudian akan mendelete data di Firebase Realtime DB
         * berdasarkan key cs_ku.
         * Jika sukses akan memunculkan SnackBar
         */
        if(database!=null){
            database.child("PETANI").child(cs_ku.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(bs_admin_tambahsaldopetani.this,"success delete", Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    public void tambahsaldopetani(View view) {
        Intent intent = new Intent(this,bs_Create_saldo_Petani.class);
        startActivity(intent);
    }
}
