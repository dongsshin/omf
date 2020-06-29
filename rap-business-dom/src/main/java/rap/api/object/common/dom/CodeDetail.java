/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CodeDetail.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.dom;


import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObject;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.util.StringUtil;

import rap.api.object.common.model.CodeDetailVO;
import rap.application.constants.ApplicationSchemaConstants;


public class CodeDetail extends BusinessObject {
    public CodeDetail(String obid){
        super(obid);
    }
    public CodeDetail(CodeDetailVO vo){
        super(vo);
    }
    @Override
    public CodeDetailVO getVo(){
        return (CodeDetailVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeCodeDetail();
    }
    public void initializeCodeDetail(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "CodeDetail[toString()=" + super.toString() + "]";
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

   @Override
    protected void validateForRevise(Map<String, Object> map){
        super.validateForRevise(map);
        /*code below*/

    }

   @Override
    protected void preProcessForRevise(Map<String, Object> map){
        super.preProcessForRevise(map);
        /*code below*/

    }

   @Override
    protected void postProcessForRevise(Map<String, Object> map){
        super.postProcessForRevise(map);
        /*code below*/

    }
   public static CodeDetailVO getCodeDetail(String codeMaster, String code){

       StringBuffer fromPattern = new StringBuffer();
       fromPattern.append("<this>ThisConnectedWithTo<[CodeMaster2Code]@REL>+");
       fromPattern.append("<[CodeMaster2Code]@REL>FromConnectedWithThis<[CodeMaster]@MASTER>+");
       
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@MASTER.[names]",
               GlobalConstants.OQL_OPERATOR_EQUAL, codeMaster);
       
       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]",
               GlobalConstants.OQL_OPERATOR_EQUAL, code);

       List<CodeDetailVO> result = ObjectRoot.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_CODEDETAIL,
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
               "SortBy@this.[sequences]",
               fromPattern.toString(),
               wherePatternBuf.toString(),
               paramPatternBuf.toString(),
               true,
               0
       );
       if(result.size() > 0){
           return result.get(0);
       }
       return null;
   }
}

