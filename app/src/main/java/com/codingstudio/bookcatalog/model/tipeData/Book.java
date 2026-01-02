package com.codingstudio.bookcatalog.model.tipeData;

public class Book {
    public String id;
    public String title;
    public String author;
    public String category;
    public String description;
    public String imagePath;
    public String status;
    public String imageUri;

    public Book(String id, String title, String author,
                String category, String description,
                String imagePath, String status) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.description = description;
        this.imagePath = imagePath;
        this.status = status;
    }
}
