/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OrganizationRange.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.dom;


import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.annotation.SCRequestDataset;
import com.rap.omc.util.StringUtil;

import rap.api.object.common.model.OrganizationRangeVO;
import rap.api.object.organization.dom.DivisionUnit;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.application.constants.ApplicationSchemaConstants;


public class OrganizationRange extends BusinessObjectMaster {
    public OrganizationRange(String obid){
        super(obid);
    }
    public OrganizationRange(OrganizationRangeVO vo){
        super(vo);
    }
    @Override
    public OrganizationRangeVO getVo(){
        return (OrganizationRangeVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeOrganizationRange();
    }
    public void initializeOrganizationRange(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "OrganizationRange[toString()=" + super.toString() + "]";
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
   public OrganizationRangeVO createOrganizationRange(){       
       //1. Init Default Data
       this.getVo().setClassName(ApplicationSchemaConstants.BIZCLASS_ORGANIZATIONRANGE);
       this.getVo().setStates(ApplicationSchemaConstants.STATE_WITHOUT_STATE_EXISTS);
       this.getVo().setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WITHOUT_STATE);
       this.getVo().setNames(NameGeneratorUtil.generateUniqueName("DSS-TEST"));

       this.createObject();
       return this.getVo();
   }
  
   public OrganizationRangeVO updateOrganizationRange( OrganizationRangeVO orgRangeVO ){       
       //1. Init Default Data
       this.getVo().setObid(orgRangeVO.getObid());
       this.getVo().setTitles(orgRangeVO.getTitles());
       this.getVo().setDescriptions(orgRangeVO.getDescriptions());
      
       this.modifyObject();
       return this.getVo();
   }
  
   public static List<OrganizationRangeVO> retrieveOrganizationRange( OrganizationRangeVO orgRangeVO){       
       // Where ���� ����
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
      
       // Business Unit Code�� �ش��ϴ� Plant�� ��ȸ
       if( !StrUtil.isEmpty(orgRangeVO.getTitles()) ){
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[titles]",
                   GlobalConstants.OQL_OPERATOR_LIKE, orgRangeVO.getTitles() + '%');
       }

       return ObjectRoot.findObjects(
               ApplicationSchemaConstants.BIZCLASS_ORGANIZATIONRANGE, // className
               GlobalConstants.FLAG_TYPE_ALL, //namePattern, 
               GlobalConstants.FLAG_TYPE_ALL, //revisionPattern, 
               GlobalConstants.FLAG_TYPE_ALL, // selectPattern
               wherePatternBuf.toString(),    // wherePattern,         
               paramPatternBuf.toString(),    // parameterPattern, 
               false,                         //expandType
               0
       );
   }
   
   public List<DivisionUnitVO> getDivisionList(){
       return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASORGANIZATIONRANGE, ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, GlobalConstants.FLAG_TYPE_TO );
   }
   
   public static void saveOrgRangeDivision( @SCRequestDataset( "orgRangeList" ) List<OrganizationRangeVO> orgRangeList, @SCRequestDataset( "divisionList" ) List<DivisionUnitVO> divisionList ){
       OrganizationRange orgRange = DomUtil.toDom(orgRangeList.get(0));
       
       for (int inx= 0; inx < divisionList.size(); inx++) {
           orgRange.addToObject(ApplicationSchemaConstants.RELCLASS_HASORGANIZATIONRANGE, DivisionUnit.getObidForDivisionName(divisionList.get(inx).getNames()), null);
       }
   }
}

