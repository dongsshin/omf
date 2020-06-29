/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcOQLPatternSelection.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 7. 25.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.rap.omc.util.NullUtil;
import com.rap.omc.api.oql.model.schema.OmcOQLClassAttribute;
import com.rap.omc.api.oql.model.schema.OmcOQLRoot;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.oql.utility.OmcPatternUtil;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.api.oql.utility.OmcUtility;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcDBMSConstants;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcOQLPatternSelection
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLPatternSelection extends OmcOQLRoot{
    private String inputSelectPattern;
    private String outputAlias;
    private String outputSelectPattern;
    private String dbmsColumnName;
    private int dataType;
    private boolean isUniqueNeed;
    private String outputSelectLast;
    private String outputSelectNameAttrAdded;
    
    
    public String getOutputSelectNameAttrAdded(){
        return outputSelectNameAttrAdded;
    }
    
    public void setOutputSelectNameAttrAdded(String outputSelectNameAttrAdded){
        this.outputSelectNameAttrAdded = outputSelectNameAttrAdded;
    }
    private ArrayList<OmcOQLPatternAlias> patternAttributeList;
    private HashMap<String,OmcOQLPatternAlias> distinctAliasAttrMap;
    public OmcOQLPatternSelection() {
        super();
    }
    /**
     * @param inputSelectPattern
     * @param outputAlias
     * @param outputSelectPattern
     * @param dbmsColumnName
     * @param dataType
     * @param isUniqueNeed
     * @param outputSelectLast
     * @param patternAttributeList
     */
    public OmcOQLPatternSelection(String inputSelectPattern, String outputAlias, String outputSelectPattern,
            String dbmsColumnName, int dataType, boolean isUniqueNeed, String outputSelectLast,
            ArrayList<OmcOQLPatternAlias> patternAttributeList) {
        super();
        this.inputSelectPattern = inputSelectPattern;
        this.outputAlias = outputAlias;
        this.outputSelectPattern = outputSelectPattern;
        this.dbmsColumnName = dbmsColumnName;
        this.dataType = dataType;
        this.isUniqueNeed = isUniqueNeed;
        this.outputSelectLast = outputSelectLast;
        this.patternAttributeList = patternAttributeList;
        this.setDistinctAliasAttrMap();
    }
        
    public HashMap<String, OmcOQLPatternAlias> getDistinctAliasAttrMap(){
        return distinctAliasAttrMap;
    }
    public void setDistinctAliasAttrMap(){
        if(distinctAliasAttrMap != null && !distinctAliasAttrMap.isEmpty()) this.distinctAliasAttrMap.clear();
        if(distinctAliasAttrMap == null) this.distinctAliasAttrMap = new HashMap<String,OmcOQLPatternAlias>();
        StringBuffer key = new StringBuffer();
        for (OmcOQLPatternAlias patternAlias : this.patternAttributeList)
        {
            key.setLength(0);
            key.append(patternAlias.getClassAlias()).append(patternAlias.getClassAttribute());
            this.distinctAliasAttrMap.put(key.toString(),new OmcOQLPatternAlias(patternAlias.getClassAlias(),patternAlias.getClassAttribute(),-1));
        }
    }
    public String getInputSelectPattern(){
        return inputSelectPattern;
    }
    

    public String getOutputAlias(){
        return outputAlias;
    }
    
    public String getOutputSelectPattern(){
        return outputSelectPattern;
    }
    
    public String getDbmsColumnName(){
        return dbmsColumnName;
    }
    
    public int getDataType(){
        return dataType;
    }
    
    public boolean isUniqueNeed(){
        return isUniqueNeed;
    }
    
    public String getOutputSelectLast(){
        return outputSelectLast;
    }
    
    public ArrayList<OmcOQLPatternAlias> getPatternAttributeList(){
        return patternAttributeList;
    }
    
    public void setInputSelectPattern(String inputSelectPattern){
        this.inputSelectPattern = inputSelectPattern;
    }
    
    public void setOutputAlias(String outputAlias){
        this.outputAlias = outputAlias;
    }
    
    public void setOutputSelectPattern(String outputSelectPattern){
        this.outputSelectPattern = outputSelectPattern;
    }
    
    public void setDbmsColumnName(String dbmsColumnName){
        this.dbmsColumnName = dbmsColumnName;
    }
    
    public void setDataType(int dataType){
        this.dataType = dataType;
    }
    
    public void setUniqueNeed(boolean isUniqueNeed){
        this.isUniqueNeed = isUniqueNeed;
    }
    
    public void setOutputSelectLast(String outputSelectLast){
        this.outputSelectLast = outputSelectLast;
    }
    
    public void setPatternAttributeList(ArrayList<OmcOQLPatternAlias> patternAttributeList){
        this.patternAttributeList = patternAttributeList;
    }
    public void setOutputPattern(ArrayList<OmcOQLPatternClass> patternCalssList,int processLevel,boolean testMode)
    {
        /*
        ArrayList<OmcOQLComparator> comparatorList = new ArrayList<OmcOQLComparator>();
        comparatorList.add(new OmcOQLComparator("classAlias",false));
        comparatorList.add(new OmcOQLComparator("aliasLenth",true));
        OmcSortUtil.sort(this.patternAttributeList, comparatorList);
        */
        String tempInputSelectPattern = this.getInputSelectPattern();
        OmcOQLConvertedParmValue convertedParmValue = OmcUtility.setGetConvertedParmValue(new OmcOQLConvertedParmValue(tempInputSelectPattern,tempInputSelectPattern,"specSelect","%","'","'",0,new HashMap<String,String>(),"All"),tempInputSelectPattern,"specSelect",processLevel+1,testMode);
        tempInputSelectPattern = convertedParmValue.getStrConverted();
        if(tempInputSelectPattern.indexOf(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator) == -1)
        {
            setOutPatternForSingle(tempInputSelectPattern,convertedParmValue,processLevel+1,testMode);
        }
        else
        {
            StringBuffer uniqueOutputSelect = new StringBuffer();
            if (patternCalssList != null)
            {
                for(OmcOQLPatternClass patternClass : patternCalssList){
                    for(String key : this.distinctAliasAttrMap.keySet()){
                        OmcOQLPatternAlias patternAlias = this.distinctAliasAttrMap.get(key);
                        String str1 = patternAlias.getClassAlias();
                        String str2 = patternClass.getAlias();
                        if(str1.indexOf(str2) != -1){
                            String dbmsColumn = null;
                            String outputSelectionPattern = null;
                            StringBuffer dbmsSel = new StringBuffer();
                            String selectPatternEach = OmcPatternUtil.makeSelectPattern(patternAlias.getClassAlias(), patternAlias.getClassAttribute());
                            //String selectPatternEach = patternAlias.getClassAlias() + "." + OmcFoundationConstant.OQL_SELECT_ATTR_LEFT + patternAlias.getClassAttribute() + OmcFoundationConstant.OQL_SELECT_ATTR_RIGHT;
                            String classAlias = StrUtil.replace(patternAlias.getClassAlias(), OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator, "").trim();
                            dbmsColumn        = OmcUtility.getDbmsColumnName(patternClass.getClassNameList(), patternClass.getDbmsTableList(), patternAlias.getClassAttribute(), patternAlias.getClassAlias(), processLevel, testMode);
                            if(patternAlias.getClassAttribute().equals(OmcFoundationConstant.OQL_ATTRIBUTE_All)){
                                if(!StrUtil.isEmpty(dbmsColumn)){
                                    String[] strArray = dbmsColumn.split(Pattern.quote(OmcFoundationConstant.OQL_FISRT_LAST_SQL_SEPERATOR));
                                    this.setOutputSelectPattern(strArray[0]);
                                    this.setOutputSelectLast(strArray[1]);
                                }
                            }
                            else{
                                if(!StrUtil.isEmpty(dbmsColumn)){
                                    String[] strArray = dbmsColumn.split(Pattern.quote(OmcFoundationConstant.OQL_DBMS_COLUMN_INFO_SEPERATOR));
                                    if(strArray.length != 4){
                                        throw new FoundationException("[Foundation]Critical Error find Attribute for {0}",key);
                                    }
                                    dbmsColumn       = strArray[0];
                                    Integer dataType = Integer.parseInt(strArray[1]);
                                    long flags       = Long.parseLong(strArray[2]);
                                    String valueInfo = strArray[3];
                                    
                                    String userDefined = null;
                                    if(this.isUniqueNeed()){
                                        outputSelectionPattern = this.getInputSelectPattern();
                                        if(StrUtil.inStr(outputSelectionPattern, selectPatternEach) > 0){
                                            if(this.getOutputAlias().equals(".")) this.setOutputAlias(getSelectAlias(outputSelectionPattern,classAlias,patternAlias.getClassAttribute()));
                                            userDefined = OmcUtility.getUserDefinedAlias(outputSelectionPattern);
                                            if(!StrUtil.isEmpty(userDefined)) outputSelectionPattern = StrUtil.replace(outputSelectionPattern, " "+userDefined,"");
                                            String uniqueAlias = StrUtil.subStr(patternAlias.getClassAttribute(), 1,15) + "_" + StrUtil.getRandomString(4);
                                            if(!StrUtil.isEmpty(uniqueOutputSelect)) uniqueOutputSelect.append(",");
                                            uniqueOutputSelect.append(classAlias).append(".").append(dbmsColumn).append(" as \"").append(uniqueAlias).append("\"");
                                            outputSelectionPattern = StrUtil.replace(outputSelectionPattern, selectPatternEach, "\"" + uniqueAlias + "\"");
                                            this.setInputSelectPattern(outputSelectionPattern);
                                            this.setOutputSelectPattern(uniqueOutputSelect.toString());
                                            this.setOutputSelectLast(outputSelectionPattern);
                                        }
                                    }
                                    else{
                                        if(StrUtil.isEmpty(outputSelectionPattern)) outputSelectionPattern = this.getOutputSelectPattern();
                                        if(StrUtil.inStr(outputSelectionPattern, selectPatternEach) > 0 && this.getOutputAlias().equals(".")){
                                            this.setDbmsColumnName(classAlias + "." + dbmsColumn);
                                            this.setDataType(dataType);
                                            this.setOutputSelectPattern(classAlias + "." + dbmsColumn);
                                            this.setOutputAlias(getSelectAlias(outputSelectionPattern,classAlias,patternAlias.getClassAttribute()));
                                            userDefined = OmcUtility.getUserDefinedAlias(outputSelectionPattern);
                                            if(!StrUtil.isEmpty(userDefined)) outputSelectionPattern = StrUtil.replace(outputSelectionPattern, " "+userDefined,"");
                                            dbmsSel.setLength(0);
                                            if(dataType == OmcSystemConstants.SCHEMA_DATA_TYPE_USERID){
                                                StringBuffer tSel = new StringBuffer();
                                                tSel.append(OmcUtility.makeUserTitles(this.getOutputAlias(),this.getOutputAlias(),true));
                                                tSel.append(",");
                                                tSel.append(OmcUtility.makeUserObid(this.getOutputAlias(),this.getOutputAlias(),true));
                                                this.outputSelectNameAttrAdded = tSel.toString();
                                            }else if(Bit.isInclude(flags,OmcSystemConstants.SYSCLASSATTR_FLAG_IsNameAttribute)){
                                                String nameQuerystr = OmcUtility.getSelectStrForNameAttr(valueInfo,this.getOutputAlias(),this.getOutputAlias());
                                                if(!NullUtil.isNone(nameQuerystr)){
                                                    StringBuffer tSel = new StringBuffer();
                                                    tSel.append(nameQuerystr.replace("#{omcDbColumn}", "PTITLES").replace("#omcAttr", "Titles"));
                                                    tSel.append(",");
                                                    tSel.append(nameQuerystr.replace("#{omcDbColumn}", "OBID").replace("#omcAttr", "Obid"));
                                                    this.outputSelectNameAttrAdded = tSel.toString();
                                                }
                                            }
                                            switch(dataType){
                                                case OmcSystemConstants.SCHEMA_DATA_TYPE_DATE:
                                                    dbmsSel.append(OmcDBMSConstants.DBMS_LOCAL2UTC_FUNCTION_CHAR).append("(\"").append(this.getOutputAlias()).append("\",'").append(OmcSystemConstants.OMC_DBMS_DATE_FORMAT).append("')");
                                                    break;
                                                case OmcSystemConstants.SCHEMA_DATA_TYPE_BOOLEAN:
                                                    dbmsSel.append("case when \"").append(this.getOutputAlias()).append("\" = 1 then 'TRUE' else 'FALSE' end");
                                                    break;
                                                default:
                                                    dbmsSel.append("\"").append(this.getOutputAlias()).append("\"");
                                            }
                                            outputSelectionPattern = StrUtil.replace(outputSelectionPattern,selectPatternEach,dbmsSel.toString());
                                            this.setOutputSelectLast(outputSelectionPattern);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void setOutputPatternForSQuery(ArrayList<OmcOQLPatternClass> patternCalssList,int processLevel,boolean testMode)
    {
        String tempInputSelectPattern = this.getInputSelectPattern();
        OmcOQLConvertedParmValue convertedParmValue = OmcUtility.setGetConvertedParmValue(new OmcOQLConvertedParmValue(tempInputSelectPattern,tempInputSelectPattern,"specSelect","%","'","'",0,new HashMap<String,String>(),"All"),tempInputSelectPattern,"specSelect",processLevel+1,testMode);
        tempInputSelectPattern = convertedParmValue.getStrConverted();
        if(tempInputSelectPattern.indexOf(OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator) == -1)
        {
            setOutPatternForSingle(tempInputSelectPattern,convertedParmValue,processLevel+1,testMode);
        }
        else
        {
            StringBuffer uniqueOutputSelect = new StringBuffer();
            for(OmcOQLPatternClass patternClass : patternCalssList)
            {
                for(String key : this.distinctAliasAttrMap.keySet())
                {
                    OmcOQLPatternAlias patternAlias = this.distinctAliasAttrMap.get(key);
                    if(patternAlias.getClassAlias().indexOf(patternClass.getAlias()) !=1)
                    {
                        String dbmsColumn = null;
                        String outputSelectionPattern = null;
                        StringBuffer dbmsSel = new StringBuffer();
                        //String selectPatternEach = patternAlias.getClassAlias() + "." + "[" + patternAlias.getClassAttribute() + "]";
                        String selectPatternEach = OmcPatternUtil.makeSelectPattern(patternAlias.getClassAlias(), patternAlias.getClassAttribute());
                        String classAlias = StrUtil.replace(patternAlias.getClassAlias(), OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator, "");
                        dbmsColumn        = OmcUtility.getDbmsColumnName(patternClass.getClassNameList(), patternClass.getDbmsTableList(), patternAlias.getClassAttribute(), patternAlias.getClassAlias(), processLevel, testMode);
                        if(!StrUtil.isEmpty(dbmsColumn))
                        {
                            String[] strArray = dbmsColumn.split(Pattern.quote(OmcFoundationConstant.OQL_DBMS_COLUMN_INFO_SEPERATOR));
                            Integer dataType = Integer.parseInt(strArray[1]);
                            dbmsColumn       = strArray[0];
                            String userDefined = null;
                            if(StrUtil.isEmpty(outputSelectionPattern)) outputSelectionPattern = this.getOutputSelectPattern();
                            if(StrUtil.inStr(outputSelectionPattern, selectPatternEach) > 0 && this.getOutputAlias().equals("."))
                            {
                                this.setDbmsColumnName(classAlias + "." + dbmsColumn);
                                this.setDataType(dataType);
                                this.setOutputSelectPattern(classAlias + "." + dbmsColumn);
                                this.setOutputAlias(getSelectAlias(outputSelectionPattern,classAlias,patternAlias.getClassAttribute()));
                                userDefined = OmcUtility.getUserDefinedAlias(outputSelectionPattern);
                                if(!StrUtil.isEmpty(userDefined)) outputSelectionPattern = StrUtil.replace(outputSelectionPattern, " "+userDefined,"");
                                dbmsSel.setLength(0);
                                switch(dataType)
                                {
                                    case 4:
                                        dbmsSel.append(OmcDBMSConstants.DBMS_UTC2LOCAL_FUNCTION_CHAR).append("\"").append(this.getOutputAlias()).append("\",'").append(OmcSystemConstants.OMC_DBMS_DATE_FORMAT).append("')");
                                        break;
                                    case 5:
                                        dbmsSel.append("decode(\"").append(this.getOutputAlias()).append("\",1,'TRUE','FALSE')");
                                        break;
                                    default:
                                        dbmsSel.append("\"").append(this.getOutputAlias()).append("\"");
                                }
                                outputSelectionPattern = StrUtil.replace(outputSelectionPattern,selectPatternEach,dbmsSel.toString());
                                this.setOutputSelectLast(outputSelectionPattern);
                            }
                        }
                    }
                }
            }
        }
    }
    public void setOutputSortBy(ArrayList<OmcOQLPatternClass> patternCalssList,int processLevel,boolean testMode)
    {
        StringBuffer tmp1 = new StringBuffer();
        StringBuffer tmp2 = new StringBuffer();
        for(OmcOQLPatternClass patternClass : patternCalssList)
        {
            for(String key : this.distinctAliasAttrMap.keySet())
            {
                OmcOQLPatternAlias patternAlias = this.distinctAliasAttrMap.get(key);
                if(patternAlias.getClassAlias().indexOf(patternClass.getAlias()) != -1)
                {
                    String dbmsColumnAlias = null;
                    String classAlias = StrUtil.replace(patternAlias.getClassAlias(), OmcFoundationConstant.OQL_ALIAS2ATTR_Seperator, "").trim();
                    List<OmcOQLClassAttribute> classAttrList = OmcUtility.getClassAttributeType(patternClass.getClassNameList(), patternClass.getDbmsTableList(), patternAlias.getClassAttribute(), patternAlias.getClassAlias(), processLevel+1, testMode);
                    if(classAttrList.size() > 0)
                    {
                        if(classAlias.equals(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this))
                        {
                            dbmsColumnAlias        = classAttrList.get(0).getDbmsColumnAliasName().trim();
                            if(!StrUtil.isEmpty(dbmsColumnAlias))
                            {
                                tmp1.setLength(0);
                                tmp1.append("\"").append(dbmsColumnAlias).append("\"");
                                dbmsColumnAlias = tmp1.toString();
                            }
                            
                            tmp1.setLength(0);
                            tmp1.append(patternAlias.getClassAlias()).append(".").append("[").append(patternAlias.getClassAttribute()).append("]");
                            this.setOutputSelectPattern(StrUtil.replace(this.getOutputSelectPattern(), tmp1.toString(),dbmsColumnAlias));
                            tmp1.setLength(0);
                            tmp1.append(classAlias.toLowerCase()).append(OmcFoundationConstant.OQL_SELPATTERN_Seperator).append(patternAlias.getClassAttribute());
                            this.setOutputAlias(tmp1.toString());
                        }
                        else
                        {
                            tmp1.setLength(0);
                            tmp1.append("\"").append(classAlias.toLowerCase()).append(OmcFoundationConstant.OQL_SELPATTERN_Seperator).append(patternAlias.getClassAttribute()).append("\"");
                            tmp2.setLength(0);
                            tmp2.append(patternAlias.getClassAlias()).append(".").append("[").append(patternAlias.getClassAttribute()).append("]");
                            this.setOutputSelectPattern(StrUtil.replace(this.getOutputSelectPattern(), tmp2.toString(), tmp1.toString()));
                        }
                    }
                }
            }
        }
    }
    private void setOutPatternForSingle(String tempInputSelectPattern,OmcOQLConvertedParmValue convertedParmValue,int processLevel,boolean testMode)
    {
        if(tempInputSelectPattern.indexOf(" ") >= 0)
        {
            String userDefinedAlias = OmcUtility.getUserDefinedAlias(inputSelectPattern);
            if(!StrUtil.isEmpty(userDefinedAlias))
            {
                int pos = StrUtil.inStr(tempInputSelectPattern, " ",-1);
                String selectValue = tempInputSelectPattern.substring(0, tempInputSelectPattern.indexOf(" ")).trim();
                selectValue = OmcUtility.revokeConvertedParmValue(selectValue,convertedParmValue,processLevel+1,testMode);
                this.setOutputSelectPattern(selectValue);
                this.setOutputAlias(userDefinedAlias);
            }
            if(StrUtil.isEmpty(this.getOutputAlias())) this.setOutputAlias(StrUtil.getRandomString(10));
            this.setOutputSelectLast(new StringBuffer("\"").append(this.getOutputAlias()).append("\"").toString());
        }
    }
    private String getSelectAlias(String strIn, String alias, String attribute)
    {
        String userDefined = OmcUtility.getUserDefinedAlias(strIn);
        if(!StrUtil.isEmpty(userDefined)) return(userDefined);
        if(StrUtil.inStr(strIn, "FN_GET_CODEDETAIL") > 0)
        {
            return((new StringBuffer()).append(alias.toLowerCase()).append(OmcFoundationConstant.OQL_SELPATTERN_Seperator).append(attribute.trim()).append("Desc").toString());
        }
        return((new StringBuffer()).append(alias.toLowerCase()).append(OmcFoundationConstant.OQL_SELPATTERN_Seperator).append(attribute.trim()).toString());
    }
}
