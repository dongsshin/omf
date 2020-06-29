/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : UserPropertyVO.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 3. 9. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.user.model;


import com.rap.omc.foundation.common.model.ModelRootVO;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : UserPropertyVO
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class User2UserResultVO extends ModelRootVO{
    private String obid;
    private long   flags;
    private String names;
    private String descriptions;
    private long   kinds;
    private String pdepartmentCode;
    private String departmentDesc;
    private String departmentDescKor;
    private String emailId;
    private String relationObid;
    private String relationFromObid;
    private long   relationFlags;
    private long   relationSchemaKind;
    private String uniqueStr;
    private String uniqueStrParent;
    private int    level;
    
    public String getUserType(){
        if(Bit.isInclude(this.kinds, OmcSystemConstants.SYSUSER_KIND_RoleGroup)) return "RoleGroup";
        if(Bit.isInclude(this.kinds, OmcSystemConstants.SYSUSER_KIND_User)) return "User";
        if(Bit.isInclude(this.kinds, OmcSystemConstants.SYSUSER_KIND_Role)) return "Role";
        if(Bit.isInclude(this.kinds, OmcSystemConstants.SYSUSER_KIND_Group)) return "Group";
        return "Not Defined";
    }
    public String getRelationType(){
        if(Bit.isInclude(this.relationSchemaKind, OmcSystemConstants.SYSREL_KIND_GroupUsrs)) return "GroupUsrs";
        if(Bit.isInclude(this.relationSchemaKind, OmcSystemConstants.SYSREL_KIND_GroupGroup)) return "GroupGroup";
        if(Bit.isInclude(this.relationSchemaKind, OmcSystemConstants.SYSREL_KIND_RoleRole)) return "RoleRole";
        if(Bit.isInclude(this.relationSchemaKind, OmcSystemConstants.SYSREL_KIND_RoleUser)) return "RoleUser";
        if(Bit.isInclude(this.relationSchemaKind, OmcSystemConstants.SYSREL_KIND_RoleGroupUser)) return "RoleGroupUser";
        if(Bit.isInclude(this.relationSchemaKind, OmcSystemConstants.SYSREL_KIND_GroupRole)) return "GroupRole";
        if(Bit.isInclude(this.relationSchemaKind, OmcSystemConstants.SYSREL_KIND_GroupUser)) return "GroupUser";
        return "Not Defined";
    }
    public String getUniqueStr(){
        return uniqueStr;
    }

    
    public void setUniqueStr(String uniqueStr){
        this.uniqueStr = uniqueStr;
    }

    
    public String getUniqueStrParent(){
        return uniqueStrParent;
    }

    
    public void setUniqueStrParent(String uniqueStrParent){
        this.uniqueStrParent = uniqueStrParent;
    }

    
    public int getLevel(){
        return level;
    }

    
    public void setLevel(int level){
        this.level = level;
    }

    public String getObid(){
        return obid;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    public long getFlags(){
        return flags;
    }
    
    public void setFlags(long flags){
        this.flags = flags;
    }
    
    public String getNames(){
        return names;
    }
    
    public void setNames(String names){
        this.names = names;
    }
    
    public String getDescriptions(){
        return descriptions;
    }
    
    public void setDescriptions(String descriptions){
        this.descriptions = descriptions;
    }
    
    public long getKinds(){
        return kinds;
    }
    
    public void setKinds(long kinds){
        this.kinds = kinds;
    }
    
    public String getPdepartmentCode(){
        return pdepartmentCode;
    }
    
    public void setPdepartmentCode(String pdepartmentCode){
        this.pdepartmentCode = pdepartmentCode;
    }
    
    public String getDepartmentDesc(){
        return departmentDesc;
    }
    
    public void setDepartmentDesc(String departmentDesc){
        this.departmentDesc = departmentDesc;
    }
    
    public String getDepartmentDescKor(){
        return departmentDescKor;
    }
    
    public void setDepartmentDescKor(String departmentDescKor){
        this.departmentDescKor = departmentDescKor;
    }
    
    public String getEmailId(){
        return emailId;
    }
    
    public void setEmailId(String emailId){
        this.emailId = emailId;
    }
    
    public String getRelationObid(){
        return relationObid;
    }
    
    public void setRelationObid(String relationObid){
        this.relationObid = relationObid;
    }
    
    public String getRelationFromObid(){
        return relationFromObid;
    }
    
    public void setRelationFromObid(String relationFromObid){
        this.relationFromObid = relationFromObid;
    }
    
    public long getRelationFlags(){
        return relationFlags;
    }
    
    public void setRelationFlags(long relationFlags){
        this.relationFlags = relationFlags;
    }
    
    public long getRelationSchemaKind(){
        return relationSchemaKind;
    }
    
    public void setRelationSchemaKind(long relationSchemaKind){
        this.relationSchemaKind = relationSchemaKind;
    }
    
}
