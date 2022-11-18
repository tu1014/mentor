package practice03.control;

import practice03.protocol.ResponseReceiver;
import practice03.protocol.RequestSender;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Controller {

    public static final int FIND_PLAYER_BY_ID = 1;
    public static final int FIND_PLAYER_BY_NAME = 2;
    public static final int FIND_ALL_PLAYER = 3;
    public static final int FIND_ALL_TEAM = 4;
    public static final int QUIT = 5;



    public boolean handleCommand(int command, Scanner s, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        ResponseReceiver resReceiver = new ResponseReceiver();
        RequestSender reqSender = new RequestSender();

        switch(command) {

            case FIND_PLAYER_BY_ID:
                reqSender.sendFindPlayerByIdReq(s, outputStream);
                resReceiver.receiveOnePlayer(inputStream);
                break;

            case FIND_PLAYER_BY_NAME:
                reqSender.sendFindPlayerByNameReq(s, outputStream);
                resReceiver.receiveOnePlayer(inputStream);
                break;

            case FIND_ALL_PLAYER:
                reqSender.sendFindAllPlayerReq(outputStream);
                resReceiver.receivePlayerList(inputStream);
                break;

            case FIND_ALL_TEAM:
                reqSender.sendFindAllTeamReq(outputStream);
                resReceiver.receiveTeamList(inputStream);
                break;

            case QUIT:
                reqSender.sendQuitReq(outputStream);
                return false;

        }

        return true;
    }




}
