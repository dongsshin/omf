package com.rap.omc.api.oql.model;

import com.rap.omc.util.NullUtil;
import com.rap.omc.api.oql.model.schema.OmcOQLRoot;
import com.rap.omc.api.oql.utility.OmcComUtility;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;

public class OmcOQLPatternSplitedFrom extends OmcOQLRoot{
    private String uniqueString;
    private String uniqueStringParent;
    private String leftTable;
    private String leftClassList;
    private String leftAlias;
    private String connectedType;
    private String rightTable;
    private String rightClassList;
    private String rightAlias;
	public OmcOQLPatternSplitedFrom() {
		super();
	}
	public OmcOQLPatternSplitedFrom(String uniqueString,
			String uniqueStringParent, String leftTable, String leftClassList,
			String leftAlias, String connectedType, String rightTable,
			String rightClassList, String rightAlias) {
		super();
		this.uniqueString = uniqueString;
		this.uniqueStringParent = uniqueStringParent;
		this.leftTable = leftTable;
		this.leftClassList = leftClassList;
		this.leftAlias = leftAlias;
		this.connectedType = connectedType;
		this.rightTable = rightTable;
		this.rightClassList = rightClassList;
		this.rightAlias = rightAlias;
	}
	public String getUniqueString() {
		return uniqueString;
	}
	public void setUniqueString(String uniqueString) {
		this.uniqueString = uniqueString;
	}
	public String getUniqueStringParent() {
		return uniqueStringParent;
	}
	public void setUniqueStringParent(String uniqueStringParent) {
		this.uniqueStringParent = uniqueStringParent;
	}
	public String getLeftTable() {
		return leftTable;
	}
	public void setLeftTable(String leftTable) {
		this.leftTable = leftTable;
	}
	public String getLeftClassList() {
		return leftClassList;
	}
	public void setLeftClassList(String leftClassList) {
		this.leftClassList = leftClassList;
	}
	public String getLeftAlias() {
		return leftAlias;
	}
	public void setLeftAlias(String leftAlias) {
		this.leftAlias = leftAlias;
	}
	public String getConnectedType() {
		return connectedType;
	}
	public void setConnectedType(String connectedType) {
		this.connectedType = connectedType;
	}
	public String getRightTable() {
		return rightTable;
	}
	public void setRightTable(String rightTable) {
		this.rightTable = rightTable;
	}
	public String getRightClassList() {
		return rightClassList;
	}
	public void setRightClassList(String rightClassList) {
		this.rightClassList = rightClassList;
	}
	public String getRightAlias() {
		return rightAlias;
	}
	public void setRightAlias(String rightAlias) {
		this.rightAlias = rightAlias;
	}
    public String createJoinClause(int processLevel,boolean testMode)
    {
        if (!StrUtil.in(this.connectedType,"MasterConnectedWithFrom","MasterConnectedWithTo","FromConnectedWithMaster","ToConnectedWithMaster","ThisConnectedWithFrom","ThisConnectedWithTo",
                "ToConnectedWithTo"    ,"ToConnectedWithThis","ToConnectedWithFrom",
                "FromConnectedWithThis","FromConnectedWithTo","FromConnectedWithFrom","ThisConnectedWithMaster","MasterConnectedWithThis","ThisConnectedWithFile","Only One")){
            OmcComUtility.error("[Foundation.omcOQLPatternSplitedFrom.createJoinClause]Invalid Connected Type[" + this.connectedType + "].");
        }
        StringBuffer strBuf = new StringBuffer(this.leftAlias);
        strBuf.append(".").append("pclass_name in").append("('").append(this.leftClassList.replace(",", "','")).append("')");
        if(!"Only One".equals(this.connectedType)){
            strBuf.append(OmcFoundationConstant.newline).append("and ").append(this.rightAlias).append(".").append("pclass_name in").append("('").append(this.rightClassList.replace(",", "','")).append("')");
        }
        
        StringBuffer joinBuf = new StringBuffer("");
        
        if("FromConnectedWithMaster".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("pfrom_obid").append(" = ").append(this.rightAlias).append(".").append("pmaster_obid");
        }
        if("ToConnectedWithMaster".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("pto_obid").append(" = ").append(this.rightAlias).append(".").append("pmaster_obid");
        }
        
        if("MasterConnectedWithFrom".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("pmaster_obid").append(" = ").append(this.rightAlias).append(".").append("pfrom_obid");
        }
        if("MasterConnectedWithTo".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("pmaster_obid").append(" = ").append(this.rightAlias).append(".").append("pto_obid");
        }
        
        if("ThisConnectedWithFrom".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("obid").append(" = ").append(this.rightAlias).append(".").append("pfrom_obid");
        }
        if("ThisConnectedWithTo".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("obid").append(" = ").append(this.rightAlias).append(".").append("pto_obid");
        }
        if("ToConnectedWithTo".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("pto_obid").append(" = ").append(this.rightAlias).append(".").append("pto_obid");
        }
        if("ToConnectedWithThis".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("pto_obid").append(" = ").append(this.rightAlias).append(".").append("obid");
        }
        if("ToConnectedWithFrom".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("pto_obid").append(" = ").append(this.rightAlias).append(".").append("pfrom_obid");
        }
        if("FromConnectedWithThis".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("pfrom_obid").append(" = ").append(this.rightAlias).append(".").append("obid");
        }
        if("FromConnectedWithTo".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("pfrom_obid").append(" = ").append(this.rightAlias).append(".").append("pto_obid");
        }
        if("FromConnectedWithFrom".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("pfrom_obid").append(" = ").append(this.rightAlias).append(".").append("pfrom_obid");
        }
        if("ThisConnectedWithMaster".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("obid").append(" = ").append(this.rightAlias).append(".").append("pmaster_obid");
        }
        if("MasterConnectedWithThis".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("pmaster_obid").append(" = ").append(this.rightAlias).append(".").append("obid");
        }        
        if("ThisConnectedWithFile".equals(this.connectedType)){
            joinBuf.append(this.leftAlias).append(".").append("obid").append(" = ").append(this.rightAlias).append(".").append("pfrom_obid");
        }
        if(!NullUtil.isNone(joinBuf.toString())){
            joinBuf.append(OmcFoundationConstant.newline).append("and ").append(strBuf);
            return(joinBuf.toString());
        }else{
            return(strBuf.toString());
        }
    }
}
