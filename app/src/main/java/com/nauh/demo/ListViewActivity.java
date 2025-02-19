package com.nauh.demo;

import android.os.Bundle;
import android.widget.ListView;

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

        // An cut
    }

    private void initData() {
        Integer[] imgs = {R.drawable.logo_android, R.drawable.logo_ios, R.drawable.logo_windows, R.drawable.logo_linux};
        String[] names = {"Android", "iOS", "Windows", "Linux"};
        String[] subs = {"Sub Android", "Sub iOS", "Sub Windows", "Sub Linux"};
        String[] descs = {"Android is a mobile operating system\n based on a modified version of the\n Linux kernel and other open source software",
                "iOS is a mobile operating system\n created and developed by Apple Inc.\nexclusively for its hardware.",
                "Windows is a group of several\n proprietary graphical operating\n system families",
                "Linux is a family of open-source\n Unix-like operating systems based\n on the Linux kernel"};
        list = new Technology[imgs.length];
        for(int i = 0; i < list.length; i++) {
            list[i] = new Technology(imgs[i], names[i], subs[i], descs[i]);
        }
    }
}