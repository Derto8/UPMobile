package com.example.upmobile.main_elements.menu_fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.upmobile.ConnDB;
import com.example.upmobile.interfaces.IItemClickListener;
import com.example.upmobile.interfaces.IMenuClickListener;
import com.example.upmobile.main_elements.MainAdapter;
import com.example.upmobile.main_elements.MenuAdapter;
import com.example.upmobile.main_elements.models.MenuViewModel;
import com.example.upmobile.databinding.FoodFragmentBinding;

import java.util.ArrayList;

public class FoodFragment extends Fragment implements IItemClickListener, IMenuClickListener {

    FoodFragmentBinding binding;
    SimpleCursorAdapter userAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FoodFragmentBinding.inflate(inflater, container, false);
        loadFood();
        return binding.getRoot();
    }

    @Override
    public void onItemClickFunc() {
        loadFood();
    }

    @Override
    public void onMenuClickFunc(MenuViewModel model) {
//        ArrayList<SelectedItemClass> sic = new ArrayList<>();
//        sic.add(new SelectedItemClass(food.name, food.price, food.img));
//
//        binding.foodsList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
//        binding.foodsList.setAdapter(new SelectedItemAdapter(sic,this));
    }

    private void loadFood(){
        ArrayList<MenuViewModel> foods = new ArrayList<>();
        ConnDB dbHelper = new ConnDB(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM foods", null);

        if (cursor.moveToFirst()) {
            do {
                int ni =cursor.getColumnIndex("name");
                int pi =cursor.getColumnIndex("price");
                int ii =cursor.getColumnIndex("img");
                String name = cursor.getString(ni);
                String price = cursor.getString(pi);
                String img = cursor.getString(ii);
                foods.add(new MenuViewModel(name, price, img));
            } while (cursor.moveToNext());
            cursor.close();

            // Set up the RecyclerView
            binding.foodsList.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            binding.foodsList.setAdapter(new MenuAdapter(foods,this));
        }
    }
}
