package com.codingstudio.bookcatalog.model;

public class WriterBook {
    public int cover;
    public String category;
    public boolean published;

    public WriterBook(int cover, String category, boolean published) {
        this.cover = cover;
        this.category = category;
        this.published = published;
    }
}
