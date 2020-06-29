package com.rap.omc.api.oql.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.rap.omc.api.oql.model.OmcOQLPatternClass;
import com.rap.omc.api.oql.model.OmcOQLPatternFrom;
import com.rap.omc.api.oql.model.OmcOQLPatternSQueryTable;
import com.rap.omc.api.oql.model.schema.OmcOQLRoot;
import com.rap.omc.api.oql.utility.OmcComUtility;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.oql.utility.OmcUtility;


public class OmcOQLPatternSQueryResult extends OmcOQLRoot{
	private String subQueryType;
	private String originQueryPattern;
	private String thisJoinPattern;
	private String relationShip;
	private String attributeName;
	private String selectAlias;
	private String parameterForWhere;
	private String operatorForWhere;
	private String parameterValueForWhere;
	private ArrayList<OmcOQLPatternSQueryTable> patternSQueryTableList;
	private ArrayList<OmcOQLPatternClass> patternClassList;
	private String fromPatternStr;
	private String lastAlias;
	private String selectStr;
	private String fromStr;
	private String joinStr;
	private String whereStr;
	private String whereParameterStr;
	private String whereInnerStr;
	private String whereInnerParameterStr;
	private boolean isNot;
	private String whereResolvedStr;
	private String sqlSelectStrAll;
	public OmcOQLPatternSQueryResult() {
		super();
	}
	
	/**
     * @param subQueryType
     * @param originQueryPattern
     * @param thisJoinPattern
     * @param relationShip
     * @param attributeName
     * @param selectAlias
     * @param parameterForWhere
     * @param operatorForWhere
     * @param parameterValueForWhere
     * @param patternSQueryTableList
     * @param patternClassList
     * @param fromPatternStr
     * @param lastAlias
     * @param selectStr
     * @param fromStr
     * @param joinStr
     * @param whereStr
     * @param whereParameterStr
     * @param whereInnerStr
     * @param whereInnerParameterStr
     * @param isNot
     * @param whereResolvedStr
     * @param sqlSelectStrAll
     */
    public OmcOQLPatternSQueryResult(String subQueryType, String originQueryPattern, String thisJoinPattern,
            String relationShip, String attributeName, String selectAlias, String parameterForWhere,
            String operatorForWhere, String parameterValueForWhere,
            ArrayList<OmcOQLPatternSQueryTable> patternSQueryTableList, ArrayList<OmcOQLPatternClass> patternClassList,
            String fromPatternStr, String lastAlias, String selectStr, String fromStr, String joinStr, String whereStr,
            String whereParameterStr, String whereInnerStr, String whereInnerParameterStr, boolean isNot,
            String whereResolvedStr, String sqlSelectStrAll) {
        super();
        this.subQueryType = subQueryType;
        this.originQueryPattern = originQueryPattern;
        this.thisJoinPattern = thisJoinPattern;
        this.relationShip = relationShip;
        this.attributeName = attributeName;
        this.selectAlias = selectAlias;
        this.parameterForWhere = parameterForWhere;
        this.operatorForWhere = operatorForWhere;
        this.parameterValueForWhere = parameterValueForWhere;
        this.patternSQueryTableList = patternSQueryTableList;
        this.patternClassList = patternClassList;
        this.fromPatternStr = fromPatternStr;
        this.lastAlias = lastAlias;
        this.selectStr = selectStr;
        this.fromStr = fromStr;
        this.joinStr = joinStr;
        this.whereStr = whereStr;
        this.whereParameterStr = whereParameterStr;
        this.whereInnerStr = whereInnerStr;
        this.whereInnerParameterStr = whereInnerParameterStr;
        this.isNot = isNot;
        this.whereResolvedStr = whereResolvedStr;
        this.sqlSelectStrAll = sqlSelectStrAll;
    }

    public OmcOQLPatternSQueryResult(String subQueryType,
			String originQueryPattern, String thisJoinPattern,
			String relationShip, String attributeName, String selectAlias,
			String parameterForWhere, String operatorForWhere,
			String parameterValueForWhere,
			ArrayList<OmcOQLPatternSQueryTable> patternSQueryTableList,
			boolean isNot) {
		super();
		this.subQueryType = subQueryType;
		this.originQueryPattern = originQueryPattern;
		this.thisJoinPattern = thisJoinPattern;
		this.relationShip = relationShip;
		this.attributeName = attributeName;
		this.selectAlias = selectAlias;
		this.parameterForWhere = parameterForWhere;
		this.operatorForWhere = operatorForWhere;
		this.parameterValueForWhere = parameterValueForWhere;
		this.patternSQueryTableList = patternSQueryTableList;
		this.isNot = isNot;
	}
	public String getSubQueryType() {
		return subQueryType;
	}
	public void setSubQueryType(String subQueryType) {
		this.subQueryType = subQueryType;
	}
	public String getOriginQueryPattern() {
		return originQueryPattern;
	}
	public void setOriginQueryPattern(String originQueryPattern) {
		this.originQueryPattern = originQueryPattern;
	}
	public String getThisJoinPattern() {
		return thisJoinPattern;
	}
	public void setThisJoinPattern(String thisJoinPattern) {
		this.thisJoinPattern = thisJoinPattern;
	}
	public String getRelationShip() {
		return relationShip;
	}
	public void setRelationShip(String relationShip) {
		this.relationShip = relationShip;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getSelectAlias() {
		return selectAlias;
	}
	public void setSelectAlias(String selectAlias) {
		this.selectAlias = selectAlias;
	}
	public String getParameterForWhere() {
		return parameterForWhere;
	}
	public void setParameterForWhere(String parameterForWhere) {
		this.parameterForWhere = parameterForWhere;
	}
	public String getOperatorForWhere() {
		return operatorForWhere;
	}
	public void setOperatorForWhere(String operatorForWhere) {
		this.operatorForWhere = operatorForWhere;
	}
	public String getParameterValueForWhere() {
		return parameterValueForWhere;
	}
	public void setParameterValueForWhere(String parameterValueForWhere) {
		this.parameterValueForWhere = parameterValueForWhere;
	}
	public ArrayList<OmcOQLPatternSQueryTable> getPatternSQueryTableList() {
		return patternSQueryTableList;
	}
	public void setPatternSQueryTableList(
	        ArrayList<OmcOQLPatternSQueryTable> patternSQueryTableList) {
		this.patternSQueryTableList = patternSQueryTableList;
	}
	public ArrayList<OmcOQLPatternClass> getPatternClassList() {
		return patternClassList;
	}
	public void setPatternClassList(ArrayList<OmcOQLPatternClass> patternClassList) {
		this.patternClassList = patternClassList;
	}
	public String getFromPatternStr() {
		return fromPatternStr;
	}
	public void setFromPatternStr(String fromPatternStr) {
		this.fromPatternStr = fromPatternStr;
	}
	public String getLastAlias() {
		return lastAlias;
	}
	public void setLastAlias(String lastAlias) {
		this.lastAlias = lastAlias;
	}
	public String getSelectStr() {
		return selectStr;
	}
	public void setSelectStr(String selectStr) {
		this.selectStr = selectStr;
	}
	public String getFromStr() {
		return fromStr;
	}
	public void setFromStr(String fromStr) {
		this.fromStr = fromStr;
	}
	public String getJoinStr() {
		return joinStr;
	}
	public void setJoinStr(String joinStr) {
		this.joinStr = joinStr;
	}
	public String getWhereStr() {
		return whereStr;
	}
	public void setWhereStr(String whereStr) {
		this.whereStr = whereStr;
	}
	public String getWhereParameterStr() {
		return whereParameterStr;
	}
	public void setWhereParameterStr(String whereParameterStr) {
		this.whereParameterStr = whereParameterStr;
	}
	public String getWhereInnerStr() {
		return whereInnerStr;
	}
	public void setWhereInnerStr(String whereInnerStr) {
		this.whereInnerStr = whereInnerStr;
	}
	public String getWhereInnerParameterStr() {
		return whereInnerParameterStr;
	}
	public void setWhereInnerParameterStr(String whereInnerParameterStr) {
		this.whereInnerParameterStr = whereInnerParameterStr;
	}
	public boolean isNot() {
		return isNot;
	}
	public void setNot(boolean isNot) {
		this.isNot = isNot;
	}
	public String getWhereResolvedStr() {
		return whereResolvedStr;
	}
	public void setWhereResolvedStr(String whereResolvedStr) {
		this.whereResolvedStr = whereResolvedStr;
	}
	public String getSqlSelectStrAll() {
		return sqlSelectStrAll;
	}
	public void setSqlSelectStrAll(String sqlSelectStrAll) {
		this.sqlSelectStrAll = sqlSelectStrAll;
	}
	
	@Override
    public String toString(){
        return "OmcOQLPatternSQueryResult [subQueryType=" + subQueryType + ", originQueryPattern=" + originQueryPattern
                + ", thisJoinPattern=" + thisJoinPattern + ", relationShip=" + relationShip + ", attributeName="
                + attributeName + ", selectAlias=" + selectAlias + ", parameterForWhere=" + parameterForWhere
                + ", operatorForWhere=" + operatorForWhere + ", parameterValueForWhere=" + parameterValueForWhere
                + ", patternSQueryTableList=" + patternSQueryTableList + ", patternClassList=" + patternClassList
                + ", fromPatternStr=" + fromPatternStr + ", lastAlias=" + lastAlias + ", selectStr=" + selectStr
                + ", fromStr=" + fromStr + ", joinStr=" + joinStr + ", whereStr=" + whereStr + ", whereParameterStr="
                + whereParameterStr + ", whereInnerStr=" + whereInnerStr + ", whereInnerParameterStr="
                + whereInnerParameterStr + ", isNot=" + isNot + ", whereResolvedStr=" + whereResolvedStr
                + ", sqlSelectStrAll=" + sqlSelectStrAll + "]";
    }
    public void createSelectClause(int processLevel,boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    String selectStr = this.makeSelectPattternForSubQuery(this.getLastAliasForSTable(),this.getAttributeName(),processLevel +1 ,testMode);
		this.setSelectStr(selectStr);
		OmcComUtility.logWriteEnd(processLevel, testMode);
	}
	public void createFromClause(int processLevel,boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
		StringBuffer strBuff = new StringBuffer();
		for(OmcOQLPatternSQueryTable patternSQueryTable : this.getPatternSQueryTableList())
		{
			if(!StrUtil.isEmpty(strBuff)) strBuff.append(",").append(OmcFoundationConstant.newline);
			strBuff.append(patternSQueryTable.getDbmsTableStr());
			strBuff.append(" ");
			strBuff.append(patternSQueryTable.getAlias());
		}
		this.setFromStr(strBuff.toString());
		OmcComUtility.logWriteEnd(processLevel, testMode);
	}
	public void createJoinClause(int processLevel,boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
		StringBuffer strBuffForJoin       = new StringBuffer();
		StringBuffer strBuffForClassWhere = new StringBuffer();
		boolean isFirst = true;
		for(OmcOQLPatternSQueryTable patternSQueryTable : this.getPatternSQueryTableList())
		{
			if (isFirst)
			{
                strBuffForJoin.append("v_obid");
                strBuffForJoin.append(" = ");
                strBuffForJoin.append(patternSQueryTable.getAlias());
                if(patternSQueryTable.getDirectionLeft().equals("self"))
				{
				    strBuffForJoin.append(".obid");
				}
				else if(patternSQueryTable.getDirectionLeft().equals("From"))
				{
					strBuffForJoin.append(".pfrom_obid");	
				}
				else
				{
					strBuffForJoin.append(".pto_obid");
				}
			}
			else
			{
				if(patternSQueryTable.getDirectionLeft().equals("self"))
				{
                    strBuffForJoin.append(patternSQueryTable.getAlias()).append(".obid");
				}else if(patternSQueryTable.getDirectionLeft().equals("From"))
				{
                    strBuffForJoin.append(patternSQueryTable.getAlias()).append(".pfrom_obid");
				}else if(patternSQueryTable.getDirectionLeft().equals("To"))
				{
				    strBuffForJoin.append(patternSQueryTable.getAlias()).append(".pto_obid");
				}else if(patternSQueryTable.getDirectionLeft().equals("master"))
				{
                    strBuffForJoin.append(patternSQueryTable.getAlias()).append(".ppart_master_obid");
				}else if(patternSQueryTable.getDirectionLeft().equals("revision"))
				{
				    strBuffForJoin.append(patternSQueryTable.getAlias()).append(".platest_part_revision_obid");
				}
			}

			if(!StrUtil.isEmpty(patternSQueryTable.getClassNameStr()))
			{
				if(!StrUtil.isEmpty(strBuffForClassWhere)) strBuffForClassWhere.append(OmcFoundationConstant.newline).append("and ");
				strBuffForClassWhere.append(patternSQueryTable.getAlias()).append(".pclass_name in (").append(StrUtil.makeWhereInStr(patternSQueryTable.getClassNameList())).append(")");
			}
			if(!StrUtil.isEmpty(patternSQueryTable.getClassNameStrFrom()))
			{
				if(!StrUtil.isEmpty(strBuffForClassWhere)) strBuffForClassWhere.append(OmcFoundationConstant.newline).append("and ");
				strBuffForClassWhere.append(patternSQueryTable.getAlias()).append(".pfrom_class in (").append(StrUtil.makeWhereInStr(patternSQueryTable.getClassNameListFrom())).append(")");
			}
			if(!StrUtil.isEmpty(patternSQueryTable.getClassNameStrTo()))
			{
				if(!StrUtil.isEmpty(strBuffForClassWhere)) strBuffForClassWhere.append(OmcFoundationConstant.newline).append("and ");
				//strBuffForClassWhere.append(patternSQueryTable.getAlias());
                strBuffForClassWhere.append(patternSQueryTable.getAlias()).append(".pto_class in (").append(StrUtil.makeWhereInStr(patternSQueryTable.getClassNameListTo())).append(")");
			}
			if(patternSQueryTable.getDirectionRight().equals("self"))
			{
			    strBuffForJoin.append(OmcFoundationConstant.newline).append("and ").append(patternSQueryTable.getAlias()).append(".obid = ");
			}else if(patternSQueryTable.getDirectionRight().equals("From"))
			{
                strBuffForJoin.append(OmcFoundationConstant.newline).append("and ").append(patternSQueryTable.getAlias()).append(".pfrom_obid = ");
			}else if(patternSQueryTable.getDirectionRight().equals("To"))
			{
                strBuffForJoin.append(OmcFoundationConstant.newline).append("and ").append(patternSQueryTable.getAlias()).append(".pto_obid = ");
			}else if(patternSQueryTable.getDirectionRight().equals("master"))
			{
                strBuffForJoin.append(OmcFoundationConstant.newline).append("and ").append(patternSQueryTable.getAlias()).append(".ppart_master_obid = ");
			}else if(patternSQueryTable.getDirectionRight().equals("revision"))
			{
                strBuffForJoin.append(OmcFoundationConstant.newline).append("and ").append(patternSQueryTable.getAlias()).append(".platest_part_revision_obid = ");
			}
			isFirst = false;
		}
		if(!StrUtil.isEmpty(strBuffForClassWhere))
		{
			strBuffForJoin.append(OmcFoundationConstant.newline).append("and ").append(strBuffForClassWhere.toString());
		}
		this.setJoinStr(strBuffForJoin.toString());
		OmcComUtility.logWriteEnd(processLevel, testMode);
	}
	public void createFromPattern(int processLevel,boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
		StringBuffer strBuff = new StringBuffer();
		for(OmcOQLPatternSQueryTable patternSQueryTable : this.getPatternSQueryTableList())
		{
			if(!StrUtil.isEmpty(strBuff)){
				strBuff.append(OmcFoundationConstant.OQL_SELECTION_Seperator);
			}
			strBuff.append("<[");
			strBuff.append(patternSQueryTable.getClassNameStr());
			strBuff.append("]@");
			strBuff.append(patternSQueryTable.getAlias());
			strBuff.append(">");
			strBuff.append("ToConnectedWithThis");
			strBuff.append("<[");
			strBuff.append(patternSQueryTable.getClassNameStr());
			strBuff.append("]@");
			strBuff.append(patternSQueryTable.getAlias());
			strBuff.append(">");
			this.setLastAlias(patternSQueryTable.getAlias());
		}
		this.setFromPatternStr(strBuff.toString());
		OmcComUtility.logWriteEnd(processLevel, testMode);
	}
	public void createParmClause(int processLevel,boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
		StringBuffer strBuff = new StringBuffer();
		for(OmcOQLPatternSQueryTable patternSQueryTable : this.getPatternSQueryTableList())
		{
			if(!StrUtil.isEmpty(patternSQueryTable.getPatternSQueryConditon().getWhereParameterStrOut())){
			    if(!StrUtil.isEmpty(strBuff)) strBuff.append(OmcFoundationConstant.ATTRIBUTE_DELIMINATOR_NAME);
			    strBuff.append(patternSQueryTable.getPatternSQueryConditon().getWhereParameterStrOut());
			}
		}
		this.setWhereInnerParameterStr(strBuff.toString());
		OmcComUtility.logWriteEnd(processLevel, testMode);
	}
	public void createWhereClause(int processLevel,boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
		StringBuffer strBuff = new StringBuffer();
		strBuff.append(this.getWhereInnerStr());
		
		for(OmcOQLPatternSQueryTable patternSQueryTable : this.getPatternSQueryTableList())
		{
			if(!StrUtil.isEmpty(strBuff) && !StrUtil.isEmpty(patternSQueryTable.getPatternSQueryConditon().getWhereConditionOut())) strBuff.append("&&");
			if(!StrUtil.isEmpty(patternSQueryTable.getPatternSQueryConditon().getWhereConditionOut())) strBuff.append(patternSQueryTable.getPatternSQueryConditon().getWhereConditionOut());
		}
		this.setWhereInnerStr(strBuff.toString());
		if (this.getSubQueryType().equals("condition"))
		{
			StringBuffer strBuffWhere = new StringBuffer();
			strBuffWhere.append(this.getSelectStr()).append(this.getOperatorForWhere()).append(this.getParameterForWhere());
			StringBuffer strBuffParm  = new StringBuffer();
			strBuffParm.append(StrUtil.getStringForFromTo(this.getParameterForWhere(), 1, "<%", "%>"));
			strBuffParm.append("^:^").append(this.getParameterValueForWhere());
			if(!StrUtil.isEmpty(strBuffWhere)){
				StringBuffer temWhere = new StringBuffer().append(this.getWhereStr());
				if(!StrUtil.isEmpty(temWhere)){
					temWhere.append(this.getWhereStr()).append("&&");
				}
				temWhere.append(strBuffWhere.toString());
				this.setWhereStr(temWhere.toString());
			}
			if(!StrUtil.isEmpty(strBuffParm)){
				StringBuffer temWhere = new StringBuffer().append(this.getWhereParameterStr());
				if(!StrUtil.isEmpty(temWhere)){
					temWhere.append(this.getWhereStr()).append("^~^");
				}
				temWhere.append(strBuffParm.toString());
				this.setWhereParameterStr(temWhere.toString());
			}
		}
		OmcComUtility.logWriteEnd(processLevel, testMode);
	}
	public void resolveWhereClause(int processLevel,boolean testMode)
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
	    ArrayList<OmcOQLPatternClass>       classList         = new ArrayList<OmcOQLPatternClass>();

		HashMap<String, Object> map = OmcUtility.getParsedOQLPatternFromInfo(this.getFromPatternStr(),processLevel+1,testMode);
	    ArrayList<OmcOQLPatternFrom>  fromList = (ArrayList<OmcOQLPatternFrom>)map.get(OmcFoundationConstant.OQL_MAPPARM_PARSED_FROM);

	    classList = OmcUtility.getFromClassSet((ArrayList<OmcOQLPatternFrom>)fromList, processLevel, testMode);
		StringBuffer strBuffWhereResolved = new StringBuffer();
		String innerWhereResolvedStr = OmcUtility.createWhereClause(classList, this.getWhereInnerStr(), this.getWhereInnerParameterStr(), "", true, processLevel+1, testMode);
		strBuffWhereResolved.append(innerWhereResolvedStr);
		String outerWhereResolvedStr = "";
		if (this.getSubQueryType().equals("condition"))
		{
		    outerWhereResolvedStr = OmcUtility.createWhereClause(classList, this.getWhereStr(), this.getWhereParameterStr(), "", false, processLevel+1, testMode);
		}
		else
		{
		    outerWhereResolvedStr = OmcUtility.createWhereClause(classList, this.getWhereStr(), this.getWhereParameterStr(), "", true, processLevel+1, testMode);
		}
		if (!StrUtil.isEmpty(outerWhereResolvedStr)) 
		{
		    if (!StrUtil.isEmpty(strBuffWhereResolved)) strBuffWhereResolved.append(OmcFoundationConstant.newline).append("and ");
			strBuffWhereResolved.append(outerWhereResolvedStr);
		}
		this.setWhereResolvedStr(strBuffWhereResolved.toString());
		OmcOQLSelectClauseVO selectClauseVO = OmcUtility.createSelectClause(classList,this.getSelectStr(),false, false, processLevel+1, testMode);
		String selectStr = selectClauseVO.getFirstSelectStr();
        selectStr = selectStr.replace("[[", "[");
        this.setSelectStr(selectStr);
        this.setPatternClassList(classList);
        OmcComUtility.logWriteEnd(processLevel, testMode);
	}
    public void createSqlAll(int processLevel,boolean testMode){
        OmcComUtility.logWriteStart(processLevel, testMode);
    	StringBuffer sqlQueryAll = new StringBuffer();
    	if (this.getSubQueryType().equals("condition"))
    	{
    		sqlQueryAll.append("select 1");
    	}
    	else
    	{
    		sqlQueryAll.append("select  ").append(this.getSelectStr());
    	}
    	sqlQueryAll.append(OmcFoundationConstant.newline).append("from ").append(this.getFromStr());
    	sqlQueryAll.append(OmcFoundationConstant.newline).append("where ").append(this.getJoinStr());
    	if (!StrUtil.isEmpty(this.getWhereResolvedStr())) sqlQueryAll.append(OmcFoundationConstant.newline).append("and ").append(this.getWhereResolvedStr());
    	this.setSqlSelectStrAll(sqlQueryAll.toString());
    	OmcComUtility.logWriteEnd(processLevel, testMode);
    }
	private String getLastAliasForSTable()
	{
		if(this.getPatternSQueryTableList() != null && !this.getPatternSQueryTableList().isEmpty()){
			return((this.getPatternSQueryTableList().get(this.getPatternSQueryTableList().size()-1)).getAlias());
		}
		return("");
	}
	private String makeSelectPattternForSubQuery(String alias, String selectIn,int processLevel,boolean testMode) 
	{
	    OmcComUtility.logWriteStart(processLevel, testMode);
		String select = StrUtil.clearStringData(selectIn);
		String rtnString = selectIn;
        if (StrUtil.inStr(select, "(") > 0)  select = StrUtil.getStringForFromTo(select, 1, "(", ")");
		String[] splitedStr = select.split(",");
		for(int i = 0; i < splitedStr.length; i++)
		{
			StringBuffer tempStrBuf = new StringBuffer();
			if((StrUtil.inStr(splitedStr[i], "[") == 0) || (StrUtil.inStr(splitedStr[i], "]") == 0))
			{
				tempStrBuf.append("@").append(alias).append(".[").append(splitedStr[i]).append("]");
			}
			else
			{
				tempStrBuf.append("@").append(alias).append(".").append(splitedStr[i]);
			}
			rtnString = StrUtil.replace(rtnString ,splitedStr[i], tempStrBuf.toString());
		}
		OmcComUtility.logWriteEnd(processLevel, testMode);
		return(rtnString.toString());
	}
}
