package kr.co.softcampus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.softcampus.service.UserService;

@RestController
public class RestApiController {

	
	@Autowired
	private UserService userService;
	
	// RestAPI 같은 경우 Client가 Server로 데이터를 보낼 때 파라미터 보다는
	// Path Value(주소에다가 데이터를 붙인다.)로 많이 보냄
	@GetMapping("/user/checkUserIdExist/{user_id}")
	public String checkUserIdExist(@PathVariable String user_id) {
		boolean chk = userService.checkUserIdExist(user_id);
		
		return chk + ""; // 문자열로 변환하기 위함.
	}
	
	
	
}







