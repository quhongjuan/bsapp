package com.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.Student;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*",maxAge = 3600)//解决跨域
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/student/add")
    @ResponseBody
    public String addStudent(){
        Student student=new Student("18704769256","123");
        int id = studentService.addStudent(student);
        if(id>0) return "ok";
        else return "false";
    }

    @ResponseBody
    @PostMapping("/student/query")
    public String yanzheng(HttpServletRequest request) throws JsonProcessingException {
        boolean flag=false;
        String phone=request.getParameter("phone");
        String password =request.getParameter("password");
        flag = studentService.isInsist(phone,password);

        Map<String,Object> map=new HashMap<String, Object>();
        if(flag)  map.put("msg","登录成功！");
        else  map.put("msg","登录失败！");
        map.put("flags",flag);
        //jackson ObjectMapper
        ObjectMapper mapper= new ObjectMapper();
        String str= mapper.writeValueAsString(map);
        System.out.println("结果："+str);
        return str;
    }
}
