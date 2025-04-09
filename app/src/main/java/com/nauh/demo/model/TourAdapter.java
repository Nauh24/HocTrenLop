package com.nauh.demo.model;

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

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private Context context;
    private List<Tour> listBackup;
    private List<Tour> mList;
    private TourItemListener mTourItem;

    public TourAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
        listBackup=new ArrayList<>();
    }
    public List<Tour> getBackup(){
        return listBackup;
    }
    public void setmList(List<Tour> mList) {
        this.mList = mList;
    }
    public void filterList(List<Tour> filterlist){
        mList=filterlist;
        notifyDataSetChanged();
    }
    public void setClickListener(TourItemListener mTourItem){
        this.mTourItem=mTourItem;
    }
    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.tour_item,parent,false);
        return new TourViewHolder(view);
    }
    public Tour getItem(int position){
        return mList.get(position);
    }
    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int positon){
        Tour tour=mList.get(positon);
        if (tour==null)
            return;
        holder.img.setImageResource(tour.getImg());
        holder.tvName.setText(tour.getName());
        holder.tvTime.setText(tour.getTime());
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac chan muon xoa "+tour.getName()+" nay khong?");
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
                AlertDialog dialog=builder.create();
                dialog.show();

            }
        });

    }
    public void add(Tour c){
        listBackup.add(c);
        mList.add(c);
        notifyDataSetChanged();
    }
    public void update(int position, Tour tour){
        listBackup.set(position,tour);
        mList.set(position,tour);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(mList!=null)
            return mList.size();
        return 0;
    }

    public class TourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView tvName,tvTime;
        private Button btRemove;
        public TourViewHolder(@NonNull View view) {
            super(view);
            img=view.findViewById(R.id.img);
            tvName=view.findViewById(R.id.txtName);
            tvTime=view.findViewById(R.id.txtTime);
            btRemove=view.findViewById(R.id.btRemove);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mTourItem!=null){
                mTourItem.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface TourItemListener{
        void onItemClick(View view,int position);
    }
}
