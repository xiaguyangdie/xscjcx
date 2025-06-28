package com.yang.service;

import com.yang.pojo.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> getAuthorityList();
    int addAuthority(Authority authority);
    Authority getAuthorityByNumber(String number);
    int updateAuthority(Authority authority);
    int deleteAuthorityByNumber(String number);
    //修改个人密码
    int updatePassword(String number, String password);
}
