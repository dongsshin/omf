/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ManagementGroup.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.dom;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rap.omc.api.object.dom.BusinessObject;
import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.oql.model.OmcOQLCondition;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.api.util.general.RelationShip;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
import com.rap.omc.util.TimeServiceUtil;

import rap.api.object.bizplan.model.AccountVO;
import rap.api.object.bizplan.model.AssetMajorVO;
import rap.api.object.bizplan.model.AssetMinorVO;
import rap.api.object.bizplan.model.BizPlanVersionVO;
import rap.api.object.bizplan.model.BizTripCostVO;
import rap.api.object.bizplan.model.BudgetActivityVO;
import rap.api.object.bizplan.model.InvestObjectVO;
import rap.api.object.bizplan.model.InvestProjectSubGroupVO;
import rap.api.object.bizplan.model.ManagementGroupListVO;
import rap.api.object.bizplan.model.ManagementGroupVO;
import rap.api.object.common.dom.CodeMaster;
import rap.api.object.common.model.CodeDetailVO;
import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.AccountingDepartmentVO;
import rap.api.object.organization.model.DepartmentVO;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.relation.dom.HasInvestObject;
import rap.api.object.relation.dom.HasInvestProjectGroup;
import rap.api.object.relation.dom.HasManagementGroupAccount;
import rap.api.object.relation.dom.HasManagementGroupAccountDept;
import rap.api.object.relation.dom.HasManagementGroupDept;
import rap.api.object.relation.dom.HasProjectSubGroup;
import rap.api.object.relation.model.HasManagementGroupAccountVO;
import rap.api.object.relation.model.HasProjectSubGroupVO;
import rap.application.constants.ApplicationSchemaConstants;


public class ManagementGroup extends BusinessObjectMaster {
    public ManagementGroup(String obid){
        super(obid);
    }
    public ManagementGroup(ManagementGroupVO vo){
        super(vo);
    }
    @Override
    public ManagementGroupVO getVo(){
        return (ManagementGroupVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeManagementGroup();
    }
    public void initializeManagementGroup(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "ManagementGroup[toString()=" + super.toString() + "]";
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

   /*mgtGroupCode�� ManagementGroupVO findOject ��ȸ*/
   public static ManagementGroupVO managementGroupFindMgtGroupCode (String mgtGroupCode){
       return ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP, mgtGroupCode, false);
   }

   /*�����׷�������� > �����׷� ����*/
   public ManagementGroupVO updateManageGroup(ManagementGroupVO managementGroupVO){
       this.getVo().setPlanType(managementGroupVO.getPlanType());
       this.getVo().setDiverYn(managementGroupVO.getDiverYn());
       this.getVo().setAutoCalculateYn(managementGroupVO.getAutoCalculateYn());
       this.getVo().setCommonProjectUseYn(managementGroupVO.getCommonProjectUseYn());
       this.getVo().setBusinessUnitCode(managementGroupVO.getBusinessUnitCode());

       this.modifyObject();
       return this.getVo();
   }

   /**
    * �����׷� ���� Update
    * @param managementGroupVO
    * @param departmentVO
    * @param corporationCurrencylist
    * @return
    */
   public ManagementGroupVO updateManagementGroup(ManagementGroupVO managementGroupVO, DepartmentVO departmentVO, AccountingDepartmentVO accountDepartmentVO, List<CodeDetailVO> corporationCurrencylist){
       this.getVo().setPlanYear( managementGroupVO.getPlanYear() );
       this.getVo().setTitles( managementGroupVO.getTitles() );
       this.getVo().setDescriptions( managementGroupVO.getTitles() );
       this.getVo().setFromObid( managementGroupVO.getFromObid() );
       this.getVo().setUseYn( managementGroupVO.getUseYn() );

       if ( departmentVO != null && corporationCurrencylist.size() > 0 ) {
           if ( "".equals(managementGroupVO.getCorporationCode()) || managementGroupVO.getCorporationCode() == null
                   || "".equals(managementGroupVO.getCurrencyCode()) || managementGroupVO.getCurrencyCode() == null ) {
               if ( accountDepartmentVO != null ) {
                   this.getVo().setCorporationCode(accountDepartmentVO.getLegalEntityName());
               } else {
                   if ( "".equals(departmentVO.getAffiliateCode()) || departmentVO.getAffiliateCode() == null ) {
                       this.getVo().setCurrencyCode(corporationCurrencylist.get(0).getAttribute02());
                       this.getVo().setFaCorporationCode(corporationCurrencylist.get(0).getAttribute01());
                   } else {
                       this.getVo().setCurrencyCode(departmentVO.getAffiliateCode());
                   }
               }
           } else {
               this.getVo().setCurrencyCode(managementGroupVO.getCurrencyCode());
               this.getVo().setCorporationCode(managementGroupVO.getCorporationCode());
           }
       }

       this.modifyObject();
       return this.getVo();
  }

   /*�����׷�������� > ��ȹ������� �ߺ� üũ */
   public ManagementGroupVO validateForManagementgroup(ManagementGroupVO managementVO){

       StringBuffer selectPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();

       StringUtil.constructWherePattern(wherePattern, paramPattern, "IFNULL(@this.[planType],'null')", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "null");
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, managementVO.getNames());

       ManagementGroupVO result = ObjectRoot.findObject(
                   ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP,
                   GlobalConstants.FLAG_TYPE_ALL,
                   GlobalConstants.FLAG_TYPE_ALL,
                   selectPattern.toString(),
                   wherePattern.toString(),
                   paramPattern.toString(),
                   false
                   );

       return result;
   }


   /*�����׷�������� > copy*/
   public void copyGroupRelation(String fromMgtGroup, String toMgtGroup,String checkInfo){

       ManagementGroupVO fromMgmtGroupVO = managementGroupFindMgtGroupCode(fromMgtGroup);
       String fromObid = fromMgmtGroupVO.getObid();

       // 1. ��ȹ�������/�����������Կ��� copy
       ManagementGroupVO toMgmtGroupVO = managementGroupFindMgtGroupCode(toMgtGroup);
       toMgmtGroupVO.setPlanType(fromMgmtGroupVO.getPlanType());
       toMgmtGroupVO.setDiverYn(fromMgmtGroupVO.getDiverYn());
       toMgmtGroupVO.setAutoCalculateYn(fromMgmtGroupVO.getAutoCalculateYn());
       toMgmtGroupVO.setCommonProjectUseYn(fromMgmtGroupVO.getCommonProjectUseYn());
       ManagementGroup tDom = (ManagementGroup)DomUtil.toDom(toMgmtGroupVO);
       tDom.modifyObject();

       // 2.üũ�׺� copy (R1:������뿩��/ R2:��������ܰ� / R3:�ؿ�����ܰ� / R4:���ڼ��� / R5:�������)
       ManagementGroup mg = DomUtil.toDom(fromObid);

       String[] array;
       array = checkInfo.split(",");

       for (int i = 0; i < array.length; i++){

           //������뿩��
           if("R1".equals(array[i])){

              //������뿩�� to relationShip ����
               StringBuffer selectPattern = new StringBuffer();
               StringBuffer wherePattern = new StringBuffer();
               StringBuffer paramPattern = new StringBuffer();
               StringBuffer fromPattern = new StringBuffer();

               fromPattern.append("<this>ThisConnectedWithTo<[HasManagementGroupAccount]@REL>+");
               fromPattern.append("<[HasManagementGroupAccount]@REL>FromConnectedWithThis<[ManagementGroup]@mgtg>+");

               StringUtil.constructSelectPattern(selectPattern, "@REL.[obid] relObid");

               StringUtil.constructWherePattern(wherePattern, paramPattern, "@mgtg.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, toMgtGroup);

               List<AccountVO> accountList = ObjectRoot.searchObjects( ApplicationSchemaConstants.BIZCLASS_ACCOUNT,
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
                       fromPattern.toString(),
                       wherePattern.toString(),
                       paramPattern.toString(),
                       true,
                       0);


               for( AccountVO aVO : accountList){
                   HashMap<String,Object> outData = null;//new HashMap<String, Object>();
                   outData = aVO.getOutData();

                   //����/�ؿ� ����ܰ� to relationShip ����
                   HasManagementGroupAccount relDeleteDom = new HasManagementGroupAccount ((String)outData.get("relObid"));
                   relDeleteDom.deleteObject();
               }

              //���� ��뿩�� relationShip copy
               selectPattern = new StringBuffer();
               wherePattern = new StringBuffer();
               paramPattern = new StringBuffer();
               fromPattern = new StringBuffer();

               fromPattern.append("<this>ThisConnectedWithTo<[HasManagementGroupAccount]@REL>+");
               fromPattern.append("<[HasManagementGroupAccount]@REL>FromConnectedWithThis<[ManagementGroup]@mgtg>+");

               StringUtil.constructSelectPattern(selectPattern, "@REL.[obid] relObid");
               StringUtil.constructSelectPattern(selectPattern, "@REL.[useFlag] relUseFlag");
               StringUtil.constructSelectPattern(selectPattern, "@REL.[bgtControlType] relBgtControlType");
               StringUtil.constructSelectPattern(selectPattern, "@REL.[diverControlType] relDiverControlType");
               StringUtil.constructSelectPattern(selectPattern, "@REL.[budgetInputType] relBudgetInputType");
               StringUtil.constructSelectPattern(selectPattern, "@REL.[budgetInputActivityType] relBudgetInputActivityType");
               StringUtil.constructSelectPattern(selectPattern, "@REL.[bmsIfYn] relBmsIfYn");
               StringUtil.constructSelectPattern(selectPattern, "@REL.[budgetInputProjectType] relBudgetInputProjectType");

               StringUtil.constructWherePattern(wherePattern, paramPattern, "@mgtg.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, fromMgtGroup);

               List<AccountVO> copyAccountList = ObjectRoot.searchObjects( ApplicationSchemaConstants.BIZCLASS_ACCOUNT,
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
                       fromPattern.toString(),
                       wherePattern.toString(),
                       paramPattern.toString(),
                       true,
                       0);

               for( AccountVO aVO : copyAccountList){
                   HashMap<String,Object> outData = null;//new HashMap<String, Object>();
                   outData = aVO.getOutData();

                   //����/�ؿ� ����ܰ� relationShip copy
                   HasManagementGroupAccount relCopyDom = new HasManagementGroupAccount ((String)outData.get("relObid"));
                   relCopyDom.getVo().setFromObid(this.getVo().getObid());
                   relCopyDom.getVo().setUseFlag((String)outData.get("relUseFlag"));
                   relCopyDom.getVo().setBgtControlType((String)outData.get("relBgtControlType"));
                   relCopyDom.getVo().setDiverControlType((String)outData.get("relDiverControlType"));
                   relCopyDom.getVo().setBudgetInputType((String)outData.get("relBudgetInputType"));
                   relCopyDom.getVo().setBudgetInputActivityType((String)outData.get("relBudgetInputActivityType"));
                   relCopyDom.getVo().setBmsIfYn((String)outData.get("relBmsIfYn"));
                   relCopyDom.getVo().setBudgetInputProjectType((String)outData.get("relBudgetInputProjectType"));
                   relCopyDom.createObject();
               }

           }
           //����/�ؿ� ����ܰ�
           else if("R2".equals(array[i]) || "R3".equals(array[i])){
               String localYn = "Y";
               if("R3".equals(array[i])){ localYn = "N"; }

               StringBuffer selectPattern = new StringBuffer();
               StringBuffer wherePattern = new StringBuffer();
               StringBuffer paramPattern = new StringBuffer();
               StringBuffer fromPattern = new StringBuffer();

               fromPattern.append("<this>ThisConnectedWithTo<[HasBizTripCost]@REL>+");
               fromPattern.append("<[HasBizTripCost]@REL>FromConnectedWithThis<[ManagementGroup]@mgtg>+");

               StringUtil.constructSelectPattern(selectPattern, "@REL.[obid] relObid");

               StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[localYn]", GlobalConstants.OQL_OPERATOR_EQUAL, localYn);
               StringUtil.constructWherePattern(wherePattern, paramPattern, "@mgtg.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, toMgtGroup);

               List<BizTripCostVO> tripList = ObjectRoot.searchObjects( ApplicationSchemaConstants.BIZCLASS_BIZTRIPCOST,
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
                       fromPattern.toString(),
                       wherePattern.toString(),
                       paramPattern.toString(),
                       true,
                       0);

               for( BizTripCostVO tVO : tripList){
                   //HashMap<String,Object> outData = null;//new HashMap<String, Object>();
                   //outData = tVO.getOutData();

                   //����/�ؿ� ����ܰ� to relationShip ����
                   //HasBizTripCost relDeleteDom = new HasBizTripCost ((String)outData.get("relObid"));
                   //relDeleteDom.deleteObject();

                   //����/�ؿ� ����ܰ� to data ����
                   BizTripCost deleteDom = new BizTripCost( tVO.getObid() );
                   deleteDom.deleteObject();
               }

               List<BizTripCostVO>  hasBizCostList = mg.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASBIZTRIPCOST,ApplicationSchemaConstants.BIZCLASS_BIZTRIPCOST, GlobalConstants.FLAG_TYPE_TO);
               for( int j = 0; j < hasBizCostList.size(); j++){
                   BizTripCostVO rVO = hasBizCostList.get(j);

                   if(localYn.equals(rVO.getLocalYn())){
                       BizTripCost btc = DomUtil.toDom(rVO.getObid());

                       //����/�ؿ� ����ܰ� data ����
                       btc.getVo().setNames(NameGeneratorUtil.generateUniqueName("BIZ-TRIPCOST"));//Name Change
                       btc.createObject();

                      //����/�ؿ� ����ܰ� relationShip copy
                       this.addRelatedObject(ApplicationSchemaConstants.RELCLASS_HASBIZTRIPCOST, btc.getVo(), null, false);
                   }
               }
           }
           //���ڼ���
           else if("R4".equals(array[i])){

               //2017.10.26 ���ϳ⵵�� �ƴѰ��, ���ڼ���> ������Ʈ �׷� copy ����
               String fromPlanYear = fromMgmtGroupVO.getPlanYear();
               String toPlanYear = toMgmtGroupVO.getPlanYear();

               if(toPlanYear.equals(fromPlanYear)){
                   /*������Ʈ �׷�*/
                   //0. ������Ʈ �׷� ��ȸ
                   HasProjectSubGroupVO hasProjectSubGroupVO = new HasProjectSubGroupVO();
                   HasProjectSubGroup proDom = (HasProjectSubGroup)DomUtil.toDom(hasProjectSubGroupVO);

                   List<HasProjectSubGroupVO> fromProList = new ArrayList<HasProjectSubGroupVO>();
                   List<HasProjectSubGroupVO> toProList = new ArrayList<HasProjectSubGroupVO>();

//
//                   List<HasProjectSubGroupVO> fromProList = proDom.hasProjectSubGroupList("", fromMgtGroup);
//                   List<HasProjectSubGroupVO> toProList = proDom.hasProjectSubGroupList("", toMgtGroup);

                   
                   
                   for( HasProjectSubGroupVO pVO : toProList){
                       HashMap<String,Object> outData = null;
                       outData = pVO.getOutData();

                       //HasInvestProjectGroup relationShip ����
                       HasInvestProjectGroup mgtRelDelDom = new HasInvestProjectGroup ((String)outData.get("mgtRelObid"));
                       mgtRelDelDom.deleteObject();
                   }

                   for( HasProjectSubGroupVO pVO : fromProList){
                      //HasInvestProjectGroup relationShip ����
                       RelationShip.connect(ApplicationSchemaConstants.RELCLASS_HASINVESTPROJECTGROUP, this.getVo().getObid(),pVO.getToObid(), null);
                   }
               }

               /*���ڸ���*/
              //0. ���ڸ��� ��ȸ
               InvestObjectVO investObjectVO = new InvestObjectVO();
               InvestObject objDom = (InvestObject)DomUtil.toDom(investObjectVO);

//               List<InvestObjectVO> fromObjList = objDom.investObjectList("", fromMgtGroup);
//               List<InvestObjectVO> toObjList = objDom.investObjectList("", toMgtGroup);

               List<InvestObjectVO> fromObjList = new ArrayList<InvestObjectVO>();
               List<InvestObjectVO> toObjList = new ArrayList<InvestObjectVO>();
               
               
               for( InvestObjectVO oVO : toObjList){
                   HashMap<String,Object> outData = null;//new HashMap<String, Object>();
                   outData = oVO.getOutData();

                   //HasInvestObject relationShip ����
                   HasInvestObject relDeleteDom = new HasInvestObject ((String)outData.get("relObid"));
                   relDeleteDom.deleteObject();
               }

               for( InvestObjectVO oVO : fromObjList){
                   //HasInvestObject relationShip ����
                   RelationShip.connect(ApplicationSchemaConstants.RELCLASS_HASINVESTOBJECT,  this.getVo().getObid(),oVO.getObid(), null);
               }
           }
       }
   }


   public List<InvestProjectSubGroupVO> retrieveInvestSubProjectList ( String  projectGroupCode ){
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer fromPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();

//       fromPattern.append("<this>FromConnectedWithThis<[InvestProjectGroup]@PRO>+");
//       fromPattern.append("<[InvestProjectSubGroup]@SUB>ThisConnectedWithTo<this>+");
//       fromPattern.append("<[InvestProjectSubGroup]@SUB>ThisConnectedWithTo<[HasInvestProjectGroup]@REL2>+");
//       fromPattern.append("<[HasInvestProjectGroup]@REL2>FromConnectedWithThis<[ManagementGroup]@MGTGR>+");

       StringUtil.constructSelectPattern(selectPattern, "@this.[*]");
       fromPattern.append("<this>ThisConnectedWithTo<[HasInvestProjectGroup]@REL>+");
       fromPattern.append("<[HasInvestProjectGroup]@REL>FromConnectedWithThis<[ManagementGroup]@MGTGR>+");

       StringUtil.constructWherePattern(wherePattern, paramPattern, "@MGTGR.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getObid());
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@MGTGR.[planYear]", GlobalConstants.OQL_OPERATOR_EQUAL, "@this.[yyyy]");
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[upProjectGroupCode]", GlobalConstants.OQL_OPERATOR_EQUAL, projectGroupCode);

//       StringUtil.constructSelectPattern(selectPattern, "@REL.[obid] mgtRelObid");
//       StringUtil.constructSelectPattern(selectPattern, "@PRO.[projectGroupKorName] proName");
//       StringUtil.constructSelectPattern(selectPattern, "@PRO.[projectGroupEngName] proEngName");
//       StringUtil.constructSelectPattern(selectPattern, "@PRO.[projectGroupCode] proCode");
//       StringUtil.constructSelectPattern(selectPattern, "@SUB.[projectGroupKorName] subProName");

       List<InvestProjectSubGroupVO> result = ObjectRoot.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_INVESTPROJECTSUBGROUP,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               selectPattern.toString(),
               fromPattern.toString(),
               wherePattern.toString(),
               paramPattern.toString(),
               true,
               0);

       return result;
   }

   public List<AssetMajorVO> retrieveAssetMajorList(){
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       StringUtil.addSortByPattern(selectPattern, "@this.[assetMajorCode]");
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[corporationCode]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getCorporationCode());
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
       List<AssetMajorVO> majorList = BusinessObject.findObjects(
               ApplicationSchemaConstants.BIZCLASS_ASSETMAJOR,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               selectPattern.toString(),
               wherePattern.toString(),
               paramPattern.toString(),
               false,
               0);
       return majorList;
   }

   public List<AssetMinorVO> retrieveAssetMinorList( String assetItFlag, String assetMajorCode ){
       StringBuffer selectPattern = new StringBuffer();
       StringBuffer fromPattern = new StringBuffer();
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();

       fromPattern.append("<this>ThisConnectedWithFrom<[HasAssetType]@REL>+");
       fromPattern.append("<[HasAssetType]@REL>ToConnectedWithThis<[AssetType]@TYPE>+");

       StringUtil.constructSelectPattern(selectPattern, "@TYPE.[assetItFlag] assetItFlag");
//       StringUtil.constructSelectPattern(selectPattern, "case when @TYPE.[assetItFlag] ='IT' then 'IT' else 'General' end assetItFlag");
       StringUtil.addSortByPattern(selectPattern, "@this.[assetMajorCode], @this.[assetMinorCode]");

       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[corporationCode]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getCorporationCode());
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");

       if(!NullUtil.isNone(assetItFlag)){
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@TYPE.[assetItFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, assetItFlag);
       }

       if(!NullUtil.isNone(assetMajorCode)){
           StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[assetMajorCode]", GlobalConstants.OQL_OPERATOR_EQUAL, assetMajorCode);
       }

       List<AssetMinorVO> minorList = BusinessObject.searchObjects(
               ApplicationSchemaConstants.BIZCLASS_ASSETMINOR,
               GlobalConstants.FLAG_TYPE_ALL,
               GlobalConstants.FLAG_TYPE_ALL,
               selectPattern.toString(),
               fromPattern.toString(),
               wherePattern.toString(),
               paramPattern.toString(),
               false,
               0);



       return minorList;
   }

   /**
   *
   * @param userLocale
   * @return
   */
  public HashMap<String, List<BudgetActivityVO>> retrieveAtivityMap(String userLocale){
      StringBuffer selectPatternBuf = new StringBuffer();
      StringBuffer fromPatternBuf = new StringBuffer();
      StringBuffer wherePatternBuf = new StringBuffer();
      StringBuffer paramPatternBuf = new StringBuffer();
      HashMap<String, List<BudgetActivityVO>> accountMap = new HashMap<String, List<BudgetActivityVO>>();

      fromPatternBuf.append("<this>ThisConnectedWithTo<[HasAccountActivity]@HAA>+");
      fromPatternBuf.append("<[HasAccountActivity]@HAA>FromConnectedWithThis<[Account]@ACT>+");
      fromPatternBuf.append("<[Account]@ACT>ThisConnectedWithTo<[HasManagementGroupAccount]@HMA>+");
      fromPatternBuf.append("<[HasManagementGroupAccount]@HMA>FromConnectedWithThis<[ManagementGroup]@MGG>+");

      if( !NullUtil.isNone(userLocale) && !"".equals(userLocale) ){
          if("ko".equals(userLocale)){
              StringUtil.addSortByPattern(selectPatternBuf, "@this.[activityLocName]");
          }else{
              StringUtil.addSortByPattern(selectPatternBuf, "@this.[activityEngName]");
          }
      }else{
          StringUtil.addSortByPattern(selectPatternBuf, "@this.[activityLocName]");
      }

      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@MGG.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getObid());
      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@HMA.[useFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ACT.[useFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@HMA.[budgetInputType]", GlobalConstants.OQL_OPERATOR_EQUAL, "A");
      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "IFNULL(@HMA.[budgetInputActivityType],'null')", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "null");

      List<BudgetActivityVO> resultList = ObjectRoot.searchObjects(
              ApplicationSchemaConstants.BIZCLASS_BUDGETACTIVITY,
              GlobalConstants.FLAG_TYPE_ALL,
              GlobalConstants.FLAG_TYPE_ALL,
              selectPatternBuf.toString(),
              fromPatternBuf.toString(),
              wherePatternBuf.toString(),
              paramPatternBuf.toString(),
              false,
              0);

      for( int inx = 0; inx < resultList.size(); inx++ ){
          if( !accountMap.containsKey( resultList.get(inx).getBmsIfAccountCode() ) ){
              accountMap.put( resultList.get(inx).getBmsIfAccountCode() , new ArrayList<BudgetActivityVO>() );
          }
          accountMap.get(resultList.get(inx).getBmsIfAccountCode()).add(resultList.get(inx));
      }

      return accountMap;
  }

  /**
   * �����׷� Activity List ��ȸ
   * @param userLocale
   * @return
   */
  public List<BudgetActivityVO> retrieveAtivityList(String userLocale){
      StringBuffer selectPatternBuf = new StringBuffer();
      StringBuffer fromPatternBuf = new StringBuffer();
      StringBuffer wherePatternBuf = new StringBuffer();
      StringBuffer paramPatternBuf = new StringBuffer();

      if( GlobalConstants.LANG_KO.equals(userLocale) ){
          StringUtil.constructSelectPattern(selectPatternBuf, "@this.[activityLocName] activityName");
      }else{
          StringUtil.constructSelectPattern(selectPatternBuf, "@this.[activityEngName] activityName");
      }

      StringUtil.addSortByPattern(selectPatternBuf, "@this.[bmsIfAccountCode], @this.[activityId]");

      fromPatternBuf.append("<this>ThisConnectedWithTo<[HasAccountActivity]@HAA>+");
      fromPatternBuf.append("<[HasAccountActivity]@HAA>FromConnectedWithThis<[Account]@ACT>+");
      fromPatternBuf.append("<[Account]@ACT>ThisConnectedWithTo<[HasManagementGroupAccount]@HMA>+");
      fromPatternBuf.append("<[HasManagementGroupAccount]@HMA>FromConnectedWithThis<[ManagementGroup]@MGG>+");


      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@MGG.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getObid());
      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@HMA.[useFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ACT.[useFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@HMA.[budgetInputType]", GlobalConstants.OQL_OPERATOR_EQUAL, "A");
      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "IFNULL(@HMA.[budgetInputActivityType],'null')", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "null");

      List<BudgetActivityVO> resultList = ObjectRoot.searchObjects(
              ApplicationSchemaConstants.BIZCLASS_BUDGETACTIVITY,
              GlobalConstants.FLAG_TYPE_ALL,
              GlobalConstants.FLAG_TYPE_ALL,
              selectPatternBuf.toString(),
              fromPatternBuf.toString(),
              wherePatternBuf.toString(),
              paramPatternBuf.toString(),
              false,
              0);

      return resultList;
  }

  /**
   * �����⵵ ��ȸ
   *
   * @return �����⵵ ���
   */
  public List<ManagementGroupVO> selectManagementYearList( String userObid ){

      //�����׷� ��ü ��ȸ
      List<ManagementGroupVO> managementGroupList = this.selectManagementGroupList( "", userObid );

      List<ManagementGroupVO> yearList = new ArrayList<ManagementGroupVO> ();
      if(managementGroupList != null){

          for( int i=0; i<managementGroupList.size(); i++ ){
              ManagementGroupVO managementGroupVO = managementGroupList.get( i );

              String planYear = managementGroupVO.getPlanYear();

              boolean bExist = false;
              for( ManagementGroupVO vo : yearList ){
                  String year = vo.getPlanYear();

                  if ( planYear.equals( year ) ){
                      bExist = true;
                      break;
                  }
              }

              if( (i == 0) || (!bExist) ){
                  ManagementGroupVO yearVO = new ManagementGroupVO();

                  yearVO.setPlanYear(planYear);

                  yearList.add(yearVO);
              }
          }
      }

      return yearList;
  }

  /**
   * �����׷� ��� ��ȸ
   *
   * @param planYear
   *        �����⵵
   * @param userObid
   *        ������ obid
   * @return
   */
  public List<ManagementGroupVO> selectManagementGroupList(String planYear, String userObid){

      StringBuffer selectPatternBuf = new StringBuffer();
      StringBuffer fromPatternBuf = new StringBuffer();
      StringBuffer wherePatternBuf = new StringBuffer();
      StringBuffer paramPatternBuf = new StringBuffer();

      if( !StrUtil.isEmpty(planYear) ){
          StringUtil.constructSelectPattern(selectPatternBuf, "SortBy@this.[titles] ASC");
      }else{
          StringUtil.constructSelectPattern(selectPatternBuf, "SortBy@this.[planYear] DESC");
      }

      fromPatternBuf.append("<this>FromConnectedWithThis<[ManagementGroupList]@mgtGrpList>+");
      fromPatternBuf.append("<[ManagementGroupList]@mgtGrpList>ThisConnectedWithFrom<[ManagementGroup2Users]@REL>+");
      fromPatternBuf.append("<[ManagementGroup2Users]@REL>ToConnectedWithThis<[Users]@user>+");

      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
      StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@user.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, userObid);

      if( !StrUtil.isEmpty(planYear) ){
          StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[planYear]", GlobalConstants.OQL_OPERATOR_EQUAL, planYear);
      }

      List<ManagementGroupVO> groupList = ObjectRoot.searchObjects(
              ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP,
              GlobalConstants.FLAG_TYPE_ALL,
              GlobalConstants.FLAG_TYPE_ALL,
              selectPatternBuf.toString(),
              fromPatternBuf.toString(),
              wherePatternBuf.toString(),
              paramPatternBuf.toString(),
              false,
              0 );

      return groupList;
  }

  /**
   * �����׷캰 ��������Ʈ ��ȸ
   *
   * @return
   */
  public List<BizPlanVersionVO> retrieveVersionList() {
      StringBuffer selectPattern = new StringBuffer();
      StringBuffer fromPattern = new StringBuffer();
      StringBuffer wherePattern = new StringBuffer();
      StringBuffer paramPattern = new StringBuffer();

      fromPattern.append("<this>ThisConnectedWithTo<[HasManagementGroupVersion]@REL>+");
      fromPattern.append("<[HasManagementGroupVersion]@REL>FromConnectedWithThis<[ManagementGroup]@MGTGR>+");

      if ( this.getVo().getObid() != null && !StrUtil.isEmpty(this.getVo().getObid()) ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@MGTGR.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getObid());
      }

      if ( this.getVo().getPlanYear() != null && !StrUtil.isEmpty(this.getVo().getPlanYear()) ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@MGTGR.[planYear]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getPlanYear());
      }
      StringUtil.constructSelectPattern(selectPattern, "@MGTGR.[planYear] year");
      StringUtil.constructSelectPattern(selectPattern, "@MGTGR.[descriptions] managementGroupDesc");
      StringUtil.constructSelectPattern(selectPattern, "@MGTGR.[obid] managementGroupObid");
      StringUtil.constructSelectPattern(selectPattern, "@MGTGR.[corporationCode] corporationCode");
      //StringUtil.constructSelectPattern(selectPattern, "case when @this.[endDate] >= date_add(now(), interval -9 hour) then 1 else 0 end isEditableFlag");
      //StringUtil.constructSelectPattern(selectPattern, "case when DATE_FORMAT(omcConvertUtcToLocal(@this.[endDate]), '%Y-%m-%d') >= DATE_FORMAT(DATE_ADD(omcConvertUtcToLocal(NOW()), INTERVAL -9 hour), '%Y-%m-%d') then 1 else 0 end isEditableFlag");

      //StringUtil.addSortByPattern(selectPattern, "@MGTGR.[obid]");
      StringUtil.addSortByPattern(selectPattern, "@this.[versionNo] desc, @this.[created] desc");
      List<BizPlanVersionVO> result = ObjectRoot.searchObjects(
              ApplicationSchemaConstants.BIZCLASS_BIZPLANVERSION,
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
              fromPattern.toString(),
              wherePattern.toString(),
              paramPattern.toString(),
              true,
              0);

      HashMap<String, Object> outDataMap = new HashMap<String, Object>();
      //Date today = TimeServiceUtil.getDBLocalTime();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

      String todayStr = TimeServiceUtil.getDBLocalTimeStr().substring(0,10).replaceAll("-","");//simpleDateFormat.format(today);
      String endDate = "";
      for ( BizPlanVersionVO vo : result ) {
          outDataMap = vo.getOutData();
          endDate = simpleDateFormat.format(vo.getEndDate());
          if ( endDate.compareTo(todayStr) >= 0 ) {
              outDataMap.put("isEditableFlag", 1);
          } else {
              outDataMap.put("isEditableFlag", 0);
          }
          vo.setOutData(outDataMap);
      }
      return result;
  }
  
  public BizPlanVersionVO retrieveLastVersion() {
      StringBuffer selectPattern = new StringBuffer();
      StringBuffer fromPattern = new StringBuffer();
      StringBuffer wherePattern = new StringBuffer();
      StringBuffer paramPattern = new StringBuffer();

      fromPattern.append("<this>ThisConnectedWithTo<[HasManagementGroupVersion]@REL>+");
      fromPattern.append("<[HasManagementGroupVersion]@REL>FromConnectedWithThis<[ManagementGroup]@MGTGR>+");

      if ( this.getVo().getObid() != null && !StrUtil.isEmpty(this.getVo().getObid()) ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@MGTGR.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getObid());
      }

      if ( this.getVo().getPlanYear() != null && !StrUtil.isEmpty(this.getVo().getPlanYear()) ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@MGTGR.[planYear]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getPlanYear());
      }
      StringUtil.constructSelectPattern(selectPattern, "@MGTGR.[planYear] year");
      StringUtil.constructSelectPattern(selectPattern, "@MGTGR.[descriptions] managementGroupDesc");
      StringUtil.constructSelectPattern(selectPattern, "@MGTGR.[obid] managementGroupObid");
      StringUtil.constructSelectPattern(selectPattern, "@MGTGR.[corporationCode] corporationCode");
      //StringUtil.constructSelectPattern(selectPattern, "case when @this.[endDate] >= date_add(now(), interval -9 hour) then 1 else 0 end isEditableFlag");
      //StringUtil.constructSelectPattern(selectPattern, "case when DATE_FORMAT(omcConvertUtcToLocal(@this.[endDate]), '%Y-%m-%d') >= DATE_FORMAT(DATE_ADD(omcConvertUtcToLocal(NOW()), INTERVAL -9 hour), '%Y-%m-%d') then 1 else 0 end isEditableFlag");

      //StringUtil.addSortByPattern(selectPattern, "@MGTGR.[obid]");
      StringUtil.addSortByPattern(selectPattern, "@this.[versionNo] desc, @this.[created] desc");
      List<BizPlanVersionVO> list = ObjectRoot.searchObjects(
              ApplicationSchemaConstants.BIZCLASS_BIZPLANVERSION,
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
              fromPattern.toString(),
              wherePattern.toString(),
              paramPattern.toString(),
              true,
              0);
      
      BizPlanVersionVO result = null;
      if(!NullUtil.isNone(list)){
          result = list.get(0);
      }
      return result;
  }

  /**
   * ������ �����׷� ����Ʈ ��ȸ
   * @param planYear
   * @param mnagementGroupVO
   * @param isSearch : Combo �� ��� false. �����׷� ���ȭ�� ��ȸ �� �����ϰ�� True ��� ����
   * @param userSession
   * @param donotHaveVersion : Version ������ �����׷� ��ȸ �� false. Version ���� ������� ��ȸ �� true
   * @return
   */
  public List<ManagementGroupVO> retrieveManagementGroupList(String planYear, ManagementGroupVO mnagementGroupVO, boolean isSearch, UserSession userSession, boolean donotHaveVersion){
      StringBuffer selectPattern = new StringBuffer();
      StringBuffer fromPattern = new StringBuffer();
      StringBuffer wherePattern = new StringBuffer();
      StringBuffer paramPattern = new StringBuffer();

      if ( (userSession.getUserLocale()).equals(GlobalConstants.LANG_KO) ) {
          StringUtil.constructSelectPattern(selectPattern, "To[ManagedByDepartment].From.titles deptName");
      } else {
          StringUtil.constructSelectPattern(selectPattern, "To[ManagedByDepartment].From.descriptions deptName");
      }
      StringUtil.constructSelectPattern(selectPattern, "To[ManagedByDepartment].From.obid deptObid_old");
      StringUtil.constructSelectPattern(selectPattern, "To[ManagedByDepartment].From.obid deptObid");

      if ( !isSearch ) {
          fromPattern.append("<this>FromConnectedWithThis<[ManagementGroupList]@MGTMST>+");
          fromPattern.append("<[ManagementGroupList]@MGTMST>ThisConnectedWithFrom<[ManagementGroup2Users]@REL>+");
          fromPattern.append("<[ManagementGroup2Users]@REL>ToConnectedWithThis<[Users]@USR>+");

          StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@MGTMST.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
          // �����׷� ���� üũ
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@USR.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, userSession.getUserBizObid());
          if ( !donotHaveVersion ) {
              // Version �̻��� �����׷� ����
              StringUtil.constructWherePattern(wherePattern, paramPattern, "From[HasManagementGroupVersion].To.previousObid", GlobalConstants.OQL_OPERATOR_EQUAL, "1");
          }
      }

      if ( planYear != null && !"".equals(planYear) ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[planYear]", GlobalConstants.OQL_OPERATOR_EQUAL, planYear);
      }

      StringUtil.addSortByPattern(selectPattern, "@this.[planYear] desc, @this.[titles] asc");
      List<ManagementGroupVO> result = ObjectRoot.searchObjects(
              ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP,
              GlobalConstants.FLAG_TYPE_ALL,
              GlobalConstants.FLAG_TYPE_ALL,
              selectPattern.toString(),
              fromPattern.toString(),
              wherePattern.toString(),
              paramPattern.toString(),
              true,
              0);

      return result;
  }
  /**
   * �����׷� �ߺ�üũ & name �� �ٴ� year suffix ����
   *
   * @param managementGroupObid
   * @param managementVO
   * @return
   */
  public List<ManagementGroupVO> retrieveManagementGroupListWithoutSuffix( String businessUnitCode, String planYear, UserSession userSession, String dataRange ){

      String tmpCode;
      List<ManagementGroupVO> mgtGroupVOList = new ArrayList<ManagementGroupVO>();
//      List<ManagementGroupVO> tmpList = this.retrieveManagementGroupList( planYear, null, false, userSession, true );
      List<ManagementGroupVO> tmpList = new ArrayList<ManagementGroupVO>();
      Users user = new Users(userSession.getUserBizObid());
      List<DivisionUnitVO> divisionUnitList = user.retrieveDivisionUnitListDataRange(planYear, dataRange, "", userSession);
      HashSet<String> divisionCodeSet = new HashSet<String>();
      HashSet<String> mgtCodeSet = new HashSet<String>();
      HashSet<String> mgtMasterObidSet = new HashSet<String>();
      for( DivisionUnitVO divisionVO : divisionUnitList ){
          divisionCodeSet.add( divisionVO.getNames() );   
      }
      List<CodeDetailVO> codeList = CodeMaster.getCodeList("DIVISION_MGTGROUP");
      for( CodeDetailVO mgtGroupCodeVO : codeList ){
          if( divisionCodeSet.contains(mgtGroupCodeVO.getAttribute01()) || divisionCodeSet.contains(mgtGroupCodeVO.getAttribute01()) ){
              mgtCodeSet.add(mgtGroupCodeVO.getNames());
          }
      }
      String aa = StrUtil.convertSet2Str(mgtCodeSet);
      
      List<ManagementGroupListVO> masterList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUPLIST, aa);
      for( ManagementGroupListVO mstVO : masterList ){
          if( mgtCodeSet.contains(mstVO.getNames()) ){
              mgtMasterObidSet.add( mstVO.getObid() );
          }
      }
      List<ManagementGroupVO> allMgtList = this.retrieveManagementGroupList(planYear, null, true, userSession, true);
      for( ManagementGroupVO mgtVO : allMgtList ){
          if( mgtMasterObidSet.contains(mgtVO.getFromObid()) ){
              tmpList.add(mgtVO);
          }
      }
      
      int size = tmpList.size();
      if( size > 0 ){
          
          if( NullUtil.isNone( businessUnitCode ) ){
              mgtGroupVOList = tmpList;
          }
          else{
              for( int index = 0; index < tmpList.size(); index ++ ){
                   tmpCode = tmpList.get( index ).getBusinessUnitCode();
                   if( !NullUtil.isNone( tmpCode ) && tmpCode.equals( businessUnitCode ) ){
                       mgtGroupVOList.add( tmpList.get( index ) );
                   }
              }
          }
          
          StringBuffer sbObidList = new StringBuffer();
          ManagementGroupVO voMgtGroup;
          for( int index = 0; index < mgtGroupVOList.size(); index ++ ){
               voMgtGroup = mgtGroupVOList.get( index );               
               if( index > 0 ){
                   sbObidList.append( "," );
               }
               sbObidList.append( voMgtGroup.getFromObid() );
          }
    
          StringBuffer sbSelect = new StringBuffer();
          if( userSession.getUserLocale().equals( GlobalConstants.LANG_KO ) ){
              StringUtil.constructSelectPattern( sbSelect, "To[ManagedByDepartment].From.titles deptName" );
          } else {
              StringUtil.constructSelectPattern( sbSelect, "To[ManagedByDepartment].From.descriptions deptName" );
          }
          StringUtil.constructSelectPattern( sbSelect, "To[ManagedByDepartment].From.obid deptObid_old" );
          StringUtil.constructSelectPattern( sbSelect, "To[ManagedByDepartment].From.obid deptObid" );          
    
          StringBuffer sbWhere = new StringBuffer();
          StringBuffer sbParam = new StringBuffer();
          
          StringUtil.constructWherePattern( sbWhere, sbParam, "@this.[obid]", GlobalConstants.OQL_OPERATOR_IN, sbObidList.toString() );
    
          List<ManagementGroupListVO> prototypeList = ObjectRoot.searchObjects(
                  ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUPLIST,
                  GlobalConstants.FLAG_TYPE_ALL,
                  GlobalConstants.FLAG_TYPE_ALL,
                  sbSelect.toString(),
                  "", // fromPattern
                  sbWhere.toString(),
                  sbParam.toString(),
                  true,
                  0 );

          ManagementGroupListVO voManagementGroupList;
          ManagementGroupVO     voManagementGroup;
          for( int index = 0; index < prototypeList.size(); index ++ ){
               voManagementGroupList = prototypeList.get( index );
               for( int tmpIndex = 0; tmpIndex < mgtGroupVOList.size(); tmpIndex ++ ){
                    voManagementGroup = mgtGroupVOList.get( tmpIndex );
                    
                    if( voManagementGroup.getFromObid().equals( voManagementGroupList.getObid() ) ){
                        voManagementGroup.getOutData().put( "baseNames", voManagementGroupList.getNames() );
                    }
               }
          }
      }
      return mgtGroupVOList;
  }

  /**
   * �����׷� �ߺ�üũ
   *
   * @param managementGroupObid
   * @param managementVO
   * @return
   */
  public ManagementGroupVO retrieveManagementGroup(String managementGroupObid, ManagementGroupVO managementVO) {
      ManagementGroupVO existVO = new ManagementGroupVO();
      StringBuffer selectPattern = new StringBuffer();
      StringBuffer wherePattern = new StringBuffer();
      StringBuffer paramPattern = new StringBuffer();

      if ( managementGroupObid != null && !"".equals(managementGroupObid) ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]",
                  GlobalConstants.OQL_OPERATOR_EQUAL, managementGroupObid);
      }

      if ( !StrUtil.isEmpty(managementVO.getPlanYear()) ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[planYear]", GlobalConstants.OQL_OPERATOR_EQUAL, managementVO.getPlanYear());
      }

      if ( !StrUtil.isEmpty(managementVO.getTitles()) ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[titles]", GlobalConstants.OQL_OPERATOR_EQUAL, managementVO.getTitles());
      }

      if ( !StrUtil.isEmpty(managementVO.getFromObid())) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, managementVO.getFromObid());
      }

      existVO = ObjectRoot.findObject(
                  ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP,
                  GlobalConstants.FLAG_TYPE_ALL,
                  GlobalConstants.FLAG_TYPE_ALL,
                  selectPattern.toString(),
                  wherePattern.toString(),
                  paramPattern.toString(),
                  false
                  );

      return existVO;
  }

  /**
   * �����׷� ��� HR�μ�
   *
   * @param planYear
   * @param userLocale
   * @return
   */
  public List<DepartmentVO> retrieveManagementGroupDeptList(String planYear, String departmentObid, String userLocale) {
      StringBuffer selectPattern = new StringBuffer();
      StringBuffer fromPattern = new StringBuffer();
      StringBuffer wherePattern = new StringBuffer();
      StringBuffer paramPattern = new StringBuffer();

      fromPattern.append("<this>ThisConnectedWithTo<[HasManagementGroupDept]@REL>+");
      fromPattern.append("<[HasManagementGroupDept]@REL>FromConnectedWithThis<[ManagementGroup]@MGTGR>+");

      /*if ( this.getVo().getObid() != null && !"".equals(this.getVo().getObid()) ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@MGTGR.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getObid());
      }*/
      StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
      if ( !"".equals(departmentObid) && departmentObid != null ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, departmentObid);
      }

      if ( planYear != null && !"".equals(planYear) ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@MGTGR.[planYear]", GlobalConstants.OQL_OPERATOR_EQUAL, planYear);
      }

      StringUtil.constructSelectPattern(selectPattern, "@REL.[obid] relationObid");
      StringUtil.constructSelectPattern(selectPattern, "@MGTGR.[obid] mgmtGroupObid");
      StringUtil.constructSelectPattern(selectPattern, "@MGTGR.[titles] managementGroupDesc");

      if ( userLocale.equals(GlobalConstants.LANG_KO) ) {
          StringUtil.addSortByPattern(selectPattern, "@this.[titles] asc");
      } else if ( userLocale.equals(GlobalConstants.LANG_EN))  {
          StringUtil.addSortByPattern(selectPattern, "@this.[descriptions] asc");
      }

      List<DepartmentVO> result = ObjectRoot.searchObjects(
              ApplicationSchemaConstants.BIZCLASS_DEPARTMENT,
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
              fromPattern.toString(),
              wherePattern.toString(),
              paramPattern.toString(),
              true,
              0);

      return result;
  }

  /**
   * �����׷����� ��ȸ
   *
   * @return
   */
  public static ManagementGroupVO retrieveManagementGroupInfo(String mgtGroupObid){

      StringBuffer selectPattern = new StringBuffer();
      StringBuffer wherePattern = new StringBuffer();
      StringBuffer paramPattern = new StringBuffer();

      StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, mgtGroupObid);

      ManagementGroupVO result = ObjectRoot.findObject(
                  ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP,
                  GlobalConstants.FLAG_TYPE_ALL,
                  GlobalConstants.FLAG_TYPE_ALL,
                  selectPattern.toString(),
                  wherePattern.toString(),
                  paramPattern.toString(),
                  false
                  );

      return result;
  }

  /**
   * �ش� �����׷� HR�μ�
   *
   * @return
   */
  public List<DepartmentVO> retrieveManagementGroupDeptList(String departmentObid) {
      StringBuffer selectPattern = new StringBuffer();
      StringBuffer wherePattern = new StringBuffer();
      StringBuffer paramPattern = new StringBuffer();

      StringUtil.constructSelectPattern(selectPattern, "To[HasAccountingDepartment{[useYn]=='Y'}].From{[states]=='Active'}.obid accountingDeptObid");
      StringUtil.constructSelectPattern(selectPattern, "To[HasAccountingDepartment{[useYn]=='Y'}].From{[states]=='Active'}.accountingUnitCode accountingUnitCode");
      StringUtil.constructSelectPattern(selectPattern, "To[HasAccountingDepartment{[useYn]=='Y'}].From{[states]=='Active'}.departmentCode accountingDeptCode");
      StringUtil.constructSelectPattern(selectPattern, "To[SubDepartment{[useYn]=='Y'}].From.titles hrUpperDeptKorName");
      StringUtil.constructSelectPattern(selectPattern, "To[SubDepartment{[useYn]=='Y'}].From.descriptions hrUpperDeptEngName");
      StringUtil.constructSelectPattern(selectPattern, "To[SubDepartment{[useYn]=='Y'}].From.names hrUpperDeptCode");

      if ( !"".equals(departmentObid) && departmentObid != null ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, departmentObid);
      }
      StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
      StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);
      List<DepartmentVO> mgtGroupDeptList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPDEPT,
              ApplicationSchemaConstants.BIZCLASS_DEPARTMENT,
              GlobalConstants.FLAG_TYPE_TO,
              selectPattern.toString(),
              wherePattern.toString(),
              paramPattern.toString(),
              false,
              false,
              0,
              0);
      return mgtGroupDeptList;
  }

  public void deleteManagementGroupHrDeptList(String planYear, String departmentObid) {
      List<DepartmentVO> hrDeptList = this.retrieveManagementGroupDeptList(planYear, departmentObid, "");
      if ( hrDeptList.size() > 0 ) {
          HasManagementGroupDept deleteDom = null;
          for ( int idx = 0 ; idx < hrDeptList.size() ; idx++ ) {
              deleteDom = new HasManagementGroupDept(hrDeptList.get(idx).getOutData().get("relationObid").toString());
              RelationShip.disconnect(deleteDom.getVo());
          }
      }
  }

  /**
   * �����׷� ��� ȸ��μ�
   *
   * @param planYear
   * @param userLocale
   * @return
   */
  public List<AccountingDepartmentVO> retrieveManagementGroupAccountDeptList(String planYear, String departmentObid, String managementGroupObid) {
      StringBuffer selectPattern = new StringBuffer();
      StringBuffer fromPattern = new StringBuffer();
      StringBuffer wherePattern = new StringBuffer();
      StringBuffer paramPattern = new StringBuffer();

      fromPattern.append("<this>ThisConnectedWithTo<[HasManagementGroupAccountDept]@REL>+");
      fromPattern.append("<[HasManagementGroupAccountDept]@REL>FromConnectedWithThis<[ManagementGroup]@MGTGR>+");

      StringUtil.constructSelectPattern(selectPattern, "@REL.[obid] relationObid");
      StringUtil.constructSelectPattern(selectPattern, "From[HasAccountingDepartment{[useYn]=='Y'}].To{[states]=='Active'}.obid hrDeptObid");
      StringUtil.constructSelectPattern(selectPattern, "From[HasAccountingDepartment{[useYn]=='Y'}].To{[states]=='Active'}.titles hrDeptName");
      StringUtil.constructSelectPattern(selectPattern, "From[HasAccountingDepartment{[useYn]=='Y'}].To{[states]=='Active'}.names hrDeptCode");
      StringUtil.constructSelectPattern(selectPattern, "getUserInfo(@this.[unlockUser],'T') unlockUserName");

      if ( !"".equals(departmentObid) && departmentObid != null ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, departmentObid);
      }

      if ( planYear != null && !"".equals(planYear) ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@MGTGR.[planYear]", GlobalConstants.OQL_OPERATOR_EQUAL, planYear);
      }

      if ( managementGroupObid != null && !"".equals(managementGroupObid) ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@MGTGR.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, managementGroupObid);

      }

      StringUtil.addSortByPattern(selectPattern, "@this.[titles]");
      List<AccountingDepartmentVO> result = ObjectRoot.searchObjects(
              ApplicationSchemaConstants.BIZCLASS_ACCOUNTINGDEPARTMENT,
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
              fromPattern.toString(),
              wherePattern.toString(),
              paramPattern.toString(),
              true,
              0);

      return result;
  }

  public void deleteManagementGroupAccountDeptList(String planYear, String departmentObid) {
      List<AccountingDepartmentVO> accountDeptList = this.retrieveManagementGroupAccountDeptList(planYear, departmentObid, "");
      if ( accountDeptList.size() > 0 ) {
          HasManagementGroupAccountDept deleteDom = null;
          for ( int idx = 0 ; idx < accountDeptList.size() ; idx++ ) {
              deleteDom = new HasManagementGroupAccountDept((String)accountDeptList.get(idx).getOutData().get("relationObid"));
              RelationShip.disconnect(deleteDom.getVo());
          }
      }
  }

  /**
   * �ش� �����׷��� ȸ��μ� ����Ʈ
   * @param departmentObid
   * @return
   */
  public List<AccountingDepartmentVO> retrieveManagementGroupAccountDeptList(String departmentObid) {
      StringBuffer selectPattern = new StringBuffer();
      StringBuffer wherePattern = new StringBuffer();
      StringBuffer paramPattern = new StringBuffer();

      if ( !"".equals(departmentObid) && departmentObid != null ) {
          StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, departmentObid);
      }
      StringUtil.constructSelectPattern(selectPattern, "@this.[*]");
      List<AccountingDepartmentVO> mgtGroupAcctDeptList = this.getRelatedObjects(
              ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPACCOUNTDEPT,
              ApplicationSchemaConstants.BIZCLASS_ACCOUNTINGDEPARTMENT,
              GlobalConstants.FLAG_TYPE_TO,
              selectPattern.toString(),
              wherePattern.toString(),
              paramPattern.toString(),
              false, false,
              0,0
              );

      return mgtGroupAcctDeptList;
  }

  /**
   * ������ ��ȸ - �����׷� ���� ��ȸ
   * @param userSession
   * @return
   */
  public List<AccountVO> retrieveManageGroupAccountList(UserSession userSession) {
      StringBuffer selectPattern = new StringBuffer();
      StringBuffer wherePattern = new StringBuffer();
      StringBuffer paramPattern = new StringBuffer();

      if ( GlobalConstants.LANG_KO.equals(userSession.getUserLocale()) ) {
          StringUtil.constructSelectPattern(selectPattern, "@this.[accountKorName] accountDescriptions");
      } else {
          StringUtil.constructSelectPattern(selectPattern, "@this.[accountEngName] accountDescriptions");
      }

      StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[useFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
      StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[useFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
      StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[budgetInputType]", GlobalConstants.OQL_OPERATOR_EQUAL, "A");
      StringUtil.constructWherePattern(wherePattern, paramPattern, "IFNULL(@REL.[budgetInputActivityType],'null')", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "null");

      StringUtil.addSortByPattern(selectPattern, "accountDescriptions");
      List<AccountVO> result = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPACCOUNT,
              ApplicationSchemaConstants.BIZCLASS_ACCOUNT,
              GlobalConstants.FLAG_TYPE_TO,
              selectPattern.toString(),
              wherePattern.toString(),
              paramPattern.toString(),
              false,
              false,
              0,
              0);
      return result;
  }

  /**
   * �����׷� --> ǰ�Ǽ� Tool Tip ��� ��ȸ
   *
   * @return
   */
  public static List<ManagementGroupVO> selectConsultToolTipList( ManagementGroupVO managementGroupVO ){

      StringBuffer selectPatternBuf = new StringBuffer();
      StringBuffer fromPatternBuf = new StringBuffer();
      StringBuffer wherePatternBuf = new StringBuffer();
      StringBuffer paramPatternBuf = new StringBuffer();

      //ǰ�Ǽ� Tool Tip --> obid
      StringUtil.constructSelectPattern(selectPatternBuf, "@RelToolTip.[obid] toolTipObid");

      //ǰ�Ǽ� ���� obid
      StringUtil.constructSelectPattern(selectPatternBuf, "@Form.[obid] consultFormObid");

      //ǰ�Ǽ� ���� �ڵ�
      StringUtil.constructSelectPattern(selectPatternBuf, "@Form.[consAcctCd] consAcctCd");

      StringUtil.addSortByPattern(selectPatternBuf, "@this.[created]");

      fromPatternBuf.append("<this>ThisConnectedWithTo<[ConsultFormToolTip]@RelToolTip>+");
      fromPatternBuf.append("<[ConsultFormToolTip]@RelToolTip>FromConnectedWithThis<[ConsultForm]@Form>+");

      //�����׷� obid
      String groupObid = managementGroupVO.getObid();
      if( !StrUtil.isEmpty(groupObid) ){
          StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, groupObid);
      }

      List<ManagementGroupVO> consultToolTipList = ObjectRoot.searchObjects(
              ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP,
              GlobalConstants.FLAG_TYPE_ALL,
              GlobalConstants.FLAG_TYPE_ALL,
              selectPatternBuf.toString(),
              fromPatternBuf.toString(),
              wherePatternBuf.toString(),
              paramPatternBuf.toString(),
              true,
              0
      );

      return consultToolTipList;
  }
  public BizPlanVersionVO getBizPlanVersion(String version){
      StringBuffer wherePattern  = new StringBuffer (); 
      StringBuffer paramPattern  = new StringBuffer (); 
      StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[revision]", GlobalConstants.OQL_OPERATOR_EQUAL, version);
      return this.getRelatedObject(ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPVERSION, ApplicationSchemaConstants.BIZCLASS_BIZPLANVERSION,GlobalConstants.FLAG_TYPE_TO);
  }
  public BizPlanVersionVO getLasterBizPlanVersion(){
      StringBuffer wherePattern  = new StringBuffer (); 
      StringBuffer paramPattern  = new StringBuffer (); 
      StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[previousObid]", GlobalConstants.OQL_OPERATOR_EQUAL, "1");
      return this.getRelatedObject(ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPVERSION, ApplicationSchemaConstants.BIZCLASS_BIZPLANVERSION,GlobalConstants.FLAG_TYPE_TO);
  }
  
  /**
   * BU�� �⺻��ȹ���� ��ȸ
   * @return
   */
  public BizPlanVersionVO getBasicPlanVersion(){
      StringBuffer selectPattern = new StringBuffer();
      StringBuffer wherePattern = new StringBuffer();
      StringBuffer paramPattern = new StringBuffer();

      StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[basicPlanFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
      StringUtil.addSortByPattern(selectPattern, "@this.[versionNo] desc, @this.[created] desc");

      List<BizPlanVersionVO> result = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPVERSION,
              ApplicationSchemaConstants.BIZCLASS_BIZPLANVERSION,
              GlobalConstants.FLAG_TYPE_TO,
              selectPattern.toString(),
              wherePattern.toString(),
              paramPattern.toString(),
              false,
              false,
              0,
              0);
      if( NullUtil.isNone( result ) ){
          return null;
      }else{
          return result.get(0);
      }
  }
    /**
     * 
     *
     * @param acctiveAccountOnly
     * @return
     */
    public final List<AccountVO> getAccountList(boolean acctiveAccountOnly){
        return getAccountList(null,acctiveAccountOnly);
    }
    /**
     * 
     *
     * @param accountCodeSet
     * @param acctiveAccountOnly
     * @return
     */
    public final List<AccountVO> getAccountList(Set<String> accountCodeSet,boolean acctiveAccountOnly){
        Set<String> managementGoupObidSet = new HashSet<String>(); 
        managementGoupObidSet.add(this.getObid());
      return getAccountListSub(managementGoupObidSet ,accountCodeSet,false,acctiveAccountOnly);
    }
    /**
     * 
     *
     * @param managementGoupObidSet
     * @return
     */
    public static final List<AccountVO> getAccountListByManagement(Set<String> managementGoupObidSet){
        return getAccountListSub(managementGoupObidSet,null,false,false);
    }
    /**
     * 
     *
     * @param managementGoupObidSet
     * @param acctiveAccountOnly
     * @return
     */
    public static final List<AccountVO> getAccountListByManagement(Set<String> managementGoupObidSet,boolean acctiveAccountOnly){
        return getAccountListSub(managementGoupObidSet,null,false,acctiveAccountOnly);
    }
    /**
     * 
     *
     * @param managementGoupObidSet
     * @param accoundObidSet
     * @param acctiveAccountOnly
     * @return
     */
    public static final List<AccountVO> getAccountListByManagement(Set<String> managementGoupObidSet,Set<String> accoundObidSet, boolean acctiveAccountOnly){
        return getAccountListSub(managementGoupObidSet,accoundObidSet,true,acctiveAccountOnly);
    }
    /**
     * 
     *
     * @param managementGoupObidSet
     * @param accoundCodeSet
     * @param acctiveAccountOnly
     * @return
     */
    public static final List<AccountVO> getAccountListWithNameByManagement(Set<String> managementGoupObidSet,Set<String> accoundCodeSet, boolean acctiveAccountOnly){
        return getAccountListSub(managementGoupObidSet,accoundCodeSet,false,acctiveAccountOnly);
    }
    /**
     * 
     *
     * @param managementGoupObidSet
     * @param accountCodeSet
     * @param acctiveAccountOnly
     * @return
     */
    private static final List<AccountVO> getAccountListSub(Set<String> managementGoupObidSet, Set<String> accountSet, boolean isAccoundObid,  boolean acctiveAccountOnly){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer fromPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        if(ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, GlobalConstants.LANG_KO).equals( GlobalConstants.LANG_KO )){
            StringUtil.constructSelectPattern(selectPattern, "@this.[accountKorName] accountName");
        }else{
            StringUtil.constructSelectPattern(selectPattern, "@this.[accountEngName] accountName");
        }
        StringUtil.constructSelectPattern(selectPattern, "@rel.[fromObid] managementGroupObid");
        fromPattern.append("<this>ThisConnectedWithTo<[" + ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPACCOUNT + "]@rel>+");
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@rel.[fromClass]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@rel.[className]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPACCOUNT);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@rel.[bmsIfYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        if(acctiveAccountOnly) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[useFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        if(!NullUtil.isNone(managementGoupObidSet)){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@rel.[fromObid]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(managementGoupObidSet));
        }
        if(!NullUtil.isNone(accountSet)){
            if(isAccoundObid) {
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(accountSet));
            }else{
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(accountSet));
            }
        }
        return ObjectRoot.searchObjects(ApplicationSchemaConstants.BIZCLASS_ACCOUNT,
                GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,selectPattern.toString(),fromPattern.toString(),wherePattern.toString(),paramPattern.toString(),true,0);
    }
    public static final List<AccountVO> getDistinctAccountListByManagement(Set<String> managementGoupObidSet,Set<String> accoundObidSet, boolean acctiveAccountOnly){
        return getDistinctAccountListSub(managementGoupObidSet,accoundObidSet,true,acctiveAccountOnly);
    }
    private static final List<AccountVO> getDistinctAccountListSub(Set<String> managementGoupObidSet, Set<String> accountSet, boolean isAccoundObid,  boolean acctiveAccountOnly){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer fromPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        if(ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, GlobalConstants.LANG_KO).equals( GlobalConstants.LANG_KO )){
            StringUtil.constructSelectPattern(selectPattern, "@this.[accountKorName] accountName");
        }else{
            StringUtil.constructSelectPattern(selectPattern, "@this.[accountEngName] accountName");
        }
        fromPattern.append("<this>ThisConnectedWithTo<[" + ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPACCOUNT + "]@rel>+");
        //StringUtil.constructWherePattern(wherePattern, paramPattern, "@rel.[fromClass]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP);
        //StringUtil.constructWherePattern(wherePattern, paramPattern, "@rel.[className]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPACCOUNT);
        //StringUtil.constructWherePattern(wherePattern, paramPattern, "@rel.[bmsIfYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        if(acctiveAccountOnly) StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[useFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");
        StringUtil.constructWherePattern(wherePattern, paramPattern, "To[HasManagementGroupAccount{bmsIfYn=='Y'&&fromClass=='ManagementGroup'&&className=='HasManagementGroupAccount'}].fromObid", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(managementGoupObidSet));
        if(!NullUtil.isNone(accountSet)){
            if(isAccoundObid) {
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(accountSet));
            }else{
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(accountSet));
            }
        }
        return ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_ACCOUNT, wherePattern.toString(), paramPattern.toString());
    }
    public final List<AccountingDepartmentVO> getAccountingDepartmentListByManagement(Set<String> accountingDepartmentSet,boolean isDepartmentObid){
        Set<String> managementGoupObidSet = new HashSet<String>();
        managementGoupObidSet.add(this.getObid());
        return getAccountingDepartmentListSub(managementGoupObidSet,accountingDepartmentSet,isDepartmentObid);
    }
    public static final List<AccountingDepartmentVO> getAccountingDepartmentListByManagement(Set<String> managementGoupObidSet,Set<String> accountingDepartmentSet,boolean isDepartmentObid){
        return getAccountingDepartmentListSub(managementGoupObidSet,accountingDepartmentSet,true);
    }
    public static final List<AccountingDepartmentVO> getAccountingDepartmentListByManagement(Set<String> managementGoupObidSet){
        return getAccountingDepartmentListSub(managementGoupObidSet,null,false);
    }
    private static final List<AccountingDepartmentVO> getAccountingDepartmentListSub(Set<String> managementGoupObidSet, Set<String> accountingDepartmentSet, boolean isDepartmentObid){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer fromPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();

        StringUtil.constructSelectPattern(selectPattern, "@rel.[fromObid] managementGroupObid");
        fromPattern.append("<this>ThisConnectedWithTo<[" + ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPACCOUNTDEPT + "]@rel>+");
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@rel.[fromClass]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@rel.[className]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPACCOUNTDEPT);
        if(!NullUtil.isNone(accountingDepartmentSet)){
            if(isDepartmentObid) {
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(accountingDepartmentSet));
            }else{
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(accountingDepartmentSet));
            }
            if(!NullUtil.isNone(managementGoupObidSet)){
                StringUtil.constructWherePattern(wherePattern, paramPattern, "concat(@rel.[fromObid],'')", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(managementGoupObidSet));
            }
        }else{
            if(!NullUtil.isNone(managementGoupObidSet)){
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@rel.[fromObid]", GlobalConstants.OQL_OPERATOR_IN, StrUtil.convertSet2Str(managementGoupObidSet));
            }
        }
        return ObjectRoot.searchObjects(ApplicationSchemaConstants.BIZCLASS_ACCOUNTINGDEPARTMENT,
                GlobalConstants.FLAG_TYPE_ALL,GlobalConstants.FLAG_TYPE_ALL,selectPattern.toString(),fromPattern.toString(),wherePattern.toString(),paramPattern.toString(),true,0);
    }
    
    /**
     * CodeDetail : Division - BudgetUnit Mapping ����, BudgetUnit���� Division ���� ��ȸ 
     *
     * @param searchInfo
     * @return
     */
    public List<CodeDetailVO> getBudgetUnitDivision(CodeDetailVO codeDetailVO) {
        StringBuffer fromPattern = new StringBuffer();
        fromPattern.append("<this>ThisConnectedWithTo<[CodeMaster2Code]@REL>+");
        fromPattern.append("<[CodeMaster2Code]@REL>FromConnectedWithThis<[CodeMaster]@MASTER>+");

        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        if (StrUtil.isEmpty(codeDetailVO.getOutDataStringValue("codeMasterName"))) {
        	codeDetailVO.setOutDataAttributeValue("codeMasterName","DIVISION_MGTGROUP");
        }
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@MASTER.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, codeDetailVO.getOutDataStringValue("codeMasterName"));
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);

        if (!StrUtil.isEmpty(codeDetailVO.getOutDataStringValue("divisionCode"))) {
            fromPattern.append("<this>ThisConnectedWithFrom<[Code2Organization]@CODE2ORG>+");
            fromPattern.append("<[Code2Organization]@CODE2ORG>ToConnectedWithThis<[Organizations]@ORG>+");
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@ORG.[names]", GlobalConstants.OQL_OPERATOR_IN, codeDetailVO.getOutDataStringValue("divisionCode"));
        }

        if ( !StrUtil.isEmpty(codeDetailVO.getAttribute01()) ) {
            List<OmcOQLCondition> conditionList = new ArrayList<OmcOQLCondition>();
            conditionList.add(new OmcOQLCondition("@this.[attribute01]", codeDetailVO.getAttribute01(), GlobalConstants.OQL_OPERATOR_EQUAL));
            conditionList.add(new OmcOQLCondition("@this.[attribute02]", codeDetailVO.getAttribute01(), GlobalConstants.OQL_OPERATOR_EQUAL));
            StringUtil.constructOrWherePattern(wherePattern, paramPattern, conditionList);
        }
        
        List<CodeDetailVO> result = ObjectRoot.searchObjects(
                ApplicationSchemaConstants.BIZCLASS_CODEDETAIL, 
                GlobalConstants.FLAG_TYPE_ALL, 
                GlobalConstants.FLAG_TYPE_ALL, 
                "SortBy@this.[sequences]", 
                fromPattern.toString(), 
                wherePattern.toString(), 
                paramPattern.toString(), 
                true, 
                0
                );

        return result;
    }
    
    /**
     * ������ BudgetUnit ������ ManagementGroupList ���� ��ȸ
     *
     * @return
     */
    public List<ManagementGroupListVO> getBudgetUnitMasterInfo() {
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[obid]", GlobalConstants.OQL_OPERATOR_EQUAL, this.getVo().getFromObid());
        return ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUPLIST, 
                wherePattern.toString(), 
                paramPattern.toString()
                );
    }
    /**
     *
     * @param year
     * @param managementGroupMstObid
     * @return
     */
    public static List<ManagementGroupVO> getManagementGroupByMasterGroup(String year, String managementGroupMstObid){
        StringBuffer selectPattern = new StringBuffer();// TODO Auto-generated method stub
       
        StringUtil.addSortByPattern(selectPattern, "@this.[planYear] desc");
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        if( !NullUtil.isNone( year ) ){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[planYear]", GlobalConstants.OQL_OPERATOR_EQUAL, year);
        }
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_EQUAL, managementGroupMstObid);
        List<ManagementGroupVO> mgtgr = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_MANAGEMENTGROUP, 
                                                                                                                    GlobalConstants.FLAG_TYPE_ALL,
                                                                                                                    GlobalConstants.FLAG_TYPE_ALL,
                                                                                                                    selectPattern.toString(),wherePattern.toString(), paramPattern.toString(),
                                                                                                                    false,
                                                                                                                    0);
        return mgtgr;
    }
    
    public List<AccountVO> getFrozenAccountList(){
        
        List<AccountVO> frozenAccountList = null;
        
        StringBuffer sbSelect = new StringBuffer();
        StringBuffer sbWhere  = new StringBuffer();
        StringBuffer sbParam  = new StringBuffer();
        StringUtil.constructWherePattern( sbWhere, sbParam, "@this.[editableFlag]", GlobalConstants.OQL_OPERATOR_EQUAL, "N" );
        
        ArrayList<ManagementGroupVO> mgtGroupList = new ArrayList<ManagementGroupVO>();
        mgtGroupList.add( this.getVo() );

        List<HasManagementGroupAccountVO> relList = this.getRelationship(
            ApplicationSchemaConstants.RELCLASS_HASMANAGEMENTGROUPACCOUNT,  // relationPattern,
            ApplicationSchemaConstants.BIZCLASS_ACCOUNT,                    // filterPattern,
            GlobalConstants.FLAG_TYPE_TO,                             // fromToDirection,
            sbSelect.toString(),                                      // selectPattern,
            sbWhere.toString(),                                       // wherePattern,
            sbParam.toString()                                        // paramPattern
        );
        
        if( relList.size() > 0 ){
            List<String> accountObidList = new ArrayList<String>();
            for( int index = 0; index < relList.size(); index ++ ){
                 accountObidList.add( relList.get( index ).getToObid() );
            }
            frozenAccountList = ObjectRoot.findObjectsWithObidList( accountObidList, ApplicationSchemaConstants.BIZCLASS_ACCOUNT );            
        }
        else{
            frozenAccountList = new ArrayList<AccountVO>();
        }
        return frozenAccountList;
    }
}

