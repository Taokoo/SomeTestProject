package com.taokoo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.taokoo.model.SgdUser;

/**
 * 用户表dao
 * @author Toka
 *
 */
@Repository
@Mapper
public interface SgdUserMapper {
	
	SgdUser getUserById(int id);
	
	/* 通过账号查找用户 */
	SgdUser getUserByName(String userName);
	
	int insert(SgdUser user);

}
