/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcOQLRelationClass.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 8. 10.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.model.schema;

import java.util.List;

import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcOQLRelationClass
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLRelationClass extends OmcOQLClassRoot {
    
    private int cardinalityFrom;
    private int cardinalityTo;
    private int revisionRuleFrom;
    private int revisionRuleTo;
    private List<OmcOQLRelatedClassInfo> relatedClassInfoList;
    
    /**
     * 
     */
    public OmcOQLRelationClass() {
        super();
    }
    
    /**
     * @param cardinalityFrom
     * @param cardinalityTo
     * @param revisionRuleFrom
     * @param revisionRuleTo
     * @param relatedClassInfoList
     */
    public OmcOQLRelationClass(int cardinalityFrom, int cardinalityTo, int revisionRuleFrom, int revisionRuleTo,
            List<OmcOQLRelatedClassInfo> relatedClassInfoList) {
        super();
        this.cardinalityFrom     = cardinalityFrom;
        this.cardinalityTo       = cardinalityTo;
        this.revisionRuleFrom    = revisionRuleFrom;
        this.revisionRuleTo      = revisionRuleTo;
        this.relatedClassInfoList = relatedClassInfoList;
    }
    public boolean allowDuplicate()
    {
        if((this.getClassInfoFlags() & OmcSystemConstants.RELATION_FLAG_AllowDuplicate) == OmcSystemConstants.RELATION_FLAG_AllowDuplicate) return(true);
        return(false);
    }
    //--------------------------Cardinality-------------------------------------
    public boolean isCardinalityFromOne()
    {
        if((this.getCardinalityFrom() & OmcSystemConstants.RELATION_CARDINALITY_One) == OmcSystemConstants.RELATION_CARDINALITY_One) return(true);
        return(false);
    }
    public boolean isCardinalityFromMany()
    {
        if((this.getCardinalityFrom() & OmcSystemConstants.RELATION_CARDINALITY_Many) == OmcSystemConstants.RELATION_CARDINALITY_Many) return(true);
        return(false);
    }
    public boolean isCardinalityToOne()
    {
        if((this.getCardinalityTo() & OmcSystemConstants.RELATION_CARDINALITY_One) == OmcSystemConstants.RELATION_CARDINALITY_One) return(true);
        return(false);
    }
    public boolean isCardinalityToMany()
    {
        if((this.getCardinalityTo() & OmcSystemConstants.RELATION_CARDINALITY_Many) == OmcSystemConstants.RELATION_CARDINALITY_Many) return(true);
        return(false);
    }
    //--------------------------Relation Rule(Revise)-------------------------------------
    public boolean isFloatFromRevise()
    {
        if((this.getRevisionRuleFrom() & OmcSystemConstants.RELATION_RULE_RevFloat) == OmcSystemConstants.RELATION_RULE_RevFloat) return(true);
        return(false);
    }
    public boolean isReplicateFromRevise()
    {
        if((this.getRevisionRuleFrom() & OmcSystemConstants.RELATION_RULE_RevReplicate) == OmcSystemConstants.RELATION_RULE_RevReplicate) return(true);
        return(false);
    }
    public boolean isFloatToRevise()
    {
        if((this.getRevisionRuleTo() & OmcSystemConstants.RELATION_RULE_RevFloat) == OmcSystemConstants.RELATION_RULE_RevFloat) return(true);
        return(false);
    }
    public boolean isReplicateToRevise()
    {
        if((this.getRevisionRuleTo() & OmcSystemConstants.RELATION_RULE_RevReplicate) == OmcSystemConstants.RELATION_RULE_RevReplicate) return(true);
        return(false);
    }
    //--------------------------Relation Rule(Clone)-------------------------------------
    public boolean isFloatFromClone()
    {
        if((this.getRevisionRuleFrom() & OmcSystemConstants.RELATION_RULE_CloneFloat) == OmcSystemConstants.RELATION_RULE_CloneFloat) return(true);
        return(false);
    }
    public boolean isReplicateFromClone()
    {
        if((this.getRevisionRuleFrom() & OmcSystemConstants.RELATION_RULE_CloneReplicate) == OmcSystemConstants.RELATION_RULE_CloneReplicate) return(true);
        return(false);
    }
    public boolean isFloatToClone()
    {
        if((this.getRevisionRuleTo() & OmcSystemConstants.RELATION_RULE_CloneFloat) == OmcSystemConstants.RELATION_RULE_CloneFloat) return(true);
        return(false);
    }
    public boolean isReplicateToClone()
    {
        if((this.getRevisionRuleTo() & OmcSystemConstants.RELATION_RULE_CloneReplicate) == OmcSystemConstants.RELATION_RULE_CloneReplicate) return(true);
        return(false);
    }
    //--------------------------Relation Rule(Delete)-------------------------------------
    public boolean isDefloatFromDelete()
    {
        if((this.getRevisionRuleFrom() & OmcSystemConstants.RELATION_RULE_DeleteDefloat) == OmcSystemConstants.RELATION_RULE_DeleteDefloat) return(true);
        return(false);
    }
    public boolean isDefloatToDelete()
    {
        if((this.getRevisionRuleTo() & OmcSystemConstants.RELATION_RULE_DeleteDefloat) == OmcSystemConstants.RELATION_RULE_DeleteDefloat) return(true);
        return(false);
    }
    
    public List<OmcOQLRelatedClassInfo> getRelatedClassInfoList(){
        return relatedClassInfoList;
    }

    
    public void setRelatedClassInfoList(List<OmcOQLRelatedClassInfo> relatedClassInfoList){
        this.relatedClassInfoList = relatedClassInfoList;
    }

    public int getCardinalityFrom(){
        return cardinalityFrom;
    }
    
    public int getCardinalityTo(){
        return cardinalityTo;
    }
    
    public int getRevisionRuleFrom(){
        return revisionRuleFrom;
    }
    
    public int getRevisionRuleTo(){
        return revisionRuleTo;
    }
    
    public void setCardinalityFrom(int cardinalityFrom){
        this.cardinalityFrom = cardinalityFrom;
    }
    
    public void setCardinalityTo(int cardinalityTo){
        this.cardinalityTo = cardinalityTo;
    }
    
    public void setRevisionRuleFrom(int revisionRuleFrom){
        this.revisionRuleFrom = revisionRuleFrom;
    }
    
    public void setRevisionRuleTo(int revisionRuleTo){
        this.revisionRuleTo = revisionRuleTo;
    }
    
}
