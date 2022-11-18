package practice03.persistence;

import practice03.protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Team implements MySerializableClass {
    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public byte[] getBytes() throws IOException {

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(name);

        return buf.toByteArray();
    }

    public static Team readTeam(DataInputStream dis) throws IOException {
        String name = dis.readUTF();
        return new Team(name);
    }

}
