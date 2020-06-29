/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SysUserVO.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 4. 15. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.user.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.model.OmcSchemaUserVO;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : SysUserVO
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class SysUserVO{

    private String obid;
    
    private long   flags;

    private String userId;
    
    private String descriptions;
    
    private long kinds;

    private String kindsStr;
    
    private String password;

    private String site;

    private String creator;

    private Date created;

    private String modifier;

    private Date modified;
    
    private String timeStamp;
       
    private Set<String> roleSet;
    
    private Set<String> groupSet;
    
    private Map<String,String> propertyList;
    
    
    public SysUserVO() {
        super();
    }
    public SysUserVO(OmcSchemaUserVO schemaUserVO) {
        super();
        this.obid = schemaUserVO.getObid();
        this.flags = schemaUserVO.getFlags();
        this.userId = schemaUserVO.getNames();
        this.descriptions = schemaUserVO.getDescriptions();
        this.kinds = schemaUserVO.getKinds();
        this.kindsStr = schemaUserVO.getKindsStr();
        this.password = schemaUserVO.getPassword();
        this.site = schemaUserVO.getSite();
        this.timeStamp = schemaUserVO.getTimeStamp();
        this.creator = schemaUserVO.getCreator();
        this.created = schemaUserVO.getCreated();
        this.modifier = schemaUserVO.getModifier();
        this.modified = schemaUserVO.getModified();
    }
    
    
    public String getTimeStamp() {
		return timeStamp;
	}
	public void setTime_stamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Set<String> getRoleSet(){
        return roleSet;
    }
    
    public Set<String> getGroupSet(){
        return groupSet;
    }



    
    public void setGroupSet(Set<String> groupSet){
        this.groupSet = groupSet;
    }



    public Map<String, String> getPropertyList(){
        return propertyList;
    }



    
    public void setRoleSet(Set<String> roleSet){
        this.roleSet = roleSet;
    }



    
    public void setPropertyList(Map<String, String> propertyList){
        this.propertyList = propertyList;
    }



    public String getUserId(){
        return userId;
    }


    
    public void setUserId(String userId){
        this.userId = userId;
    }


    public String getObid(){
        return obid;
    }

    
    public long getFlags(){
        return flags;
    }
    
    public String getDescriptions(){
        return descriptions;
    }

    
    public long getKinds(){
        return kinds;
    }

    
    public String getKindsStr(){
        return kindsStr;
    }

    
    public String getPassword(){
        return password;
    }

    
    public String getSite(){
        return site;
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


    
    public void setDescriptions(String descriptions){
        this.descriptions = descriptions;
    }

    
    public void setKinds(long kinds){
        this.kinds = kinds;
    }

    
    public void setKindsStr(String kindsStr){
        this.kindsStr = kindsStr;
    }

    
    public void setPassword(String password){
        this.password = password;
    }

    
    public void setSite(String site){
        this.site = site;
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
    public void setGroupSet(String groupList){
        if(this.groupSet == null) this.groupSet = new HashSet<String>();
        String[] array = groupList.split(",");
        if(!NullUtil.isNone(groupList)){
            for(int i = 0; i < array.length; i ++){
                this.groupSet.add(array[i]);
            }     
        }
    }
    public void setGroupSet(List<String> groupList){
        if(this.groupSet == null) this.groupSet = new HashSet<String>();
        for(String group : groupList){
            this.groupSet.add(group);
        }
    }
    public void setRoleSet(String roleList){
        String[] roleArray = roleList.split(",");
        if(this.roleSet == null) this.roleSet = new HashSet<String>();
        if(!NullUtil.isNone(roleList)){
            for(int i = 0; i < roleArray.length; i ++){
                this.roleSet.add(roleArray[i]);
            }            
        }
    }
    public void setRoleSet(List<String> roleList){
        if(this.roleSet == null) this.roleSet = new HashSet<String>();
        for(String role : roleList){
            this.roleSet.add(role);
        }
    }
    public void setPropertyList(String propertyList){
        String[] ptyArray = propertyList.split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME));
        if(NullUtil.isNone(this.propertyList)) this.propertyList = new HashMap<String,String>();
        if(!NullUtil.isNone(propertyList)){
            for(int i = 0; i < ptyArray.length; i ++){
                String[] valueArray = ptyArray[i].split(Pattern.quote(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE));
                if(valueArray.length != 2) throw new FoundationException("Property Format Error");
                this.propertyList.put(valueArray[0],valueArray[1]);
            }            
        }
    }
}
