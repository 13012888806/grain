/**  
 * @Title: Supplier.java
 * @Package com.xinlin.baseInfo.entity
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-4
 */
package com.xinlin.baseInfo.entity;

import java.io.Serializable;

/**
 * ClassName: Supplier 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-4
 */
public class SupplierVo implements Serializable {
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private String ID; 			// 主键
	private String SUP_CD; 		// 供应商编号
	private String SUP_NM; 		// 供应商名称
	private String SL_CD; 		// 供应商级别编码
	private String SUP_ADDR; 	// 地址
	private String PROVINCE;	// 所属省
	private String CITY;		// 所属市
	private String COUNTY;		// 所属县/区
	private String MOBILE;		// 手机
	private String TEL;			// 固话
	private String EMAIL;		// 电子邮件
	private String INPUT_CD;	// 录入人编码
	private String INPUT_NM;	// 录入人
	private String INPUT_DT;	// 录入时间
	private String MODIFY_CD;	// 修改人编码
	private String MODIFY_NM;	// 修改人
	private String MODIFY_DT;	// 修改时间
	private String DEL_FLAG;	// 删除标示:0已删除，1未删除
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
	 * @return the sUP_CD
	 */
	public String getSUP_CD() {
		return SUP_CD;
	}
	/**
	 * @param sUPCD the sUP_CD to set
	 */
	public void setSUP_CD(String sUPCD) {
		SUP_CD = sUPCD;
	}
	/**
	 * @return the sUP_NM
	 */
	public String getSUP_NM() {
		return SUP_NM;
	}
	/**
	 * @param sUPNM the sUP_NM to set
	 */
	public void setSUP_NM(String sUPNM) {
		SUP_NM = sUPNM;
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
	 * @return the sUP_ADDR
	 */
	public String getSUP_ADDR() {
		return SUP_ADDR;
	}
	/**
	 * @param sUPADDR the sUP_ADDR to set
	 */
	public void setSUP_ADDR(String sUPADDR) {
		SUP_ADDR = sUPADDR;
	}
	/**
	 * @return the pROVINCE
	 */
	public String getPROVINCE() {
		return PROVINCE;
	}
	/**
	 * @param pROVINCE the pROVINCE to set
	 */
	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}
	/**
	 * @return the cITY
	 */
	public String getCITY() {
		return CITY;
	}
	/**
	 * @param cITY the cITY to set
	 */
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	/**
	 * @return the cOUNTY
	 */
	public String getCOUNTY() {
		return COUNTY;
	}
	/**
	 * @param cOUNTY the cOUNTY to set
	 */
	public void setCOUNTY(String cOUNTY) {
		COUNTY = cOUNTY;
	}
	/**
	 * @return the mOBILE
	 */
	public String getMOBILE() {
		return MOBILE;
	}
	/**
	 * @param mOBILE the mOBILE to set
	 */
	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}
	/**
	 * @return the tEL
	 */
	public String getTEL() {
		return TEL;
	}
	/**
	 * @param tEL the tEL to set
	 */
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	/**
	 * @return the eMAIL
	 */
	public String getEMAIL() {
		return EMAIL;
	}
	/**
	 * @param eMAIL the eMAIL to set
	 */
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
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
