/**
 * huangyue
 * 2018年5月14日
 */
package com.crp.qa.qaGateWay.util.exception;

/**
 * 用户错误
 * @author huangyue
 * @date 2018年5月14日 上午11:48:05
 * @ClassName UserNotFondException
 */
public class QaGroupException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 错误编码
	 */
	private String errorCode;

	/**
	 * 构造函数
	 * @param errorMsg 错误信息
	 */
	public QaGroupException(String errorMsg) {
		super(errorMsg);
	}
	
	/**
	 * 构造函数
	 * @param errorMsg 错误信息
	 */
	public QaGroupException(String errorMsg,Throwable cause) {
		super(errorMsg,cause);
	}
	
	/**
	 * 构造函数
	 * @param errorMsg 错误信息
	 * @param errorCode 错误代码
	 */
	public QaGroupException(String errorMsg,String errorCode) {
		super(errorMsg);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
