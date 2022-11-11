package practice02;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.net.Socket;

public class Main {
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

            if(command == 1) {
                int id;
                System.out.print("조회할 Book의 id를 입력하세요 : ");
                id = s.nextInt();

                // REQUEST 보내기
                byte[] header = MyProtocol.getHeader(
                        MyProtocol.TYPE_REQ,
                        MyProtocol.ACTION_READ_ONE,
                        MyProtocol.TARGET_BOOK,
                        MyProtocol.UNUSED,
                        4
                );

                dos.write(header);
                dos.writeInt(id);

                // RESPONSE 받기
                byte[] resHeader = new byte[MyProtocol.SIZE_HEADER];
                dis.read(resHeader);

                DataInputStream resInput = new DataInputStream(
                        new ByteArrayInputStream(resHeader)
                );


                byte type = resInput.readByte();
                byte action = resInput.readByte();
                byte target = resInput.readByte();
                byte status = resInput.readByte();
                int size = resInput.readInt();

                byte[] body = null;
                DataInputStream resBodyReader = null;

                // 읽어야 할 body가 있다면 읽기
                if(size > 0) {
                    body = new byte[size];
                    dis.read(body);
                }

                Book book = Book.bytesToBook(body);
                System.out.println(book);

            }


            else if(command == 2){

                // REQUEST 보내기
                byte[] header = MyProtocol.getHeader(
                        MyProtocol.TYPE_REQ,
                        MyProtocol.ACTION_READ_ALL,
                        MyProtocol.TARGET_BOOK,
                        MyProtocol.UNUSED,
                        0
                );

                dos.write(header);

                // RESPONSE 받기
                byte[] resHeader = new byte[MyProtocol.SIZE_HEADER];
                dis.read(resHeader);

                DataInputStream resInput = new DataInputStream(
                        new ByteArrayInputStream(resHeader)
                );


                byte type = resInput.readByte();
                byte action = resInput.readByte();
                byte target = resInput.readByte();
                byte status = resInput.readByte();
                int size = resInput.readInt();

                byte[] body = null;
                DataInputStream resBodyReader = null;

                // 읽어야 할 body가 있다면 읽기
                if(size > 0) {
                    body = new byte[size];
                    dis.read(body);
                    resBodyReader = new DataInputStream(
                            new ByteArrayInputStream(body)
                    );
                }

                int count = resBodyReader.readInt();

                for(int i=0; i<count; i++) {

                    int bookSize = resBodyReader.readInt();
                    byte[] byteArray = new byte[bookSize];
                    resBodyReader.read(byteArray);

                    Book book = Book.bytesToBook(byteArray);
                    System.out.println(book);

                }

            }

            else {

                // REQUEST 보내기
                byte[] header = MyProtocol.getHeader(
                        MyProtocol.TYPE_REQ,
                        MyProtocol.ACTION_QUIT,
                        MyProtocol.UNUSED,
                        MyProtocol.UNUSED,
                        0
                );

                dos.write(header);
                break;

            }

            System.out.println("======================================");


        }

        System.out.println("Good Bye!");

    }
}
