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

    public Student(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
}
