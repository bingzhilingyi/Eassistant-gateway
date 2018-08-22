/**
 * huangyue
 * 2018年6月1日
 */
package com.crp.qa.qaGateWay.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.crp.qa.qaGateWay.domain.dto.QaSearchNoResultDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.service.inte.QaClientService;
import com.crp.qa.qaGateWay.service.inte.QaSearchHistoryService;
import com.crp.qa.qaGateWay.service.inte.QaSearchNoResultService;
import com.crp.qa.qaGateWay.service.inte.QaTreeService;
import com.crp.qa.qaGateWay.util.exception.QaClientException;
import com.crp.qa.qaGateWay.util.exception.QaSearchNoResultException;
import com.crp.qa.qaGateWay.util.exception.QaTreeException;
import com.crp.qa.qaGateWay.util.transfer.QaClientStage;
import com.crp.qa.qaGateWay.util.transfer.QaGenericBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;

/**
 * @author huangyue
 * @date 2018年6月1日 下午3:43:59
 * @ClassName QaClientServiceImpl
 */
@Service(value="qaClientService")
public class QaClientServiceImpl extends QaBaseServiceImpl implements QaClientService {
	
	@Resource(name="qaTreeService")
	private QaTreeService qaTreeService;
	
	@Resource(name="qaSearchNoResultService")
	QaSearchNoResultService qaSearchNoResultService;
	
	@Resource(name="QaSearchHistoryService")
	private QaSearchHistoryService qaSearchHistoryService;

	@Override
	public QaGenericBaseTransfer<QaTreeDto> findByTitle(String title) throws QaClientException{
		return findByTitle(title,null,false);
	}
	
	@Override
	public QaGenericBaseTransfer<QaTreeDto> findByTitle(String title,List<String> domain) throws QaClientException{
		return findByTitle(title,domain,true);
	}
	
	public QaGenericBaseTransfer<QaTreeDto> findByTitle(String title,List<String> domain,Boolean strict) throws QaClientException{
		title = title==null?"":title.trim();
		if(title.equals("")) {
			throw new QaClientException("输入的title为空！");
		}
		if(strict && (domain==null || domain.size()==0)) {
			throw new QaClientException("输入的domain为空！");
		}
		QaGenericBaseTransfer<QaTreeDto> dto = new QaGenericBaseTransfer<QaTreeDto>();
		try {
			boolean noResult = false; //默认是有数据返回的
			//精确查找
			if(strict) {
				dto = qaTreeService.findChildrenByTitle(title,domain);
			}else {
				dto = qaTreeService.findChildrenByTitle(title);
			}
			
			//状态设置为无更多信息
			dto.setStage(QaClientStage.END);
			//如果查询内容为空，说明精确查询无匹配，则进行模糊查询
			if(dto.getContent()==null) {
				//模糊查
				QaGenericPagedTransfer<QaTreeSimpleDto> dto2;
				if(strict) {
					dto2 = qaTreeService.findPagedByTitleOrKeyword(title, 0, 10,domain);
				} else {
					dto2 = qaTreeService.findPagedByTitleOrKeyword(title, 0, 10);
				}
							
				//取到查询结果
				List<QaTreeSimpleDto> l = dto2.getContent();
				//如果模糊查也查不到结果，就记录下这次查询
				if(l.size()==0) {
//					QaSearchNoResultDto record = new QaSearchNoResultDto();
//					record.setRecordTitle(title);
//					qaSearchNoResultService.save(record);
					noResult = true; //无数据返回
				}
				//把结果放入到一个空对象里
				QaTreeDto emptytree = new QaTreeDto();
				emptytree.setChild(new HashSet<QaTreeSimpleDto>(l));
				//把空对象给返回值
				dto.setContent(emptytree);
			}
			//记录这次查询历史
			qaSearchHistoryService.searchRecord(title,noResult);
			
		} catch (QaTreeException | NullPointerException e) {
			throw new QaClientException("查询层级结构出错",e);
		}
		return dto;
	}

	@Override
	public QaGenericPagedTransfer<QaTreeSimpleDto> findTopRank(Integer size) throws QaClientException{
		QaGenericPagedTransfer<QaTreeSimpleDto> dto = new QaGenericPagedTransfer<QaTreeSimpleDto>();
		try {
			dto = qaTreeService.findTopRank(size);
		} catch (QaTreeException e) {
			throw new QaClientException("查询最高rank节点出错",e);
		}
		return dto;
	}

}
