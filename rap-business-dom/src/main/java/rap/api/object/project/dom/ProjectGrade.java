/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectGrade.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.dom.DivisionUnit;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.project.model.ProjectGradeVO;
import rap.application.constants.ApplicationSchemaConstants;


public class ProjectGrade extends BusinessObjectMaster {
    public ProjectGrade(String obid){
        super(obid);
    }
    public ProjectGrade(ProjectGradeVO vo){
        super(vo);
    }
     @Override
    public ProjectGradeVO getVo(){
        return (ProjectGradeVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeProjectGrade();
    }
    public void initializeProjectGrade(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "ProjectGrade[toString()=" + super.toString() + "]";
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
    public static final ProjectGrade getProjectGrade(String names){
        if(NullUtil.isNull(names) ) { return null; };
        
        ProjectGradeVO projectGradeVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_PROJECTGRADE, names, false);
        if(!NullUtil.isNull(projectGradeVO) ){ return DomUtil.toDom(projectGradeVO); }
        return null;
    }
    public static final List<ProjectGradeVO> getUsingProjectGradeByDevelopType(String divisionUnit, String projectDevelopmentType, boolean activeOnly){
         StringBuffer wherePatternBuf = new StringBuffer();
         StringBuffer paramPatternBuf = new StringBuffer();
         StringBuffer selectPatternBuf = new StringBuffer();

         DivisionUnitVO divisionUnitVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, divisionUnit);
         DivisionUnit divisionUnitDom = new DivisionUnit(divisionUnitVO);
         StringUtil.constructSelectPattern(selectPatternBuf, "SortBy@REL.[sequences] asc");
         StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf,"@REL.[isActive]",GlobalConstants.OQL_OPERATOR_EQUAL, "Y");

         if(activeOnly){
             StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, 
                     "@REL.obid.To["+ApplicationSchemaConstants.RELCLASS_DEFINEDGRADEFORDEVTYPE+"].From.self["+ApplicationSchemaConstants.RELCLASS_USINGDEVELOPMENTTYPE+"{isDeleted=='N'}].To.names", 
                     GlobalConstants.OQL_OPERATOR_EQUAL, projectDevelopmentType);
                         
         }else{
             StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, 
                     "@REL.obid.To["+ApplicationSchemaConstants.RELCLASS_DEFINEDGRADEFORDEVTYPE+"].From.self["+ApplicationSchemaConstants.RELCLASS_USINGDEVELOPMENTTYPE+"{isDeleted=='N'}].To.names", 
                     GlobalConstants.OQL_OPERATOR_EQUAL, projectDevelopmentType);
             
         }

         List<ProjectGradeVO> list =  divisionUnitDom.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USINGPROJECTGRADE, 
                                                  ApplicationSchemaConstants.BIZCLASS_PROJECTGRADE, 
                                                  GlobalConstants.FLAG_TYPE_TO,
                                                  selectPatternBuf.toString(), 
                                                  wherePatternBuf.toString(), 
                                                  paramPatternBuf.toString(), 
                                                  false, false, 0, 1);

         return list;
     }
    
    public static final List<ProjectGradeVO> getUsingProjectGradeList(String divisionUnit){
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringBuffer selectPatternBuf = new StringBuffer();
        
        DivisionUnitVO divisionUnitVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, divisionUnit);
        DivisionUnit divisionUnitDom = new DivisionUnit(divisionUnitVO);
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf,"@REL.[isActive]",GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        StringUtil.constructSelectPattern(selectPatternBuf, "SortBy@REL.[sequences] asc");
        
        return divisionUnitDom.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USINGPROJECTGRADE, 
                ApplicationSchemaConstants.BIZCLASS_PROJECTGRADE, 
                GlobalConstants.FLAG_TYPE_TO,
                selectPatternBuf.toString(), 
                wherePatternBuf.toString(), 
                paramPatternBuf.toString(), 
                false, false, 0, 1);
    }
    
    public static final List<ProjectGradeVO> getAllProjectGradeList() {

        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        StringUtil.addSortByPattern(selectPattern, "@this.[titles]");

        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_LIKE, "%%");
        
        return ObjectRoot.searchObjects( 
                ApplicationSchemaConstants.BIZCLASS_PROJECTGRADE,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                false,
                false,
                false,
                false,
                selectPattern.toString(),
                null,
                null,
                null,
                true,
                0);
        
    }
    
    public static List<ProjectGradeVO> getProjectGradeByNames(String names){
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf,"@this.[names]",GlobalConstants.OQL_OPERATOR_LIKE, names + GlobalConstants.FLAG_TYPE_ALL);
        
        return ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_PROJECTGRADE, wherePatternBuf.toString(), paramPatternBuf.toString());
    }
   
}

