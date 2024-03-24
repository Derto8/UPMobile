package com.example.upmobile.main_elements;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.example.upmobile.ItemScreen;
import com.example.upmobile.R;
import com.example.upmobile.interfaces.IItemClickListener;
import com.example.upmobile.main_elements.models.SelectedItemViewHolder;
import com.example.upmobile.main_elements.models.SelectedItemViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class SelectedItemAdapter extends RecyclerView.Adapter<SelectedItemViewHolder> {

    private ArrayList<SelectedItemViewModel> sItem = new ArrayList<>();
    private IItemClickListener _iItemClickListener;
    public int count;

    public SelectedItemAdapter(ArrayList<SelectedItemViewModel> item,IItemClickListener iItemClickListener) {
        sItem = item;
        _iItemClickListener = iItemClickListener;
    }

    @NonNull
    @Override
    public SelectedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_selected_item_screen,parent,false);
        return new SelectedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedItemViewHolder holder, int position) {
        SelectedItemViewModel item = sItem.get(position);
        if(count > 0)
        {
            Integer.parseInt(item.name);
            holder.name.setText(count*Integer.parseInt(item.name));
        }
        else holder.name.setText(item.name);
        holder.price.setText(item.price);
        Glide.with(holder.itemView.getContext()).load(item.image).into(holder.image);
        holder.itemView.findViewById(R.id.backItem).setOnClickListener(v -> {
            if (_iItemClickListener != null) {
                _iItemClickListener.onItemClickFunc();
            }
        });
        holder.itemView.findViewById(R.id.btnAddToCart).setOnClickListener(v -> {
            if (_iItemClickListener != null) {
                holder.itemView.findViewById(R.id.SelectedItem).setVisibility(View.GONE);
                holder.itemView.findViewById(R.id.AddToCart).setVisibility(View.VISIBLE);
            }
        });
        holder.itemView.findViewById(R.id.btnGoToCart).setOnClickListener(v -> {
            if (_iItemClickListener != null) {
                _iItemClickListener.onItemClickFunc();
            }
        });
        holder.itemView.findViewById(R.id.plus).setOnClickListener(v -> {
            if (_iItemClickListener != null) {
                count--;
            }
        });
        holder.itemView.findViewById(R.id.minus).setOnClickListener(v -> {
            if (_iItemClickListener != null) {
                count++;
            }
        });
        holder.itemView.findViewById(R.id.btnContinueShop).setOnClickListener(v -> {
            if (_iItemClickListener != null) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, ItemScreen.class);

                intent.putExtra("name", item.name);
                intent.putExtra("price", item.price);
                intent.putExtra("img", item.image);

                context.startActivity(intent);
            }
        });
        holder.itemView.findViewById(R.id.more).setOnClickListener(v -> {
            if (_iItemClickListener != null) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, ItemScreen.class);

                intent.putExtra("name", item.name);
                intent.putExtra("price", item.price);
                intent.putExtra("img", item.image);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sItem.size();
    }
}
