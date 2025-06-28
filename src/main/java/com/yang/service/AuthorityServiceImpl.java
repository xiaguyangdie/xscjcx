package com.yang.service;

import com.yang.mapper.AuthorityMapper;
import com.yang.pojo.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityMapper authorityMapper;
    @Override
    public List<Authority> getAuthorityList() {
        return authorityMapper.getAuthorityList();
    }

    @Override
    public int addAuthority(Authority authority) {
        return authorityMapper.addAuthority(authority);
    }

    @Override
    public Authority getAuthorityByNumber(String number) {
        return authorityMapper.getAuthorityByNumber(number);
    }

    @Override
    public int updateAuthority(Authority authority) {
        return authorityMapper.updateAuthority(authority);
    }

    @Override
    public int deleteAuthorityByNumber(String number) {
        return authorityMapper.deleteAuthorityByNumber(number);
    }
    @Override
    public int updatePassword(String number, String password) {
        return authorityMapper.updatePassword(number,password);
    }
}
