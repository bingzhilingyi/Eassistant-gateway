package com.crp.qa.qaGateWay.domain.dto;

public class QaSysUserGroupDto {

	private Integer id;
	private Integer userId;
	private Integer groupId;
	
	public QaSysUserGroupDto() {
		
	}
	
	public QaSysUserGroupDto(Integer groupId) {
		this.groupId = groupId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	
}
