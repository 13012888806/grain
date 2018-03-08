/**  
 * @Title: Variety.java
 * @Package com.xinlin.baseInfo.entity
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-4
 */
package com.xinlin.baseInfo.entity;

import java.io.Serializable;

/**
 * ClassName: Variety 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-4
 */
public class VarietyVo implements Serializable {

	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private String ID;			//varchar(30) not null  '主键',
	private String CG_CD;       //varchar(12) comment '物品种类编码',
	private String VAR_CD;  	//varchar(12) comment '品种编号',
	private String VAR_NM;      //varchar(30) comment '品种名称',
	private String INPUT_CD;    //varchar(12) comment '录入人编码',
	private String INPUT_NM;    //varchar(30) comment '录入人',
	private String INPUT_DT;    //date comment '录入时间',
	private String MODIFY_CD;   //varchar(12) comment '修改人编码',
	private String MODIFY_NM;   //varchar(30) comment '修改人',
	private String MODIFY_DT;   //date comment '修改时间',
	private String DEL_FLAG;    //char(1) comment '删除标示 ： 0已删除，1未删除',
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
	 * @return the vAR_CD
	 */
	public String getVAR_CD() {
		return VAR_CD;
	}
	/**
	 * @param vARCD the vAR_CD to set
	 */
	public void setVAR_CD(String vARCD) {
		VAR_CD = vARCD;
	}
	/**
	 * @return the vAR_NM
	 */
	public String getVAR_NM() {
		return VAR_NM;
	}
	/**
	 * @param vARNM the vAR_NM to set
	 */
	public void setVAR_NM(String vARNM) {
		VAR_NM = vARNM;
	}
	/**
	 * @return the iNPUT_CD
	 */
	public String getINPUT_CD() {
		return INPUT_CD;
	}
	/**
	 * @param iNPUTCD the iNPUT_CD to set
	 */
	public void setINPUT_CD(String iNPUTCD) {
		INPUT_CD = iNPUTCD;
	}
	/**
	 * @return the iNPUT_NM
	 */
	public String getINPUT_NM() {
		return INPUT_NM;
	}
	/**
	 * @param iNPUTNM the iNPUT_NM to set
	 */
	public void setINPUT_NM(String iNPUTNM) {
		INPUT_NM = iNPUTNM;
	}
	/**
	 * @return the iNPUT_DT
	 */
	public String getINPUT_DT() {
		return INPUT_DT;
	}
	/**
	 * @param iNPUTDT the iNPUT_DT to set
	 */
	public void setINPUT_DT(String iNPUTDT) {
		INPUT_DT = iNPUTDT;
	}
	/**
	 * @return the mODIFY_CD
	 */
	public String getMODIFY_CD() {
		return MODIFY_CD;
	}
	/**
	 * @param mODIFYCD the mODIFY_CD to set
	 */
	public void setMODIFY_CD(String mODIFYCD) {
		MODIFY_CD = mODIFYCD;
	}
	/**
	 * @return the mODIFY_NM
	 */
	public String getMODIFY_NM() {
		return MODIFY_NM;
	}
	/**
	 * @param mODIFYNM the mODIFY_NM to set
	 */
	public void setMODIFY_NM(String mODIFYNM) {
		MODIFY_NM = mODIFYNM;
	}
	/**
	 * @return the mODIFY_DT
	 */
	public String getMODIFY_DT() {
		return MODIFY_DT;
	}
	/**
	 * @param mODIFYDT the mODIFY_DT to set
	 */
	public void setMODIFY_DT(String mODIFYDT) {
		MODIFY_DT = mODIFYDT;
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
