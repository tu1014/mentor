package template.practice02;

import java.io.IOException;

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

        return null;

    }

    public static Student bytesToStudent(byte[] bytes) throws IOException {

        return null;

    }






}
