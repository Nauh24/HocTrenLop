package com.nauh.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nauh.demo.model.Cat;
import com.nauh.demo.model.CatAdapter;

import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements CatAdapter.CatItemListener {
    private RecyclerView rv;
    private CatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.recycler_view);
        rv = findViewById(R.id.rview);
        adapter = new CatAdapter(getList());
        adapter.setListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(adapter);
    }

    private List<Cat> getList() {
        List<Cat> list = List.of(
                new Cat(R.drawable.cat1, "Cute Cat"),
                new Cat(R.drawable.cat2, "Angry Cat"),
                new Cat(R.drawable.cat3, "Boring Cat"),
                new Cat(R.drawable.cat4, "Happy Cat"),
                new Cat(R.drawable.cat5, "Sad Cat"),
                new Cat(R.drawable.cat6, "Sleepy Cat")
        );

        return list;
    }

    @Override
    public void onClick(View view, int position) {
        Cat cat = getList().get(position);
        Toast.makeText(this, cat.getName(), Toast.LENGTH_SHORT).show();
    }
}