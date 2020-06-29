/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CodeDetailVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class CodeDetailVO extends BusinessObjectVO {
    private Integer       sequences                                          = 1;
    private Boolean       isDefault                                          = (Boolean)false;
    private String        usingOrganizationList                             ;
    private String        displayNameKr                                     ;
    private String        subCodeMaster                                     ;
    private String        attribute01                                       ;
    private String        attribute02                                       ;
    private String        attribute03                                       ;
    private String        attribute04                                       ;
    private String        attribute05                                       ;


    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setIsDefault(Boolean isDefault){
        this.isDefault = isDefault;
    }
    public void    setUsingOrganizationList(String usingOrganizationList){
        this.usingOrganizationList = usingOrganizationList;
    }
    public void    setDisplayNameKr(String displayNameKr){
        this.displayNameKr = displayNameKr;
    }
    public void    setSubCodeMaster(String subCodeMaster){
        this.subCodeMaster = subCodeMaster;
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
    public Integer getSequences(){
        return sequences;
    }
    public Boolean getIsDefault(){
        return isDefault;
    }
    public String getUsingOrganizationList(){
        return usingOrganizationList;
    }
    public String getDisplayNameKr(){
        return displayNameKr;
    }
    public String getSubCodeMaster(){
        return subCodeMaster;
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
}

