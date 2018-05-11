package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertCategory(UserVo userVo) {
		return sqlSession.insert("category.insertCategory",userVo);
	}
	
	public List<CategoryVo> getCategory(String id) {
		return sqlSession.selectList("category.getCategoryById",id);
	}
	
	public CategoryVo getCategory(int cateNo) {
		return sqlSession.selectOne("category.getCategoryByCateNo",cateNo);
	}
	
	public int addCategory(CategoryVo categoryVo) {
		sqlSession.insert("category.addCategory",categoryVo);
		return categoryVo.getCateNo();
	}
	
	public int deleteCategory(int cateNo) {
		return sqlSession.delete("category.deleteCategory",cateNo);
	}
	
	public int getNo(String id) {
		return sqlSession.selectOne("category.getNo",id);
	}
}
