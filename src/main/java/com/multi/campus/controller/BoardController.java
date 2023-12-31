package com.multi.campus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.campus.service.BoardService;
import com.multi.campus.vo.BoardVO;
import com.multi.campus.vo.PagingVO;

@Controller
public class BoardController {
	@Autowired
	BoardService service;
	//게시판
	@GetMapping("/board/list")
	public ModelAndView boardList(PagingVO pVO) {
		ModelAndView mav=new ModelAndView();
		//총레코드 수
		pVO.setTotalRecord(service.totalRecord(pVO));
		//DB선택(page,검색)
		List<BoardVO> list=service.boardPageList(pVO);
		
		mav.addObject("pVO", pVO);
		mav.addObject("list", list);
		mav.setViewName("board/boardList");
		return mav;
	}
	//글쓰기폼
	@GetMapping("/board/write")
	public String boardWrite() {
		return "board/boardWrite";
	}
	//글등록(DB)
	@PostMapping("/board/writeOk")

	public ModelAndView boardWriteOk(BoardVO vo, HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		vo.setIp(request.getRemoteAddr());
		vo.setUserid((String)request.getSession().getAttribute("logId"));
		
		int result = service.boardInsert(vo);
		if(result>0) {//글등록
			mav.setViewName("redirect:list");
		}else {//등록실패
			mav.addObject("msg", "등록");
			mav.setViewName("board/boardResult");
		}
		return mav;
	}
	
	//글내용보기
	@GetMapping("/board/view")
	public ModelAndView boardView(int no, PagingVO pVO) {
		
		service.hitCount(no);//조회수 증가
		BoardVO vo=service.boardSelect(no);
		ModelAndView mav= new ModelAndView();
		mav.addObject("vo", vo);
		mav.addObject("pVO", pVO);
		mav.setViewName("board/boardView");
		
		return mav;
	}
	//글수정 폼
	@GetMapping("/board/edit")
	public ModelAndView boardEdit(int no) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("vo",service.boardSelect(no));
		mav.setViewName("board/boardEdit");
		return mav;
	}
	//글수정(DB)
	@PostMapping("/board/editOk")
	public ModelAndView boardEditOk(BoardVO vo) {
		int result=service.boardUpdate(vo);
		ModelAndView mav=new ModelAndView();
		if(result>0) {//수정성공
			mav.setViewName("redirect:view?no="+vo.getNo());
		}else {//수정실패
			mav.addObject("msg","수정");
			mav.setViewName("board/boardResult");
		}
		return mav;
	}
	//게시글 삭제
	@GetMapping("/board/delete")
	public ModelAndView boardDelete(int no) {
		ModelAndView mav=new ModelAndView();
		int result=service.boardDelete(no);
		if(result>0) {//삭제성공
			mav.setViewName("redirect:list");
		}else {//삭제실패
			mav.setViewName("redirect:view?no="+no);
		}
		return mav;
	}
}
