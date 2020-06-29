/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : WorkflowRelationSample1VO.java
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
public class WorkflowRelationSample1VO extends BusinessRelationObjectVO {
    private String        atestattr1                                        ;
    private String        atestattr2                                        ;
    private String        atestattr3                                        ;


    public void    setAtestattr1(String atestattr1){
        this.atestattr1 = atestattr1;
    }
    public void    setAtestattr2(String atestattr2){
        this.atestattr2 = atestattr2;
    }
    public void    setAtestattr3(String atestattr3){
        this.atestattr3 = atestattr3;
    }
    public String getAtestattr1(){
        return atestattr1;
    }
    public String getAtestattr2(){
        return atestattr2;
    }
    public String getAtestattr3(){
        return atestattr3;
    }
}

