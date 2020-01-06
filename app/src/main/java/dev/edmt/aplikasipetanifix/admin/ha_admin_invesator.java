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
import dev.edmt.aplikasipetanifix.admin.adapter.ha_investor_rv;
import dev.edmt.aplikasipetanifix.admin.model.ha_i_ku;

public class ha_admin_invesator extends AppCompatActivity implements ha_investor_rv.FirebaseDataListener {

    /**
     * Mendefinisikan variable yang akan dipakai
     */
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ha_i_ku> daftarBsSaldoInvestor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Mengeset layout
         */
        setContentView(R.layout.ha_activity_investor_read);

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
        database.child("INVESTOR").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                /**
                 * Saat ada data baru, masukkan datanya ke ArrayList
                 */
                daftarBsSaldoInvestor = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /**
                     * Mapping data pada DataSnapshot ke dalam object ha_i_ku
                     * Dan juga menyimpan primary key pada object ha_i_ku
                     * untuk keperluan Edit dan Delete data
                     */
                    ha_i_ku ha_i_ku = noteDataSnapshot.getValue(ha_i_ku.class);
                    ha_i_ku.setKey(noteDataSnapshot.getKey());

                    /**
                     * Menambahkan object ha_i_ku yang sudah dimapping
                     * ke dalam ArrayList
                     */
                    daftarBsSaldoInvestor.add(ha_i_ku);
                }

                /**
                 * Inisialisasi adapter dan data barang dalam bentuk ArrayList
                 * dan mengeset Adapter ke dalam RecyclerView
                 */
                adapter = new ha_investor_rv(daftarBsSaldoInvestor, ha_admin_invesator.this);
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
        return new Intent(activity, ha_admin_invesator.class);
    }

    @Override
    public void onDeleteData(ha_i_ku ha_i_ku, final int position) {
        /**
         * Kode ini akan dipanggil ketika method onDeleteData
         * dipanggil dari adapter lewat interface.
         * Yang kemudian akan mendelete data di Firebase Realtime DB
         * berdasarkan key ha_i_ku.
         * Jika sukses akan memunculkan SnackBar
         */
        if(database!=null){
            database.child("INVESTOR").child(ha_i_ku.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(ha_admin_invesator.this,"success delete", Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    public void tambahsaldoinvestor(View view) {
        Intent intent = new Intent(this,ha_admin_c_Investor.class);
        startActivity(intent);
    }
}
