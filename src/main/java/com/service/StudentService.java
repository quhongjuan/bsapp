package com.service;

import com.mapper.StudentMapper;
import com.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 添加学生
     */
    public int addStudent(Student student){
        //处理一下电话号重复注册的问题
       return studentMapper.addStudent(student);
    }
    /**
     * 验证学生是否存在
     */
    public boolean isInsist(String phone,String password){
        Student student=new Student(phone,password);
        if(studentMapper.queryStudent(phone,password)==null)
            return false;
        else return true;
    }
}
