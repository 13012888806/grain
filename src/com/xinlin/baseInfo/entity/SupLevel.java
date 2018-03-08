/**  
 * @Title: SupLevel.java
 * @Package com.xinlin.baseInfo.entity
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-4
 */
package com.xinlin.baseInfo.entity;

import java.io.Serializable;

import com.xinlin.app.entity.vo.PageQueryParams;

/**
 * ClassName: SupLevel 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-4
 */
public class SupLevel extends PageQueryParams implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private String ID;				//主键
	private String SL_CD;			//级别编码
	private String SL_NM;			//级别名称
	private String VAR_CD;			//品种编码
	private String PRC;				//单价
	private String UNIT_CD;			//单位编码
	private String DISCOUNT_RATE;	//折扣率
	private String ADJUST_LIMIT;	//调整额
	private String FINAL_PRC;		//成交价格
	private String INPUT_CD;		//录入编码
	private String INPUT_NM;		//录入人
	private String INPUT_DT;		//录入时间
	private String MODIFY_CD;		//修改人编码
	private String MODIFY_NM;		//修改人
	private String MODIFY_DT;		//修改时间
	private String DEL_FLAG;		//删除标示:0已删除，1未删除
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
	 * @return the sL_CD
	 */
	public String getSL_CD() {
		return SL_CD;
	}
	/**
	 * @param sLCD the sL_CD to set
	 */
	public void setSL_CD(String sLCD) {
		SL_CD = sLCD;
	}
	/**
	 * @return the sL_NM
	 */
	public String getSL_NM() {
		return SL_NM;
	}
	/**
	 * @param sLNM the sL_NM to set
	 */
	public void setSL_NM(String sLNM) {
		SL_NM = sLNM;
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
	 * @return the pRC
	 */
	public String getPRC() {
		return PRC;
	}
	/**
	 * @param pRC the pRC to set
	 */
	public void setPRC(String pRC) {
		PRC = pRC;
	}
	/**
	 * @return the uNIT_CD
	 */
	public String getUNIT_CD() {
		return UNIT_CD;
	}
	/**
	 * @param uNITCD the uNIT_CD to set
	 */
	public void setUNIT_CD(String uNITCD) {
		UNIT_CD = uNITCD;
	}
	/**
	 * @return the dISCOUNT_RATE
	 */
	public String getDISCOUNT_RATE() {
		return DISCOUNT_RATE;
	}
	/**
	 * @param dISCOUNTRATE the dISCOUNT_RATE to set
	 */
	public void setDISCOUNT_RATE(String dISCOUNTRATE) {
		DISCOUNT_RATE = dISCOUNTRATE;
	}
	/**
	 * @return the aDJUST_LIMIT
	 */
	public String getADJUST_LIMIT() {
		return ADJUST_LIMIT;
	}
	/**
	 * @param aDJUSTLIMIT the aDJUST_LIMIT to set
	 */
	public void setADJUST_LIMIT(String aDJUSTLIMIT) {
		ADJUST_LIMIT = aDJUSTLIMIT;
	}
	/**
	 * @return the fINAL_PRC
	 */
	public String getFINAL_PRC() {
		return FINAL_PRC;
	}
	/**
	 * @param fINALPRC the fINAL_PRC to set
	 */
	public void setFINAL_PRC(String fINALPRC) {
		FINAL_PRC = fINALPRC;
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
