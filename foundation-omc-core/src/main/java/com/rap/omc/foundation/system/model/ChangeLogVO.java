/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ChangeLogVO.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 4. 15. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.system.model;

import java.util.Date;

/**
 * <pre>
 * Class : ChangeLogVO
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class ChangeLogVO {

    private String changingCategory;

    private String targetObid;

    private String targetNames;

    private String targetClass;

    private String changingUser;

    private Date changedDate;

    private String attribute01;

    private String attribute02;

    private String attribute03;

    private String attribute04;

    private String attribute05;

    private String creator;

    private Date created;

    private String modifier;

    private Date modified;

    private Date startChangedDate;

    private Date endChangedDate;

    /**
     * 
     * 
     * @return the changingCategory
     */
    public String getChangingCategory(){
        return changingCategory;
    }

    /**
     * 
     * 
     * @param changingCategory the changingCategory to set
     */
    public void setChangingCategory(String changingCategory){
        this.changingCategory = changingCategory;
    }

    /**
     * 
     * 
     * @return the targetObid
     */
    public String getTargetObid(){
        return targetObid;
    }

    /**
     * 
     * 
     * @param targetObid the targetObid to set
     */
    public void setTargetObid(String targetObid){
        this.targetObid = targetObid;
    }

    /**
     * 
     * 
     * @return the targetNames
     */
    public String getTargetNames(){
        return targetNames;
    }

    /**
     * 
     * 
     * @param targetNames the targetNames to set
     */
    public void setTargetNames(String targetNames){
        this.targetNames = targetNames;
    }

    /**
     * 
     * 
     * @return the targetClass
     */
    public String getTargetClass(){
        return targetClass;
    }

    /**
     * 
     * 
     * @param targetClass the targetClass to set
     */
    public void setTargetClass(String targetClass){
        this.targetClass = targetClass;
    }

    /**
     * 
     * 
     * @return the changingUser
     */
    public String getChangingUser(){
        return changingUser;
    }

    /**
     * 
     * 
     * @param changingUser the changingUser to set
     */
    public void setChangingUser(String changingUser){
        this.changingUser = changingUser;
    }

    /**
     * 
     * 
     * @return the changedDate
     */
    public Date getChangedDate(){
        return changedDate;
    }

    /**
     * 
     * 
     * @param changedDate the changedDate to set
     */
    public void setChangedDate(Date changedDate){
        this.changedDate = changedDate;
    }

    /**
     * 
     * 
     * @return the attribute01
     */
    public String getAttribute01(){
        return attribute01;
    }

    /**
     * 
     * 
     * @param attribute01 the attribute01 to set
     */
    public void setAttribute01(String attribute01){
        this.attribute01 = attribute01;
    }

    /**
     * 
     * 
     * @return the attribute02
     */
    public String getAttribute02(){
        return attribute02;
    }

    /**
     * 
     * 
     * @param attribute02 the attribute02 to set
     */
    public void setAttribute02(String attribute02){
        this.attribute02 = attribute02;
    }

    /**
     * 
     * 
     * @return the attribute03
     */
    public String getAttribute03(){
        return attribute03;
    }

    /**
     * 
     * 
     * @param attribute03 the attribute03 to set
     */
    public void setAttribute03(String attribute03){
        this.attribute03 = attribute03;
    }

    /**
     * 
     * 
     * @return the attribute04
     */
    public String getAttribute04(){
        return attribute04;
    }

    /**
     * 
     * 
     * @param attribute04 the attribute04 to set
     */
    public void setAttribute04(String attribute04){
        this.attribute04 = attribute04;
    }

    /**
     * 
     * 
     * @return the attribute05
     */
    public String getAttribute05(){
        return attribute05;
    }

    /**
     * 
     * 
     * @param attribute05 the attribute05 to set
     */
    public void setAttribute05(String attribute05){
        this.attribute05 = attribute05;
    }

    /**
     * 
     * 
     * @return the creator
     */
    public String getCreator(){
        return creator;
    }

    /**
     * 
     * 
     * @param creator the creator to set
     */
    public void setCreator(String creator){
        this.creator = creator;
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
     * @return the modifier
     */
    public String getModifier(){
        return modifier;
    }

    /**
     * 
     * 
     * @param modifier the modifier to set
     */
    public void setModifier(String modifier){
        this.modifier = modifier;
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
     * @return the startChangedDate
     */
    public Date getStartChangedDate(){
        return startChangedDate;
    }

    /**
     * 
     * 
     * @param startChangedDate the startChangedDate to set
     */
    public void setStartChangedDate(Date startChangedDate){
        this.startChangedDate = startChangedDate;
    }

    /**
     * 
     * 
     * @return the endChangedDate
     */
    public Date getEndChangedDate(){
        return endChangedDate;
    }

    /**
     * 
     * 
     * @param endChangedDate the endChangedDate to set
     */
    public void setEndChangedDate(Date endChangedDate){
        this.endChangedDate = endChangedDate;
    }

    /**
     * 
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return "ChangeLogVO [changingCategory=" + changingCategory + ", targetObid=" + targetObid + ", targetNames="
                + targetNames + ", targetClass=" + targetClass + ", changingUser=" + changingUser + ", changedDate="
                + changedDate + ", attribute01=" + attribute01 + ", attribute02=" + attribute02 + ", attribute03="
                + attribute03 + ", attribute04=" + attribute04 + ", attribute05=" + attribute05 + ", creator="
                + creator + ", created=" + created + ", modifier=" + modifier + ", modified=" + modified
                + ", startChangedDate=" + startChangedDate + ", endChangedDate=" + endChangedDate + "]";
    }

}
