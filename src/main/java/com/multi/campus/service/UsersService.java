package com.multi.campus.service;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.multi.campus.vo.UsersVO;

public interface UsersService {
	
	public int idCheck(String userid);
	public int usersInsert(UsersVO vo);//회원가입
	public UsersVO loginSelect(String userid, String userpwd);//로그인
	public int userUpdate(UsersVO vo);
	public UsersVO userSelect(String userid);
	
	public List<UsersVO> userList();//관리자페이지 리스트
    public List<UsersVO> todaySignup(LocalDate date);
    public List<UsersVO> monthSignup();
    public List<UsersVO> monthSignupPrevious();
    public int userDelete(String userid);
    public int userManager(String userid);
}
