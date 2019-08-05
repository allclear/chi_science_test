package com.train.databaseparctice.workdatabaseconnectionhuayuhui;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseOperator implements CourseOperatable {
    public static void main(String[] args) {

    }
    @Override
    public  List<Course> findCourseInfo() {
        List<Course> list = new ArrayList<>();
        Connection connection = JdbcUtil.getIntance().getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            String mysql = "select * from course";
            resultSet = statement.executeQuery(mysql);

            while(resultSet.next()){
                String cno = resultSet.getString(1);
                String cname = resultSet.getString(2);
                int tno = resultSet.getInt("tno");
                Course course = new Course();
                course.setCourseNumber(cno);
                course.setCourseName(cname);
                course.setTeacherNumber(tno);
                list.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int updateCourseById(Course course) {
        Connection connection = JdbcUtil.getIntance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("update course set cno ='");
            stringBuffer.append(course.getCourseNumber());
            stringBuffer.append("',");
            stringBuffer.append("cname ='");
            stringBuffer.append(course.getCourseName());
            stringBuffer.append("',");
            stringBuffer.append("tno ='");
            stringBuffer.append(course.getTeacherNumber());
            stringBuffer.append("' where cno = '");
            stringBuffer.append(course.getCourseNumber());
            stringBuffer.append("'");
            System.out.println(stringBuffer.toString());
            int affectedRows = statement.executeUpdate(stringBuffer.toString());

            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.getIntance().closeConnection(statement);
            JdbcUtil.getIntance().closeConnection(connection);
        }
        return 0;
    }
    @Override
    public int insertCourse(Course course) {
        Connection connection = JdbcUtil.getIntance().getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("insert into course values('");
            stringBuffer.append(course.getCourseNumber());
            stringBuffer.append("','");
            stringBuffer.append(course.getCourseName());
            stringBuffer.append("','");
            stringBuffer.append(course.getTeacherNumber());
            stringBuffer.append("')");
            System.out.println(stringBuffer.toString());
            int affectedRows = statement.executeUpdate(stringBuffer.toString());
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.getIntance().closeConnection(statement);
            JdbcUtil.getIntance().closeConnection(connection);
        }
        return 0;
    }

    @Override
    public int deleteCourseById(int id) {
        Connection connection = JdbcUtil.getIntance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("delete from course where tno = '");
            stringBuffer.append(id);
            stringBuffer.append("'");
            int affectedRows = statement.executeUpdate(stringBuffer.toString());

            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeConnection(statement);
            JdbcUtil.closeConnection(connection);
        }
        return 0;
    }
}
