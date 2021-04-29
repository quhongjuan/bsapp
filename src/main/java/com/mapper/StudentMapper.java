package com.mapper;

import com.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    List<Student> queryStudentByPhone(@Param("phone") String phone);
    Student queryStudentById(@Param("studentId") int studentId);
    Student queryStudent(@Param("phone") String phone,@Param("password") String password);
    int addStudent(Student student);
    int updatePwd(@Param("phone") String phone,@Param("password") String password);
    int updateInfo(@Param("studentId") int studentId,@Param("number") String number,@Param("school") String school,@Param("major") String major,@Param("grade") String grade);
    int addPhoto(@Param("studentId") int studentId,@Param("photo") byte[] photo);

}
