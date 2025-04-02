// CafeTabAdapter.java
package com.nauh.demo.model;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CafeTabAdapter extends FragmentStateAdapter {
    
    public CafeTabAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MokaFragment();
            case 1:
                return new RobustaFragment();
            case 2:
                return new CuliFragment();
            default:
                return new MokaFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Number of tabs
    }
}