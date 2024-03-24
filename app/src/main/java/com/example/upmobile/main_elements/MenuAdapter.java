package com.example.upmobile.main_elements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.example.upmobile.R;
import com.example.upmobile.main_elements.models.MenuItemViewHolder;
import com.example.upmobile.main_elements.models.MenuViewModel;
import com.example.upmobile.interfaces.IMenuClickListener;

import java.util.ArrayList;


public class MenuAdapter extends RecyclerView.Adapter<MenuItemViewHolder> {

    ArrayList<MenuViewModel> _menuList = new ArrayList<>();
    IMenuClickListener _menuClickListener;
    public MenuAdapter(ArrayList<MenuViewModel> menuList, IMenuClickListener menuClickListener){
        _menuList = menuList;
        _menuClickListener = menuClickListener;
    }

    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_screen_items,parent,false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        MenuViewModel menu = _menuList.get(position);
        holder.name.setText(menu.name);
        holder.price.setText(menu.price);
        Glide.with(holder.itemView.getContext()).load(menu.image).into(holder.image);
        holder.itemView.setOnClickListener(c -> {
            if (_menuClickListener != null) {
                _menuClickListener.onMenuClickFunc(menu);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _menuList.size();
    }
}
