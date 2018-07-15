/**
 * huangyue
 * 2018年5月31日
 */
package com.crp.qa.qaGateWay.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crp.qa.qaGateWay.service.inte.QaUserService;
import com.crp.qa.qaGateWay.util.exception.QaUserException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaPagedTransfer;


/**
 * 用户
 * @author huangyue
 * @date 2018年5月31日 下午7:29:11
 * @ClassName QaUserController
 */
@RestController
@RequestMapping(path="/user")
public class QaUserController extends QaBaseController{
	
	@Resource(name="qaUserService")
	private QaUserService qaUserService;
	
	/**
	 *  查找所有用户
	 * @author huangyue
	 * @date 2018年5月31日 下午8:49:51
	 * @return
	 */
	@GetMapping(path="/findAll")
	public QaBaseTransfer findAll() {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaUserService.findAll();
		} catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 获取所有用户,分页
	 * @param username
	 * @return
	 */
	@GetMapping(path="/findPagedAll")
	public QaPagedTransfer findPagedAll(
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="size",defaultValue="10") Integer size) {
		//创建返回对象
		QaPagedTransfer dto = new QaPagedTransfer();
		try {
			dto = qaUserService.findPagedAll(page, size);
		} catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}

	/**
	 * 根据id查找用户
	 * @author huangyue
	 * @date 2018年5月10日 上午10:17:14
	 * @param id
	 * @return
	 */
	@GetMapping(path="/find/{id}")
	public QaBaseTransfer get(@PathVariable(value="id") Integer id) {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaUserService.findById(id);
		} catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 根据账户或姓名查找用户,不分页
	 * @author huangyue
	 * @date 2018年5月15日 下午1:08:30
	 * @param account 账户或姓名
	 * @return
	 */
	@GetMapping(path="/findByAccountOrName")
	public QaBaseTransfer findByAccountOrName(@RequestParam(value="account") String account) {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaUserService.findByAccountOrName(account);
		} catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 根据账户或姓名查找用户,分页
	 * @author huangyue
	 * @date 2018年5月15日 下午2:58:11
	 * @param account 账户或姓名
	 * @param page 当前页
	 * @param size 每页条目数
	 * @param isSlice 返回值是否是slice
	 * 			false (default):  返回page，包含总页数及总条目数，效率低。
	 * 			true: 返回slice，只有数据，没有总页数和总条目数，效率高。
	 * @return
	 */
	@GetMapping(path="/findPagedByAccountOrName")
	public QaPagedTransfer findPagedByAccountOrName(
			@RequestParam(value="account") String account,
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="size",defaultValue="10") Integer size,
			@RequestParam(value="searchType",defaultValue="account") String searchType){
		//创建返回对象
		QaPagedTransfer dto = new QaPagedTransfer();
		try {
			dto = qaUserService.findPagedByAccountOrName(account, page, size, searchType);
		} catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 保存传入的对象
	 * @author huangyue
	 * @date 2018年5月15日 下午7:53:40
	 * @param user
	 * @return
	 */
	@PostMapping(path="/save")
	public QaBaseTransfer save(@RequestParam(value="user") String user){
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaUserService.save(user);
		} catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 根据id删除对象
	 * @author huangyue
	 * @date 2018年5月15日 下午7:53:47
	 * @param id
	 * @return
	 */
	@DeleteMapping(path="/delete/{id}")
	public QaBaseTransfer delete(@PathVariable(value="id") Integer id){
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaUserService.deleteById(id);
		} catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 更新传入的对象
	 * @author huangyue
	 * @date 2018年5月15日 下午7:53:54
	 * @param user
	 * @return
	 */
	@PutMapping(path="/update")
	public QaBaseTransfer update(@RequestParam(value="user") String user){
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer();
		try {
			dto = qaUserService.update(user);
		} catch (QaUserException e) {
			returnError(e,dto);
		}
		return dto;
	}
}
