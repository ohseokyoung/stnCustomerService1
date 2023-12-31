package com.multi.campus.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  
@NoArgsConstructor
@AllArgsConstructor
public class DataVO {
	private int no;
	private String subject;
	private String content;
	private String userid;
	private int hit;
	private String writedate;
	
	//삭제된 파일정보 보관할 객체: 배열,컬렉션(List)
	private List<String> delFile;
	
}
