package com.yang.controller;

import com.yang.pojo.Authority;
import com.yang.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class loginController {
    @Autowired
    AuthorityService authorityService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session) {
        session.removeAttribute("loginUser");
        return "login";
    }


    @RequestMapping("/toLogin")
    public String toLogin(@RequestParam("number") String number, @RequestParam("password") String password, Model model, HttpSession session) {
        List<Authority> authorities = authorityService.getAuthorityList();
        for (Authority authority : authorities) {
            if ((number.equals(authority.getNumber())) && (password.equals(authority.getPassword()))) {
                session.setAttribute("loginUser",authority);
                if (authority.getAccess().equals("root")){
                    return "redirect:/admin.html";
                } else {
                    if (authority.getAccess().equals("v1")){
                        return "redirect:/teacher.html";
                    }else {
                        return "redirect:/student.html";
                    }

                }
            } else {
                model.addAttribute("msg", "用户名或者密码错误");
            }
        }
        return "login";
    }
}

