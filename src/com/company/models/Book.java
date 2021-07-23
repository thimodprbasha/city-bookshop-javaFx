package com.company.models;

import java.io.Serializable;

public class Book implements Serializable {
    private String bookId;
    private String bookName;
    private  String author;
    private double price;

    public Book(String bookId, String bookName, String author, double price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "BookId='" + bookId + '\'' +
                ", Book Name='" + bookName + '\'' +
                ", Author='" + author + '\'' +
                ", Price=" + price +
                '}';
    }
}
