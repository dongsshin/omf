/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : Company.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.dom;


import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.model.CompanyVO;
import rap.application.constants.ApplicationSchemaConstants;


public class Company extends Organizations {
    public Company(String obid){
        super(obid);
    }
    public Company(CompanyVO vo){
        super(vo);
    }
    @Override
    public CompanyVO getVo(){
        return (CompanyVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeCompany();
    }
    public void initializeCompany(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "Company[toString()=" + super.toString() + "]";
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
    public final static CompanyVO getCompanyByName(String names) {
        if(StrUtil.isEmpty(names) ) { return null; };
        return BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_COMPANY, names);
    }
    public final static CompanyVO getMainCompany(){
    	return getCompanyByName("LE");
    }
    
    public final static List<CompanyVO> getCompanyList(String names, String descriptions, boolean isOrderByNames) {
        // Order by ���� ����
        StringBuffer selectPatternBuf = new StringBuffer();
        if( isOrderByNames ){
            StringUtil.addSortByPattern(selectPatternBuf, "@this.[names]");
        }
        else{
            StringUtil.addSortByPattern(selectPatternBuf, "@this.[titles]");
        }
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        if( !StrUtil.isEmpty(names) ){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "LOWER(@this.[names])",
                    GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + names.toLowerCase() + GlobalConstants.FLAG_TYPE_ALL);
        }

        if( !StrUtil.isEmpty(descriptions) ){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "LOWER(@this.[descriptions])",
                    GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + descriptions.toLowerCase() + GlobalConstants.FLAG_TYPE_ALL);
        }
        if( !StrUtil.isEmpty(descriptions) ){
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);
        }
        List<CompanyVO> result = ObjectRoot.searchObjects(ApplicationSchemaConstants.BIZCLASS_COMPANY,
        		selectPatternBuf.toString(),
        		"",
                wherePatternBuf.toString(),
                paramPatternBuf.toString());
        if( result != null && result.size() > 0 ){
            for( int inx = 0; inx < result.size(); inx++ ){
                result.get(inx).setTitles(result.get(inx).getNames() + " | " + result.get(inx).getTitles());
            }
        }
        return result;
    }
    
    
}

