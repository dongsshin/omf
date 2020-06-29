/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WorkflowRelationSample2VO.java
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
public class WorkflowRelationSample2VO extends BusinessRelationObjectVO {
    private String        btestattr1                                        ;
    private Date          btestattr2                                        ;


    public void    setBtestattr1(String btestattr1){
        this.btestattr1 = btestattr1;
    }
    public void    setBtestattr2(Date btestattr2){
        this.btestattr2 = btestattr2;
    }
    public void    setBtestattr2(String    btestattr2){
        this.btestattr2 = this.omcConvertStr2Date(btestattr2);
    }
    public String getBtestattr1(){
        return btestattr1;
    }
    public Date getBtestattr2(){
        return btestattr2;
    }
}

