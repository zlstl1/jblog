package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	@Transactional
	public int join(UserVo userVo) {
		int count = 0;
		count += userDao.insertUser(userVo);
		count += blogDao.insertBlog(userVo);
		count += categoryDao.insertCategory(userVo);
		return count;
	}
	
	public UserVo login(UserVo userVo) {
		return userDao.login(userVo);
	}
	
	public boolean idcheck(String id) {
		return userDao.isExists(id);
	}
	
}
