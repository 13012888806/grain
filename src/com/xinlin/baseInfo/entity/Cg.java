package com.xinlin.baseInfo.entity;

import java.io.Serializable;

import com.xinlin.app.entity.vo.PageQueryParams;

/**
 * ClassName: Variety 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-4
 */
public class Cg extends PageQueryParams implements Serializable {

	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	 private String ID; // varchar(30) not null comment '主键',
	 private String CG_CD ;// varchar(12) comment '种类编号',
	 private String CG_NM;//varchar(30) comment '种类名称：面粉、大米、油、杂粮等',
	 private String DEL_FLAG; //char(1) comment '删除标示：0已删除，1未删除',
	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}
	/**
	 * @return the cG_CD
	 */
	public String getCG_CD() {
		return CG_CD;
	}
	/**
	 * @param cGCD the cG_CD to set
	 */
	public void setCG_CD(String cGCD) {
		CG_CD = cGCD;
	}
	/**
	 * @return the cG_NM
	 */
	public String getCG_NM() {
		return CG_NM;
	}
	/**
	 * @param cGNM the cG_NM to set
	 */
	public void setCG_NM(String cGNM) {
		CG_NM = cGNM;
	}
	/**
	 * @return the dEL_FLAG
	 */
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	/**
	 * @param dELFLAG the dEL_FLAG to set
	 */
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	 
	
}
