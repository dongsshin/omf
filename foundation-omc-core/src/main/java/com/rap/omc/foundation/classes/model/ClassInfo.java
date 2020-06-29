package com.rap.omc.foundation.classes.model;
/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : KeyInfo.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 18. hyeyoung.park Initial
 * ===========================================
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.rap.omc.util.NullUtil;
import com.rap.omc.api.oql.utility.OmcUtility;
import com.rap.omc.foundation.common.model.ModelRootVO;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcDBMSConstants;
import com.rap.omc.schema.util.OmcSystemConstants;

/**
 * <pre>
 * Class : KeyInfo
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class ClassInfo extends ModelRootVO{

    private long   flags;
    private long   classInfoFlags;
    private String className;
    private String classNameParent;
    private String javaPackage;
    private String javaPackageParent;
    private String dbmsTable;
    private List<ColumnInfo> columnList;
    private String displayedName;
    private String displayedNameKr;
    private String defaultPolicy;
    private String workflowUrl;
    private String classIcon;
    private String classIconSmall;
    
    private String classIconReal;
    private String classIconSmallReal;
    
    private String upperClassListStr;
    private String lowerClassListStr;
    private long cardinalityFrom;
    private long cardinalityTo;
    private long revisionRuleFrom;
    private long revisionRuleTo;
    private List<String> upperClassList;
    private List<String> lowerClassList;
    private List<ClassDbmsTableVO> instantiableClassList;
    private List<ClassNameForDisplayVO> childClassListForCombo;
    private String obid;
    private String classMenu;
    private String structureMenu;
    private List<String> allowedLifeCycleList;
    private List<AllowedClassInfo> allowedClassInfo;
    
    private List<AllowedClassInfo> relatedClassInfo;
    
    private Set<String> allowedFromClassSet;
    private Set<String> allowedToClassSet;
    
    
    
    public Set<String> getAllowedFromClassSet(){
        return allowedFromClassSet;
    }




    
    public void setAllowedFromClassSet(Set<String> allowedFromClassSet){
        this.allowedFromClassSet = allowedFromClassSet;
    }




    
    public Set<String> getAllowedToClassSet(){
        return allowedToClassSet;
    }




    
    public void setAllowedToClassSet(Set<String> allowedToClassSet){
        this.allowedToClassSet = allowedToClassSet;
    }




    public List<AllowedClassInfo> getRelatedClassInfo(){
        return relatedClassInfo;
    }



    
    public void setRelatedClassInfo(List<AllowedClassInfo> relatedClassInfo){
        this.relatedClassInfo = relatedClassInfo;
    }



    public List<String> getAllowedLifeCycleList(){
        return allowedLifeCycleList;
    }


    
    public void setAllowedLifeCycleList(List<String> allowedLifeCycleList){
        this.allowedLifeCycleList = allowedLifeCycleList;
    }


    public List<ClassNameForDisplayVO> getChildClassListForCombo(){
        return childClassListForCombo;
    }


    public void setChildClassListForCombo(List<ClassNameForDisplayVO> childClassListForCombo){
        this.childClassListForCombo = childClassListForCombo;
    }

    public List<ClassDbmsTableVO> getInstantiableClassList(){
        return instantiableClassList;
    }

    public void setInstantiableClassList(List<ClassDbmsTableVO> instantiableClassList){
        this.instantiableClassList = instantiableClassList;
    }



    public String getStructureMenu(){
        return structureMenu;
    }


    
    public void setStructureMenu(String structureMenu){
        this.structureMenu = structureMenu;
    }
    
    
    
    public String getClassIconReal(){
        return classIconReal;
    }

    
    public String getClassIconSmallReal(){
        return classIconSmallReal;
    }

    
    public void setClassIconReal(String classIconReal){
        this.classIconReal = classIconReal;
    }

    
    public void setClassIconSmallReal(String classIconSmallReal){
        this.classIconSmallReal = classIconSmallReal;
    }

    public String getClassMenu(){
        return classMenu;
    }
    
    public void setClassMenu(String classMenu){
        this.classMenu = classMenu;
    }
    public String getClassNameParent(){
        return classNameParent;
    }
    public String getJavaPackageParent(){
        return javaPackageParent;
    }
    public void setClassNameParent(String classNameParent){
        this.classNameParent = classNameParent;
    }
    public void setJavaPackageParent(String javaPackageParent){
        this.javaPackageParent = javaPackageParent;
    }
    public List<AllowedClassInfo> getAllowedClassInfo(){
        return allowedClassInfo;
    }

    
    public void setAllowedClassInfo(List<AllowedClassInfo> allowedClassInfo){
        this.allowedClassInfo = allowedClassInfo;
    }

    public String getObid(){
        return obid;
    }
    public void setObid(String obid){
        this.obid = obid;
    }




    public long getClassInfoFlags(){
        return classInfoFlags;
    }


    
    
    public String getUpperClassListStr(){
        return upperClassListStr;
    }



    
    public String getLowerClassListStr(){
        return lowerClassListStr;
    }



    
    public void setUpperClassListStr(String upperClassListStr){
        String[] strArray = upperClassListStr.split(Pattern.quote(","));
        List<String> strList = new ArrayList<String>();
        for(int i = 0; i < strArray.length; i++){
            strList.add(strArray[i]);
        }
        this.upperClassListStr = upperClassListStr;
        this.upperClassList = strList;
    }
    public void setLowerClassListStr(String lowerClassListStr){
        String[] strArray = lowerClassListStr.split(Pattern.quote(","));
        List<String> strList = new ArrayList<String>();
        for(int i = 0; i < strArray.length; i++){
            strList.add(strArray[i]);
        }
        this.lowerClassListStr = lowerClassListStr;
        this.lowerClassList = strList;
    }
    public void setClassInfoFlags(long classInfoFlags){
        this.classInfoFlags = classInfoFlags;
    }

    
    public List<String> getUpperClassList(){
        return upperClassList;
    }

    
    public List<String> getLowerClassList(){
        return lowerClassList;
    }

    
    public void setUpperClassList(List<String> upperClassList){
        this.upperClassList = upperClassList;
    }
    public void setUpperClassList(String upperClassList){
        String[] strArray = upperClassList.split(Pattern.quote(","));
        this.upperClassList = Arrays.asList(strArray);
    }
    public void setLowerClassList(List<String> lowerClassList){
        this.lowerClassList = lowerClassList;
    }
    public void setLowerClassList(String lowerClassList){
        String[] strArray = lowerClassList.split(Pattern.quote(","));
        this.lowerClassList = Arrays.asList(strArray);;
    }
    public long getFlags(){
        return flags;
    }
    
    public String getClassName(){
        return className;
    }
    
    public String getJavaPackage(){
        return javaPackage;
    }
    
    public String getDbmsTable(){
        return dbmsTable;
    }
    
    public String getDisplayedName(){
        return displayedName;
    }
    
    public String getDisplayedNameKr(){
        return displayedNameKr;
    }
    
    public String getDefaultPolicy(){
        return defaultPolicy;
    }
    
    public String getWorkflowUrl(){
        return workflowUrl;
    }
    
    public String getClassIcon(){
        return classIcon;
    }
    
    public String getClassIconSmall(){
        return classIconSmall;
    }
    
    public long getCardinalityFrom(){
        return cardinalityFrom;
    }
    
    public long getCardinalityTo(){
        return cardinalityTo;
    }
    
    public long getRevisionRuleFrom(){
        return revisionRuleFrom;
    }
    
    public long getRevisionRuleTo(){
        return revisionRuleTo;
    }
    
    public void setFlags(long flags){
        this.flags = flags;
    }
    
    public void setClassName(String className){
        this.className = className;
    }
    
    public void setJavaPackage(String javaPackage){
        this.javaPackage = javaPackage;
    }
    
    public void setDbmsTable(String dbmsTable){
        this.dbmsTable = dbmsTable;
    }
    
    public void setColumnList(List<ColumnInfo> columnList){
        this.columnList = columnList;
    }
    
    public void setDisplayedName(String displayedName){
        this.displayedName = displayedName;
    }
    
    public void setPdisplayedNameKr(String displayedNameKr){
        this.displayedNameKr = displayedNameKr;
    }
    
    public void setDefaultPolicy(String defaultPolicy){
        this.defaultPolicy = defaultPolicy;
    }
    
    public void setWorkflowUrl(String workflowUrl){
        this.workflowUrl = workflowUrl;
    }
    
    public void setClassIcon(String classIcon){
        this.classIcon = classIcon;
    }
    
    public void setClassIconSmall(String classIconSmall){
        this.classIconSmall = classIconSmall;
    }
    
    public void setCardinalityFrom(long cardinalityFrom){
        this.cardinalityFrom = cardinalityFrom;
    }
    
    public void setCardinalityTo(long cardinalityTo){
        this.cardinalityTo = cardinalityTo;
    }
    
    public void setRevisionRuleFrom(long revisionRuleFrom){
        this.revisionRuleFrom = revisionRuleFrom;
    }
    
    public void setRevisionRuleTo(long revisionRuleTo){
        this.revisionRuleTo = revisionRuleTo;
    }
    /**
     * 
     * 
     * @return the columList
     */
    public List<ColumnInfo> getColumnList(){
        return columnList;
    }
    /**
     * 
     *
     * @param columnList the columList to set
     */
    public void setColumList(List<ColumnInfo> columnList){
        this.columnList = columnList;
    }

    public String getColumnListString(){
        String strColumns = null;
        if (!NullUtil.isNone(columnList)) {
            StringBuffer sb = new StringBuffer();
            for (ColumnInfo column : columnList) {
                sb.append(column.getDbmsColumn()).append(",");
            }
            strColumns = sb.toString();
            strColumns = strColumns.substring(0, strColumns.length() - 1);
        }
        return strColumns;
    }
    public String getConvert2SelectString(){
        return getConvert2SelectString(true);
    }
    public String makeWhereString(Set<String> attributes){
        StringBuffer strBuf = new StringBuffer();
        for(String attribute : attributes){
            for (ColumnInfo column : columnList) {
                if(attribute.equals(column.getAttributeName())){
                    if(strBuf.length() > 0) strBuf.append(" and ");
                    strBuf.append(column.getDbmsColumn()).append( "=").append("#{item.").append(attribute).append("}");
                }
            }
        }
        return strBuf.toString();
    }
    public String getConvert2SelectString(boolean includeNameAttr){
        String strColumns = null;
        if (!NullUtil.isNone(columnList)) {
            StringBuffer sb = new StringBuffer();
            for (ColumnInfo column : columnList) {
                if (column.getDataType()== OmcSystemConstants.SCHEMA_DATA_TYPE_DATE) {
                    sb.append(OmcDBMSConstants.DBMS_UTC2LOCAL_FUNCTION).append("(").append(column.getDbmsColumn()).append(") as \"").append(column.getColumnAlias()).append("\",");
                } else {
                    sb.append(column.getDbmsColumn()).append(" as \"").append(column.getColumnAlias()).append("\",");
                }
                if(includeNameAttr){
                    if(Bit.isInclude(column.getFlags(),OmcSystemConstants.SYSCLASSATTR_FLAG_IsNameAttribute)){
                        String nameQuerystr = OmcUtility.getSelectStrForNameAttr(column.getValueSettingInfo(),column.getDbmsColumn(),BaseFoundationUtil.convert2CamelCase(column.getColumnAlias()));
                        if(!NullUtil.isNone(nameQuerystr)){
                            StringBuffer tSel = new StringBuffer();
                            tSel.append(nameQuerystr.replace("#{omcDbColumn}", "PTITLES").replace("#omcAttr", "Titles"));
                            tSel.append(",");
                            tSel.append(nameQuerystr.replace("#{omcDbColumn}", "OBID").replace("#omcAttr", "Obid"));
                            sb.append(tSel).append(",");
                        }
                    }                    
                }
                if(includeNameAttr){
                    if(column.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_USERID){
                        sb.append(OmcUtility.makeUserTitles(column.getDbmsColumn(),column.getColumnAlias(),true)).append(",");
                        sb.append(OmcUtility.makeUserObid(column.getDbmsColumn(),column.getColumnAlias(),true)).append(",");
                    }                    
                }
            }
            strColumns = sb.toString();
            strColumns = strColumns.substring(0, strColumns.length() - 1);
        }
        return strColumns;
    }
}