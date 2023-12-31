package com.multi.campus.mapper;

import java.util.List;

import com.multi.campus.vo.DataVO;
import com.multi.campus.vo.DatafileVO;

public interface DataMapper {
	public List<DataVO> dataList();
	public int dataInsert(DataVO vo);
	public int datafileInsert(List<DatafileVO> list );//���ϸ� insert
	public void dataHitCount(int no);//��ȸ�� ����
	public DataVO dataSelect(int no);//�۳��뺸�� �ټ���
	public List<DatafileVO> getDataFile(int no);//÷�����ϼ���
	public int dataUpdate(DataVO vo);//�ۼ���
	public int dataDelete(int no);//÷�����ϻ���
	public int dataRecordDelete(int no,String userid);//���ۻ���
}
