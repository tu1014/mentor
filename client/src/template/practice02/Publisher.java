package template.practice02;

import java.io.IOException;

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

        return null;

    }

    public static Publisher bytesToPublisher(byte[] bytes) throws IOException {

        return null;

    }


}
