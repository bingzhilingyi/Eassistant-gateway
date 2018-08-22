package com.crp.qa.qaGateWay.service.inte;

import java.util.Date;

import com.crp.qa.qaGateWay.domain.dto.QaSearchHistoryDto;
import com.crp.qa.qaGateWay.domain.dto.QaSearchNoResultDto;
import com.crp.qa.qaGateWay.util.exception.QaSearchHistoryException;
import com.crp.qa.qaGateWay.util.exception.QaSearchNoResultException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;

public interface QaSearchNoResultService {

	public QaGenericPagedTransfer<QaSearchHistoryDto> findPagedAll(Integer page, Integer size) throws QaSearchHistoryException;
	
	/**
	 * 查找指定日期范围内的记录
	 * @param from
	 * @param to
	 * @param page
	 * @param size
	 * @return
	 * @throws QaSearchNoResultException
	 * @Date 2018年8月14日
	 * @author huangyue
	 */
	public QaGenericPagedTransfer<QaSearchHistoryDto> findByCreationDateBetween(Date from,Date to,Integer page,Integer size)throws QaSearchHistoryException;
}
