/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysRoot.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.dom;

import java.util.Map;

import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.schema.object.model.OmcSchemaSysRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaSysRoot
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
@SuppressWarnings("rawtypes")
public abstract class OmcSchemaSysRoot {
    OmcSchemaSysRootVO vo;
    /**
     * @param vo
     */
    public OmcSchemaSysRoot() {
        super();
    }

    public OmcSchemaSysRoot(OmcSchemaSysRootVO vo) {
        super();
        this.vo = vo;
    }
    public OmcSchemaSysRootVO getVo(){
        return vo;
    }
    public void setVo(OmcSchemaSysRootVO vo){
        this.vo = vo;
    }
    public final void setProperty(String propertyName,String propertyValue){
        
    }
    
    
    /**************************Create************************/
    
    protected abstract void create(Map map);
    public final void createObject(Map map){
        this.validateForCreate(map);
        //Should be implemented at Child Class: setFlags/setClassKind
        this.setFlags();
        this.setClassKind();
        this.preProcessForCreate(map);
        this.createSysKeyTable();
        this.create(map);
        this.postProcessForCreate(map);
    }
    private void createSysKeyTable(){
        this.vo.setObid(OmcSchemaServiceUtils.getObid(this.getVo().getClassKinds()));
    }
    
    protected void validateForCreate(Map map){
        
    }
    protected void preProcessForCreate(Map map){
 
    }
    protected void postProcessForCreate(Map map){
        
    }
    /**************************inActiviate************************/
    protected abstract void inActiviate(Map map);
    public final void inActiviateObject(Map map){
        validateForInActiviate(map);
        preProcessForInActiviate(map);
        inActiviate(map);
        postProcessForInActiviate(map);
    }
    protected void validateForInActiviate(Map map){
        
    }
    protected void preProcessForInActiviate(Map map){
        
    }
    protected void postProcessForInActiviate(Map map){
        
    }
    /**************************delete************************/
    protected abstract void delete(Map map);
    public final void deleteObject(Map map){
        validateForDelete(map);
        preProcessForDelete(map);
        delete(map);
        postProcessForDelete(map);
    }
    protected void validateForDelete(Map map){
        
    }
    protected void preProcessForDelete(Map map){
        
    }
    protected void postProcessForDelete(Map map){
        
    }
    /**************************modify************************/
    protected abstract void modify(Map map);
    public final void modifyObject(Map map){
        validateForModify(map);
        this.setFlags();
        preProcessForModify(map);
        modify(map);
        postProcessForModify(map);
    }
    protected void validateForModify(Map map){
        
    }
    protected void preProcessForModify(Map map){
        
    }
    protected void postProcessForModify(Map map){
        
    }
    protected abstract OmcSchemaSysRootVO getObjectInfoByName(String Names);
    protected abstract void setFlags();
    protected abstract void setClassKind();
    public static void appendPropertySql(StringBuffer sqlStrBuff, OmcSQLVariableParameter variableParameter,long kinds, String propertyName, String alias, int varSequence){
        String str1 = "funVariable_0010" + String.valueOf(varSequence);
        String str2 = "funVariable_0011" + String.valueOf(varSequence);
        
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            sqlStrBuff.append(OmcFoundationConstant.newline).append("(select x.pproperty_value from psysproperty x where x.psys_object = a.obid and x.pproperty_name = #{").append(str1).append("} and omcBitAnd(x.pkinds,(#{").append(str2).append("})) = (#{").append(str2).append("}))");
        
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_DB2))
            sqlStrBuff.append(OmcFoundationConstant.newline).append("(select x.pproperty_value from psysproperty x where x.psys_object = a.obid and x.pproperty_name = #{").append(str1).append("} and omcBitAnd(x.pkinds,(#{").append(str2).append("})) = (#{").append(str2).append("}))");

        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA))
            sqlStrBuff.append(OmcFoundationConstant.newline).append("(select x.pproperty_value from psysproperty x where x.psys_object = a.obid and x.pproperty_name = #{").append(str1).append("} and omcBitAnd(x.pkinds ,(#{").append(str2).append("})) = (#{").append(str2).append("}))");
        
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MSSQL))
            sqlStrBuff.append(OmcFoundationConstant.newline).append("(select x.pproperty_value from psysproperty x where x.psys_object = a.obid and x.pproperty_name = #{").append(str1).append("} and omcBitAnd(x.pkinds ,(#{").append(str2).append("})) = (#{").append(str2).append("}))");
        
        if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MYSQL))
            sqlStrBuff.append(OmcFoundationConstant.newline).append("(select x.pproperty_value from psysproperty x where x.psys_object = a.obid and x.pproperty_name = #{").append(str1).append("} and omcBitAnd(x.pkinds ,(#{").append(str2).append("})) = (#{").append(str2).append("}))");
        sqlStrBuff.append(" as ").append(alias);
        if(varSequence == 1){
            variableParameter.setFunVariable_00101(propertyName);
            variableParameter.setFunVariable_00111(String.valueOf(kinds));
        }else if(varSequence == 2){
            variableParameter.setFunVariable_00102(propertyName);
            variableParameter.setFunVariable_00112(String.valueOf(kinds));
        }else if(varSequence == 3){
            variableParameter.setFunVariable_00103(propertyName);
            variableParameter.setFunVariable_00113(String.valueOf(kinds));
        }else if(varSequence == 4){
            variableParameter.setFunVariable_00104(propertyName);
            variableParameter.setFunVariable_00114(String.valueOf(kinds));
        }else if(varSequence == 5){
            variableParameter.setFunVariable_00105(propertyName);
            variableParameter.setFunVariable_00115(String.valueOf(kinds));
        }else if(varSequence == 6){
            variableParameter.setFunVariable_00106(propertyName);
            variableParameter.setFunVariable_00116(String.valueOf(kinds));
        }else if(varSequence == 7){
            variableParameter.setFunVariable_00107(propertyName);
            variableParameter.setFunVariable_00117(String.valueOf(kinds));
        }else if(varSequence == 8){
            variableParameter.setFunVariable_00108(propertyName);
            variableParameter.setFunVariable_00118(String.valueOf(kinds));
        }else if(varSequence == 9){
            variableParameter.setFunVariable_00109(propertyName);
            variableParameter.setFunVariable_00119(String.valueOf(kinds));
        }
    }
}
