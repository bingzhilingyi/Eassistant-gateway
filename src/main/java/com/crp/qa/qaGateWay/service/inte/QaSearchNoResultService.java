package com.crp.qa.qaGateWay.service.inte;

import java.util.Date;

import com.crp.qa.qaGateWay.domain.dto.QaSearchNoResultDto;
import com.crp.qa.qaGateWay.util.exception.QaSearchNoResultException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;

public interface QaSearchNoResultService {

	/**
	 * 保存一条记录
	 * @param dto
	 * @return
	 * @throws QaSearchNoResultException
	 * @Date 2018年8月13日
	 * @author huangyue
	 */
	public QaBaseTransfer save(QaSearchNoResultDto dto) throws QaSearchNoResultException,NullPointerException;
	
	/**
	 * 查找分页的所有数据
	 * @param page
	 * @param size
	 * @return
	 * @throws QaSearchNoResultException
	 * @Date 2018年8月13日
	 * @author huangyue
	 */
	public QaGenericPagedTransfer<QaSearchNoResultDto> findPagedAll(Integer page,Integer size)throws QaSearchNoResultException;
	
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
	public QaGenericPagedTransfer<QaSearchNoResultDto> findByCreationDateBetween(Date from,Date to,Integer page,Integer size)throws QaSearchNoResultException;
}
