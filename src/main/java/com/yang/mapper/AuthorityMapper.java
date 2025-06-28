package com.yang.mapper;

import com.yang.pojo.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AuthorityMapper {
    List<Authority> getAuthorityList();
    int addAuthority(Authority authority);
    Authority getAuthorityByNumber(String number);
    int updateAuthority(Authority authority);
    int deleteAuthorityByNumber(String number);
    //修改个人密码
    int updatePassword(String number,String password);
}
