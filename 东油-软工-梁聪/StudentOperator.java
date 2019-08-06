package com.train.day19;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lc
 * @date 2019/8/6 19:14
 */
public class StudentOperator implements StudentOperatable {
    //初始化，节省资源
    static Connection connection = null;
    static PreparedStatement stmt = null;
    static ResultSet rs = null;

    public static void main(String[] args) {
        StudentOperatable studentOperatable = new StudentOperator();

        //错误提示，查询mysql数据库如果有中文的时候，需要在URI后加上
        //useUnicode=true&characterEncoding=utf-8
        System.out.println("学生姓名模糊查询");
        Student student = studentOperatable.getStudentInfoByNameLike("昭君");
        if (student != null) {
            System.out.println(student.toString());
        }

        System.out.println("按学生年龄降序排序查询");
        List<Student> students = studentOperatable.getStudentsOrderByAge();
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }

        System.out.println("按照学生系dept模糊查询并排序，分页显示");
        List<Student> studentList = studentOperatable.getStudentsByDeptLikeOrderLimit("S", 2, 3);
        Iterator<Student> it1 = studentList.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next().toString());
        }
    }

    @Override
    public Student getStudentInfoByNameLike(String sname) {
        Student student = new Student();
        try {
            connection = JdbcUtil.getInstance().getConnection();
            /*
             * 错误提示，使用通配符时 ? 不能出现在like的单引号中，如果要使用则需要将 % 一起赋值
             */
            String sql = "select * from student where sname like ?";
            stmt = connection.prepareStatement(sql);
            //正确写法
            stmt.setString(1, "%" + sname + "%");
            rs = stmt.executeQuery();
            if (rs.next()) {
                student.setSno(rs.getString(1));
                student.setSname(rs.getString(2));
                student.setSsex(rs.getString(3));
                student.setSage(rs.getInt(4));
                student.setDept(rs.getString(5));
                return student;
            } else {
                System.out.println("查无此人!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(rs);
            JdbcUtil.closeResource(stmt);
            JdbcUtil.closeResource(connection);
        }
        return null;
    }

    @Override
    public List<Student> getStudentsOrderByAge() {
        List<Student> students = new ArrayList<>(16);
        try {
            connection = JdbcUtil.getInstance().getConnection();
            String sql = "select * from student order by sage desc";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setSno(rs.getString(1));
                student.setSname(rs.getString(2));
                student.setSsex(rs.getString(3));
                student.setSage(rs.getInt(4));
                student.setDept(rs.getString(5));
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(rs);
            JdbcUtil.closeResource(stmt);
            JdbcUtil.closeResource(connection);
        }
        return students;
    }

    @Override
    public List<Student> getStudentsByDeptLikeOrderLimit(String dept, int currPage, int pageSize) {
        List<Student> students = new ArrayList<>(16);
        try {
            connection = JdbcUtil.getInstance().getConnection();
            String sql = "select * from student where sdept like ? order by sdept asc limit ?,?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + dept + "%");
            stmt.setInt(2, (currPage - 1) * pageSize);//limit中m的值,忽略当前页-1*每页容量的数据
            stmt.setInt(3, pageSize);//limit中n的值,忽略之后显示页面容量的数据,即可实现显示指定当前页的数据
            rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setSno(rs.getString(1));
                student.setSname(rs.getString(2));
                student.setSsex(rs.getString(3));
                student.setSage(rs.getInt(4));
                student.setDept(rs.getString(5));
                students.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(rs);
            JdbcUtil.closeResource(stmt);
            JdbcUtil.closeResource(connection);
        }
        return students;
    }
}
