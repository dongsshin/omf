/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : MenuItem.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2015. 2. 2.  kwanghyui.choi   Initial
 * ===========================================
 */
package com.rap.omc.foundation.ui.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Class : MenuItemVO
 * Description : TODO
 * </pre>
 *
 * @author kwanghyui.choi
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class MenuItemVO extends DefaultUIItemVO{
    private String  mnuLevel = null;
    private String  mnuKindName = null;
    private String  mnuName = null;
    private String  mnuDescriptions = null;
    private String  mnuLabel = null;
    private String  mnuHref = null;
    private String  mnuAlt = null;
    private String  mnuImage = null;
    private String  mnuSort = null;
    private String  mnuModule = null;
    private String  mnuTarget = null;
    private String  windowWidth = null;
    private String  windowHeight = null;
    private String  controlProperties = null;
    //// { Append 11.13 by Inhan.
    private String  mnuPstring01 = null;
    private String  mnuPstring02 = null;
    private String  mnuPstring03 = null;
    private String  mnuPstring04 = null;
    private String  mnuPstring05 = null;
    private String  extraData    = null;

    

	private List<CheckboxItemVO> checkboxItems = new ArrayList();
    private List<RadioItemVO> radioItems = new ArrayList();
    //// { Append 11.13 by Inhan.

    // yspark 2015.03.10
    private String  callingType = null;
    public MenuItemVO() {

    }

    /**
     *
     *
     * @return the mnuLevel
     */
    public String getMnuLevel(){
        return mnuLevel;
    }


    /**
     *
     *
     * @return the mnuKindName
     */
    public String getMnuKindName(){
        return mnuKindName;
    }


    /**
     *
     *
     * @return the mnuName
     */
    public String getMnuName(){
        return mnuName;
    }


    /**
     *
     *
     * @return the mnuDescriptions
     */
    public String getMnuDescriptions(){
        return mnuDescriptions;
    }


    /**
     *
     *
     * @return the mnuLabel
     */
    public String getMnuLabel(){
        return mnuLabel;
    }



    /**
     *
     *
     * @return the mnuHref
     */
    public String getMnuHref(){
        return mnuHref;
    }


    /**
     *
     *
     * @return the mnuAlt
     */
    public String getMnuAlt(){
        return mnuAlt;
    }


    /**
     *
     *
     * @return the mnuImage
     */
    public String getMnuImage(){
        return mnuImage;
    }


    /**
     *
     *
     * @return the mnuSort
     */
    public String getMnuSort(){
        return mnuSort;
    }

    /**
     *
     *
     * @return the mnuModule
     */
    public String getMnuModule(){
        return mnuModule;
    }

    /**
     *
     *
     * @return the mnuTarget
     */
    public String getMnuTarget(){
        return mnuTarget;
    }


    /**
     *
     *
     * @param mnuTarget the mnuTarget to set
     */
    public void setMnuTarget(String mnuTarget){
        this.mnuTarget = (null == mnuTarget || "null".equals(mnuTarget))?"": mnuTarget;
    }


    /**
     *
     *
     * @return the windowWidth
     */
    public String getWindowWidth(){
        return windowWidth;
    }


    /**
     *
     *
     * @param windowWidth the windowWidth to set
     */
    public void setWindowWidth(String windowWidth){
        this.windowWidth = (null == windowWidth || "null".equals(windowWidth))?"": windowWidth;
    }


    /**
     *
     *
     * @return the windowHeight
     */
    public String getWindowHeight(){
        return windowHeight;
    }

    /**
     *
     *
     * @return the callingType
     */
    public String getCallingType(){
        return callingType;
    }


    /**
     *
     *
     * @param windowHeight the windowHeight to set
     */
    public void setWindowHeight(String windowHeight){
        this.windowHeight = (null == windowHeight || "null".equals(windowHeight))?"": windowHeight;
    }

    /**
     *
     *
     * @param mnuLevel the mnuLevel to set
     */
    public void setMnuLevel(String mnuLevel){
        this.mnuLevel = mnuLevel;
    }


    /**
     *
     *
     * @param mnuKindName the mnuKindName to set
     */
    public void setMnuKindName(String mnuKindName){
        this.mnuKindName = mnuKindName;
    }


    /**
     *
     *
     * @param mnuName the mnuName to set
     */
    public void setMnuName(String mnuName){
        this.mnuName = mnuName;
    }


    /**
     *
     *
     * @param mnuDescriptions the mnuDescriptions to set
     */
    public void setMnuDescriptions(String mnuDescriptions){
        this.mnuDescriptions = (null == mnuDescriptions || "null".equals(mnuDescriptions))?"": mnuDescriptions;
    }


    /**
     *
     *
     * @param mnuLabel the mnuLabel to set
     */
    public void setMnuLabel(String mnuLabel){
        this.mnuLabel = mnuLabel;
    }


    /**
     *
     *
     * @param mnuHref the mnuHref to set
     */
    public void setMnuHref(String mnuHref){
        this.mnuHref = (null == mnuHref || "null".equals(mnuHref))?"": mnuHref;
    }


    /**
     *
     *
     * @param mnuAlt the mnuAlt to set
     */
    public void setMnuAlt(String mnuAlt){
        this.mnuAlt = (null == mnuAlt || "null".equals(mnuAlt))?"": mnuAlt;
    }


    /**
     *
     *
     * @param mnuImage the mnuImage to set
     */
    public void setMnuImage(String mnuImage){
        this.mnuImage = (null == mnuImage ||"null".equals(mnuImage))?"": mnuImage;
    }


    /**
     *
     *
     * @param mnuSort the mnuSort to set
     */
    public void setMnuSort(String mnuSort){
        this.mnuSort = mnuSort;
    }

    /**
     *
     *
     * @param mnuModule the mnuModule to set
     */
    public void setMnuModule(String mnuModule){
        this.mnuModule = (null == mnuModule || "null".equals(mnuModule))?"": mnuModule;
    }

    /**
     *
     *
     * @return the callingType
     */
    public void setCallingType( String callingType ){
        this.callingType = (null == callingType || "null".equals(callingType))?"": callingType;
    }

    /**
     *
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return "MenuItemVO [id=" + super.getId() + ", parentId=" + super.getParentId() + ", mnuLevel=" + mnuLevel
                + ", mnuKindName=" + mnuKindName + ", mnuName=" + mnuName + ", mnuDescriptions=" + mnuDescriptions
                + ", mnuLabel=" + mnuLabel + ", mnuHref=" + mnuHref + ", mnuAlt=" + mnuAlt + ", mnuImage=" + mnuImage
                + ", mnuSort=" + mnuSort + ", mnuClass=" + super.getMnuAccess() + ", mnuModule=" + mnuModule + "]";
    }

    /**
     *
     *
     * @return the controlProperties
     */
    public String getControlProperties(){
        return controlProperties;
    }

    /**
     *
     *
     * @param controlProperties the controlProperties to set
     */
    public void setControlProperties(String controlProperties){
        this.controlProperties = controlProperties;
    }

    public String getMnuPstring01() {
        return mnuPstring01;
    }

    public void setMnuPstring01(String mnuPstring01) {
        this.mnuPstring01 = mnuPstring01;
    }

    public String getMnuPstring02() {
        return mnuPstring02;
    }

    public void setMnuPstring02(String mnuPstring02) {
        this.mnuPstring02 = mnuPstring02;
    }

    public String getMnuPstring03() {
        return mnuPstring03;
    }

    public void setMnuPstring03(String mnuPstring03) {
        this.mnuPstring03 = mnuPstring03;
    }

    public String getMnuPstring04() {
        return mnuPstring04;
    }

    public void setMnuPstring04(String mnuPstring04) {
        this.mnuPstring04 = mnuPstring04;
    }

    public String getMnuPstring05() {
        return mnuPstring05;
    }

    public void setMnuPstring05(String mnuPstring05) {
        this.mnuPstring05 = mnuPstring05;
    }

    public List<CheckboxItemVO> getCheckboxItems() {
        return checkboxItems;
    }

    public void setCheckboxItems(List<CheckboxItemVO> checkboxItems) {
        this.checkboxItems = checkboxItems;
    }
    public void addCheckboxItems(CheckboxItemVO checkboxItemVo) {
        this.checkboxItems.add ( checkboxItemVo);
    }

    public List<RadioItemVO> getRadioItems() {
        return radioItems;
    }

    public void setRadioItems(List<RadioItemVO> radioItems) {
        this.radioItems = radioItems;
    }
    public void addRadioItems(RadioItemVO radioItemVo) {
        this.radioItems.add ( radioItemVo);
    }
    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

}
