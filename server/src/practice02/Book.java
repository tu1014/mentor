package practice02;

import java.io.*;
import java.util.ArrayList;

public class Book {

    private String name;
    private String publisher;
    private int pageNum;
    private boolean isBorrowed;

    public Book(String name, String publisher, int pageNum, boolean isBorrowed) {
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

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(name);
        dos.writeUTF(publisher);
        dos.writeInt(pageNum);
        dos.writeBoolean(isBorrowed);

        return buf.toByteArray();

    }

    public static Book bytesToBook(byte[] bytes) throws IOException {

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));

        String name = dis.readUTF();
        String publisher = dis.readUTF();
        int pageNum = dis.readInt();
        boolean isBorrowed = dis.readBoolean();

        return new Book(name, publisher, pageNum, isBorrowed);

    }








}
