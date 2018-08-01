package com.howen.controller;
import com.howen.config.OperateLog;
import com.howen.db.DBConfig;
import com.howen.entity.Student;
import com.howen.service.StudentService;
import org.aspectj.lang.annotation.AdviceName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;


    @OperateLog(operatorModule = "待刻印章 》》 修改" )
    @RequestMapping(value = "/change")
    public String change(int id, String address,int age){
        boolean isChanged = StudentService.change(id,address,age);
        return "success";
    }

    @OperateLog(operatorModule = "待刻印章 》》 添加")
    @RequestMapping(value = "/add")
    public String add(int id,String name,String address,int age,String sex){
        Student student = new Student();
        student.setAge(age);
        student.setAddress(address);
        student.setId(id);
        student.setName(name);
        student.setSex(sex);
        System.out.println(student.toString());
        DBConfig.studentDB.put(id,student);
        return "success";
    }
}
