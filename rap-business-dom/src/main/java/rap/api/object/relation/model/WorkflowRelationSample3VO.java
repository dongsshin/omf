/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WorkflowRelationSample3VO.java
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
public class WorkflowRelationSample3VO extends BusinessRelationObjectVO {
    private String        ctestattr1                                        ;
    private Date          ctestattr2                                        ;


    public void    setCtestattr1(String ctestattr1){
        this.ctestattr1 = ctestattr1;
    }
    public void    setCtestattr2(Date ctestattr2){
        this.ctestattr2 = ctestattr2;
    }
    public void    setCtestattr2(String    ctestattr2){
        this.ctestattr2 = this.omcConvertStr2Date(ctestattr2);
    }
    public String getCtestattr1(){
        return ctestattr1;
    }
    public Date getCtestattr2(){
        return ctestattr2;
    }
}

