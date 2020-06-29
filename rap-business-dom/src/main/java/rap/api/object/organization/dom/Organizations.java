/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : Organizations.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.dom;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.foundation.user.model.UserSession;

import rap.api.object.organization.model.BusinessUnitVO;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.organization.model.OrganizationsVO;


public class Organizations extends BusinessObjectMaster {
    public Organizations(String obid){
        super(obid);
    }
    public Organizations(OrganizationsVO vo){
        super(vo);
    }
    @Override
    public OrganizationsVO getVo(){
        return (OrganizationsVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeOrganizations();
    }
    public void initializeOrganizations(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "Organizations[toString()=" + super.toString() + "]";
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
   public static List<BusinessUnitVO> retrieveBusinessUnitListByUser( UserSession userSession, String yyyy, String dataRange ){
       Users user = DomUtil.toDom( userSession.getUserBizObid(), false );
       List<DivisionUnitVO> divisionUnitList = user.retrieveDivisionUnitListDataRange( yyyy, dataRange, "", userSession );
       DivisionUnitVO voDivision;
       ArrayList<String> buNameList = new ArrayList<String>();
       for( int index = 0; index < divisionUnitList.size(); index ++ ){
            voDivision = divisionUnitList.get( index );
            if( !buNameList.contains( voDivision.getBusinessUnitCode() ) && voDivision.getUseFlag().equals( "Y" ) ){
                buNameList.add( voDivision.getBusinessUnitCode() );
            }
       }
       List<BusinessUnitVO> businessUnitList = BusinessUnit.getBusinessUnitList( null, yyyy );       
       List<BusinessUnitVO> businessUnitListByUser = new ArrayList<BusinessUnitVO>();
       String buNames;
       for( int index = 0; index < businessUnitList.size(); index ++ ){
            buNames = businessUnitList.get( index ).getNames();
            if( buNameList.contains( buNames ) ){
                businessUnitListByUser.add( businessUnitList.get( index ) );   
            }
       }
       return businessUnitListByUser;
   }  
}

