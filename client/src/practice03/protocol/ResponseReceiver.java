package practice03.protocol;

import practice03.persistence.Player;
import practice03.persistence.Team;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class ResponseReceiver {

    public void receiveOnePlayer(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.bodySize];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        Player player = Player.readPlayer(bodyReader);
        System.out.println(player);

    }

    public void receivePlayerList(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.bodySize];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        int size = bodyReader.readInt();

        for(int i=0; i<size; i++) {
            Player player = Player.readPlayer(bodyReader);
            System.out.println(player);
        }

    }

    public void receiveTeamList(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.bodySize];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        int size = bodyReader.readInt();

        for(int i=0; i<size; i++) {
            Team team = Team.readTeam(bodyReader);
            System.out.println(team);
        }

    }


}
