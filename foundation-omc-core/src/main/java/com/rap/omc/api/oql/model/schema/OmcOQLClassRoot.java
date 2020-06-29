/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcOQLClass.java
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
 * Class : OmcOQLClass
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLClassRoot extends OmcOQLRoot {
    private String className;
    private int flags;
    private int classInfoFlags;
    private String classNameParent;
    private String dbmsTable;
    private String javaPackage;
    private String displayedName;
    private List<String> childClassList;
    private List<String> parentClassList;
    private List<OmcOQLRelationShipInfo> relationShipInfoList;
    
    /**
     * 
     */
    public OmcOQLClassRoot() {
        super();
    }

    
    /**
     * @param className
     * @param flags
     * @param classInfoFlags
     * @param classNameParent
     * @param dbmsTable
     * @param javaPackage
     * @param displayedName
     * @param childClassList
     * @param parentClassList
     * @param relationShipInfoList
     */
    public OmcOQLClassRoot(String className, int flags, int classInfoFlags, String classNameParent, String dbmsTable,
            String javaPackage, String displayedName, List<String> childClassList, List<String> parentClassList,
            List<OmcOQLRelationShipInfo> relationShipInfoList) {
        super();
        this.className = className;
        this.flags = flags;
        this.classInfoFlags = classInfoFlags;
        this.classNameParent = classNameParent;
        this.dbmsTable = dbmsTable;
        this.javaPackage = javaPackage;
        this.displayedName = displayedName;
        this.childClassList = childClassList;
        this.parentClassList = parentClassList;
        this.relationShipInfoList = relationShipInfoList;
    }
    public int getClassInfoFlags(){
        return classInfoFlags;
    }
    public void setClassInfoFlags(int classInfoFlags){
        this.classInfoFlags = classInfoFlags;
    }
    public boolean isRootObject()
    {
        if(this.getClassName().equals("ObjectRoot")) return(true);
        return(false);
    }
    public boolean isNotFoundationObject()
    {
        if(this.getClassName().equals("BusinessObjectRoot"))     return(false);
        if(this.getClassName().equals("BusinessObject"))         return(false);
        if(this.getClassName().equals("BusinessObjectMaster"))   return(false);
        if(this.getClassName().equals("BusinessRelationObject")) return(false);
        return(true);
    }
    public boolean isFoundationObject()
    {
        return(!this.isNotFoundationObject());
    }
    public boolean isBusinessClass()
    {
        if(this.isRootObject()) return(true);
        if((this.getFlags() & OmcSystemConstants.CLASSINFO_FLAG_Relation) == OmcSystemConstants.CLASSINFO_FLAG_Relation) return(false);
        return(true);
    }
    public boolean isRelationClass()
    {
        if(this.isRootObject()) return(true);
        return(!this.isBusinessClass());
    }
    public boolean isInstantiable()
    {
        if(this.isNotFoundationObject()) return(false);
        if((this.getFlags() & OmcSystemConstants.CLASSINFO_FLAG_Instantiable) == OmcSystemConstants.CLASSINFO_FLAG_Instantiable) return(true);
        return(false);
    }
    public boolean isActive()
    {
        if(this.isNotFoundationObject()) return(true);
        if(isBusinessClass() && (this.getClassInfoFlags() & OmcSystemConstants.BUSINESS_FLAG_Active) == OmcSystemConstants.BUSINESS_FLAG_Active) return(true);
        if(isRelationClass() && (this.getClassInfoFlags() & OmcSystemConstants.RELATION_FLAG_Active) == OmcSystemConstants.RELATION_FLAG_Active) return(true);
        return(false);
    }
    public List<OmcOQLRelationShipInfo> getRelationShipInfoList(){
        return relationShipInfoList;
    }
    public void setRelationShipInfoList(List<OmcOQLRelationShipInfo> relationShipInfoList){
        this.relationShipInfoList = relationShipInfoList;
    }

    public int getFlags(){
        return flags;
    }

    public void setFlags(int flags){
        this.flags = flags;
    }
    public List<String> getChildClassList(){
        return childClassList;
    }
    public List<String> getParentClassList(){
        return parentClassList;
    }
    public void setChildClassList(List<String> childClassList){
        this.childClassList = childClassList;
    }
    public void setParentClassList(List<String> parentClassList){
        this.parentClassList = parentClassList;
    }

    public String getClassName(){
        return className;
    }
    
    public String getClassNameParent(){
        return classNameParent;
    }
    public String getDbmsTable(){
        return dbmsTable;
    }
    public String getJavaPackage(){
        return javaPackage;
    }
    
    public String getDisplayedName(){
        return displayedName;
    }
    public void setClassName(String className){
        this.className = className;
    }
    public void setClassNameParent(String classNameParent){
        this.classNameParent = classNameParent;
    }
    
    public void setDbmsTable(String dbmsTable){
        this.dbmsTable = dbmsTable;
    }
    
    public void setJavaPackage(String javaPackage){
        this.javaPackage = javaPackage;
    }
    
    public void setDisplayedName(String displayedName){
        this.displayedName = displayedName;
    }
}
