/**  
 * @Title: WareHouse.java
 * @Package com.xinlin.baseInfo.entity
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-4
 */
package com.xinlin.baseInfo.entity;

import java.io.Serializable;

/**
 * ClassName: WareHouse 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-4
 */
public class WareHouseVo implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private String ID;			//varchar(30) not null  '主键',
	private String WS_CD;		//varchar(30)  '仓库编号',
	private String WS_NM;		//varchar(255)  '仓库名称',
	private String WS_ADDR;		//varchar(255)  '仓库地址',
	private String LENGTH;		//double  '长',
	private String WIDTH;		//double  '宽',
	private String HEIGHT;		//double  '高',
	private String WS_TYP;		//char(1)  '类型：自购、租用',
	private String WS_PRC;		//double  '价格：默认元',
	private String PAY_TYP;		//char(1)  '付费方式：天、月、年',
	private String PROVINCE;	//varchar(30)  '所属省',
	private String CITY;		//varchar(30)  '所属市',
	private String COUNTY;		//varchar(30)  '所属县/区',
	private String INPUT_DT;	//date  '录入时间',
	private String INPUT_CD;	//varchar(12)  '录入人编码',
	private String INPUT_NM;	//varchar(30)  '录入人',
	private String MODIFY_DT;	//date  '修改时间',
	private String MODIFY_CD;	//varchar(12)  '修改人编码',
	private String MODIFY_NM;	//varchar(30)  '修改人',
	private String DEL_FLAG;	//char(1)  '删除标示 ： 0已删除，1未删除',
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
	 * @return the wS_CD
	 */
	public String getWS_CD() {
		return WS_CD;
	}
	/**
	 * @param wSCD the wS_CD to set
	 */
	public void setWS_CD(String wSCD) {
		WS_CD = wSCD;
	}
	/**
	 * @return the wS_NM
	 */
	public String getWS_NM() {
		return WS_NM;
	}
	/**
	 * @param wSNM the wS_NM to set
	 */
	public void setWS_NM(String wSNM) {
		WS_NM = wSNM;
	}
	/**
	 * @return the wS_ADDR
	 */
	public String getWS_ADDR() {
		return WS_ADDR;
	}
	/**
	 * @param wSADDR the wS_ADDR to set
	 */
	public void setWS_ADDR(String wSADDR) {
		WS_ADDR = wSADDR;
	}
	/**
	 * @return the lENGTH
	 */
	public String getLENGTH() {
		return LENGTH;
	}
	/**
	 * @param lENGTH the lENGTH to set
	 */
	public void setLENGTH(String lENGTH) {
		LENGTH = lENGTH;
	}
	/**
	 * @return the wIDTH
	 */
	public String getWIDTH() {
		return WIDTH;
	}
	/**
	 * @param wIDTH the wIDTH to set
	 */
	public void setWIDTH(String wIDTH) {
		WIDTH = wIDTH;
	}
	/**
	 * @return the hEIGHT
	 */
	public String getHEIGHT() {
		return HEIGHT;
	}
	/**
	 * @param hEIGHT the hEIGHT to set
	 */
	public void setHEIGHT(String hEIGHT) {
		HEIGHT = hEIGHT;
	}
	/**
	 * @return the wS_TYP
	 */
	public String getWS_TYP() {
		return WS_TYP;
	}
	/**
	 * @param wSTYP the wS_TYP to set
	 */
	public void setWS_TYP(String wSTYP) {
		WS_TYP = wSTYP;
	}
	/**
	 * @return the wS_PRC
	 */
	public String getWS_PRC() {
		return WS_PRC;
	}
	/**
	 * @param wSPRC the wS_PRC to set
	 */
	public void setWS_PRC(String wSPRC) {
		WS_PRC = wSPRC;
	}
	/**
	 * @return the pAY_TYP
	 */
	public String getPAY_TYP() {
		return PAY_TYP;
	}
	/**
	 * @param pAYTYP the pAY_TYP to set
	 */
	public void setPAY_TYP(String pAYTYP) {
		PAY_TYP = pAYTYP;
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
