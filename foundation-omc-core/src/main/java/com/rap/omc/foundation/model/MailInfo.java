/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : MailInfo.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 3. 4. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.foundation.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.rap.omc.util.NullUtil;



/**
 * <pre>
 * Class : MailInfo
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class MailInfo {

    private String subject;

    private String fromMailAddress;

    private List<String> toMailAddressList;

    private boolean textType = true;

    private String body;

    private String htmlTemplateName;

    private Map<String, Object> attributeMap;

    private List<String> fileNameList;

    
    public MailInfo() {
        super();        
    }
    
    /**
     * @param subject
     * @param fromMailAddress
     * @param body
     */
    public MailInfo(String subject, String fromMailAddress, String toMailAddress, String body) {
        super();
        this.subject = subject;
        this.fromMailAddress = fromMailAddress;        
        this.body = body;
        this.addToMailAddress(toMailAddress);
    }

    /**
     * 
     * 
     * @return the fromMailAddress
     */
    public String getFromMailAddress(){
        return fromMailAddress;
    }

    /**
     * 
     * 
     * @param fromMailAddress the fromMailAddress to set
     */
    public void setFromMailAddress(String fromMailAddress){
        this.fromMailAddress = fromMailAddress;
    }

    /**
     * 
     * 
     * @return the toMailAddressList
     */
    public List<String> getToMailAddressList(){
        return toMailAddressList;
    }

    /**
     * 
     * 
     * @param toMailAddressList the toMailAddressList to set
     */
    public void setToMailAddressList(List<String> toMailAddressList){
        this.toMailAddressList = toMailAddressList;
    }

    private void initToMailAddressList(){
        if (NullUtil.isNone(this.toMailAddressList)) {
            this.toMailAddressList = new ArrayList<String>();
        }
    }

    public String[] getToMailAddressArray(){
        return toMailAddressList.toArray(new String[toMailAddressList.size()]);
    }

    public void addToMailAddress(String address){
        initToMailAddressList();

        this.toMailAddressList.add(address);
    }

    public void addToMailAddresses(String[] address){
        initToMailAddressList();
        for (String element : address) {
            this.toMailAddressList.add(element);
        }
    }

    public void addToMailAddresses(Collection<String> addresses){
        initToMailAddressList();
        this.toMailAddressList.addAll(addresses);
    }

    /**
     * 
     * 
     * @return the subject
     */
    public String getSubject(){
        return subject;
    }

    /**
     * 
     * 
     * @param subject the subject to set
     */
    public void setSubject(String subject){
        this.subject = subject;
    }

    /**
     * 
     * 
     * @return the textType
     */
    public boolean isTextType(){
        return textType;
    }

    /**
     * 
     * 
     * @param textType the textType to set
     */
    public void setTextType(boolean textType){
        this.textType = textType;
    }

    /**
     * 
     * 
     * @return the body
     */
    public String getBody(){
        return body;
    }

    /**
     * 
     * 
     * @param body the body to set
     */
    public void setBody(String body){
        this.body = body;
    }

    /**
     * 
     * 
     * @return the htmlTemplateName
     */
    public String getHtmlTemplateName(){
        return htmlTemplateName;
    }

    /**
     * 
     * 
     * @param htmlTemplateName the htmlTemplateName to set
     */
    public void setHtmlTemplateName(String htmlTemplateName){
        this.htmlTemplateName = htmlTemplateName;
    }

    /**
     * 
     * 
     * @return the attributeMap
     */
    public Map<String, Object> getAttributeMap(){
        return attributeMap;
    }

    /**
     * 
     * 
     * @param attributeMap the attributeMap to set
     */
    public void setAttributeMap(Map<String, Object> attributeMap){
        this.attributeMap = attributeMap;
    }

    /**
     * 
     * 
     * @return the fileNameList
     */
    public List<String> getFileNameList(){
        return fileNameList;
    }

    public String[] getFileNameArray(){
        return fileNameList.toArray(new String[fileNameList.size()]);
    }

    /**
     * 
     * 
     * @param fileNameList the fileNameList to set
     */
    public void setFileNameList(List<String> fileNameList){
        this.fileNameList = fileNameList;
    }

    public void setFileNameList(String[] fileNames){
        initFileNameList();
        for (String element : fileNames) {
            this.fileNameList.add(element);
        }
    }

    public void setFileNameList(String fileName){
        initFileNameList();
        this.fileNameList.add(fileName);
    }

    private void initFileNameList(){
        if (NullUtil.isNone(this.fileNameList)) {
            this.fileNameList = new ArrayList<String>();
        }
    }
}
