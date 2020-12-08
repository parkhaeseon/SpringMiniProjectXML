package kr.co.softcampus.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softcampus.beans.BoardInfoBean;
import kr.co.softcampus.beans.UserBean;
import kr.co.softcampus.service.TopMenuService;

import java.util.*;

public class TopMenuInterceptor implements HandlerInterceptor {


	/* Java 프로젝트에서는 인터셉터에서 Bean을 주입받지 못 하지만
	 * xml 프로젝트에서는 인터셉터에서 Bean을 주입받을 수 있다.
	 * 
	 */
	
	
	@Autowired
	private TopMenuService topMenuService;
	
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub


		List<BoardInfoBean> topMenuList 
		= topMenuService.getTopMenuList();
		
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		
		return true;
	}
	
	
}



