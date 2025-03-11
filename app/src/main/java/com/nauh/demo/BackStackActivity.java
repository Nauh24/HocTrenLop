package com.nauh.demo;

import android.os.Bundle;
import android.view.View;

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
import com.nauh.demo.model.FragmentC;

public class BackStackActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.back_stack);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fragmentManager = getSupportFragmentManager();
    }

    private void add(Fragment fragment, String tag, String name) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, fragment, tag);
        fragmentTransaction.addToBackStack(name);
        fragmentTransaction.commit();
    }

    public void addA(View view) {
        add(new FragmentA(), "A", "Fragment A");
    }

    public void addB(View view) {
        add(new FragmentB(), "B", "Fragment B");
    }

    public void addC(View view) {
        add(new FragmentC(), "C", "Fragment C");
    }

    private void remove(Fragment fragment, String tag) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = fragmentManager.findFragmentByTag(tag);
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    public void removeA(View view) {
        remove(new FragmentA(), "A");
    }

    public void removeB(View view) {
        remove(new FragmentB(), "B");
    }

    public void removeC(View view) {
        remove(new FragmentC(), "C");
    }

    public void back(View view) {
        fragmentManager.popBackStack();
    }

    public void popA(View view) {
        fragmentManager.popBackStack("Fragment A", 0);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }
}