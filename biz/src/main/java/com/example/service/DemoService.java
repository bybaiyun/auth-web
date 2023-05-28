package com.example.service;


import com.example.domain.Student;

import java.util.List;

public interface DemoService {
    String test();

    List<Student> queryAllStudent();
}
