package service;


import domain.Student;
import java.util.List;

public interface DemoService {
    String test();

    List<Student> queryAllStudent();
}
