/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaSysRelClassVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import com.rap.omc.api.util.PropertyUtil;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaSysRelClassVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaSysRelClassVO extends OmcSchemaSysRootVO {
    private String displayedName;
    private String displayedNameKr;
    private String parentObid;
    private String javaPackage;
    private String namesParent;
    private int    classLevel;
    private String uniqueStr;
    private String uniqueStrParent;
    
    private String  fromClass           ;
    private String  toClass             ;
    private String  fromRelationship    ;
    private String  toRelationship      ;
    
    private String  relationFromMeaning ;
    private String  relationToMeaning ;
    
    private String  cardinalityFromStr  ;
    private String  cardinalityToStr    ;
    private String  revisionRuleFromStr ;
    private String  revisionRuleToStr   ;
    private String  allowDuplicateStr     ;
    private boolean allowDuplicate = false     ;
    private String  isInstantiableStr     ;
    private boolean isInstantiable = false     ;
    
    private long    cardinalityFrom   = 0;
    private long    cardinalityTo     = 0;
    private long    revisionRuleFrom  = 0;
    private long    revisionRuleTo    = 0;
    
    private int isReferenced = 1;
    
    @Override
    public void setFlags(long flags){
        super.setFlags(flags);
        if(Bit.isInclude(flags, OmcSystemConstants.RELATION_FLAG_Instantiable  )) this.isInstantiable = true;
        if(Bit.isInclude(flags, OmcSystemConstants.RELATION_FLAG_AllowDuplicate)) this.allowDuplicate = true;
    }
    
    
    public int getIsReferenced(){
        return isReferenced;
    }

    
    public void setIsReferenced(int isReferenced){
        this.isReferenced = isReferenced;
    }

    public String getRelationFromMeaning(){
        return relationFromMeaning;
    }


    
    public String getRelationToMeaning(){
        return relationToMeaning;
    }


    
    public void setRelationFromMeaning(String relationFromMeaning){
        this.relationFromMeaning = relationFromMeaning;
    }


    
    public void setRelationToMeaning(String relationToMeaning){
        this.relationToMeaning = relationToMeaning;
    }


    public String getDisplayedNameKr(){
        return displayedNameKr;
    }

    
    public void setDisplayedNameKr(String displayedNameKr){
        this.displayedNameKr = displayedNameKr;
    }

    public boolean getIsInstantiable(){
        return isInstantiable;
    }

    public String getNamesParent(){
        return namesParent;
    }
    
    public void setNamesParent(String namesParent){
        this.namesParent = namesParent;
    }
    public String getDisplayedName(){
        return displayedName;
    }
    
    public String getParentObid(){
        return parentObid;
    }
    
    public String getJavaPackage(){
        return javaPackage;
    }
    
    public int getClassLevel(){
        return classLevel;
    }
    
    public String getUniqueStr(){
        return uniqueStr;
    }
    
    public String getUniqueStrParent(){
        return uniqueStrParent;
    }
    public void setDisplayedName(String displayedName){
        this.displayedName = displayedName;
    }
    
    public void setParentObid(String parentObid){
        this.parentObid = parentObid;
    }
    
    public void setJavaPackage(String javaPackage){
        this.javaPackage = javaPackage;
    }
    
    public void setClassLevel(int classLevel){
        this.classLevel = classLevel;
    }
    
    public void setUniqueStr(String uniqueStr){
        this.uniqueStr = uniqueStr;
    }
    
    public void setUniqueStrParent(String uniqueStrParent){
        this.uniqueStrParent = uniqueStrParent;
    }
    public void setIsInstantiable(String isInstantiable){
        if(isInstantiable.equals("Y")){
            this.isInstantiable = true;
        }else{
            this.isInstantiable = false;
        }
    }
    public boolean getAllowDuplicate(){
        return allowDuplicate;
    }
    
    public void setAllowDuplicate(String allowDuplicate){
        if(allowDuplicate.equals("Y")){
            this.allowDuplicate = true;
        }else{
            this.allowDuplicate = false;
        }
    }
    public String getCardinalityFromStr(){
        return cardinalityFromStr;
    }
    public String getCardinalityToStr(){
        return cardinalityToStr;
    }
    public String getRevisionRuleFromStr(){
        return revisionRuleFromStr;
    }
    public String getRevisionRuleToStr(){
        return revisionRuleToStr;
    }
    public void setCardinalityFromStr(String cardinalityFromStr){
        this.cardinalityFromStr = cardinalityFromStr;
        if(cardinalityFromStr.indexOf("One")  > - 1) this.cardinalityFrom = Bit.or(OmcSystemConstants.RELATION_CARDINALITY_Default, OmcSystemConstants.RELATION_CARDINALITY_One);
        if(cardinalityFromStr.indexOf("Many") > - 1) this.cardinalityFrom = Bit.or(OmcSystemConstants.RELATION_CARDINALITY_Default, OmcSystemConstants.RELATION_CARDINALITY_Many);
    }
    public void setCardinalityToStr(String cardinalityToStr){
        this.cardinalityToStr = cardinalityToStr;
        if(cardinalityToStr.indexOf("One")  > - 1) this.cardinalityTo = Bit.or(OmcSystemConstants.RELATION_CARDINALITY_Default, OmcSystemConstants.RELATION_CARDINALITY_One);
        if(cardinalityToStr.indexOf("Many") > - 1) this.cardinalityTo = Bit.or(OmcSystemConstants.RELATION_CARDINALITY_Default, OmcSystemConstants.RELATION_CARDINALITY_Many);
    }
    public void setRevisionRuleFromStr(String revisionRuleFromStr){
        this.revisionRuleFromStr = revisionRuleFromStr;
        long v = OmcSystemConstants.RELATION_RULE_Default;
        if(revisionRuleFromStr.indexOf("RevNone"       ) > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_RevNone);
        if(revisionRuleFromStr.indexOf("RevFloat"      ) > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_RevFloat);
        if(revisionRuleFromStr.indexOf("RevReplicate"  ) > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_RevReplicate);
        if(revisionRuleFromStr.indexOf("CloneNone"     ) > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_CloneNone);
        if(revisionRuleFromStr.indexOf("CloneFloat"    ) > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_CloneFloat);
        if(revisionRuleFromStr.indexOf("CloneReplicate") > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_CloneReplicate);
        if(revisionRuleFromStr.indexOf("DeleteDefloat" ) > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_DeleteDefloat);
        this.revisionRuleFrom = v;
    }
    public void setRevisionRuleToStr(String revisionRuleToStr){
        this.revisionRuleToStr = revisionRuleToStr;
        long v = OmcSystemConstants.RELATION_RULE_Default;
        if(revisionRuleToStr.indexOf("RevNone"       ) > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_RevNone);
        if(revisionRuleToStr.indexOf("RevFloat"      ) > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_RevFloat);
        if(revisionRuleToStr.indexOf("RevReplicate"  ) > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_RevReplicate);
        if(revisionRuleToStr.indexOf("CloneNone"     ) > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_CloneNone);
        if(revisionRuleToStr.indexOf("CloneFloat"    ) > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_CloneFloat);
        if(revisionRuleToStr.indexOf("CloneReplicate") > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_CloneReplicate);
        if(revisionRuleToStr.indexOf("DeleteDefloat" ) > - 1) v = Bit.or(v, OmcSystemConstants.RELATION_RULE_DeleteDefloat);
        this.revisionRuleTo = v;
    }
    public String getFromClass(){
        return fromClass;
    }
    
    public String getToClass(){
        return toClass;
    }
    
    public String getFromRelationship(){
        return fromRelationship;
    }
    
    public String getToRelationship(){
        return toRelationship;
    }
    
    public long getCardinalityFrom(){
        return cardinalityFrom;
    }
    
    public long getCardinalityTo(){
        return cardinalityTo;
    }
    
    public long getRevisionRuleFrom(){
        return revisionRuleFrom;
    }
    
    public long getRevisionRuleTo(){
        return revisionRuleTo;
    }
    
    public void setFromClass(String fromClass){
        this.fromClass = fromClass;
    }
    
    public void setToClass(String toClass){
        this.toClass = toClass;
    }
    
    public void setFromRelationship(String fromRelationship){
        this.fromRelationship = fromRelationship;
    }
    
    public void setToRelationship(String toRelationship){
        this.toRelationship = toRelationship;
    }
    
    public void setCardinalityFrom(long cardinalityFrom){
        this.cardinalityFrom = cardinalityFrom;
        if(Bit.isInclude(cardinalityFrom, OmcSystemConstants.RELATION_CARDINALITY_One )) this.cardinalityFromStr = "One";
        if(Bit.isInclude(cardinalityFrom, OmcSystemConstants.RELATION_CARDINALITY_Many)) this.cardinalityFromStr = "Many";
    }
    
    public void setCardinalityTo(long cardinalityTo){
        this.cardinalityTo = cardinalityTo;
        if(Bit.isInclude(cardinalityFrom, OmcSystemConstants.RELATION_CARDINALITY_One )) this.cardinalityToStr = "One";
        if(Bit.isInclude(cardinalityFrom, OmcSystemConstants.RELATION_CARDINALITY_Many)) this.cardinalityToStr = "Many";
            
    }
    public void setRevisionRuleFrom(long revisionRuleFrom){
        this.revisionRuleFrom = revisionRuleFrom;
        StringBuffer sb = new StringBuffer();
        if(Bit.isInclude(revisionRuleFrom, OmcSystemConstants.RELATION_RULE_RevNone)) sb.append("RevNone");
        if(sb.length() > 0) sb.append(",");
        if(Bit.isInclude(revisionRuleFrom, OmcSystemConstants.RELATION_RULE_RevFloat)) sb.append("RevFloat");
        if(sb.length() > 0) sb.append(",");
        if(Bit.isInclude(revisionRuleFrom, OmcSystemConstants.RELATION_RULE_RevReplicate)) sb.append("RevReplicate");
        if(sb.length() > 0) sb.append(",");
        if(Bit.isInclude(revisionRuleFrom, OmcSystemConstants.RELATION_RULE_CloneNone)) sb.append("CloneNone");
        if(sb.length() > 0) sb.append(",");
        if(Bit.isInclude(revisionRuleFrom, OmcSystemConstants.RELATION_RULE_CloneFloat)) sb.append("CloneFloat");
        if(sb.length() > 0) sb.append(",");
        if(Bit.isInclude(revisionRuleFrom, OmcSystemConstants.RELATION_RULE_CloneReplicate)) sb.append("CloneReplicate");
        if(sb.length() > 0) sb.append(",");
        if(Bit.isInclude(revisionRuleFrom, OmcSystemConstants.RELATION_RULE_DeleteDefloat)) sb.append("DeleteDefloat");
        this.revisionRuleFromStr = sb.toString();
    }
    public void setRevisionRuleTo(long revisionRuleTo){
        this.revisionRuleTo = revisionRuleTo;
        StringBuffer sb = new StringBuffer();
        if(Bit.isInclude(revisionRuleTo, OmcSystemConstants.RELATION_RULE_RevNone)) sb.append("RevNone");
        if(sb.length() > 0) sb.append(",");
        if(Bit.isInclude(revisionRuleTo, OmcSystemConstants.RELATION_RULE_RevFloat)) sb.append("RevFloat");
        if(sb.length() > 0) sb.append(",");
        if(Bit.isInclude(revisionRuleTo, OmcSystemConstants.RELATION_RULE_RevReplicate)) sb.append("RevReplicate");
        if(sb.length() > 0) sb.append(",");
        if(Bit.isInclude(revisionRuleTo, OmcSystemConstants.RELATION_RULE_CloneNone)) sb.append("CloneNone");
        if(sb.length() > 0) sb.append(",");
        if(Bit.isInclude(revisionRuleTo, OmcSystemConstants.RELATION_RULE_CloneFloat)) sb.append("CloneFloat");
        if(sb.length() > 0) sb.append(",");
        if(Bit.isInclude(revisionRuleTo, OmcSystemConstants.RELATION_RULE_CloneReplicate)) sb.append("CloneReplicate");
        if(sb.length() > 0) sb.append(",");
        if(Bit.isInclude(revisionRuleTo, OmcSystemConstants.RELATION_RULE_DeleteDefloat)) sb.append("DeleteDefloat");
        this.revisionRuleToStr = sb.toString();
    }
    public String getAllowDuplicateStr(){
        return allowDuplicateStr;
    }
    public String getIsInstantiableStr(){
        return isInstantiableStr;
    }
    public void setAllowDuplicateStr(String allowDuplicateStr){
        this.allowDuplicateStr = allowDuplicateStr;
        if(allowDuplicateStr.equals("Y")){
            this.allowDuplicate = true;
        }else{
            this.allowDuplicate = false;
        }
    }
    public void setIsInstantiableStr(String isInstantiableStr){
        this.isInstantiableStr = isInstantiableStr;
        if("Y".equals(isInstantiableStr)){
            this.isInstantiable = true;
        }else{
            this.isInstantiable = false;
        }
    }
    @Override
    public String toString(){
        return "OmcSchemaSysRelClassVO [" 
        + "names = " + this.getNames() + ", "
        + "sequences = " + this.getSequences() + ", "
        + "changeComments = " + this.getChangeComments() + ", "
        + "namesParent = " + namesParent + ", "
        + "allowDuplicateStr = " + allowDuplicateStr + ", "
        + "allowDuplicate = " + allowDuplicate + ", "
        + "fromClass = " + fromClass + ", "
        + "toClass = " + toClass + ", "
        + "fromRelationship = " + fromRelationship + ", "
        + "toRelationship = " + toRelationship + ", "
        + "cardinalityFromStr = " + cardinalityFromStr + ", "
        + "cardinalityFrom = " + cardinalityFrom + ", "
        + "cardinalityToStr = " + cardinalityToStr + ", "
        + "cardinalityTo = " + cardinalityTo + ", "
        + "revisionRuleFromStr = " + revisionRuleFromStr + ", "
        + "revisionRuleFrom = " + revisionRuleFrom + ", "
        + "revisionRuleToStr = " + revisionRuleToStr + ", "
        + "revisionRuleTo = " + revisionRuleTo + ", "
        + "isInstantiableStr = " + isInstantiableStr + ", "
        + "isInstantiable = " + isInstantiable + ", "
        + "javaPackage = " + javaPackage + ", "
        + "moduleName = " + this.getModuleName() + ", "
        + "remarks = " + this.getRemarks() + ", "
        + "owners = " + this.getOwners();
    }
}
