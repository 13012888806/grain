package com.xinlin.app.entity.vo;

import java.util.List;

/**
 * 树节点实体
 * 
 * @author Jiangshui
 * @date 2013-10-16
 */
public class TreeNode {

	/**节点id*/
	private String id;
	/**节点名称*/
	private String text;
	/**节点状态，open：打开，closed：关闭*/
	private String state;
	/**节点图标*/
	private String iconCls = null;
	/**节点是否勾选上，true：勾选上，false：不勾选上*/
	private boolean checked = false;
	/**当前节点的子节点*/
	private List<TreeNode> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
}