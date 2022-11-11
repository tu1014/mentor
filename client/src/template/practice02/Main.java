package template.practice02;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    private final static int COMMAND_READ_BOOK_WITH_ID = 1;
    private final static int COMMAND_READ_ALL_BOOK = 2;
    private final static int COMMAND_QUIT = 3;


    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 3000);

        System.out.println("connected!");

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        Scanner s = new Scanner(System.in);

        System.out.println("======================================");

        while(true) {

            int command;
            System.out.println("1. ID로 Book 조회하기");
            System.out.println("2. Book 전체 리스트 조회하기");
            System.out.println("3. 종료하기");
            System.out.println("======================================");
            System.out.print("메뉴를 선택하세요 : ");
            command = s.nextInt();

            if(command == COMMAND_READ_BOOK_WITH_ID) {
                int id;
                System.out.print("조회할 Book의 id를 입력하세요 : ");
                id = s.nextInt();

                // REQUEST 보내기
                byte[] header = null;
                // 참고 : MyProtocol.getHeader();

                dos.write(header);
                dos.writeInt(id);

                // RESPONSE 받기
                byte[] resHeader = null;

                // 헤더 읽기
                byte type = 0;
                byte action = 0;
                byte target = 0;
                byte status = 0;
                int size = 0;;

                byte[] body = null;
                DataInputStream resBodyReader = null;

                // 읽어야 할 body가 있다면 읽기
                if(size > 0) {

                }


                // 바디로부터 Book 역직렬화하기
                Book book = null;
                System.out.println(book);

            }


            else if(command == COMMAND_READ_ALL_BOOK){

                // REQUEST 보내기
                byte[] header = null;


                // RESPONSE 받기
                byte[] resHeader = null;

                DataInputStream resInput = new DataInputStream(
                        new ByteArrayInputStream(resHeader)
                );


                byte type = 0;
                byte action = 0;
                byte target = 0;
                byte status = 0;
                int size = 0;

                byte[] body = null;
                DataInputStream resBodyReader = null;

                // 읽어야 할 body가 있다면 읽기
                if(size > 0) {

                }

                // Body에서 Book 여러번 읽어 츨력하기

            }

            else if(command == COMMAND_QUIT) {

                // REQUEST 보내기
                byte[] header = null;



                break;

            }

            else {
                // 예외 처리
                // 실습시 작성할 필요 없음
            }

            System.out.println("======================================");


        }

        System.out.println("Good Bye!");

    }
}
