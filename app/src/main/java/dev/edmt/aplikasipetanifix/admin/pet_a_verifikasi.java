package dev.edmt.aplikasipetanifix.admin;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.List;

import dev.edmt.aplikasipetanifix.R;

/**
 * Created by Protek on 04/03/2018.
 */

public class pet_a_verifikasi extends ArrayAdapter<pet_m_verifikasi> {

    Activity activity;
    int resouse;
    List<pet_m_verifikasi>list;

    public pet_a_verifikasi(Activity activity, int resouse, List<pet_m_verifikasi> list) {
        super(activity, resouse, list);
        this.activity = activity;
        this.resouse = resouse;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(resouse, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.getImages);
        TextView name = (TextView)view.findViewById(R.id.getName);
        TextView email = (TextView)view.findViewById(R.id.getEmail);

        name.setText(list.get(position).getName());
        email.setText(list.get(position).getEmail());

        Glide.with(activity).load(list.get(position).getFilePath()).into(imageView);

        return view;

    }
}
