package com.mysql;

import java.util.Iterator;
import java.util.List;

/**
 * @author lc
 * @date 2019/8/5 18:57
 */
public class MySQLDemo {
    static StudentDAO stuDAO = new StudentDAO();
    public static void main(String[] args) {
        selectAll();
        selectBySno();
        deleteBySno();
        deleteAll();
        addStu();
        updateStu();
    }

    /**
     * 查询全部学生
     */
    static void selectAll(){
        List<Student> stuList = stuDAO.getAllStuInfo();
        Iterator<Student> it = stuList.iterator();
        while (it.hasNext()){
            Student student = it.next();
            System.out.println(student.toString());
        }
    }

    /**
     * 按学号查
     */
    static void selectBySno(){
        String stuNo = "200215121";
        Student student = stuDAO.getStuInfo(stuNo);
        System.out.println(student.toString());
    }

    /**
     * 按学号删
     */
    static  void deleteBySno(){
        String stuNo = "200215125";
        stuDAO.deleteOneStu(stuNo);
    }

    /**
     * 删除全部学生信息
     */
    static void deleteAll(){
        stuDAO.deleteAllStu();
    }

    /**
     * 添加学生
     */
    static void addStu(){
        Student stuAdd = new Student();
        stuAdd.setSno("200211431");
        stuAdd.setSname("nick");
        stuAdd.setSsex("男");
        stuAdd.setSage(20);
        stuAdd.setDept("MS");
        stuDAO.addStu(stuAdd);
    }

    /**
     * 修改学生信息
     */
    static void updateStu(){
        Student stu = new Student();
        stu.setSno("200215122");
        stu.setSname("nick");
        stu.setSsex("男");
        stu.setSage(20);
        stu.setDept("MS");
        stuDAO.updateStu(stu);
    }
}
