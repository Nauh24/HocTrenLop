package com.nauh.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nauh.demo.model.FragmentA;
import com.nauh.demo.model.FragmentB;

public class DynamicFragmentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btA, btB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dynamic_fragment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btA = findViewById(R.id.btA);
        btB = findViewById(R.id.btB);
        btA.setOnClickListener(this);
        btB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;

        if (v == btA) {
            fragment = new FragmentA();
            fragmentTransaction.add(R.id.frame, fragment);
        } else {
            fragment = new FragmentB();
            fragmentTransaction.add(R.id.frame, fragment);
        }
        fragmentTransaction.commit();
    }
}