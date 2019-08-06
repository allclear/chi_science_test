package com.train.databaseparctice.workdatabaseconnectionhuayuhui;

import java.util.ArrayList;
import java.util.List;

public class TheMainCourse {
    public static void main(String[] args) {
        Course course = new Course();
        List<Course>  list = new ArrayList<>();
        course.setCourseNumber("3-112");
        course.setCourseName("Java");
        course.setTeacherNumber(159);
        CourseOperator courseOperatable = new CourseOperator();
        list = courseOperatable.findCourseInfo();
        list.forEach(System.out::println);
        courseOperatable.insertCourse(course);
        list = courseOperatable.findCourseInfo();
        list.forEach(System.out::println);
        courseOperatable.deleteCourseById(course.getTeacherNumber());
        list = courseOperatable.findCourseInfo();
        list.forEach(System.out::println);
        courseOperatable.insertCourse(course);
        courseOperatable.updateCourseById(course);
        list = courseOperatable.findCourseInfo();
        list.forEach(System.out::println);

    }
}
