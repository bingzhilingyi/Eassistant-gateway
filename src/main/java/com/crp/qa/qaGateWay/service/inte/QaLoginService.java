/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaGateWay.service.inte;

import java.util.Set;

import com.crp.qa.qaGateWay.util.exception.QaLoginException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;

/**
 * 登录服务
 * @author huangyue
 * @date 2018年5月31日 下午6:10:19
 * @ClassName QaLoginService
 */
public interface QaLoginService {

	/**
	 * 登录方法，返回一个带有token的传输对象
	 * @author huangyue
	 * @date 2018年5月31日 下午7:06:47
	 * @param account
	 * @param password
	 * @return
	 * @throws QaLoginException
	 */
	public QaBaseTransfer login(String account,String password) throws QaLoginException;
	
	/**
	 * 判断是否登录的方法
	 * @author huangyue
	 * @date 2018年5月31日 下午7:07:38
	 * @param token
	 * @return
	 * @throws QaLoginException
	 */
	public boolean isLogin(String logingToken) throws QaLoginException;

	
	/**
	 * 根据token获取当前用户信息
	 * @param token
	 * @return
	 * @throws QaLoginException
	 * @Date 2018年7月11日
	 * @author huangyue
	 */
	public QaBaseTransfer findByToken(String token) throws QaLoginException;
}
