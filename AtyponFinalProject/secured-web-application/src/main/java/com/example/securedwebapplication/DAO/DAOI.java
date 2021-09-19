package com.example.securedwebapplication.DAO;

import com.example.securedwebapplication.Model.Instructor;
import com.example.securedwebapplication.Model.Student;


import java.util.List;

//Item 20
public interface DAOI{
    public List<Student> ViewStudents(String IID);
    public String getKeyOfUser(String UserName, String Type);
    public String getInstructorName(String IID);
    public Student getStudentInfo(String SID);
    public void CreateStudent(Student student);
    public Instructor getInstructor(String UserName);
    public Student getStudent(String UserName);
}
