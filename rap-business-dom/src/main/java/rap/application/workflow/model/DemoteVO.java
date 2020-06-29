/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : DemoteVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 5. 12.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.model;


/**
 * <pre>
 * Class : DemoteVO
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class DemoteVO {
    private String obid;
    private String comments;
    
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
     * @return the comments
     */
    public String getComments(){
        return comments;
    }
    
    /**
     * 
     * 
     * @param comments the comments to set
     */
    public void setComments(String comments){
        this.comments = comments;
    }
    
    
}
