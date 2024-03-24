package com.example.upmobile.main_elements.models;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.upmobile.R;

public class SelectedItemViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView price;
    public ImageView image;
    public SelectedItemViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.Name);
        price = itemView.findViewById(R.id.price);
        image = itemView.findViewById(R.id.imge);
    }
}
