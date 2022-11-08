package practice02;

import java.io.*;

public class Student {

    private String name;
    private String address;
    private int age;

    public Student(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

    public byte[] getBytes() throws IOException {

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(name);
        dos.writeUTF(address);
        dos.writeInt(age);

        return buf.toByteArray();

    }

    public static Student bytesToStudent(byte[] bytes) throws IOException {

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));

        String name = dis.readUTF();
        String address = dis.readUTF();
        int age = dis.readInt();

        return new Student(name, address, age);

    }






}
