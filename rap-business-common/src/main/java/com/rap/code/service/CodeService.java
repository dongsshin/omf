package com.rap.code.service;

import java.util.List;

import com.rap.code.model.CodeDetailSearchVO;
import com.rap.code.model.SearcherVO;
import com.rap.omc.api.object.dom.BusinessObjectRoot;

import rap.api.object.common.dom.CodeDetail;
import rap.api.object.common.dom.CodeMaster;
import rap.api.object.common.model.CodeDetailVO;
import rap.api.object.common.model.CodeMasterVO;
import rap.api.object.organization.model.AffiliateUnitVO;
import rap.api.object.organization.model.BusinessUnitVO;
import rap.api.object.organization.model.CompanyVO;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.organization.model.OrganizationsVO;
import rap.api.object.organization.model.PlantUnitVO;
public interface CodeService {
	
	public List<BusinessObjectRoot> findObjectWithSearcher(SearcherVO searcherVO);
    
    public List<CompanyVO> getCompanyList();
    public List<CompanyVO> getCompanyList(String names, String descriptions);
    public List<CompanyVO> getCompanyList(String names, String descriptions, boolean isOrderByNames);
    
    public List<BusinessUnitVO> getBusinessList(String businessUnitCode, String names, String descriptions);
    public List<BusinessUnitVO> getBusinessList(String businessUnitCode, String names, String descriptions, boolean isOrderByNames);
    
    public List<DivisionUnitVO> getDivisionList();
    public List<DivisionUnitVO> getDivisionList(String businessUnitCode);
    public List<DivisionUnitVO> getDivisionList(String businessUnitCode, String names, String descriptions);
    public List<DivisionUnitVO> getDivisionList(String businessUnitCode, String names, String descriptions, boolean isOrderByNames);
    public List<DivisionUnitVO> getDivisionList(String names, String descriptions);
    public List<DivisionUnitVO> getDivisionList(String names, String descriptions, boolean isOrderByNames);
    
    public List<AffiliateUnitVO> getAffiliateList(String divisionCode, boolean isOrderByNames);
    
    public List<PlantUnitVO> getPlantList(String divisionCode);
    public List<PlantUnitVO> getPlantList(String businessUnitCode, String divisionCode, String names, String descriptions);
    public List<PlantUnitVO> getPlantList(String businessUnitCode, String divisionCode, String names, String descriptions, boolean isOrderByNames);
    public List<PlantUnitVO> getPlantList(String divisionCode, String names, String descriptions, boolean isOrderByNames);
    
    public List<CodeDetailVO> getRangeList(String codeId);
    public List<CodeDetailVO> getRangeList(String codeId, boolean sort);
    public List<CodeDetailVO> getClassNameList(String CodeScope, boolean orderByDesc);
    public List<CodeDetailVO> getStatesList(String CodeScope);
    public List<CodeDetailVO> getPolicyList(String CodeScope);
    
    public List<CodeMasterVO> getCodeMasterList(CodeMasterVO searchInfo);
    public CodeMasterVO getCodeMaster(String names);
    public String getCodeMasterInfo(String codeMasterName);
    public List<CodeMasterVO> getCodeMasterListForManagement(String userId);
    public List<CodeMasterVO> getCodeMasterByAssignedAuthType(String authName);
    
    public List<CodeDetailVO> getCodeDetailListByScope(String masterCode, String codeScope);
    public List<CodeDetailVO> getCodeDetailList(String codeMasterObid, String codeScope, String states);
    public List<CodeDetailVO> getCodeDetailList(CodeDetailSearchVO searchInfo, boolean isPaging);
    public List<CodeDetailVO> getCodeDetailList(String codeMasterObid, CodeDetailVO searchInfo);
    public List<CodeDetailVO> getCodeDetailList(String codeMasterObid, String codeDetailNames);
    
    public List<CodeDetailVO> getCodeDetailListbyCodeName(String codeMasterName, String codeNames );
    public List<CodeDetailVO> getCodeDetailListForInactive(String masterCode, String codeScope);
    public List<CodeDetailVO> getCodeDetailListForAll(String masterCode, String codeScope);
    
    public CodeDetailVO getCodeDetail(String codeMasterObid, String names);
    
    public CodeMaster txnCreateCodeMaster(CodeMasterVO codeMasterVO);
    public CodeMaster txnUpdateCodeMaster(String codeMasterObid, CodeMasterVO codeMasterVO);
    public void txnDeleteCodeMaster(String codeMasterObid);
    
    
    public CodeDetail txnCreateCodeDetail(String codeMasterObid, CodeDetailVO codeDetailVO, List<OrganizationsVO> relatedOrgList);
    public void txnCreateCodeDetail(String codeMasterName, CodeDetailVO codeDetailVO, OrganizationsVO orgVO);
    public CodeDetail txnUpdateCodeDetail(String codeMasterObid, String codeDetailObid, CodeDetailVO codeDetailVO, List<OrganizationsVO> relatedOrgList);
    public void txnUpdateStateCodeDetail(String codeMasterObid, String activeStr, List<CodeDetailVO> codeDetailList);
    public void txnDeleteCodeDetail(String codeMasterObid, List<CodeDetailVO> codeDetailList);
    
}