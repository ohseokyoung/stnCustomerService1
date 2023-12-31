package com.multi.campus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.campus.service.CustomerService;
import com.multi.campus.vo.CustomerVO;
import com.multi.campus.vo.PagingVO;

@Controller
public class CustomerController {
	@Autowired
	CustomerService service;
	
	//고객센터메인
	@GetMapping("/customer/customerMain")
	public ModelAndView CustomerMain(PagingVO pVO){

			ModelAndView mav=new ModelAndView();
			//총레코드 수
			pVO.setTotalRecord(service.totalRecord(pVO));
			//DB선택(page,검색)
			List<CustomerVO> list=service.customerPageList(pVO);
			
			mav.addObject("pVO", pVO);
			mav.addObject("list", list);
			mav.setViewName("customer/customerMain");
			return mav;	
		
	}
	//내 문의사항
	@GetMapping("/customer/myquestion")
	public ModelAndView myquestion(PagingVO pVO){

			ModelAndView mav=new ModelAndView();
			
			pVO.setTotalRecord(service.totalRecord(pVO));
			
			List<CustomerVO> list=service.customerPageList(pVO);
			
			mav.addObject("pVO", pVO);
			mav.addObject("list", list);
			mav.setViewName("customer/myquestion");
			return mav;	
		
	}
	
	//고객문의글쓰기
	@GetMapping("/customer/customerWrite")
	public String CustomerWrite() {
		
		return "customer/customerWrite";
	}
	
	
	//문의 등록
	@PostMapping("/customer/customerWriteOk")
	public ModelAndView customerWriteOk(CustomerVO vo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		vo.setIp(request.getRemoteAddr());
		vo.setUserid((String)request.getSession().getAttribute("logId"));
		
		int result = service.customerInsert(vo);
		if(result>0) {//글등록
			mav.setViewName("redirect:customerMain");
		}else {//등록실패
			mav.addObject("msg", "등록");
			mav.setViewName("customer/customerWrite");
		}
		
		return mav;
	}
	
	//문의 내용보기
	@GetMapping("/customer/customerView")
	public ModelAndView customerView(int no, PagingVO pVO) {
		
		CustomerVO vo= service.customerSelect(no);
		ModelAndView mav= new ModelAndView();
		mav.addObject("vo", vo);
		mav.addObject("pVO", pVO);
		mav.setViewName("customer/customerView");
		
		return mav;
	}
	//문의 수정
	@GetMapping("/customer/customerEdit")
	public ModelAndView customerEdit(int no) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("vo", service.customerSelect(no));
		mav.setViewName("customer/customerEdit");
		return mav;
	}
	//문의 수정(DB)
	@PostMapping("/customer/customerEditOk")
	public ModelAndView customerEditOk(CustomerVO vo) {
		int result=service.customerUpdate(vo);
		ModelAndView mav=new ModelAndView();
		if(result>0) {//수정성공
			mav.setViewName("redirect:customerView?no="+vo.getNo());
		}else {//수정실패
			mav.addObject("msg","수정");
			mav.setViewName("customer/customerResult");
		}
		return mav;
	}
	//문의 삭제(DB)
	@GetMapping("/customer/delete")
	public ModelAndView customerDelete(int no) {
		ModelAndView mav=new ModelAndView();
		int result=service.customerDelete(no);
		if(result>0) {//삭제성공
			mav.setViewName("redirect:customerMain");
		}else {//삭제실패
			mav.setViewName("redirect:customerView?no="+no);
		}
		return mav;
	}
	
	//문의 답변(DB)
	@PostMapping("/customer/replyOk")
	public ModelAndView replyOk(int no) {
		ModelAndView mav = new ModelAndView();
		int result= service.replyWrite(no);
		
		if(result>0) {
			mav.setViewName("redirect:customerMain");
		}else {
			mav.setViewName("redirect:customerView?no="+no);
		}
		return mav;
		
	}
}
