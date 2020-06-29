/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SearchTargetInfo.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 9. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.common.model.search;

import java.util.List;

import com.rap.omc.api.object.model.BaseModel;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : SearchTargetInfo
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
@SuppressWarnings("serial")
public class SearchTargetInfo extends BaseModel {

    private String obid;

    private String className;

    private String fromTo;

    private String fromClassNames;

    private List<ClassInfo> fromClassList;

    private String toClassNames;

    private List<ClassInfo> toClassList;

    private boolean bFrom;

    private boolean bTo;

    private String newClassName;

    /**
     * 
     * 
     * @return the pclassName
     */
    public String getClassName(){
        return className;
    }

    /**
     * 
     * 
     * @param pclassName the pclassName to set
     */
    public void setClassName(String className){
        this.className = className;
    }

    /**
     * 
     * 
     * @return the fromTo
     */
    public String getFromTo(){
        return fromTo;
    }

    /**
     * 
     * 
     * @param fromTo the fromTo to set
     */
    public void setFromTo(String fromTo){
        this.fromTo = fromTo;
        if (fromTo.equals(GlobalConstants.FLAG_TYPE_FROM)) {
            this.bFrom = true;
            this.bTo = false;
        } else if (fromTo.equals(GlobalConstants.FLAG_TYPE_TO)) {
            this.bFrom = false;
            this.bTo = true;
        } else {
            this.bFrom = false;
            this.bTo = false;
        }
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
     * @return the bFrom
     */
    public boolean isbFrom(){
        return bFrom;
    }

    /**
     * 
     * 
     * @param bFrom the bFrom to set
     */
    public void setbFrom(boolean bFrom){
        this.bFrom = bFrom;
    }

    /**
     * 
     * 
     * @return the bTo
     */
    public boolean isbTo(){
        return bTo;
    }

    /**
     * 
     * 
     * @param bTo the bTo to set
     */
    public void setbTo(boolean bTo){
        this.bTo = bTo;
    }

    /**
     * 
     * 
     * @return the fromClassName
     */
    public String getFromClassNames(){
        return fromClassNames;
    }

    /**
     * 
     * 
     * @param fromClassName the fromClassName to set
     */
    public void setFromClassNames(String fromClassName){
        this.fromClassNames = fromClassName;
    }

    /**
     * 
     * 
     * @return the toClassName
     */
    public String getToClassNames(){
        return toClassNames;
    }

    /**
     * 
     * 
     * @param toClassName the toClassName to set
     */
    public void setToClassNames(String toClassName){
        this.toClassNames = toClassName;
    }

    /**
     * 
     * 
     * @return the fromClassList
     */
    public List<ClassInfo> getFromClassList(){
        return fromClassList;
    }

    /**
     * 
     * 
     * @param fromClassList the fromClassList to set
     */
    public void setFromClassList(List<ClassInfo> fromClassList){
        this.fromClassList = fromClassList;
        this.fromClassNames = getStrFromClassList();
    }

    public String getStrFromClassList(){
        String strClassList = null;
        if (!NullUtil.isNone(fromClassList)) {
            StringBuffer sb = new StringBuffer();
            for (ClassInfo classInfo : fromClassList) {
                sb.append("'").append(classInfo.getClassName()).append("',");
            }
            strClassList = sb.toString();
            strClassList = strClassList.substring(0, strClassList.length() - 1);
        }
        return strClassList;
    }

    /**
     * 
     * 
     * @return the toClassList
     */
    public List<ClassInfo> getToClassList(){
        return toClassList;
    }

    /**
     * 
     * 
     * @param toClassList the toClassList to set
     */
    public void setToClassList(List<ClassInfo> toClassList){
        this.toClassList = toClassList;
        this.toClassNames = getStrToClassList();
    }

    public String getStrToClassList(){
        String strClassList = null;
        if (!NullUtil.isNone(toClassList)) {
            StringBuffer sb = new StringBuffer();
            for (ClassInfo classInfo : toClassList) {
                sb.append("'").append(classInfo.getClassName()).append("',");
            }
            strClassList = sb.toString();
            strClassList = strClassList.substring(0, strClassList.length() - 1);
        }
        return strClassList;
    }

    /**
     * 
     * 
     * @return the newClassName
     */
    public String getNewClassName(){
        return newClassName;
    }

    /**
     * 
     * 
     * @param newClassName the newClassName to set
     */
    public void setNewClassName(String newClassName){
        this.newClassName = newClassName;
    }

}
