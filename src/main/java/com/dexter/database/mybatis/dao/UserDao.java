package com.dexter.database.mybatis.dao;

import com.dexter.database.mybatis.domain.User;

public interface UserDao {
	
    int deleteById(Integer id);

    int insert(User user);

    int insertSelective(User user);

    User selectById(Integer id);

    int updateByIdSelective(User user);

    int update(User user);
}