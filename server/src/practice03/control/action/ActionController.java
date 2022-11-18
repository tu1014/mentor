package practice03.control.action;

import practice03.control.target.PlayerController;
import practice03.control.target.TeamController;
import practice03.protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ActionController {

    ReadController readController;
    PlayerController playerController;
    TeamController teamController;

    public ActionController() {

        playerController = new PlayerController();
        teamController = new TeamController();

        readController = new ReadController(playerController, teamController);
    }

    public boolean handleRequest(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {

        switch(header.action) {

            case Header.ACTION_QUIT:
                return false;

            case Header.ACTION_READ:
                readController.handleRead(header, bodyReader, outputStream);
                break;

            default:
                // BadRequest 알려주는 패킷 전송
                break;


        }

        return true;
    }


}
