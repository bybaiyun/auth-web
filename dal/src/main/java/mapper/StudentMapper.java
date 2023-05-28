package mapper;


import domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author suragi
* @description 针对表【student】的数据库操作Mapper
* @createDate 2023-05-21 19:37:15
* @Entity generator.domain.Student
*/
@Mapper
public interface StudentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long id);

    List<Student> selectAllStudent();

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

}
