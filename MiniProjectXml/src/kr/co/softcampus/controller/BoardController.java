package kr.co.softcampus.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softcampus.beans.ContentBean;
import kr.co.softcampus.beans.PageBean;
import kr.co.softcampus.beans.UserBean;
import kr.co.softcampus.service.BoardService;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Resource(name = "loginUserBean")
	@Lazy
	private UserBean loginUserBean;

	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx,
					   @RequestParam(value = "page", defaultValue = "1") int page,
					   Model model) {
		
		model.addAttribute("board_info_idx", board_info_idx);
		
		String boardInfoName = boardService.getBoardInfoName(board_info_idx);
		model.addAttribute("boardInfoName", boardInfoName);
		
		// page 넘김
		List<ContentBean> contentList = boardService.getContentList(board_info_idx, page);
		model.addAttribute("contentList", contentList);
		
		PageBean pageBean = boardService.getContentCnt(board_info_idx, page);
		
		model.addAttribute("pageBean", pageBean);
		
		// 글을 읽고 '목록보기'를 클릭했을 때 그 페이지로 가야하기 위해서는 jsp에 넘겨줘야한다. 추출한 page를 model을 통해 
		model.addAttribute("page", page);
		 
		return "board/main";
	}
	

	@GetMapping("/read")
	public String read(@RequestParam("board_info_idx") int board_info_idx,
						@RequestParam("content_idx") int content_idx,
						@RequestParam("page") int page,
						Model model) {
		
		System.out.println("10/27 page = " + page);
		
		model.addAttribute("board_info_idx", board_info_idx);
		//model.addAttribute("content_idx", content_idx);
	
		model.addAttribute("content_idx", content_idx);
		
		ContentBean readContentBean = boardService.getContentInfo(content_idx);
		model.addAttribute("readContentBean", readContentBean);
		
		model.addAttribute("loginUserBean", loginUserBean);
		
		model.addAttribute("page", page);
		
		return "board/read";
	}
	
	
	
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentBean") ContentBean writeContentBean,
						@RequestParam("board_info_idx") int board_info_idx) {
		
		
		// 게시판 번호 세팅
		writeContentBean.setContent_board_idx(board_info_idx);
		
		
		return "board/write";
	}
	
	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean,
							BindingResult result) {
		
		if(result.hasErrors()) {
			return "board/write";
		}
		
		// 호출
		boardService.addContentInfo(writeContentBean); // 게시판 인덱스 번호도 hidden으로 넘겨 받음.
		
		// @SeleceKey에서 실행된 Content_idx를 여기서 쓸 수 있음.
		// 주소를 통해 전달전달되었기 때문이다.
		// jsp에서 사용가능.(자동 전달)
		
		//테스트
		//System.out.println(writeContentBean.getContent_text());
		
		return "board/write_success";
	}
	
	
	@GetMapping("/modify")
	public String modify(@RequestParam("board_info_idx") int board_info_idx,
						 @RequestParam("content_idx") int content_idx,
						 @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
						 @RequestParam("page") int page,
						 Model model) {
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		
		ContentBean tempContentBean = boardService.getContentInfo(content_idx);
		
		modifyContentBean.setContent_writer_name(tempContentBean.getContent_writer_name());
		modifyContentBean.setContent_date(tempContentBean.getContent_date());
		modifyContentBean.setContent_subject(tempContentBean.getContent_subject());
		modifyContentBean.setContent_text(tempContentBean.getContent_text());
		modifyContentBean.setContent_file(tempContentBean.getContent_file());
		modifyContentBean.setContent_writer_idx(tempContentBean.getContent_writer_idx());
		modifyContentBean.setContent_board_idx(board_info_idx);
		modifyContentBean.setContent_idx(content_idx);
		
		model.addAttribute("page", page);
		
		return "board/modify";
	}
	
	
	// modify.jsp에서 hidden으로 글번호, 게시판 번호가 세팅됐기 때문에
	// modifyContentBean에는 둘 다 들어있다.
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
							 @RequestParam("page") int page, Model model,
							 BindingResult result) {
		
		model.addAttribute("page", page);
		
		System.out.println("오긴 오냐");
		if(result.hasErrors()) {
			System.out.println("오류 개수 = " + result.getErrorCount());
			
			for(ObjectError obj : result.getAllErrors()) {
                System.out.println("=====================================");
 
                System.out.println("메시지 : " + obj.getDefaultMessage());
                System.out.println("코드(무엇을 위반했는지) : " + obj.getCode());
                System.out.println("Object Name : " + obj.getObjectName());
			}
			
			return "board/modify";
		}
		
		boardService.modifyContentInfo(modifyContentBean);
		
		System.out.println("아래아래");
		
		return "board/modify_success";
		
	}
	
	
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("board_info_idx") int board_info_idx,
			 			 @RequestParam("content_idx") int content_idx,
			 			 Model model) {	
		
		boardService.deleteContentInfo(content_idx);
		
		model.addAttribute("board_info_idx", board_info_idx);
		
		return "board/delete";
	}
	
	
	@GetMapping("/not_writer")
	public String not_writer() {
		return "board/not_writer";
	}
	
	
	
	
	
	
}
