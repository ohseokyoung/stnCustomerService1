package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.BoardVO;
import com.multi.campus.vo.PagingVO;

public interface BoardMapper {
	public int boardInsert(BoardVO vo);
	public List<BoardVO> boardPageList(PagingVO pVO);//page에 해당하는 레코드 선택 
	public int totalRecord(PagingVO pVO);//총레코드 수
	public BoardVO boardSelect(int no); //해당레코드 선택
	public void hitCount(int no);//조회수 증가
	public int boardUpdate(BoardVO vo);//계시판 수정
	public int boardDelete(int no);//게시판 삭제
}
