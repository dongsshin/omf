/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SchemaObjectRoot.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 7. 5.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.temp.model;


/**
 * <pre>
 * Class : SchemaObjectRoot
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class SchemaClassRootVO {
    private Integer zschemaSequence                    ;
    private String  zschemaComments                    ;
    private String  zschemaNames                       ;
    private String  zschemaParent                      ;
    private String  zschemaParentObid                  ;
    private String  zschemaDbmsTable                   ;
    private String  zschemaInstantiable                ;
    private String  zschemaPackagePath                 ;
    private String  zschemaMainModule                  ;
    private String  zschemaRemarks                     ;
    private String  zschemaOwner                       ;
    private String  zschemaIsDeleted                   ;
    private String  obid                               ;
    private Integer zschemaClassLevel                  ;
    private String  zschemaClassPath                   ;
    private String  contextMenuFlags                   ;
    
    public Integer getZschemaSequence(){
        return zschemaSequence;
    }
    
    public String getZschemaComments(){
        return zschemaComments;
    }
    
    public String getZschemaNames(){
        return zschemaNames;
    }
    
    public String getZschemaParent(){
        return zschemaParent;
    }
    
    public String getZschemaParentObid(){
        return zschemaParentObid;
    }
    
    public String getZschemaDbmsTable(){
        return zschemaDbmsTable;
    }
    
    public String getZschemaInstantiable(){
        return zschemaInstantiable;
    }
    
    public String getZschemaPackagePath(){
        return zschemaPackagePath;
    }
    
    public String getZschemaMainModule(){
        return zschemaMainModule;
    }
    
    public String getZschemaRemarks(){
        return zschemaRemarks;
    }
    
    public String getZschemaOwner(){
        return zschemaOwner;
    }
    
    public String getZschemaIsDeleted(){
        return zschemaIsDeleted;
    }
    
    public String getObid(){
        return obid;
    }
    
    public Integer getZschemaClassLevel(){
        return zschemaClassLevel;
    }
    
    public String getZschemaClassPath(){
        return zschemaClassPath;
    }
    
    public String getContextMenuFlags(){
        return contextMenuFlags;
    }
    
    public void setZschemaSequence(Integer zschemaSequence){
        this.zschemaSequence = zschemaSequence;
    }
    
    public void setZschemaComments(String zschemaComments){
        this.zschemaComments = zschemaComments;
    }
    
    public void setZschemaNames(String zschemaNames){
        this.zschemaNames = zschemaNames;
    }
    
    public void setZschemaParent(String zschemaParent){
        this.zschemaParent = zschemaParent;
    }
    
    public void setZschemaParentObid(String zschemaParentObid){
        this.zschemaParentObid = zschemaParentObid;
    }
    
    public void setZschemaDbmsTable(String zschemaDbmsTable){
        this.zschemaDbmsTable = zschemaDbmsTable;
    }
    
    public void setZschemaInstantiable(String zschemaInstantiable){
        this.zschemaInstantiable = zschemaInstantiable;
    }
    
    public void setZschemaPackagePath(String zschemaPackagePath){
        this.zschemaPackagePath = zschemaPackagePath;
    }
    
    public void setZschemaMainModule(String zschemaMainModule){
        this.zschemaMainModule = zschemaMainModule;
    }
    
    public void setZschemaRemarks(String zschemaRemarks){
        this.zschemaRemarks = zschemaRemarks;
    }
    
    public void setZschemaOwner(String zschemaOwner){
        this.zschemaOwner = zschemaOwner;
    }
    
    public void setZschemaIsDeleted(String zschemaIsDeleted){
        this.zschemaIsDeleted = zschemaIsDeleted;
    }
    
    public void setObid(String obid){
        this.obid = obid;
    }
    public void setZschemaClassLevel(Integer zschemaClassLevel){
        this.zschemaClassLevel = zschemaClassLevel;
    }
    
    public void setZschemaClassPath(String zschemaClassPath){
        this.zschemaClassPath = zschemaClassPath;
    }
    
    public void setContextMenuFlags(String contextMenuFlags){
        this.contextMenuFlags = contextMenuFlags;
    }

    @Override
    public String toString(){
        return "SchemaBusinessClassRootVO [zschemaSequence=" + zschemaSequence + ", zschemaComments=" + zschemaComments
                + ", zschemaNames=" + zschemaNames + ", zschemaParent=" + zschemaParent + ", zschemaParentObid="
                + zschemaParentObid + ", zschemaDbmsTable=" + zschemaDbmsTable + ", zschemaInstantiable="
                + zschemaInstantiable + ", zschemaPackagePath=" + zschemaPackagePath + ", zschemaMainModule="
                + zschemaMainModule + ", zschemaRemarks=" + zschemaRemarks + ", zschemaOwner=" + zschemaOwner
                + ", zschemaIsDeleted=" + zschemaIsDeleted + ", obid=" + obid + ", zschemaClassLevel="
                + zschemaClassLevel + ", zschemaClassPath=" + zschemaClassPath + ", contextMenuFlags="
                + contextMenuFlags + "]";
    }
}
