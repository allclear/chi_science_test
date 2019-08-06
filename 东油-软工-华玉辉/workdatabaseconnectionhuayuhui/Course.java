package com.train.databaseparctice.workdatabaseconnectionhuayuhui;
//数据表的映射
public class Course {
    private String courseNumber;
    private String courseName;
    private int teacherNumber;

    public Course(String courseNumber, String courseName, int teacherNumber) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.teacherNumber = teacherNumber;
    }

    public Course() {

    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(int teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseNumber='" + courseNumber + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teacherNumber=" + teacherNumber +
                '}';
    }
}
