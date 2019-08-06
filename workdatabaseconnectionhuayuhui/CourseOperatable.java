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
}
