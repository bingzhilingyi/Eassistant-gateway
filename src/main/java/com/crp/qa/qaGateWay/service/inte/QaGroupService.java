package com.crp.qa.qaGateWay.service.inte;

import com.crp.qa.qaGateWay.util.exception.QaGroupException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaPagedTransfer;

public interface QaGroupService {

	/**
	 * 查询出所有组
	 * @return
	 * @throws QaGroupException
	 * @Date 2018年7月3日
	 * @author huangyue
	 */
	public QaBaseTransfer findAll() throws QaGroupException;
	
	
	/**
	 * 根据id查找组
	 * @param id
	 * @return
	 * @throws QaGroupException
	 * @Date 2018年7月4日
	 * @author huangyue
	 */
	public QaBaseTransfer findById(Integer id) throws QaGroupException;
	
	/**
	 * 根据组名精确查询组
	 * @param groupName
	 * @return
	 * @throws QaGroupException
	 * @Date 2018年7月3日
	 * @author huangyue
	 */
	public QaBaseTransfer findByGroupName(String groupName) throws QaGroupException;
	
	/**
	 * 根据组名模糊查询组
	 * @param groupName
	 * @return
	 * @throws QaGroupException
	 * @Date 2018年7月3日
	 * @author huangyue
	 */
	public QaPagedTransfer findByGroupNameLike(String groupName,Integer page,Integer size) throws QaGroupException;
	
	/**
	 * create
	 * @param group
	 * @return
	 * @throws QaGroupException
	 * @Date 2018年7月4日
	 * @author huangyue
	 */
	public QaBaseTransfer save(String group) throws QaGroupException;
	
	/**
	 * delete
	 * @param id
	 * @return
	 * @throws QaGroupException
	 * @Date 2018年7月4日
	 * @author huangyue
	 */
	public QaBaseTransfer delete(Integer id) throws QaGroupException;
	
	/**
	 * update
	 * @param group
	 * @return
	 * @throws QaGroupException
	 * @Date 2018年7月4日
	 * @author huangyue
	 */
	public QaBaseTransfer update(String group) throws QaGroupException;
}
