package com.yang.service;

import com.yang.pojo.Grade;

import java.util.List;
import java.util.Map;

public interface GradeService {
    List<Grade> getGradeList();
    Grade getGradeById(String id);
    int getGradeAvg(String id);
    int updateGrade(Grade grade);
    int deleteGradeById(String id);
    int addGrade(Grade grade);
    List<String> getGradeIdList();
}
