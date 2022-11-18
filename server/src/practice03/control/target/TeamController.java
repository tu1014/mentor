package practice03.control.target;

import practice03.persistence.Database;
import practice03.protocol.BodyMaker;
import practice03.protocol.Header;
import practice03.protocol.MySerializableClass;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class TeamController {

    public void handleRead(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException{

        switch(header.option) {

            case Header.FIND_TEAM_ALL:
                findAll(outputStream);
                break;
        }

    }

    public void findAll(DataOutputStream outputStream) throws IOException {

        List<MySerializableClass> list = Database.getALlTeam();

        BodyMaker bodyMaker = new BodyMaker();
        bodyMaker.add(list);

        byte[] resBody = bodyMaker.getBody();

        Header resHeader = new Header(
                Header.TYPE_RES,
                Header.ACTION_READ,
                Header.TARGET_TEAM,
                Header.FIND_TEAM_ALL,
                Header.STATUS_SUCCESS,
                resBody.length
        );

        outputStream.write(resHeader.getBytes());
        outputStream.write(resBody);

    }


}
