package com.yang.controller;
import com.yang.pojo.Authority;
import com.yang.pojo.Course;
import com.yang.pojo.Grade;
import com.yang.service.AuthorityService;
import com.yang.service.CourseService;
import com.yang.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class SelectController {
    @Autowired
    GradeService gradeService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    CourseService courseService;
    //跳转到学生个人页面
    @RequestMapping("/person")
    public String getGradeByNumber(String id, Model model, HttpSession session){
        //通过ssession获取当前用户信息
        Authority loginUser = (Authority) session.getAttribute("loginUser");
        id= loginUser.getNumber();
        Grade list =gradeService.getGradeById(id);
        if(list==null){
            model.addAttribute("errorCourse",loginUser.getName());
            model.addAttribute("errorNumber",loginUser.getNumber());
            return "student/errorCourse";
        }else {
            float i = (list.getC() + list.getJAVA() + list.getPython()) / 3;
            float sum = (list.getC() + list.getJAVA() + list.getPython());
            int level = gradeService.getGradeAvg(id);
            model.addAttribute("list",list);
            model.addAttribute("average",i);
            model.addAttribute("sum",sum);
            model.addAttribute("level",level);
            return "student/student";
        }
    }
    //跳转到管理员成绩查询页面
    @RequestMapping("/admin/hostPage")
    public String getGradeList(HttpSession session,Model model){
        Authority loginUser = (Authority) session.getAttribute("loginUser");
        model.addAttribute("name",loginUser.getName());
        List<Grade> gradeList = gradeService.getGradeList();
        model.addAttribute("list",gradeList);
        return "admin/hostPage";
    }
    //管理员跳转到修改页面
    @RequestMapping("/admin/toUpdateGrade")
    public String toUpdateGrade(String id,Model model){
        Grade grade = gradeService.getGradeById(id);
        model.addAttribute("grade",grade);
        return "admin/update";
    }
    //修改完成后跳转到管理员成绩查询页面
    @RequestMapping("/admin/updateGrade")
    public String updateGrade(Grade grade){
        gradeService.updateGrade(grade);
        return "redirect:/admin/hostPage";
    }
    //管理员删除成绩后返回其主页
    @RequestMapping("/admin/del")
    public String deleteGradeById(String id){
        gradeService.deleteGradeById(id);
        return "redirect:/admin/hostPage";
    }
    //管理员跳转到增加成绩信息页面
    @RequestMapping("/admin/toAddGrade")
    public String toAddGrade(){
        return "admin/add";
    }
    //增加后返回管理员成绩主页
    @RequestMapping("/admin/addGrade")
    public String addGrade(Grade grade){
        gradeService.addGrade(grade);
        return "redirect:/admin/hostPage";
    }
    //管理员查看每个学生成绩详细情况
    @RequestMapping("/admin/toPerson")
    public String toPerson(String id,Model model,HttpSession session){
        Authority loginUser = (Authority) session.getAttribute("loginUser");
        model.addAttribute("name",loginUser.getName());
        Grade list =gradeService.getGradeById(id);
        float i = (list.getC() + list.getJAVA() + list.getPython()) / 3;
        float sum = (list.getC() + list.getJAVA() + list.getPython());
        int level = gradeService.getGradeAvg(id);
        model.addAttribute("list",list);
        model.addAttribute("average",i);
        model.addAttribute("sum",sum);
        model.addAttribute("level",level);
        return "admin/details";
    }
    //跳转到权限详情页面
    @RequestMapping("/admin/authPage")
    public String getAuthList(HttpSession session,Model model){
        Authority loginUser = (Authority) session.getAttribute("loginUser");
        model.addAttribute("name",loginUser.getName());
        List<Authority> authorityList = authorityService.getAuthorityList();
        model.addAttribute("list",authorityList);
        return "admin/authPage";
    }
    //返回管理员首页
    @RequestMapping("/admin/admin")
    public String toHost(){
        return "admin/admin";
    }
    //返回学生首页
    @RequestMapping("/student/hostPage")
    public String toHostStudent(HttpSession session,Model model){
        return "student/hostPage";
    }
    //跳转到权限增加页面
    @RequestMapping("/admin/toAddAuth")
    public String toAddAuth(){
        return "admin/addAuth";
    }
    //增加权限后返回权限主页
    @RequestMapping("/admin/addAuth")
    public String addAuth(Authority authority){
       authorityService.addAuthority(authority);
       return "redirect:/admin/authPage";
    }
    //管理员跳转到权限修改页面
    @RequestMapping("/admin/toUpdateAuth")
    public String toAddAuth(String number,Model model){
        Authority byNumber = authorityService.getAuthorityByNumber(number);
        model.addAttribute("auth",byNumber);
        return "admin/updateAuth";
    }
    //管理员权限修改后跳转到权限主页
    @RequestMapping("/admin/updateAuth")
    public String updateAuth(Authority authority){
        authorityService.updateAuthority(authority);
        return "redirect:/admin/authPage";
    }
    //管理员删除权限记录并返回权限主页
    @RequestMapping("/admin/delAuth")
    public String delAuth(String number){
        authorityService.deleteAuthorityByNumber(number);
        return "redirect:/admin/authPage";
    }
    //跳转到课程详情页面
    @RequestMapping("/admin/coursePage")
    public String getCourseList(HttpSession session,Model model){
        Authority loginUser = (Authority) session.getAttribute("loginUser");
        model.addAttribute("name",loginUser.getName());
        List<Course> courseList = courseService.getCourseList();
        model.addAttribute("list",courseList);
        return "admin/coursePage";
    }
    //跳转到课程主页面
    @RequestMapping("/admin/toAddCourse")
    public String toAddCourse(){
        return "admin/addCourse";
    }
    //增加课程信息后返回课程主页面
    @RequestMapping("/admin/addCourse")
    public String addCourse(Course course){
        courseService.addCourse(course);
        return "redirect:/admin/coursePage";
    }
    //跳转到课程主页面
    @RequestMapping("/admin/toUpdateCourse")
    public String toUpdateCourse(HttpSession session,Model model,String cid){
        Authority loginUser = (Authority) session.getAttribute("loginUser");
        model.addAttribute("name",loginUser.getName());
        Course course = courseService.getCourseByCid(cid);
        model.addAttribute("course",course);
        return "admin/updateCourse";
    }
    //修改课程信息后返回课程信息主页
    @RequestMapping("/admin/updateCourse")
    public String updateCourse(Course course){
        courseService.updateCourseByCourse(course);
        return "redirect:/admin/coursePage";
    }
    //管理员删除课程记录并返回课程信息主页
    @RequestMapping("/admin/delCourse")
    public String delCourse(String cid){
        courseService.deleteCourseByCid(cid);
        return "redirect:/admin/coursePage";
    }
    //修改个人密码
    @RequestMapping("/teacher/toUpdatePwd")
    public String toUpdatePwdTeacher(){
        return "teacher/updatePassword";
    }
    //修改密码后重新登录
    @RequestMapping("/teacher/reLogin")
    public String reLoginTeacher(String password, String password1, HttpSession session, Model model){
        if (password.equals(password1)){
            Authority loginUser = (Authority) session.getAttribute("loginUser");
            String number = loginUser.getNumber();
            authorityService.updatePassword(number,password);
            session.removeAttribute("loginUser");
            return "redirect:/login";
        }else{
            //model.addAttribute("password1",password1);
            return "redirect:/teacher/toUpdatePwd";
        }

    }
    //修改个人密码
    @RequestMapping("/admin/toUpdatePwd")
    public String toUpdatePwdAdmin(){
        return "admin/updatePassword";
    }
    //修改密码后重新登录
    @RequestMapping("/admin/reLogin")
    public String reLoginAdmin(String password, String password1, HttpSession session, Model model){
        if (password.equals(password1)){
            Authority loginUser = (Authority) session.getAttribute("loginUser");
            String number = loginUser.getNumber();
            authorityService.updatePassword(number,password);
            session.removeAttribute("loginUser");
            return "redirect:/login";
        }else{
            //model.addAttribute("password1",password1);
            return "redirect:/admin/toUpdatePwd";
        }

    }
    //修改个人密码
    @RequestMapping("/person/toUpdatePwd")
    public String toUpdatePwdStu(){
        return "student/updatePassword";
    }
    //修改密码后重新登录
    @RequestMapping("/person/reLogin")
    public String reLoginStu(String password, String password1, HttpSession session, Model model){
        if (password.equals(password1)){
            Authority loginUser = (Authority) session.getAttribute("loginUser");
            String number = loginUser.getNumber();
            authorityService.updatePassword(number,password);
            session.removeAttribute("loginUser");
            return "redirect:/login";
        }else{
            //model.addAttribute("password1",password1);
            return "redirect:/person/toUpdatePwd";
        }

    }
    //教师首页
    @RequestMapping("/teacher/teacher")
    public String toTeacher(){
        return "teacher/teacher";
    }
    //跳转到教学任务详情页面
    @RequestMapping("/teacher/courseTask")
    public String getCourseTask(HttpSession session,Model model){
        Authority loginUser = (Authority) session.getAttribute("loginUser");
        model.addAttribute("name",loginUser.getName());
        String tname = loginUser.getName();
        List<Course> courseByName = courseService.getCourseByName(tname);
        model.addAttribute("courseByName",courseByName);
        return "teacher/courseTask";
    }
    //根据学生id查询学生的分数信息
    @RequestMapping("/select/courseID")
    public String SelectCourseId(String id,Model model){
        List<String> gradeIdList = gradeService.getGradeIdList();
        if (gradeIdList.contains(id)){
            Grade grade = gradeService.getGradeById(id);
            model.addAttribute("grade",grade);
            return "admin/findGrade";
        }else {
            model.addAttribute("error","不存在这个学生");
            return "admin/errorCourse";
        }
    }
}
