/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartProjectsVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartProjectsVO extends BusinessObjectMasterVO {
    private String        partProjectCode                                   ;
    private String        isActiveYn                                         = "Y";
    private String        partStatus                                        ;
    private String        divisionCode                                      ;
    private String        attribute01                                       ;
    private String        attribute02                                       ;
    private String        attribute03                                       ;
    private String        attribute04                                       ;
    private String        attribute05                                       ;
    private String        attribute06                                       ;
    private String        attribute07                                       ;
    private String        attribute08                                       ;
    private String        attribute09                                       ;
    private String        attribute10                                       ;


    public void    setPartProjectCode(String partProjectCode){
        this.partProjectCode = partProjectCode;
    }
    public void    setIsActiveYn(String isActiveYn){
        this.isActiveYn = isActiveYn;
    }
    public void    setPartStatus(String partStatus){
        this.partStatus = partStatus;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setAttribute01(String attribute01){
        this.attribute01 = attribute01;
    }
    public void    setAttribute02(String attribute02){
        this.attribute02 = attribute02;
    }
    public void    setAttribute03(String attribute03){
        this.attribute03 = attribute03;
    }
    public void    setAttribute04(String attribute04){
        this.attribute04 = attribute04;
    }
    public void    setAttribute05(String attribute05){
        this.attribute05 = attribute05;
    }
    public void    setAttribute06(String attribute06){
        this.attribute06 = attribute06;
    }
    public void    setAttribute07(String attribute07){
        this.attribute07 = attribute07;
    }
    public void    setAttribute08(String attribute08){
        this.attribute08 = attribute08;
    }
    public void    setAttribute09(String attribute09){
        this.attribute09 = attribute09;
    }
    public void    setAttribute10(String attribute10){
        this.attribute10 = attribute10;
    }
    public String getPartProjectCode(){
        return partProjectCode;
    }
    public String getIsActiveYn(){
        return isActiveYn;
    }
    public String getPartStatus(){
        return partStatus;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getAttribute01(){
        return attribute01;
    }
    public String getAttribute02(){
        return attribute02;
    }
    public String getAttribute03(){
        return attribute03;
    }
    public String getAttribute04(){
        return attribute04;
    }
    public String getAttribute05(){
        return attribute05;
    }
    public String getAttribute06(){
        return attribute06;
    }
    public String getAttribute07(){
        return attribute07;
    }
    public String getAttribute08(){
        return attribute08;
    }
    public String getAttribute09(){
        return attribute09;
    }
    public String getAttribute10(){
        return attribute10;
    }
}

