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

import com.crp.qa.qaGateWay.domain.dto.QaTreeDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.service.inte.QaClientService;
import com.crp.qa.qaGateWay.service.inte.QaTreeService;
import com.crp.qa.qaGateWay.util.exception.QaClientException;
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

	@Override
	public QaGenericBaseTransfer<QaTreeDto> findByTitle(String title) throws QaClientException{
		title = title==null?"":title.trim();
		if(title.equals("")) {
			throw new QaClientException("输入的title为空！");
		}
		QaGenericBaseTransfer<QaTreeDto> dto = new QaGenericBaseTransfer<QaTreeDto>();
		try {
			//精确查找
			dto = qaTreeService.findChildrenByTitle(title);
			//状态设置为无更多信息
			dto.setStage(QaClientStage.END);
			//如果查询内容为空，说明精确查询无匹配，则进行模糊查询
			if(dto.getContent()==null) {
				//模糊查
				//QaGenericPagedTransfer<QaTreeSimpleDto> dto2 = qaTreeService.findPagedByTitleLike(title, 0, 10);
				QaGenericPagedTransfer<QaTreeSimpleDto> dto2 = qaTreeService.findPagedByTitleOrKeyword(title, 0, 10);
				
				//如果结果还是空，那么就去查关键字，关键字要求精确查询
//				if(dto2.getContent().size()==0) {
//					dto2 = qaTreeService.findPagedByKeyword(title,0,10);
//				}else {
//					//状态设置为还有更多信息
//					dto.setStage(QaClientStage.MORE);
//				}
				
				//取到查询结果
				List<QaTreeSimpleDto> l = dto2.getContent();
				//把结果放入到一个空对象里
				QaTreeDto emptytree = new QaTreeDto();
				emptytree.setChild(new HashSet<QaTreeSimpleDto>(l));
				//把空对象给返回值
				dto.setContent(emptytree);
			}
		} catch (QaTreeException e) {
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
