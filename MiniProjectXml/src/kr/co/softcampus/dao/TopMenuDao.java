package kr.co.softcampus.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softcampus.beans.BoardInfoBean;

import java.util.*;

@Repository
public class TopMenuDao {

	

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;


	public List<BoardInfoBean> getTopMenuList() {
		
		/* 만약 db에서 가져온 작업이 여러 번 한다고 한다면, 여러 메서드 호출해도 됨.
		 * 
		 * */
		
		// topmenu.get_topmenu_lis는 top_menu_mapper.xml에 있는 namespace와 id
		List<BoardInfoBean> topMenuList = sqlSessionTemplate.selectList("topmenu.get_topmenu_list");
		
		return topMenuList;
	}
	

}



