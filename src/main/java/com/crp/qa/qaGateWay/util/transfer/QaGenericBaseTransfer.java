/**
 * huangyue
 * 2018年6月4日
 */
package com.crp.qa.qaGateWay.util.transfer;

import java.io.Serializable;

/**
 * @author huangyue
 * @date 2018年6月4日 上午9:39:56
 * @ClassName QaGnenricBaseTransfer
 */
public class QaGenericBaseTransfer<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status; //状态
	private String message; //返回的信息
	private String token; //token
	private T content; //传输的内容
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	
	
}
