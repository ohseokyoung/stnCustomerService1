package com.multi.campus.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.campus.service.BoardReplyService;
import com.multi.campus.vo.BoardReplyVO;

@Controller
@RequestMapping("/boardReply")
public class BoardReplyController {
	@Autowired
	BoardReplyService service;
	
	//댓글등록
	@PostMapping("/write")
	@ResponseBody
	public String replyWrite(BoardReplyVO vo,HttpSession session) {//coment와no는 BoardReplyVO에서 userid는HttpSessiondptj 
		vo.setUserid((String)session.getAttribute("logId"));
		int result=service.replyInsert(vo);
		
		return result+"";
	}
	
	//댓글목록
	@GetMapping("/list")
	@ResponseBody
	public List<BoardReplyVO> replyList(int no) {
		List<BoardReplyVO> replyList=service.replySelect(no);
		return replyList;
	}
	
	//댓글수정(DB)
	@PostMapping("/editOk")
	@ResponseBody
	public String replyEditOk(BoardReplyVO vo) {
		return service.replyUpdate(vo)+"";
	}
	//댓글 삭제
	@GetMapping("/delete")
	@ResponseBody
	public String replyDelete(int replyno) {
		return service.replyDelete(replyno)+"";
	}
}
