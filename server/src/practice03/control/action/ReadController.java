package practice03.control.action;

import practice03.control.target.PlayerController;
import practice03.control.target.TeamController;
import practice03.protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ReadController {

    private PlayerController playerController;
    private TeamController teamController;

    public ReadController(PlayerController playerController, TeamController teamController) {
        this.playerController = playerController;
        this.teamController = teamController;
    }


    public void handleRead(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {

        switch (header.target) {

            case Header.TARGET_PLAYER:
                playerController.handleRead(header, bodyReader, outputStream);
                break;

            case Header.TARGET_TEAM:
                teamController.handleRead(header, bodyReader, outputStream);
                break;

        }
    }


}
