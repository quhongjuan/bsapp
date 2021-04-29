package com.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.Student;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域
@Controller
public class StudentController {
    @Autowired
    @Qualifier("student")
    private StudentService studentService;

    //注册
    @RequestMapping("/student/add")
    @ResponseBody
    public String addStudent(HttpServletRequest request) throws JsonProcessingException {
        boolean flag=false;
        String phone=request.getParameter("phone");
        String password =request.getParameter("password");
        String name=request.getParameter("name");

        Map<String,Object> map=new HashMap<String,Object>();
        Student student=new Student(phone,password,name);
        if(studentService.addStudent(student)==-1)
            map.put("msg","手机号已经注册过了！");
        else {
            map.put("msg","注册成功！");
            flag=true;
        }
        map.put("flags",flag);
        ObjectMapper mapper=new ObjectMapper();
        String str=mapper.writeValueAsString(map);
        System.out.println("注册结果："+str);
        return str;
    }

    //登录
    @ResponseBody
    @PostMapping("/student/query")
    public String yanzheng(HttpServletRequest request) throws JsonProcessingException {
        boolean flag=false;
        String phone=request.getParameter("phone");
        String password =request.getParameter("password");
        Student student = studentService.isInsist(phone,password);

        Map<String,Object> map=new HashMap<String, Object>();
        if(student!=null) flag=true;
        if(flag)  map.put("msg","登录成功！");
        else  map.put("msg","登录失败！");
        map.put("flags",flag);
        map.put("user",student);
        //jackson ObjectMapper
        ObjectMapper mapper= new ObjectMapper();
        String str= mapper.writeValueAsString(map);
        System.out.println("结果："+str);
        return str;
    }

    //修改密码
    @ResponseBody
    @PostMapping("/student/change")
    public String updatePwd(HttpServletRequest request) throws JsonProcessingException {
        boolean flag=false;
        String phone=request.getParameter("phone");
        String oldPassword=request.getParameter("oldPassword");
        String newPassword=request.getParameter("newPassword");

        Map<String,Object> map=new HashMap<String, Object>();
        String msg="";
        if(studentService.updatePwd(phone,oldPassword,newPassword)){
            flag=true;  msg="修改成功";
        }else msg="手机号或者旧密码错误";
        map.put("flags",flag);
        map.put("msg",msg);
        ObjectMapper mapper=new ObjectMapper();
        String str=mapper.writeValueAsString(map);
        System.out.println("密码修改结果："+str);
        return str;
    }

    //完善个人信息
    @ResponseBody
    @PostMapping("/student/updateInfo")
    public String updateInfo(HttpServletRequest request) throws JsonProcessingException {
        int id= Integer.parseInt(request.getParameter("id"));
        String number=request.getParameter("number");
        String school=request.getParameter("school");
        String major=request.getParameter("major");
        String grade=request.getParameter("grade");

        Student student = studentService.updateInfo(id,number,school,major,grade);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("student",student);
        String str=new ObjectMapper().writeValueAsString(map);
        System.out.println("完善个人信息"+str);
        return str;
    }
    //显示个人信息
    @ResponseBody
    @PostMapping("/student/queryStudent")
    public String queryStudentInfo(HttpServletRequest request) throws JsonProcessingException {
        int id= Integer.parseInt(request.getParameter("id"));

        Student student = studentService.queryStudentInfo(id);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("student",student);
        String str=new ObjectMapper().writeValueAsString(map);
        System.out.println("查看个人信息"+str);
        return str;
    }

    //接收个人照片
    @ResponseBody
    @PostMapping("/student/uploadPhoto")
    public String uploadPhoto(HttpServletRequest request, @RequestParam("myPhoto")MultipartFile file) throws IOException {
        String path=request.getParameter("path");
        byte[] photo=file.getBytes();
        int id= Integer.parseInt(request.getParameter("userId"));
        //System.out.println("path:"+path);
        //System.out.println("id:"+id);

        return studentService.addPhoto(id,photo);
    }

    //查看个人照片

    public String isUpload(HttpServletRequest request) throws JsonProcessingException {
        int studentId= Integer.parseInt(request.getParameter("userId"));
        return studentService.isUpload(studentId);
    }
}
