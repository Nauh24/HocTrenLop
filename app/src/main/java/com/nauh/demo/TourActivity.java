package com.nauh.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nauh.demo.model.Tour;
import com.nauh.demo.model.TourAdapter;
import com.nauh.demo.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;


public class TourActivity extends AppCompatActivity implements TourAdapter.TourItemListener,
        SearchView.OnQueryTextListener{
    private Spinner sp;
    private RecyclerView recyclerView;
    private TourAdapter adapter;
    private EditText eName,eTime;
    private Button btAdd,btUpdate;
    private SearchView searchView;
    private int pcurr;
    private int[] imgs={R.drawable.car,R.drawable.motobike,R.drawable.airplane};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_activity);
        initView();
        adapter=new TourAdapter(this);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i=sp.getSelectedItem().toString();
                String name=eName.getText().toString();
                String time=eTime.getText().toString();
                int img;
                try{
                    img=imgs[Integer.parseInt(i)];
                    if(!name.isEmpty()) {
                        adapter.add(new Tour(img, name, time));
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour tour=new Tour();
                String i=sp.getSelectedItem().toString();
                String name=eName.getText().toString();
                String time=eTime.getText().toString();
                int img=R.drawable.car;
                try{
                    img=imgs[Integer.parseInt(i)];
                    tour.setImg(img);
                    tour.setName(name);
                    tour.setTime(time);

                    adapter.update(pcurr,tour);
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        sp=findViewById(R.id.img);
        SpinnerAdapter adapter=new SpinnerAdapter(this,imgs);
        sp.setAdapter(adapter);
        recyclerView=findViewById(R.id.recycleView);
        eName=findViewById(R.id.name);
        eTime=findViewById(R.id.time);
        btAdd=findViewById(R.id.btAdd);
        btUpdate=findViewById(R.id.btUpdate);
        btUpdate.setEnabled(false);
        searchView=findViewById(R.id.search);
    }

    @Override
    public void onItemClick(View view, int position) {
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
        pcurr=position;
        Tour tour=adapter.getItem(position);
        int img= tour.getImg();
        int p=0;
        for (int i = 0; i < imgs.length; i++) {
            if (img == imgs[i]) {
                p = i;
                break;
            }
        }
        sp.setSelection(p);
        eName.setText(tour.getName());
        eTime.setText(tour.getTime());

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }

    private void filter(String s) {
        List<Tour> filterlist=new ArrayList<>();
        for(Tour i:adapter.getBackup()){
            if(i.getName().toLowerCase().contains(s.toLowerCase())){
                filterlist.add(i);
            }
        }
        if(filterlist.isEmpty()){
            Toast.makeText(this,"No data found",Toast.LENGTH_SHORT).show();
        }
        adapter.filterList(filterlist);
    }
}