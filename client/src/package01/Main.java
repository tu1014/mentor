package package01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {

            Socket socket = new Socket("127.0.0.1", 3000);
            Scanner s = new Scanner(System.in);

            System.out.println("connected!");

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            while(true) {

                System.out.print("조회할 학생의 id를 입력해주세요( 1 <= id <= 10) : ");
                int id = s.nextInt();

                byte[] idBytes = new byte[4];

                idBytes[0] = (byte)(id >> 24);
                idBytes[1] = (byte)(id >> 16);
                idBytes[2] = (byte)(id >> 8);
                idBytes[3] = (byte)(id);

                os.write(idBytes);

                if(id == 0) break;

                byte[] buf = new byte[1024];
                is.read(buf);

                int size;

                size = ((((int)buf[0] & 0xff) << 24) |
                        (((int)buf[1] & 0xff) << 16) |
                        (((int)buf[2] & 0xff) << 8) |
                        (((int)buf[3] & 0xff)));

                String type = new String(buf, 4, size);
                System.out.println("received " + type + " type data!");

                switch (type) {
                    case "Student":
                        Student student = Student.bytesToStudent(buf);
                        System.out.println(student);

                    default:
                        System.out.println("error");
                }

            }


        }

        catch(IOException e) {
            System.out.println("서버가 종료되었습니다");
        }

        System.out.println("접속을 종료합니다");

    }
}
