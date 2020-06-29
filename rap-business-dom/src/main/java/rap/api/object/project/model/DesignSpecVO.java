/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesignSpecVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DesignSpecVO extends BusinessObjectVO {
    private String        buyerInLoc                                        ;
    private String        importFlag                                        ;
    private String        specModelName                                     ;
    private String        specCompanyName                                   ;
    private String        specFlag                                          ;
    private String        recipientEmployeeNo                               ;
    private String        specBase                                          ;
    private String        majorComment                                      ;


    public void    setBuyerInLoc(String buyerInLoc){
        this.buyerInLoc = buyerInLoc;
    }
    public void    setImportFlag(String importFlag){
        this.importFlag = importFlag;
    }
    public void    setSpecModelName(String specModelName){
        this.specModelName = specModelName;
    }
    public void    setSpecCompanyName(String specCompanyName){
        this.specCompanyName = specCompanyName;
    }
    public void    setSpecFlag(String specFlag){
        this.specFlag = specFlag;
    }
    public void    setRecipientEmployeeNo(String recipientEmployeeNo){
        this.recipientEmployeeNo = recipientEmployeeNo;
    }
    public void    setSpecBase(String specBase){
        this.specBase = specBase;
    }
    public void    setMajorComment(String majorComment){
        this.majorComment = majorComment;
    }
    public String getBuyerInLoc(){
        return buyerInLoc;
    }
    public String getImportFlag(){
        return importFlag;
    }
    public String getSpecModelName(){
        return specModelName;
    }
    public String getSpecCompanyName(){
        return specCompanyName;
    }
    public String getSpecFlag(){
        return specFlag;
    }
    public String getRecipientEmployeeNo(){
        return recipientEmployeeNo;
    }
    public String getSpecBase(){
        return specBase;
    }
    public String getMajorComment(){
        return majorComment;
    }
}

