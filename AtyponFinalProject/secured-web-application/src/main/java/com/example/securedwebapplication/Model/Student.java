package com.example.securedwebapplication.Model;

import java.io.Serializable;
import java.util.Objects;

public class Student extends User implements Serializable {
    //Item15+16
    //Item 61
    //Item 62: Avoid strings where other types are more appropriate
    private int FID,NumOfCourse;
    private float GPA;

    public float getGPA() {
        return GPA;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }

    public int getFID() {
        return FID;
    }

    public void setFID(int FID) {
        this.FID = FID;
    }

    public int getNumOfCourse() {
        return NumOfCourse;
    }

    public void setNumOfCourse(int numOfCourse) {
        NumOfCourse = numOfCourse;
    }
    @Override
    public int hashCode() {
        return Objects.hash(FID, NumOfCourse,GPA);
    }


}
