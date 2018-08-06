/**
 * huangyue
 * 2018年6月1日
 */
package com.crp.qa.qaGateWay.service.inte;

import java.util.List;

import com.crp.qa.qaGateWay.domain.dto.QaTreeDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.util.exception.QaClientException;
import com.crp.qa.qaGateWay.util.transfer.QaGenericBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;

/**
 * @author huangyue
 * @date 2018年6月1日 上午11:51:17
 * @ClassName QaClientService
 */
public interface QaClientService {
	
	/**
	 * 根据title去查找结果，有如下功能：
	 * 	1.精确查找该标题,找到该节点以及其子集；
	 * 	2.如果精确查找不到，改为模糊查询,标题或关键字包含就显示
	 * @author huangyue
	 * @date 2018年6月1日 下午5:31:17
	 * @param title
	 * @return
	 * @throws QaClientException
	 */
	public QaGenericBaseTransfer<QaTreeDto> findByTitle(String title) throws QaClientException;
	
	public QaGenericBaseTransfer<QaTreeDto> findByTitle(String title,List<String> domain) throws QaClientException;
	
	public QaGenericBaseTransfer<QaTreeDto> findByTitle(String title,List<String> domain,Boolean strict) throws QaClientException;

	/**
	 * 查找指定数量的最高排名节点
	 * @author huangyue
	 * @date 2018年6月6日 上午9:45:44
	 * @param size
	 * @return
	 * @throws QaClientException
	 */
	public QaGenericPagedTransfer<QaTreeSimpleDto> findTopRank(Integer size) throws QaClientException;
}
