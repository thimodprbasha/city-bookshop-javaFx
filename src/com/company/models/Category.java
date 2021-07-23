package com.company.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {
    private String categoryName;
    private List<Book> books;

    public Category(String categoryName){
        books = new ArrayList<>();
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Book getBookByIndex(int index){
        return this.books.get(index);
    }

    public Book findByName(String name){
        for (Book book:books) {
            if (book.getBookName().equals(name)){
                return book;
            }
        }
        return null;
    }

    public Book findByPrice(int price){
        for (Book book:books) {
            if (book.getPrice() == price){
                return book;
            }
        }
        return null;
    }

    public Book findByAuthor(String author){
        for (Book book:books) {
            if (book.getAuthor().equals(author)){
                return book;
            }
        }
        return null;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void addBooks(Book book) {
        this.books.add(book);
    }

    @Override
    public String toString() {
        return "Category{" +
                "Category Name='" + categoryName + '\'' +
                ", Books=" + books +
                '}';
    }
}
