package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;

	public void writePost(PostVo postVo) {
		sqlSession.insert("post.writePost",postVo);
	}
	
	public List<PostVo> getPost(int cateNo) {
		return sqlSession.selectList("post.getPost",cateNo);
	}
	
	public PostVo getMainPost(int postNo) {
		return sqlSession.selectOne("post.getMainPost",postNo);
	}
}
