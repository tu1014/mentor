package practice03.persistence;

import practice03.protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Player implements MySerializableClass {

    private String name;
    private int age;

    private Team team;

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", team=" + team +
                '}';
    }

    public Player(String name, int age, Team team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public byte[] getBytes() throws IOException {

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(name);
        dos.writeInt(age);
        dos.write(team.getBytes());

        return buf.toByteArray();

    }

    public static Player readPlayer(DataInputStream dis) throws IOException {

        String name = dis.readUTF();
        int age = dis.readInt();
        Team team = Team.readTeam(dis);

        return new Player(name, age, team);

    }
}
