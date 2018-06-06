/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaGateWay.service.inte;


import com.crp.qa.qaGateWay.util.exception.QaUserException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaPagedTransfer;


/**
 * 有关用户的服务
 * @author huangyue
 * @date 2018年5月31日 下午6:06:44
 * @ClassName QaAuthorizationService
 */
public interface QaUserService {

	/**
	 * 获取所有用户
	 * @author huangyue
	 * @date 2018年5月14日 下午4:58:57
	 * @return
	 * @throws QaUserException
	 */
	public QaBaseTransfer findAll() throws QaUserException;
	
	/**
	 * 获取分页的所有用户
	 * @author huangyue
	 * @date 2018年5月14日 下午4:29:21
	 * @param page 第几页
	 * @param size 每页几条数据
	 * @return
	 * @throws QaUserException
	 */
	public QaPagedTransfer findPagedAll(Integer page,Integer size) throws QaUserException;
	
	/**
	 * 根据id查找用户
	 * @author huangyue
	 * @date 2018年5月14日 下午4:59:12
	 * @param id
	 * @return
	 * @throws QaUserException
	 */
	public QaBaseTransfer findById(Integer id) throws QaUserException;
	
	/**
	 * 根据账号精确查找用户
	 * @author huangyue
	 * @date 2018年5月14日 下午5:10:22
	 * @param account 账户
	 * @return
	 * @throws QaUserException
	 */
	public QaBaseTransfer findByAccountEqual(String account) throws QaUserException;
	
	/**
	 * 根据账号或姓名模糊查找用户
	 * @author huangyue
	 * @date 2018年5月14日 下午5:10:22
	 * @param account 账户或姓名
	 * @return
	 * @throws QaUserException
	 */
	public QaBaseTransfer findByAccountOrName(String account) throws QaUserException;
	
	/**
	 * 根据账号或姓名模糊查找用户,分页
	 * @author huangyue
	 * @date 2018年5月15日 上午11:00:08
	 * @param account 账户或姓名
	 * @param page 当前页数
	 * @param size 每页大小
	 * @param isSlice 是否返回slice。
	 * 			false :  返回page，包含总页数及总条目数，效率低。
	 * 			true: 返回slice，只有数据，没有总页数和总条目数，效率高。
	 * @return
	 * @throws QaUserException
	 */
	public QaPagedTransfer findPagedByAccountOrName(String account,Integer page,Integer size,Boolean isSlice) throws QaUserException;
	
	/**
	 * 保存传入的对象并返回保存后的对象
	 * @author huangyue
	 * @date 2018年5月15日 下午5:04:11
	 * @param dto
	 * @return
	 * @throws QaUserException
	 */
	public QaBaseTransfer save(String user) throws QaUserException;
	/**
	 * 根据id删除对象
	 * @author huangyue
	 * @date 2018年5月15日 下午7:32:50
	 * @param dto
	 * @throws QaUserException
	 */
	public QaBaseTransfer deleteById(Integer id) throws QaUserException;
	
	/**
	 * 根据传入的对象更新并返回更新后的对象
	 * @author huangyue
	 * @date 2018年5月15日 下午7:47:29
	 * @param dto
	 * @return
	 * @throws QaUserException
	 */
	public QaBaseTransfer update(String user) throws QaUserException;
}
