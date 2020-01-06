package dev.edmt.aplikasipetanifix.admin;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dev.edmt.aplikasipetanifix.R;

public class admin_pagetwo extends Fragment {

    View view;

    private Button ubah_profil;
    public admin_pagetwo() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.admin_pagetwo, container, false);

    }




/*    private void ubah_profil() {
        ubah_profil = (Button) view.findViewById(R.id.ubahprofil);
        ubah_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), admin_ubah_profil.class);
                startActivity(intent);
            }
        });
    }*/



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view=view;
        /*ubah_profil();*/
    }
}
