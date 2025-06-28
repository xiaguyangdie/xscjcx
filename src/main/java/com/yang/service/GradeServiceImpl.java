package com.yang.service;

import com.yang.mapper.GradeMapper;
import com.yang.pojo.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GradeServiceImpl implements GradeService{
    @Autowired
    GradeMapper gradeMapper;
    @Override
    public List<Grade> getGradeList() {
        return gradeMapper.getGradeList();
    }

    @Override
    public int getGradeAvg(String id) {
        List<Grade> gradeList = gradeMapper.getGradeList();
        int size = gradeList.size();
        int[][] grades = new int[size][2];
        int i = 0;
        for (Grade grade : gradeList) {
            grades[i][0]= Integer.parseInt(grade.getId());
            grades[i][1] = grade.getC() + grade.getPython() + grade.getJAVA();
            i = i + 1;
        }
        int temp=0;
        for (i = 0; i < grades.length - 1; i++) {
            for (int j = i + 1; j < grades.length; j++) {
                if (grades[i][1] < grades[j][1]) {
                    temp = grades[i][1];
                    grades[i][1] = grades[j][1];
                    grades[j][1] = temp;
                    temp = grades[i][0];
                    grades[i][0] = grades[j][0];
                    grades[j][0] = temp;
                }
            }
        }
        for(i=0;i<grades.length;i++){
            if (id.equals((String.valueOf(grades[i][0])))){
                break;
            }
        }
        return i+1;
    }

    @Override
    public int updateGrade(Grade grade) {
        return gradeMapper.updateGrade(grade);
    }

    @Override
    public int deleteGradeById(String id) {
        return gradeMapper.deleteGradeById(id);
    }

    @Override
    public int addGrade(Grade grade) {
        return gradeMapper.addGrade(grade);
    }

    @Override
    public Grade getGradeById(String id) {
        return gradeMapper.getGradeById(id);
    }

    @Override
    public List<String> getGradeIdList() {
        return gradeMapper.getGradeIdList();
    }
}
