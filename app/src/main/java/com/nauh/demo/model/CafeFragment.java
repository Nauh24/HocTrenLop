// CafeFragment.java
package com.nauh.demo.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.nauh.demo.R;

public class CafeFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private CafeTabAdapter cafeTabAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habbits, container, false);
        
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
        
        cafeTabAdapter = new CafeTabAdapter(this);
        viewPager.setAdapter(cafeTabAdapter);
        
        // Connect TabLayout with ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("MOKA");
                        break;
                    case 1:
                        tab.setText("ROBUSTA");
                        break;
                    case 2:
                        tab.setText("CULI");
                        break;
                }
            }
        }).attach();
        
        return view;
    }
}