/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectSWGovernanceVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectSWGovernanceVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        checkItemCode                                     ;
    private String        checkItemDesc                                     ;
    private String        checkItemType                                     ;
    private String        evaluation                                        ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setCheckItemCode(String checkItemCode){
        this.checkItemCode = checkItemCode;
    }
    public void    setCheckItemDesc(String checkItemDesc){
        this.checkItemDesc = checkItemDesc;
    }
    public void    setCheckItemType(String checkItemType){
        this.checkItemType = checkItemType;
    }
    public void    setEvaluation(String evaluation){
        this.evaluation = evaluation;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getCheckItemCode(){
        return checkItemCode;
    }
    public String getCheckItemDesc(){
        return checkItemDesc;
    }
    public String getCheckItemType(){
        return checkItemType;
    }
    public String getEvaluation(){
        return evaluation;
    }
}

