/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kiandrasin
 */
public class Enrollment {
    private Student student;
    private Course course;
    
    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
    
    public Student getStudent() {
        return student;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
}
