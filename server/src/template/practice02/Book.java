package template.practice02;

import java.io.*;

public class Book {

    private String name;
    private Publisher publisher;
    private int pageNum;
    private boolean isBorrowed;

    public Book(String name, Publisher publisher, int pageNum, boolean isBorrowed) {
        this.name = name;
        this.publisher = publisher;
        this.pageNum = pageNum;
        this.isBorrowed = isBorrowed;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", pageNum=" + pageNum +
                ", isBorrowed=" + isBorrowed +
                '}';
    }

    public byte[] getBytes() throws IOException {


        return null;

    }

    public static Book bytesToBook(byte[] bytes) throws IOException {

        return null;

    }








}
