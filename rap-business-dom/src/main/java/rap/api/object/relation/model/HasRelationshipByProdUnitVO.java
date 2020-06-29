/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasRelationshipByProdUnitVO.java
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
public class HasRelationshipByProdUnitVO extends BusinessRelationObjectVO {
    private String        targetId                                          ;
    private String        targetFlag                                        ;
    private String        conditionValueCode                                ;
    private String        condition1stValue                                 ;
    private String        condition2ndValue                                 ;
    private String        targetValueList                                   ;


    public void    setTargetId(String targetId){
        this.targetId = targetId;
    }
    public void    setTargetFlag(String targetFlag){
        this.targetFlag = targetFlag;
    }
    public void    setConditionValueCode(String conditionValueCode){
        this.conditionValueCode = conditionValueCode;
    }
    public void    setCondition1stValue(String condition1stValue){
        this.condition1stValue = condition1stValue;
    }
    public void    setCondition2ndValue(String condition2ndValue){
        this.condition2ndValue = condition2ndValue;
    }
    public void    setTargetValueList(String targetValueList){
        this.targetValueList = targetValueList;
    }
    public String getTargetId(){
        return targetId;
    }
    public String getTargetFlag(){
        return targetFlag;
    }
    public String getConditionValueCode(){
        return conditionValueCode;
    }
    public String getCondition1stValue(){
        return condition1stValue;
    }
    public String getCondition2ndValue(){
        return condition2ndValue;
    }
    public String getTargetValueList(){
        return targetValueList;
    }
}

