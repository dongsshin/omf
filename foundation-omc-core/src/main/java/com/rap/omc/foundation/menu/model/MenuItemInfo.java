/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : MenuItemVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 4. 24.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.foundation.menu.model;

import java.util.List;

import com.rap.omc.foundation.common.model.ModelRootVO;




/**
 * <pre>
 * Class : MenuItemVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class MenuItemInfo extends ModelRootVO{
    private String  obid                               ;
    private int     mnuLevel                           ;
    private String  uniqueStrParent                    ;
    private String  uniqueStr                          ;
    private long    mnuFlags                           ;
    private long    mnuKind                            ;
    private String  mnuKindName                        ;
    private String  mnuName                            ;
    private String  mnuCallingType                     ;
    private String  mnuDescriptions                    ;
    private String  mnuLabel                           ;
    private String  mnuLabelKr                         ;
    private String  mnuHref                            ;
    private String  mnuAlt                             ;
    private String  mnuImage                           ;
    private int     mnuSort                            = 0;
    private String  mnuAccess                          ;
    private String  mnuAccessExpression                ;
    private String  mnuResolvedExpression              ;
    private String  mnuModule                          ;
    private String  mnuPstring01                       ;
    private String  mnuPstring02                       ;
    private String  mnuPstring03                       ;
    private String  mnuPstring04                       ;
    private String  mnuPstring05                       ;
    private long    treeFlags                          ;
    private String  treeAttribute01                    ;
    private String  treeAttribute02                    ;
    private String  classIcon                          ;
    private String  classIconSmall                     ;
    private String  extraData                          ;
    
    private String  mnuTarget                          ;
    private String  windowWidth                        ;
    private String  windowHeight                       ;
    private String  controlProperties                  ;
    
    private List<MenuSubMenuInfo> childItemList;

    
    public String getObid(){
        return obid;
    }

    
    public int getMnuLevel(){
        return mnuLevel;
    }

    
    public String getUniqueStrParent(){
        return uniqueStrParent;
    }

    
    public String getUniqueStr(){
        return uniqueStr;
    }

    
    public long getMnuFlags(){
        return mnuFlags;
    }

    
    public long getMnuKind(){
        return mnuKind;
    }

    
    public String getMnuKindName(){
        return mnuKindName;
    }

    
    public String getMnuName(){
        return mnuName;
    }

    
    public String getMnuCallingType(){
        return mnuCallingType;
    }

    
    public String getMnuDescriptions(){
        return mnuDescriptions;
    }

    
    public String getMnuLabel(){
        return mnuLabel;
    }

    
    public String getMnuLabelKr(){
        return mnuLabelKr;
    }

    
    public String getMnuHref(){
        return mnuHref;
    }

    
    public String getMnuAlt(){
        return mnuAlt;
    }

    
    public String getMnuImage(){
        return mnuImage;
    }

    
    public int getMnuSort(){
        return mnuSort;
    }

    
    public String getMnuAccess(){
        return mnuAccess;
    }

    
    public String getMnuAccessExpression(){
        return mnuAccessExpression;
    }

    
    public String getMnuResolvedExpression(){
        return mnuResolvedExpression;
    }

    
    public String getMnuModule(){
        return mnuModule;
    }

    
    public String getMnuPstring01(){
        return mnuPstring01;
    }

    
    public String getMnuPstring02(){
        return mnuPstring02;
    }

    
    public String getMnuPstring03(){
        return mnuPstring03;
    }

    
    public String getMnuPstring04(){
        return mnuPstring04;
    }

    
    public String getMnuPstring05(){
        return mnuPstring05;
    }

    
    public long getTreeFlags(){
        return treeFlags;
    }

    
    public String getTreeAttribute01(){
        return treeAttribute01;
    }

    
    public String getTreeAttribute02(){
        return treeAttribute02;
    }

    
    public String getClassIcon(){
        return classIcon;
    }

    
    public String getClassIconSmall(){
        return classIconSmall;
    }

    
    public String getExtraData(){
        return extraData;
    }

    
    public String getMnuTarget(){
        return mnuTarget;
    }

    
    public String getWindowWidth(){
        return windowWidth;
    }

    
    public String getWindowHeight(){
        return windowHeight;
    }

    
    public String getControlProperties(){
        return controlProperties;
    }

    
    public List<MenuSubMenuInfo> getChildItemList(){
        return childItemList;
    }

    
    public void setObid(String obid){
        this.obid = obid;
    }

    
    public void setMnuLevel(int mnuLevel){
        this.mnuLevel = mnuLevel;
    }

    
    public void setUniqueStrParent(String uniqueStrParent){
        this.uniqueStrParent = uniqueStrParent;
    }

    
    public void setUniqueStr(String uniqueStr){
        this.uniqueStr = uniqueStr;
    }

    
    public void setMnuFlags(long mnuFlags){
        this.mnuFlags = mnuFlags;
    }

    
    public void setMnuKind(long mnuKind){
        this.mnuKind = mnuKind;
    }

    
    public void setMnuKindName(String mnuKindName){
        this.mnuKindName = mnuKindName;
    }

    
    public void setMnuName(String mnuName){
        this.mnuName = mnuName;
    }

    
    public void setMnuCallingType(String mnuCallingType){
        this.mnuCallingType = mnuCallingType;
    }

    
    public void setMnuDescriptions(String mnuDescriptions){
        this.mnuDescriptions = mnuDescriptions;
    }

    
    public void setMnuLabel(String mnuLabel){
        this.mnuLabel = mnuLabel;
    }

    
    public void setMnuLabelKr(String mnuLabelKr){
        this.mnuLabelKr = mnuLabelKr;
    }

    
    public void setMnuHref(String mnuHref){
        this.mnuHref = mnuHref;
    }

    
    public void setMnuAlt(String mnuAlt){
        this.mnuAlt = mnuAlt;
    }

    
    public void setMnuImage(String mnuImage){
        this.mnuImage = mnuImage;
    }

    
    public void setMnuSort(int mnuSort){
        this.mnuSort = mnuSort;
    }

    
    public void setMnuAccess(String mnuAccess){
        this.mnuAccess = mnuAccess;
    }

    
    public void setMnuAccessExpression(String mnuAccessExpression){
        this.mnuAccessExpression = mnuAccessExpression;
    }

    
    public void setMnuResolvedExpression(String mnuResolvedExpression){
        this.mnuResolvedExpression = mnuResolvedExpression;
    }

    
    public void setMnuModule(String mnuModule){
        this.mnuModule = mnuModule;
    }

    
    public void setMnuPstring01(String mnuPstring01){
        this.mnuPstring01 = mnuPstring01;
    }

    
    public void setMnuPstring02(String mnuPstring02){
        this.mnuPstring02 = mnuPstring02;
    }

    
    public void setMnuPstring03(String mnuPstring03){
        this.mnuPstring03 = mnuPstring03;
    }

    
    public void setMnuPstring04(String mnuPstring04){
        this.mnuPstring04 = mnuPstring04;
    }

    
    public void setMnuPstring05(String mnuPstring05){
        this.mnuPstring05 = mnuPstring05;
    }

    
    public void setTreeFlags(long treeFlags){
        this.treeFlags = treeFlags;
    }

    
    public void setTreeAttribute01(String treeAttribute01){
        this.treeAttribute01 = treeAttribute01;
    }

    
    public void setTreeAttribute02(String treeAttribute02){
        this.treeAttribute02 = treeAttribute02;
    }

    
    public void setClassIcon(String classIcon){
        this.classIcon = classIcon;
    }

    
    public void setClassIconSmall(String classIconSmall){
        this.classIconSmall = classIconSmall;
    }

    
    public void setExtraData(String extraData){
        this.extraData = extraData;
    }

    
    public void setMnuTarget(String mnuTarget){
        this.mnuTarget = mnuTarget;
    }

    
    public void setWindowWidth(String windowWidth){
        this.windowWidth = windowWidth;
    }

    
    public void setWindowHeight(String windowHeight){
        this.windowHeight = windowHeight;
    }

    
    public void setControlProperties(String controlProperties){
        this.controlProperties = controlProperties;
    }

    
    public void setChildItemList(List<MenuSubMenuInfo> childItemList){
        this.childItemList = childItemList;
    }
    
}
