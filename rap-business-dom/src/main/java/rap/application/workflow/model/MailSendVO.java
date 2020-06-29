/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : MailSendVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 6. 3.  youngmi.won   Initial
 * ===========================================
 */
package rap.application.workflow.model;

import java.util.List;

/**
 * <pre>
 * Class : MailSendVO
 * Description : TODO
 * </pre>
 * 
 * @author youngmi.won
 */
public class MailSendVO {
    /**
     * sendType : 아래 상수 사용
     * 1) Distribution : GpdmConstants.MAIL_TYPE_DISTRIBUTION
     * 2) Reject : GpdmConstants.MAIL_TYPE_REJECT
     */
    private String sendType;
    private String obid;
    private String className;
    private String names;
    private String fromUserId;
    private List<String> toUserIdList;
    private String currentStatus;

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getObid() {
        return obid;
    }

    public void setObid(String obid) {
        this.obid = obid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public List<String> getToUserIdList() {
        return toUserIdList;
    }

    public void setToUserIdList(List<String> toUserIdList) {
        this.toUserIdList = toUserIdList;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}