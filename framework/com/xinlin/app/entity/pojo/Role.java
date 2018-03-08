package com.xinlin.app.entity.pojo;

import java.io.Serializable;
import java.util.Date;

import com.xinlin.app.entity.vo.PageQueryParams;

/**
 * 角色信息实体
 * 
 * @author Jiangshui
 * @date 2013-10-16
 */
public class Role extends PageQueryParams implements Serializable {

	private static final long serialVersionUID = 3647233284813657927L;
	/**角色编号*/
    private String roleId;
    /**角色名称*/
    private String roleName;
    /**角色角色备注*/
    private String roleMark;
    /**创建时间*/
    private Date createTime;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleMark() {
        return roleMark;
    }

    public void setRoleMark(String roleMark) {
        this.roleMark = roleMark;
    }
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}