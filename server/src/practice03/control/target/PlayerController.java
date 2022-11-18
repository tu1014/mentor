package practice03.control.target;

import practice03.persistence.Database;
import practice03.persistence.Player;
import practice03.protocol.BodyMaker;
import practice03.protocol.Header;
import practice03.protocol.MySerializableClass;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class PlayerController {

    public void handleRead(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {

        switch(header.option) {

            case Header.OPTION_PLAYER_ALL:
                findAll(outputStream);
                break;

            case Header.OPTION_PLAYER_BY_ID:
                int id = bodyReader.readInt();
                findById(id, outputStream);
                break;

            case Header.OPTION_PLAYER_BY_NAME:
                String name = bodyReader.readUTF();
                findByName(name, outputStream);
                break;

        }
    }


    public void findAll(DataOutputStream outputStream) throws IOException {
        List<MySerializableClass> list = Database.getAllPlayer();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(list);

        byte[] resBody = bodyMaker.getBody();

        Header resHeader = new Header(
                Header.TYPE_RES,
                Header.ACTION_READ,
                Header.TARGET_PLAYER,
                Header.OPTION_PLAYER_ALL,
                Header.STATUS_SUCCESS,
                resBody.length
        );

        outputStream.write(resHeader.getBytes());
        outputStream.write(resBody);

    }

    public void findById(int id, DataOutputStream outputStream) throws IOException {

        Player player = Database.getPlayer(id);

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(player);

        byte[] resBody = bodyMaker.getBody();

        Header resHeader = new Header(
                Header.TYPE_RES,
                Header.ACTION_READ,
                Header.TARGET_PLAYER,
                Header.OPTION_PLAYER_BY_ID,
                Header.STATUS_SUCCESS,
                resBody.length
        );

        outputStream.write(resHeader.getBytes());
        outputStream.write(resBody);


    }

    public void findByName(String name, DataOutputStream outputStream) throws IOException{

        Player player = Database.getPlayer(name);

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(player);

        byte[] resBody = bodyMaker.getBody();

        Header resHeader = new Header(
                Header.TYPE_RES,
                Header.ACTION_READ,
                Header.TARGET_PLAYER,
                Header.OPTION_PLAYER_BY_ID,
                Header.STATUS_SUCCESS,
                resBody.length
        );

        outputStream.write(resHeader.getBytes());
        outputStream.write(resBody);

    }


}
