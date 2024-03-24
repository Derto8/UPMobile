package com.example.upmobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.example.upmobile.databinding.MainItemScreenBinding;

import java.util.HashMap;
import java.util.Map;

public class ItemScreen extends AppCompatActivity {
    MainItemScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = MainItemScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        if (intent != null) {
            binding.Name.setText(intent.getStringExtra("name"));
            binding.Price.setText(intent.getStringExtra("price"));
            Glide.with(this).load(intent.getStringExtra("img")).into(binding.image);
        }
        binding.back.setOnClickListener(v -> {
            startActivity(new Intent(ItemScreen.this, MainScreen.class));
            finish();
        });
    }
}
