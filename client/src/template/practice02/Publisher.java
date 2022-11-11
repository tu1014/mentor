package practice02;

import java.io.*;

public class Publisher {


    private String name;
    private String address;

    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public byte[] getBytes() throws IOException {

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(name);
        dos.writeUTF(address);

        return buf.toByteArray();

    }

    public static Publisher bytesToPublisher(byte[] bytes) throws IOException {

        DataInputStream dis = new DataInputStream(
                new ByteArrayInputStream(bytes)
        );

        String name = dis.readUTF();
        String address = dis.readUTF();

        return new Publisher(name, address);



    }


}
