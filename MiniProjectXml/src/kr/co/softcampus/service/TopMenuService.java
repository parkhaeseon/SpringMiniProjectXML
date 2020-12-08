package kr.co.softcampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softcampus.beans.BoardInfoBean;
import kr.co.softcampus.dao.TopMenuDao;

import java.util.*;

@Service
public class TopMenuService {

	
	@Autowired
	private TopMenuDao topMenuDao;
	
	
	public List<BoardInfoBean> getTopMenuList() {
		
		List<BoardInfoBean> topMenuList = topMenuDao.getTopMenuList();
		
		return topMenuList;
	}
	
	
	
	
	
	
	
}


