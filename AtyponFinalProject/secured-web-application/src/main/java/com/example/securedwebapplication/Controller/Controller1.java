package com.example.securedwebapplication.Controller;
//Item 59
import com.example.securedwebapplication.DAO.DAOI;
import com.example.securedwebapplication.DAO.DaoImpl;
import com.example.securedwebapplication.Model.Instructor;
import com.example.securedwebapplication.Model.Student;
import com.example.securedwebapplication.Services.Service;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//Item 67+68 in general
@Controller
@RequestMapping("/home")
public class Controller1 {
    //Item 17-----Not found example
//Item5
    //Item 64
    DAOI dao;
    {
        Resource r=new ClassPathResource("applicationContext.xml");
        BeanFactory factory=new XmlBeanFactory(r);
        dao=(DaoImpl) factory.getBean("dao");
    }

    @RequestMapping(value = "/viewprocess", method= RequestMethod.GET)
    public String ProcessLoginAndViewPageUser(HttpServletRequest request, Model m){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String TypeUser= Service.ExtractTypeUser(currentPrincipalName);
        HttpSession session = request.getSession();
        String Key= dao.getKeyOfUser(currentPrincipalName,TypeUser);
        session.setAttribute("UID",Key);
        session.setAttribute("TypeUser",TypeUser);
        if(!Key.equals("Not Found")) {
            if (TypeUser.equals("Student")) {
                Student student=dao.getStudentInfo(Key);
                String InstructorName=dao.getInstructorName(Key);
                m.addAttribute("Student",student);
                m.addAttribute("InstructorName",InstructorName);
                return "StudentInfo";
            } else if (TypeUser.equals("Instructor")) {
               return "ViewOperationsInstructor";
            }
        }
        return "";
    }
    @RequestMapping(value = "/viewstudent", method= RequestMethod.GET)
    public String ViewStudent(HttpServletRequest request, Model m){
        HttpSession session=request.getSession();
        String Key=(String) session.getAttribute("UID");
        List<Student> list=dao.ViewStudents(Key);
        System.out.println(list);
        m.addAttribute("Students", list);
        return "ViewStudents";
    }


    @RequestMapping(value = "/createstudent", method= RequestMethod.GET)
    public String CreateStudent(HttpServletRequest request, Model m){
        Student student=new Student();
        String FName=request.getParameter("fname");
        String LName=request.getParameter("lname");
        float GPA=Float.parseFloat(request.getParameter("gpa"));
        int NumberOfCourse=Integer.parseInt(request.getParameter("NumCourse"));
        String Email=request.getParameter("email");
        String Password=request.getParameter("psw");
        student.setFName(FName);
        student.setLName(LName);
        student.setUserName(Email);
        student.setPass(Password);
        student.setGPA(GPA);
        student.setNumOfCourse(NumberOfCourse);
        HttpSession session=request.getSession();
        String Key=(String) session.getAttribute("UID");
        student.setFID(Integer.parseInt(Key));
        dao.CreateStudent(student);
        //Item 58 in the jsp file
        return "redirect:/home/viewstudent";

    }












    @RequestMapping(value = "/ViewCreate", method= RequestMethod.GET)
    public String ViewCreate(){

        return "ViewCreate";
    }
/*
    @RequestMapping(value = "/Create", method= RequestMethod.GET)
    public void Create(HttpServletRequest request, HttpServletResponse response){
        System.out.println("Craete Instroctur-4");
        Instructor instructor=new Instructor();
        String FName=request.getParameter("fname");
        String LName=request.getParameter("lname");
        String Email=request.getParameter("email");
        String Password=request.getParameter("psw");
        System.out.println("Craete Instroctur-6");
        instructor.setFName(FName);
        instructor.setLName(LName);
        instructor.setUserName(Email);
        instructor.setPass(Password);
        dao.CreateInstructor(instructor);

        try {
            PrintWriter printWriter= response.getWriter();
            printWriter.println("Hello!!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    */

}
