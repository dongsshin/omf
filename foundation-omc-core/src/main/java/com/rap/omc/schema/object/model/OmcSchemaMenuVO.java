/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaMenuVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 3. 6.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.object.model;

import java.util.List;

import com.rap.omc.util.NullUtil;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSystemConstants;


/**
 * <pre>
 * Class : OmcSchemaMenuVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcSchemaMenuVO extends OmcSchemaSysRootVO{
    private String  isSub                              ;
    private String  kindsStr                           ;
    private long    objectKind                         ;
    private String  isAccessControlObject              ;
    private Integer sortings                           ;
    private String  subNames                           ;
    private String  isHidden                           ;
    private String  labels                             ;
    private String  labelsKr                           ;
    private String  callingType                        ;
    private String  linkHref                           ;
    private String  linkAlt                            ;
    private String  images                             ;
    private String  accessExpression                   ;
    private String  descriptions                       ;
    private List<String>  subItemList                  ;
        
    private String  string01                           ;
    private String  string02                           ;
    private String  string03                           ;
    private String  string04                           ;
    private String  string05                           ;
    
    private String relationObid  ;
    private long   schemaKind    ;
    private long   relationFlags ;
    private String attribute01   ;
    private String attribute02   ;
    private String attribute03   ;
    private String attribute04   ;
    private String attribute05   ;
    private String attribute06   ;
    private String attribute07   ;
    private String attribute08   ;
    private String attribute09   ;
    private String attribute10   ;
    private String attribute11   ;
    private String attribute12   ;
    private String attribute13   ;
    private String attribute14   ;
    private String attribute15   ;
    private String attribute16   ;
    private String attribute17   ;
    private String attribute18   ;
    private String attribute19   ;
    private String attribute20   ;
    
    public String getIsAccessControlObject(){
        return isAccessControlObject;
    }

    public void setIsAccessControlObject(String isAccessControlObject){
        this.isAccessControlObject = isAccessControlObject;
    }


    public String getRelationObid(){
        return relationObid;
    }

    
    public void setRelationObid(String relationObid){
        this.relationObid = relationObid;
    }

    
    public long getSchemaKind(){
        return schemaKind;
    }

    
    public void setSchemaKind(long schemaKind){
        this.schemaKind = schemaKind;
    }

    
    public long getRelationFlags(){
        return relationFlags;
    }

    
    public void setRelationFlags(long relationFlags){
        this.relationFlags = relationFlags;
    }

    
    public String getAttribute01(){
        return attribute01;
    }

    
    public void setAttribute01(String attribute01){
        this.attribute01 = attribute01;
    }

    
    public String getAttribute02(){
        return attribute02;
    }

    
    public void setAttribute02(String attribute02){
        this.attribute02 = attribute02;
    }

    
    public String getAttribute03(){
        return attribute03;
    }

    
    public void setAttribute03(String attribute03){
        this.attribute03 = attribute03;
    }

    
    public String getAttribute04(){
        return attribute04;
    }

    
    public void setAttribute04(String attribute04){
        this.attribute04 = attribute04;
    }

    
    public String getAttribute05(){
        return attribute05;
    }

    
    public void setAttribute05(String attribute05){
        this.attribute05 = attribute05;
    }

    
    public String getAttribute06(){
        return attribute06;
    }

    
    public void setAttribute06(String attribute06){
        this.attribute06 = attribute06;
    }

    
    public String getAttribute07(){
        return attribute07;
    }

    
    public void setAttribute07(String attribute07){
        this.attribute07 = attribute07;
    }

    
    public String getAttribute08(){
        return attribute08;
    }

    
    public void setAttribute08(String attribute08){
        this.attribute08 = attribute08;
    }

    
    public String getAttribute09(){
        return attribute09;
    }

    
    public void setAttribute09(String attribute09){
        this.attribute09 = attribute09;
    }

    
    public String getAttribute10(){
        return attribute10;
    }

    
    public void setAttribute10(String attribute10){
        this.attribute10 = attribute10;
    }

    
    public String getAttribute11(){
        return attribute11;
    }

    
    public void setAttribute11(String attribute11){
        this.attribute11 = attribute11;
    }

    
    public String getAttribute12(){
        return attribute12;
    }

    
    public void setAttribute12(String attribute12){
        this.attribute12 = attribute12;
    }

    
    public String getAttribute13(){
        return attribute13;
    }

    
    public void setAttribute13(String attribute13){
        this.attribute13 = attribute13;
    }

    
    public String getAttribute14(){
        return attribute14;
    }

    
    public void setAttribute14(String attribute14){
        this.attribute14 = attribute14;
    }

    
    public String getAttribute15(){
        return attribute15;
    }

    
    public void setAttribute15(String attribute15){
        this.attribute15 = attribute15;
    }

    
    public String getAttribute16(){
        return attribute16;
    }

    
    public void setAttribute16(String attribute16){
        this.attribute16 = attribute16;
    }

    
    public String getAttribute17(){
        return attribute17;
    }

    
    public void setAttribute17(String attribute17){
        this.attribute17 = attribute17;
    }

    
    public String getAttribute18(){
        return attribute18;
    }

    
    public void setAttribute18(String attribute18){
        this.attribute18 = attribute18;
    }

    
    public String getAttribute19(){
        return attribute19;
    }

    
    public void setAttribute19(String attribute19){
        this.attribute19 = attribute19;
    }

    
    public String getAttribute20(){
        return attribute20;
    }

    
    public void setAttribute20(String attribute20){
        this.attribute20 = attribute20;
    }

    
    public void setObjectKind(long objectKind){
        this.objectKind = objectKind;
        if(NullUtil.isNone(this.kindsStr)){
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_Menu)) {this.kindsStr = "Menu";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_ClassPopupMenu)) {this.kindsStr = "Class Popup Menu";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_PopupMenu)) {this.kindsStr = "Popup Menu";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_Command)) {this.kindsStr = "Command";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_Toolbar)) {this.kindsStr = "Toolbar";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_ClassMenu)) {this.kindsStr = "Class Menu";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_RadioGroup)) {this.kindsStr = "Radio Group";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_Radio)) {this.kindsStr = "Radio";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_CheckBoxGroup)) {this.kindsStr = "Checkbox Group";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_CheckBox)) {this.kindsStr = "Checkbox";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_FilterGroup)) {this.kindsStr = "Filter Group";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_Filter)) {this.kindsStr = "Filter";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_Label)) {this.kindsStr = "Label";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_Combo)) {this.kindsStr = "Combo";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_Text)) {this.kindsStr = "Text";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_Calendar)) {this.kindsStr = "Calendar";return;}
            if(Bit.isInclude(objectKind, OmcSystemConstants.SYSMNU_KIND_StructureMenu)) {this.kindsStr = "Structure Menu";return;}
        }
    }
    public List<String> getSubItemList(){
        return subItemList;
    }
    
    public void setSubItemList(List<String> subItemList){
        this.subItemList = subItemList;
    }



    public String getLabelsKr(){
        return labelsKr;
    }


    
    public void setLabelsKr(String labelsKr){
        this.labelsKr = labelsKr;
    }


    public String getString01(){
        return string01;
    }

    
    public String getString02(){
        return string02;
    }

    
    public String getString03(){
        return string03;
    }

    
    public String getString04(){
        return string04;
    }

    
    public String getString05(){
        return string05;
    }

    
    public void setString01(String string01){
        this.string01 = string01;
    }

    
    public void setString02(String string02){
        this.string02 = string02;
    }

    
    public void setString03(String string03){
        this.string03 = string03;
    }

    
    public void setString04(String string04){
        this.string04 = string04;
    }

    
    public void setString05(String string05){
        this.string05 = string05;
    }

    public long getObjectKind(){
        return objectKind;
    }

    public String getIsSub(){
        return isSub;
    }
    
    public String getKindsStr(){
        return kindsStr;
    }
    
    public Integer getSortings(){
        return sortings;
    }
    
    public String getSubNames(){
        return subNames;
    }
    
    public String getIsHidden(){
        return isHidden;
    }
    
    public String getLabels(){
        return labels;
    }
    
    public String getCallingType(){
        return callingType;
    }
    
    public String getLinkHref(){
        return linkHref;
    }
    
    public String getLinkAlt(){
        return linkAlt;
    }
    
    public String getImages(){
        return images;
    }
    
    public String getAccessExpression(){
        return accessExpression;
    }
    
    public String getDescriptions(){
        return descriptions;
    }
    
    public void setIsSub(String isSub){
        this.isSub = isSub;
    }
    
    public void setKindsStr(String kindsStr){
        this.kindsStr = kindsStr;
        
        long kinds = OmcSystemConstants.SYSMNU_KIND_Default;
        
        if("Menu".equals(               kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_Menu);
        if("Class Popup Menu".equals(   kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_ClassPopupMenu);
        if("Popup Menu".equals(         kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_PopupMenu);
        if("Command".equals(            kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_Command);
        if("Toolbar".equals(            kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_Toolbar);
        if("Class Menu".equals(         kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_ClassMenu);
        if("Radio Group".equals(        kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_RadioGroup);
        if("Radio".equals(              kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_Radio);
        if("Checkbox Group".equals(     kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_CheckBoxGroup);
        if("Checkbox".equals(           kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_CheckBox);
        if("Filter Group".equals(       kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_FilterGroup);
        if("Filter".equals(             kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_Filter);
        if("Label".equals(              kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_Label);

        if("Combo".equals(              kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_Combo);
        if("Text".equals(               kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_Text);
        if("Calendar".equals(           kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_Calendar);
        if("Structure Menu".equals(     kindsStr)) kinds = Bit.or(kinds,OmcSystemConstants.SYSMNU_KIND_StructureMenu);
        
        this.objectKind = kinds;
    }
    
    public void setSortings(Integer sortings){
        this.sortings = sortings;
    }
    
    public void setSubNames(String subNames){
        this.subNames = subNames;
    }
    
    public void setIsHidden(String isHidden){
        this.isHidden = isHidden;
    }
    
    public void setLabels(String labels){
        this.labels = labels;
    }
    
    public void setCallingType(String callingType){
        this.callingType = callingType;
    }
    
    public void setLinkHref(String linkHref){
        this.linkHref = linkHref;
    }
    
    public void setLinkAlt(String linkAlt){
        this.linkAlt = linkAlt;
    }
    
    public void setImages(String images){
        this.images = images;
    }
    
    public void setAccessExpression(String accessExpression){
        this.accessExpression = accessExpression;
    }
    
    public void setDescriptions(String descriptions){
        this.descriptions = descriptions;
    }
    
}
