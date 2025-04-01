package com.nauh.demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nauh.demo.R;
import com.nauh.demo.ViewPagerTablayoutCRUDActivity;
import com.nauh.demo.adapter.CatAdapter;
import com.nauh.demo.adapter.CatAdapter.CatItemListener;
import com.nauh.demo.adapter.SpinnerAdapter;
import com.nauh.demo.model.Cat;

public class FragmentAdd extends Fragment implements CatItemListener {
    private CatAdapter adapter;
    private Spinner spinner;

    private EditText etName, etPrice, etDescribe;
    private Button btAdd, btUpdate;
    private RecyclerView recyclerView;
    private int[] imgs = {R.drawable.cat1, R.drawable.cat2, R.drawable.cat3,
            R.drawable.cat4, R.drawable.cat5, R.drawable.cat6};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new CatAdapter(getContext(), (ViewPagerTablayoutCRUDActivity) getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i = spinner.getSelectedItem().toString();
                int img;
                try {
                    img = imgs[Integer.parseInt(i)];
                    double price = Double.parseDouble(etPrice.getText().toString());
                    Cat cat = new Cat(img, etName.getText().toString(), etDescribe.getText().toString(), price);
                } catch (NumberFormatException e) {

                }
            }
        });
    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(getContext(), imgs);
        spinner.setAdapter(adapter);

        etName = view.findViewById(R.id.etName);
        etPrice = view.findViewById(R.id.etPrice);
        etDescribe = view.findViewById(R.id.etDescribe);
        btAdd = view.findViewById(R.id.btAdd);
        btUpdate = view.findViewById(R.id.btUpdate);
        btUpdate.setVisibility(View.INVISIBLE);

        recyclerView = view.findViewById(R.id.recyclerView);

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
