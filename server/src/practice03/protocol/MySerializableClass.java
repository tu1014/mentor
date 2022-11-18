package practice03.protocol;


import java.io.DataInputStream;
import java.io.IOException;

public interface MySerializableClass {

    public byte[] getBytes() throws IOException;

}
