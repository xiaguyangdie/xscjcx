package com.yang.mapper;

import com.yang.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CourseMapper {
    //获取所有课程信息
    List<Course> getCourseList();
    //增加课程信息
    int addCourse(Course course);
    //修改课程信息
    int updateCourseByCourse(Course course);
    //根据课程id查询课程信息
    Course getCourseByCid(String cid);
    //根据课程id删除课程信息
    int deleteCourseByCid(String cid);
    //根据登录的教师名字查询课程信息
    List<Course> getCourseByName(String tname);
}
