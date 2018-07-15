/**
 * huangyue
 * 2018年5月14日
 */
package com.crp.qa.qaGateWay.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crp.qa.qaGateWay.service.inte.QaLoginService;
import com.crp.qa.qaGateWay.util.exception.QaLoginException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;

/**
 * 登录
 * @author huangyue
 * @date 2018年5月14日 下午7:05:35
 * @ClassName QaLoginController
 */
@RestController
@RequestMapping(path="/Login")
public class QaLoginController extends QaBaseController{
	
	@Resource(name="qaLoginService")
	private QaLoginService qaLoginService;

	/**
	 * 登录方法
	 * @author huangyue
	 * @date 2018年5月29日 上午10:26:40
	 * @param req
	 * @param res
	 * @param account
	 * @param password
	 * @return
	 */
	@PostMapping(path="/login")
	public QaBaseTransfer Login(
			@RequestParam(value="account") String account,
			@RequestParam(value="password") String password) {
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaLoginService.login(account,password);
		} catch (QaLoginException e) {
			this.returnError(e, dto);
		}
		return dto;
	}
	
	@GetMapping(path="/findByToken")
	public QaBaseTransfer findByToken(@RequestParam(value="token") String token) {
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaLoginService.findByToken(token);
		} catch (QaLoginException e) {
			this.returnError(e, dto);
		}
		return dto;
	}
}
