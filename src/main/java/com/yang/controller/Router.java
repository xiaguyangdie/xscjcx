package com.yang.controller;

import com.yang.pojo.Grade;
import com.yang.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Router {
    @Autowired
    GradeService gradeService;
    @RequestMapping("/a")
    public List<Grade> a(){
        return gradeService.getGradeList();
    }

}
