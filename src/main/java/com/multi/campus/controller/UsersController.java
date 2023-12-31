package com.multi.campus.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.multi.campus.service.UsersService;
import com.multi.campus.vo.UsersVO;

@Controller 
public class UsersController {

	@Autowired
	UsersService service;
	
	
	//회원가입폼
	@GetMapping("/users/usersForm")
	public String usersForm() {
		return "users/userForm";
	}
	//아이디 중복검사
	@GetMapping("/users/idCheck")
	public ModelAndView idCheck(String userid) {
		System.out.println("userid->"+userid);
		
		//DB조회
		int result=service.idCheck(userid);
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("result", result);
		mav.addObject("userid", userid);
		mav.setViewName("users/idCheck");
		return mav;
	}
	//회원등록(DB)
	@RequestMapping(value="/users/userFormOk",method=RequestMethod.POST)
	public ModelAndView usersFormOk(UsersVO vo) {
		ModelAndView mav = new ModelAndView();
		
		try {
		
			int result = service.usersInsert(vo);
			
			if(result>0){//회원가입성공->로그인 폼
				mav.setViewName("redirect:login");
			}else{//회원가입실패->회원가입 폼
				mav.setViewName("users/usersFormResult");
			}
		}catch(Exception e) {
			e.printStackTrace();
			mav.setViewName("users/usersFormResult");
		}
		
		return mav;
	}
	
	//로그인
	@RequestMapping("/users/login")
	public String login() {
		return "users/login";
	}
	//로그인(DB)
	@PostMapping("/users/loginOk")
	public ModelAndView loginOk(String userid, String userpwd, HttpSession session) {
		ModelAndView mav=new ModelAndView();
		
		UsersVO logVO =service.loginSelect(userid, userpwd);
		
		if(logVO!=null) {
			
			session.setAttribute("logId", logVO.getUserid());
			session.setAttribute("logName", logVO.getUsername());
			session.setAttribute("logStatus", "Y");
			session.setAttribute("manager", logVO.getManager());
			
			mav.setViewName("redirect:/");
		}else {//로그인 실패 -> 로그인폼으로 이동
			mav.setViewName("redirect:login");
		}
		return mav;
	}
	//로그아웃
	@GetMapping("/users/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		
		ModelAndView mav= new ModelAndView();
		mav.setViewName("redirect:/");
		return mav;
	}
	
	//회원정보수정폼
	@GetMapping("/users/userUpdate")
	public ModelAndView userUpdate(String userid, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		
		UsersVO vo = service.userSelect((String)(session.getAttribute("logId")));
		
		mav.addObject("vo", vo);
		
		mav.setViewName("users/userUpdate");
		return mav;
	}
	
	//회원정보수정(DB)
	@PostMapping("/users/userUpdateOk")
	public ModelAndView userUpdateOk(UsersVO vo, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		try {			
			String id = (String)(session.getAttribute("logId"));
			
			
			int result = service.userUpdate(vo);
			
			if(result>0){
				mav.setViewName("redirect:login");
			}else{
				mav.setViewName("users/userUpdate");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			mav.setViewName("users/usersFormResult");
		}
		return mav;
	}
	
	//관리자페이지
	@GetMapping("/users/admin")
	public ModelAndView admin() {
		ModelAndView mav = new ModelAndView();
		List<UsersVO> list = service.userList();

        List<UsersVO> todaySignup = service.todaySignup(LocalDate.now());

        List<UsersVO> monthSignup = service.monthSignup();
        
        List<UsersVO> monthSignupPrevious = service.monthSignupPrevious();

        mav.addObject("list", list);
        mav.addObject("todaySignup", todaySignup);
        mav.addObject("monthSignup", monthSignup);
        mav.addObject("monthSignupPrevious", monthSignupPrevious);
        mav.setViewName("users/admin");

        return mav;
    }
	
	//관리자페이지 - 사용자 삭제
	@GetMapping("/users/userDelete")
	public ModelAndView userDelete(@RequestParam("userid") String userid) {
		ModelAndView mav = new ModelAndView();
		service.userDelete(userid);
		
		mav.setViewName("redirect:admin");
		return mav;
	}	
	
	//관리자페이지 - 권한부여
	@GetMapping("/users/userManager")
	public ModelAndView userManager(@RequestParam("userid") String userid) {
		ModelAndView mav = new ModelAndView();
		service.userManager(userid);
		
		mav.setViewName("redirect:admin");
		return mav;
	}
}


