package service.impl;


import domain.Student;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import mapper.StudentMapper;
import org.springframework.stereotype.Service;
import service.DemoService;
import utils.RedisUtil;

import java.util.List;

@Slf4j
@Service
public class DemoServiceImpl implements DemoService {
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private StudentMapper studentMapper;

    @Override
    //@Transactional
    public String test() {
//        ClassPathResource resource = new ClassPathResource("bean.xml");
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
//        reader.loadBeanDefinitions(resource);
//        Object hashMap = factory.getBean("hashMap");
        if(redisUtil.tryLock("lockKey", 30, 3)){
            List<Student> students = studentMapper.selectAllStudent();
            studentMapper.updateByPrimaryKey(Student.builder().sid("1").sName("娜可露露").build());
            students.forEach(student -> System.out.println(student.getSName()));
            //int i = 3 / 0;
            return "redis lock success";
        }
        //TransactionUtils.doAfterTransaction(new DoTransactionCompletion(() -> System.out.println("transaction proper committed")));
        return "redis lock failed";
    }

    /**
     * 查询所有学生信息
     * @return students
     */
    @Override
    public List<Student> queryAllStudent() {
        return studentMapper.selectAllStudent();
    }
}
