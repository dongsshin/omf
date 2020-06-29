/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaUserVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 5.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaUserVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaUserVO extends OmcSchemaSysRootVO{
    private String  descriptions ;
    private String  kindsStr      ;
    private String  password     ;
    private String  parent       = "1";
    private String  site         ;
    private long    kinds        ;
    
    private String  departmentCode ;
    private String  departmentDesc ;
    private String  departmentDescKor ;
    private String  emailId ;
    private String  timeStamp ;

    private String relationObid  ;
    private long   schemaKind    ;
    private long   relationFlags ;
    private String attribute01   ;
    private String attribute02   ;
    private String attribute03   ;
    private String attribute04   ;
    private String attribute05   ;
    private String attribute06   ;
    private String attribute07   ;
    private String attribute08   ;
    private String attribute09   ;
    private String attribute10   ;
    private String attribute11   ;
    private String attribute12   ;
    private String attribute13   ;
    private String attribute14   ;
    private String attribute15   ;
    private String attribute16   ;
    private String attribute17   ;
    private String attribute18   ;
    private String attribute19   ;
    private String attribute20   ;
    
    public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getRelationObid(){
        return relationObid;
    }
    public void setRelationObid(String relationObid){
        this.relationObid = relationObid;
    }
    
    public boolean getCommonUserIsActive(){
        if(Bit.isInclude(this.getFlags(), OmcSystemConstants.SYSUSER_FLAG_Active)) return true;
        return false;
    }
    
    public long getSchemaKind(){
        return schemaKind;
    }


    
    public void setSchemaKind(long schemaKind){
        this.schemaKind = schemaKind;
    }


    
    public long getRelationFlags(){
        return relationFlags;
    }


    
    public void setRelationFlags(long relationFlags){
        this.relationFlags = relationFlags;
    }


    
    public String getAttribute01(){
        return attribute01;
    }


    
    public void setAttribute01(String attribute01){
        this.attribute01 = attribute01;
    }


    
    public String getAttribute02(){
        return attribute02;
    }


    
    public void setAttribute02(String attribute02){
        this.attribute02 = attribute02;
    }


    
    public String getAttribute03(){
        return attribute03;
    }


    
    public void setAttribute03(String attribute03){
        this.attribute03 = attribute03;
    }


    
    public String getAttribute04(){
        return attribute04;
    }


    
    public void setAttribute04(String attribute04){
        this.attribute04 = attribute04;
    }


    
    public String getAttribute05(){
        return attribute05;
    }


    
    public void setAttribute05(String attribute05){
        this.attribute05 = attribute05;
    }


    
    public String getAttribute06(){
        return attribute06;
    }


    
    public void setAttribute06(String attribute06){
        this.attribute06 = attribute06;
    }


    
    public String getAttribute07(){
        return attribute07;
    }


    
    public void setAttribute07(String attribute07){
        this.attribute07 = attribute07;
    }


    
    public String getAttribute08(){
        return attribute08;
    }


    
    public void setAttribute08(String attribute08){
        this.attribute08 = attribute08;
    }


    
    public String getAttribute09(){
        return attribute09;
    }


    
    public void setAttribute09(String attribute09){
        this.attribute09 = attribute09;
    }


    
    public String getAttribute10(){
        return attribute10;
    }


    
    public void setAttribute10(String attribute10){
        this.attribute10 = attribute10;
    }


    
    public String getAttribute11(){
        return attribute11;
    }


    
    public void setAttribute11(String attribute11){
        this.attribute11 = attribute11;
    }


    
    public String getAttribute12(){
        return attribute12;
    }


    
    public void setAttribute12(String attribute12){
        this.attribute12 = attribute12;
    }


    
    public String getAttribute13(){
        return attribute13;
    }


    
    public void setAttribute13(String attribute13){
        this.attribute13 = attribute13;
    }


    
    public String getAttribute14(){
        return attribute14;
    }


    
    public void setAttribute14(String attribute14){
        this.attribute14 = attribute14;
    }


    
    public String getAttribute15(){
        return attribute15;
    }


    
    public void setAttribute15(String attribute15){
        this.attribute15 = attribute15;
    }


    
    public String getAttribute16(){
        return attribute16;
    }


    
    public void setAttribute16(String attribute16){
        this.attribute16 = attribute16;
    }


    
    public String getAttribute17(){
        return attribute17;
    }


    
    public void setAttribute17(String attribute17){
        this.attribute17 = attribute17;
    }


    
    public String getAttribute18(){
        return attribute18;
    }


    
    public void setAttribute18(String attribute18){
        this.attribute18 = attribute18;
    }


    
    public String getAttribute19(){
        return attribute19;
    }


    
    public void setAttribute19(String attribute19){
        this.attribute19 = attribute19;
    }


    
    public String getAttribute20(){
        return attribute20;
    }


    
    public void setAttribute20(String attribute20){
        this.attribute20 = attribute20;
    }


    public String getDepartmentCode(){
        return departmentCode;
    }

    
    public String getDepartmentDesc(){
        return departmentDesc;
    }

    
    public String getDepartmentDescKor(){
        return departmentDescKor;
    }

    
    public String getEmailId(){
        return emailId;
    }

    
    public void setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }

    
    public void setDepartmentDesc(String departmentDesc){
        this.departmentDesc = departmentDesc;
    }

    
    public void setDepartmentDescKor(String departmentDescKor){
        this.departmentDescKor = departmentDescKor;
    }

    
    public void setEmailId(String emailId){
        this.emailId = emailId;
    }

    public long getKinds(){
        return kinds;
    }
    
    public void setKinds(long kinds){
        this.kinds = kinds;
        String kindStr = "";
        if(Bit.isInclude(kinds, OmcSystemConstants.SYSUSER_KIND_User)) kindStr = "User";
        if(Bit.isInclude(kinds, OmcSystemConstants.SYSUSER_KIND_Role)) kindStr = "Role";
        if(Bit.isInclude(kinds, OmcSystemConstants.SYSUSER_KIND_Group)) kindStr = "Group";
        if(Bit.isInclude(kinds, OmcSystemConstants.SYSUSER_KIND_RoleGroup)) kindStr = "RoleGroup";
        this.kindsStr = kindStr;
    }

    public String getDescriptions(){
        return descriptions;
    }
    
    public String getKindsStr(){
        return kindsStr;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getParent(){
        return parent;
    }
    
    public String getSite(){
        return site;
    }
    
    public void setDescriptions(String descriptions){
        this.descriptions = descriptions;
    }
    
    public void setKindsStr(String kindsStr){
        this.kindsStr = kindsStr;
        long kinds = 0; 
        if("User".equals(kindsStr))      kinds = Bit.or(OmcSystemConstants.SYSUSER_KIND_Default,OmcSystemConstants.SYSUSER_KIND_User);
        if("Role".equals(kindsStr))      kinds = Bit.or(OmcSystemConstants.SYSUSER_KIND_Default,OmcSystemConstants.SYSUSER_KIND_Role);
        if("Group".equals(kindsStr))     kinds = Bit.or(OmcSystemConstants.SYSUSER_KIND_Default,OmcSystemConstants.SYSUSER_KIND_Group);
        if("RoleGroup".equals(kindsStr)) kinds = Bit.or(OmcSystemConstants.SYSUSER_KIND_Default,OmcSystemConstants.SYSUSER_KIND_RoleGroup);
        this.kinds = kinds;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setParent(String parent){
        this.parent = parent;
    }
    
    public void setSite(String site){
        this.site = site;
    }
    
}
