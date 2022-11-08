package practice02;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MyProtocol {

    public final static byte UNUSED = 0;

    public final static byte TYPE_REQ = 0;
    public final static byte TYPE_RES = 1;

    public final static byte ACTION_QUIT = 0;
    public final static byte ACTION_READ_ONE = 1;
    public final static byte ACTION_READ_ALL = 2;

    public final static byte TARGET_STUDENT = 0;
    public final static byte TARGET_BOOK = 1;

    public final static byte STATUS_FAIL = 0;
    public final static byte STATUS_SUCCESS = 1;

    public final static int SIZE_HEADER = 8;

    public static byte[] getHeader(byte type, byte action, byte target, byte status, int size) throws IOException {

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeByte(type);
        dos.writeByte(action);
        dos.writeByte(target);
        dos.writeByte(status);
        dos.writeInt(size);

        return buf.toByteArray();

    }

}
