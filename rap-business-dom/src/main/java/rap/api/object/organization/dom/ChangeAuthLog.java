/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ChangeAuthLog.java
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

import rap.api.object.organization.model.ChangeAuthLogVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.CodeConstants;


public class ChangeAuthLog extends BusinessObjectMaster {
    public ChangeAuthLog(String obid){
        super(obid);
    }
    public ChangeAuthLog(ChangeAuthLogVO vo){
        super(vo);
    }
    @Override
    public ChangeAuthLogVO getVo(){
        return (ChangeAuthLogVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeChangeAuthLog();
    }
    public void initializeChangeAuthLog(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "ChangeAuthLog[toString()=" + super.toString() + "]";
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
   public static List<ChangeAuthLogVO> getAuthLogList(ChangeAuthLogVO changeAuthLogVO ){

	    StringBuffer selectPattern = new StringBuffer();
       StringBuffer wherePattern  = new StringBuffer();
       StringBuffer paramPattern  = new StringBuffer();
       StringBuffer fromPattern   = new StringBuffer();

       fromPattern.append("<this>FromConnectedWithThis<[PlantUnit]@PU>");
       StringUtil.constructSelectPattern(selectPattern, "@PU.[descriptions] plantDesc");
       StringUtil.constructSelectPattern(selectPattern, "FN_GET_CODEDETAIL(@this.[authName],'" + CodeConstants.CODE_NAME_AUTH_TYPE + "')");        
       StringUtil.constructSelectPattern(selectPattern, "\"getUserInfo\"(@this.[person],1) personName");
       StringUtil.constructSelectPattern(selectPattern, "SortBy@this.[created] DESC");
       
       if( !StrUtil.isEmpty( changeAuthLogVO.getDivisionName() ) ){
           StringUtil.constructWherePattern( wherePattern, paramPattern,
           "@PU.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, changeAuthLogVO.getDivisionName() );
       }
       else{
           if( !StrUtil.isEmpty( changeAuthLogVO.getOutDataStringValue("ivisions")) ){
               StringUtil.constructWherePattern( wherePattern, paramPattern, 
                   "@PU.[names]", GlobalConstants.OQL_OPERATOR_LIKE, changeAuthLogVO.getOutDataStringValue("ivisions") + GlobalConstants.FLAG_TYPE_ALL);
           }            
       }
       
       if( !StrUtil.isEmpty( changeAuthLogVO.getOutDataStringValue("authTypes"))){
           StringUtil.constructWherePattern( wherePattern, paramPattern,
               "@this.[authName]", GlobalConstants.OQL_OPERATOR_EQUAL, changeAuthLogVO.getOutDataStringValue("authTypes"));
       }
       
       if( !StrUtil.isEmpty( changeAuthLogVO.getOutDataStringValue("personId"))){
           StringUtil.constructWherePattern( wherePattern, paramPattern,
               "@this.[person]", GlobalConstants.OQL_OPERATOR_EQUAL, changeAuthLogVO.getOutDataStringValue("personId"));
       }        

       if( !StrUtil.isEmpty( changeAuthLogVO.getOpType())){
           StringUtil.constructWherePattern( wherePattern, paramPattern,
               "@this.[opType]", GlobalConstants.OQL_OPERATOR_EQUAL, changeAuthLogVO.getOpType());
       } 
       //CommonUtil.convertDate("@this.[created]", voSearch.getStartDate(), voSearch.getEndDate(),"BT", wherePattern, paramPattern );

//       if( StrUtil.getStartDate() != null && !StringUtils.isEmpty( changeAuthLogVO.getStartDate() ) ){
//           StringUtil.constructWherePattern( wherePattern, paramPattern,
//               "@this.[created]", GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, changeAuthLogVO.getStartDate() );
//       }
       
//       if( voSearch.getEndDate() != null && !StringUtils.isEmpty( changeAuthLogVO.getEndDate() ) ){
//           StringUtil.constructWherePattern( wherePattern, paramPattern,
//                   "@this.[created]", GlobalConstants.OQL_OPERATOR_LESS_THAN, changeAuthLogVO.getEndDate() );
//       }

       if( !StrUtil.isEmpty( changeAuthLogVO.getRoleName() ) ){
           StringUtil.constructWherePattern( wherePattern, paramPattern, 
               "@this.[roleName]",
               GlobalConstants.OQL_OPERATOR_LIKE, 
               GlobalConstants.FLAG_TYPE_ALL + changeAuthLogVO.getRoleName() + GlobalConstants.FLAG_TYPE_ALL );
       }

//       List<ChangeAuthLogVO> result = ObjectRoot.searchObjectPagingList(
//               ApplicationSchemaConstants.BIZCLASS_CHANGEAUTHLOG,
//               GlobalConstants.FLAG_TYPE_ALL,  // namePattern
//               GlobalConstants.FLAG_TYPE_ALL,  // revisionPattern
//               selectPattern.toString(),       // selectPattern
//               fromPattern.toString(),         // fromPattern
//               wherePattern.toString(),        // wherePattern
//               paramPattern.toString(),        // parameterPattern
//               false,
//               voSearch.getTargetRow(),
//               voSearch.getRowSize());
//   }
       return ObjectRoot.searchObjects(ApplicationSchemaConstants.BIZCLASS_CODEDETAIL, selectPattern.toString(), fromPattern.toString(),wherePattern.toString(),paramPattern.toString());
   }
}

