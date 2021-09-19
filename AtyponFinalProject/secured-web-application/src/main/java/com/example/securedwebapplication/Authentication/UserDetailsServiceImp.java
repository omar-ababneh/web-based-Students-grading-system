package com.example.securedwebapplication.Authentication;

import com.example.securedwebapplication.DAO.DaoImpl;
import com.example.securedwebapplication.Model.Instructor;
import com.example.securedwebapplication.Model.Student;
import com.example.securedwebapplication.Model.User;
import com.example.securedwebapplication.Services.Service;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpSession;

public class UserDetailsServiceImp implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        DaoImpl dao;
        Resource r=new ClassPathResource("applicationContext.xml");
        BeanFactory factory=new XmlBeanFactory(r);
        dao=(DaoImpl) factory.getBean("dao");
        String Type=Service.ExtractTypeUser(s);
        if(Type.equals("Student")){
            Student student=dao.getStudent(s);
            UserDetails userDetails=new SecurityUser(student);
            return userDetails;
        }else if(Type.equals("Instructor")){
            Instructor instructor=dao.getInstructor(s);
            UserDetails userDetails=new SecurityUser(instructor);
            return userDetails;
        }
        throw  new UsernameNotFoundException("User Not Found In Data base!");

    }
}
