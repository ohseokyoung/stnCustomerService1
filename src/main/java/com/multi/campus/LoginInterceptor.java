package com.multi.campus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//interceptor 클래스는 HandlerInterceptorAdapter를 상속받아야함!.
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	//컨트롤러가 호출되기 전에 실행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
	
		
		HttpSession session=request.getSession();
		
		String logStatus = (String)session.getAttribute("logStatus");
		
		if(logStatus==null || !logStatus.equals("Y")){
			response.sendRedirect(request.getContextPath()+"/users/login");
			return false;
		}
	
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,
			@Nullable ModelAndView mav)throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,
			@Nullable Exception e)throws Exception {
		
	}
} 
