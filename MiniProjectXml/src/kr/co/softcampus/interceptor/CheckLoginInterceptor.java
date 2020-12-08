package kr.co.softcampus.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softcampus.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor {

	// Java에서 Interceptor는 Bean을 주입받지 못 한다.
	// xml에서의 Interceptor는 Bean을 주입 받을 수 있다.
	
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if(loginUserBean.isUserLogin()  == false) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/not_login");			
			return false;
		}
		
		return true;
	}
	
	
	
	
	
	
	
}
