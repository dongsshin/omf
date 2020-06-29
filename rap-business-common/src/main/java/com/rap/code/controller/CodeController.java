package com.rap.code.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rap.code.model.CodeVO;
import com.rap.code.model.SearcherVO;
import com.rap.code.service.CodeService;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.MessageUtil;
import com.rap.omc.api.util.general.UserManagementUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.ResponseConstants;
import com.rap.omc.foundation.ui.service.UIService;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.framework.annotation.SCRequestDataset;
import com.rap.omc.framework.controller.BaseController;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.ResponseMapper;
import com.rap.omc.util.TimeServiceUtil;
import com.rap.user.service.UsersService;
import com.rap.util.BusinessCommonConstants;
import com.rap.util.BusinessUnitUtil;

import rap.api.object.common.dom.CodeDetail;
import rap.api.object.common.dom.CodeMaster;
import rap.api.object.common.model.CodeDetailVO;
import rap.api.object.common.model.CodeMasterVO;
import rap.api.object.organization.dom.DivisionUnit;
import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.organization.model.OrganizationsVO;
import rap.api.object.organization.model.UsersVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.CodeConstants;
import rap.application.constants.CommandConstants;
@Controller
public class CodeController extends BaseController {
    static final Logger LOGGER = LoggerFactory.getLogger(CodeController.class);

    @Autowired
    private UserSession userSession;

    @Resource(name = "codeService")
    private CodeService codeService;

    @Resource(name = "usersService")
    private UsersService usersService;

    @Resource(name = "uiService")
    private UIService uiService;

    @RequestMapping( "/common/code/searchInitData.do" )
    public String searchInitData( @SCRequestDataset( "toReadList" ) List<CodeVO> idList, ModelMap map ){
        ResponseMapper rm = new ResponseMapper();
        CodeVO tmpCodeVO;
        String tmpId;
        String tmpScope;
        
        String userDivision = "";
        String userBusiness = "";
            
        if(userSession.getPropertyMap() != null) {
            userDivision = userSession.getPropertyMap().get(BusinessCommonConstants.USER_PROP_DIVISION);
            userBusiness = userSession.getPropertyMap().get(BusinessCommonConstants.USER_PROP_BUSINESS_UNIT);   
        }

        rm.setData( "searchBusiness", userBusiness);
        rm.setData( "searchDivision", userDivision);
        rm.setData( "userBusiness", userBusiness);
        rm.setData( "userDivision", userDivision);
        rm.setData( "currentUser", userSession.getUserId());
        rm.setData( "userNameAndSsoid", userSession.getUserNameKor() + "(" + userSession.getSsoid() +")" );
        //rm.setData( "userInfo", userSession.duplicate() );

        Users curUser = null;
        for( int index = 0; index < idList.size(); index ++ ){
            tmpCodeVO = (CodeVO)idList.get( index );
            tmpId = tmpCodeVO.getId();
            tmpScope = tmpCodeVO.getScope();
            if( tmpId.equals( CodeConstants.CODE_SUB_CODE_MASTER ) ){
                List<CodeMasterVO> subCodeMasterList = codeService.getCodeMasterByAssignedAuthType("");
                rm.setData( CodeConstants.CODE_SUB_CODE_MASTER, subCodeMasterList );
            }
            else if( tmpId.equals( CodeConstants.CODE_NAME_AUTH_TYPE ) ){
                List<CodeDetailVO> authList = codeService.getCodeDetailListByScope(CodeConstants.CODE_NAME_AUTH_TYPE, BusinessCommonConstants.MAIN_COMPANY_NAME);
                rm.setData( CodeConstants.CODE_NAME_AUTH_TYPE, authList);  // AuthType 목록 정보 설정(Search, Create 공통)
            }
            else if( tmpId.equals( CodeConstants.USER_PLANT_LIST ) ){
                if(curUser == null){
                    curUser = new Users( userSession.getUserBizObid());
                }
                rm.setData( tmpId, curUser.retrievePlantUnitList() );
            }
            else if( tmpId.equals( CodeConstants.USER_PLANT_LIST ) ){
                if(curUser == null){
                    curUser = new Users( userSession.getUserBizObid() );
                }
                rm.setData( tmpId, curUser.retrievePlantUnitList() );
            }
            else if( tmpId.equals( CodeConstants.USER_MANAGEMENTGROUP_LIST ) ){
                if(curUser == null){
                    curUser = new Users( userSession.getUserBizObid() );
                }
                rm.setData( tmpId, curUser.retrieveManagementGroupList() );
            }
            else if( tmpId.equals( CodeConstants.CODE_AFFILIATE_LIST ) ){
                rm.setData( tmpId, codeService.getAffiliateList("",true) );
            }           
            else if( tmpId.equals( CodeConstants.USER_DIVISION_LIST ) ){
                curUser = new Users( userSession.getUserBizObid() );
                List<DivisionUnitVO> userDivisionList = (new ArrayList<DivisionUnitVO>(curUser.retrieveDivisionUnitList()));
                rm.setData( tmpId, userDivisionList );
                
                DivisionUnitVO userDivisionVO = null;
                userDivisionVO = DivisionUnit.getVOByName(userDivision);
                if ( !NullUtil.isNull(userDivisionVO) ) {
                    if ( ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_INACTIVE.equals(userDivisionVO.getStates()) ) userDivision = "";
                }
                
                if ( !NullUtil.isNone(userDivisionList) ) {
                    if ( NullUtil.isNone(userDivision) ) {
                        UserManagementUtil.setUserProperty(userSession.getUserId(), BusinessCommonConstants.USER_PROP_DIVISION, userDivisionList.get(0).getNames());
                        rm.setData( "searchDivision", userDivisionList.get(0).getNames() );
                        rm.setData( "userDivision", userDivisionList.get(0).getNames() );
                        //userSession.setDivisionUnitCode(userDivisionList.get(0).getNames());
                    }
                }
            }
            else if( tmpId.equals( CodeConstants.CODE_COMPANY_LIST ) ){
                rm.setData( tmpId, codeService.getCompanyList(tmpCodeVO.getNames(),tmpCodeVO.getDescriptions()) );
            }
            else if( tmpId.equals( CodeConstants.CODE_BUSINESS_LIST ) ){
                if( StrUtil.isEmpty(tmpScope) ){
                    if( !StrUtil.isEmpty(tmpCodeVO.getNames()) || !StrUtil.isEmpty(tmpCodeVO.getDescriptions()) ){
                        rm.setData( tmpId, codeService.getBusinessList(userBusiness, tmpCodeVO.getNames(),tmpCodeVO.getDescriptions(), true) );
                    }
                    else{
                        rm.setData( tmpId, codeService.getBusinessList(userBusiness, tmpCodeVO.getNames(),tmpCodeVO.getDescriptions()) );
                    }
                }
                else{
                    if( !StrUtil.isEmpty(tmpCodeVO.getNames()) || !StrUtil.isEmpty(tmpCodeVO.getDescriptions()) ){
                        rm.setData( tmpId, codeService.getBusinessList(tmpScope, tmpCodeVO.getNames(),tmpCodeVO.getDescriptions(), true) );
                    }
                    else{
                        rm.setData( tmpId, codeService.getBusinessList(tmpScope, tmpCodeVO.getNames(),tmpCodeVO.getDescriptions()) );
                    }
                }
            }
            else if( tmpId.equals( CodeConstants.CODE_DIVISION_LIST ) ){
                if( StrUtil.isEmpty(tmpScope) ){
                    if( !StrUtil.isEmpty(tmpCodeVO.getNames()) || !StrUtil.isEmpty(tmpCodeVO.getDescriptions()) ){
                        rm.setData( tmpId, codeService.getDivisionList(userBusiness, tmpCodeVO.getNames(),tmpCodeVO.getDescriptions(),true) );
                    }
                    else{
                        rm.setData( tmpId, codeService.getDivisionList(userBusiness, tmpCodeVO.getNames(),tmpCodeVO.getDescriptions()) );
                    }
                }
                else{
                    rm.setData( "searchBusiness", tmpScope );
                    rm.setData( "searchDivision", "" );
                    if( !StrUtil.isEmpty(tmpCodeVO.getNames()) || !StrUtil.isEmpty(tmpCodeVO.getDescriptions()) ){
                        rm.setData( tmpId, codeService.getDivisionList(tmpScope, tmpCodeVO.getNames(),tmpCodeVO.getDescriptions(), true) );
                    }
                    else{
                        rm.setData( tmpId, codeService.getDivisionList(tmpScope, tmpCodeVO.getNames(),tmpCodeVO.getDescriptions()));
                    }
                }
            }
            else if( tmpId.endsWith( CodeConstants.DIVISION_LIST_ALL ) ){
                if( !StrUtil.isEmpty(tmpCodeVO.getNames()) || !StrUtil.isEmpty(tmpCodeVO.getDescriptions()) ){
                    rm.setData( tmpId, codeService.getDivisionList("", tmpCodeVO.getNames(), tmpCodeVO.getDescriptions(),false) );
                }
                else{
                    rm.setData( tmpId, codeService.getDivisionList("", tmpCodeVO.getNames(), tmpCodeVO.getDescriptions()) );
                }
            }
            else if( tmpId.endsWith( CodeConstants.CODE_PLANT_LIST_ALL ) ){
                if( !StrUtil.isEmpty(tmpCodeVO.getNames()) || !StrUtil.isEmpty(tmpCodeVO.getDescriptions()) ){
                    rm.setData( tmpId, codeService.getPlantList(userBusiness, "", tmpCodeVO.getNames(),tmpCodeVO.getDescriptions(), true));
                }
                else{
                    rm.setData( tmpId, codeService.getPlantList(userBusiness, "", tmpCodeVO.getNames(),tmpCodeVO.getDescriptions()));
                }
            }
            else if( tmpId.endsWith( CodeConstants.CODE_PLANT_LIST ) ){
                if( StrUtil.isEmpty(tmpScope) ){
                    if( !StrUtil.isEmpty(tmpCodeVO.getNames()) || !StrUtil.isEmpty(tmpCodeVO.getDescriptions()) ){
                        rm.setData( tmpId, codeService.getPlantList(userBusiness, userDivision, tmpCodeVO.getNames(),tmpCodeVO.getDescriptions(), true) );
                    }
                    else{
                        rm.setData( tmpId, codeService.getPlantList(userBusiness, userDivision, tmpCodeVO.getNames(),tmpCodeVO.getDescriptions()));
                    }
                }
                else{
                    rm.setData( "searchDivision", tmpScope );
                    rm.setData( "searchPlant", "" );
                    if( !StrUtil.isEmpty(tmpCodeVO.getNames()) || !StrUtil.isEmpty(tmpCodeVO.getDescriptions()) ){
                        rm.setData( tmpId, codeService.getPlantList(userBusiness, tmpScope, tmpCodeVO.getNames(),tmpCodeVO.getDescriptions(), true) );
                    }
                    else{
                        rm.setData( tmpId, codeService.getPlantList(userBusiness, tmpScope, tmpCodeVO.getNames(),tmpCodeVO.getDescriptions()));
                    }
                }
            }
            else if ( tmpId.endsWith( CodeConstants.CODE_CLASS_NAME_LIST ) ){
                rm.setData( tmpId, codeService.getClassNameList(tmpScope, tmpCodeVO.getOrderByDesc()) );
            }
            else if ( tmpId.endsWith( CodeConstants.CODE_POLICY_LIST ) ){
                rm.setData( tmpId, codeService.getPolicyList(tmpScope) );
            }
            else if ( tmpId.endsWith( CodeConstants.CODE_STATES_LIST ) ){
                rm.setData( tmpId, codeService.getStatesList(tmpScope) );
            }
            else if( tmpId.endsWith( CodeConstants.CODE_ANOTHER_LIST ) ){
            }
            else{
                if( tmpScope != null && tmpScope.endsWith( CodeConstants.CODE_RANGE_LIST ) ){
                    rm.setData( tmpId, codeService.getRangeList( tmpId));
                }
                else{
                    String codeMasterInfoStr = codeService.getCodeMasterInfo(tmpCodeVO.getId());
                    String[] codeMasterInfo = null;
                    String codeScope = "";
                    if( codeMasterInfoStr != null ){
                        codeMasterInfo = codeMasterInfoStr.split( BusinessCommonConstants.DELIM_1_S );
                        codeScope = codeMasterInfo[0];
                    }
                    if( codeScope != null && codeScope.endsWith( ApplicationSchemaConstants.BIZCLASS_COMPANY ) ){
                        rm.setData( tmpId, codeService.getCodeDetailListByScope(tmpId, BusinessCommonConstants.MAIN_COMPANY_NAME) );
                    }
                    else if( codeScope != null && (codeScope.endsWith( ApplicationSchemaConstants.BIZCLASS_BUSINESSUNIT ))){
                        if( StrUtil.isEmpty(tmpScope) ){
                            rm.setData( tmpId, codeService.getCodeDetailListByScope( tmpId, userBusiness ) );
                        }
                        else{
                            rm.setData( tmpId, codeService.getCodeDetailListByScope( tmpId, tmpScope ) );
                        }
                    }
                    else if( codeScope != null && (codeScope.endsWith( ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT ))){
                        if( StrUtil.isEmpty(tmpScope) ){
                            rm.setData( tmpId, codeService.getCodeDetailListByScope( tmpId, userDivision ) );
                        }
                        else{
                            rm.setData( tmpId, codeService.getCodeDetailListByScope( tmpId, tmpScope ) );
                        }
                    }
                    else{
                        rm.setData( tmpId, "");
                    }
                }
            }
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(TimeServiceUtil.getDBLocalTime());
        rm.setData("currentYYYY", String.valueOf(cal.get(cal.YEAR)));
        rm.setData("currentMM", String.format("%02d", cal.get(cal.MONTH) + 1));
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        rm.setData("currentDate", sdf.format(cal.getTime()));

        rm.setModelMap( map );
        return GlobalConstants.AJAX_VIEW;
    }

    /**
     * Code Master 목록 조회
     * @param searchInfo
     * @param model
     * @return
     */
    @RequestMapping( "/common/code/retrieveCodeMasterList.do" )
    public String retrieveCodeMasterList( @SCRequestDataset( "codeMasterVO" ) CodeMasterVO searchInfo, ModelMap model ){
        ResponseMapper rm = new ResponseMapper();
        rm.setData( "codeMasterList", codeService.getCodeMasterList( searchInfo ) );
        rm.setModelMap( model );
        return GlobalConstants.AJAX_VIEW;
    }

    /**
     * Code Detail 목록 조회
     * @param searchInfo
     * @param model
     * @return
     */
    @RequestMapping( "/common/code/retrieveCodeDetailList.do" )
    public String retrieveCodeDetailList( @SCRequestDataset( "codeDetailVO" ) CodeDetailVO searchInfo, @SCRequestDataset( "codeMasterObid" ) String codeMasterObid, ModelMap model ){
        ResponseMapper rm = new ResponseMapper();
        rm.setData( "codeDetailList", codeService.getCodeDetailList( codeMasterObid, searchInfo) );
        rm.setModelMap( model );

        return GlobalConstants.AJAX_VIEW;
    }
    
    /**
     * Code Detail 목록 조회 (개별 화면 관리용)
     * @param searchInfo
     * @param model
     * @return
     */
    @RequestMapping( "/common/code/retrieveCodeDetailListForManage.do" )
    public String retrieveCodeDetailListForManage( @SCRequestDataset( "codeDetailVO" ) CodeDetailVO searchInfo, @SCRequestDataset( "masterCode" ) String masterCode, ModelMap model ){
        ResponseMapper rm = new ResponseMapper();

        // Admin인지 확인
        UsersVO usersVO = usersService.getUserInfo(userSession.getUserId());
       
        Boolean isAdmin = true;
        String defaultScope = null;
        CodeMasterVO codeMasterVO = codeService.getCodeMaster( masterCode );
        if( codeMasterVO != null ){
            // BOM Admin 일 경우에만 제약
            if( !isAdmin ){
                if( ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT.equals(codeMasterVO.getCodeMasterScope()) ){
                    defaultScope = userSession.getDivisionUnitCode();
                }
            }
            searchInfo.setUsingOrganizationList( defaultScope );
            rm.setData( "defaultScope", defaultScope );
            rm.setData( "codeMasterVO", codeMasterVO );
            rm.setData( "codeDetailList", codeService.getCodeDetailListByScope(codeMasterVO.getNames(), defaultScope));
        }
        else{
            rm.setStatusCode( ResponseConstants.STATUS_VALIDATION_ERROR );
            rm.setMessage( "There is no code master(" + masterCode + ")" );
        }
        
        rm.setModelMap( model );

        return GlobalConstants.AJAX_VIEW;
    }

    /**
     * Code Master 생성
     * @param codeMasterVO
     * @param map
     * @return
     */
    @RequestMapping("/common/code/createCodeMaster.do")
    public String createCodeMaster( @SCRequestDataset( "codeMasterVO" ) CodeMasterVO codeMasterVO, ModelMap map) {
        if( !uiService.checkMenuAccess(CommandConstants.cmdCodeMasterCreate, "")) throw new ApplicationException( "plm.common.error.menu.access" );
        
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        ObjectRootVO result = null;

        try {
            //0. Validation
            if( codeMasterVO.getNames() == null || codeMasterVO.getNames().isEmpty() ) {
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                
                message = MessageUtil.getMessage("plm.admin.validation.inputdata", new Object[] { " (Master Code)" });                
                //message = "Please input data. (Master Code)";
            }
            else if( codeMasterVO.getTitles() == null || codeMasterVO.getTitles().isEmpty() ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = MessageUtil.getMessage("plm.admin.validation.inputdata", new Object[] { " (Master Code Display)" });
                //message = "Please input data. (Master Code Display)";
            }
            else if( codeMasterVO.getCodeMasterScope() == null || codeMasterVO.getCodeMasterScope().isEmpty() ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = MessageUtil.getMessage("plm.admin.validation.inputdata", new Object[] { " (Master Scope)" });
                //message = "Please select data. (Master Scope)";
            }
            else if( codeMasterVO.getDescriptions() == null || codeMasterVO.getDescriptions().isEmpty() ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = MessageUtil.getMessage("plm.admin.validation.inputdata", new Object[] { " (Description)" });
                //message = "Please input data. (Description)";
            }
            else if( codeMasterVO.getNames().length() > 128 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = MessageUtil.getMessage("plm.admin.validation.maxlength", new Object[] { " Master Code"," : 128" });
                //message = "Max length for Master Code : 128";
            }
            else if( codeMasterVO.getTitles().length() > 256 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = MessageUtil.getMessage("plm.admin.validation.maxlength", new Object[] { " Master Code Name"," : 256" });
                //message = "Max length for Master Code Name : 256";
            }
            else if( codeMasterVO.getDescriptions().length() > 4000 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = MessageUtil.getMessage("plm.admin.validation.maxlength", new Object[] { " Description"," : 4000" });
                //message = "Max length for Description : 4000";
            }
            else{
                // 중복체크
                CodeMasterVO existCodeMaster = codeService.getCodeMaster(codeMasterVO.getNames());
                if( existCodeMaster != null ){
                    statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                    message = MessageUtil.getMessage("plm.admin.validation.dupcheck");
                }
                else{
                    result = codeService.txnCreateCodeMaster(codeMasterVO).getVo();
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = MessageUtil.getMessage("plm.common.error");;
        }
        finally{
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setData("CodeMasterVO", result);
            rm.setModelMap(map);
        }

        return GlobalConstants.AJAX_VIEW;
    }

    /**
     * Code Detail 생성
     * @param codeMasterObid
     * @param codeDetailVO
     * @param relatedOrgList
     * @param map
     * @return
     */
    @RequestMapping("/common/code/createCodeDetail.do")
    public String createCodeDetail(
            @SCRequestDataset( "codeMasterObid" ) String codeMasterObid,
            @SCRequestDataset( "codeDetailVO" ) CodeDetailVO codeDetailVO,
            @SCRequestDataset( "organizationList" ) List<OrganizationsVO> relatedOrgList,
            ModelMap map) {
        // Menu(Button) Access check 추가
        if( !uiService.checkMenuAccess(CommandConstants.cmdCodeDetailCreate, "") ){
            throw new ApplicationException( "plm.common.error.menu.access" );
        }
        
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        ObjectRootVO result = null;

        try {
            //0. Validation
            if( codeDetailVO.getNames() == null || codeDetailVO.getNames().isEmpty() ) {
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Please input data. (Code)";
            }
            else if( codeDetailVO.getTitles() == null || codeDetailVO.getTitles().isEmpty() ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Please input data. (Code Display)";
            }
            else if( codeDetailVO.getDescriptions() == null || codeDetailVO.getDescriptions().isEmpty() ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Please input data. (Description)";
            }
            else if( codeDetailVO.getStates() == null || codeDetailVO.getStates().isEmpty() ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Please select data. (Active)";
            }
            else if( relatedOrgList == null || relatedOrgList.size() < 1 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Please select data. (Applied Organization)";
            }
            else if( codeDetailVO.getNames().length() > 128 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Max length for Code : 128";
            }
            else if( codeDetailVO.getTitles().length() > 256 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Max length for Code Name : 256";
            }
            else if( codeDetailVO.getDescriptions().length() > 4000 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Max length for Description : 4000";
            }
            else if( codeDetailVO.getAttribute01() != null && codeDetailVO.getAttribute01().length() > 256 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Max length for Attribute01 : 256";
            }
            else if( codeDetailVO.getAttribute02() != null && codeDetailVO.getAttribute02().length() > 256 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Max length for Attribute02 : 256";
            }
            else if( codeDetailVO.getAttribute03() != null && codeDetailVO.getAttribute03().length() > 256 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Max length for Attribute03 : 256";
            }
            else{
                // 중복체크 (UsingOrganizationList 이용하여 중복체크 수행함)
                boolean isExist = false;
                String dupOrgInfo = "";
                String codeDisplayInfo = "";
                CodeDetailVO codeDetail = null;
                List<CodeDetailVO> existCodeDetailList = codeService.getCodeDetailList(codeMasterObid, codeDetailVO.getNames());
                if( existCodeDetailList != null && existCodeDetailList.size() > 0 ){
                    for( int jnx = 0; jnx < existCodeDetailList.size(); jnx++ ){
                        codeDetail = existCodeDetailList.get(jnx);
                        for( int inx = 0; inx < relatedOrgList.size(); inx++ ){
                            if( codeDetail.getUsingOrganizationList().indexOf(relatedOrgList.get(inx).getNames()) >= 0 ){
                                isExist = true;
                                dupOrgInfo = relatedOrgList.get(inx).getNames();
                                codeDisplayInfo = codeDetail.getTitles();
                                break;
                            }
                        }
                    }
                    result = codeService.txnCreateCodeDetail(codeMasterObid, codeDetailVO, relatedOrgList).getVo();
                }
                else{
                    result = codeService.txnCreateCodeDetail(codeMasterObid, codeDetailVO, relatedOrgList).getVo();
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = "Error occurred.";
        }
        finally{
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setData("CodeDetailVO", result);
            rm.setModelMap(map);
        }

        return GlobalConstants.AJAX_VIEW;
    }

    /**
     * Code Master 상세정보 조회
     * @param searchInfo
     * @param model
     * @return
     */
    @RequestMapping( "/common/code/retrieveCodeMaster.do" )
    public String retrieveCodeMaster( String codeMasterObid, ModelMap model ){
        ResponseMapper rm = new ResponseMapper();
        CodeMaster resultDom = new CodeMaster(codeMasterObid);
        rm.setData( "codeMaster", resultDom.getVo() );
        rm.setModelMap( model );

        return GlobalConstants.AJAX_VIEW;
    }

    /**
     * Code Detail 상세정보 조회
     * @param searchInfo
     * @param model
     * @return
     */
    @RequestMapping( "/common/code/retrieveCodeDetail.do" )
    public String retrieveCodeDetail( String codeDetailObid, ModelMap model ){
        ResponseMapper rm = new ResponseMapper();
        CodeDetail resultDom = new CodeDetail(codeDetailObid);
        rm.setData( "codeDetail", resultDom.getVo() );
        rm.setData( "organizationsList", resultDom.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_CODE2ORGANIZATION) );
        rm.setModelMap( model );

        return GlobalConstants.AJAX_VIEW;
    }

    /**
     * Code Master 수정
     * @param codeMasterObid
     * @param codeMasterVO
     * @param map
     * @return
     */
    @RequestMapping("/common/code/updateCodeMaster.do")
    public String updateCodeMaster(@SCRequestDataset( "codeMasterObid" ) String codeMasterObid,
                                   @SCRequestDataset( "codeMasterVO" ) CodeMasterVO codeMasterVO,
                                   ModelMap map) {
        if( !uiService.checkMenuAccess(CommandConstants.cmdCodeMasterUpdate, "") ) throw new ApplicationException( "plm.common.error.menu.access" );
        
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        ObjectRootVO result = null;

        try {
            if( codeMasterVO.getTitles() == null || codeMasterVO.getTitles().isEmpty() ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Please input data. (Master Code Display)";
            }
            else if( codeMasterVO.getCodeMasterScope() == null || codeMasterVO.getCodeMasterScope().isEmpty() ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Please select data. (Master Scope)";
            }
            else if( codeMasterVO.getDescriptions() == null || codeMasterVO.getDescriptions().isEmpty() ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Please input data. (Description)";
            }
            else if( codeMasterVO.getTitles().length() > 256 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Max length for Master Code Name : 256";
            }
            else if( codeMasterVO.getDescriptions().length() > 4000 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Max length for Description : 4000";
            }
            else{
                result = codeService.txnUpdateCodeMaster(codeMasterObid, codeMasterVO).getVo();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = "Error occurred.";
        }
        finally{
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setData("CodeMasterVO", result);
            rm.setModelMap(map);
        }

        return GlobalConstants.AJAX_VIEW;
    }
    
    @RequestMapping("/common/code/deleteCodeMaster.do")
    public String deleteCodeMaster(String codeMasterObid, ModelMap map) {
        if( !uiService.checkMenuAccess(CommandConstants.cmdCodeMasterDelete, "") )  throw new ApplicationException( "plm.common.error.menu.access" );
        
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";

        try {
            codeService.txnDeleteCodeMaster( codeMasterObid );
        }
        catch(ApplicationException ex){
            statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
            message = ex.getMessage();
        }
        catch(Exception ex){
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = "Error occurred.";
        }
        finally{
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setModelMap( map );
        }

        return GlobalConstants.AJAX_VIEW;
    }

    /**
     * Code Detail 수정
     * @param codeDetailObid
     * @param codeDetailVO
     * @param relatedOrgList
     * @param map
     * @return
     */
    @RequestMapping("/common/code/updateCodeDetail.do")
    public String updateCodeDetail(
            @SCRequestDataset( "codeMasterObid" ) String codeMasterObid,
            @SCRequestDataset( "codeDetailObid" ) String codeDetailObid,
            @SCRequestDataset( "codeDetailVO" ) CodeDetailVO codeDetailVO,
            @SCRequestDataset( "organizationList" ) List<OrganizationsVO> relatedOrgList,
            ModelMap map) {
        // Menu(Button) Access check 추가
        if( !uiService.checkMenuAccess(CommandConstants.cmdCodeDetailUpdate, "") ){
            throw new ApplicationException( "plm.common.error.menu.access" );
        }
        
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        ObjectRootVO result = null;

        try {
            //0. Validation
            if( codeDetailVO.getNames() == null || codeDetailVO.getNames().isEmpty() ) {
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Please input data. (Code)";
            }
            else if( codeDetailVO.getTitles() == null || codeDetailVO.getTitles().isEmpty() ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Please input data. (Code Display)";
            }
            else if( codeDetailVO.getDescriptions() == null || codeDetailVO.getDescriptions().isEmpty() ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Please input data. (Description)";
            }
            else if( codeDetailVO.getStates() == null || codeDetailVO.getStates().isEmpty() ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Please select data. (Active)";
            }
            else if( relatedOrgList == null || relatedOrgList.size() < 1 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Please select data. (Applied Organization)";
            }
            else if( codeDetailVO.getTitles().length() > 256 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Max length for Code Name : 256";
            }
            else if( codeDetailVO.getDescriptions().length() > 4000 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Max length for Description : 4000";
            }
            else if( codeDetailVO.getAttribute01() != null && codeDetailVO.getAttribute01().length() > 256 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Max length for Attribute01 : 256";
            }
            else if( codeDetailVO.getAttribute02() != null && codeDetailVO.getAttribute02().length() > 256 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Max length for Attribute02 : 256";
            }
            else if( codeDetailVO.getAttribute03() != null && codeDetailVO.getAttribute03().length() > 256 ){
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Max length for Attribute03 : 256";
            }
            else{
                // 중복체크 (UsingOrganizationList 이용하여 중복체크 수행함)
                boolean isExist = false;
                String dupOrgInfo = "";
                String codeDisplayInfo = "";
                CodeDetailVO codeDetail = null;
                List<CodeDetailVO> existCodeDetailList = codeService.getCodeDetailList(codeMasterObid, codeDetailVO.getNames());
                if( existCodeDetailList != null && existCodeDetailList.size() > 0 ){
                    for(int jnx = 0; jnx < existCodeDetailList.size(); jnx++ ){
                        codeDetail = existCodeDetailList.get(jnx);
                        if ( !codeDetail.getObid().equals(codeDetailObid) ){
                            for( int inx = 0; inx < relatedOrgList.size(); inx++ ){
                                if( codeDetail.getUsingOrganizationList().indexOf(relatedOrgList.get(inx).getNames()) >= 0 ){
                                    isExist = true;
                                    dupOrgInfo = relatedOrgList.get(inx).getNames();
                                    codeDisplayInfo = codeDetail.getTitles();
                                    break;
                                }
                            }
                        }
                    }

                    if( isExist ){
                        statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                        message = "Duplication error : <br>Please select another organization.<br>'" + dupOrgInfo + "' is selected in " + codeDisplayInfo;
                    }
                    else{
                        result = codeService.txnUpdateCodeDetail(codeMasterObid, codeDetailObid, codeDetailVO, relatedOrgList).getVo();
                    }
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = "Error occurred.";
        }
        finally{
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setData("CodeDetailVO", result);
            rm.setModelMap(map);
        }

        return GlobalConstants.AJAX_VIEW;
    }

    /**
     * Code Detail State 일괄수정
     * @param isActive
     * @param codeDetailList
     * @param map
     * @return
     */
    @RequestMapping("/common/code/updateStateCodeDetailList.do")
    public String updateStateCodeDetailList(
            @SCRequestDataset( "codeMasterObid" ) String codeMasterObid,
            @SCRequestDataset( "activeStr" ) String activeStr,
            @SCRequestDataset( "codeDetailList" ) List<CodeDetailVO> codeDetailList,
            ModelMap map) {
        // Menu(Button) Access check 추가
        if( !uiService.checkMenuAccess(CommandConstants.cmdCodeDetailActive, "") && !uiService.checkMenuAccess(CommandConstants.cmdCodeDetailInactive, "") ){
            throw new ApplicationException( "plm.common.error.menu.access" );
        }
        
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";

        try {
            codeService.txnUpdateStateCodeDetail(codeMasterObid, activeStr, codeDetailList);
        }
        catch(Exception ex){
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = "Error occurred.";
        }
        finally{
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setModelMap(map);
        }

        return GlobalConstants.AJAX_VIEW;
    }

    /**
     * Code Detail 일괄 삭제
     * @param codeDetailList
     * @param map
     * @return
     */
    @RequestMapping("/common/code/deleteCodeDetailList.do")
    public String deleteCodeDetailList(
            @SCRequestDataset( "codeMasterObid" ) String codeMasterObid,
            @SCRequestDataset( "codeDetailList" ) List<CodeDetailVO> codeDetailList,
            ModelMap map) {
        // Menu(Button) Access check 추가
        if( !uiService.checkMenuAccess(CommandConstants.cmdCodeDetailDelete, "") ){
            throw new ApplicationException( "plm.common.error.menu.access" );
        }
        
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";

        try {
            codeService.txnDeleteCodeDetail(codeMasterObid, codeDetailList);
        }
        catch(Exception ex){
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = "Error occurred.";
        }
        finally{
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setModelMap(map);
        }

        return GlobalConstants.AJAX_VIEW;
    }

    /**
     *
     *
     * @param codeDetailObid
     * @param model
     * @return
     */
    @RequestMapping( "/common/code/retrieveSearcher.do" )
    public String retrieveSearcher( @SCRequestDataset( "searcherVO" ) SearcherVO searcherVO, ModelMap map ){

        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";

        ResponseMapper rm = new ResponseMapper();
        rm.setData("resultList", codeService.findObjectWithSearcher(searcherVO));
        rm.setStatusCode( statusCode );
        rm.setMessage( message );
        rm.setModelMap(map);

        return GlobalConstants.AJAX_VIEW;
    }
//
//    /**
//     * Code Detail Excel Import (일괄등록)
//     * @param files
//     * @param uploadId
//     * @param codeMasterObid
//     * @param model
//     * @return
//     */
//    @RequestMapping("/common/code/excelImportCodeDetail.do")
//    public String excelImportCodeDetail(@RequestParam MultipartFile files,
//            @RequestParam String uploadId,
//            @RequestParam String codeMasterObid,
//            Model model){
//        // Menu(Button) Access check 추가
//        /*
//        if( !uiService.checkMenuAccess(PlmConstants.cmdCodeDetailExcelImport, "") ){
//            throw new ApplicationException( "plm.common.error.menu.access" );
//        }
//        */
//
//        List<CodeDetailVO> codeDetailList = new ArrayList<CodeDetailVO>();
//        CodeDetailVO codeDetail = null;
//        HashMap<String, Object> detailData = new HashMap<String, Object>();
//        HashMap<String, Object> result = null;
//        
//        try{
//            
//            if (!files.isEmpty()) {
//                InputStream in = files.getInputStream();
//                int colIndex = 0;
//                
//                if(files.getOriginalFilename().contains(".xlsx")) {  
//                    XSSFWorkbook xlsxWorkbook = new XSSFWorkbook(in);
//                    XSSFSheet xlsxSheet = xlsxWorkbook.getSheetAt(0);
//    
//                    codeDetail = null;
//                    colIndex = 0;
//                    for( int inx = 1; inx <= xlsxSheet.getLastRowNum(); inx++ ){
//                        XSSFRow row = xlsxSheet.getRow(inx);
//                        codeDetail = new CodeDetailVO();
//                        
//                        colIndex = 1;
//                        if( row.getCell(colIndex).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
//                            codeDetail.setSequences( row.getCell(colIndex) == null ? 0 : ((Double)row.getCell(colIndex).getNumericCellValue()).intValue() );
//                        } else {
//                            codeDetail.setSequences( row.getCell(colIndex) == null ? 0 : Integer.parseInt(CommonUtil.getExcelCellValue(row.getCell(colIndex))) );
//                        }
//                        colIndex++;
//                        codeDetail.setNames( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setTitles( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setDisplayNameKr( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setDescriptions(CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setStates( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setUsingOrganizationList( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setSubCodeMaster( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setAttribute01( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setAttribute02( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setAttribute03( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        
//                        codeDetailList.add( codeDetail );
//                    }
//                    xlsxWorkbook.close();
//                    
//                } 
//                else {
//                    HSSFWorkbook xlsWorkbook = new HSSFWorkbook(in);
//                    HSSFSheet xlsSheet = xlsWorkbook.getSheetAt(0);
//                    colIndex = 0;
//                    for( int inx = 1; inx <= xlsSheet.getLastRowNum(); inx++ ){
//                        HSSFRow row = xlsSheet.getRow(inx);
//                        codeDetail = new CodeDetailVO();
//                        
//                        colIndex = 1;
//                        if( row.getCell(colIndex).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
//                            codeDetail.setSequences( row.getCell(colIndex) == null ? 0 : ((Double)row.getCell(colIndex).getNumericCellValue()).intValue() );
//                        }
//                        else {
//                            codeDetail.setSequences( row.getCell(colIndex) == null ? 0 : Integer.parseInt(CommonUtil.getExcelCellValue(row.getCell(colIndex))) );
//                        }
//                        colIndex++;
//                        codeDetail.setNames( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setTitles( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setDescriptions( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setDisplayNameKr(CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setStates( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setUsingOrganizationList( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setSubCodeMaster( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setAttribute01( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setAttribute02( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        colIndex++;
//                        codeDetail.setAttribute03( CommonUtil.getExcelCellValue(row.getCell(colIndex)) );
//                        
//                        codeDetailList.add( codeDetail );
//                    }
//                    xlsWorkbook.close();
//                }
//                
//                result = codeService.txnExcelImportCodeDetail( codeMasterObid, codeDetailList );
//            }
//
//        }
//        catch( Exception e ){
//            e.printStackTrace();
//            result = new HashMap<String, Object>();
//            result.put( "message", e.getClass() );
//        }
//        
//        detailData.put( GlobalConstants.D_UPLOAD_ID, uploadId ); // 지우지 말 것.
//        detailData.put( "codeDetailList", codeDetailList ); // 지우지 말 것.
//        detailData.put( "result", result ); // 지우지 말 것.
//
//        HashMap<String, Object> resultData = new HashMap<String, Object>();
//        resultData.put( GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_SUCCESS );
//        resultData.put( GlobalConstants.M_MESSAGE, "" );
//        resultData.put( GlobalConstants.M_DATA, detailData );
//
//        JSONObject js = JSONObject.fromObject(resultData);
//        model.addAttribute( "result", js );
//        
//        return "common/file/uploadResponse";
//    }
//    


    /**
     * Code Management 목록 조회
     * @param searchInfo
     * @param model
     * @return
     */
    @RequestMapping( "/common/code/retrieveCodeManagementList.do" )
    public String retrieveCodeManagementList( ModelMap model ){
        
        String userId = userSession.getUserId();
        ResponseMapper rm = new ResponseMapper();
        rm.setData( "retrieveCodeManagementList", codeService.getCodeMasterListForManagement(userId));
        rm.setModelMap( model );

        return GlobalConstants.AJAX_VIEW;
    }

    /**
     * Scope List 조회
     * @param idList
     * @param map
     * @return
     */
    @RequestMapping( "/common/code/searchScopeList.do" )
    public String searchScopeList( @SCRequestDataset( "toReadList" ) List<CodeVO> idList, ModelMap map ){
        ResponseMapper rm = new ResponseMapper();
        CodeVO tmpCodeVO;
        String tmpId; 

        for( int index = 0; index < idList.size(); index ++ ){
            tmpCodeVO = (CodeVO)idList.get( index );
            tmpId = tmpCodeVO.getId();
            Users curUser = new Users( userSession.getUserBizObid());
            
            if( tmpId.equals( CodeConstants.USER_DIVISION_UNIT_LIST ) ){
                rm.setData( tmpId, curUser.retrieveDivisionUnitList() );
                rm.setData( "defaultUserScope", userSession.getPlantUnitCode());
            }else if( tmpId.equals( CodeConstants.USER_COMPANY_LIST ) ){
                rm.setData( tmpId, codeService.getCompanyList(tmpCodeVO.getNames(),tmpCodeVO.getDescriptions()));
                rm.setData( "defaultUserScope", BusinessCommonConstants.MAIN_COMPANY_NAME);
            }else if( tmpId.equals( CodeConstants.USER_BUSINESS_UNIT_LIST ) ){
                rm.setData( tmpId, BusinessUnitUtil.getBusinessUnitList() );
                rm.setData( "defaultUserScope", userSession.getBusinessUnitCode() );
            }
        }
        
        rm.setModelMap( map );
        return GlobalConstants.AJAX_VIEW;
    }
    
    /**
     * CodeMaster명으로 Code Detail 조회(Select Item 조회용)
     * @param codeMasterName
     * @param state
     * @param map
     * @return
     */
    @RequestMapping( "/common/code/retrieveCodeDetailListForCodeMaster.do" )
    public String retrieveCodeDetailListForCodeMaster( String codeMasterName, String state, ModelMap map ) {
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        List<CodeDetailVO> codeDeatilList = new ArrayList<CodeDetailVO>();
        try {
            if ( ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE.equals(state) ) {
                codeDeatilList = codeService.getCodeDetailList(codeMasterName, BusinessCommonConstants.MAIN_COMPANY_NAME,state);
            } else {
                codeDeatilList = codeService.getCodeDetailListByScope(codeMasterName, BusinessCommonConstants.MAIN_COMPANY_NAME);
            }
        } catch ( Exception ex) {
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = "Error occurred.";
        } finally {
            ResponseMapper rm = new ResponseMapper();
            rm.setData("codeDeatilList", codeDeatilList);
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setModelMap(map);
        }
        
        return GlobalConstants.AJAX_VIEW;
    }
}