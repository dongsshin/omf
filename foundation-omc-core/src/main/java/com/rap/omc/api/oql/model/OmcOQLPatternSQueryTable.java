package com.rap.omc.api.oql.model;

import java.util.List;

import com.rap.omc.api.oql.model.schema.OmcOQLRoot;

public class OmcOQLPatternSQueryTable extends OmcOQLRoot{
	private String          directionLeft;
	private String          directionRight;
	private List<String>    classNameList;
	private String          classNameStr;
	private List<String>    classNameListFrom;
	private String          classNameStrFrom;
	private List<String>    classNameListTo;
	
    public List<String> getClassNameListFrom(){
        return classNameListFrom;
    }

    
    public List<String> getClassNameListTo(){
        return classNameListTo;
    }

    
    public void setClassNameListFrom(List<String> classNameListFrom){
        this.classNameListFrom = classNameListFrom;
    }

    
    public void setClassNameListTo(List<String> classNameListTo){
        this.classNameListTo = classNameListTo;
    }

    private String          classNameStrTo;
    private List<String>    dbmsTableList;
    private String          dbmsTableStr;
    private String          alias;
    private String          conditionStr;
    private String          firstAttribute;
    private String          lastAttribute;
    private OmcOQLPatternSQueryCondition  patternSQueryConditon;
	public OmcOQLPatternSQueryTable() {
		super();
	}

	/**
     * @param directionLeft
     * @param directionRight
     * @param classNameList
     * @param classNameStr
     * @param classNameListFrom
     * @param classNameStrFrom
     * @param classNameListTo
     * @param classNameStrTo
     * @param dbmsTableList
     * @param dbmsTableStr
     * @param alias
     * @param conditionStr
     * @param firstAttribute
     * @param lastAttribute
     * @param patternSQueryConditon
     */
    public OmcOQLPatternSQueryTable(String directionLeft, String directionRight, List<String> classNameList,
            String classNameStr, List<String> classNameListFrom, String classNameStrFrom, List<String> classNameListTo,
            String classNameStrTo, List<String> dbmsTableList, String dbmsTableStr, String alias, String conditionStr,
            String firstAttribute, String lastAttribute, OmcOQLPatternSQueryCondition patternSQueryConditon) {
        super();
        this.directionLeft = directionLeft;
        this.directionRight = directionRight;
        this.classNameList = classNameList;
        this.classNameStr = classNameStr;
        this.classNameListFrom = classNameListFrom;
        this.classNameStrFrom = classNameStrFrom;
        this.classNameListTo = classNameListTo;
        this.classNameStrTo = classNameStrTo;
        this.dbmsTableList = dbmsTableList;
        this.dbmsTableStr = dbmsTableStr;
        this.alias = alias;
        this.conditionStr = conditionStr;
        this.firstAttribute = firstAttribute;
        this.lastAttribute = lastAttribute;
        this.patternSQueryConditon = patternSQueryConditon;
    }


    public String getDirectionLeft() {
		return directionLeft;
	}

	public void setDirectionLeft(String directionLeft) {
		this.directionLeft = directionLeft;
	}

	public String getDirectionRight() {
		return directionRight;
	}

	public void setDirectionRight(String directionRight) {
		this.directionRight = directionRight;
	}

	public List<String> getClassNameList() {
		return classNameList;
	}

	public void setClassNameList(List<String> classNameList) {
		this.classNameList = classNameList;
	}

	public String getClassNameStr() {
		return classNameStr;
	}

	public void setClassNameStr(String classNameStr) {
		this.classNameStr = classNameStr;
	}

	public String getClassNameStrFrom() {
		return classNameStrFrom;
	}

	public void setClassNameStrFrom(String classNameStrFrom) {
		this.classNameStrFrom = classNameStrFrom;
	}

	public String getClassNameStrTo() {
		return classNameStrTo;
	}

	public void setClassNameStrTo(String classNameStrTo) {
		this.classNameStrTo = classNameStrTo;
	}

	public List<String> getDbmsTableList() {
		return dbmsTableList;
	}

	public void setDbmsTableList(List<String> dbmsTableList) {
		this.dbmsTableList = dbmsTableList;
	}

	public String getDbmsTableStr() {
		return dbmsTableStr;
	}

	public void setDbmsTableStr(String dbmsTableStr) {
		this.dbmsTableStr = dbmsTableStr;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getConditionStr() {
		return conditionStr;
	}

	public void setConditionStr(String conditionStr) {
		this.conditionStr = conditionStr;
	}

	public String getFirstAttribute() {
		return firstAttribute;
	}

	public void setFirstAttribute(String firstAttribute) {
		this.firstAttribute = firstAttribute;
	}

	public String getLastAttribute() {
		return lastAttribute;
	}

	public void setLastAttribute(String lastAttribute) {
		this.lastAttribute = lastAttribute;
	}

	public OmcOQLPatternSQueryCondition getPatternSQueryConditon() {
		return patternSQueryConditon;
	}

	public void setPatternSQueryConditon(
			OmcOQLPatternSQueryCondition patternSQueryConditon) {
		this.patternSQueryConditon = patternSQueryConditon;
	}


    @Override
    public String toString(){
        return "OmcOQLPatternSQueryTable [directionLeft=" + directionLeft + ", directionRight=" + directionRight
                + ", classNameList=" + classNameList + ", classNameStr=" + classNameStr + ", classNameListFrom="
                + classNameListFrom + ", classNameStrFrom=" + classNameStrFrom + ", classNameListTo=" + classNameListTo
                + ", classNameStrTo=" + classNameStrTo + ", dbmsTableList=" + dbmsTableList + ", dbmsTableStr="
                + dbmsTableStr + ", alias=" + alias + ", conditionStr=" + conditionStr + ", firstAttribute="
                + firstAttribute + ", lastAttribute=" + lastAttribute + ", patternSQueryConditon="
                + patternSQueryConditon + "]";
    }
	
}
