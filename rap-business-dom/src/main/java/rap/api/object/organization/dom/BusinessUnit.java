/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BusinessUnit.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.dom;


import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.model.BusinessUnitPeriodVO;
import rap.api.object.organization.model.BusinessUnitVO;
import rap.api.object.organization.model.DivisionPeriodVO;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.application.constants.ApplicationSchemaConstants;


public class BusinessUnit extends Organizations {
    public BusinessUnit(String obid){
        super(obid);
    }
    public BusinessUnit(BusinessUnitVO vo){
        super(vo);
    }
    @Override
    public BusinessUnitVO getVo(){
        return (BusinessUnitVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeBusinessUnit();
    }
    public void initializeBusinessUnit(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "BusinessUnit[toString()=" + super.toString() + "]";
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
        throw new ApplicationException("Cannot Create Business Unit!!!");

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
    public final static BusinessUnitVO getBusinessUnitName(String names) {
        if(StrUtil.isEmpty(names) ) { return null; };
        return ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_BUSINESSUNIT, names, false);
    }
    
    public final static BusinessUnitVO getVOByName(String names) {
        if(StrUtil.isEmpty(names) ) { return null; };
        return ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_BUSINESSUNIT, names, false);
    }
    
    public static List<DivisionUnitVO> getDivisionUnitList(String businessUnitCode){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return getDivisionUnitList(businessUnitCode, String.valueOf(year));
    }
    public static List<DivisionUnitVO> getDivisionUnitList(String businessUnitCode, String yyyy){
        
        StringBuffer selectPatternBuf = new StringBuffer();
        StringUtil.addSortByPattern(selectPatternBuf, "@this.[titles]");
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        if( !StrUtil.isEmpty(yyyy) ){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[startYyyy]",
                    GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, yyyy);
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[endYyyy]",
                    GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, yyyy);
        }
        
        List<DivisionPeriodVO> divisionPeriodVOList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_DIVISIONPERIOD, 
                                                                             selectPatternBuf.toString(), 
                                                                             wherePatternBuf.toString(), 
                                                                             paramPatternBuf.toString());
        
        Set<String> divisionPeriodNameSet =  new HashSet<String>();
        
        for(DivisionPeriodVO divisionPeriodVO : divisionPeriodVOList){
            divisionPeriodNameSet.add(divisionPeriodVO.getDivisionCode());
        }
        wherePatternBuf.setLength(0);
        paramPatternBuf.setLength(0);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[businessUnitCode]",
                                        GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, businessUnitCode);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]",
                                        GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(divisionPeriodNameSet));
        
        return ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, 
                                      selectPatternBuf.toString(), 
                                      wherePatternBuf.toString(), 
                                      paramPatternBuf.toString());
    }
    
    public static List<BusinessUnitVO> getBusinessUnitList(String businessUnitCode, String yyyy) {
        StringBuffer selectPatternBuf = new StringBuffer();
        StringUtil.addSortByPattern(selectPatternBuf, "@this.[titles]");
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        if ( !StrUtil.isEmpty(yyyy) ) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[startYyyy]",
                    GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, yyyy);
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[endYyyy]",
                    GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, yyyy);
        }
        
        List<BusinessUnitPeriodVO> businessUnitPeriodVOList = ObjectRoot.findObjects(
                ApplicationSchemaConstants.BIZCLASS_BUSINESSUNITPERIOD, 
                selectPatternBuf.toString(), 
                wherePatternBuf.toString(), 
                paramPatternBuf.toString()
        );
        
        Set<String> businessUnitPeriodNameSet =  new HashSet<String>();
        for ( BusinessUnitPeriodVO businessUnitPeriodVO : businessUnitPeriodVOList ) {
            businessUnitPeriodNameSet.add(businessUnitPeriodVO.getBusinessUnitCode());
        }
        
        wherePatternBuf.setLength(0);
        paramPatternBuf.setLength(0);
        if ( !NullUtil.isNone(businessUnitCode) ) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[businessUnitCode]",
                    GlobalConstants.OQL_OPERATOR_IN, businessUnitCode);
        }
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]",
                                        GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(businessUnitPeriodNameSet));
        
        return ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_BUSINESSUNIT, 
                selectPatternBuf.toString(), 
                wherePatternBuf.toString(), 
                paramPatternBuf.toString());
    }
    
    public static List<BusinessUnitVO> getBusinessUnitList(String businessUnitCode, String names, String descriptions, boolean isOrderByNames) {
        StringBuffer selectPatternBuf = new StringBuffer();
        if( isOrderByNames ){
            StringUtil.addSortByPattern(selectPatternBuf, "@this.[names]");
        }
        else{
            StringUtil.addSortByPattern(selectPatternBuf, "@this.[titles]");
        }
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();

        if( !StrUtil.isEmpty(names)) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "LOWER(@this.[names])", GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + names.toLowerCase() + GlobalConstants.FLAG_TYPE_ALL);
        if( !StrUtil.isEmpty(descriptions)) StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "LOWER(@this.[descriptions])", GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + descriptions.toLowerCase() + GlobalConstants.FLAG_TYPE_ALL);
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]",GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);
        return ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_BUSINESSUNIT, selectPatternBuf.toString(), wherePatternBuf.toString(), paramPatternBuf.toString());
    }
}

