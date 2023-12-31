package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.BoardReplyVO;

public interface BoardReplyMapper {
	public int replyInsert(BoardReplyVO vo);//´ñ±Û
	public List<BoardReplyVO> replySelect(int no);//´ñ±Û¸ñ·Ï
	public int replyUpdate(BoardReplyVO vo); //´ñ±Û¼öÁ¤(DB)
	public int replyDelete(int replyno);//´ñ±Û »èÁ¦

}
