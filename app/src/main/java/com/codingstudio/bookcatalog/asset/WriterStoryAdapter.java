package com.codingstudio.bookcatalog.asset;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstudio.bookcatalog.DetailActivity;
import com.codingstudio.bookcatalog.R;
import com.codingstudio.bookcatalog.model.tipeData.Book;

import java.util.List;

public class WriterStoryAdapter
        extends RecyclerView.Adapter<WriterStoryAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onClick(Book book);
    }

    Context context;
    List<Book> books;
    OnItemClickListener listener;

    public WriterStoryAdapter(Context context,
                              List<Book> books,
                              OnItemClickListener listener) {
        this.context = context;
        this.books = books;
        this.listener = listener;
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
        Book book = books.get(position);

        holder.txtCategory.setText(
                book.category + " • " + book.status.toUpperCase()
        );

        if (book.imagePath != null && !book.imagePath.isEmpty()) {
            holder.imgCover.setImageURI(Uri.parse(book.imagePath));
        } else {
            holder.imgCover.setImageResource(R.drawable.ic_launcher_background);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(book);
            }
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

