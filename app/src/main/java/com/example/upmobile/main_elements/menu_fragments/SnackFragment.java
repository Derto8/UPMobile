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
import com.example.upmobile.interfaces.IItemClickListener;
import com.example.upmobile.interfaces.IMenuClickListener;
import com.example.upmobile.main_elements.MenuAdapter;
import com.example.upmobile.main_elements.SelectedItemAdapter;
import com.example.upmobile.main_elements.models.MenuViewModel;
import com.example.upmobile.databinding.SnacksFragmentBinding;
import com.example.upmobile.main_elements.models.SelectedItemViewModel;

import java.util.ArrayList;

public class SnackFragment extends Fragment implements IItemClickListener, IMenuClickListener {

    SnacksFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SnacksFragmentBinding.inflate(inflater, container, false);
        loadSnacks();
        return binding.getRoot();
    }
    @Override
    public void onItemClickFunc() {
        loadSnacks();
    }

    @Override
    public void onMenuClickFunc(MenuViewModel model) {
        ArrayList<SelectedItemViewModel> sic = new ArrayList<>();
        sic.add(new SelectedItemViewModel(model.name, model.price, model.image));

        binding.snackList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        binding.snackList.setAdapter(new SelectedItemAdapter(sic,this));
    }

    public void loadSnacks(){
        ArrayList<MenuViewModel> snacks = new ArrayList<>();
        ConnDB connDB = new ConnDB(getContext());
        SQLiteDatabase db = connDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM snacks", null);

        if (cursor.moveToFirst()) {
            do {
                int ni =cursor.getColumnIndex("name");
                int pi =cursor.getColumnIndex("price");
                int ii =cursor.getColumnIndex("img");
                String name = cursor.getString(ni);
                String price = cursor.getString(pi);
                String img = cursor.getString(ii);
                snacks.add(new MenuViewModel(name,price,img));
            } while (cursor.moveToNext());
            cursor.close();

            // Set up the RecyclerView
            binding.snackList.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            binding.snackList.setAdapter(new MenuAdapter(snacks,this));
        }
    }
}
