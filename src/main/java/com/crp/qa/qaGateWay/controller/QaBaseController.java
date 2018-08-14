/**
 * huangyue
 * 2018年5月29日
 */
package com.crp.qa.qaGateWay.controller;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaGenericBaseTransfer;

/**
 * @author huangyue
 * @date 2018年5月29日 上午10:09:19
 * @ClassName QaBaseForwardController
 */
@Component 
public class QaBaseController {
	
	final Logger LOGGER = LoggerFactory.getLogger(QaBaseController.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
	/**
	 * 返回错误信息并打印日志的通用方法
	 * @author huangyue
	 * @date 2018年5月15日 下午1:59:26
	 * @param e
	 * @param dto
	 */
	protected void returnError(Exception e,QaBaseTransfer dto) {
		LOGGER.error(e.getMessage(),e);
		dto.setStatus("failed");
		dto.setMessage(e.getMessage());
	}
	
	/**
	 * 返回错误信息并打印日志的通用方法
	 * @author huangyue
	 * @date 2018年5月15日 下午1:59:26
	 * @param e
	 * @param dto
	 */
	protected void returnError(Exception e,@SuppressWarnings("rawtypes") QaGenericBaseTransfer dto) {
		LOGGER.error(e.getMessage(),e);
		dto.setStatus("failed");
		dto.setMessage(e.getMessage());
	}
}
