/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ECOEssentialApproverItemExcelImportVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 9. 22.  youngmi.won   Initial
 * ===========================================
 */
package rap.application.workflow.model;

/**
 * <pre>
 * Class : ECOEssentialApproverItemExcelImportVO
 * Description : Excel Import 데이터를 담는 Value Object
 * </pre>
 * 
 * @author youngmi.won
 */
public class ECOEssentialApproverItemExcelImportVO {
    private String site;
    private String productGroup;
    private String workGroup;
    private String state;
    private String step;
    private String requiredAction;
    private String userId;
    private String  approverRole;
    private Boolean isEssential = false;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getWorkGroup() {
        return workGroup;
    }

    public void setWorkGroup(String workGroup) {
        this.workGroup = workGroup;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getRequiredAction() {
        return requiredAction;
    }

    public void setRequiredAction(String requiredAction) {
        this.requiredAction = requiredAction;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApproverRole() {
        return approverRole;
    }

    public void setApproverRole(String approverRole) {
        this.approverRole = approverRole;
    }

    public Boolean getIsEssential() {
        return isEssential;
    }

    public void setIsEssential(Boolean isEssential) {
        this.isEssential = isEssential;
    }
}