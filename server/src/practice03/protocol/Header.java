package practice03.protocol;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Header implements MySerializableClass {

    public final static byte UNUSED = 0;

    public final static byte TYPE_REQ = 0;
    public final static byte TYPE_RES = 1;

    public final static byte ACTION_QUIT = 0;
    public final static byte ACTION_READ = 1;

    public final static byte TARGET_PLAYER = 0;
    public final static byte TARGET_TEAM = 1;

    public final static byte OPTION_PLAYER_ALL = 0;
    public final static byte OPTION_PLAYER_BY_ID = 1;
    public final static byte OPTION_PLAYER_BY_NAME = 2;

    public final static byte FIND_TEAM_ALL = 0;


    public final static byte STATUS_FAIL = 0;
    public final static byte STATUS_SUCCESS = 1;
    public final static byte STATUS_BAD_REQUEST = 2;

    public final static int SIZE_HEADER = 8;

    public byte type;
    public byte action;
    public byte target;
    public byte option;
    public byte status;
    public int bodySize;

    public Header(byte type, byte action, byte target, byte option, byte status, int bodySize) {
        this.type = type;
        this.action = action;
        this.target = target;
        this.option = option;
        this.status = status;
        this.bodySize = bodySize;
    }

    public static Header readHeader(DataInputStream dis) throws IOException {

        byte type = dis.readByte();
        byte action = dis.readByte();
        byte target = dis.readByte();
        byte findOption = dis.readByte();
        byte status = dis.readByte();
        int bodySize = dis.readInt();

        return new Header(type, action, target, findOption, status, bodySize);

    }


    @Override
    public byte[] getBytes() throws IOException {

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeByte(type);
        dos.writeByte(action);
        dos.writeByte(target);
        dos.writeByte(option);
        dos.writeByte(status);
        dos.writeInt(bodySize);

        return buf.toByteArray();


    }
}
