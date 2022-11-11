package practice02;

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

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(name);

        byte[] publisherBytes = publisher.getBytes();
        dos.writeInt(publisherBytes.length);
        dos.write(publisherBytes);

        dos.writeInt(pageNum);
        dos.writeBoolean(isBorrowed);

        return buf.toByteArray();

    }

    public static Book bytesToBook(byte[] bytes) throws IOException {

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));

        String name = dis.readUTF();

        // publisher
        String publisherName = dis.readUTF();
        String publisherAddress = dis.readUTF();
        Publisher publisher = new Publisher(publisherName, publisherAddress);

        int pageNum = dis.readInt();
        boolean isBorrowed = dis.readBoolean();

        return new Book(name, publisher, pageNum, isBorrowed);

    }








}
