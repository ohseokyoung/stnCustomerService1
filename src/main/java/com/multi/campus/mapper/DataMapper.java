package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.DataVO;
import com.multi.campus.vo.DatafileVO;

public interface DataMapper {
	public List<DataVO> dataList();
	public int dataInsert(DataVO vo);
	public int datafileInsert(List<DatafileVO> list );//파일명 insert
	public void dataHitCount(int no);//조회수 증가
	public DataVO dataSelect(int no);//글내용보기 근선택
	public List<DatafileVO> getDataFile(int no);//첨부파일선택
	public int dataUpdate(DataVO vo);//글수정
	public int dataDelete(int no);//첨부파일삭제
	public int dataRecordDelete(int no,String userid);//원글삭제
}
