package practice02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(3000);
        System.out.println("ServerSocket created.\nWaiting for connection ...\n\n");

        Socket socket = ss.accept();

        System.out.println("Client connected!\n");

        DataInputStream is = new DataInputStream(socket.getInputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        Database database = new Database();

        while(true) {

            // header 읽기
            byte type = is.readByte();
            byte action = is.readByte();
            byte target = is.readByte();
            byte status = is.readByte();
            int size = is.readInt();
            System.out.println("type : " + type);
            System.out.println("action : " + action);
            System.out.println("target : " + target);
            System.out.println("status : " + status);
            System.out.println("body size : " + size);

            byte[] body = null;
            DataInputStream bodyReader = null;

            // 읽어야 할 body가 있다면 읽기
            if(size > 0) {
                body = new byte[size];
                is.read(body);
                bodyReader = new DataInputStream(
                        new ByteArrayInputStream(body)
                );
            }

            // type 확인하는 코드

            if(action == MyProtocol.ACTION_QUIT) break;

            if(action == MyProtocol.ACTION_READ_ONE) {
                System.out.println("READ_ONE");
                int id = bodyReader.readInt();
                System.out.println("조회할 id : " + id);

                if(target == MyProtocol.TARGET_BOOK) {
                    System.out.println("TARGET : BOOK");
                    Book book = database.getBook(id);
                    byte[] resBody = book.getBytes();
                    byte[] resHeader = MyProtocol.getHeader(
                            MyProtocol.TYPE_RES,
                            MyProtocol.ACTION_READ_ONE,
                            MyProtocol.TARGET_BOOK,
                            MyProtocol.STATUS_SUCCESS,
                            resBody.length
                    );
                    os.write(resHeader);
                    os.write(resBody);
                }

            }

            else if(action == MyProtocol.ACTION_READ_ALL) {
                ArrayList<Book> books = database.getAllBook();
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                DataOutputStream dbuf = new DataOutputStream(buf);

                dbuf.writeInt(books.size());

                for(Book book : books) {
                    byte[] bookByteArray = book.getBytes();
                    dbuf.writeInt(bookByteArray.length);
                    dbuf.write(bookByteArray);
                }

                byte[] resBody = buf.toByteArray();
                byte[] resHeader = MyProtocol.getHeader(
                        MyProtocol.TYPE_RES,
                        MyProtocol.ACTION_READ_ALL,
                        MyProtocol.TARGET_BOOK,
                        MyProtocol.STATUS_SUCCESS,
                        resBody.length
                );
                os.write(resHeader);
                os.write(resBody);

            }

            // 이상한 요청 들어왔을 때 처리할 수 없다는 것을 알려주는 패킷을 클라이언트로 전송

        } // end of while





    }
}
