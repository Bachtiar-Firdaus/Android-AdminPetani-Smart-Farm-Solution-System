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
import dev.edmt.aplikasipetanifix.admin.adapter.lap_inv_rv;
import dev.edmt.aplikasipetanifix.admin.model.lap_inv_ku;


public class lap_admin_investor extends AppCompatActivity implements lap_inv_rv.FirebaseDataListener {

    /**
     * Mendefinisikan variable yang akan dipakai
     */
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<lap_inv_ku> daftarBsSaldo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Mengeset layout
         */
        setContentView(R.layout.lap_activity_saldo_read);

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
        database.child("INVESTOR-LAPORAN").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                /**
                 * Saat ada data baru, masukkan datanya ke ArrayList
                 */
                daftarBsSaldo = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /**
                     * Mapping data pada DataSnapshot ke dalam object lap_inv_ku
                     * Dan juga menyimpan primary key pada object lap_inv_ku
                     * untuk keperluan Edit dan Delete data
                     */
                    lap_inv_ku lap_inv_ku = noteDataSnapshot.getValue(lap_inv_ku.class);
                    lap_inv_ku.setKey(noteDataSnapshot.getKey());

                    /**
                     * Menambahkan object lap_inv_ku yang sudah dimapping
                     * ke dalam ArrayList
                     */
                    daftarBsSaldo.add(lap_inv_ku);
                }

                /**
                 * Inisialisasi adapter dan data barang dalam bentuk ArrayList
                 * dan mengeset Adapter ke dalam RecyclerView
                 */
                adapter = new lap_inv_rv(daftarBsSaldo, lap_admin_investor.this);
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
        return new Intent(activity, lap_admin_investor.class);
    }

    @Override
    public void onDeleteData(lap_inv_ku lap_inv_ku, final int position) {
        /**
         * Kode ini akan dipanggil ketika method onDeleteData
         * dipanggil dari adapter lewat interface.
         * Yang kemudian akan mendelete data di Firebase Realtime DB
         * berdasarkan key lap_inv_ku.
         * Jika sukses akan memunculkan SnackBar
         */
        if(database!=null){
            database.child("INVESTOR-LAPORAN").child(lap_inv_ku.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(lap_admin_investor.this,"success delete", Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    public void tambahsaldopetani(View view) {
        Intent intent = new Intent(this,lap_admin_c_investor.class);
        startActivity(intent);
    }
}
