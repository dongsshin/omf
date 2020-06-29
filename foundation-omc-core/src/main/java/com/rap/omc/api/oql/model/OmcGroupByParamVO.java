package com.rap.omc.api.oql.model;

import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcSystemConstants;

/**
 * 
 * <pre>
 * Class : OmcForGroupByVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcGroupByParamVO {
    public static enum KEY {
        groubBy, min, max, sum, avg
    }
    String attributePattern ;
    String aliasPattern;
    KEY    type;
    
    public OmcGroupByParamVO(String attributePattern, KEY type) {
        super();
        this.attributePattern = attributePattern;
        this.aliasPattern = "";
        this.type = type;
    }

    public String getAttributePattern(){
        return attributePattern;
    }
    
    public void setAttributePattern(String attributePattern){
        this.attributePattern = attributePattern;
    }
    
    public String getAliasPattern(){
        return aliasPattern;
    }
    
    public void setAliasPattern(String aliasPattern){
        this.aliasPattern = aliasPattern;
    }

    
    public KEY getType(){
        return type;
    }

    
    public void setType(KEY type){
        this.type = type;
    }
    private String getPrefix(){
        if(this.type.equals(KEY.groubBy))  return OmcSystemConstants.QUERY_GROUPBY_omcGroupByBasisOnPrefix;
        if(this.type.equals(KEY.max))      return OmcSystemConstants.QUERY_GROUPBY_omcGroupByMaxPrefix;
        if(this.type.equals(KEY.min))      return OmcSystemConstants.QUERY_GROUPBY_omcGroupByMinPrefix;
        if(this.type.equals(KEY.sum))      return OmcSystemConstants.QUERY_GROUPBY_omcGroupBySumPrefix;
        if(this.type.equals(KEY.avg))      return OmcSystemConstants.QUERY_GROUPBY_omcGroupByAvgPrefix;
        throw new FoundationException(this.type + " is not Impemented.");
    }
    private String getFunction(String str){
        if(this.type.equals(KEY.groubBy))  return str;
        if(this.type.equals(KEY.max))      return "max(" + str + ")";
        if(this.type.equals(KEY.min))      return "min(" + str + ")";
        if(this.type.equals(KEY.sum))      return "sum(" + str + ")";
        if(this.type.equals(KEY.avg))      return "avg(" + str + ")";
        throw new FoundationException(this.type + " is not Impemented.");
    }
    public String makeSelectPattern(){
         return getFunction(this.attributePattern) + " " + getPrefix() + this.aliasPattern;
    }
    public String makeGroupByPattern(){
        if(!this.type.equals(KEY.groubBy)) throw new FoundationException("This is not GroupBy Attribute");
        return getPrefix() + this.aliasPattern;
    }
    private String getAttribute(){
        String str = this.attributePattern;
        str = str.substring(str.lastIndexOf("[")+1,str.lastIndexOf("]"));
        return str;
    }
    public void splitPattern(){
        String str = this.attributePattern.trim();
        String attributePattern = str;
        String aliasPattern     = "";
        int idx = str.lastIndexOf(" ");
        if(idx > 1){
            aliasPattern = (str.substring(idx+1)).trim();
            attributePattern = (str.substring(0,idx)).trim();
        }else{
            aliasPattern = getAttribute();
            attributePattern = this.attributePattern;
        }
        this.attributePattern = attributePattern;
        this.aliasPattern = aliasPattern;
    }
}
