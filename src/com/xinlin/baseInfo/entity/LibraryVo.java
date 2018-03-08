/**  
 * @Title: Library.java
 * @Package com.xinlin.baseInfo.entity
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-4
 */
package com.xinlin.baseInfo.entity;

import java.io.Serializable;

/**
 * ClassName: Library 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-4
 */
public class LibraryVo implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private String ID;				// 出库主键
	private String WS_CD;			// 库存编码
	private String VAR_CD;			// 品种编码
	private String OUT_CNT;			// 出库数量
	private String CNT_UNIT;		// 数量单位
	private String WGT;				// 单位重量
	private String WGT_UNIT;		// 重量单位
	private String TOTAL_WGT;		// 总重量
	private String CUS_CD;			// 客户编码
	
	private String OUT_NM;			//出库人
	private String OUT_DT;			//出库时间
	
	private String INPUT_CD;		// 录入人编码
	private String INPUT_NM;		// 录入人
	private String INPUT_DT;		// 录入时间
	private String MODIFY_CD;		// 修改人编码
	private String MODIFY_NM;		// 修改人
	private String MODIFY_DT;		// 修改时间
	private String DEL_FLAG;		// 删除标示:0已删除，1未删除
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
	 * @return the oUT_CNT
	 */
	public String getOUT_CNT() {
		return OUT_CNT;
	}
	/**
	 * @param oUTCNT the oUT_CNT to set
	 */
	public void setOUT_CNT(String oUTCNT) {
		OUT_CNT = oUTCNT;
	}
	/**
	 * @return the cNT_UNIT
	 */
	public String getCNT_UNIT() {
		return CNT_UNIT;
	}
	/**
	 * @param cNTUNIT the cNT_UNIT to set
	 */
	public void setCNT_UNIT(String cNTUNIT) {
		CNT_UNIT = cNTUNIT;
	}
	/**
	 * @return the wGT
	 */
	public String getWGT() {
		return WGT;
	}
	/**
	 * @param wGT the wGT to set
	 */
	public void setWGT(String wGT) {
		WGT = wGT;
	}
	/**
	 * @return the wGT_UNIT
	 */
	public String getWGT_UNIT() {
		return WGT_UNIT;
	}
	/**
	 * @param wGTUNIT the wGT_UNIT to set
	 */
	public void setWGT_UNIT(String wGTUNIT) {
		WGT_UNIT = wGTUNIT;
	}
	/**
	 * @return the tOTAL_WGT
	 */
	public String getTOTAL_WGT() {
		return TOTAL_WGT;
	}
	/**
	 * @param tOTALWGT the tOTAL_WGT to set
	 */
	public void setTOTAL_WGT(String tOTALWGT) {
		TOTAL_WGT = tOTALWGT;
	}
	/**
	 * @return the cUS_CD
	 */
	public String getCUS_CD() {
		return CUS_CD;
	}
	/**
	 * @param cUSCD the cUS_CD to set
	 */
	public void setCUS_CD(String cUSCD) {
		CUS_CD = cUSCD;
	}
	
	/**
	 * @return the oUT_NM
	 */
	public String getOUT_NM() {
		return OUT_NM;
	}
	/**
	 * @param oUTNM the oUT_NM to set
	 */
	public void setOUT_NM(String oUTNM) {
		OUT_NM = oUTNM;
	}
	/**
	 * @return the oUT_DT
	 */
	public String getOUT_DT() {
		return OUT_DT;
	}
	/**
	 * @param oUTDT the oUT_DT to set
	 */
	public void setOUT_DT(String oUTDT) {
		OUT_DT = oUTDT;
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
