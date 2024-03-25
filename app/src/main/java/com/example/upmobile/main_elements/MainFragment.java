package com.example.upmobile.main_elements;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.upmobile.ConnDB;
import com.example.upmobile.databinding.MainScreenFragmetBinding;
import com.example.upmobile.interfaces.IItemClickListener;
import com.example.upmobile.interfaces.IMenuClickListener;
import com.example.upmobile.main_elements.models.MenuViewModel;
import com.example.upmobile.main_elements.models.SelectedItemViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainFragment extends Fragment implements IMenuClickListener, IItemClickListener {
    MainScreenFragmetBinding binding;
    MainAdapter mainAdapter;
    ConnDB connDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MainScreenFragmetBinding.inflate(inflater, container, false);
        mainAdapter = new MainAdapter(this);
        binding.viewPager.setAdapter(mainAdapter);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tabLayout.getTabAt(position).select();
            }
        });
        binding.clickSearch.setOnClickListener(v -> {
            binding.Lmeal.setVisibility(View.VISIBLE);
            binding.Ladress.setVisibility(View.GONE);
            binding.adresSearch.setText("");
            binding.mealSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length() > 0){
                        binding.blockRes.setVisibility(View.VISIBLE);
                        binding.blockMenu.setVisibility(View.GONE);
                        getItems();
                    }
                    else {
                        binding.blockRes.setVisibility(View.GONE);
                        binding.blockMenu.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        });

        binding.clickonAdress.setOnClickListener(c -> {
            Matcher match = Pattern.compile("^([а-яА-ЯёЁa-zA-Z0-9 ,.-]+,){2}[а-яА-ЯёЁa-zA-Z0-9 ,.-]+$").matcher(binding.adresSearch.getText().toString());
            if(match.matches())
                binding.adresSearch.setTextColor(Color.GREEN);
            else binding.adresSearch.setTextColor(Color.RED);
        });

        binding.clickAdres.setOnClickListener(v -> {
            binding.Lmeal.setVisibility(View.GONE);
            binding.Ladress.setVisibility(View.VISIBLE);
            binding.blockRes.setVisibility(View.GONE);
            binding.blockMenu.setVisibility(View.VISIBLE);
            binding.mealSearch.setText("");
        });
        binding.cleartext.setOnClickListener(v -> {
            binding.mealSearch.setText("");
        });


        return binding.getRoot();
    }

    @Override
    public void onMenuClickFunc(MenuViewModel model) {
        ArrayList<SelectedItemViewModel> sic = new ArrayList<>();
        sic.add(new SelectedItemViewModel(model.name, model.price, model.image));

        binding.searchResult.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        binding.searchResult.setAdapter(new SelectedItemAdapter(sic, this));
    }

    @Override
    public void onItemClickFunc() {
        getItems();
    }

    private void getItems(){
        ArrayList<MenuViewModel> search = new ArrayList<>();
        connDB = new ConnDB(getContext());
        SQLiteDatabase db = connDB.getReadableDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM foods WHERE name LIKE '%"+binding.mealSearch.getText().toString()+"%' " +
                "UNION " +
                "SELECT * FROM drinks WHERE name LIKE '%"+binding.mealSearch.getText().toString()+"%' " +
                "UNION " +
                "SELECT * FROM sauce WHERE name LIKE '%"+binding.mealSearch.getText().toString()+"%' " +
                "UNION " +
                "SELECT * FROM snacks WHERE name LIKE '%"+binding.mealSearch.getText().toString()+"%' ", null);

        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    int ni =cursor.getColumnIndex("name");
                    int pi =cursor.getColumnIndex("price");
                    int ii =cursor.getColumnIndex("img");
                    String name = cursor.getString(ni);
                    String price = cursor.getString(pi);
                    String img = cursor.getString(ii);
                    search.add(new MenuViewModel(name,price,img));
                } while (cursor.moveToNext());
            }
        }

        cursor.close();
        binding.searchResult.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        binding.searchResult.setAdapter(new MenuAdapter(search,this));
    }
}
