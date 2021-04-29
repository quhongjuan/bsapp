package com.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.StudentMapper;
import com.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("student")
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 添加学生
     */
    public int addStudent(Student student){
        //处理一下电话号重复注册的问题.-1表示电话号码重复注册
        if(isUsedPhone(student.getPhone()))
            return studentMapper.addStudent(student);
        else return -1;
    }
    //电话重复注册
    public boolean isUsedPhone(String phone){
        List<Student> students = null;
        students=studentMapper.queryStudentByPhone(phone);
        if(students.size()>=1) return false;
        else return true;
    }

    /**
     * 验证学生是否存在
     */
    public Student isInsist(String phone,String password){
        return studentMapper.queryStudent(phone,password);

    }

    /**
     * 修改学生密码
     */
    public boolean updatePwd(String phone,String oldPassword,String newPassword){
        if(isInsist(phone,oldPassword)!=null){
            studentMapper.updatePwd(phone,newPassword);
            return true;
        }else return false;//电话或者旧密码错误
    }
    /**
     * 完善个人信息
     */
    public Student updateInfo(int id,String number,String school,String major,String grade){
        studentMapper.updateInfo(id,number,school,major,grade);
        return studentMapper.queryStudentById(id);
    }
    //查看个人信息
    public  Student queryStudentInfo(int id){
        return studentMapper.queryStudentById(id);
    }
    //上传照片
    public String addPhoto(int studentId,byte[] photo) throws JsonProcessingException {
        studentMapper.addPhoto(studentId,photo);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("msg","上传成功");
        String str=new ObjectMapper().writeValueAsString(map);
        return str;
    }
    //判断是否已经上传图片
    public String isUpload(int studentId) throws JsonProcessingException {
        boolean flag=false;
        Student student=studentMapper.queryStudentById(studentId);
        if(student.getPhoto()!=null) flag=true;
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("flag",flag);
        map.put("student",student);
        String str=new ObjectMapper().writeValueAsString(map);
        return str;
    }
}
