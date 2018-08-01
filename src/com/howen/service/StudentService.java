package com.howen.service;

import com.howen.db.DBConfig;
import com.howen.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public static boolean change(int id, String address, int age) {
        Student student = DBConfig.studentDB.get(id);
        student.setAddress(address);
        student.setAge(age);
        System.out.println(student.toString());
        DBConfig.studentDB.put(id,student);
        return true;
    }
}
