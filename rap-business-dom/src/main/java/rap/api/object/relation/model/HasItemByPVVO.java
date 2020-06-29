/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasItemByPVVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class HasItemByPVVO extends BusinessRelationObjectVO {
    private String        globalMandatoryFlag                               ;
    private String        domesticMandatoryFlag                             ;
    private String        pcoMandatoryFlag                                  ;
    private Integer       orderValue                                        ;


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
}

