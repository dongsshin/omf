/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ObjectRoot.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 5. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.object.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.rap.omc.api.util.StrUtil;

import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcSystemConstants;

/**
 * <pre>
 * Class : ObjectRoot
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
@SuppressWarnings("serial")
public class ObjectRootVO extends BaseModel{
    /**
     * 
     */
    private String obid;

    private Long flags = 0l;

    private String className;

    private String owner = "1";

    private String locker = "1";

    private String checkouter = "1";

    private Date checkouted;

    private String creator;

    private Date created;

    private String modifier;

    private Date modified;
    
    private HashMap<String, Object> outData = new HashMap<String, Object>();

    /**
     * 
     * 
     * @return the obid
     */
    public String getObid(){
        return obid;
    }

    /**
     * 
     * 
     * @param obid the obid to set
     */
    public void setObid(String obid){
        this.obid = obid;
    }

    /**
     * 
     * 
     * @return the flags
     */
    public Long getFlags(){
        return flags;
    }

    /**
     * 
     * 
     * @param flags the flags to set
     */
    public void setFlags(Long flags){
        this.flags = flags;
    }
    /**
     * 
     * 
     * @return the className
     */
    public String getClassName(){
        return className;
    }

    /**
     * 
     * 
     * @param className the className to set
     */
    public void setClassName(String className){
        this.className = className;
    }

    /**
     * 
     * 
     * @return the creator
     */
    public String getCreator(){
        return creator;
    }

    /**
     * 
     * 
     * @param creator the creator to set
     */
    public void setCreator(String creator){
        this.creator = creator;
    }

    /**
     * 
     * 
     * @return the created
     */
    public Date getCreated(){
        return created;
    }

    /**
     * 
     * 
     * @param created the created to set
     */
    public void setCreated(Date created){
        this.created = created;
    }
    
    /**
     * 
     * 
     * @param created the created to set
     */
    public void setCreated(Long created){
        this.created = new Date(created);
    }

    /**
     * 
     * 
     * @return the modifier
     */
    public String getModifier(){
        return modifier;
    }

    /**
     * 
     * 
     * @param modifier the modifier to set
     */
    public void setModifier(String modifier){
        this.modifier = modifier;
    }

    /**
     * 
     * 
     * @return the modified
     */
    public Date getModified(){
        return modified;
    }

    /**
     * 
     * 
     * @param modified the modified to set
     */
    public void setModified(Date modified){
        this.modified = modified;
    }
    
    /**
     * 
     * 
     * @param modified the modified to set
     */
    public void setModified(Long modified){
        this.modified = new Date(modified);
    }

    /**
     * 
     * 
     * @return the owner
     */
    public String getOwner(){
        return owner;
    }

    /**
     * 
     * 
     * @param owner the owner to set
     */
    public void setOwner(String owner){
        this.owner = owner;
    }

    /**
     * 
     * 
     * @return the locker
     */
    public String getLocker(){
        return locker;
    }

    /**
     * 
     * 
     * @param locker the locker to set
     */
    public void setLocker(String locker){
        this.locker = locker;
    }

    /**
     * 
     * 
     * @return the checkouter
     */
    public String getCheckouter(){
        return checkouter;
    }

    /**
     * 
     * 
     * @param checkouter the checkouter to set
     */
    public void setCheckouter(String checkouter){
        this.checkouter = checkouter;
    }

    /**
     * 
     * 
     * @return the checkouted
     */
    public Date getCheckouted(){
        return checkouted;
    }

    /**
     * 
     * 
     * @param checkouted the checkouted to set
     */
    public void setCheckouted(Date checkouted){
        this.checkouted = checkouted;
    }
    
    /**
     * 
     * 
     * @param checkouted the checkouted to set
     */
    public void setCheckouted(Long checkouted){
        this.checkouted = new Date(checkouted);
    }

    /**
     * 
     * 
     * @return the outData
     */
    public HashMap<String, Object> getOutData(){
        return outData;
    }
    public String getOutDataStringValue(String attribute){
        return (String)this.outData.get(attribute);
    }
    /**
     * Out Data의 값을 Return
     *
     * @param attribute
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getOutDataAttributeValue(String attribute){
        return (T)this.outData.get(attribute);
    }
    /**
     * Out Data에 값을 Put함.
     *
     * @param attribute
     * @param value
     */
    public void setOutDataAttributeValue(String attribute, Object value){
        this.outData.put(attribute,value);
    }
    /**
     * 
     * 
     * @param outData the outData to set
     */
    public void setOutData(HashMap<String, Object> outData){
        this.outData = outData;
    }
    
    public void setCheckouted(String checkouted) { 
        this.checkouted = omcConvertStr2Date(checkouted);
    }
    public void setCreated(String created){
        this.created = omcConvertStr2Date(created);
    }
    public void setModified(String modified){
        this.modified= omcConvertStr2Date(modified);
    }
    protected Date omcConvertStr2Date(String strDate) {
        Date date = null;
        if(strDate == null || strDate.length() == 0 ) return date;

        StringBuffer format = new StringBuffer();
        
        if(strDate.length() >= 10) {
            String prefix = strDate.substring(0,10);
            
            if( Pattern.matches("[0-9][0-9][0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9]",prefix)){
                format.append("yyyy-MM-dd");
                if(strDate.length() == 19) format.append(" HH:mm:ss");
                if(strDate.length() == 16) format.append(" HH:mm");
            }else if(Pattern.matches("[0-9][0-9][0-9][0-9]\\/[0-9][0-9]\\/[0-9][0-9]",prefix)){
                format.append("yyyy/MM/dd");
                if(strDate.length() == 19) format.append(" HH:mm:ss");
                if(strDate.length() == 16) format.append(" HH:mm");
            }else if(Pattern.matches("[0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9][0-9][0-9]",prefix)){
                format.append("MM-dd-yyyy");
                if(strDate.length() == 19) format.append(" HH:mm:ss");
                if(strDate.length() == 16) format.append(" HH:mm");
            }else if(Pattern.matches("[0-9][0-9]\\/[0-9][0-9]\\/[0-9][0-9][0-9][0-9]",prefix)){
                format.append("MM/dd/yyyy");
                if(strDate.length() == 19) format.append(" HH:mm:ss");
                if(strDate.length() == 16) format.append(" HH:mm");
            }else{
                prefix = strDate.substring(1,9);
                if(Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]",prefix)){
                    format.append("yyyyMMdd");
                    if(strDate.length() == 17) format.append(" HH:mm:ss");
                    if(strDate.length() == 14) format.append(" HH:mm");
                }
            }
        } else if(strDate.length() == 8) {
            format.append("yyyyMMdd");
        }
        
        SimpleDateFormat transFormat = new SimpleDateFormat(format.toString());
        try {
            date = transFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new FoundationException("[Foundation]Date Format does not support for '" + strDate + "' format.");
        }
        return date;
    }
    public final String makeVoDBKey(String attributePattern){
        List<String> strSet = StrUtil.convertArrayToList(attributePattern.split(","));
        StringBuffer str = new StringBuffer();
        boolean isFirst = true;
        for(String attr : strSet){
            if(!isFirst) str.append(OmcSystemConstants.DELIMINATOR_VALUE_GENERAL);
            if(attr.startsWith(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX)){
                str.append((String)this.getOutDataAttributeValue(attr.substring(OmcSystemConstants.OMC_SORT_UTIL_ATTR_SUFFIX.length())));
            }else{
                str.append((String)this.getAttributeValue(attr));
            }
            isFirst = false;
        }
        return str.toString();
    }
    public final <T> T getAttributeValue(String attribute){
        return this.getAttribute(attribute);
    }
}
