package practice02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private Map<Integer, Student> studentDB;
    private Map<Integer, Book> bookDB;

    private void init() {

        studentDB.put(1, new Student("김동빈", "구미시", 22));
        studentDB.put(2, new Student("이아영", "구미시", 23));
        studentDB.put(3, new Student("진성민", "구미시", 24));
        studentDB.put(4, new Student("김가연", "구미시", 22));
        studentDB.put(5, new Student("권미영", "구미시", 22));
        studentDB.put(6, new Student("박철수", "구미시", 23));
        studentDB.put(7, new Student("고영희", "구미시", 21));
        studentDB.put(8, new Student("이동욱", "구미시", 24));
        studentDB.put(9, new Student("이지영", "구미시", 24));
        studentDB.put(10, new Student("박진호", "구미시", 30));

        bookDB.put(1, new Book("데이터베이스", "한빛", 300, false));
        bookDB.put(2, new Book("운영체제", "한빛", 300, false));
        bookDB.put(3, new Book("컴파일러", "한빛", 300, true));
        bookDB.put(4, new Book("C++", "한빛", 300, false));
        bookDB.put(5, new Book("리눅스 커널", "한빛", 300, false));
        bookDB.put(6, new Book("컴퓨터 네트워크", "한빛", 300, true));
        bookDB.put(7, new Book("컴퓨터 구조", "한빛", 300, false));
        bookDB.put(8, new Book("JPA 프로그래밍", "한빛", 300, true));
        bookDB.put(9, new Book("매트랩", "한빛", 300, true));
        bookDB.put(10, new Book("임베디드 시스템", "한빛", 300, false));
    }

    public Database() {
        studentDB = new HashMap<>();
        bookDB = new HashMap<>();
        init();
    }

    public Student getStudent(int id) {
        if (1 <= id && id <= 10) return studentDB.get(id);
        return null;
    }

    public Book getBook(int id) {
        if(1 <= id && id <= 10) return bookDB.get(id);
        return null;
    }

    public ArrayList<Book> getAllBook() {
        ArrayList<Book> books = new ArrayList<>();
        for(int i=1; i<=10; i++) {
            Book book = getBook(i);
            books.add(book);
        }
        return books;
    }


}
