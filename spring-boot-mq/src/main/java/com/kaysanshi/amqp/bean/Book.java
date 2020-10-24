package com.kaysanshi.amqp.bean;

/**
 * @Author kay三石
 * @date:2019/8/26
 */
public class Book {
    private String name;

    private String author;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
