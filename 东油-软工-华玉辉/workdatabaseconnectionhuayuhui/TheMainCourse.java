package com.train.databaseparctice.workdatabaseconnectionhuayuhui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheMainCourse {
    public static void main(String[] args) {
     /*  Course course = new Course();
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
        list.forEach(System.out::println);*/


        Scanner scanner = new Scanner(System.in);
        String cno = scanner.nextLine();
        CourseOperator courseOperator = new CourseOperator();
        List<Course> list = new ArrayList<>();
      //  list = courseOperator.findByUserNameLike(cno);
         courseOperator.findOrderByUserId(Integer.parseInt(cno));
        System.out.println();
        System.out.println();
         courseOperator.findByUserNameLikeOrderLimit(Integer.parseInt(cno),2,2);

    }
}
