package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryVo> getCategory(String id) {
		return categoryDao.getCategory(id);
	}
	
	public CategoryVo getCategory(int cateNo) {
		return categoryDao.getCategory(cateNo);
	}
	
	public CategoryVo addCategory(CategoryVo categoryVo) {
		int cateNo = categoryDao.addCategory(categoryVo);
		int no = categoryDao.getNo(categoryVo.getId());
		CategoryVo getCategoryVo = categoryDao.getCategory(cateNo);
		getCategoryVo.setNo(no);
		return getCategoryVo;
	}
	
	public int deleteCategory(int cateNo) {
		CategoryVo cateVo = categoryDao.getCategory(cateNo);
		int delCateNo = 0;
		if(cateVo.getPostTime()==0) {
			if(categoryDao.deleteCategory(cateNo)!=0) {
				delCateNo = cateNo;
			}
		}
		
		return delCateNo;
	}
}
