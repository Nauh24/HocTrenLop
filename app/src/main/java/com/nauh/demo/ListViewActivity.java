package com.nauh.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nauh.demo.model.Technology;
import com.nauh.demo.model.TechnologyAdapter;

public class ListViewActivity extends AppCompatActivity {
    private ListView lv;
    TechnologyAdapter adapter;
    private Technology[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.list_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv = findViewById(R.id.lv);
        initData();
        adapter = new TechnologyAdapter(this, list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for(int i = 0; i < lv.getAdapter().getCount(); i++) {
                    lv.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                lv.getChildAt(position).setBackgroundColor(Color.YELLOW);
                Technology technology = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), technology.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        Integer[] imgs = {R.drawable.logo_android, R.drawable.logo_ios, R.drawable.logo_windows, R.drawable.logo_linux};
        String[] names = {"Android", "iOS", "Windows", "Linux"};
        String[] subs = {"Sub Android", "Sub iOS", "Sub Windows", "Sub Linux"};
        String[] descs = {"Android is a mobile operating system\n based on a modified version of the\n Linux kernel and other open source",
                "iOS is a mobile operating system\n created and developed by Apple Inc.\nexclusively for its hardware.",
                "Windows is a group of several\n proprietary graphical operating\n system families",
                "Linux is a family of open-source\n Unix-like operating systems based\n on the Linux kernel"};
        list = new Technology[imgs.length];
        for(int i = 0; i < list.length; i++) {
            list[i] = new Technology(imgs[i], names[i], subs[i], descs[i]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


}