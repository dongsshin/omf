/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ECOEssentialApproverCategoryLogVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.workflow.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ECOEssentialApproverCategoryLogVO extends BusinessObjectMasterVO {
    private String        opType                                            ;
    private String        obidUpper                                         ;
    private String        targetObid                                        ;
    private String        istop                                             ;
    private Integer       sort                                              ;


    public void    setOpType(String opType){
        this.opType = opType;
    }
    public void    setObidUpper(String obidUpper){
        this.obidUpper = obidUpper;
    }
    public void    setTargetObid(String targetObid){
        this.targetObid = targetObid;
    }
    public void    setIstop(String istop){
        this.istop = istop;
    }
    public void    setSort(Integer sort){
        this.sort = sort;
    }
    public String getOpType(){
        return opType;
    }
    public String getObidUpper(){
        return obidUpper;
    }
    public String getTargetObid(){
        return targetObid;
    }
    public String getIstop(){
        return istop;
    }
    public Integer getSort(){
        return sort;
    }
}

