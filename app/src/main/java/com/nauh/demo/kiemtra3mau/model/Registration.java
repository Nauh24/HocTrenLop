package com.nauh.demo.kiemtra3mau.model;

import java.util.Date;

public class Registration {
    private int id;
    private int studentId;
    private int courseId;
    private Date registrationDate;

    public Registration() {
    }

    public Registration(int studentId, int courseId, Date registrationDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
