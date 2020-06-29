/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectDefinedRole.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.project.model.ProjectDefinedRoleVO;
import rap.api.object.project.model.ProjectPersonVO;
import rap.api.object.project.model.ProjectRoleVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.relation.dom.AllocatedMyRole;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;


public class ProjectDefinedRole extends ProjectMembers {
    public ProjectDefinedRole(String obid){
        super(obid);
    }
    public ProjectDefinedRole(ProjectDefinedRoleVO vo){
        super(vo);
    }
    @Override
    public ProjectDefinedRoleVO getVo(){
        return (ProjectDefinedRoleVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeProjectDefinedRole();
    }
    public void initializeProjectDefinedRole(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "ProjectDefinedRole[toString()=" + super.toString() + "]";
    }


    @Override
    protected void validateForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.validateForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/
        this.validateRoleCode(map);
        this.validateRoleCodeChanged(map);
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
        this.validateRoleCode(map);
        this.isAlreadyExists(map);
    }

   @Override
    protected void preProcessForCreate(Map<String, Object> map){
        super.preProcessForCreate(map);
        /*code below*/
        this.getVo().setNames(this.makeNames(map));
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
   public void allocateRoleForUser(List<ProjectPersonVO> list){
       for(ProjectPersonVO vo : list){
           this.allocateRoleForUser(vo);
       }
   }
   public void allocateRoleForUser(ProjectPersonVO personVO){
       this.addFromObject(ApplicationSchemaConstants.RELCLASS_ALLOCATEDMYROLE, personVO, new HashMap<String,Object>());
   }
   public void allocateRoleForUser(ProjectPersonVO personVO, String isMainMember, String workingType){
       HashMap<String,Object> map =  new HashMap<String,Object>();
       map.put("isMainMember", isMainMember);
       map.put("workingType", workingType);
       if(!NullUtil.isNull(personVO))
       this.addFromObject(ApplicationSchemaConstants.RELCLASS_ALLOCATEDMYROLE, personVO, map);
   }
   public void removeUserForRole(String userId, boolean deleteProjectPerson){
       List<ProjectPersonVO> memberList = this.getMemberList(userId);
       if(deleteProjectPerson){
           for(ProjectPersonVO vo : memberList){
               ProjectPerson projectPersonDom = DomUtil.toDom(vo);
               projectPersonDom.deleteObject();
           }
       }else{
           for(ProjectPersonVO vo : memberList){
               AllocatedMyRole relDom = new AllocatedMyRole((String)vo.getOutData().get("rel_obid"));
               relDom.deleteObject();
           }
       }
   }
   public boolean isMember(){
       return false;
   }
   public List<ProjectPersonVO> getMemberList(){
       return getMemberList(null);
   }
   public List<ProjectPersonVO> getMemberList(String userId){
       return getMemberList(userId, null);
   }
   public List<ProjectPersonVO> getMemberList(String userId, String isMainMember){
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       if(!StrUtil.isEmpty(userId)) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[userId]", GlobalConstants.OQL_OPERATOR_EQUAL, userId);
       if(!StrUtil.isEmpty(isMainMember)) StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[isMainMember]", GlobalConstants.OQL_OPERATOR_EQUAL, isMainMember);
       return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ALLOCATEDMYROLE, ApplicationSchemaConstants.BIZCLASS_PROJECTPERSON,  GlobalConstants.FLAG_TYPE_FROM, selectPattern.toString(), wherePattern.toString(), paramPattern.toString(), false, false, 0, 1);
   }
   private void validateRoleCodeChanged(Map<String, Object> map){
       BusinessObjectMaster dom = new BusinessObjectMaster(this.getVo().getObid());        
       ProjectDefinedRoleVO vo = (ProjectDefinedRoleVO)dom.getVo();
       if(!vo.getRoleCode().equals(this.getVo().getRoleCode())) throw new FoundationException("Cannot Change Role Code!");
   }
   private void validateRoleCode(Map<String, Object> map){
       if(StrUtil.isEmpty(this.getVo().getRoleCode())) throw new FoundationException("Role Code cannot be empty.");
   }
   private void isAlreadyExists(Map<String, Object> map){
       String names = makeNames(map);
       ProjectDefinedRoleVO vo = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTDEFINEDROLE, names);
       if(!NullUtil.isNull(vo)) throw new FoundationException("Role(" + names + ") is already exists.");
   } 
   private String makeNames(Map<String, Object> map){
       ProjectsVO projectVO = (ProjectsVO)map.get(ProjectConstants.CREATE_CREATE_MAP_PROJECT);
       return(projectVO.getNames() + "-" + this.getVo().getRoleCode());
   }
   
   public String getDisplayNameAsLocale(){
       ProjectRoleVO projectRoleVO = ProjectRole.getProjectRoleVO(getVo().getRoleCode());
       ProjectRole projectRole = new ProjectRole(projectRoleVO);
       return projectRole.getDisplayNameAsLocale();
   }
}

