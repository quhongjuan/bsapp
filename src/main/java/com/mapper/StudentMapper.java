package com.mapper;

import com.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {
    Student queryStudentByPhone(String phone);
    Student queryStudent(@Param("phone") String phone,@Param("password") String password);
    int addStudent(Student student);
}
