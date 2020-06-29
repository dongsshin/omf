/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ApprovalLineExcelImportVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 7. 22.  youngmi.won   Initial
 * ===========================================
 */
package rap.application.workflow.model;

/**
 * <pre>
 * Class : ApprovalLineExcelImportVO
 * Description : Excel Import 데이터를 담는 Value Object
 * </pre>
 * 
 * @author youngmi.won
 */
public class ApprovalLineExcelImportVO {
    private String manageType;
    private String plantName;
    private String userId;
    private String approvalLineId;
    private String approvalLineName;
    private String appliedType;
    private String appliedPolicy;
    private boolean isDefault = false;
    private String assigneeId;
    private int order;
    private String routeState;
    private String step;
    private int sequences;
    private String requiredAction;
    private boolean isEssential = false;

    public String getManageType() {
        return manageType;
    }
    

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApprovalLineId() {
        return approvalLineId;
    }

    public void setApprovalLineId(String approvalLineId) {
        this.approvalLineId = approvalLineId;
    }

    public String getApprovalLineName() {
        return approvalLineName;
    }

    public void setApprovalLineName(String approvalLineName) {
        this.approvalLineName = approvalLineName;
    }

    public String getAppliedType() {
        return appliedType;
    }

    public void setAppliedType(String appliedType) {
        this.appliedType = appliedType;
    }

    public String getAppliedPolicy() {
        return appliedPolicy;
    }

    public void setAppliedPolicy(String appliedPolicy) {
        this.appliedPolicy = appliedPolicy;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getRouteState() {
        return routeState;
    }

    public void setRouteState(String routeState) {
        this.routeState = routeState;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public int getSequences() {
        return sequences;
    }

    public void setSequences(int sequences) {
        this.sequences = sequences;
    }

    public String getRequiredAction() {
        return requiredAction;
    }

    public void setRequiredAction(String requiredAction) {
        this.requiredAction = requiredAction;
    }

    public boolean isEssential() {
        return isEssential;
    }

    public void setEssential(boolean isEssential) {
        this.isEssential = isEssential;
    }
}