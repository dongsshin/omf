/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasItemByProductVO.java
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
public class HasItemByProductVO extends BusinessRelationObjectVO {
    private String        mandatoryFlag                                     ;
    private Integer       orderValue                                        ;


    public void    setMandatoryFlag(String mandatoryFlag){
        this.mandatoryFlag = mandatoryFlag;
    }
    public void    setOrderValue(Integer orderValue){
        this.orderValue = orderValue;
    }
    public String getMandatoryFlag(){
        return mandatoryFlag;
    }
    public Integer getOrderValue(){
        return orderValue;
    }
}

