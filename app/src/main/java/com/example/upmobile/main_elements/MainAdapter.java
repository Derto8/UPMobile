package com.example.upmobile.main_elements;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.upmobile.main_elements.menu_fragments.DrinkFragment;
import com.example.upmobile.main_elements.menu_fragments.FoodFragment;
import com.example.upmobile.main_elements.menu_fragments.SauceFragment;
import com.example.upmobile.main_elements.menu_fragments.SnackFragment;

public class MainAdapter extends FragmentStateAdapter {
    public MainAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new FoodFragment();
            case 1: return new DrinkFragment();
            case 2: return  new SnackFragment();
            case 3: return new SauceFragment();
            default: return new FoodFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }
}
