/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysRootVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import java.util.Date;

import com.rap.omc.api.object.model.BaseModel;
import com.rap.omc.api.oql.model.OmcSQLDefaultMap;
import com.rap.omc.schema.util.OmcSchemaConstants;


/**
 * <pre>
 * Class : OmcSchemaSysRootVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSysRootVO extends BaseModel{
    private int    classKinds = 0;
    private String obid;
    private long   flags = 0;
    private String names;
    private String creator  = OmcSchemaConstants.C_SCHEMA_DEFAULT_USER;
    private Date   created;
    private String modifier = OmcSchemaConstants.C_SCHEMA_DEFAULT_USER;
    private Date   modified;
    private long   sequences;
    private String changeComments;
    private String owners;
    private String moduleName;
    private String remarks;
    
    public int getClassKinds(){
        return classKinds;
    }

    
    
    public String getModuleName(){
        return moduleName;
    }


    
    public void setModuleName(String moduleName){
        this.moduleName = moduleName;
    }


    
    public void setRemarks(String remarks){
        this.remarks = remarks;
    }


    public void setClassKinds(int classKinds){
        this.classKinds = classKinds;
    }

    public String getObid(){
        return obid;
    }
    
    public long getFlags(){
        return flags;
    }
    
    public String getNames(){
        return names;
    }
    
    public String getCreator(){
        return creator;
    }
    
    public Date getCreated(){
        return created;
    }
    
    public String getModifier(){
        return modifier;
    }
    
    public Date getModified(){
        return modified;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    public void setFlags(long flags){
        this.flags = flags;
    }
    
    public void setNames(String names){
        this.names = names;
    }
    
    public void setCreator(String creator){
        this.creator = creator;
    }
    
    public void setCreated(Date created){
        this.created = created;
    }
    
    public void setModifier(String modifier){
        this.modifier = modifier;
    }
    
    public void setModified(Date modified){
        this.modified = modified;
    }


    
    public long getSequences(){
        return sequences;
    }


    
    public String getChangeComments(){
        return changeComments;
    }


    
    public String getOwners(){
        return owners;
    }

    
    public String getRemarks(){
        return remarks;
    }
    
    public void setSequences(long sequences){
        this.sequences = sequences;
    }


    
    public void setChangeComments(String changeComments){
        this.changeComments = changeComments;
    }


    
    public void setOwners(String owners){
        this.owners = owners;
    }
}
