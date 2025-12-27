package com.codingstudio.bookcatalog.asset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstudio.bookcatalog.R;
import com.codingstudio.bookcatalog.model.WriterBook;

import java.util.List;

public class WriterStoryAdapter
        extends RecyclerView.Adapter<WriterStoryAdapter.ViewHolder> {

    Context context;
    List<WriterBook> books;

    public WriterStoryAdapter(Context context, List<WriterBook> books) {
        this.context = context;
        this.books = books;
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
        WriterBook book = books.get(position);

        holder.imgCover.setImageResource(book.cover);
        holder.txtCategory.setText(
                book.category + (book.published ? " • Published" : " • Draft")
        );

        holder.txtCategory.setTextColor(
                book.published ? 0xFF4CAF50 : 0xFFFF9800
        );

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context,
                    book.published ? "Edit Buku" : "Lanjutkan Draft",
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCover;
        TextView txtCategory;

        ViewHolder(View itemView) {
            super(itemView);
            imgCover = itemView.findViewById(R.id.imgCover);
            txtCategory = itemView.findViewById(R.id.txtCategory);
        }
    }
}

