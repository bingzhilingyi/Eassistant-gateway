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

import com.crp.qa.qaGateWay.service.inte.QaGroupService;
import com.crp.qa.qaGateWay.util.exception.QaGroupException;
import com.crp.qa.qaGateWay.util.transfer.QaBaseTransfer;
import com.crp.qa.qaGateWay.util.transfer.QaPagedTransfer;

@RestController
@RequestMapping(path="/group")
public class QaGroupController extends QaBaseController{

	@Resource(name="qaGroupService")
	private QaGroupService qaGroupService;
	
	/**
	 * 查找所有组
	 * @return
	 * @Date 2018年7月6日
	 * @author huangyue
	 */
	@GetMapping(path="/findAll")
	public QaBaseTransfer findAll() {
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功");
		try {
			dto = qaGroupService.findAll();
		} catch (QaGroupException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 根据id查找组
	 * @param groupId
	 * @return
	 * @Date 2018年7月6日
	 * @author huangyue
	 */
	@GetMapping(path="/findById/{groupId}")
	public QaBaseTransfer findById(@PathVariable(value="groupId")Integer groupId) {
		//创建返回对象
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功！");
		try {
			dto = qaGroupService.findById(groupId);
		}catch(QaGroupException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 根据组名查找组
	 * @param groupName
	 * @return
	 * @Date 2018年7月6日
	 * @author huangyue
	 */
	@GetMapping(path="/findByGroupName")
	public QaBaseTransfer findByGroupName(@RequestParam(value="groupName")String groupName) {
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功");
		try {
			dto = qaGroupService.findByGroupName(groupName);
		} catch (QaGroupException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	/**
	 * 根据组名模糊查找组
	 * @param groupName
	 * @param page
	 * @param size
	 * @return
	 * @Date 2018年7月6日
	 * @author huangyue
	 */
	@GetMapping(path="/findByGroupNameLike")
	public QaPagedTransfer findByGroupNameLike(
			@RequestParam(value="groupName")String groupName,
			@RequestParam(value="page",defaultValue="0") Integer page,
			@RequestParam(value="size",defaultValue="10") Integer size) {
		QaPagedTransfer dto = new QaPagedTransfer("success","查询成功");
		try {
			dto = qaGroupService.findByGroupNameLike(groupName,page,size);
		} catch (QaGroupException e) {
			returnError(e, dto);
		}
		return dto;
	}
	
	@PostMapping(path="/save")
	public QaBaseTransfer save(@RequestParam(value="group") String group) {
		QaBaseTransfer dto = new QaBaseTransfer("success","保存成功！");	
		try {
			dto = qaGroupService.save(group);
		}catch (QaGroupException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	
	@DeleteMapping(path="/delete/{id}")
	public QaBaseTransfer delete(@PathVariable(value="id") Integer id) {
		QaBaseTransfer dto = new QaBaseTransfer("success","删除成功！");
		try {
			dto = qaGroupService.delete(id);
		}catch (QaGroupException e) {
			returnError(e,dto);
		}
		return dto;
	}
	
	/**
	 * 更新组
	 * @param group
	 * @return
	 * @Date 2018年7月6日
	 * @author huangyue
	 */
	@PutMapping(path="/update")
	public QaBaseTransfer update(@RequestParam(value="group")String group) {
		QaBaseTransfer dto = new QaBaseTransfer("success","查询成功");
		try {
			dto = qaGroupService.update(group);
		} catch (QaGroupException e) {
			returnError(e, dto);
		}
		return dto;
	}
}
