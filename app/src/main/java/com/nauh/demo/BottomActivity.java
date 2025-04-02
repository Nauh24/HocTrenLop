package com.nauh.demo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nauh.demo.model.CafeFragment;
import com.nauh.demo.model.HomeFragment;
import com.nauh.demo.model.NotificationsFragment;
import com.nauh.demo.model.SearchFragment;

public class BottomActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_activity);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Set default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.viewPager, new HomeFragment())
                .commit();

        // Set up bottom navigation listener
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.notification) {
                selectedFragment = new NotificationsFragment();
            } else if (itemId == R.id.search) {
                selectedFragment = new SearchFragment();
            } else if (itemId == R.id.habbits) {
                selectedFragment = new CafeFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.viewPager, selectedFragment)
                        .commit();
                return true;
            }
            return false;
        });
    }
}