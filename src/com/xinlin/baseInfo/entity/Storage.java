/**  
 * @Title: Storage.java
 * @Package com.xinlin.baseInfo.entity
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-4
 */
package com.xinlin.baseInfo.entity;

import java.io.Serializable;

import com.xinlin.app.entity.vo.PageQueryParams;

/**
 * ClassName: Storage
 * 
 * @Description: TODO
 * @author Chenxl
 * @date 2015-6-4
 */
public class Storage extends PageQueryParams implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private String ID;				// 主键
	private String CUS_CD;			// 客户编号
	private String WS_CD;			// 仓库编号
	
	private String WS_NM;			//仓库表的仓库名称
	
	private String VAR_CD;			// 品种编号
	private String CNT;				// 数量
	private String CNT_UNIT;		// 单位编号
	private String WGT;				// 单位重量
	private String TOTAL_WGT;		// 总重量
	private String WGT_UNIT;		// 重量单位
	private String SUP_CD;			// 供应商编码
	private String STORAGE_TPY;		// 入库种类:供应商采购、客户退货
	private String RMK;				// 备注
	
	private String IN_NM;//入库人
	private String IN_DT;//入库时间
	
	private String INPUT_CD;		// 录入人编码
	private String INPUT_NM;		// 录入人
	private String INPUT_DT;		// 录入时间
	private String MODIFY_CD;		// 修改人编码
	private String MODIFY_NM;		// 修改人
	private String MODIFY_DT;		// 修改时间
	private String DEL_FLAG;		// 删除标示：0已删除，1未删除
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
	 * @return the cNT
	 */
	public String getCNT() {
		return CNT;
	}
	/**
	 * @param cNT the cNT to set
	 */
	public void setCNT(String cNT) {
		CNT = cNT;
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
	 * @return the sTORAGE_TPY
	 */
	public String getSTORAGE_TPY() {
		return STORAGE_TPY;
	}
	/**
	 * @param sTORAGETPY the sTORAGE_TPY to set
	 */
	public void setSTORAGE_TPY(String sTORAGETPY) {
		STORAGE_TPY = sTORAGETPY;
	}
	/**
	 * @return the rMK
	 */
	public String getRMK() {
		return RMK;
	}
	/**
	 * @param rMK the rMK to set
	 */
	public void setRMK(String rMK) {
		RMK = rMK;
	}
	
	/**
	 * @return the iN_NM
	 */
	public String getIN_NM() {
		return IN_NM;
	}
	/**
	 * @param iNNM the iN_NM to set
	 */
	public void setIN_NM(String iNNM) {
		IN_NM = iNNM;
	}
	/**
	 * @return the iN_DT
	 */
	public String getIN_DT() {
		return IN_DT;
	}
	/**
	 * @param iNDT the iN_DT to set
	 */
	public void setIN_DT(String iNDT) {
		IN_DT = iNDT;
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
