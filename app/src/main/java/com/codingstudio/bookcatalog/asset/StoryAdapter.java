package com.codingstudio.bookcatalog.asset;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstudio.bookcatalog.DetailActivity;
import com.codingstudio.bookcatalog.R;

import java.util.List;

public class StoryAdapter  extends RecyclerView.Adapter<StoryAdapter.ViewHolder>{
    Context context;
    List<Integer> images;

    public StoryAdapter(Context context, List<Integer> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgCover.setImageResource(images.get(position));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCover;

        ViewHolder(View itemView) {
            super(itemView);
            imgCover = itemView.findViewById(R.id.imgCover);
        }
    }
}
