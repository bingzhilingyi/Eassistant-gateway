/**
 * huangyue
 * 2018年6月4日
 */
package com.crp.qa.qaGateWay.util.transfer;

/**
 * @author huangyue
 * @date 2018年6月4日 上午9:44:38
 * @ClassName QaGenericPagedTransfer
 */
public class QaGenericPagedTransfer<T> extends QaGenericBaseTransfer<T>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long totalElements; //总数
	private Integer totalPages; //总页数
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

}
