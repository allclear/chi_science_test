package com.train.day19;

import java.util.List;

/**
 * @author lc
 * @date 2019/8/6 19:07
 */
public interface StudentOperatable {
    /**
     * 按照学生姓名模糊查询学生信息
     *
     * @param sname
     * @return 返回的是一个学生对象，如果没有查询到则返回null
     */
    Student getStudentInfoByNameLike(String sname);

    /**
     * 获取全部学生信息，按学生年龄sage降序排序
     *
     * @return 返回List<Student>
     */
    List<Student> getStudentsOrderByAge();

    /**
     * 查询学生信息
     * 学生所在系dept的模糊查询，并升序排序
     *分页显示,自定义每页显示数量，当前页
     * @param dept
     * @param currPage
     * @param pageSize
     * @return
     */
    List<Student> getStudentsByDeptLikeOrderLimit(String dept, int currPage, int pageSize);
}
