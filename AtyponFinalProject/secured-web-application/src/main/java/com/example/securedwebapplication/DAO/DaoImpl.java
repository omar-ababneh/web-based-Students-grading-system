package com.example.securedwebapplication.DAO;

import com.example.securedwebapplication.LowLevelDataBase.*;
import com.example.securedwebapplication.LowLevelDataBase.RecordsFile;
import com.example.securedwebapplication.Model.Instructor;
import com.example.securedwebapplication.Model.Student;
import com.example.securedwebapplication.Model.User;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class DaoImpl implements DAOI{

    private RecordsFile recordsFileInstructor;
    private RecordsFile recordsFileStudent;

    public DaoImpl(String PathInstr,String PathStud,int InitialSize)throws Exception{
        if (InitialSize==0){
            recordsFileInstructor=new RecordsFile(PathInstr,"rw");
            recordsFileStudent =new RecordsFile(PathStud,"rw");
        }else {
            recordsFileInstructor=new RecordsFile(PathInstr,InitialSize);
            recordsFileStudent =new RecordsFile(PathStud,InitialSize);
        }
    }
    @Override
    public List<Student> ViewStudents(String IID) {
        List<Student>list=new ArrayList<>();
        Enumeration enumeration=recordsFileStudent.enumerateKeys();
        while (enumeration.hasMoreElements()) {
            try {
                Object obj = enumeration.nextElement();
                RecordReader rr = recordsFileStudent.readRecord((String) obj);
                Student d = (Student) rr.readObject();
                if (String.valueOf(d.getFID()).equals(IID)){
                    list.add(d);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return list;
    }
@Override
    public Student getStudent(String UserName) {
        Enumeration enumeration = recordsFileStudent.enumerateKeys();
        while (enumeration.hasMoreElements()) {
            try {
                Object obj = enumeration.nextElement();
                RecordReader rr = recordsFileStudent.readRecord((String) obj);
                Student student = (Student) rr.readObject();
                if (student.getUserName().equals(UserName)) {
                    return student;
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        Student student = new Student();
        return student;
    }
    @Override
    public Instructor getInstructor(String UserName){

            Enumeration enumeration=recordsFileInstructor.enumerateKeys();
            while (enumeration.hasMoreElements()) {
                try {
                    Object obj = enumeration.nextElement();
                    RecordReader rr = recordsFileInstructor.readRecord((String) obj);
                    Instructor instructor = (Instructor) rr.readObject();
                    System.out.println(obj);
                    if (instructor.getUserName().equals(UserName)){
                        return instructor;
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            }
            Instructor instructor=new Instructor();
        return instructor;
    }

    @Override
    public  String getKeyOfUser(String UserName, String Type) {
        if(Type.equals("Student")){
            Enumeration enumeration=recordsFileStudent.enumerateKeys();
            while (enumeration.hasMoreElements()) {
                try {
                    Object obj = enumeration.nextElement();
                    RecordReader rr = recordsFileStudent.readRecord((String) obj);
                    User   user = (Student) rr.readObject();
                    if (user.getUserName().equals(UserName)){
                        return String.valueOf(obj);
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }else if(Type.equals("Instructor")){
            Enumeration enumeration=recordsFileInstructor.enumerateKeys();
            while (enumeration.hasMoreElements()) {
                try {
                    Object obj = enumeration.nextElement();
                    RecordReader rr = recordsFileInstructor.readRecord((String) obj);
                    User user = (Instructor) rr.readObject();
                    System.out.println(obj);
                    if (user.getUserName().equals(UserName)){
                        return String.valueOf(obj);
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }
        return "Not Found";
    }

    @Override
    public String getInstructorName(String SID) {
        try {
            RecordReader rr=recordsFileStudent.readRecord(SID);
            Student student=(Student)rr.readObject();
            String IID=String.valueOf(student.getFID());
            RecordReader rr1=recordsFileInstructor.readRecord(IID);
            Instructor instructor = (Instructor) rr1.readObject();
            return instructor.getFName()+" "+instructor.getLName();
        }catch (Exception e){
            System.out.println(e);
        }
        //Item 54
    return "NotFound";
    }

    @Override
    public Student getStudentInfo(String SID) {

        try {
            RecordReader rr=recordsFileStudent.readRecord(SID);
            Student student = (Student) rr.readObject();
            return student;
        }catch (Exception e){
            System.out.println(e);
        }
        Student student=new Student();
        return student;
    }

    @Override
    public void CreateStudent(Student student) {
     int Key=student.hashCode();
     try {
         RecordWriter rw=new RecordWriter(String.valueOf(Key));
         rw.writeObject(student);
         recordsFileStudent.insertRecord(rw);
         }catch (Exception e){
         System.out.println(e);
             }


    }
    public void CreateInstructor(Instructor instructor){
        int Key=instructor.hashCode();
        try {
            RecordWriter rw=new RecordWriter(String.valueOf(Key));
            rw.writeObject(instructor);
            recordsFileInstructor.insertRecord(rw);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
