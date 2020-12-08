package kr.co.softcampus.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.softcampus.beans.UserBean;

/* xml로 프로젝트 세팅한 것에서 Java Resources → src → kr.co.softcampus.controller → HomeController.java와 같다.
 * 비교하면서 학습할 것.
 */

@Controller
public class HomeController {

	/* loginUserBean는 SessionScope로 등록한 Bean임.
	 * SessionScope는 브라우저가 최초의 요청을 발생시키는 그 시점에.
	 * Java일 경우 최초의 요청의 발생이 될 때 그 때 주입.
	 * xml일 경우 서버가 가동될때 자동으로 주입하려고 시도.
	 * 하지만. 해당 BEan은 sessionscopre이라서 오류 발생.
	 */
	
//	@Resource(name = "loginUserBean")
//	@Lazy // 서버가 가동될 떄 안 하고, 브라우저가 최초로 요청했을 때 반응
//	private UserBean loginUserBean;
	
	
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		// 그냥 아무 주소만 치고 들어오면 home() 함수가 호출된다. 

		//System.out.println("loginUserBean = " + loginUserBean);
		
		return "redirect:/main";
	}
	
	
}
