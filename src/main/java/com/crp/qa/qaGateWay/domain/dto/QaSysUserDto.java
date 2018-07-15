package com.crp.qa.qaGateWay.domain.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class QaSysUserDto implements Serializable{
	
	//权限类，记录着用户的权限信息
	public static class UserRights{
		private String rightsCode; //权限编码
		private String rightsSearch; //查询权限
		private String rightsCreate; //新增权限
		private String rightsUpdate; //更新权限
		private String rightsDelete; //删除权限
		public String getRightsCode() {
			return rightsCode;
		}
		public void setRightsCode(String rightsCode) {
			this.rightsCode = rightsCode;
		}
		public String getRightsSearch() {
			return rightsSearch;
		}
		public void setRightsSearch(String rightsSearch) {
			this.rightsSearch = rightsSearch;
		}
		public String getRightsCreate() {
			return rightsCreate;
		}
		public void setRightsCreate(String rightsCreate) {
			this.rightsCreate = rightsCreate;
		}
		public String getRightsUpdate() {
			return rightsUpdate;
		}
		public void setRightsUpdate(String rightsUpdate) {
			this.rightsUpdate = rightsUpdate;
		}
		public String getRightsDelete() {
			return rightsDelete;
		}
		public void setRightsDelete(String rightsDelete) {
			this.rightsDelete = rightsDelete;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userAccount;
	private String userPassword;
	private String userName;
	private String userLdap;
	private Integer createdBy;
	private Date creationDate;
	private Integer lastUpdatedBy;
	private Date lastUpdateDate;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;
	private String attribute6;
	private String attribute7;
	private String attribute8;
	private String attribute9;
	private String attribute10;
	private String userEmail;
	private Integer userPhone;
	
	private String token;
	
	private Set<QaSysUserGroupDto> qaSysUserGroup = new HashSet<QaSysUserGroupDto>(0);
	private Set<UserRights> rights = new HashSet<UserRights>(0); //该用户拥有的菜单权限

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLdap() {
		return userLdap;
	}

	public void setUserLdap(String userLdap) {
		this.userLdap = userLdap;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(Integer userPhone) {
		this.userPhone = userPhone;
	}

	public Set<QaSysUserGroupDto> getQaSysUserGroup() {
		return qaSysUserGroup;
	}

	public void setQaSysUserGroup(Set<QaSysUserGroupDto> qaSysUserGroupDto) {
		this.qaSysUserGroup = qaSysUserGroupDto;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Set<UserRights> getRights() {
		return rights;
	}

	public void setRights(Set<UserRights> rights) {
		this.rights = rights;
	}

	
	
	
	
	
}
