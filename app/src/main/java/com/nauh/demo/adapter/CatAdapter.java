package com.nauh.demo.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nauh.demo.R;
import com.nauh.demo.ViewPagerTablayoutCRUDActivity;
import com.nauh.demo.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    private Context context;
    private List<com.nauh.demo.model.Cat> listBackup;
    private List<com.nauh.demo.model.Cat> mList;

    private CatItemListener mCatItem;
    private ViewPagerTablayoutCRUDActivity viewPagerTablayoutCRUDActivity;

    public CatAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
        listBackup = new ArrayList<>();
    }
    public CatAdapter(Context context, ViewPagerTablayoutCRUDActivity viewPagerTablayoutCRUDActivity) {
        this.context = context;
        this.viewPagerTablayoutCRUDActivity = viewPagerTablayoutCRUDActivity;
        mList = new ArrayList<>();
        listBackup = new ArrayList<>();
    }

    public List<com.nauh.demo.model.Cat> getBackup() {
        return listBackup;
    }

    public void setmList(List<com.nauh.demo.model.Cat> mList) {
        this.mList = mList;
    }

    public void filterList(List<com.nauh.demo.model.Cat> filterlist) {
        mList = filterlist;
        notifyDataSetChanged();
    }

    public void setClickListener(CatItemListener mCatItem) {
        this.mCatItem = mCatItem;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new CatViewHolder(view);
    }

    public com.nauh.demo.model.Cat getItem(int position) {
        return mList.get(position);
    }

    public List<Cat> getList() {
        return mList;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int positon) {
        com.nauh.demo.model.Cat cat = mList.get(positon);
        if (cat == null)
            return;
        holder.img.setImageResource(cat.getImg());
        holder.tvName.setText(cat.getName());
        holder.tvDescribe.setText(cat.getDescribe());
        holder.tvPrice.setText(cat.getPrice() + "");
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac chan muon xoa " + cat.getName() + " nay khong?");
                builder.setIcon(R.drawable.ic_remove);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listBackup.remove(positon);
                        mList.remove(positon);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    public void add(com.nauh.demo.model.Cat c) {
        listBackup.add(c);
        mList.add(c);
        notifyDataSetChanged();
    }

    public void update(int position, com.nauh.demo.model.Cat cat) {
        listBackup.set(position, cat);
        mList.set(position, cat);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView tvName, tvDescribe, tvPrice;
        private Button btRemove;

        public CatViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            tvName = view.findViewById(R.id.txtName);
            tvDescribe = view.findViewById(R.id.txtDescribe);
            tvPrice = view.findViewById(R.id.txtPrice);
            btRemove = view.findViewById(R.id.btRemove);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mCatItem != null) {
                mCatItem.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface CatItemListener {
        void onItemClick(View view, int position);
    }
}
