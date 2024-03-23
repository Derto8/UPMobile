package com.example.upmobile.main_elements;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainAdapter extends FragmentStateAdapter {
    public MainAdapter(@NonNull Fragment fragment) { super(fragment);}

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return null;
    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
