/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PVRelationEditLogVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PVRelationEditLogVO extends BusinessObjectMasterVO {
    private String        targetClassName                                   ;
    private String        fromClass                                         ;
    private String        fromObid                                          ;
    private String        toClass                                           ;
    private String        toObid                                            ;
    private String        opType                                            ;
    private String        globalMandatoryFlag                               ;
    private String        domesticMandatoryFlag                             ;
    private String        pcoMandatoryFlag                                  ;
    private Integer       orderValue                                        ;
    private String        useFlag                                           ;


    public void    setTargetClassName(String targetClassName){
        this.targetClassName = targetClassName;
    }
    public void    setFromClass(String fromClass){
        this.fromClass = fromClass;
    }
    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setToClass(String toClass){
        this.toClass = toClass;
    }
    public void    setToObid(String toObid){
        this.toObid = toObid;
    }
    public void    setOpType(String opType){
        this.opType = opType;
    }
    public void    setGlobalMandatoryFlag(String globalMandatoryFlag){
        this.globalMandatoryFlag = globalMandatoryFlag;
    }
    public void    setDomesticMandatoryFlag(String domesticMandatoryFlag){
        this.domesticMandatoryFlag = domesticMandatoryFlag;
    }
    public void    setPcoMandatoryFlag(String pcoMandatoryFlag){
        this.pcoMandatoryFlag = pcoMandatoryFlag;
    }
    public void    setOrderValue(Integer orderValue){
        this.orderValue = orderValue;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public String getTargetClassName(){
        return targetClassName;
    }
    public String getFromClass(){
        return fromClass;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getToClass(){
        return toClass;
    }
    public String getToObid(){
        return toObid;
    }
    public String getOpType(){
        return opType;
    }
    public String getGlobalMandatoryFlag(){
        return globalMandatoryFlag;
    }
    public String getDomesticMandatoryFlag(){
        return domesticMandatoryFlag;
    }
    public String getPcoMandatoryFlag(){
        return pcoMandatoryFlag;
    }
    public Integer getOrderValue(){
        return orderValue;
    }
    public String getUseFlag(){
        return useFlag;
    }
}

