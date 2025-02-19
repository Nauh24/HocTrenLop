package com.nauh.demo.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nauh.demo.R;

public class TechnologyAdapter extends ArrayAdapter<Technology> {
    private Context context;
    private Technology[] mList;
    public TechnologyAdapter(@NonNull Context context, Technology[] mList) {
        super(context, R.layout.tech_item, mList);
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint({"ViewHolder", "InflateParams"}) View view = inflater.inflate(R.layout.tech_item, null, true);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView img = view.findViewById(R.id.img);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView tname = view.findViewById(R.id.tname);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView tsub = view.findViewById(R.id.tsub);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView tdesc = view.findViewById(R.id.tdesc);

        Technology technology = mList[position];
        img.setImageResource(technology.getImg());
        tname.setText(technology.getName());
        tsub.setText(technology.getSub());
        tdesc.setText(technology.getDescribe());

        return view;
    }

    public Technology getItem(int position) {
        return mList[position];
    }
}
