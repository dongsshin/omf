/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : PendingJobByRetirementVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 12. 16.  kiyoung.jang   Initial
 * ===========================================
 */
package rap.application.workflow.model;

import rap.api.object.workflow.model.WorkflowInboxTaskVO;

/**
 * <pre>
 * Class : PendingJobByRetirementVO
 * Description : TODO
 * </pre>
 * 
 * @author kiyoung.jang
 */
public class PendingJobByRetirementVO extends WorkflowInboxTaskVO {

    private String target;
    private String year;
    private String dummyYear;
    private String mm;
    private String dummyMM;
    private String personInfo;
    private String dummySpace;
    private String targetUserId;
    private String personName;
    private int rowSize;
    private int targetRow;
    
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
    

}
