/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AbstractUsers.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.dom;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.bizplan.model.BizPlanProjectVO;
import rap.api.object.organization.model.AbstractUsersVO;
import rap.application.constants.ApplicationSchemaConstants;


public class AbstractUsers extends BusinessObjectMaster {
    public AbstractUsers(String obid){
        super(obid);
    }
    public AbstractUsers(AbstractUsersVO vo){
        super(vo);
    }
    @Override
    public AbstractUsersVO getVo(){
        return (AbstractUsersVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeAbstractUsers();
    }
    public void initializeAbstractUsers(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "AbstractUsers[toString()=" + super.toString() + "]";
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
   public String retrieveUserPlanProjectList( String versionObid ){
       List<BizPlanProjectVO> projectVOList = new ArrayList<BizPlanProjectVO>();
       HashSet<String> obidSet = new HashSet<String>();
       
       if( !NullUtil.isNone( versionObid ) && !"".equals( versionObid ) ){
           projectVOList.addAll( this.retrieveCowokerPlanProjectList( versionObid ) );
           projectVOList.addAll( this.retrieveLeaderPlanProjectList( versionObid ) );
           projectVOList.addAll( this.retrieveCreatorPlanProjectList( versionObid ) );
       }
       
       String authProjectList = "none";
       if( projectVOList.size() > 0 ) authProjectList = "";
       
       //중복제거
       for( int inx = 0; inx < projectVOList.size(); inx++ ){
           obidSet.add(projectVOList.get(inx).getObid());
       }
       
       for( String obid :  obidSet ){
           if ( "".equals(authProjectList) ) {
               authProjectList = obid;
           } else {
               authProjectList += "," + obid;
           }
       }

       return authProjectList;
   }
   
   /**
    * User가 Co-worker인 사업계획 프로젝트 조회
    * @return
    */
   public List<BizPlanProjectVO> retrieveCowokerPlanProjectList( String versionObid  ){
       List<BizPlanProjectVO> projectList = null;
       
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer fromPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       StringUtil.constructSelectPattern(selectPattern, "@USR.[descriptions] userDesc");
       StringUtil.constructSelectPattern(selectPattern, "@USR.[titleName] titleName");
       fromPattern.append("<this>ThisConnectedWithTo<[HasVersionProject]@HVP>+");
       fromPattern.append("<[HasPlanProjectCoworker]@HPC>FromConnectedWithThis<this>+");
       fromPattern.append("<[AbstractUsers]@USR>ThisConnectedWithTo<[HasPlanProjectCoworker]@HPC>+");
       fromPattern.append("<[HasVersionProject]@HVP>FromConnectedWithThis<[BizPlanVersion]@VER>+");
       
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@VER.[obid]",GlobalConstants.OQL_OPERATOR_IN, versionObid);
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@USR.[obid]",GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getObid());
       
       projectList = ObjectRoot.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_BIZPLANPROJECT,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               selectPattern.toString(),
               fromPattern.toString(),
               wherePattern.toString(),
               paramPattern.toString(),
               false,
               0);
       
//       projectList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASPLANPROJECTCOWORKER, ApplicationSchemaConstants.BIZCLASS_BIZPLANPROJECT, GlobalConstants.FLAG_TYPE_FROM);
       return projectList;
   }
   
   /**
    * User가 Co-worker인 사업계획 프로젝트 조회( project 조건 )
    * @return
    */
   public List<BizPlanProjectVO> retrieveCowokerPlanProjectListByProject( List<BizPlanProjectVO> pjtCondList ){
       String pjtObids = "";
       if( !NullUtil.isNull( pjtCondList ) ){
           for( int inx = 0; inx < pjtCondList.size(); inx++ ){
               if ( "".equals(pjtObids) ) {
                   pjtObids = pjtCondList.get(inx).getObid();
               } else {
                   pjtObids += "," + pjtCondList.get(inx).getObid();
               }
           }
       }
       
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer fromPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       StringUtil.constructSelectPattern(selectPattern, "@USR.[descriptions] userDesc");
       StringUtil.constructSelectPattern(selectPattern, "@USR.[titleName] titleName");
       fromPattern.append("<this>ThisConnectedWithFrom<[HasPlanProjectCoworker]@HPC>+");
       fromPattern.append("<[HasPlanProjectCoworker]@HPC>ToConnectedWithThis<[AbstractUsers]@USR>+");
       
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]",GlobalConstants.OQL_OPERATOR_IN, pjtObids);
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@USR.[obid]",GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getObid());
       
       List<BizPlanProjectVO> projectList = ObjectRoot.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_BIZPLANPROJECT,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               selectPattern.toString(),
               fromPattern.toString(),
               wherePattern.toString(),
               paramPattern.toString(),
               false,
               0);
       
//       projectList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASPLANPROJECTCOWORKER, ApplicationSchemaConstants.BIZCLASS_BIZPLANPROJECT, GlobalConstants.FLAG_TYPE_FROM);
       return projectList;
   }

   /**
    *User가 PL인 사업계획 프로젝트 조회
    * @return
    */
   public List<BizPlanProjectVO> retrieveLeaderPlanProjectList( String versionObid ){
       List<BizPlanProjectVO> projectList = null;
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer fromPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       StringUtil.constructSelectPattern(selectPattern, "@USR.[descriptions] userDesc");
       StringUtil.constructSelectPattern(selectPattern, "@USR.[titleName] titleName");
       fromPattern.append("<this>ThisConnectedWithTo<[HasVersionProject]@HVP>+");
       fromPattern.append("<[HasPlanProjectLeader]@HPL>FromConnectedWithThis<this>+");
       fromPattern.append("<[AbstractUsers]@USR>ThisConnectedWithTo<[HasPlanProjectLeader]@HPL>+");
       fromPattern.append("<[HasVersionProject]@HVP>FromConnectedWithThis<[BizPlanVersion]@VER>+");
       
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@VER.[obid]",GlobalConstants.OQL_OPERATOR_IN, versionObid);
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@USR.[obid]",GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getObid());
       
       projectList = ObjectRoot.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_BIZPLANPROJECT,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               selectPattern.toString(),
               fromPattern.toString(),
               wherePattern.toString(),
               paramPattern.toString(),
               false,
               0);
       
//       projectList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASPLANPROJECTLEADER, ApplicationSchemaConstants.BIZCLASS_BIZPLANPROJECT, GlobalConstants.FLAG_TYPE_FROM);
       
       return projectList;
   }

   /**
    *User가 작성자인 사업계획 프로젝트 조회
    * @return
    */
   public List<BizPlanProjectVO> retrieveCreatorPlanProjectList( String versionObid ){
       List<BizPlanProjectVO> projectList = null;
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer fromPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       StringUtil.constructSelectPattern(selectPattern, "@VER.[titles] versionTitles");
       fromPattern.append("<this>ThisConnectedWithTo<[HasVersionProject]@HVP>+");
       fromPattern.append("<[HasVersionProject]@HVP>FromConnectedWithThis<[BizPlanVersion]@VER>+");
       
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@VER.[obid]",GlobalConstants.OQL_OPERATOR_IN, versionObid);
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[creator]",GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getNames());
       
       projectList = ObjectRoot.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_BIZPLANPROJECT,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               selectPattern.toString(),
               fromPattern.toString(),
               wherePattern.toString(),
               paramPattern.toString(),
               false,
               0);
       
       return projectList;
   }
}

