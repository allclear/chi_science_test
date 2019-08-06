package com.train.databaseparctice.workdatabaseconnectionhuayuhui;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseOperator implements CourseOperatable {
    public static void main(String[] args) {

    }
    //显示全部信息
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
        }finally {
            JdbcUtil.closeConnection(resultSet);
            JdbcUtil.getIntance().closeConnection(statement);
            JdbcUtil.getIntance().closeConnection(connection);
        }
        return list;
    }
    //通过ID更新信息
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
    //添加信息
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

    //通过ID删除信息
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

    //通过号码模糊查询
    @Override
    public List<Course> findByUserNameLike(String name) {
        List<Course> list = new ArrayList<>();
        Connection connection = JdbcUtil.getIntance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("select * from course where cname like '%");
            stringBuffer.append(name);
            stringBuffer.append("%'");
            preparedStatement = connection.prepareStatement(stringBuffer.toString());
            /*preparedStatement =connection.prepareStatement("select * from course where cname like ?");
            preparedStatement.setString(1 , "%" + name + "%");*/
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Course course = new Course();
                course.setCourseNumber(resultSet.getString(1));
                course.setCourseName(resultSet.getString("cname"));
                course.setTeacherNumber(resultSet.getInt(3));
                list.add(course);
            }
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeConnection(resultSet);
            JdbcUtil.closeConnection(preparedStatement);
            JdbcUtil.closeConnection(connection);
        }
        return list;
    }

    //通过号码模糊查询并且倒序
    @Override
    public List<Course> findOrderByUserId(int id) {
        List<Course> list = new ArrayList<>();
        Connection connection = JdbcUtil.getIntance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from course where tno like '%");
        stringBuffer.append(id);
        stringBuffer.append("%' order by tno desc");
        System.out.println(stringBuffer.toString());
        try {
           preparedStatement = connection.prepareStatement(stringBuffer.toString());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Course course = new Course();
                course.setCourseNumber(resultSet.getString(1));
                course.setCourseName(resultSet.getString(2));
                course.setTeacherNumber(resultSet.getInt(3));
                list.add(course);
            }
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeConnection(resultSet);
            JdbcUtil.closeConnection(preparedStatement);
            JdbcUtil.closeConnection(connection);
        }
        return list;
    }

    //通过号码模糊查询并且正序，分页 id 是号码 currPage 是当前页码，pageSize 是每页的数据条数
    @Override
    public List<Course> findByUserNameLikeOrderLimit(int id,int currPage,int pageSize) {
         List<Course> list = new ArrayList<>();
        Connection connection = JdbcUtil.getIntance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from course where tno like '%");
        stringBuffer.append(id);
        stringBuffer.append("%' order by tno asc");
        try {
            preparedStatement = connection.prepareStatement(stringBuffer.toString());
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Course course = new Course();
                course.setCourseNumber(resultSet.getString(1));
                course.setCourseName(resultSet.getString(2));
                course.setTeacherNumber(resultSet.getInt(3));
                list.add(course);
            }
            System.out.println("当前页数" + currPage + "\n一共" + list.size() + "条数据");
                for (int i = (currPage - 1) * pageSize; i < currPage * pageSize; i++) {
                        System.out.println(list.get(i));
                }
            System.out.println("每页" + pageSize +"条数据");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeConnection(resultSet);
            JdbcUtil.closeConnection(preparedStatement);
            JdbcUtil.closeConnection(connection);
        }
        return list;
    }
}
