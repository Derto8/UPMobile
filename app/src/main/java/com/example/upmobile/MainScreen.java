package com.example.upmobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.upmobile.databinding.MainScreenBinding;
import com.example.upmobile.home_elements.HomeMain;

import java.util.HashMap;
import java.util.Map;

public class MainScreen extends AppCompatActivity {
    private MainScreenBinding binding;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = MainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(),new HomeMain()).commit();
        binding.bottomNav.setSelectedItemId(R.id.home);
        Map<Integer, Fragment> fragmentMap = new HashMap<>();
        //fragmentMap.put(R.id.home,new HomeMain());
        binding.bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = fragmentMap.get(item.getItemId());
            getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(), fragment).commit();

            return true;
        });
    }


}

