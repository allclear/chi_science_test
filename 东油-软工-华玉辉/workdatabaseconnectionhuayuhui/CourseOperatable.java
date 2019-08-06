package com.train.databaseparctice.workdatabaseconnectionhuayuhui;

import java.util.List;

public interface CourseOperatable {
    /**
     * 获取数据库信息。
     * @return
     */
    List<Course> findCourseInfo();
    /**
     * 修改信息
     */
    int updateCourseById(Course course);
    /**
     * 添加数据
     */
    int insertCourse(Course course);
    /**
     * 根据ID删除数据
     */
    int deleteCourseById(int id);

    /**
     * 根据名称模糊查询
     * @return
     */
    List<Course> findByUserNameLike(String name);

    /**
     * 根据某个字段进行降序操作
     * @return
     */
    List<Course> findOrderByUserId(int id);

    /**
     * 根据某个字段模糊查找并排序（升序），
     * 然后分页获取第二页数据的操作（每页显示2条）
     * @return
     */
    List<Course> findByUserNameLikeOrderLimit(int id,int currPage ,int pageSize);


}
