package com.crp.qa.qaGateWay.service.inte;

import java.util.Date;
import java.util.List;

import com.crp.qa.qaGateWay.domain.dto.QaSearchCountDto;
import com.crp.qa.qaGateWay.domain.dto.QaSearchHistoryDto;
import com.crp.qa.qaGateWay.util.exception.QaSearchHistoryException;
import com.crp.qa.qaGateWay.util.exception.QaTreeException;
import com.crp.qa.qaGateWay.util.transfer.QaGenericListTransfer;

public interface QaSearchHistoryService {

	/**
	 * 查询排名最高的历史记录
	 * @param rank
	 * @return
	 * @throws QaSearchHistoryException
	 * @Date 2018年7月24日
	 * @author huangyue
	 */
	public QaGenericListTransfer<QaSearchHistoryDto> findTopRank(Integer rank) throws QaSearchHistoryException;
	
	/**
	 * 把该查询条件记录进查询历史中,并统计当日查询总次数
	 * @param title
	 * @throws QaTreeException
	 * @Date 2018年6月21日
	 * @author huangyue
	 */
	public void searchRecord(String title) throws QaTreeException;
	
	/**
	 * 根据起始时间与结束时间查询一段时间内的访问记录
	 * @param from
	 * @param to
	 * @return
	 * @throws QaSearchHistoryException
	 * @Date 2018年8月3日
	 * @author huangyue
	 */
	public QaGenericListTransfer<QaSearchCountDto> findCount(Date from,Date to) throws QaSearchHistoryException;
}
