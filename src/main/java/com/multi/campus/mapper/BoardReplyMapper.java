package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.BoardReplyVO;

public interface BoardReplyMapper {
	public int replyInsert(BoardReplyVO vo);//���
	public List<BoardReplyVO> replySelect(int no);//��۸��
	public int replyUpdate(BoardReplyVO vo); //��ۼ���(DB)
	public int replyDelete(int replyno);//��� ����

}
