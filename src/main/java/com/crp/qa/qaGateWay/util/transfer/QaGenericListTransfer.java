package com.crp.qa.qaGateWay.util.transfer;

import java.io.Serializable;
import java.util.List;

public class QaGenericListTransfer<T> extends QaBaseTransfer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<T> content; //传输的内容
	
	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}
	
	
}
