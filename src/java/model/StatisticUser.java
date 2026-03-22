/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package model;

/**
 *
 * @author THIN15
 */
public class StatisticUser {

    private int admin;
    private int staff;
    private int teacher;
    private int student;

    public StatisticUser() {
    }

    public StatisticUser(int admin, int staff, int teacher, int student) {
        this.admin = admin;
        this.staff = staff;
        this.teacher = teacher;
        this.student = student;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }

    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    
}
