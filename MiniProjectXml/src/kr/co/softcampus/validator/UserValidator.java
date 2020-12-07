package kr.co.softcampus.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.softcampus.beans.UserBean;

public class UserValidator implements Validator {

	// 유효성 검사
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		// 유효성 검사를 하기 위한 Bean 객체가 target으로 넘어옴
		// 형변환 필요
		
		UserBean userBean = (UserBean)target;
		
		
		// 똑같이 UserBean 클래스로 사용하긴 하지만, Bean의 이름을 추출하여 사용할 수 있다.
		String beanName = errors.getObjectName();
		System.out.println("beanName = " + beanName);
		
		if(beanName.equals("joinUserBean") || beanName.equals("modifyUserBean")) {
			if(userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}				
		}			
		
		if(beanName.equals("joinUserBean")) {
			// 중복확인
			if(userBean.isUserIdExist() == false) {
				errors.rejectValue("user_id", "DontCheckUserIdExist");
			}	
		}
		
	}

	
	
}
