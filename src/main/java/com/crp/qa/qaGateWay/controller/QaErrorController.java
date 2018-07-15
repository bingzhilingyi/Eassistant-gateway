/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaGateWay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;


/**
 * 专门返回错误的controller
 * @author huangyue
 * @date 2018年5月31日 下午7:23:25
 * @ClassName QaErrorController
 */
@RestController
@RequestMapping(path="/error")
public class QaErrorController {

	/**
	 * 当token验证错误时的处理方法
	 * @author huangyue
	 * @date 2018年5月29日 上午10:26:47
	 * @return
	 */
	@RequestMapping(path="/notoken")
	public QaBaseTransfer noToken() {
		QaBaseTransfer dto = new QaBaseTransfer("failed","token错误或已过期！");
		return dto;
	}
	
	/**
	 * 当无权限发起请求的处理方法
	 * @return
	 * @Date 2018年7月15日
	 * @author huangyue
	 */
	@RequestMapping(path="/noRight")
	public QaBaseTransfer noRight() {
		QaBaseTransfer dto = new QaBaseTransfer("failed","抱歉，您无权进行该操作");
		return dto;
	}
}
