/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DivisionPeriod.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.dom;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.oql.model.OmcOQLCondition;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.model.DivisionPeriodVO;
import rap.application.constants.ApplicationSchemaConstants;


public class DivisionPeriod extends Organizations {
    public DivisionPeriod(String obid){
        super(obid);
    }
    public DivisionPeriod(DivisionPeriodVO vo){
        super(vo);
    }
    @Override
    public DivisionPeriodVO getVo(){
        return (DivisionPeriodVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeDivisionPeriod();
    }
    public void initializeDivisionPeriod(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "DivisionPeriod[toString()=" + super.toString() + "]";
    }


    @Override
    protected void validateForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.validateForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

    @Override
    protected void preProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.preProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

    @Override
    protected void postProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.postProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

   @Override
    protected void validateForCreate(Map<String, Object> map){
        super.validateForCreate(map);
        /*code below*/

    }

   @Override
    protected void preProcessForCreate(Map<String, Object> map){
        super.preProcessForCreate(map);
        /*code below*/

    }

   @Override
    protected void postProcessForCreate(Map<String, Object> map){
        super.postProcessForCreate(map);
        /*code below*/

    }

   @Override
    protected void validateForDelete(Map<String, Object> map){
        super.validateForDelete(map);
        /*code below*/

    }

   @Override
    protected void preProcessForDelete(Map<String, Object> map){
        super.preProcessForDelete(map);
        /*code below*/

    }

   @Override
    protected void postProcessForDelete(Map<String, Object> map){
        super.postProcessForDelete(map);
        /*code below*/

    }

   @Override
    protected void validateForModify(Map<String, Object> map){
        super.validateForModify(map);
        /*code below*/

    }

   @Override
    protected void preProcessForModify(Map<String, Object> map){
        super.preProcessForModify(map);
        /*code below*/

    }

   @Override
    protected void postProcessForModify(Map<String, Object> map){
        super.postProcessForModify(map);
        /*code below*/

    }

   @Override
    protected void validateForWithdraw(Map<String, Object> map){
        super.validateForWithdraw(map);
        /*code below*/

    }

   @Override
    protected void preProcessForWithdraw(Map<String, Object> map){
        super.preProcessForWithdraw(map);
        /*code below*/

    }

   @Override
    protected void postProcessForWithdraw(Map<String, Object> map){
        super.postProcessForWithdraw(map);
        /*code below*/

    }

   @Override
    protected void validateForDemote(Map<String, Object> map){
        super.validateForDemote(map);
        /*code below*/

    }

   @Override
    protected void preProcessForDemote(Map<String, Object> map){
        super.preProcessForDemote(map);
        /*code below*/

    }

   @Override
    protected void postProcessForDemote(Map<String, Object> map){
        super.postProcessForDemote(map);
        /*code below*/

    }

   @Override
    protected void validateForPromote(Map<String, Object> map){
        super.validateForPromote(map);
        /*code below*/

    }

   @Override
    protected void preProcessForPromote(Map<String, Object> map){
        super.preProcessForPromote(map);
        /*code below*/

    }

   @Override
    protected void postProcessForPromote(Map<String, Object> map){
        super.postProcessForPromote(map);
        /*code below*/

    }

    @Override
    protected void validateForClone(Map<String, Object> map){
        super.validateForClone(map);
        /*code below*/

    }

    @Override
    protected void preProcessForClone(Map<String, Object> map){
        super.preProcessForClone(map);
        /*code below*/

    }
    @Override
    protected void postProcessForClone(Map<String, Object> map){
        super.postProcessForClone(map);
        /*code below*/

    }
    public static String getHrDepartmentCode(String divisionCode, String yyyy){
        StringBuffer selectPatternBuf = new StringBuffer();
        StringUtil.constructSelectPattern(selectPatternBuf, "@this.[divisionCode] tempDivisionCode");
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        if( !StrUtil.isEmpty(divisionCode) ){           
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[divisionCode]",GlobalConstants.OQL_OPERATOR_EQUAL, divisionCode);
        }
        if( !StrUtil.isEmpty(yyyy) ){           
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[startYyyy]",GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, yyyy);
            List<OmcOQLCondition> conditionList = new ArrayList<OmcOQLCondition>();
            conditionList.add(new OmcOQLCondition("@this.[endYyyy]",   yyyy  ,GlobalConstants.OQL_OPERATOR_GREATER_THAN));
            StringUtil.constructOrWherePattern(wherePatternBuf, paramPatternBuf, conditionList);
        }
        
        List<DivisionPeriodVO> result = ObjectRoot.searchObjects(ApplicationSchemaConstants.BIZCLASS_DIVISIONPERIOD,
        		selectPatternBuf.toString(),
                "",
                wherePatternBuf.toString(),
                paramPatternBuf.toString());
       
        if(result.size() > 0 ){
            return result.get(0).getDepartmentCode();
        }
        return "";
    }
}

