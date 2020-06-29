/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPDRPropertiesByDivisonVO.java
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
public class HasPDRPropertiesByDivisonVO extends BusinessRelationObjectVO {
    private String        useflag                                           ;
    private String        mandatoryflag                                     ;
    private String        useConditionFlag                                  ;
    private Integer       orderValue                                        ;


    public void    setUseflag(String useflag){
        this.useflag = useflag;
    }
    public void    setMandatoryflag(String mandatoryflag){
        this.mandatoryflag = mandatoryflag;
    }
    public void    setUseConditionFlag(String useConditionFlag){
        this.useConditionFlag = useConditionFlag;
    }
    public void    setOrderValue(Integer orderValue){
        this.orderValue = orderValue;
    }
    public String getUseflag(){
        return useflag;
    }
    public String getMandatoryflag(){
        return mandatoryflag;
    }
    public String getUseConditionFlag(){
        return useConditionFlag;
    }
    public Integer getOrderValue(){
        return orderValue;
    }
}

