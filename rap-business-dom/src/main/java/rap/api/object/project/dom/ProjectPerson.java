/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectPerson.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.dom;


import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.SortUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.model.ProjectDefinedRoleVO;
import rap.api.object.project.model.ProjectPersonVO;
import rap.api.object.project.model.ProjectsVO;
import rap.api.object.project.model.WBSItemsVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.ProjectConstants;


public class ProjectPerson extends ProjectMembers {
    public ProjectPerson(String obid){
        super(obid);
    }
    public ProjectPerson(ProjectPersonVO vo){
        super(vo);
    }
    @Override
    public ProjectPersonVO getVo(){
        return (ProjectPersonVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeProjectPerson();
    }
    public void initializeProjectPerson(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "ProjectPerson[toString()=" + super.toString() + "]";
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
   /**
    * �ش� ����ڰ� ������ �ִ� Role List�� Return��.
    *
    * @return
    */
    public List<ProjectDefinedRoleVO> getRoleListAll(){
       return this.getRoleList(null);
    }
    /**
     * �ش� ����ڰ� ������ �ִ� Role List(�ش� Role��)�� Return��.
     *
     * @param roleCode ã�����ϴ� Role Code
     * @return
     */
    public List<ProjectDefinedRoleVO> getRoleList(String roleCode){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        if(!StrUtil.isEmpty(roleCode))  StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[roleCode]", GlobalConstants.OQL_OPERATOR_EQUAL, roleCode);
        return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ALLOCATEDMYROLE, ApplicationSchemaConstants.BIZCLASS_PROJECTDEFINEDROLE, GlobalConstants.FLAG_TYPE_TO, wherePattern.toString(), parameterPattern.toString(), 1);
    }
    
    /**
     * getRoleListForDisplay,getRoleCodeListForDisplay ��ħ
     */
    public void getRoleDefinedInfoListForDisplay(){
        List<ProjectDefinedRoleVO> list = this.getRoleListAll();
        SortUtil.sort(list, "titles",false);
        // �������� ó��??? role ����.. vo�� ����...
        StringBuffer titleBuf = new StringBuffer("");
        StringBuffer codeBuf = new StringBuffer("");
        StringBuffer isMainBuf = new StringBuffer("");
        for(ProjectDefinedRoleVO vo : list){
            titleBuf.append(vo.getTitles()).append(",");
            codeBuf.append(vo.getRoleCode()).append(",");
            
            isMainBuf.append(vo.getOutData().get("rel_isMainMember")).append(",");
        }
        
        this.getVo().getOutData().put(ProjectConstants.PROJECT_OUTDATA_ROLE_LIST, (StrUtil.isEmpty(titleBuf))?"":titleBuf.substring(0,titleBuf.lastIndexOf(",")) ) ;
        this.getVo().getOutData().put(ProjectConstants.PROJECT_OUTDATA_ROLE_CODE_LIST, (StrUtil.isEmpty(codeBuf))?"":codeBuf.substring(0,codeBuf.lastIndexOf(",")) ) ;
        this.getVo().getOutData().put(ProjectConstants.PROJECT_OUTDATA_ROLE_IS_MAIN, (StrUtil.isEmpty(isMainBuf))?"":isMainBuf.substring(0,isMainBuf.lastIndexOf(",")) ) ;
        
    }
    
    /**
     * �ش� ����ڰ� ������ �ִ� Role List�� ȭ�� ��ȸ�뵵(titles)�� Concatenate�ؼ� Return�� 
     * 20181217 radmip(���)
     * @return
     */
    public String getRoleListForDisplay(){
        List<ProjectDefinedRoleVO> list = this.getRoleListAll();
        for ( ProjectDefinedRoleVO vo : list ) {
            System.out.println("..)/ pis_main_member getRoleListForDisplay : isMainMember " + vo.getOutData().get("rel_isMainMember") + " / iaMainMember " + vo.getOutData().get("rel_isMainMember"));
        }
        SortUtil.sort(list, "titles",false);
        StringBuffer strBuf = new StringBuffer();
        for(ProjectDefinedRoleVO vo : list){
            strBuf.append(vo.getTitles()).append(",");
        }
        if(StrUtil.isEmpty(strBuf)) return "";
        return strBuf.substring(0,strBuf.lastIndexOf(","));
    }
    /**
     * 20181217 radmip (���)
     *
     * @return
     */
    public String getRoleCodeListForDisplay(){
        List<ProjectDefinedRoleVO> list = this.getRoleListAll();
        SortUtil.sort(list, "titles",false);
        StringBuffer strBuf = new StringBuffer();
        for(ProjectDefinedRoleVO vo : list){
            strBuf.append(vo.getRoleCode()).append(",");
        }
        if(StrUtil.isEmpty(strBuf)) return "";
        return strBuf.substring(0,strBuf.lastIndexOf(","));
    }
    
    private void validateUserIdChanged(Map<String, Object> map){
        BusinessObjectMaster dom = new BusinessObjectMaster(this.getVo().getObid());        
        ProjectPersonVO vo = (ProjectPersonVO)dom.getVo();
        if(!vo.getUserId().equals(this.getVo().getUserId())) throw new FoundationException("Cannot Change User ID!");
    }
    private void validateParamter(Map<String, Object> map){
        UsersVO userVO = (UsersVO)map.get(ProjectConstants.CREATE_CREATE_MAP_USERS);
        if(NullUtil.isNull(userVO)) throw new FoundationException("User Info is null");
    }
    private void validateUserId(Map<String, Object> map){
        if(StrUtil.isEmpty(this.getVo().getUserId())) throw new FoundationException("User ID cannot be empty.");
    }

    private void isAlreadyExists(Map<String, Object> map){
        String names = makeNames(map);
        ProjectDefinedRoleVO vo = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_PROJECTDEFINEDROLE, names);
        if(!NullUtil.isNull(vo)) throw new FoundationException("Role(" + names + ") is already exists.");
    } 
    private String makeNames(Map<String, Object> map){
        ProjectsVO projectVO = (ProjectsVO)map.get(ProjectConstants.CREATE_CREATE_MAP_PROJECT);
        return(projectVO.getNames() + "-" + this.getVo().getUserId());
    }
    public List<WBSItemsVO> getAssignedActivityList(String projectScheduleObid){
        return getAssignedActivityList(projectScheduleObid, null);
    }
    public List<WBSItemsVO> getAssignedActivityList(String projectScheduleObid, Set<String> stateFilter){
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[isSkipped]", GlobalConstants.OQL_OPERATOR_EQUAL, "N" );
        
        if( stateFilter != null && stateFilter.size() > 0){
            StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(stateFilter));
        }
        
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "From["+ApplicationSchemaConstants.RELCLASS_CONTROLLEDBYPROJECTSCHEDULECONTEXT+"].toObid", GlobalConstants.OQL_OPERATOR_EQUAL, projectScheduleObid );
        return this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_ASSIGNEDTOACTIVITY, ApplicationSchemaConstants.BIZCLASS_WBSITEMS,
                GlobalConstants.FLAG_TYPE_FROM, wherePattern.toString(), parameterPattern.toString(), 1);
    }
}

