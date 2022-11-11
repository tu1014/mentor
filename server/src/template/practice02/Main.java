package template.practice02;

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
            byte type = 0;
            byte action = 0;
            byte target = 0;
            byte status = 0;
            int size = 0;

            /*System.out.println("type : " + type);
            System.out.println("action : " + action);
            System.out.println("target : " + target);
            System.out.println("status : " + status);
            System.out.println("body size : " + size);*/

            byte[] body = null;
            DataInputStream bodyReader = null;

            // 읽어야 할 body가 있다면 읽기
            if(size > 0) {

                // body 배열 생성 및 읽기

                bodyReader = new DataInputStream(
                        new ByteArrayInputStream(body)
                );
            }

            // type 확인하는 코드

            if(action == MyProtocol.ACTION_QUIT) break;

            if(action == MyProtocol.ACTION_READ_ONE) {

                // 조회할 id 읽기
                System.out.println("READ_ONE");
                int id = 0;
                System.out.println("조회할 id : " + id);

                if(target == MyProtocol.TARGET_BOOK) {
                    System.out.println("TARGET : BOOK");
                    Book book = database.getBook(id);

                    byte[] resBody = null;
                    byte[] resHeader = null;
                    // 참고 : MyProtocol.getHeader();

                    // 헤더 및 바디 전송

                }

            }

            else if(action == MyProtocol.ACTION_READ_ALL) {
                ArrayList<Book> books = database.getAllBook();

                // body 바이트 배열 생성에 사용
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                DataOutputStream dbuf = new DataOutputStream(buf);

                // books 직렬화하여 바디에 담고 헤더 생성하여 전송

            }

            // 추가 실습 : Student 에 대해 조회하는 기능 추가


            // 이상한 요청 들어왔을 때 처리할 수 없다는 것을 알려주는 패킷을 클라이언트로 전송
            // 실습에서는 구현할 필요 x
            System.out.println("======================================");

        } // end of while





    }
}
