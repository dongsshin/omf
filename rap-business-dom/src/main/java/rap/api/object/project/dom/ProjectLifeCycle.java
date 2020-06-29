/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectLifeCycle.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.BusinessRelationObject;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.dom.DivisionUnit;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.project.model.ProjectLifeCycleVO;
import rap.api.object.project.model.ProjectPhaseVO;
import rap.api.object.relation.dom.DetailedProjectPhase;
import rap.api.object.relation.dom.UsingProjectPhases;
import rap.api.object.relation.model.DetailedProjectPhaseVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;
import rap.application.workflow.model.ConvertedPhaseVO;


public class ProjectLifeCycle extends BusinessObjectMaster {
    public ProjectLifeCycle(String obid){
        super(obid);
    }
    public ProjectLifeCycle(ProjectLifeCycleVO vo){
        super(vo);
    }
     @Override
    public ProjectLifeCycleVO getVo(){
        return (ProjectLifeCycleVO)super.getVo();
    }
     @Override
    public void initialize(){
        super.initialize();
        initializeProjectLifeCycle();
    }
    public void initializeProjectLifeCycle(){
    /*code here*/
    }
     @Override
    public String toString() {
        return "ProjectLifeCycle[toString()=" + super.toString() + "]";
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
    public void addProjectPhase(DivisionUnitVO targetDivisionUnitVO, List<ProjectPhaseVO> projectPhaseVOList){
        int sequences = 10;
        for(ProjectPhaseVO projectPhaseVO : projectPhaseVOList){
            projectPhaseVO.setOutDataAttributeValue(ProjectConstants.ADD_PHASE_TO_LIFECYCLE_MAP_SEQUENCE, sequences);
            addProjectPhase(targetDivisionUnitVO,projectPhaseVO);
            sequences = sequences + 10;
        }
     }
     public void addProjectPhase(DivisionUnitVO targetDivisionUnitVO, ProjectPhaseVO projectPhaseVO){
        Map<String, Object> attributes = new HashMap<String, Object>();
        Map<String, Object> createParmMap = new HashMap<String, Object>();
        Integer sequences = this.getProjectPhaseMaxSequence()+10;
        if(NullUtil.isNull(sequences)) throw new ApplicationException("Sequence is not input.");

        
//        DivisionUnitVO divisionUnitVO = (DivisionUnitVO)map.get(ProjectConstants.USING_PHASE_TO_LIFECYCLE_MAP_DIVISION);
//        if(NullUtil.isNull(divisionUnitVO)) throw new ApplicationException("Division is null");
//        DivisionUnit divisionUnitDom = new DivisionUnit(divisionUnitVO);
//        Map<String, Object> attributes = new HashMap<String, Object>();
//        String displayedCode = (String)map.get(ProjectConstants.USING_PHASE_TO_LIFECYCLE_MAP_DISPLAY_CODE);
//        if(StrUtil.isEmpty(displayedCode)) throw new ApplicationException("displayedCode is null");
//        String displayedName = (String)map.get(ProjectConstants.USING_PHASE_TO_LIFECYCLE_MAP_DISPLAY_NAME);
//        if(StrUtil.isEmpty(displayedName)) throw new ApplicationException("displayedName is null");
//        Integer sequences = (Integer)map.get(ProjectConstants.USING_PHASE_TO_LIFECYCLE_MAP_SEQUENCE);
//        if(NullUtil.isNull(sequences)) throw new ApplicationException("sequences is null");
//        String isActive = (String)map.get(ProjectConstants.USING_PHASE_TO_LIFECYCLE_MAP_IS_ACTIVE);
//        if(StrUtil.isEmpty(isActive)) throw new ApplicationException("isActive is null");
//        attributes.put("displayedCode", displayedCode);attributes.put("displayedName", displayedName);attributes.put("sequences", sequences);attributes.put("isActive", isActive);
//        divisionUnitDom.addToObject(ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES, this.getVo(), attributes);

        
        List<DetailedProjectPhaseVO> lsit = BusinessRelationObject.findRelationObjects(ApplicationSchemaConstants.RELCLASS_DETAILEDPROJECTPHASE, this.getClassName(), this.getObid(), ApplicationSchemaConstants.BIZCLASS_PROJECTPHASE, projectPhaseVO.getObid());
        if(NullUtil.isNone(lsit)){
            attributes.put("sequences", sequences);
            //createParmMap.put(ProjectConstants.USING_PHASE_TO_LIFECYCLE_MAP_DISPLAY_CODE, projectPhaseVO.getCodeForSystemControl());
            //createParmMap.put(ProjectConstants.USING_PHASE_TO_LIFECYCLE_MAP_DISPLAY_NAME, projectPhaseVO.getNameForSystemControl());
            
            createParmMap.put(ProjectConstants.USING_PHASE_TO_LIFECYCLE_MAP_DISPLAY_CODE, projectPhaseVO.getNames());
            createParmMap.put(ProjectConstants.USING_PHASE_TO_LIFECYCLE_MAP_DISPLAY_NAME, projectPhaseVO.getDescriptions());
            createParmMap.put(ProjectConstants.USING_PHASE_TO_LIFECYCLE_MAP_IS_ACTIVE, "Y");
            createParmMap.put(ProjectConstants.USING_PHASE_TO_LIFECYCLE_MAP_SEQUENCE, sequences);
            createParmMap.put(ProjectConstants.USING_PHASE_TO_LIFECYCLE_MAP_DIVISION, targetDivisionUnitVO);
            this.addToObject(ApplicationSchemaConstants.RELCLASS_DETAILEDPROJECTPHASE, projectPhaseVO, attributes,createParmMap);           
        }
        else{
            DivisionUnit divisionUnitDom = new DivisionUnit(targetDivisionUnitVO);
            String displayedCode = projectPhaseVO.getCodeForSystemControl();
            String displayedName = projectPhaseVO.getNameForSystemControl();
            String isActive = "Y";
            attributes.put("displayedCode", displayedCode);attributes.put("displayedName", displayedName);attributes.put("sequences", sequences);attributes.put("isActive", isActive);
            divisionUnitDom.addToObject(ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES, lsit.get(0), attributes);
        }
     }
     public void copyLifeCycleAndPhase(DivisionUnitVO sourceDivisionUnitVO,DivisionUnitVO targetDivisionUnitVO){
//        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();

        StringUtil.constructWherePattern(wherePattern, parameterPattern, "To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTGRADE + "].From.names", GlobalConstants.OQL_OPERATOR_EQUAL, this.getNames());
        List<ProjectPhaseVO> phaseVOList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_DETAILEDPROJECTPHASE, ApplicationSchemaConstants.BIZCLASS_PROJECTPHASE, GlobalConstants.FLAG_TYPE_TO);
        
        for(ProjectPhaseVO phaseVO : phaseVOList){
            if(NullUtil.isNull(sourceDivisionUnitVO)){
                Map<String, Object> attributes = new HashMap<String, Object>();
                attributes.put("displayedCode", phaseVO.getTitles());
                attributes.put("displayedName", null == phaseVO.getDescriptions() ? "": phaseVO.getDescriptions());
                DetailedProjectPhase detailedProjectPhase = DomUtil.toDom((String)phaseVO.getOutData().get("rel_obid"));
                List<UsingProjectPhases> list = BusinessRelationObject.findRelationObjects(ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES, targetDivisionUnitVO.getClassName(), targetDivisionUnitVO.getObid(), GlobalConstants.FLAG_TYPE_ALL, detailedProjectPhase.getObid());
                if(NullUtil.isNone(list)) detailedProjectPhase.addFromObject(ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES, targetDivisionUnitVO, attributes);
            }else{
                /*
                 * TODO �Ʒ� ��� �̱��� �Ǿ� ����. ���� ���� 
                 */
                throw new ApplicationException("sourceDivisionUnitVO is not null(ProjectLifeCycle.java::copyLifeCycleAndPhase)");
//                List<UsingProjectPhasesVO> usingProjectPhasesVOList = projectPhaseDom.getRelationship(ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES, 
//                        ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, 
//                        GlobalConstants.FLAG_TYPE_FROM, 
//                        selectPattern.toString(), 
//                        wherePattern.toString(), 
//                        parameterPattern.toString());
//                for(UsingProjectPhasesVO relVO : usingProjectPhasesVOList){
//                    relVO.setFromObid(targetDivisionUnitVO.getObid());
//                    UsingProjectPhases usingProjectPhasesDom = new UsingProjectPhases(relVO);
//                    usingProjectPhasesDom.createObject();
//                }
            }
        }
     }
     public boolean isApplied(DivisionUnitVO divisionUnitVO){
         StringBuffer wherePattern = new StringBuffer();
         StringBuffer parameterPattern = new StringBuffer();
         StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[names]",GlobalConstants.OQL_OPERATOR_EQUAL,divisionUnitVO.getNames());
         List<DivisionUnitVO> list = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_USINGPROJECTLIFECYCLE, ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
         if(NullUtil.isNone(list)) return false;
         if(list.size() > 1) throw new FoundationException("Data Error!");
         return true;
     }
     public void applyLifeCycleAndPhase(DivisionUnitVO targetDivisionUnitVO){
         copyLifeCycleAndPhase(null,targetDivisionUnitVO);
         this.addFromObject(ApplicationSchemaConstants.RELCLASS_USINGPROJECTLIFECYCLE, targetDivisionUnitVO, null);
     }
     
     /**************************************************************************/
     public final List<ProjectPhaseVO> getProjectPhaseList(String divisionUnit, boolean activeOnly) {
         
         DivisionUnitVO divisionUnitVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, divisionUnit);
         if(NullUtil.isNull(divisionUnitVO)) return null;
         return getProjectPhaseList(divisionUnitVO,activeOnly) ;
         
     }
     public final List<ConvertedPhaseVO> getConvertedPhaseList(String divisionUnit, boolean activeOnly) {
         return convertProjectPhaseToConvertVO(getProjectPhaseList(divisionUnit,activeOnly));
     }
     /**************************************************************************/
     public final List<ProjectPhaseVO> getProjectPhaseList(DivisionUnitVO divisionUnitVO, boolean activeOnly) {
         return getProjectPhaseList(divisionUnitVO,null,activeOnly);
     }
     public final List<ConvertedPhaseVO> getConvertedPhaseList(DivisionUnitVO divisionUnitVO, boolean activeOnly) {
         return convertProjectPhaseToConvertVO(getProjectPhaseList(divisionUnitVO,activeOnly));
     }
     /**************************************************************************/
     public static final ProjectPhaseVO getProjectPhase(String lifeCycleName, DivisionUnitVO divisionUnitVO, String projectPhaseCode) {
         Set<String> projectPhaseCodeSet = new HashSet<String>();
         projectPhaseCodeSet.add(projectPhaseCode);
         List<ProjectPhaseVO> list = getProjectPhaseList(lifeCycleName,divisionUnitVO,projectPhaseCodeSet,false);
         if(NullUtil.isNone(list)) return null;
         if(list.size() > 1) throw new FoundationException("Phase(" + StrUtil.convertSet2Str(projectPhaseCodeSet) + "," + divisionUnitVO.getNames() + "," + lifeCycleName + ") Info Error!");
         return list.get(0);
     }
     public static final ConvertedPhaseVO getConvertedPhase(String lifeCycleName, DivisionUnitVO divisionUnitVO, String projectPhaseCode) {
         return convertProjectPhaseToConvertVO(getProjectPhase(lifeCycleName,divisionUnitVO,projectPhaseCode));
     }
     /**************************************************************************/
     public static final List<ProjectPhaseVO> getProjectPhaseList(String lifeCycleName, DivisionUnitVO divisionUnitVO, Set<String> projectPhaseCodeSet, boolean activeOnly) {
         ProjectLifeCycleVO projectLifeCycleVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTLIFECYCLE, lifeCycleName);
         ProjectLifeCycle projectLifeCycleDom = new ProjectLifeCycle(projectLifeCycleVO);
         if(NullUtil.isNull(projectLifeCycleVO)) return null;
         return projectLifeCycleDom.getProjectPhaseList(divisionUnitVO,projectPhaseCodeSet,activeOnly);
     }
     public static final List<ConvertedPhaseVO> getConvertedPhaseList(String lifeCycleName, DivisionUnitVO divisionUnitVO, Set<String> projectPhaseCodeSet, boolean activeOnly) {
         return convertProjectPhaseToConvertVO(getProjectPhaseList(lifeCycleName,divisionUnitVO,projectPhaseCodeSet,activeOnly));
     }
     /**************************************************************************/
     public final List<ProjectPhaseVO> getProjectPhaseList(DivisionUnitVO divisionUnitVO,  Set<String> projectPhaseCodeSet, boolean activeOnly) {
         
         StringBuffer wherePattern = new StringBuffer();
         StringBuffer parameterPattern = new StringBuffer();
         StringBuffer selectPattern = new StringBuffer();

         if(projectPhaseCodeSet != null && projectPhaseCodeSet.size() > 0){
             StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[names]",GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(projectPhaseCodeSet));
         }
         StringUtil.constructSelectParameterPattern(selectPattern, wherePattern, parameterPattern, "displayedCode"    , "REL.obid.To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES+"{fromObid==#1}].displayedCode", divisionUnitVO.getObid());
         StringUtil.constructSelectParameterPattern(selectPattern, wherePattern, parameterPattern, "displayedName"    , "REL.obid.To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES+"{fromObid==#1}].displayedName", divisionUnitVO.getObid());
         StringUtil.constructSelectParameterPattern(selectPattern, wherePattern, parameterPattern, "usingRelationObid", "REL.obid.To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES+"{fromObid==#1}].obid", divisionUnitVO.getObid());
         StringUtil.constructSelectParameterPattern(selectPattern, wherePattern, parameterPattern, "statesForDivision", "REL.obid.To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES+"{fromObid==#1}].isActive", divisionUnitVO.getObid());
         StringUtil.constructSelectPattern(selectPattern,"SortBy@REL.[sequences] asc");
         StringUtil.constructWherePattern(wherePattern, parameterPattern, "REL.obid.To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES + "].fromObid",GlobalConstants.OQL_OPERATOR_EQUAL, divisionUnitVO.getObid());
         if(activeOnly){
             StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[states]",GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);
             StringUtil.constructWherePattern(wherePattern, parameterPattern, "REL.obid.To[" + ApplicationSchemaConstants.RELCLASS_USINGPROJECTPHASES+"].isActive",GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
         }
         
         return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_DETAILEDPROJECTPHASE,
                 ApplicationSchemaConstants.BIZCLASS_PROJECTPHASE, GlobalConstants.FLAG_TYPE_TO, selectPattern.toString(),
                 wherePattern.toString(),
                 parameterPattern.toString(),
                 false,
                 false,
                 0,
                 1);   
     }
     public final Integer getProjectPhaseMaxSequence() {
         List<DetailedProjectPhaseVO> list = this.getRelationship(ApplicationSchemaConstants.RELCLASS_DETAILEDPROJECTPHASE, ApplicationSchemaConstants.BIZCLASS_PROJECTPHASE, GlobalConstants.FLAG_TYPE_TO);
         if(NullUtil.isNone(list)) return 0;
         Integer rtn = 10;
         for(DetailedProjectPhaseVO vo : list){
             if(vo.getSequences() > rtn)  rtn = vo.getSequences();
         }
         return rtn;
     }
     public final List<ConvertedPhaseVO> getConvertedPhaseList(DivisionUnitVO divisionUnitVO,  Set<String> projectPhaseCodeSet, boolean activeOnly) {
         return convertProjectPhaseToConvertVO(getProjectPhaseList(divisionUnitVO,projectPhaseCodeSet,activeOnly));
     }
     /**************************************************************************/
     private static List<ConvertedPhaseVO> convertProjectPhaseToConvertVO(List<ProjectPhaseVO> list){
         List<ConvertedPhaseVO> rList = new ArrayList<ConvertedPhaseVO>();
         for(ProjectPhaseVO vo : list){
             rList.add(convertProjectPhaseToConvertVO(vo));
         }
         return rList;
     }
     private static ConvertedPhaseVO convertProjectPhaseToConvertVO(ProjectPhaseVO vo){
         return new ConvertedPhaseVO(vo.getNames(),
                                     (String)vo.getOutDataAttributeValue("displayedCode"),
                                     (String)vo.getOutDataAttributeValue("displayedName"),
                                     (String)vo.getCodeForSystemControl(),
                                     (String)vo.getNameForSystemControl());
     }
}

