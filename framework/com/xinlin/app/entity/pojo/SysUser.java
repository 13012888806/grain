package com.xinlin.app.entity.pojo;

import java.io.Serializable;
import java.util.Date;

import com.xinlin.app.entity.vo.PageQueryParams;

/**
 * 用户信息实体
 * 
 * @author Jiangshui
 * @date 2013-10-16
 */
public class SysUser extends PageQueryParams implements Serializable {

	private static final long serialVersionUID = 3647233284813657927L;
	/**用户账号*/
    private String userId;
    /**用户名称*/
    private String userName;
    /**用户密码*/
    private String userPass;
    /**创建时间*/
    private Date createTime;
    /**用户类型：1：超级管理员，2：普通用户*/
    private Integer userType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysUser other = (SysUser) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}