package kr.co.softcampus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.softcampus.beans.BoardInfoBean;
import kr.co.softcampus.beans.ContentBean;
import kr.co.softcampus.service.MainService;
import kr.co.softcampus.service.TopMenuService;

@Controller
public class MainController {

	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private TopMenuService topMenuService;
	
	@GetMapping("/main")
	public String main(Model model) {
		
		ArrayList<List<ContentBean>> list = new ArrayList<List<ContentBean>>();
	
		for(int i=1; i<=4; i++) {
			List<ContentBean> tempList = mainService.getMainList(i);
			list.add(tempList);
		}
		
		model.addAttribute("list", list);
		
		List<BoardInfoBean> board_list = topMenuService.getTopMenuList();
		
		model.addAttribute("board_list", board_list);

		return "main";
	}
	
}
