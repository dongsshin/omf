package com.rap.code.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;

import com.rap.code.model.CodeDetailSearchVO;
import com.rap.code.model.SearcherVO;
import com.rap.code.service.CodeService;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.util.omc.CacheUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.RelationShip;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.model.ClassNameForDisplayVO;
import com.rap.omc.foundation.lifecycle.model.StateInfo;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.foundation.LifeCycleUtil;
import com.rap.omc.util.StringUtil;
import com.rap.util.BusinessCommonConstants;
import com.rap.util.BusinessUnitUtil;

import rap.api.object.common.dom.CodeDetail;
import rap.api.object.common.dom.CodeMaster;
import rap.api.object.common.model.CodeDetailVO;
import rap.api.object.common.model.CodeMasterVO;
import rap.api.object.organization.dom.AffiliateUnit;
import rap.api.object.organization.dom.BusinessUnit;
import rap.api.object.organization.dom.ChangeAuth;
import rap.api.object.organization.dom.Company;
import rap.api.object.organization.dom.DivisionUnit;
import rap.api.object.organization.dom.PlantUnit;
import rap.api.object.organization.model.AffiliateUnitVO;
import rap.api.object.organization.model.BusinessUnitVO;
import rap.api.object.organization.model.ChangeAuthVO;
import rap.api.object.organization.model.CompanyVO;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.organization.model.OrganizationsVO;
import rap.api.object.organization.model.PlantUnitVO;
import rap.application.constants.ApplicationSchemaConstants;
@Service("codeService")
public class CodeServiceImpl implements CodeService {

    static final Logger LOGGER = LoggerFactory.getLogger(CodeServiceImpl.class);

    @Autowired
    private UserSession userSession;
    
    @Override
    public List<BusinessObjectRoot> findObjectWithSearcher(SearcherVO searcherVO) {

        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringBuffer selectPattern = new StringBuffer();
        StringUtil.constructSelectPattern(selectPattern, "@this.[*]");

        String searchCondition = searcherVO.getTitle() + GlobalConstants.FLAG_TYPE_ALL;
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "UPPER(@this.[titles])", GlobalConstants.OQL_OPERATOR_LIKE, searchCondition);
        String wherePattern = wherePatternBuf.toString();
        String paramPattern = paramPatternBuf.toString();

        List<BusinessObjectRoot> result = null;

        if(searcherVO.getClassName().equals(ApplicationSchemaConstants.BIZCLASS_USERS))
        {
            result = ObjectRoot.searchObjects(
                searcherVO.getClassName(),
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE,
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
                "",
                wherePattern,
                paramPattern,
                true,
                0
            );
        }
        else
        {
            result = ObjectRoot.searchObjects(
                searcherVO.getClassName(),
                searchCondition,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                false,
                true,
                false,
                false,
                null,
                null,
                null,
                null,
                true,
                0
            );
        }

        return result;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public List<CompanyVO> getCompanyList() {
        return this.getCompanyList("","");
    }
    @Override
    public List<CompanyVO> getCompanyList(String names, String descriptions) {
        return this.getCompanyList(names, descriptions, true);
    }
    @Override
    public List<CompanyVO> getCompanyList(String names, String descriptions, boolean isOrderByNames) {
    	return Company.getCompanyList(names, descriptions, isOrderByNames);
        
    }
    
    @Override
    public List<BusinessUnitVO> getBusinessList(String businessUnitCode, String names, String descriptions) {
        return this.getBusinessList(businessUnitCode,names,descriptions, true);
    }
    @Override
    public List<BusinessUnitVO> getBusinessList(String businessUnitCode, String names, String descriptions, boolean isOrderByNames) {
    	return BusinessUnit.getBusinessUnitList(businessUnitCode,names,descriptions,isOrderByNames);
    }
    
    
    @Override
    public List<DivisionUnitVO> getDivisionList() {
        return this.getDivisionList(null, null);
    }
    @Override
    public List<DivisionUnitVO> getDivisionList(String businessUnitCode) {
        return this.getDivisionList(businessUnitCode, null);
    }
    @Override
    public List<DivisionUnitVO> getDivisionList(String businessUnitCode, String names, String descriptions) {
        return this.getDivisionList(businessUnitCode, names, descriptions, false);
    }
    @Override
    public List<DivisionUnitVO> getDivisionList(String businessUnitCode, String names, String descriptions, boolean isOrderByNames) {
        return DivisionUnit.getDivisionList(businessUnitCode, names, descriptions,isOrderByNames);
    }
    @Override
    public List<DivisionUnitVO> getDivisionList(String names, String descriptions) {
        return this.getDivisionList("", names, descriptions,false);
    }

    @Override
    public List<DivisionUnitVO> getDivisionList(String names, String descriptions, boolean isOrderByNames) {
        return this.getDivisionList("", names, descriptions,isOrderByNames);
    }
    
    
    @Override
    public List<AffiliateUnitVO> getAffiliateList(String divisionCode, boolean isOrderByNames) {
    	return AffiliateUnit.getAffiliateList(divisionCode, isOrderByNames);
    }
    
    @Override
    public List<PlantUnitVO> getPlantList(String divisionCode) {
        return this.getPlantList(BusinessUnitUtil.getBusinessUnit(), divisionCode, "","");
    }
    @Override
    public List<PlantUnitVO> getPlantList(String businessUnitCode, String divisionCode, String names, String descriptions) {
        return this.getPlantList(businessUnitCode, divisionCode, names, descriptions, true);
    }
    @Override
    public List<PlantUnitVO> getPlantList(String businessUnitCode, String divisionCode, String names, String descriptions, boolean isOrderByNames) {
        return PlantUnit.getPlantUnitList(businessUnitCode, divisionCode, names, descriptions, isOrderByNames);
    }
    
    @Override
    public List<PlantUnitVO> getPlantList(String divisionCode, String names, String descriptions, boolean isOrderByNames) {
        return this.getPlantList( BusinessUnitUtil.getBusinessUnit(), divisionCode, names, descriptions, isOrderByNames);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CodeDetailVO> getCodeDetailListByScope(String masterCode, String codeScope) {
        // useCache 정보 조회 (in cache)
        String codeMasterInfoStr = getCodeMasterInfo( masterCode );
        String[] codeMasterInfo = null;
        boolean useCache = false;
//        if( !NullUtil.isNone(codeMasterInfoStr) ){
//            codeMasterInfo = codeMasterInfoStr.split( PlmConstants.DELIM_1_S );
//            if( codeMasterInfo != null && codeMasterInfo.length > 1 ){
//                useCache = Boolean.valueOf( codeMasterInfo[1] );
//            }
//            else{
//                throw new ApplicationException( "plm.common.error.cache", new Object[] { "masterCode=" + masterCode + ", codeMasterInfoStr=" + codeMasterInfoStr } );
//            }
//        }else{
//            throw new ApplicationException( "plm.common.error.codemaster.nodata", new Object[] { "masterCode=" + masterCode + ", codeMasterInfoStr=" + codeMasterInfoStr } );
//        }

        List<CodeDetailVO> result = null;
        if( useCache ){
            Object oCache = CacheUtil.getCache("commonCodeCache", masterCode + '-' + codeScope + userSession.getUserLocale());
            if (oCache == null) {
                result = getCodeDetailList( masterCode, codeScope, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE );
                CacheUtil.putCache("commonCodeCache", masterCode + '-' + codeScope, result);
            } else {
                LOGGER.debug("---------> getCodeList ::::::: masterCode = " + masterCode + ", codeScope = " +  codeScope);
                result = (List<CodeDetailVO>)((SimpleValueWrapper)oCache).get();
            }
        }
        else{
            LOGGER.debug("---------> getCodeList( no cache ) ::::::: masterCode = " + masterCode + ", codeScope = " +  codeScope);
            result = getCodeDetailList( masterCode, codeScope, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE );
        }

        return result;
    }
    
    private List<CodeDetailVO> getCodeDetailListSub( String masterCode, String codeScope, String states ){
        List<CodeDetailVO> result = null;
        CodeMasterVO codeMasterVO = this.getCodeMaster(masterCode);
        if( codeMasterVO != null){
            result = getCodeDetailList(codeMasterVO.getObid(), codeScope,  states);

        }
        return result;
    }
    @Override
    public List<CodeDetailVO> getCodeDetailList(String codeMasterObid, String codeScope, String states) {
        CodeDetailVO searchInfo = new CodeDetailVO();
        searchInfo.setUsingOrganizationList(codeScope);
        searchInfo.setStates(states);
        return this.getCodeDetailList(codeMasterObid, searchInfo);
    }

    @Override
    public List<CodeDetailVO> getRangeList(String codeId) {
        return getRangeList(codeId, false);
    }

    @Override
    public List<CodeDetailVO> getRangeList(String codeId, boolean sort) {
        String[] strArray = codeId.split("\\.");
        List<String> strList = ClassInfoUtil.getRanges(strArray[0], strArray[1], sort);
        List<CodeDetailVO> result = new ArrayList<CodeDetailVO>();

        for(String str : strList){
            CodeDetailVO vo = new CodeDetailVO();
            vo.setNames(str);vo.setDescriptions(str);vo.setTitles(str);
            result.add(vo);
        }
        return result;
    }

    @Override
    public List<CodeDetailVO> getClassNameList(String codeScope, boolean orderByDesc) {
        List<ClassNameForDisplayVO> classList = ClassInfoUtil.getChildClassListForCombo(codeScope);
        List<CodeDetailVO> result = new ArrayList<CodeDetailVO>();
        for(ClassNameForDisplayVO classVO : classList){
            CodeDetailVO vo = new CodeDetailVO();
            vo.setNames(classVO.getCalssName());vo.setDescriptions(classVO.getDisplayedName());vo.setTitles(classVO.getDisplayedName());
            result.add(vo);
        }
        return result;
    }

    @Override
    @Cacheable(value = "valueCache", key = "#codeScope")
    public List<CodeDetailVO> getStatesList(String codeScope) {
        List<StateInfo> stateInfoList = LifeCycleUtil.getLifieCycleStateListByName(codeScope);
        List<CodeDetailVO> result = new ArrayList<CodeDetailVO>();
        for (StateInfo statInfo : stateInfoList){
            CodeDetailVO vo = new CodeDetailVO();
            vo.setNames(statInfo.getStateName());
            vo.setDescriptions(statInfo.getStateName());
            vo.setTitles(statInfo.getStateName());
            result.add(vo);
        }
        return result;
    }

    @Override
    @Cacheable(value = "valueCache", key = "#codeScope")
    public List<CodeDetailVO> getPolicyList(String codeScope) {
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(codeScope);
        List<String> lifeCycleList = classInfo.getAllowedLifeCycleList();

        List<CodeDetailVO> result = new ArrayList<CodeDetailVO>();
        for (String str : lifeCycleList){
            CodeDetailVO vo = new CodeDetailVO();
            vo.setNames(str);
            vo.setDescriptions(str);
            vo.setTitles(str);
            result.add(vo);
        }
        return result;
    }
    @Override
    public List<CodeMasterVO> getCodeMasterList(CodeMasterVO searchInfo) {
    	return CodeMaster.getCodeMasterList("%" + searchInfo.getNames() + "%",true);
    }
    @Override
    public List<CodeDetailVO> getCodeDetailList(String codeMasterObid, String codeDetailNames) {
        CodeDetailVO searchInfo = new CodeDetailVO();
        searchInfo.setNames( codeDetailNames );
        return this.getCodeDetailList(codeMasterObid, searchInfo);
    }

    @Override
    public List<CodeDetailVO> getCodeDetailList(CodeDetailSearchVO searchInfo, boolean isPaging){
    	Map<String,String> parmMap = new HashMap<String,String>();
        parmMap.put("states", ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);
        parmMap.put("titles", searchInfo.getTitles());
        parmMap.put("organizationList", searchInfo.getDivisionCode());
        parmMap.put("attribute01", searchInfo.getAttribute01());
        parmMap.put("attribute02", searchInfo.getAttribute02());
        parmMap.put("attribute03", searchInfo.getAttribute03());
    	return CodeMaster.getCodeDetailList(searchInfo.getCodeMasterName(), parmMap, GlobalConstants.LANG_KO, true, isPaging,searchInfo);
    }
    @Override
    public List<CodeDetailVO> getCodeDetailList(String codeMasterObid, CodeDetailVO searchInfo) {
    	return CodeMaster.getCodeDetailList(codeMasterObid, searchInfo.getNames(), searchInfo.getUsingOrganizationList(), searchInfo.getStates(),GlobalConstants.LANG_KO);
    }
    
    @Override
    public CodeMaster txnCreateCodeMaster(CodeMasterVO codeMasterVO) {
        codeMasterVO.setClassName(ApplicationSchemaConstants.BIZCLASS_CODEMASTER);
        codeMasterVO.setStates(ApplicationSchemaConstants.STATE_WITHOUT_STATE_EXISTS);
        CodeMaster inputDom = (CodeMaster)DomUtil.toDom(codeMasterVO);
        inputDom.createObject();
        return new CodeMaster(codeMasterVO.getObid());
    }
    @Override
    public CodeDetail txnCreateCodeDetail(String codeMasterObid, CodeDetailVO codeDetailVO, List<OrganizationsVO> relatedOrgList) {
        StringBuffer usingOrganizationList = new StringBuffer();
        if( relatedOrgList != null ){
            for( int inx = 0; inx < relatedOrgList.size(); inx++ ){
                usingOrganizationList.append("^").append(relatedOrgList.get(inx).getNames()).append("^");
            }
        }
        codeDetailVO.setClassName( ApplicationSchemaConstants.BIZCLASS_CODEDETAIL );
        codeDetailVO.setLifeCycle( ApplicationSchemaConstants.LIFECYCLE_ACTIVE_INACTIVE );
        codeDetailVO.setUsingOrganizationList( usingOrganizationList.toString() );
        codeDetailVO.setRevision( codeMasterObid + usingOrganizationList.toString().replaceAll("\\^\\^", "^") );
        CodeDetail inputDom = (CodeDetail)DomUtil.toDom(codeDetailVO);
        inputDom.createObject();
        String codeDetailObid = codeDetailVO.getObid();
        RelationShip.connect(ApplicationSchemaConstants.RELCLASS_CODEMASTER2CODE, codeMasterObid, codeDetailObid, null);
        if( relatedOrgList != null ){
            for( int inx = 0; inx < relatedOrgList.size(); inx++ ){
                RelationShip.connect(ApplicationSchemaConstants.RELCLASS_CODE2ORGANIZATION, codeDetailObid, relatedOrgList.get(inx).getObid(), null);
                this.evictCacheForCodeDetail( codeMasterObid, relatedOrgList.get(inx).getNames() );     // Cache 정보 삭제
            }
        }
        return new CodeDetail(codeDetailObid);
    }
    @Override
    public CodeMasterVO getCodeMaster(String names) {
    	return CodeMaster.findCodeMaster(names);
    }
    @Override
    public CodeDetailVO getCodeDetail(String codeMasterObid, String names) {
    	return CodeMaster.getCodeDetail(codeMasterObid, names);
    }
    @Override
    public CodeMaster txnUpdateCodeMaster(String codeMasterObid,
            CodeMasterVO codeMasterVO) {
        //1. Update CodeMaster
        CodeMaster updateDom = new CodeMaster(codeMasterObid);
        updateDom.getVo().setTitles(codeMasterVO.getTitles());
        updateDom.getVo().setDescriptions(codeMasterVO.getDescriptions());
        updateDom.getVo().setUsecache(codeMasterVO.getUsecache());
        updateDom.getVo().setAuthType(codeMasterVO.getAuthType());
        updateDom.modifyObject();

        // cache에 저장된 codeMaster scope, useCache 정보 삭제
        //CacheUtil.evictCache("valueCache", "CodeMasterInfo-" + codeMasterVO.getNames());
        CacheUtil.evictCache("commonCodeMasterCache", codeMasterVO.getNames());

        return new CodeMaster(codeMasterObid);
    }
    @Override
    public CodeDetail txnUpdateCodeDetail(String codeMasterObid,
            String codeDetailObid,
            CodeDetailVO codeDetailVO,
            List<OrganizationsVO> relatedOrgList) {
        StringBuffer usingOrganizationList = new StringBuffer();
        if( relatedOrgList != null ){
            for( int inx = 0; inx < relatedOrgList.size(); inx++ ){
                usingOrganizationList.append("^").append(relatedOrgList.get(inx).getNames()).append("^");
            }
        }

        //1. Update CodeDetail
        CodeDetail updateDom = new CodeDetail(codeDetailObid);
        String[] ordOrgNameList = updateDom.getVo().getUsingOrganizationList() == null ? null : updateDom.getVo().getUsingOrganizationList().split("\\^\\^");
        updateDom.getVo().setSequences( codeDetailVO.getSequences() );
        updateDom.getVo().setTitles( codeDetailVO.getTitles() );
        updateDom.getVo().setDescriptions( codeDetailVO.getDescriptions() );
        updateDom.getVo().setDisplayNameKr( codeDetailVO.getDisplayNameKr() );
        updateDom.getVo().setUsingOrganizationList( usingOrganizationList.toString() );
        updateDom.getVo().setRevision( codeMasterObid + usingOrganizationList.toString().replaceAll("\\^\\^", "^") );
        updateDom.getVo().setSubCodeMaster( codeDetailVO.getSubCodeMaster() );
        updateDom.getVo().setAttribute01( codeDetailVO.getAttribute01() );
        updateDom.getVo().setAttribute02( codeDetailVO.getAttribute02() );
        updateDom.getVo().setAttribute03( codeDetailVO.getAttribute03() );
        updateDom.getVo().setAttribute04( codeDetailVO.getAttribute04() );
        updateDom.getVo().setAttribute05( codeDetailVO.getAttribute05() );


        updateDom.modifyObject();
        updateDom.change(null, null, null, codeDetailVO.getStates());           // 상태 정보 변경

        //2. Delete Relationship : Code2Organization
        List<BusinessRelationObjectVO> organizationRelList = updateDom.getRelationship(ApplicationSchemaConstants.RELCLASS_CODE2ORGANIZATION);
        if( organizationRelList != null && organizationRelList.size() > 0 ){
            for( int inx = 0; inx < organizationRelList.size(); inx++ ){
                RelationShip.disconnect(organizationRelList.get(inx));
            }
        }

        //3. Create Relationship : Code2Organization
        if( relatedOrgList != null ){
            for( int inx = 0; inx < relatedOrgList.size(); inx++ ){
                RelationShip.connect(ApplicationSchemaConstants.RELCLASS_CODE2ORGANIZATION, codeDetailObid, relatedOrgList.get(inx).getObid(), null);
                this.evictCacheForCodeDetail( codeMasterObid, relatedOrgList.get(inx).getNames() );   // Cache 정보 삭제
            }
        }

        //4. 기존 Organization 관련 Cache 삭제
        if( ordOrgNameList != null && ordOrgNameList.length > 0 ){
            for( int inx = 0; inx< ordOrgNameList.length; inx++ ){
                this.evictCacheForCodeDetail( codeMasterObid, ordOrgNameList[inx].replaceAll("\\^", "") );   // Cache 정보 삭제
            }
        }

        return new CodeDetail(codeDetailObid);
    }
    @Override
    public void txnUpdateStateCodeDetail(String codeMasterObid, String activeStr, List<CodeDetailVO> codeDetailList) {
        CodeDetail updateDom = null;
        for( int inx = 0; inx < codeDetailList.size(); inx++ ){
            updateDom = new CodeDetail(codeDetailList.get(inx).getObid());
            updateDom.change(null, null, null, activeStr);

            List<OrganizationsVO> relatedOrgList = updateDom.getRelatedObjects( ApplicationSchemaConstants.RELCLASS_CODE2ORGANIZATION );
            if( relatedOrgList != null && relatedOrgList.size() > 0 ){
                for( int jnx = 0; jnx < relatedOrgList.size(); jnx++ ){
                    this.evictCacheForCodeDetail( codeMasterObid, relatedOrgList.get(jnx).getNames() );       // Cache 정보 삭제
                }
            }
        }
    }
    @Override
    public void txnDeleteCodeDetail(String codeMasterObid, List<CodeDetailVO> codeDetailList) {
        CodeDetail deleteDom = null;
        for( int inx = 0; inx < codeDetailList.size(); inx++ ){
            deleteDom = new CodeDetail(codeDetailList.get(inx).getObid());

            // 삭제 전 관련 Cache 정보 삭제
            List<OrganizationsVO> relatedOrgList = deleteDom.getRelatedObjects( ApplicationSchemaConstants.RELCLASS_CODE2ORGANIZATION );
            if( relatedOrgList != null && relatedOrgList.size() > 0 ){
                for( int jnx = 0; jnx < relatedOrgList.size(); jnx++ ){
                    this.evictCacheForCodeDetail( codeMasterObid, relatedOrgList.get(jnx).getNames() );       // Cache 정보 삭제
                }
            }

            deleteDom.deleteObject();
        }
    }
    @Override
    public void txnDeleteCodeMaster(String codeMasterObid) {
        CodeMaster deleteDom = new CodeMaster( codeMasterObid );
        deleteDom.deleteObject();
    }
    private void evictCacheForCodeDetail(String codeMasterObid, String codeScope){
        CodeMaster masterDom = new CodeMaster(codeMasterObid);
        CodeMasterVO masterVO = masterDom.getVo();
        LOGGER.debug("---------> evict ::::::: masterCode = " + masterVO.getNames() + ", codeScope = " + codeScope);
        CacheUtil.evictCache("commonCodeCache", masterVO.getNames() + "-" + codeScope);
    }

    @Override
    public void txnCreateCodeDetail(String codeMasterName, CodeDetailVO codeDetailVO, OrganizationsVO orgVO) {
        CodeMasterVO masterVO = this.getCodeMaster(codeMasterName);
        if( masterVO != null ){
            // 중복체크
            CodeDetailVO existDetailVO = this.getCodeDetail(masterVO.getObid(), codeDetailVO.getNames());
            if( existDetailVO == null ){
                // Sequence 설정
                List<CodeDetailVO> detailList = this.getCodeDetailList(masterVO.getObid(), existDetailVO);
                if( detailList != null ){
                    codeDetailVO.setSequences( detailList.size() + 1 );
                }
                codeDetailVO.setStates(ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);

                // Organization 설정
                List<OrganizationsVO> orgList = new ArrayList<OrganizationsVO>();
                orgList.add(orgVO);

                this.txnCreateCodeDetail( masterVO.getObid(), codeDetailVO, orgList );
            }
        }
    }
    public String getCodeMasterInfo( String codeMasterName ) {
        String result=null;
        Object oCache = CacheUtil.getCache("commonCodeMasterCache", codeMasterName);
        if (oCache == null) {
            CodeMasterVO codeMasterVo = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_CODEMASTER, codeMasterName, true);
            result = (codeMasterVo == null ? "" : codeMasterVo.getCodeMasterScope() + BusinessCommonConstants.DELIM_1 + codeMasterVo.getUsecache());
            CacheUtil.putCache("commonCodeMasterCache", codeMasterName, result);

        }else{
            result = (String)((SimpleValueWrapper)oCache).get();
        }
        return result;
    }
    @Override
    public List<CodeDetailVO> getCodeDetailListForInactive(String masterCode, String codeScope) {
        return getCodeDetailList( masterCode, codeScope, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_INACTIVE );
    }
    @Override
    public List<CodeDetailVO> getCodeDetailListForAll(String masterCode, String codeScope) {
        return getCodeDetailList( masterCode, codeScope, GlobalConstants.FLAG_TYPE_ALL );
    }
    @Override
    public List<CodeMasterVO> getCodeMasterListForManagement(String userId) {
        // User의 Auth Type 가져오기
        List<ChangeAuthVO> retrieveAuthList = getAuthListByDivisionAndUser(userId);
        List<CodeMasterVO> rtnAuthTypeList = new ArrayList<CodeMasterVO>();
        List<CodeMasterVO> lastAuthTypeList = new ArrayList<CodeMasterVO>();
        String authName = "";
        List<String> tmpAuthTypeDup = new ArrayList<String>();

        for(int i=0; i<retrieveAuthList.size(); i++){
            authName = retrieveAuthList.get(i).getAuthName();
            rtnAuthTypeList = getCodeMasterByAssignedAuthType(authName);
            for(int j=0; j<rtnAuthTypeList.size(); j++){
                boolean codeDup = false;
                for(int k=0; k<tmpAuthTypeDup.size(); k++){
                    if(tmpAuthTypeDup.get(k).equals(rtnAuthTypeList.get(j).getNames())){
                        codeDup = true;
                    }
                }
                if(!codeDup){
                    tmpAuthTypeDup.add(rtnAuthTypeList.get(j).getNames());
                    lastAuthTypeList.add(rtnAuthTypeList.get(j));
                }
            }
        }
        return lastAuthTypeList;
    }
    @Override
    public List<CodeMasterVO> getCodeMasterByAssignedAuthType(String authName) {
    	return CodeMaster.getCodeMasterByAssignedAuthType(authName);
    }
    private List<ChangeAuthVO> getAuthListByDivisionAndUser(String userId){
    	return ChangeAuth.getAuthListByDivisionAndUser(userId,userSession.getPlantUnitCode());
    }
    

	@Override
	public List<CodeDetailVO> getCodeDetailListbyCodeName(String codeMasterName, String codeNames) {
		return CodeMaster.getCodeDetailWithNames(codeMasterName,codeNames);
	}
}