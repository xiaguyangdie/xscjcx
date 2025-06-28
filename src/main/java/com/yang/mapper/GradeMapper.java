package com.yang.mapper;

import com.yang.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GradeMapper {
    List<Grade> getGradeList();
    Grade getGradeById(String id);
    int updateGrade(Grade grade);
    int deleteGradeById(String id);
    int addGrade(Grade grade);
    List<String> getGradeIdList();
}
