package com.rap.mail.model;

import java.util.List;

import rap.api.object.organization.model.UsersVO;

public class MailVO {
    private UsersVO fromUserVO;
    private String subject;
    private List<String> toEmailList;   //수신자
    private List<String> ccEmailList;   //참조자
    private String mailContents;

    public UsersVO getFromUserVO() {
        return fromUserVO;
        
    }

    public void setFromUserVO(UsersVO fromUserVO) {
        this.fromUserVO = fromUserVO;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getToEmailList() {
        return toEmailList;
    }

    public void setToEmailList(List<String> toEmailList) {
        this.toEmailList = toEmailList;
    }

    public String getMailContents() {
        return mailContents;
    }

    public void setMailContents(String mailContents) {
        this.mailContents = mailContents;
    }

    public List<String> getCcEmailList(){
        return ccEmailList;
    }

    public void setCcEmailList(List<String> ccEmailList){
        this.ccEmailList = ccEmailList;
    }
}
