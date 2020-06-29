/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcOQLTableClassAttrDB.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 7. 29.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.cache.support.SimpleValueWrapper;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.oql.model.OmcOQLTableAndClass;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.model.schema.OmcOQLClassAttribute;
import com.rap.omc.api.oql.model.schema.OmcOQLClassRoot;
import com.rap.omc.api.oql.model.schema.OmcOQLRelatedClassInfo;
import com.rap.omc.api.oql.model.schema.OmcOQLRelationShipInfo;
import com.rap.omc.api.oql.model.schema.OmcOQLRoot;
import com.rap.omc.api.oql.utility.OmcSortUtil.OmcOQLComparator;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.CacheUtil;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.model.ColumnInfo;
import com.rap.omc.foundation.classes.service.ClassInfoService;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : OmcOQLTableClassAttrDB
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLCacheDBUtility {
    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;
    
    @Resource(name = "classInfoService")
    private ClassInfoService classInfoService;
    

    public static List<OmcOQLClassRoot> getChildClassList(String classNmae)
    {
        String[] classNmaeList = classNmae.split(",");
        return(getChildClassList(classNmaeList));
    }
    public static List<OmcOQLClassRoot> getChildClassList(List<String> classNmaeList)
    {
        String[] classNmaeAr = (String[])classNmaeList.toArray();
        return(getChildClassList(classNmaeAr));
    }
    public static List<OmcOQLClassRoot> getChildClassList(String[] classNmaeList)
    {
        List<OmcOQLRoot> wrkList = new ArrayList<OmcOQLRoot>();
        for(String str:classNmaeList)
        {
            List<OmcOQLClassRoot> tmpList  = getChildClassListSub(str);
            wrkList.addAll(tmpList);
        }
        if(wrkList != null && wrkList.size() > 1) 
        {
            wrkList = OmcSortUtil.uniquized(wrkList, "className", 1, false);
        }
        List<OmcOQLClassRoot> rtnList = new ArrayList<OmcOQLClassRoot>();
        for(OmcOQLRoot obj:wrkList) rtnList.add((OmcOQLClassRoot)obj);
        return(rtnList);
    }
    
    public static String getChildClassStr(String classNmae)
    {
        List<OmcOQLClassRoot> childClassList = getChildClassList(classNmae);
        return(convertClassList2Str(childClassList));
    }
    public static  String getChildClassStr(List<String> classNmaeList)
    {
        List<OmcOQLClassRoot> childClassList = getChildClassList(classNmaeList);
        return(convertClassList2Str(childClassList));
    }
    public static String getChildClassStr(String[] classNmaeList)
    {
        List<OmcOQLClassRoot> childClassList = getChildClassList(classNmaeList);
        return(convertClassList2Str(childClassList));
    }
    
    public static List<String> getChildClassStrList(String classNmae)
    {
        List<OmcOQLClassRoot> childClassList = getChildClassList(classNmae);
        return(convertClassList2StrList(childClassList));
    }
    public static  List<String> getChildClassStrList(List<String> classNmaeList)
    {
        List<OmcOQLClassRoot> childClassList = getChildClassList(classNmaeList);
        return(convertClassList2StrList(childClassList));
    }
    public static List<String> getChildClassStrList(String[] classNmaeList)
    {
        List<OmcOQLClassRoot> childClassList = getChildClassList(classNmaeList);
        return(convertClassList2StrList(childClassList));
    }
    
    private static String convertClassList2Str(List<OmcOQLClassRoot> childClassList)
    {
        StringBuffer stBuff = new StringBuffer();
        for(OmcOQLClassRoot getChildClassList : childClassList)
        {
            if(!StrUtil.isEmpty(stBuff)) stBuff.append(",");
            stBuff.append(getChildClassList.getClassName());
        }
        return(stBuff.toString());
    }
    private static List<String> convertClassList2StrList(List<OmcOQLClassRoot> childClassList)
    {
        List<String> strList = new ArrayList<String>();
        for(OmcOQLClassRoot getChildClassList : childClassList)
        {
            strList.add(getChildClassList.getClassName());
        }
        return(strList);
    }
    private static List<OmcOQLClassRoot> getChildClassListSub(String classNmae)
    {
        Object oCache = CacheUtil.getCache("omcOQLClassDB", classNmae);
        List<OmcOQLClassRoot> rtnList = new ArrayList<OmcOQLClassRoot>();
        if (oCache != null)
        {
            OmcOQLClassRoot  classRoot = (OmcOQLClassRoot)((SimpleValueWrapper)oCache).get();
            List<String> childList = classRoot.getChildClassList();
            for(String strClass : childList)
            {
                oCache = CacheUtil.getCache("omcOQLClassDB", strClass);
                if (oCache != null) rtnList.add((OmcOQLClassRoot)((SimpleValueWrapper)oCache).get());
            }
            return(rtnList);
        }
        return(null);
    }
    
    public static List<OmcOQLTableAndClass> getTableAndClassList(final String classNameList, final boolean isInstantialOnly,final int processLevel,final boolean testMode )
    {
        List<OmcOQLTableAndClass>  dataList       = new ArrayList<OmcOQLTableAndClass>();
        List<OmcOQLClassRoot>      childClassList = getChildClassList(classNameList);
        for(OmcOQLClassRoot oqlClass: childClassList)
        {
            dataList.add(new OmcOQLTableAndClass(oqlClass.getClassName(),oqlClass.getDbmsTable()));
        }
        return(dataList);
    }
    private static List<OmcOQLRelationShipInfo> getRelationShipInfoFromDB(String className, int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter(); 
        sqlStrBuff.append                                      ("select a.pflags           as flags          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       b.pclass_name      as relation_class ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       c.pclass_name      as related_class   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysallowedclassforrel a, psysrelobjectclassinfo b, psysclassinfo c");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.prelationship_obid = b.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pclass_obid        = c.pclass_obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   c.pclass_name        = '").append(className).append("'");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcOQLRelationShipInfo>  relationShipInfoList   = OmcOQLServiceUtil.getRelationShipInfoList(variableParameter);
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(relationShipInfoList);
    }
    private static List<OmcOQLRelatedClassInfo> getRelatedClassInfoFromDB(String className, int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);

        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter(); 
        sqlStrBuff.append                                      ("select a.pflags           as flags          ,");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("       c.pclass_name      as related_class   ");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysallowedclassforrel a, psysrelobjectclassinfo b, psysclassinfo c");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.prelationship_obid = b.obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.pclass_obid        = c.pclass_obid");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("and   b.pclass_name        = '").append(className).append("'");

        variableParameter.setSql(sqlStrBuff.toString());
        List<OmcOQLRelatedClassInfo>  relatedInfoList   = OmcOQLServiceUtil.getRelatedInfoList(variableParameter);
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(relatedInfoList);
    }
    
    public static void refreshAPIGetRelatedLogDB(int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        StringBuffer parmClassList = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter(); 
        parmClassList.append("select distinct '[' || pclass_name || ']' || '[' || pdbms_table || ']' as classTableStr");
        parmClassList.append(OmcFoundationConstant.newline).append("from psysclassinfo x");
        parmClassList.append(OmcFoundationConstant.newline).append("where bit.band(pflags,").append(OmcSystemConstants.CLASSINFO_FLAG_Instantiable).append(")").append("=").append(OmcSystemConstants.CLASSINFO_FLAG_Instantiable);
        variableParameter.setSql(parmClassList.toString());
        List<String>  classList   = OmcOQLServiceUtil.getChildClassList(variableParameter);
        StringBuffer key     = new StringBuffer();
        StringBuffer savedKey     = new StringBuffer();
        StringBuffer attrKey = new StringBuffer();
        for(String classTableStr : classList)
        {
            String[] splited = classTableStr.split(Pattern.quote("]["));
            String className = StrUtil.replace(StrUtil.replace(splited[0],"]",""),"[","").trim();
            String tableName = StrUtil.replace(StrUtil.replace(splited[1],"]",""),"[","").trim();

            key.setLength(0);savedKey.setLength(0);
            List<OmcOQLClassAttribute>  dataList = getAttributeListFromDb(className,tableName,"*",processLevel+1,testMode);
            savedKey.append(classTableStr);
            key.append(savedKey).append("[").append("*").append("]");
            OmcComUtility.logWrite((new StringBuffer("key     :")).append(key.toString()).toString(),processLevel,testMode);
            
            if(dataList.size() > 0)
            {
                CacheUtil.putCache("omcOQLTableClassAttrDB", key.toString(), dataList);
                String dkdkkd = (new StringBuffer()).append("[").append(className).append("]").append("[").append("*").append("]").append("[").append("*").append("]").toString();
                CacheUtil.putCache("omcOQLTableClassAttrDB", dkdkkd, dataList);
                for(OmcOQLClassAttribute classAttr : dataList)
                {
                    attrKey.setLength(0);
                    attrKey.append(savedKey).append("[").append(classAttr.getAttributeName()).append("]");
                    //OmcComUtility.logWrite((new StringBuffer("attrKey     :")).append(attrKey.toString()).toString(),processLevel,testMode);
                    CacheUtil.putCache("omcOQLTableClassAttrDB", attrKey.toString(), classAttr);
                    CacheUtil.putCache("omcOQLTableClassAttrDB", (new StringBuffer()).append("[").append("*").append("]").append("[").append("*").append("]").append("[").append(classAttr.getAttributeName()).append("]").toString(), classAttr);
                }
            }
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
    }
    
    public static void refreshAPIFindAndSearchLogDB(int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        StringBuffer parmClassList = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter(); 
        parmClassList.append("select distinct '[' || pclass_name || ']' || '[' || pdbms_table || ']' as classTableStr");
        parmClassList.append(OmcFoundationConstant.newline).append("from psysclassinfo x");
        parmClassList.append(OmcFoundationConstant.newline).append("where bit.band(pflags,").append(OmcSystemConstants.CLASSINFO_FLAG_Instantiable).append(")").append("=").append(OmcSystemConstants.CLASSINFO_FLAG_Instantiable);
        variableParameter.setSql(parmClassList.toString());
        List<String>  classList   = OmcOQLServiceUtil.getChildClassList(variableParameter);
        StringBuffer key     = new StringBuffer();
        StringBuffer savedKey     = new StringBuffer();
        StringBuffer attrKey = new StringBuffer();
        for(String classTableStr : classList)
        {
            String[] splited = classTableStr.split(Pattern.quote("]["));
            String className = StrUtil.replace(StrUtil.replace(splited[0],"]",""),"[","").trim();
            String tableName = StrUtil.replace(StrUtil.replace(splited[1],"]",""),"[","").trim();

            key.setLength(0);savedKey.setLength(0);
            List<OmcOQLClassAttribute>  dataList = getAttributeListFromDb(className,tableName,"*",processLevel+1,testMode);
            savedKey.append(classTableStr);
            key.append(savedKey).append("[").append("*").append("]");
            OmcComUtility.logWrite((new StringBuffer("key     :")).append(key.toString()).toString(),processLevel,testMode);
            
            if(dataList.size() > 0)
            {
                CacheUtil.putCache("omcOQLTableClassAttrDB", key.toString(), dataList);
                String dkdkkd = (new StringBuffer()).append("[").append(className).append("]").append("[").append("*").append("]").append("[").append("*").append("]").toString();
                CacheUtil.putCache("omcOQLTableClassAttrDB", dkdkkd, dataList);
                for(OmcOQLClassAttribute classAttr : dataList)
                {
                    attrKey.setLength(0);
                    attrKey.append(savedKey).append("[").append(classAttr.getAttributeName()).append("]");
                    //OmcComUtility.logWrite((new StringBuffer("attrKey     :")).append(attrKey.toString()).toString(),processLevel,testMode);
                    CacheUtil.putCache("omcOQLTableClassAttrDB", attrKey.toString(), classAttr);
                    CacheUtil.putCache("omcOQLTableClassAttrDB", (new StringBuffer()).append("[").append("*").append("]").append("[").append("*").append("]").append("[").append(classAttr.getAttributeName()).append("]").toString(), classAttr);
                }
            }
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
    }
    public static List<OmcOQLClassAttribute> getAttributeList(String tableStrIn, String classStrIn, String attribute,boolean isSortOnly, String uniqueMethod,int processLevel,boolean testMode)
    {
        List<String> tableList = new ArrayList<String>();
        if(!StrUtil.isEmpty(tableStrIn) && !tableStrIn.equals("*"))
        {
            String[] tableArray = tableStrIn.split(",");
            for(String str: tableArray) if(!StrUtil.isEmpty(str) && !str.trim().equals("")) tableList.add(str.trim());
        }
        List<String> classList = new ArrayList<String>();
        if(!StrUtil.isEmpty(classStrIn) && !classStrIn.equals("*"))
        {
            String[] classArray = classStrIn.split(",");
            for(String str: classArray) if(!StrUtil.isEmpty(str) && !str.trim().equals("")) classList.add(str.trim());            
        }

        if(StrUtil.isEmpty(attribute) || attribute.trim().equals("*") || attribute.trim().equals("")) attribute = "*";
        
        String[] attrArray = attribute.split(",");
        List<String> attrList = new ArrayList<String>();
        for(String str: attrArray) if(!StrUtil.isEmpty(str) && !str.trim().equals("")) attrList.add(str.trim());
        
        return(getAttributeList(tableList,classList,attrList,isSortOnly,uniqueMethod,processLevel+1,testMode));
    }
    
    public static List<OmcOQLClassAttribute> getAttributeList(String tableStrIn, List<String> classList, String attribute,boolean isSortOnly, String uniqueMethod,int processLevel,boolean testMode)
    {
        List<String> tableList = new ArrayList<String>();
        if(!StrUtil.isEmpty(tableStrIn) && !tableStrIn.equals("*"))
        {
            String[] tableArray = tableStrIn.split(",");
            for(String str: tableArray) if(!StrUtil.isEmpty(str) && !str.trim().equals("")) tableList.add(str.trim());
        }
        if(StrUtil.isEmpty(attribute) || attribute.trim().equals("*") || attribute.trim().equals("")) attribute = "*";
        
        String[] attrArray = attribute.split(",");
        List<String> attrList = new ArrayList<String>();
        for(String str: attrArray) if(!StrUtil.isEmpty(str) && !str.trim().equals("")) attrList.add(str.trim());
        
        return(getAttributeList(tableList,classList,attrList,isSortOnly,uniqueMethod,processLevel+1,testMode));
    }
    
    public static List<OmcOQLClassAttribute> getAttributeList(String classStrIn,boolean isSortOnly, String uniqueMethod,int processLevel,boolean testMode)
    {
        return(getAttributeList(null,classStrIn,null,isSortOnly,uniqueMethod,processLevel+1,testMode));
    }
    public static List<OmcOQLClassAttribute> getAttributeListWithAttr(String attribute,boolean isSortOnly, String uniqueMethod,int processLevel,boolean testMode)
    {
        return(getAttributeList(null,(String)null,attribute,isSortOnly,uniqueMethod,processLevel+1,testMode));
    }
    public static List<OmcOQLClassAttribute> getAttributeList(String classStrIn, String attribute,String uniqueMethod,int processLevel,boolean testMode)
    {
        return(getAttributeList(null,classStrIn,attribute,false,uniqueMethod,processLevel+1,testMode));
    }
    public static List<OmcOQLClassAttribute> getAttributeList(List<String> tableList, List<String> classList, List<String> attributeList,boolean isSortOnly, String uniqueMethod,int processLevel,boolean testMode)
    {
        return(_getAttributeList(tableList,classList,attributeList,isSortOnly,uniqueMethod,processLevel+1,testMode));
    }
    
    private static List<OmcOQLClassAttribute> _getAttributeList(List<String> tableList, List<String> classList, List<String> attributeList,boolean isSortOnly, String uniqueMethod, int processLevel,boolean testMode)
    {
        boolean isAttrAll = false;
        boolean isTableAll = false;
        if(StrUtil.in("*", attributeList)) isAttrAll = true;
        if(NullUtil.isNone(tableList)) isTableAll = true;
        String classListStr = StrUtil.convertList2Str(classList);
        List<ClassInfo> classInfoList = ClassInfoUtil.getClassInfoList(classListStr);
        List<OmcOQLClassAttribute> rtnAttributeList = new ArrayList<OmcOQLClassAttribute>();
        for(ClassInfo classInfo : classInfoList){
            List<ColumnInfo> columnList = classInfo.getColumnList();
            if(isTableAll){
                for(ColumnInfo colInfo : columnList){
                    if(isAttrAll){
                        rtnAttributeList.add(new OmcOQLClassAttribute(classInfo.getClassName(), classInfo.getDbmsTable(),colInfo.getAttributeName(),colInfo.getDbmsColumn(),colInfo.getColumnAlias(),colInfo.getDataType(),colInfo.getFlags(),colInfo.getValueSettingInfo()));
                    }else
                    {
                        //if(StrUtil.in("*", attributeList)){
                            rtnAttributeList.add(new OmcOQLClassAttribute(classInfo.getClassName(), classInfo.getDbmsTable(),colInfo.getAttributeName(),colInfo.getDbmsColumn(),colInfo.getColumnAlias(),colInfo.getDataType(),colInfo.getFlags(),colInfo.getValueSettingInfo()));
                        //}
                    }
                }
            }else{
                if(StrUtil.in(classInfo.getDbmsTable(), tableList)){
                    for(ColumnInfo colInfo : columnList){
                        if(isAttrAll){
                            rtnAttributeList.add(new OmcOQLClassAttribute(classInfo.getClassName(), classInfo.getDbmsTable(),colInfo.getAttributeName(),colInfo.getDbmsColumn(),colInfo.getColumnAlias(),colInfo.getDataType(),colInfo.getFlags(),colInfo.getValueSettingInfo()));
                        }else
                        {
                            //if(StrUtil.in("*", attributeList)){
                                rtnAttributeList.add(new OmcOQLClassAttribute(classInfo.getClassName(), classInfo.getDbmsTable(),colInfo.getAttributeName(),colInfo.getDbmsColumn(),colInfo.getColumnAlias(),colInfo.getDataType(),colInfo.getFlags(),colInfo.getValueSettingInfo()));
                            //}
                        }
                    }
                }
            }
        }
        if(isSortOnly || !StrUtil.isEmpty(uniqueMethod)) return(_uniquized(rtnAttributeList,isSortOnly,uniqueMethod,processLevel+1,testMode));
        return (rtnAttributeList);
    }
    private static List<OmcOQLClassAttribute> _uniquized(List<OmcOQLClassAttribute> attributeList,boolean isSortOnly, String patternUniqueAttrStr, int processLevel,boolean testMode){
        List<OmcOQLClassAttribute> rtnattributeList = new ArrayList<OmcOQLClassAttribute>();
        
        String[] splited   = patternUniqueAttrStr.split(Pattern.quote(":"));
        int splitedCnt = splited.length;
        String[] valueSave = new String[splitedCnt];
        for(int i = 0; i < splitedCnt; i++) valueSave[i] = "...";
        //OmcComUtility.logWrite("----------------------------------------------------------0001", 1, testMode);
        ArrayList<OmcOQLComparator> comparatorList = new ArrayList<OmcOQLComparator>();
        for(String str : splited)
        {
            comparatorList.add(new OmcOQLComparator(str,false));
        }
        //OmcComUtility.logWrite("----------------------------------------------------------0002", 1, testMode);
        OmcSortUtil.sort(attributeList, comparatorList);
        if(isSortOnly) return(attributeList);
        //OmcComUtility.logWrite("----------------------------------------------------------0003", 1, testMode);
        StringBuffer attrVaue = new StringBuffer();
        for(OmcOQLClassAttribute classAttribute : attributeList)
        {
            boolean isSame = true;
            for(int i = 0; i < splitedCnt; i++)
            {
                attrVaue.setLength(0);
                //String dddd = (String)classAttribute.getAttribute(splited[i]);
                attrVaue.append((String)classAttribute.getAttribute(splited[i]));
                if(!attrVaue.toString().equals(valueSave[i]))  isSame = false;
                valueSave[i] = attrVaue.toString();
            }
            if(!isSame) rtnattributeList.add(classAttribute);
        }
        OmcComUtility.logWrite("----------------------------------------------------------0004", 1, testMode);
        return(rtnattributeList);
    }
    private static List<OmcOQLClassAttribute> getAttributeListFromDb(String className, String tableName, String attribute, int processLevel,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        StringBuffer parmClassList = new StringBuffer();
        Integer classCnt = 1;
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter(); 
        parmClassList.append("select a.pclass_name class_name,a.pdbms_table dbms_table ,a.pattribute_name attribute_name,a.pdbms_column dbms_column_name ,a.pcolumn_alias dbms_column_alias_name ,a.pdata_type data_type");
        parmClassList.append(OmcFoundationConstant.newline).append("from psysattrref a");
        
        StringBuffer strTempList  = new StringBuffer();
        StringBuffer attributeName = new StringBuffer();

        parmClassList.append(OmcFoundationConstant.newline).append("where a.pdata_type in(1,2,3,4,5,9)");
        if(!StrUtil.isEmpty(className) && !className.equals("*"))
        {
            attributeName.setLength(0);strTempList.setLength(0);
            attributeName.append("funVariable_");
            attributeName.append(StrUtil.LPAD(classCnt.toString(), 5, "0"));
            strTempList.append("#{").append(attributeName.toString()).append("}");
            variableParameter.setAttribute(attributeName.toString(), className);
            parmClassList.append(OmcFoundationConstant.newline).append("and   a.pclass_name in(").append(strTempList.toString()).append(")");
            classCnt++;
        }
        if(!StrUtil.isEmpty(tableName) && !tableName.equals("*"))
        {
            attributeName.setLength(0);strTempList.setLength(0);
            attributeName.append("funVariable_");
            attributeName.append(StrUtil.LPAD(classCnt.toString(), 5, "0"));
            strTempList.append("#{").append(attributeName.toString()).append("}");
            variableParameter.setAttribute(attributeName.toString(), tableName);
            parmClassList.append(OmcFoundationConstant.newline).append("and   a.pdbms_table in(").append(strTempList.toString()).append(")");
            classCnt++;
        }
        if(!StrUtil.isEmpty(attribute) && !attribute.equals("*"))
        {
            attributeName.setLength(0);strTempList.setLength(0);
            attributeName.append("funVariable_");
            attributeName.append(StrUtil.LPAD(classCnt.toString(), 5, "0"));
            strTempList.append("#{").append(attributeName.toString()).append("}");
            variableParameter.setAttribute(attributeName.toString(), attribute);
            parmClassList.append(OmcFoundationConstant.newline).append("and   a.pattribute_name in(").append(strTempList.toString()).append(")");
            classCnt++;
        }
        variableParameter.setSql(parmClassList.toString());
        List<OmcOQLClassAttribute>  dataList   = OmcOQLServiceUtil.getClassAttributeList(variableParameter);
        OmcComUtility.logWriteEnd(processLevel, testMode);
        OmcComUtility.logWrite((new StringBuffer("dataList Size           :")).append(dataList.size()).toString(),processLevel,testMode);
        return(dataList);

    }
}
