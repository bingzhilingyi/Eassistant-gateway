/**
 * huangyue
 * 2018年6月1日
 */
package com.crp.qa.qaGateWay.service.impl;

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
		QaGenericBaseTransfer<QaTreeDto> dto = new QaGenericBaseTransfer<QaTreeDto>();
		try {
			//精确查找
			dto = qaTreeService.findChildrenByTitle(title);
			//获取查找对象
			//QaTreeDto content = (QaTreeDto)dto.getContent();
			//如果查询内容为空，说明精确查询无匹配，那么就模糊查
			if(dto.getContent()==null) {
				//模糊查
				QaGenericBaseTransfer<List<QaTreeSimpleDto>> dto2 = qaTreeService.findPagedByTitleLike(title, 0, 10);
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
	public QaGenericPagedTransfer<List<QaTreeSimpleDto>> findTopRank(Integer size) throws QaClientException{
		QaGenericPagedTransfer<List<QaTreeSimpleDto>> dto = new QaGenericPagedTransfer<List<QaTreeSimpleDto>>();
		try {
			dto = qaTreeService.findTopRank(size);
		} catch (QaTreeException e) {
			throw new QaClientException("查询最高rank节点出错",e);
		}
		return dto;
	}
	
}
