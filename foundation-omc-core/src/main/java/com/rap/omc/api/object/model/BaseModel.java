/**
 * ===========================================
 * System Name : LGE GPDM
 * Program ID : BaseModel.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 9. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.object.model;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.framework.exception.FoundationException;


/**
 * <pre>
 * Class : BaseModel
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class BaseModel extends PagingEntity implements Serializable{
    private static final long serialVersionUID = 1171201651043484533L;

    private String dbmsTable;
    private String sql;
    private String columns;
    private String recordMode;
    
    
    private String uniqueString;
    private String uniqueStringParent;
    private String dataFindingDirection;
    private int    explodedDepth = 0;
    
    public String getRecordMode() {
		return recordMode;
	}
	public void setRecordMode(String recordMode) {
		this.recordMode = recordMode;
	}
	public String getDbmsTable() {
		return dbmsTable;
	}
	public void setDbmsTable(String dbmsTable) {
		this.dbmsTable = dbmsTable;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getColumns() {
		return columns;
	}
	public void setColumns(String columns) {
		this.columns = columns;
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
	public String getDataFindingDirection() {
		return dataFindingDirection;
	}
	public void setDataFindingDirection(String dataFindingDirection) {
		this.dataFindingDirection = dataFindingDirection;
	}
	public int getExplodedDepth() {
		return explodedDepth;
	}
	public void setExplodedDepth(int explodedDepth) {
		this.explodedDepth = explodedDepth;
	}
	@SuppressWarnings("unchecked")
    public <T> T getAttribute(String attribute){
        StringBuffer methodBufStr = new StringBuffer();
        methodBufStr.append("get").append(attribute.substring(0, 1).toUpperCase()).append(attribute.substring(1));
        try {
            Method method = this.getClass().getMethod(methodBufStr.toString());
            return (T)method.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return(null);
    }
    public void setAttributeValue(String attribute, Object value){
        StringBuffer methodBufStr = new StringBuffer();
        methodBufStr.append("set").append(attribute.substring(0, 1).toUpperCase()).append(attribute.substring(1));
        try {
            Class<?>[] paramType = new Class<?>[1];
            Object[] sArguments = new Object[1];
            paramType[0] = value.getClass();
            sArguments[0] = value;
            Method method = this.getClass().getMethod(methodBufStr.toString(),paramType);
            method.invoke(this,sArguments);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FoundationException("Cannot Set value" + value.toString() + ") for Attribute(" + attribute + ")");
        }
    }
}
