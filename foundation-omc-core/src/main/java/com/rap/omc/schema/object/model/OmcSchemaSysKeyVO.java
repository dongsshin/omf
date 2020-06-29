/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SchemaSysKeyVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 1. 31.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import com.rap.omc.api.oql.model.OmcSQLDefaultMap;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;


/**
 * <pre>
 * Class : OmcSchemaSysKeyVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSysKeyVO extends OmcSQLDefaultMap{
    private String obid;
    private int kinds = 0;
    
    /**
     * @param kinds
     */
    public OmcSchemaSysKeyVO() {
        super();
        this.setSql("INSERT INTO PSYSKEYTABLE(OBID,PKINDS)VALUES(#{obid},#{kinds})");
    }
    public OmcSchemaSysKeyVO(int kinds) {
        super();
        this.kinds = kinds;
        this.setSql("INSERT INTO PSYSKEYTABLE(OBID,PKINDS)VALUES(#{obid},#{kinds})");
    }
    /**
     * @param obid
     */
    public OmcSchemaSysKeyVO(String obid) {
        super();
        this.obid = obid;
        this.setSql("INSERT INTO PSYSKEYTABLE(OBID,PKINDS)VALUES(#{obid},#{kinds})");
    }
    
    /**
     * @param obid
     * @param kinds
     */
    public OmcSchemaSysKeyVO(String obid, int kinds) {
        super();
        this.obid = obid;
        this.kinds = kinds;
        this.setSql("INSERT INTO PSYSKEYTABLE(OBID,PKINDS)VALUES(#{obid},#{kinds})");
    }
    public String getObid(){
        return obid;
    }
    
    public long getKinds(){
        return kinds;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    public void setKinds(int kinds){
        this.kinds = kinds;
    }

    @Override
    public String toString(){
        return "SchemaSysKeyVO [obid=" + obid + ", kinds=" + kinds + ", getObid()=" + getObid() + ", getKinds()="
                + getKinds() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }
}
