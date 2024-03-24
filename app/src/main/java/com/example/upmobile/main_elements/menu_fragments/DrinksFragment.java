package com.example.upmobile.main_elements.menu_fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.upmobile.ConnDB;
import com.example.upmobile.databinding.FoodFragmentBinding;
import com.example.upmobile.interfaces.IItemClickListener;
import com.example.upmobile.interfaces.IMenuClickListener;
import com.example.upmobile.main_elements.MenuAdapter;
import com.example.upmobile.main_elements.SelectedItemAdapter;
import com.example.upmobile.main_elements.models.MenuViewModel;
import com.example.upmobile.databinding.DrinksFragmentBinding;
import com.example.upmobile.main_elements.models.SelectedItemViewModel;

import java.util.ArrayList;

public class DrinksFragment extends Fragment implements IItemClickListener, IMenuClickListener {

    DrinksFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DrinksFragmentBinding.inflate(inflater, container, false);
        loadDrinks();
        return binding.getRoot();
    }
    @Override
    public void onItemClickFunc() {
        loadDrinks();
    }

    @Override
    public void onMenuClickFunc(MenuViewModel model) {
        ArrayList<SelectedItemViewModel> sic = new ArrayList<>();
        sic.add(new SelectedItemViewModel(model.name, model.price, model.image));

        binding.drinksList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        binding.drinksList.setAdapter(new SelectedItemAdapter(sic,this));
    }

    public void loadDrinks(){
        ArrayList<MenuViewModel> drinks = new ArrayList<>();
        ConnDB connDb = new ConnDB(getContext());
        SQLiteDatabase db = connDb.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM drinks", null);

        if (cursor.moveToFirst()) {
            do {
                int ni =cursor.getColumnIndex("name");
                int pi =cursor.getColumnIndex("price");
                int ii =cursor.getColumnIndex("img");
                String name = cursor.getString(ni);
                String price = cursor.getString(pi);
                String img = cursor.getString(ii);
                drinks.add(new MenuViewModel(name,price,img));
            } while (cursor.moveToNext());
            cursor.close();

            binding.drinksList.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            binding.drinksList.setAdapter(new MenuAdapter(drinks,this));
        }
    }
}
