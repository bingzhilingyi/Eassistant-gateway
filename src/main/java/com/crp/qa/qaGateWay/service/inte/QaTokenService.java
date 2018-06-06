/**
 * huangyue
 * 2018年5月22日
 */
package com.crp.qa.qaGateWay.service.inte;

/**
 * token service
 * @author huangyue
 * @date 2018年5月22日 上午9:01:29
 * @ClassName QaTokenService
 */
public interface QaTokenService {

	/**
	 * 生成随机token
	 * @author huangyue
	 * @date 2018年5月22日 上午9:08:27
	 * @return
	 */
	public String generateToken();
	
	/**
	 * 往redis里以token为key保存信息
	 * @author huangyue
	 * @date 2018年5月22日 上午9:10:49
	 * @param token
	 * @param value
	 */
	public void setToken(String token,Object value);
	
	/**
	 * 根据token从redis里取到信息
	 * @author huangyue
	 * @date 2018年5月22日 上午9:15:23
	 * @param token
	 */
	public String getByToken(String token);
	
	/**
	 * 查看某token是否存在
	 * @author huangyue
	 * @date 2018年5月22日 上午10:02:50
	 * @param token
	 * @return
	 */
	public boolean isExists(String token);
	
	/**
	 * 根据token删除信息
	 * @author huangyue
	 * @date 2018年5月22日 上午11:54:02
	 * @param token
	 */
	public void deleteByToken(String token);
}
