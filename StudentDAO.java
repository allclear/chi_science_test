package com.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lc
 * @date 2019/8/5 19:22
 */
public class StudentDAO {

    /**
     * 根据学号查询学生信息
     *
     * @param sno
     * @return Student
     */
    public Student getStuInfo(String sno) {
        DBManager db = new DBManager();
        String sql = "select * from student where sno='" + sno + "'";
        ResultSet rs = db.executeQuery(sql);
        Student stu = new Student();
        if (rs != null) {
            try {
                while (rs.next()) {
                    stu = new Student();
                    stu.setSno(rs.getString(1));
                    stu.setSname(rs.getString(2));
                    stu.setSsex(rs.getString(3));
                    stu.setSage(rs.getInt(4));
                    stu.setDept(rs.getString(5));
                }
                db.releaseDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return stu;
    }

    /**
     * 获取全体学生的信息
     *
     * @return stuInfoList
     */
    public List<Student> getAllStuInfo() {
        List<Student> stuInfoList = new ArrayList();
        DBManager db = new DBManager();
        String sql = "select * from student";
        ResultSet rs = db.executeQuery(sql);
        try {
            while (rs.next()) {
                Student stu = new Student();
                stu.setSno(rs.getString(1));
                stu.setSname(rs.getString(2));
                stu.setSsex(rs.getString(3));
                stu.setSage(rs.getInt(4));
                stu.setDept(rs.getString(5));
                stuInfoList.add(stu);
            }
            db.releaseDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.releaseDB();
        return stuInfoList;
    }

    /**
     * 按学号删除学生，返回true则删除成功
     *
     * @param sno
     * @return flag
     */
    public boolean deleteOneStu(String sno) {
        boolean flag = false;
        DBManager db = new DBManager();
        String sql = "delete from student where sno='" + sno + "'";
        int count = db.executeUpdate(sql);
        if (count > 0) {
            flag = true;
            System.out.println("删除成功");
        } else {
            System.out.println("删除时出现错误");
        }
        db.releaseDB();
        return flag;
    }

    /**
     * 删除全部学生信息，返回true为删除成功
     *
     * @return
     */
    public boolean deleteAllStu() {
        boolean flag = false;
        DBManager db = new DBManager();
        String sql = "delete from student";
        int count = db.executeUpdate(sql);
        if (count > 0) {
            flag = true;
            System.out.println("删除成功");
        } else {
            System.out.println("删除时出现错误");
        }
        db.releaseDB();
        return flag;
    }

    /**
     * 添加学生信息,返回true则添加成功
     *
     * @param stu
     * @return
     */
    public boolean addStu(Student stu) {
        boolean flag = false;
        DBManager db = new DBManager();
        String sql = "insert into student values" +
                "('" + stu.getSno() + "', '" + stu.getSname() + "','" + stu.getSsex() + "'," +
                stu.getSage() + ",'" + stu.getDept() + "')";
        int count = db.executeUpdate(sql);
        if (count > 0) {
            flag = true;
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
        db.releaseDB();
        return flag;
    }

    /**
     * 更新学生信息，按照学号更新
     * @param stu
     * @return
     */
    public boolean updateStu(Student stu) {
        boolean flag = false;
        DBManager db = new DBManager();
        String sql = "update student set sname='" + stu.getSname() + "',ssex='" + stu.getSsex() + "',sage=" + stu.getSage() +
                ",sdept='" + stu.getDept() + "' where sno='" + stu.getSno() + "';";
        int count = db.executeUpdate(sql);
        if (count > 0) {
            flag = true;
            System.out.println("更新成功s");
        } else {
            System.out.println("更新错误");
        }
        db.releaseDB();
        return flag;
    }


}
