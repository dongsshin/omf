/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaTableColumnVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 18.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;


/**
 * <pre>
 * Class : OmcSchemaTableColumnVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaTableColumnVO {
    
    private String classType;
    private String dbmsColumn;
    private int    dataType;
    private int    lengths;
    private String nullable;
        
    public String getNullable(){
        return nullable;
    }


    
    public void setNullable(String nullable){
        this.nullable = nullable;
    }


    public String getClassType(){
        return classType;
    }

    
    public void setClassType(String classType){
        this.classType = classType;
    }

    public String getDbmsColumn(){
        return dbmsColumn;
    }
    

    
    public int getDataType(){
        return dataType;
    }



    
    public void setDataType(int dataType){
        this.dataType = dataType;
    }



    public int getLengths(){
        return lengths;
    }
    
    public void setDbmsColumn(String dbmsColumn){
        this.dbmsColumn = dbmsColumn;
    }
    

    
    public void setLengths(int lengths){
        this.lengths = lengths;
    }
}
