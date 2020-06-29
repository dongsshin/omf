package com.rap.omc.api.oql.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.rap.omc.api.oql.model.schema.OmcOQLClassAttribute;
import com.rap.omc.api.oql.model.schema.OmcOQLRoot;
import com.rap.omc.api.oql.utility.OmcComUtility;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.oql.utility.OmcSortUtil;
import com.rap.omc.api.oql.utility.OmcSortUtil.OmcOQLComparator;
import com.rap.omc.api.oql.utility.OmcUtility;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.foundation.classes.model.ClassDbmsTableVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcDBMSConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.util.NullUtil;

/**
 * 
 * <pre>
 * Class : OmcOQLSelectForQuery
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLSelectForQuery extends OmcOQLRoot{
	private List<String>                classList            ;
	private List<String>                dbmsTableList        ;
	private List<ClassDbmsTableVO>      dbmsTableAndClassList;
	private List<OmcOQLClassAttribute>  classAttributeList   ;
	private List<OmcOQLAttribute>       attributeList        ;
	private HashMap<String,Integer>     classCntForTable     ;
	private String                      alias                ;
	public OmcOQLSelectForQuery()
	{
		super();
	}
    public OmcOQLSelectForQuery(
            List<String>               classList            ,
            List<String>               dbmsTableList        ,
            List<ClassDbmsTableVO>     dbmsTableAndClassList,
            List<OmcOQLClassAttribute> classAttributeList   ,
            List<OmcOQLAttribute>      attributeList        , 
            String                     alias) {
        super();
        this.classList = classList;
        this.dbmsTableList = dbmsTableList;
        this.dbmsTableAndClassList = dbmsTableAndClassList;
        this.classAttributeList = classAttributeList;
        setClassCntForTable();
        this.attributeList = attributeList;
        this.alias = alias;
        //Attribute 순서로 Sort
        ArrayList<OmcOQLComparator> comparator = new ArrayList<OmcOQLComparator>();
        comparator.add(new OmcOQLComparator("attributeName",false));
        OmcSortUtil.sort(this.attributeList, comparator);   
        
      //Table 순서로 Sort
        comparator = new ArrayList<OmcOQLComparator>();
        comparator.add(new OmcOQLComparator("dbmsTable",false));
        comparator.add(new OmcOQLComparator("attributeName",false));
        OmcSortUtil.sort(this.classAttributeList, comparator); 
    }
    
    public Map<String,String> getAliasAttrColumn(){
        Map<String,String> map = new HashMap<String,String>();
        if(NullUtil.isNone(this.classAttributeList)) return map;
        for(OmcOQLClassAttribute attr : this.classAttributeList){
            map.put(this.alias + ":" + attr.getAttributeName(), attr.getDbmsColumnName());
        }
        return map;
    }
    public Map<String,String> getColumnAliasForSelectPattern(){
        Map<String,String> map = new HashMap<String,String>();
        if(NullUtil.isNone(this.classAttributeList)) return map;
        for(OmcOQLClassAttribute attr : this.classAttributeList){
            map.put("@" + this.alias + "." + "[" + attr.getAttributeName() + "]", attr.getDbmsColumnAliasName());
        }
        return map;
    }
	public List<String> getClassList() {
		return classList;
	}

	public void setClassList(List<String> classList) {
		this.classList = classList;
	}

	public List<String> getDbmsTableList() {
		return dbmsTableList;
	}

	public void setDbmsTableList(List<String> dbmsTableList) {
		this.dbmsTableList = dbmsTableList;
	}

	public List<ClassDbmsTableVO> getDbmsTableAndClassList() {
		return dbmsTableAndClassList;
	}

	public void setDbmsTableAndClassList(
	        List<ClassDbmsTableVO> dbmsTableAndClassList) {
		this.dbmsTableAndClassList = dbmsTableAndClassList;
	}

	public List<OmcOQLClassAttribute> getClassAttributeList() {
		return classAttributeList;
	}

	public void setClassAttributeList(List<OmcOQLClassAttribute> classAttributeList) {
		this.classAttributeList = classAttributeList;
		setClassCntForTable();
	}
    private void setClassCntForTable()
    {   
        this.classCntForTable = new HashMap<String,Integer>();
        StringBuffer keyValue = new StringBuffer();
        for(OmcOQLClassAttribute attrList:this.classAttributeList)
        {
            keyValue.append(attrList.getDbmsTable()).append(".").append(attrList.getAttributeName()).append(".").append(attrList.getDbmsColumnName());
            this.classCntForTable.put(keyValue.toString(), 0);
            keyValue.setLength(0);
        }
        int cnt = 0;
        for(String key : this.classCntForTable.keySet())
        {
            cnt = 0;
            String[] splitedKey = key.split(Pattern.quote("."));
            for(OmcOQLClassAttribute attrList:this.classAttributeList)
            {
                if(splitedKey[2].equals(attrList.getDbmsColumnName()) && splitedKey[1].equals(attrList.getAttributeName()) && splitedKey[0].equals(attrList.getDbmsTable()) ) cnt++;
            }
            this.classCntForTable.put(key, cnt);
        }
    }
    public HashMap<String,Integer> getClassCntForTable()
    {   
        return(this.classCntForTable);
    }
	public List<OmcOQLAttribute> getAttributeList() {
		return attributeList;
	}

	public void setAttributeList(List<OmcOQLAttribute> attributeList) {
		this.attributeList = attributeList;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	//-------------------------------------------------------------------------------------------------------------------
	public String getClassListStrForTable(String dbmsTable, int processLevel ,boolean testMode)
	{
		OmcComUtility.logWriteStart( processLevel, testMode);
		StringBuffer r = new StringBuffer();
		for(ClassDbmsTableVO tblAndClass : this.dbmsTableAndClassList)
		{
			if (tblAndClass.getDbmsTable().equals(dbmsTable))
			{
				if (!StrUtil.isEmpty(r)) r.append(",");
				r.append("'").append(tblAndClass.getClassName()).append("'");
			}
		}
		OmcComUtility.logWrite((new StringBuffer("r      :")).append(r).toString(),processLevel,testMode);
		OmcComUtility.logWriteEnd(processLevel, testMode);
		return(r.toString());
	}
	//-------------------------------------------------------------------------------------------------------------------
	public String getSQLSelectStrAll(String dbmsTable, int classCount, String aliasType, int processLevel ,boolean testMode)
	{
	    //OmcComUtility.logWrite("----------------------------------------------------------getSQLSelectStrAll-0000", 1, true);
		//OmcComUtility.logWriteStart( processLevel, testMode);
		boolean isExist = false;
		
		Integer cnt  = 0;
		String dbmsColumn = null;
		String attributeName = null;
		String columnAlias   = null;
		String sqlAlias      = null; 
		StringBuffer rtnSql = new StringBuffer();
		StringBuffer eachSql = new StringBuffer();
		boolean isDBMSColumn = false;
		if (aliasType.equals("DBMS Column")) isDBMSColumn = true;
		
		//OmcComUtility.logWrite("----------------------------------------------------------getSQLSelectStrAll-0000", 1, testMode);
		List<OmcOQLClassAttribute> classAtrList = OmcUtility.getAllClassAttributeList(this.getClassList(), dbmsTable, "*", processLevel+1, testMode);
        //OmcComUtility.logWrite("----------------------------------------------------------getSQLSelectStrAll-0001", 1, testMode);
		OmcSortUtil.sort(classAtrList, "dbmsColumnName:className:attributeName",  processLevel+1, testMode);
        //OmcComUtility.logWrite("----------------------------------------------------------getSQLSelectStrAll-0002", 1, testMode);
        //OmcComUtility.logWrite("----------------------------------------------------------getSQLSelectStrAll-0003", 1, testMode);
        //OmcComUtility.logWrite("----------------------------------------------------------getSQLSelectStrAll-0003", 1, testMode);
		
        for (OmcOQLAttribute attr : this.attributeList){
		    dbmsColumn = "";cnt  = 0;isExist = false;
		    eachSql.setLength(0);
		    for(String key : this.classCntForTable.keySet())
		    {
		        if (key.indexOf(dbmsTable + "." + attr.getAttributeName() + ".") != -1)
		        {
		            dbmsColumn = key.substring(key.lastIndexOf(".")+1);
		            cnt        = this.classCntForTable.get(key);
		            isExist    = true;
		            break;
		        }
		    }
		    attributeName = attr.getAttributeName();
		    columnAlias   = attr.getDbmsColumnAliasName();
		    if (isDBMSColumn)
		    {
		        sqlAlias = columnAlias; 
		    }
		    else 
		    {
		        sqlAlias = (new StringBuffer()).append(this.getAlias().toLowerCase()).append("_").append(attributeName).toString();
		    }
		    if (isExist)
		    {
		        if(classCount==cnt) 
		        {
		            eachSql.append(this.alias).append(".").append(dbmsColumn.toLowerCase()).append(" as \"").append(sqlAlias).append("\"");
		        }
		        else
		        {
		            String temp = getSQLSelectStr(classAtrList,dbmsTable,attributeName,sqlAlias,attr.getDataType(),processLevel+1,testMode);
		            eachSql.append(temp);
		        }
		    }
		    else
		    {
		        eachSql.append("null").append(" as \"").append(sqlAlias).append("\"");
		    }
		    if(StrUtil.isEmpty(eachSql))
		    {
		        OmcComUtility.logWrite((new StringBuffer("attr      :")).append(attr).toString(),processLevel,testMode);
		    }
		    OmcComUtility.logWrite((new StringBuffer("eachSql                     :")).append(eachSql.toString()).toString(),processLevel,testMode);
		    if(!StrUtil.isEmpty(rtnSql)) rtnSql.append(",").append(OmcFoundationConstant.newline);
		    rtnSql.append(eachSql);
		}
		//OmcComUtility.logWrite("----------------------------------------------------------getSQLSelectStrAll-0003", 1, testMode);
		OmcComUtility.logWriteEnd(processLevel, testMode);
		OmcComUtility.logWrite((new StringBuffer("rtnSql      :")).append(rtnSql).toString(),processLevel,testMode);
		return(rtnSql.toString());
	}
	//-------------------------------------------------------------------------------------------------------------------
	public String getSQLSelectLastAll(int processLevel ,boolean testMode)
	{
		OmcComUtility.logWriteStart( processLevel, testMode);
		StringBuffer r = new StringBuffer();

		for(OmcOQLAttribute attr : this.attributeList){
            if(!StrUtil.isEmpty(r)) r.append(",");
            OmcComUtility.logWrite((new StringBuffer("attr.toString      :")).append(attr.toString()).toString(),processLevel,testMode);
            
            String eachAlias = this.getAlias().equals(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this) ? attr.getDbmsColumnAliasName() : (new StringBuffer(this.getAlias().toLowerCase())).append("_").append(attr.getAttributeName()).toString();
            String eachSelectStr = covertResultFormat(attr.getDataType(),
                                                      this.getAlias()   ,
                                                      eachAlias         ,
                                                      processLevel+1    ,
                                                      testMode          );

		    r.append(eachSelectStr + " as \"" + eachAlias + "\"");
            if(attr.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_USERID){
                if(!StrUtil.isEmpty(r)) r.append(",");
                r.append(OmcUtility.makeUserTitles(eachAlias,eachAlias,true));
                r.append(",");
                r.append(OmcUtility.makeUserObid(eachAlias,eachAlias,true));
            }
            if(Bit.isInclude(attr.getFlags(),OmcSystemConstants.SYSCLASSATTR_FLAG_IsNameAttribute)){
                String attribute = BaseFoundationUtil.convert2CamelCase(eachAlias.toString());
                String nameQuerystr = OmcUtility.getSelectStrForNameAttr(attr.getValueSettingInfo(),eachAlias.toString(),attribute);
                if(!NullUtil.isNone(nameQuerystr)){
                    if(!StrUtil.isEmpty(r)) r.append(",");
                    r.append(nameQuerystr.replace("#{omcDbColumn}", "PTITLES").replace("#omcAttr", "Titles"));
                    if(!StrUtil.isEmpty(r)) r.append(",");
                    r.append(nameQuerystr.replace("#{omcDbColumn}", "OBID").replace("#omcAttr", "Obid"));
                }
             }
		}
		OmcComUtility.logWrite((new StringBuffer("r      :")).append(r).toString(),processLevel,testMode);
		OmcComUtility.logWriteEnd(processLevel, testMode);
		return(r.toString());
	}
	private String getSQLSelectStr(List<OmcOQLClassAttribute> classAtrList, String dbmsTable, String attributeName, String dbmsColumnAliasName ,int dataType ,int processLevel ,boolean testMode)
	{
		OmcComUtility.logWriteStart(processLevel, testMode);
		StringBuffer rtnSql = new StringBuffer();
		String  dbmsColumnSave = "";
		boolean isFirst = true;
		StringBuffer classList = new StringBuffer();
		//이미 Sort되어져 있음.
		//OmcSortUtil.sort(classAtrList, "dbmsColumnName", processLevel+1, testMode);
		for (OmcOQLClassAttribute clsAttr: classAtrList)
		{
		    if(clsAttr.getAttributeName().equals(attributeName))
		    {
		        if (dbmsColumnSave.equals(clsAttr.getDbmsColumnName()))
		        {
		            classList.append(",").append("'").append(clsAttr.getClassName()).append("'");
		        }
		        else
		        {
		            if(!isFirst)
		            {
		                rtnSql.append("case when ").append(this.alias).append(".pclass_name in(").append(classList.toString()).append(") then ").append(this.alias).append(".").append(dbmsColumnSave.toLowerCase());//.append(OmcFoundationConstant.newline);
		                rtnSql.append(" else null end as \"").append(dbmsColumnAliasName).append("\"");
		            }
		            classList.setLength(0);
		            classList.append("'").append(clsAttr.getClassName()).append("'");
		        }
		        dbmsColumnSave = clsAttr.getDbmsColumnName();
		        isFirst = false;
		    }
		}
		if(!StrUtil.isEmpty(dbmsColumnSave))
		{
		    rtnSql.append("case when ").append(this.alias).append(".pclass_name in(").append(classList.toString()).append(") then ").append(this.alias).append(".").append(dbmsColumnSave.toLowerCase());//.append(OmcFoundationConstant.newline);
		    rtnSql.append(" else null end as \"").append(dbmsColumnAliasName).append("\"");
		}else{
		    rtnSql.append(" null as \"").append(dbmsColumnAliasName).append("\"");
		}
		OmcComUtility.logWrite((new StringBuffer("rtnSql      :")).append(rtnSql).toString(),processLevel,testMode);
		OmcComUtility.logWriteEnd(processLevel, testMode);
		OmcComUtility.logWriteEnd(processLevel, testMode);
		return(rtnSql.toString());
	}
    private static String covertResultFormat(int dataType, String alias, String selectAlias, int processLevel ,boolean testMode)
    {
        OmcComUtility.logWriteStart(processLevel, testMode);
        StringBuffer r = new StringBuffer();
        switch(dataType){
            case OmcSystemConstants.SCHEMA_DATA_TYPE_STRING:
                r.append("\"").append(selectAlias).append("\"");
                break;
            case OmcSystemConstants.SCHEMA_DATA_TYPE_INTEGER:
                r.append("\"").append(selectAlias).append("\"");
                break;
            case OmcSystemConstants.SCHEMA_DATA_TYPE_FLOAT:
                r.append("\"").append(selectAlias).append("\"");
                break;
            case OmcSystemConstants.SCHEMA_DATA_TYPE_DATE:
                if(alias.equals(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this)){
                    r.append(OmcDBMSConstants.DBMS_UTC2LOCAL_FUNCTION).append("(\"").append(selectAlias).append("\")");
                }else{
                    r.append(OmcDBMSConstants.DBMS_UTC2LOCAL_FUNCTION_CHAR).append("(\"").append(selectAlias).append("\",'").append(OmcSystemConstants.OMC_DBMS_DATE_FORMAT).append("')"); 
                }
                break;
            case OmcSystemConstants.SCHEMA_DATA_TYPE_BOOLEAN:
                if(alias.equals(OmcFoundationConstant.OQL_TARGET_VO_ALIAS_this)){
                    r.append("\"").append(selectAlias).append("\"");
                }else{
                    r.append("case when ").append(selectAlias).append(" = 1 then 'TRUE' else 'FALSE' end"); 
                }
                break;
            case OmcSystemConstants.SCHEMA_DATA_TYPE_LONG:
                r.append("\"").append(selectAlias).append("\"");
                break;    
            case OmcSystemConstants.SCHEMA_DATA_TYPE_DOUBLE:
                r.append("\"").append(selectAlias).append("\"");
                break;    
            case OmcSystemConstants.SCHEMA_DATA_TYPE_BIGDECIMAL:
                r.append("\"").append(selectAlias).append("\"");
                break;
            case OmcSystemConstants.SCHEMA_DATA_TYPE_ATTRIBUTESET:
                r.append("\"").append(selectAlias).append("\"");
                break;
            case OmcSystemConstants.SCHEMA_DATA_TYPE_LONGSTRING:
                r.append("\"").append(selectAlias).append("\"");
                break;
            case OmcSystemConstants.SCHEMA_DATA_TYPE_ARRAY:
                r.append("\"").append(selectAlias).append("\"");
                break;
            case OmcSystemConstants.SCHEMA_DATA_TYPE_FILE:
                r.append("\"").append(selectAlias).append("\"");
                break;
            case OmcSystemConstants.SCHEMA_DATA_TYPE_USERID:
                r.append("\"").append(selectAlias).append("\"");
                break;
            default:
                OmcComUtility.error("Not Defined Data Type");
        }
        //OmcComUtility.logWrite((new StringBuffer("r      :")).append(r).toString(),processLevel,testMode);
        OmcComUtility.logWriteEnd(processLevel, testMode);
        return(r.toString());
    }
}
