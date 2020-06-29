/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : PendingJobByRetirementSearchVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 12. 22.  kiyoung.jang   Initial
 * ===========================================
 */
package rap.application.workflow.model;

import java.util.Date;

import com.rap.omc.dataaccess.paging.model.PagingEntity;




/**
 * <pre>
 * Class : PendingJobByRetirementSearchVO
 * Description : TODO
 * </pre>
 * 
 * @author kiyoung.jang
 */
public class PendingJobByRetirementSearchVO extends PagingEntity {

    private String target;
    private String year;
    private String dummyYear;
    private String mm;
    private String dummyMM;
    private String personInfo;
    private String dummySpace;
    private String targetUserId;
    private String personName;
    private String businessUnitCode;
    private String className;
    
    private int rowSize;
    private int targetRow;    

    private String ownerName;
    private String department;
    private String names;
    private String states;
    private String revision;
    private String techDocumentTypeDesc;
    private String modelSuffix;
    private String creatorName;
    private Date modified;
    private Date created;
    private String obid;
    private String activeInactive;
    private String lifeCycle;
    
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
     * @return the businessUnitCode
     */
    public String getBusinessUnitCode(){
        return businessUnitCode;
    }
    
    /**
     * 
     * 
     * @param businessUnitCode the businessUnitCode to set
     */
    public void setBusinessUnitCode(String businessUnitCode){
        this.businessUnitCode = businessUnitCode;
    }

    /**
     * 
     * 
     * @return the target
     */
    public String getTarget(){
        return target;
    }
    
    /**
     * 
     * 
     * @param target the target to set
     */
    public void setTarget(String target){
        this.target = target;
    }
    
    /**
     * 
     * 
     * @return the year
     */
    public String getYear(){
        return year;
    }
    
    /**
     * 
     * 
     * @param year the year to set
     */
    public void setYear(String year){
        this.year = year;
    }
    
    /**
     * 
     * 
     * @return the dummyYear
     */
    public String getDummyYear(){
        return dummyYear;
    }
    
    /**
     * 
     * 
     * @param dummyYear the dummyYear to set
     */
    public void setDummyYear(String dummyYear){
        this.dummyYear = dummyYear;
    }
    
    /**
     * 
     * 
     * @return the mm
     */
    public String getMm(){
        return mm;
    }
    
    /**
     * 
     * 
     * @param mm the mm to set
     */
    public void setMm(String mm){
        this.mm = mm;
    }
    
    /**
     * 
     * 
     * @return the dummyMM
     */
    public String getDummyMM(){
        return dummyMM;
    }
    
    /**
     * 
     * 
     * @param dummyMM the dummyMM to set
     */
    public void setDummyMM(String dummyMM){
        this.dummyMM = dummyMM;
    }
    
    /**
     * 
     * 
     * @return the personInfo
     */
    public String getPersonInfo(){
        return personInfo;
    }
    
    /**
     * 
     * 
     * @param personInfo the personInfo to set
     */
    public void setPersonInfo(String personInfo){
        this.personInfo = personInfo;
    }
    
    /**
     * 
     * 
     * @return the dummySpace
     */
    public String getDummySpace(){
        return dummySpace;
    }
    
    /**
     * 
     * 
     * @param dummySpace the dummySpace to set
     */
    public void setDummySpace(String dummySpace){
        this.dummySpace = dummySpace;
    }
    
    /**
     * 
     * 
     * @return the targetUserId
     */
    public String getTargetUserId(){
        return targetUserId;
    }
    
    /**
     * 
     * 
     * @param targetUserId the targetUserId to set
     */
    public void setTargetUserId(String targetUserId){
        this.targetUserId = targetUserId;
    }
    
    /**
     * 
     * 
     * @return the personName
     */
    public String getPersonName(){
        return personName;
    }
    
    /**
     * 
     * 
     * @param personName the personName to set
     */
    public void setPersonName(String personName){
        this.personName = personName;
    }


    public int getRowSize() {
        return rowSize < 10 ? 20 : rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getTargetRow() {
        return targetRow;
    }

    public void setTargetRow(int targetRow) {
        this.targetRow = targetRow;
    }

    
    /**
     * 
     * 
     * @return the ownerName
     */
    public String getOwnerName(){
        return ownerName;
    }

    
    /**
     * 
     * 
     * @param ownerName the ownerName to set
     */
    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }

    
    /**
     * 
     * 
     * @return the department
     */
    public String getDepartment(){
        return department;
    }

    
    /**
     * 
     * 
     * @param department the department to set
     */
    public void setDepartment(String department){
        this.department = department;
    }

    
    /**
     * 
     * 
     * @return the names
     */
    public String getNames(){
        return names;
    }

    
    /**
     * 
     * 
     * @param names the names to set
     */
    public void setNames(String names){
        this.names = names;
    }

    
    /**
     * 
     * 
     * @return the states
     */
    public String getStates(){
        return states;
    }

    
    /**
     * 
     * 
     * @param states the states to set
     */
    public void setStates(String states){
        this.states = states;
    }

    
    /**
     * 
     * 
     * @return the revision
     */
    public String getRevision(){
        return revision;
    }

    
    /**
     * 
     * 
     * @param revision the revision to set
     */
    public void setRevision(String revision){
        this.revision = revision;
    }

    
    /**
     * 
     * 
     * @return the techDocumentTypeDesc
     */
    public String getTechDocumentTypeDesc(){
        return techDocumentTypeDesc;
    }

    
    /**
     * 
     * 
     * @param techDocumentTypeDesc the techDocumentTypeDesc to set
     */
    public void setTechDocumentTypeDesc(String techDocumentTypeDesc){
        this.techDocumentTypeDesc = techDocumentTypeDesc;
    }

    
    /**
     * 
     * 
     * @return the modelSuffix
     */
    public String getModelSuffix(){
        return modelSuffix;
    }

    
    /**
     * 
     * 
     * @param modelSuffix the modelSuffix to set
     */
    public void setModelSuffix(String modelSuffix){
        this.modelSuffix = modelSuffix;
    }

    
    /**
     * 
     * 
     * @return the creatorName
     */
    public String getCreatorName(){
        return creatorName;
    }

    
    /**
     * 
     * 
     * @param creatorName the creatorName to set
     */
    public void setCreatorName(String creatorName){
        this.creatorName = creatorName;
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
     * @return the activeInactive
     */
    public String getActiveInactive(){
        return activeInactive;
    }
    
    /**
     * 
     * 
     * @param activeInactive the activeInactive to set
     */
    public void setActiveInactive(String activeInactive){
        this.activeInactive = activeInactive;
    }
    
    /**
     * 
     * 
     * @return the lifeCycle
     */
    public String getLifeCycle(){
        return lifeCycle;
    }
    
    /**
     * 
     * 
     * @param lifeCycle the lifeCycle to set
     */
    public void setLifeCycle(String lifeCycle){
        this.lifeCycle = lifeCycle;
    }
    


}
