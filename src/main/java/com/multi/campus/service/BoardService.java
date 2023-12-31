package com.multi.campus.service;

import java.util.List;

import com.multi.campus.vo.BoardVO;
import com.multi.campus.vo.PagingVO;

public interface BoardService {
	public int boardInsert(BoardVO vo);
	public List<BoardVO> boardPageList(PagingVO pVO);
	public int totalRecord(PagingVO pVO);
	public BoardVO boardSelect(int no);
	public void hitCount(int no);
	public int boardUpdate(BoardVO vo);
	public int boardDelete(int no);
}
