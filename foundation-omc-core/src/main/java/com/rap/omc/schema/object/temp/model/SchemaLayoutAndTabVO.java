/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SchemaBusinessObjectClassVO.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2016. 06. 27. dongsik.shin Initial
 * ===========================================
 */
package com.rap.omc.schema.object.temp.model;
/**
 * <pre>
 * Class : SchemaClassAttributeVO
 * Description : BusinessObject관리를 위한 VO.
 * </pre>
 * 
 * @author dongsik.shin
 */
public class SchemaLayoutAndTabVO {
   
    private Integer zschemaSequence                    ;
    private String  zschemaComments                    ;
    private String  zschemaRemarks                     ;
    private String  zschemaNames                       ;
    private String  zschemaKind                        ;
    private String  zschemaLabel                       ;
    private String  zschemaSubNames                    ;
    private String  zschemaHerf                        ;
    private String  zschemaAlt                         ;
    private Integer zschemaHeight                      ;
    private String  zschemaOwner                       ;
    private String  obid                               ;
    private String  fromObidList                       ;
    private String  toObidList                         ;
    private String  relationObidList                   ;
    
    public Integer getZschemaSequence(){
        return zschemaSequence;
    }
    
    public String getZschemaComments(){
        return zschemaComments;
    }
    
    public String getZschemaRemarks(){
        return zschemaRemarks;
    }
    
    public String getZschemaNames(){
        return zschemaNames;
    }
    
    public String getZschemaKind(){
        return zschemaKind;
    }
    
    public String getZschemaLabel(){
        return zschemaLabel;
    }
    
    public String getZschemaSubNames(){
        return zschemaSubNames;
    }
    
    public String getZschemaHerf(){
        return zschemaHerf;
    }
    
    public String getZschemaAlt(){
        return zschemaAlt;
    }
    
    public Integer getZschemaHeight(){
        return zschemaHeight;
    }
    
    public String getZschemaOwner(){
        return zschemaOwner;
    }
    
    public String getObid(){
        return obid;
    }
    
    public String getFromObidList(){
        return fromObidList;
    }
    
    public String getToObidList(){
        return toObidList;
    }
    
    public String getRelationObidList(){
        return relationObidList;
    }
    
    public void setZschemaSequence(Integer zschemaSequence){
        this.zschemaSequence = zschemaSequence;
    }
    
    public void setZschemaComments(String zschemaComments){
        this.zschemaComments = zschemaComments;
    }
    
    public void setZschemaRemarks(String zschemaRemarks){
        this.zschemaRemarks = zschemaRemarks;
    }
    
    public void setZschemaNames(String zschemaNames){
        this.zschemaNames = zschemaNames;
    }
    
    public void setZschemaKind(String zschemaKind){
        this.zschemaKind = zschemaKind;
    }
    
    public void setZschemaLabel(String zschemaLabel){
        this.zschemaLabel = zschemaLabel;
    }
    
    public void setZschemaSubNames(String zschemaSubNames){
        this.zschemaSubNames = zschemaSubNames;
    }
    
    public void setZschemaHerf(String zschemaHerf){
        this.zschemaHerf = zschemaHerf;
    }
    
    public void setZschemaAlt(String zschemaAlt){
        this.zschemaAlt = zschemaAlt;
    }
    
    public void setZschemaHeight(Integer zschemaHeight){
        this.zschemaHeight = zschemaHeight;
    }
    
    public void setZschemaOwner(String zschemaOwner){
        this.zschemaOwner = zschemaOwner;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    
    public void setFromObidList(String fromObidList){
        this.fromObidList = fromObidList;
    }
    
    public void setToObidList(String toObidList){
        this.toObidList = toObidList;
    }
    
    public void setRelationObidList(String relationObidList){
        this.relationObidList = relationObidList;
    }
    
    
}
