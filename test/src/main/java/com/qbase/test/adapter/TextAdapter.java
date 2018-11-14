package com.qbase.test.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qbase.auxilibrary.util.CollectionUtil;
import com.qbase.test.R;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ItemViewHolder> {


    private List<String> mItems;

    public TextAdapter(List<String> mItems) {
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item, parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        String text = mItems.get(position);
        holder.name.setText(text);

        int current = position % 9;
        if (current == 0) {
            holder.name.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.Red));
        } else if (current == 1) {
            holder.name.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.Pink));
        } else if (current == 2) {
            holder.name.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.Purple));
        } else if (current == 3) {
            holder.name.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.Deep_purple));
        } else if (current == 4) {
            holder.name.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.Indigo));
        } else if (current == 5) {
            holder.name.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.Blue));
        } else if (current == 6) {
            holder.name.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.Light_Blue));
        } else if (current == 7) {
            holder.name.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.Cyan));
        } else if (current == 8) {
            holder.name.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.Teal));
        } else if (current == 9) {
            holder.name.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.Green));
        }
    }

    @Override
    public int getItemCount() {
        return CollectionUtil.isEmpty(mItems) ? 0 : mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public ItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_item_name);
        }
    }
}
