package com.codingstudio.bookcatalog.model;

import com.codingstudio.bookcatalog.model.tipeData.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static final List<Book> bookList = new ArrayList<>();

    public static void add(Book book) {
        bookList.add(book);
    }

    public static List<Book> getAll() {
        return bookList;
    }

    public static Book getById(String id) {
        for (Book b : bookList) {
            if (b.id.equals(id)) return b;
        }
        return null;
    }

    public static void update(Book book) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).id.equals(book.id)) {
                bookList.set(i, book);
                break;
            }
        }
    }
}
