/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaGateWay.service.inte;


import java.util.List;


import com.crp.qa.qaGateWay.domain.dto.QaTreeDto;
import com.crp.qa.qaGateWay.domain.dto.QaTreeSimpleDto;
import com.crp.qa.qaGateWay.util.exception.QaTreeException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericPagedTransfer;

/**
 * @author huangyue
 * @date 2018年5月31日 下午8:57:18
 * @ClassName QaTreeService
 */
public interface QaTreeService {

	/**
	 * 找到根节点
	 * @author huangyue
	 * @date 2018年5月31日 下午8:59:43
	 * @return
	 * @throws QaUserException
	 */
	public QaBaseTransfer findRoot() throws QaTreeException;
	
	/**
	 * 根据父级节点查找子集
	 * @author huangyue
	 * @date 2018年5月31日 下午9:00:48
	 * @param parentId
	 * @return
	 * @throws QaUserException
	 */
	public QaBaseTransfer findByParentId(Integer parentId) throws QaTreeException;
		
	/**
	 * 根据id查找节点
	 * @author huangyue
	 * @date 2018年5月31日 下午9:01:39
	 * @param id
	 * @return
	 * @throws QaUserException
	 */
	public QaBaseTransfer findById(Integer id) throws QaTreeException;
	
	/**
	 * 根据title精确查找节点
	 * @author huangyue
	 * @date 2018年5月31日 下午9:02:44
	 * @param title
	 * @return
	 * @throws QaUserException
	 */
	public QaBaseTransfer findByTitle(String title) throws QaTreeException;
	
	/**
	 * 根据title模糊分页查找节点
	 * @author huangyue
	 * @date 2018年5月31日 下午9:04:02
	 * @param title
	 * @param page
	 * @param size
	 * @param isNeedRecord 是否需要记录此次查询
	 * @return
	 * @throws QaUserException
	 */
	public QaGenericPagedTransfer<List<QaTreeSimpleDto>> findPagedByTitleLike(String title,Integer page,Integer size) throws QaTreeException;
	
	/**
	 * 新增节点
	 * @author huangyue
	 * @date 2018年5月31日 下午9:05:04
	 * @param node
	 * @return
	 * @throws QaUserException
	 */
	public QaBaseTransfer save(String node) throws QaTreeException;
	
	/**
	 * 更新节点
	 * @author huangyue
	 * @date 2018年5月31日 下午9:05:04
	 * @param node
	 * @return
	 * @throws QaUserException
	 */
	public QaBaseTransfer update(String node) throws QaTreeException;
	
	
	/**
	 * 删除节点
	 * @author huangyue
	 * @date 2018年5月31日 下午9:05:04
	 * @param node
	 * @return
	 * @throws QaUserException
	 */
	public QaBaseTransfer delete(Integer id) throws QaTreeException;
	
	/**
	 * 根据title查找节点及其子节点
	 * @author huangyue
	 * @date 2018年6月1日 下午5:15:49
	 * @param title 标题
	 * @param isNeedRecord 是否是否需要记录这次查询条件（用于统计，如果是后台运维，请设为false）
	 * @return
	 * @throws QaTreeException
	 */
	public QaGenericBaseTransfer<QaTreeDto> findChildrenByTitle(String title,Boolean isNeedRecord) throws QaTreeException;
	
	/**
	 * 以rank值排序查找指定数量的节点
	 * @author huangyue
	 * @date 2018年6月6日 上午9:25:28
	 * @return
	 * @throws QaTreeException
	 */
	public QaGenericPagedTransfer<List<QaTreeSimpleDto>> findTopRank(Integer size) throws QaTreeException;

	/**
	 * 把该查询条件记录进查询历史中
	 * @param title
	 * @throws QaTreeException
	 * @Date 2018年6月21日
	 * @author huangyue
	 */
	public void searchRecord(String title) throws QaTreeException;
}
