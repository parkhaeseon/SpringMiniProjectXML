package kr.co.softcampus.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softcampus.beans.UserBean;

@Repository // 이거 써줘야함.
public class UserDao {

	
	// xml 프로젝트는 mapper를 주입받는게 아니라 SqlSessionTemplate 사용.
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;


	
	
	
	public String checkUserIdExist(String user_id) {
		return sqlSessionTemplate.selectOne("user.checkUserIdExist", user_id);
	}
	
	
	public void addUserInfo(UserBean joinUserBean) {
		sqlSessionTemplate.insert("user.addUserInfo", joinUserBean);

	}
	
	public UserBean getLoginUserInfo(UserBean tempLoginUserBean) {
		return sqlSessionTemplate.selectOne("user.getLoginUserInfo", tempLoginUserBean);
		
	}
	
	public UserBean getModifyUserInfo(int user_idx) {
		return sqlSessionTemplate.selectOne("user.getModifyUserInfo", user_idx);
	}
	
	
	public void modifyUserInfo(UserBean modifyUserBean) {
		sqlSessionTemplate.update("user.modifyUserInfo", modifyUserBean);
	}
	
}


