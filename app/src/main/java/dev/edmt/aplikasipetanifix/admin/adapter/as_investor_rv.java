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
import dev.edmt.aplikasipetanifix.admin.asCreate_saldo_Investor;
import dev.edmt.aplikasipetanifix.admin.asadmin_tambahsaldoinvesator;
import dev.edmt.aplikasipetanifix.admin.asRead_saldo_Investor_singgle;
import dev.edmt.aplikasipetanifix.admin.model.saldo;

/**
 * Created by Hafizh Herdi on 10/8/2017.
 */

public class as_investor_rv extends RecyclerView.Adapter<as_investor_rv.ViewHolder> {

    private ArrayList<saldo> daftarSaldoInvestor;
    private Context context;
    FirebaseDataListener listener;

    public as_investor_rv(ArrayList<saldo> saldos, Context ctx){
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarSaldoInvestor = saldos;
        context = ctx;
        listener = (asadmin_tambahsaldoinvesator)ctx;
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
            tvTitle = (TextView) v.findViewById(R.id.tv_saldo_investor);
            cvMain = (CardView) v.findViewById(R.id.cv_main);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_investor_petani, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */
        final String name = daftarSaldoInvestor.get(position).getemail();
        System.out.println("BARANG DATA one by one "+position+ daftarSaldoInvestor.size());
        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Read detail data
                 */
                context.startActivity(asRead_saldo_Investor_singgle.getActIntent((Activity) context).putExtra("data", daftarSaldoInvestor.get(position)));
            }
        });
        holder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Delete dan update data
                 */
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);


                //apabila tombol delete diklik
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                listener.onDeleteData(daftarSaldoInvestor.get(position), position);
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
        return daftarSaldoInvestor.size();
    }

    public interface FirebaseDataListener{
        void onDeleteData(saldo saldo, int position);
    }
}
