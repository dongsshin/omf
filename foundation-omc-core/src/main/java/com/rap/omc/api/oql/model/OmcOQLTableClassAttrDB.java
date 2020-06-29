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
package com.rap.omc.api.oql.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.cache.support.SimpleValueWrapper;

import com.rap.omc.api.oql.model.schema.OmcOQLClassAttribute;
import com.rap.omc.api.oql.utility.OmcComUtility;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.oql.utility.OmcOQLServiceUtil;
import com.rap.omc.api.oql.utility.OmcSortUtil;
import com.rap.omc.api.oql.utility.OmcSortUtil.OmcOQLComparator;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.CacheUtil;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcOQLTableClassAttrDB
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLTableClassAttrDB {
    
    public static void test(int processLevel,boolean testMode)
    {
        testMode = true;

        StringBuffer parmClassList = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter(); 
        parmClassList.append("select distinct '[' || pclass_name || ']' || '[' || pdbms_table || ']' as classTableStr");
        parmClassList.append(OmcFoundationConstant.newline).append("from psysclassinfo x");
        parmClassList.append(OmcFoundationConstant.newline).append("where bit.band(pflags,").append(OmcSystemConstants.CLASSINFO_FLAG_Instantiable).append(")").append("=").append(OmcSystemConstants.CLASSINFO_FLAG_Instantiable);
        variableParameter.setSql(parmClassList.toString());
        List<String>  classList   = OmcOQLServiceUtil.getChildClassList(variableParameter);
        StringBuffer key     = new StringBuffer();
        OmcComUtility.logWriteStart(processLevel, testMode);
        for(String classTableStr : classList)
        {
            key.setLength(0);
            key.append(classTableStr).append("[").append("*").append("]");
            
            //OmcComUtility.logWrite((new StringBuffer("key     :")).append(key.toString()).toString(),processLevel,testMode);
            
            Object oCache = CacheUtil.getCache("omcOQLTableClassAttrDB", key.toString());
            if (oCache != null)
            {
                //OmcComUtility.logWrite((new StringBuffer("key(Defined):")).append(key.toString()).toString(),processLevel,testMode);
                List<OmcOQLClassAttribute>  attributeList = (List<OmcOQLClassAttribute>)((SimpleValueWrapper)oCache).get();
//                for(OmcOQLClassAttribute attribute: attributeList)
//                {
//                    //OmcComUtility.logWrite((new StringBuffer("attribute                     :")).append(attribute.toString()).toString(),processLevel,testMode);
//                }
            }
            else
            {
                //OmcComUtility.logWrite((new StringBuffer("key(Not Defined):")).append(key.toString()).toString(),processLevel,testMode);
            }
        }
        OmcComUtility.logWriteEnd(processLevel, testMode);
    }
    
    public static List<OmcOQLClassAttribute> getAttributeList(String tableStrIn, String classStrIn, String attribute,String uniqueMethod,int processLevel,boolean testMode)
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
        
        return(getAttributeList(tableList,classList,attrList,uniqueMethod,processLevel+1,testMode));
    }
    
    public static List<OmcOQLClassAttribute> getAttributeList(String tableStrIn, List<String> classList, String attribute,String uniqueMethod,int processLevel,boolean testMode)
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
        
        return(getAttributeList(tableList,classList,attrList,uniqueMethod,processLevel+1,testMode));
    }
    
    public static List<OmcOQLClassAttribute> getAttributeList(String classStrIn,String uniqueMethod,int processLevel,boolean testMode)
    {
        return(getAttributeList(null,classStrIn,null,uniqueMethod,processLevel+1,testMode));
    }
    public static List<OmcOQLClassAttribute> getAttributeListWithAttr(String attribute,String uniqueMethod,int processLevel,boolean testMode)
    {
        return(getAttributeList(null,(String)null,attribute,uniqueMethod,processLevel+1,testMode));
    }
    public static List<OmcOQLClassAttribute> getAttributeList(String classStrIn, String attribute,String uniqueMethod,int processLevel,boolean testMode)
    {
        return(getAttributeList(null,classStrIn,attribute,uniqueMethod,processLevel+1,testMode));
    }
    public static List<OmcOQLClassAttribute> getAttributeList(List<String> tableList, List<String> classList, List<String> attributeList,String uniqueMethod,int processLevel,boolean testMode)
    {
        String  dkdkkd = null;
        return(_getAttributeList(tableList,classList,attributeList,uniqueMethod,processLevel+1,testMode));
    }
    
    private static List<OmcOQLClassAttribute> _getAttributeList(List<String> tableList, List<String> classList, List<String> attributeList,String uniqueMethod, int processLevel,boolean testMode)
    {
        List<OmcOQLClassAttribute> rtnAttributeList = new ArrayList<OmcOQLClassAttribute>();
        StringBuffer key = new StringBuffer();
        
        if(tableList != null && !tableList.isEmpty())
        {
            if (classList != null && !classList.isEmpty())
            {
                for(String tableStr : tableList)
                {
                    for(String classStr : classList)
                    {
                        for(String attrStr : attributeList)
                        {
                            key.setLength(0);
                            if(StrUtil.isEmpty(attrStr) || attrStr.equals("*"))
                            {
                                key.append("[").append(classStr).append("]").append("[").append(tableStr).append("]").append("[*]");
                                Object oCache = CacheUtil.getCache("omcOQLTableClassAttrDB", key.toString());
                                if(oCache != null) 
                                {
                                    @SuppressWarnings("unchecked")
                                    List<OmcOQLClassAttribute> attrList = (List<OmcOQLClassAttribute>)((SimpleValueWrapper)oCache).get();
                                    rtnAttributeList.addAll(attrList);
                                }                    
                            }
                            else
                            {
                                key.append("[").append(classStr).append("]").append("[").append(tableStr).append("]").append("[").append(attrStr).append("]");
                                Object oCache = CacheUtil.getCache("omcOQLTableClassAttrDB", key.toString());
                                if(oCache != null) 
                                {
                                    OmcOQLClassAttribute attr = (OmcOQLClassAttribute)((SimpleValueWrapper)oCache).get();
                                    rtnAttributeList.add(attr);
                                }     
                            }
                        }
                    }
                }
            }
            else
            {
                for(String attrStr : attributeList)
                {
                    key.setLength(0);
                    if(StrUtil.isEmpty(attrStr) || attrStr.equals("*"))
                    {
                        key.append("[").append("*").append("]").append("[").append("*").append("]").append("[*]");
                        Object oCache = CacheUtil.getCache("omcOQLTableClassAttrDB", key.toString());
                        if(oCache != null) 
                        {
                            @SuppressWarnings("unchecked")
                            List<OmcOQLClassAttribute> attrList = (List<OmcOQLClassAttribute>)((SimpleValueWrapper)oCache).get();
                            rtnAttributeList.addAll(attrList);
                        }                    
                    }
                    else
                    {
                        key.append("[").append("*").append("]").append("[").append("*").append("]").append("[").append(attrStr).append("]");
                        Object oCache = CacheUtil.getCache("omcOQLTableClassAttrDB", key.toString());
                        if(oCache != null) 
                        {
                            OmcOQLClassAttribute attr = (OmcOQLClassAttribute)((SimpleValueWrapper)oCache).get();
                            rtnAttributeList.add(attr);
                        }     
                    }
                }
            }
        }
        else
        {
            if (classList != null && !classList.isEmpty())
            {
                for(String classStr : classList)
                {
                    for(String attrStr : attributeList)
                    {
                        key.setLength(0);
                        if(StrUtil.isEmpty(attrStr) || attrStr.equals("*"))
                        {
                            key.append("[").append(classStr).append("]").append("[").append("*").append("]").append("[*]");
                            Object oCache = CacheUtil.getCache("omcOQLTableClassAttrDB", key.toString());
                            if(oCache != null) 
                            {
                                @SuppressWarnings("unchecked")
                                List<OmcOQLClassAttribute> attrList = (List<OmcOQLClassAttribute>)((SimpleValueWrapper)oCache).get();
                                rtnAttributeList.addAll(attrList);
                            }                    
                        }
                        else
                        {
                            key.append("[").append(classStr).append("]").append("[").append("*").append("]").append("[").append(attrStr).append("]");
                            Object oCache = CacheUtil.getCache("omcOQLTableClassAttrDB", key.toString());
                            if(oCache != null) 
                            {
                                OmcOQLClassAttribute attr = (OmcOQLClassAttribute)((SimpleValueWrapper)oCache).get();
                                rtnAttributeList.add(attr);
                            }     
                        }
                    }
                }
            }
            else
            {
                for(String attrStr : attributeList)
                {
                    key.setLength(0);
                    if(StrUtil.isEmpty(attrStr) || attrStr.equals("*"))
                    {
                        key.append("[").append("*").append("]").append("[").append("*").append("]").append("[*]");
                        Object oCache = CacheUtil.getCache("omcOQLTableClassAttrDB", key.toString());
                        if(oCache != null) 
                        {
                            @SuppressWarnings("unchecked")
                            List<OmcOQLClassAttribute> attrList = (List<OmcOQLClassAttribute>)((SimpleValueWrapper)oCache).get();
                            rtnAttributeList.addAll(attrList);
                        }                    
                    }
                    else
                    {
                        key.append("[").append("*").append("]").append("[").append("*").append("]").append("[").append(attrStr).append("]");
                        Object oCache = CacheUtil.getCache("omcOQLTableClassAttrDB", key.toString());
                        if(oCache != null) 
                        {
                            OmcOQLClassAttribute attr = (OmcOQLClassAttribute)((SimpleValueWrapper)oCache).get();
                            rtnAttributeList.add(attr);
                        }     
                    }
                } 
            }
        }
        if(!StrUtil.isEmpty(uniqueMethod)) return(_uniquized(rtnAttributeList,uniqueMethod,processLevel+1,testMode));
        return (rtnAttributeList);
    }
    private static List<OmcOQLClassAttribute> _uniquized(List<OmcOQLClassAttribute> attributeList,String patternUniqueAttrStr, int processLevel,boolean testMode){
        List<OmcOQLClassAttribute> rtnattributeList = new ArrayList<OmcOQLClassAttribute>();
        
        String[] splited   = patternUniqueAttrStr.split(Pattern.quote(":"));
        int splitedCnt = splited.length;
        String[] valueSave = new String[splitedCnt];
        for(int i = 0; i < splitedCnt; i++) valueSave[i] = "...";

        ArrayList<OmcOQLComparator> comparatorList = new ArrayList<OmcOQLComparator>();
        for(String str : splited)
        {
            comparatorList.add(new OmcOQLComparator(str,false));
        }
        OmcSortUtil.sort(attributeList, comparatorList);
        StringBuffer attrVaue = new StringBuffer();
        for(OmcOQLClassAttribute classAttribute : attributeList)
        {
            boolean isSame = true;
            for(int i = 0; i < splitedCnt; i++)
            {
                attrVaue.setLength(0);
                String dddd = (String)classAttribute.getAttribute(splited[i]);
                attrVaue.append(dddd);
                if(!attrVaue.toString().equals(valueSave[i]))  isSame = false;
                valueSave[i] = attrVaue.toString();
            }
            if(!isSame) rtnattributeList.add(classAttribute);
        }
        return(rtnattributeList);
    }
    public static void refreshCashAll(int processLevel,boolean testMode)
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
            if(className.equals("ECO"))
            {
                OmcComUtility.logWrite((new StringBuffer("className     :")).append(className.toString()).toString(),processLevel,testMode);
            }
            
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
                if(dkdkkd.toString().equals("[*][ECO][*]"))
                {
                    OmcComUtility.logWrite((new StringBuffer("dkdkkd     :")).append(dkdkkd.toString()).toString(),processLevel,testMode);
                }
                
                OmcComUtility.logWrite((new StringBuffer("dkdkkd     :")).append(dkdkkd.toString()).toString(),processLevel,testMode);
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
