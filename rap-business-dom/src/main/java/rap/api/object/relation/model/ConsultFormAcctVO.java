/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ConsultFormAcctVO.java
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
public class ConsultFormAcctVO extends BusinessRelationObjectVO {
    private String        bgtCtrYn                                          ;
    private String        actualReflYn                                      ;
    private String        requestGroup                                      ;


    public void    setBgtCtrYn(String bgtCtrYn){
        this.bgtCtrYn = bgtCtrYn;
    }
    public void    setActualReflYn(String actualReflYn){
        this.actualReflYn = actualReflYn;
    }
    public void    setRequestGroup(String requestGroup){
        this.requestGroup = requestGroup;
    }
    public String getBgtCtrYn(){
        return bgtCtrYn;
    }
    public String getActualReflYn(){
        return actualReflYn;
    }
    public String getRequestGroup(){
        return requestGroup;
    }
}

