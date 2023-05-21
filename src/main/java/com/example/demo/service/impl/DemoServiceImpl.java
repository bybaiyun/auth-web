package com.example.demo.service.impl;

import com.example.demo.dal.domain.Student;
import com.example.demo.dal.mapper.StudentMapper;
import com.example.demo.service.DemoService;
import com.example.demo.utils.RedisUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DemoServiceImpl implements DemoService {
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private StudentMapper studentMapper;
    /**
     * @return
     */
    @Override
    public String test() {
        if(redisUtil.tryLock("lockKey", 30, 3)){
            List<Student> students = studentMapper.selectAllStudent();
            students.stream().forEach(student -> {
                System.out.println(student.getSName());
            });
            return "redis lock success";
        }
        return "redis lock failed";
    }
}
