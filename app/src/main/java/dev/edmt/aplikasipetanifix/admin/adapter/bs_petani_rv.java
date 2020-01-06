package dev.edmt.aplikasipetanifix.admin.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import dev.edmt.aplikasipetanifix.R;
import dev.edmt.aplikasipetanifix.admin.bs_Create_saldo_Petani;
import dev.edmt.aplikasipetanifix.admin.bs_Read_saldo_Petani_singgle;
import dev.edmt.aplikasipetanifix.admin.bs_admin_tambahsaldopetani;
import dev.edmt.aplikasipetanifix.admin.model.cs_ku;


/**
 * Created by Hafizh Herdi on 10/8/2017.
 */

public class bs_petani_rv extends RecyclerView.Adapter<bs_petani_rv.ViewHolder> {

    private ArrayList<cs_ku> daftarBsSaldo;
    private Context context;
    FirebaseDataListener listener;

    public bs_petani_rv(ArrayList<cs_ku> cs_kuses, Context ctx){
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarBsSaldo = cs_kuses;
        context = ctx;
        listener = (bs_admin_tambahsaldopetani)ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvTitle;
        CardView cvMain;

        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_saldo_petani);
            cvMain = (CardView) v.findViewById(R.id.cv_main);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saldo_petani, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */
        final String name = daftarBsSaldo.get(position).getemail();
        System.out.println("BARANG DATA one by one "+position+ daftarBsSaldo.size());
        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Read detail data
                 */
                context.startActivity(bs_Read_saldo_Petani_singgle.getActIntent((Activity) context).putExtra("data", daftarBsSaldo.get(position)));
            }
        });
        holder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Delete dan update data
                 */
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.bs_dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data);

                //apabila tombol edit diklik
                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                context.startActivity(bs_Create_saldo_Petani.getActIntent((Activity) context).putExtra("data", daftarBsSaldo.get(position)));
                            }
                        }
                );

                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada barang
         */
        return daftarBsSaldo.size();
    }

    public interface FirebaseDataListener{
        void onDeleteData(cs_ku cs_ku, int position);
    }
}
