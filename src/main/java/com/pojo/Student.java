package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int studentId;
    private String phone;
    private String password;
    private String name;
    private String number;
    private String school;
    private String major;
    private String grade;
    private byte[] photo;

    //登录
    public Student(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
    //注册
    public Student(String phone, String password, String name) {
        this.phone = phone;
        this.password = password;
        this.name = name;
    }


}
