package com.taokoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taokoo.dao.SgdUserMapper;
import com.taokoo.model.SgdUser;

@Service
public class SgdUserService {

    @Autowired
    private SgdUserMapper sgdUserMapper;

    public SgdUser getUserById(int id) {
        return sgdUserMapper.getUserById(id);
    }

    public SgdUser getUserByName(String userName) {
        return sgdUserMapper.getUserByName(userName);
    }
    
    @Transactional
    public int addUser(SgdUser user) {
        return sgdUserMapper.insert(user);
    }
    
    public SgdUser login(String userName,String passWord) {
        return sgdUserMapper.login(userName, passWord);
    }

}
