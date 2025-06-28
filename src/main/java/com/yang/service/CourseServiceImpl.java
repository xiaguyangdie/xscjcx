package com.yang.service;

import com.yang.mapper.CourseMapper;
import com.yang.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseMapper courseMapper;
    @Override
    public List<Course> getCourseList() {
        return courseMapper.getCourseList();
    }

    @Override
    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    @Override
    public Course getCourseByCid(String cid) {
        return courseMapper.getCourseByCid(cid);
    }

    @Override
    public int deleteCourseByCid(String cid) {
        return courseMapper.deleteCourseByCid(cid);
    }

    @Override
    public int updateCourseByCourse(Course course){
        return courseMapper.updateCourseByCourse(course);
    }
    //根据登录的教师名字查询课程信息

    @Override
    public List<Course> getCourseByName(String tname) {
        return courseMapper.getCourseByName(tname);
    }
}
