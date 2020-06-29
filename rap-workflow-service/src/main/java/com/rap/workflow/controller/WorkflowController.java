/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : WorkflowController.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2015.03.13 kwanghyui.choi   Initial
 * 2015.11.23 eunsu.Jang  2D, 3D File Down 로직 추가
 * ===========================================
 */
package com.rap.workflow.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rap.code.service.CodeService;
import com.rap.omc.api.object.dom.BusinessObjectRoot;
import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.ResponseConstants;
import com.rap.omc.dataaccess.paging.model.PagingList;
import com.rap.omc.foundation.lifecycle.service.LifeCycleService;
import com.rap.omc.foundation.ui.service.UIService;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.framework.annotation.SCRequestDataset;
import com.rap.omc.framework.controller.BaseController;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.ResponseMapper;
import com.rap.user.service.UsersService;
import com.rap.util.BusinessCommonConstants;

import rap.api.object.common.model.CodeDetailVO;
import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.workflow.dom.WorkflowInboxTask;
import rap.api.object.workflow.model.ApprovalLineVO;
import rap.api.object.workflow.model.WorkflowInboxTaskVO;
import rap.application.constants.CodeConstants;
import rap.application.constants.CommandConstants;
import rap.application.constants.WorkflowConstants;
import rap.application.workflow.model.ApprovalHistoryVO;
import rap.application.workflow.model.ApprovalVO;
import rap.application.workflow.model.ReassignVO;
import rap.application.workflow.service.WorkflowService;


/**
 * <pre>
 * Class : WorkflowController
 * Description : TODO
 * </pre>
 *
 * @author kwanghyui.choi
 */
@Controller
public class WorkflowController extends BaseController{
    static final Logger LOGGER = LoggerFactory.getLogger(WorkflowController.class);

    @Autowired
    private UserSession userSession;

    @Resource(name = "workflowService")
    private WorkflowService workflowService;

    @Resource(name = "codeService")
    private CodeService codeService;

    @Resource(name = "uiService")
    private UIService uiService;

    @Resource(name = "usersService")
    private UsersService usersService;

    @Resource(name = "lifeCycleService")
    private LifeCycleService lifeCycleService;
    
    /**
     * Approvals 목록에 사용되는 초기 데이터를 조회 
     *
     * @param businessObjectRootVO
     * @param model
     * @return
     */
    @RequestMapping( value="/workflow/initWorkflowInfo.do", method = RequestMethod.POST )
    public String initWorkflowInfo(@SCRequestDataset( "businessObjectRootVO" ) BusinessObjectRootVO businessObjectRootVO, ModelMap model) {
        ResponseMapper rm = new ResponseMapper();
        BusinessObjectRoot businessObjectRoot = null;
        if(!StringUtils.isEmpty(businessObjectRootVO.getObid()) ) {
            businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid());
            businessObjectRootVO = businessObjectRoot.getVo();
        }
        
        if(StringUtils.isEmpty(businessObjectRootVO.getLifeCycle())) 
            throw new ApplicationException("api.object.error.workflow.illegalArgument", new Object[] { "Lifecycle is null" });
        
        List<CodeDetailVO> stateList                = workflowService.retrieveApprovalStateList(businessObjectRootVO.getLifeCycle());
        List<CodeDetailVO> allStateList             = workflowService.retrieveAllApprovalStateList(businessObjectRootVO.getLifeCycle());
        String distributionState                    = workflowService.retrieveDistributionState(businessObjectRootVO.getLifeCycle());
//        List<CodeDetailVO> routeInstructionsList    = codeService.getCodeListByScope("APPROVALLINE_APPROVAL_TYPE", BusinessCommonConstants.MAIN_COMPANY_NAME);
//        List<CodeDetailVO> processRoleList          = codeService.getCodeListByScope("APPROVALLINE_JOB_GRADE", BusinessCommonConstants.MAIN_COMPANY_NAME);
        
        List<CodeDetailVO> routeInstructionsList    =       codeService.getCodeDetailListByScope(CodeConstants.CODE_MASTER_NAME_APPROVALLINE_APPROVAL_TYPE, BusinessCommonConstants.MAIN_COMPANY_NAME);
        List<CodeDetailVO> processRoleList    =       codeService.getCodeDetailListByScope(CodeConstants.CODE_MASTER_NAME_APPROVALLINE_JOB_GRADE, BusinessCommonConstants.MAIN_COMPANY_NAME);
        
        List<ApprovalVO> approvalList   = null;
        if(!StringUtils.isEmpty(businessObjectRootVO.getObid())) {
            approvalList   =     workflowService.retrieveWorkflow(businessObjectRoot);
            ApprovalVO inProcessState    =       workflowService.retrieveInProcessWorkflowRouteNStep(businessObjectRoot);
            if(NullUtil.isNull(inProcessState.getState())) {
                inProcessState = new ApprovalVO();
                inProcessState.setState(businessObjectRootVO.getStates());
            }
            rm.setData("inProcessState",       inProcessState);
        }else{
            approvalList = new ArrayList<ApprovalVO>();
        }
        rm.setData("approvalList",          approvalList);
        
        rm.setData("businessObjectInfo",    businessObjectRootVO );
        rm.setData("className",             businessObjectRootVO.getClassName());
        rm.setData("lifeCycle",             businessObjectRootVO.getLifeCycle());
        rm.setData("stateList",             stateList);
        rm.setData("allStateList",          allStateList);
        rm.setData("distributionState",     distributionState);
        if(!StringUtils.isEmpty(businessObjectRootVO.getObid()) ) {
            rm.setData("creatorObid",           Users.getUsers(businessObjectRootVO.getCreator()).getObid());
        } else {
            rm.setData("creatorObid",           userSession.getUserBizObid());
        }
        rm.setData("routeInstructionsList", routeInstructionsList);
        rm.setData("processRoleList",       processRoleList);
        rm.setData("loginUserObid",         userSession.getUserBizObid());
//        rm.setData("approverTypeList",      approverTypeList);
        //rm.setData("prodPlanApprovalRoleList",      prodPlanApprovalRoleList);

        rm.setModelMap( model );
        return "PlmConstants.AJAX_VIEW";
    }

//    @RequestMapping( value="/workflow/initWorkflowDemoteInfo.do", method = RequestMethod.POST )
//    public String initWorkflowDemoteInfo(@SCRequestDataset( "businessObjectRootVO" ) BusinessObjectRootVO businessObjectRootVO, ModelMap model) {
//        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid());
//        ResponseMapper rm = new ResponseMapper();
//        rm.setData("businessObjectRootVO",           businessObjectRoot.getVo());
//        rm.setModelMap( model );
//        return "PlmConstants.AJAX_VIEW";
//    }
//
    @RequestMapping( value="/workflow/retrieveWorkflowList.do", method = RequestMethod.POST )
    public String retrieveWorkflowList(@SCRequestDataset( "businessObjectRootVO" ) BusinessObjectRootVO businessObjectRootVO, ModelMap model) {
        if(StringUtils.isEmpty(businessObjectRootVO.getObid())) 
            throw new ApplicationException("api.object.error.workflow.illegalArgument", new Object[] { "Obid is null" });
        
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid());
        List<ApprovalVO> approvalList = workflowService.retrieveWorkflow(businessObjectRoot);
        
        ResponseMapper rm = new ResponseMapper();
        rm.setData("approvalList", approvalList);
        rm.setModelMap( model );
        return "PlmConstants.AJAX_VIEW";
    }

    @RequestMapping( value="/workflow/retrieveBusinessObjectRoot.do", method = RequestMethod.POST )
    public String retrieveBusinessObjectRoot(@SCRequestDataset( "businessObjectRootVO" ) BusinessObjectRootVO businessObjectRootVO, ModelMap model) {
        if(StringUtils.isEmpty(businessObjectRootVO.getObid())) 
            throw new ApplicationException("api.object.error.workflow.illegalArgument", new Object[] { "Obid is null" });
        
        ResponseMapper rm = new ResponseMapper();
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid());
        
        List<CodeDetailVO> routeInstructionsList    =       codeService.getCodeDetailListByScope(CodeConstants.CODE_MASTER_NAME_APPROVALLINE_APPROVAL_TYPE, BusinessCommonConstants.MAIN_COMPANY_NAME);
        List<CodeDetailVO> processRoleList    =       codeService.getCodeDetailListByScope(CodeConstants.CODE_MASTER_NAME_APPROVALLINE_JOB_GRADE, BusinessCommonConstants.MAIN_COMPANY_NAME);
        Users creatorDom = Users.getUsers(businessObjectRoot.getVo().getCreator());
        rm.setData("businessObjectRootVO",  businessObjectRoot.getVo());
        rm.setData("creatorObid",           creatorDom == null ? "" : creatorDom.getObid());
        rm.setData("routeInstructionsList", routeInstructionsList);
        rm.setData("processRoleList",       processRoleList);
        rm.setData("loginUser",             userSession.getUserBizObid());
        rm.setModelMap( model );
        return "PlmConstants.AJAX_VIEW";
    }

    /**
     * Modify Approvals을 위한 목록 조회
     *
     * @param businessObjectRootVO
     * @param model
     * @return
     */
    @RequestMapping( value="/workflow/retrieveInProcessWorkflowRouteNStep.do", method = RequestMethod.POST )
    public String retrieveInProcessWorkflowRouteNStepInfo(@SCRequestDataset( "businessObjectRootVO" ) BusinessObjectRootVO businessObjectRootVO, ModelMap model) {
        if(StringUtils.isEmpty(businessObjectRootVO.getObid())) 
            throw new ApplicationException("api.object.error.workflow.illegalArgument", new Object[] { "Obid is null" });
        
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid());
        ApprovalVO inProcessState    =       workflowService.retrieveInProcessWorkflowRouteNStep(businessObjectRoot);

        ResponseMapper rm = new ResponseMapper();
        rm.setData("inProcessState",   inProcessState);
        rm.setModelMap( model );
        return "PlmConstants.AJAX_VIEW";
    }

    /**
     * 결재 정보 업데이트
     *
     * @param businessObjectRootVO
     * @param approvaList
     * @param model
     * @return
     */
    @RequestMapping( value="/workflow/updateWorkflow.do", method = RequestMethod.POST )
    public String updateWorkflow(
            @SCRequestDataset( "businessObjectRootVO" ) BusinessObjectRootVO businessObjectRootVO,
            @SCRequestDataset( "approvalList" ) List<ApprovalVO> approvaList,
            ModelMap model) {
        if(StringUtils.isEmpty(businessObjectRootVO.getObid())) 
            throw new ApplicationException("api.object.error.workflow.illegalArgument", new Object[] { "Obid is null" });
        
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid());

        // Modify Approval - Done 수행 시 Validation
        Map<String, Object> inputParams = new HashMap<String, Object>();
        inputParams.put( "approvalList", approvaList );
        businessObjectRoot.preProcessUpdate( inputParams );
        
        workflowService.txnUpdateWorkflow(businessObjectRoot, approvaList);

        ResponseMapper rm = new ResponseMapper();
        rm.setStatusCode(ResponseConstants.STATUS_SUCCESS);
        rm.setMessage( "updated successfully." );
        rm.setModelMap( model );

        return "PlmConstants.AJAX_VIEW";
    }

    @RequestMapping( value="/workflow/submitApproval.do", method = RequestMethod.POST )
    public String submitApproval(
            @SCRequestDataset( "approvalVO" ) ApprovalVO approvalVO,
            @SCRequestDataset( "sendBackToList" ) List<ApprovalVO> sendBackToList,
            @SCRequestDataset( "fileList" ) List<FilesVO> fileList,
            ModelMap model) {
        if(NullUtil.isNone(approvalVO.getObid()) ||
                NullUtil.isNone(approvalVO.getApprovalStatus()) ||
                NullUtil.isNone(approvalVO.getComments()) ||
                NullUtil.isNone(approvalVO.getWorkflowInboxTaskObid()) ||
                NullUtil.isNone(approvalVO.getWorkflowRouteObid()) ||
                NullUtil.isNull(approvalVO.getStep()) ||
                NullUtil.isNone(approvalVO.getParallelNodeProcessionRule())){
            throw new IllegalArgumentException();
        }
        ResponseMapper rm = new ResponseMapper();
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(approvalVO.getObid());

        if(!NullUtil.isNone(sendBackToList)){
            businessObjectRoot.getVo().getOutData().put("sendBackToList", sendBackToList);
        }
        try{
            workflowService.txnSubmitApproval(businessObjectRoot, approvalVO);
        }catch(Exception e) {
            rm.setStatusCode(ResponseConstants.STATUS_TRIGGER_ERROR);
            rm.setMessage( e.getMessage());
            rm.setModelMap(model);

            return "PlmConstants.AJAX_VIEW";
        }

        //approval File 연계..
        if(!NullUtil.isNone(fileList)){
            BusinessObjectRoot businessObjectRootHistory = DomUtil.toDom(approvalVO.getWorkflowInboxTaskObid());
            businessObjectRootHistory.checkInFiles(GlobalConstants.FILE_UNLOCK, GlobalConstants.FILE_APPEND, fileList);
        }
        
        rm.setStatusCode(ResponseConstants.STATUS_SUCCESS);
        rm.setMessage( "Action Completed!" );
        rm.setModelMap(model);

        return "PlmConstants.AJAX_VIEW";
    }
    
    /**
     * For CRMS Simple approval list 
     */
    @RequestMapping( value="/workflow/submitApprovalBySimple.do", method = RequestMethod.POST )
    public String submitApprovalBySimple(
            @SCRequestDataset( "approvalVO" ) ApprovalVO approvalVO,
            @SCRequestDataset( "fileList" ) List<FilesVO> fileList,
            @SCRequestDataset( "toUpdateApprovalList" ) List<ApprovalVO> toUpdateApprovalList,
            ModelMap model) {
        if(NullUtil.isNone(approvalVO.getObid()) ||
                NullUtil.isNone(approvalVO.getApprovalStatus()) ||
                NullUtil.isNone(approvalVO.getWorkflowInboxTaskObid()) ||
                NullUtil.isNone(approvalVO.getWorkflowRouteObid()) ||
                NullUtil.isNull(approvalVO.getStep()) ||
                NullUtil.isNone(approvalVO.getParallelNodeProcessionRule())){
            throw new IllegalArgumentException();
        }
        ResponseMapper rm = new ResponseMapper();
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(approvalVO.getObid());

        try{
            workflowService.txnSubmitApprovalBySimple(businessObjectRoot, approvalVO, toUpdateApprovalList);
        }catch(Exception e) {
            rm.setStatusCode(ResponseConstants.STATUS_TRIGGER_ERROR);
            rm.setMessage( e.getMessage());
            rm.setModelMap(model);
            e.printStackTrace();
            return "PlmConstants.AJAX_VIEW";
        }

        //approval File 연계..
        if(!NullUtil.isNone(fileList)){
            BusinessObjectRoot businessObjectRootHistory = DomUtil.toDom(approvalVO.getWorkflowInboxTaskObid());
            businessObjectRootHistory.checkInFiles(GlobalConstants.FILE_UNLOCK, GlobalConstants.FILE_APPEND, fileList);
        }
        if(approvalVO.getApprovalStatus().equals(WorkflowConstants.APPROVAL_STATUS_APPROVE)) {
            workflowService.rescursiveApproveBySystem(businessObjectRoot);
        }
        
        rm.setStatusCode(ResponseConstants.STATUS_SUCCESS);
        rm.setMessage( "Action Completed!" );
        rm.setModelMap(model);

        return "PlmConstants.AJAX_VIEW";
    }
    
    /**
     * Self Reject
     *
     * @param approvalVO
     * @param model
     * @return
     */
    @RequestMapping( value="/workflow/submitSelfReject.do", method = RequestMethod.POST )
    public String submitSelfReject(@SCRequestDataset( "approvalVO" ) ApprovalVO approvalVO, ModelMap model) {
        if(NullUtil.isNull(approvalVO.getObid())) throw new IllegalArgumentException();
        
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(approvalVO.getObid());
        workflowService.txnSubmitSelfReject(businessObjectRoot, approvalVO);
        ResponseMapper rm = new ResponseMapper();
        rm.setStatusCode(ResponseConstants.STATUS_SUCCESS);
        rm.setMessage( "Action Completed!" );
        rm.setModelMap(model);
        return "PlmConstants.AJAX_VIEW";
    }
//
//    @RequestMapping( value="/workflow/addManualDistribution.do", method = RequestMethod.POST )
//    public String addManualDistribution(@SCRequestDataset( "businessObjectRootVO" ) BusinessObjectRootVO businessObjectRootVO,
//            @SCRequestDataset( "approvalVO" ) ApprovalVO approvalVO,
//            ModelMap model) {
//        if(NullUtil.isNull(businessObjectRootVO.getObid()) ||
//                NullUtil.isNull(approvalVO.getAssigneeObid()) ){
//            throw new ApplicationException("");
//        }
//        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid());
//        List<ApprovalVO> approvalVOList = new ArrayList<ApprovalVO>();
//        approvalVOList.add(approvalVO);
//        workflowService.txnUpdateWorkflow(businessObjectRoot, approvalVOList);
//        ResponseMapper rm = new ResponseMapper();
//        rm.setModelMap( model );
//        return "PlmConstants.AJAX_VIEW";
//    }
//
    @RequestMapping( value="/workflow/retrieveApprovalHistoryList.do", method = RequestMethod.POST )
    public String retrieveApprovalHistoryList(@SCRequestDataset( "businessObjectRootVO" ) BusinessObjectRootVO businessObjectRootVO, ModelMap model) {
        if(NullUtil.isNull(businessObjectRootVO.getObid())){
            throw new ApplicationException("obid is null");
        }
        List<ApprovalHistoryVO> approvalHistoryList   =       workflowService.retrieveApprovalHistoryList(businessObjectRootVO);

        ResponseMapper rm = new ResponseMapper();
        rm.setData("approvalHistoryList",           approvalHistoryList);
        rm.setModelMap( model );

        return "PlmConstants.AJAX_VIEW";
    }
    
    @RequestMapping( value="/workflow/retrievePreviousRejectedHistory.do", method = RequestMethod.POST )
    public String retrievePreviousRejectedHistory(@SCRequestDataset( "businessObjectRootVO" ) BusinessObjectRootVO businessObjectRootVO, ModelMap model) {
        if(NullUtil.isNull(businessObjectRootVO.getObid())){
            throw new ApplicationException("obid is null");
        }
        List<ApprovalHistoryVO> approvalHistoryList   =       workflowService.retrievePreviousRejectedHistory(businessObjectRootVO);

        ResponseMapper rm = new ResponseMapper();
        rm.setData("approvalHistoryList",           approvalHistoryList);
        rm.setModelMap( model );

        return "PlmConstants.AJAX_VIEW";
    }
    
//
//    @RequestMapping( value="/workflow/doDemote.do", method = RequestMethod.POST )
//    public String doDemote(@SCRequestDataset( "demoteVO" ) DemoteVO demoteVO,
//            ModelMap model) {
//        // Menu(Button) Access check 추가
//        if( !uiService.checkMenuAccess(PlmConstants.cmdDemote, demoteVO.getObid()) ){
//            throw new ApplicationException( "plm.common.error.menu.access" );
//        }
//
//        if(NullUtil.isNull(demoteVO.getObid()) || NullUtil.isNull(demoteVO.getComments())){
//            throw new IllegalArgumentException();
//        }
//        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(demoteVO.getObid());
//        workflowService.txnDoWithdraw(businessObjectRoot, demoteVO);
//        ResponseMapper rm = new ResponseMapper();
//        rm.setStatusCode(ResponseConstants.STATUS_SUCCESS);
//        rm.setMessage( "Action Completed!" );
//        rm.setModelMap(model);
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    @RequestMapping( value="/workflow/isLastApprovalInWorking.do", method = RequestMethod.POST )
//    public String isLastApprovalInWorking(@SCRequestDataset( "approvalVO" ) ApprovalVO approvalVO,
//            ModelMap model) {
//        if(NullUtil.isNull(approvalVO.getObid())){
//            throw new IllegalArgumentException();
//        }
//        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(approvalVO.getObid());
//        boolean bResult = workflowService.isLastApprovalInWorking(businessObjectRoot, approvalVO);
//        ResponseMapper rm = new ResponseMapper();
//        rm.setData("isLastApprovalInWorking", bResult);
//        rm.setModelMap(model);
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    @RequestMapping( value="/workflow/isLastApproval.do", method = RequestMethod.POST )
//    public String isLastApproval(@SCRequestDataset( "obid" ) String obid,
//            @SCRequestDataset( "state" ) String state,
//            ModelMap model) {
//        if(NullUtil.isNull(obid)){
//            throw new IllegalArgumentException();
//        }
//        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(obid);
//        boolean bResult = workflowService.isLastApproval(businessObjectRoot);
//        ResponseMapper rm = new ResponseMapper();
//        rm.setData("isLastApproval", bResult);
//        rm.setModelMap(model);
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
    @RequestMapping( value="/workflow/reassignApprover.do", method = RequestMethod.POST )
    public String reassignApprover(@SCRequestDataset( "reassignVO" ) ReassignVO reassignVO, ModelMap model) {
        if(NullUtil.isNull(reassignVO.getObid())) throw new ApplicationException("api.object.error.workflow.illegalArgument", new Object[] { "Obid is null" });
        
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(reassignVO.getObid());
        workflowService.txnReassignApprover(businessObjectRoot, reassignVO);
        ResponseMapper rm = new ResponseMapper();
        rm.setStatusCode(ResponseConstants.STATUS_SUCCESS);
        rm.setMessage( "Action Completed!" );
        rm.setModelMap(model);
        return "PlmConstants.AJAX_VIEW";
    }

    @RequestMapping( value="/workflow/retrieveSendBackToList.do", method = RequestMethod.POST )
    public String retrieveSendBackToList(@SCRequestDataset( "approvalVO" ) ApprovalVO approvalVO,
            ModelMap model) {
        if(NullUtil.isNull(approvalVO.getObid()) || NullUtil.isNull(approvalVO.getApprovalStatus())){
            throw new IllegalArgumentException();
        }
        List<ApprovalVO> rtnApprovalVOVOList = workflowService.retrieveSendBackToList(approvalVO);

        ResponseMapper rm = new ResponseMapper();
        rm.setData("sendBackToList",            rtnApprovalVOVOList);
        rm.setModelMap(model);
        return "PlmConstants.AJAX_VIEW";
    }
    
    @RequestMapping( value="/workflow/retrieveFileList.do", method = RequestMethod.POST )
    public String retrieveFileList(@SCRequestDataset( "workflowInboxTaskVO" ) WorkflowInboxTaskVO workflowInboxTaskVO, ModelMap model) {
        if(NullUtil.isNull(workflowInboxTaskVO)) throw new IllegalArgumentException();
        WorkflowInboxTask workflowInboxTask = DomUtil.toDom(workflowInboxTaskVO);
        ResponseMapper rm = new ResponseMapper();
        rm.setData("relatedFilesVOList",            workflowService.retrieveFilesVOList(workflowInboxTask));
        rm.setModelMap(model);
        return "PlmConstants.AJAX_VIEW";
    }

    /**
     * Multiple Reassign (Approval, AdminApproval 탭에서 사용하는 기능)
     * @param reassignVOList
     * @param map
     * @return
     */
    @RequestMapping( value="/workflow/doReassignList.do" )
    public String doReassignList( @SCRequestDataset( "reassignVOList" ) List<ReassignVO> reassignVOList, ModelMap map ) {
        // 권한체크
        boolean isOwner = true;
        boolean isExist = false;
        List<WorkflowInboxTaskVO> inboxTaskVOList = Users.retrieveApprovalList(userSession.getUserId());
        if( inboxTaskVOList != null && inboxTaskVOList.size() > 0 ){
            for( int inx = 0; inx < reassignVOList.size(); inx++ ){
                isExist = false;
                for( int jnx = 0; jnx < inboxTaskVOList.size(); jnx++ ){
                    if( reassignVOList.get(inx).getWorkflowStepNodeUserObid().equals(inboxTaskVOList.get(jnx).getRouteNodeObid()) ){
                        isExist = true;
                        break;
                    }
                }
                if( !isExist ){
                    isOwner = false;
                    break;
                }
            }
        }
        else{
            isOwner = false;
        }
        
        // Menu(Button) Access check 추가
        if( !isOwner && !uiService.checkMenuAccess(CommandConstants.cmdAdminApprovalPortal, "") ){
            throw new ApplicationException( "plm.common.error.menu.access" );
        }

        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";

        try {

            if( reassignVOList != null && reassignVOList.size() > 0 ){
                for( int inx = 0; inx < reassignVOList.size(); inx++ ){
                    reassignVOList.get(inx).setFromAssigneeObid( userSession.getUserBizObid() );
                }

                workflowService.txnDoReassignList( reassignVOList );
            }
            else{
                statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                message = "Target list is null. (or size is 0)";
            }
        }
        catch( ApplicationException ax ){
            statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
            message = ax.getMessage();
        }
        catch( Exception ex ){
            statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
            message = "Selected user is exist in approval line.<br>Please select another user or remove target object.";
        }
        finally{
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setModelMap( map );
        }

        return "PlmConstants.AJAX_VIEW";
    }

//    @RequestMapping( value="/workflow/doRevoke.do", method = RequestMethod.POST )
//    public String doReassign(@SCRequestDataset( "approvalVO" ) ApprovalVO approvalVO,
//            ModelMap model) {
//        if(NullUtil.isNull(approvalVO.getObid()) ||
//                NullUtil.isNull(approvalVO.getApprovalStatus()) ||
//                NullUtil.isNull(approvalVO.getWorkflowStepNodeUserObid()) ||
//                NullUtil.isNull(approvalVO.getWorkflowRouteObid())){
//            throw new IllegalArgumentException();
//        }
//
//        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(approvalVO.getObid());
//
//        workflowService.txnRevoke(businessObjectRoot, approvalVO);
//        ResponseMapper rm = new ResponseMapper();
//        rm.setStatusCode(ResponseConstants.STATUS_SUCCESS);
//        rm.setMessage( "Action Completed!" );
//        rm.setModelMap(model);
//
//        return "PlmConstants.AJAX_VIEW";
//    }


//
//    /**
//     * ECO 필수 결재자 정보 저장
//     * @param createList
//     * @param updateList
//     * @param deleteList
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/updateECOApproverInfoList.do" )
//    public String updateECOApproverInfoList(
//            @SCRequestDataset( "createList" ) List<ECOEssentialApproverSearchVO> createList,
//            @SCRequestDataset( "updateList" ) List<ECOEssentialApproverSearchVO> updateList,
//            @SCRequestDataset( "deleteList" ) List<ECOEssentialApproverSearchVO> deleteList,
//            ModelMap map ) {
//        // Menu(Button) Access check 추가
//        if( !uiService.checkMenuAccess(PlmConstants.cmdSaveApproverInfo, "") ){
//            throw new ApplicationException( "plm.common.error.menu.access" );
//        }
//
//        int statusCode = ResponseConstants.STATUS_SUCCESS;
//        String message = "";
//        boolean isValid = true;
//
//        try {
//            if( createList != null && createList.size() > 0 ){
//                for(int inx = 0; inx < createList.size(); inx++ ){
//                    if( createList.get(inx).getStep() == null || createList.get(inx).getStep().isEmpty() ){
//                        statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
//                        message = "Please select step. (row number : " + (inx + 1) + ")";
//                        isValid = false;
//                        break;
//                    }
//                    else if( createList.get(inx).getUserId() == null || createList.get(inx).getUserId().isEmpty() ){
//                        statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
//                        message = "Please search/select user data. (row number : " + (inx + 1) + ")";
//                        isValid = false;
//                        break;
//                    }
//                }
//            }
//
//            if( updateList != null && updateList.size() > 0 ){
//                for(int inx = 0; inx < updateList.size(); inx++ ){
//                    if( updateList.get(inx).getStep() == null || updateList.get(inx).getStep().isEmpty() ){
//                        statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
//                        message = "Please select step. (row number : " + (inx + 1) + ")";
//                        isValid = false;
//                        break;
//                    }
//                    else if( updateList.get(inx).getUserId() == null || updateList.get(inx).getUserId().isEmpty() ){
//                        statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
//                        message = "Please search/select user data. (row number : " + (inx + 1) + ")";
//                        isValid = false;
//                        break;
//                    }
//                    else if( updateList.get(inx).getEssentialFlag() == null || updateList.get(inx).getEssentialFlag().isEmpty() ){
//                        statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
//                        message = "Please select Auto Apploval User. (row number : " + (inx + 1) + ")";
//                        isValid = false;
//                        break;
//                    }
//                }
//            }
//
//            if( isValid ){
//                workflowService.txnUpdateECOApproverInfo(createList, updateList, deleteList);
//            }
//        }
//        catch(Exception ex){
//            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
//            message = "Error occurred.";
//        }
//        finally{
//            ResponseMapper rm = new ResponseMapper();
//            rm.setStatusCode( statusCode );
//            rm.setMessage( message );
//            rm.setModelMap( map );
//        }
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * ECO 필수결재자 조회 (For ApprovalLineState)
//     * @param userId
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/retrieveECOEssentialApproverList.do" )
//    public String retrieveECOEssentialApproverList( String site, String productGroup, String module, ModelMap map) {
//        ResponseMapper rm = new ResponseMapper();
//        if( StringUtils.isEmpty(site) ){
//            site = userSession.getPreferredSite();
//        }
//
//        rm.setData( "approverList", ECOEssentialApprover.retrieveECOEssentialApproverList( site, productGroup, module ) );
//        rm.setModelMap( map );
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
    /**
     * 결재대상 조회
     * @param userId
     * @param map
     * @return
     */
//    @RequestMapping( value="/workflow/retrieveApprovalList.do" )
//    public String retrieveApprovalList( @SCRequestDataset( "searchInfo" ) WorkflowInboxTaskSearchVO searchInfo, @SCRequestDataset( "userId" ) String userId, ModelMap map) {
//        ResponseMapper rm = new ResponseMapper();
//        Users users = null;
//        List<WorkflowInboxTaskVO> approvalList = null;
//
//        // 조건이 없을 경우 session에서 사용자정보 조회
//        if( StringUtils.isEmpty( userId ) ){
//            users = new Users( userSession.getUserBizObid() );
//            approvalList = users.retrieveApprovalList( searchInfo );
//        }
//        else{
//            approvalList = Users.retrieveApprovalList( searchInfo, userId );
//        }
//
//        int totalRow =0;
//        if ( approvalList instanceof OmcPagingList ) {
//            OmcPagingList<WorkflowInboxTaskVO> pagingList = (OmcPagingList<WorkflowInboxTaskVO>)approvalList;
//            totalRow =  pagingList.getTotalCount();
//        }
//
//        String loginUser = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, null);
//        rm.setData( "isSystemAdmin", AccessControl.hasRole(PlmConstants.ROLE_NAME_SYSTEM_ADMIN, loginUser));
//        rm.setData( "systemAdminName", usersService.retrieveUserInfo(loginUser).getTitles());
//        rm.setData( "searchId", userId);
//        rm.setData( "totRecCount", totalRow );
//        rm.setData( "approvalList", approvalList );
//        rm.setModelMap( map );
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * 로그인 한 사용자에게 배포된 목록 조회
//     * @param userId
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/retrieveDistributionList.do" )
//    public String retrieveDistributionList( @SCRequestDataset( "searchInfo" ) WorkflowInboxTaskSearchVO searchInfo, @SCRequestDataset( "userId" ) String userId, ModelMap map) {
//        ResponseMapper rm = new ResponseMapper();
//        Users users = null;
//        List<WorkflowInboxTaskVO> distributionList = null;
//
//        // 조건이 없을 경우 session에서 사용자정보 조회
//        if( StringUtils.isEmpty( userId ) ){
//            users = new Users( userSession.getUserBizObid() );
//            distributionList = users.retrieveDistributionList( searchInfo );
//        }
//        else{
//            distributionList = Users.retrieveDistributionList( searchInfo, userId );
//        }
//
//        int totalRow =0;
//        if ( distributionList instanceof OmcPagingList ) {
//            OmcPagingList<WorkflowInboxTaskVO> pagingList = (OmcPagingList<WorkflowInboxTaskVO>)distributionList;
//            totalRow =  pagingList.getTotalCount();
//        }
//
//        rm.setData( "totRecCount", totalRow );
//        rm.setData( "distributionList", distributionList );
//        rm.setModelMap( map );
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * 로그인 한 사용자의 요청목록 조회
//     * @param userId
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/retrieveRequestedList.do" )
//    public String retrieveRequestedList( @SCRequestDataset( "searchInfo" ) WorkflowInboxTaskSearchVO searchInfo, @SCRequestDataset( "userId" ) String userId, ModelMap map) {
//        ResponseMapper rm = new ResponseMapper();
//        Users users = null;
//        List<WorkflowInboxTaskVO> requestedList = null;
//
//        // 조건이 없을 경우 session에서 사용자정보 조회
//        if( StringUtils.isEmpty( userId ) ){
//            users = new Users( userSession.getUserBizObid() );
//            requestedList = users.retrieveRequestedList( searchInfo );
//        }
//        else{
//            requestedList = Users.retrieveRequestedList( searchInfo, userId );
//        }
//
//        int totalRow =0;
//        if ( requestedList instanceof OmcPagingList ) {
//            OmcPagingList<WorkflowInboxTaskVO> pagingList = (OmcPagingList<WorkflowInboxTaskVO>)requestedList;
//            totalRow =  pagingList.getTotalCount();
//        }
//
//        rm.setData( "totRecCount", totalRow );
//        rm.setData( "requestedList", requestedList );
//        rm.setModelMap( map );
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * 로그인 한 사용자의 승인완료목록 조회
//     * @param searchInfo
//     * @param userId
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/retrieveApprovedList.do" )
//    public String retrieveApprovedList( @SCRequestDataset( "searchInfo" ) WorkflowInboxTaskSearchVO searchInfo, @SCRequestDataset( "userId" ) String userId, ModelMap map) {
//        ResponseMapper rm = new ResponseMapper();
//        Users users = null;
//        List<WorkflowInboxTaskVO> approvedList = null;
//
//        // 조건이 없을 경우 session에서 사용자정보 조회
//        if( StringUtils.isEmpty( userId ) ){
//            users = new Users( userSession.getUserBizObid() );
//            approvedList = users.retrieveApprovedList( searchInfo );
//        }
//        else{
//            approvedList = Users.retrieveApprovedList( searchInfo, userId );
//        }
//
//        int totalRow =0;
//        if ( approvedList instanceof OmcPagingList ) {
//            OmcPagingList<WorkflowInboxTaskVO> pagingList = (OmcPagingList<WorkflowInboxTaskVO>)approvedList;
//            totalRow =  pagingList.getTotalCount();
//        }
//
//        rm.setData( "totRecCount", totalRow );
//        rm.setData( "approvedList", approvedList );
//        rm.setModelMap( map );
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    @RequestMapping("/workflow/retrieveChangeWorkList.do")
//    public String retrieveChangeWorkList(ModelMap map){
//        StringBuffer sql = new StringBuffer();
//
//        sql.append("FN_GET_CHGITEM_COUNT(@this.[obid])");
//        sql.append("+FN_GET_RELATED_PART_NO(@this.[obid]) partNames");
//        sql.append("+FN_GET_RELATED_DOC_NO(@this.[obid]) docNames");
//        sql.append("+SortBy@this.[created] desc ");
//
//        String selectPattern = sql.toString();
//
//        sql.setLength(0);   // string buffer 초기화
//        sql.append(ApplicationSchemaConstants.BIZCLASS_RELEASE);
//        sql.append(",");
//        sql.append(ApplicationSchemaConstants.BIZCLASS_ECO);
//
//        String className = sql.toString();
//        /*
//        List<ObjectRootVO> findObjectList = ObjectRoot.findObjects(className, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
//                GlobalConstants.FLAG_TYPE_ALL, ApplicationSchemaConstants.STATE_ECO_WORKING, userSession.getUserId(), GlobalConstants.FLAG_TYPE_ALL,
//                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, selectPattern,
//                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false, 0);
//       */
//        List<ObjectRootVO> selectObjectList = ObjectRoot.searchObjects(className, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
//                GlobalConstants.FLAG_TYPE_ALL, ApplicationSchemaConstants.STATE_ECO_WORKING, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL,
//                userSession.getUserId(), GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, selectPattern,
//                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, false, 0);
//
//        ResponseMapper rm = new ResponseMapper();
//        rm.setData("changeWorkList",selectObjectList);
//        rm.setModelMap(map);
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//
//    /**
//     * Approval Line 등록
//     * @param approvalLineVO
//     * @param approvalLineStateList
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/createApprovalLine.do" )
//    public String createApprovalLine(
//            @SCRequestDataset( "approvalLineVO" ) ApprovalLineVO approvalLineVO,
//            @SCRequestDataset( "approvalLineStateList" ) List<ApprovalLineStateVO> approvalLineStateList,
//            ModelMap map) {
//        int statusCode = ResponseConstants.STATUS_SUCCESS;
//        String message = "";
//
//        try {
//            workflowService.txnCreateApprovalLine( userSession.getUserBizObid(), approvalLineVO, approvalLineStateList );
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
//            message = "Error occurred.";
//        }
//        finally{
//            ResponseMapper rm = new ResponseMapper();
//            rm.setStatusCode( statusCode );
//            rm.setMessage( message );
//            rm.setModelMap( map );
//        }
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * Approval Line multiple 수정
//     * @param approvalLineVO
//     * @param approvalLineObid
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/updateApprovalLineList.do" )
//    public String updateApprovalLineList(
//            @SCRequestDataset( "approvalLineList" ) List<ApprovalLineVO> approvalLineList,
//            ModelMap map) {
//        int statusCode = ResponseConstants.STATUS_SUCCESS;
//        String message = "";
//
//        try {
//            workflowService.txnUpdateApprovalLineList( userSession.getUserBizObid(), approvalLineList );
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
//            message = "Error occurred.";
//        }
//        finally{
//            ResponseMapper rm = new ResponseMapper();
//            rm.setStatusCode( statusCode );
//            rm.setMessage( message );
//            rm.setModelMap( map );
//        }
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * Approval Line 수정
//     * @param approvalLineVO
//     * @param approvalLineObid
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/updateApprovalLine.do" )
//    public String updateApprovalLine(
//            @SCRequestDataset( "approvalLineObid" ) String approvalLineObid,
//            @SCRequestDataset( "approvalLineVO" ) ApprovalLineVO approvalLineVO,
//            ModelMap map) {
//        int statusCode = ResponseConstants.STATUS_SUCCESS;
//        String message = "";
//
//        try {
//            workflowService.txnUpdateApprovalLine( userSession.getUserBizObid(), approvalLineObid, approvalLineVO );
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
//            message = "Error occurred.";
//        }
//        finally{
//            ResponseMapper rm = new ResponseMapper();
//            rm.setStatusCode( statusCode );
//            rm.setMessage( message );
//            rm.setModelMap( map );
//        }
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * Approval Line 수정
//     * @param approvalLineVO
//     * @param approvalLineObid
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/updateApprovalLineStateList.do" )
//    public String updateApprovalLineStateList(
//            @SCRequestDataset( "approvalLineObid" ) String approvalLineObid,
//            @SCRequestDataset( "createList" ) List<ApprovalLineStateVO> createList,
//            @SCRequestDataset( "updateList" ) List<ApprovalLineStateVO> updateList,
//            @SCRequestDataset( "deleteList" ) List<ApprovalLineStateVO> deleteList,
//            @SCRequestDataset( "isEssential" ) String isEssential,
//            ModelMap map) {
//        int statusCode = ResponseConstants.STATUS_SUCCESS;
//        String message = "";
//
//        try {
//
//            // PDEV Setting : Approval Line Menu Access 추가
//            if( "true".equals(isEssential) ){
//                if( !uiService.checkMenuAccess(PlmConstants.cmdSaveEssentialApproverForRequest, "") ){
//                    throw new ApplicationException("plm.common.error.menu.access");
//                }
//            }
//            // My approval line 수정 시
//            else{
//                List<ApprovalLineVO> approvalLineVOList = Users.retrieveApprovalLineList(userSession.getUserId(), null);
//                if( approvalLineVOList != null && approvalLineVOList.size() > 0 ){
//                    boolean isExist = false;
//                    for( int inx = 0; inx < approvalLineVOList.size(); inx++ ){
//                        if( approvalLineVOList.get(inx).getObid().equals(approvalLineObid) ){
//                            isExist = true;
//                            break;
//                        }
//                    }
//                    if( !isExist ){
//                        throw new ApplicationException( "plm.common.error.menu.access" );
//                    }
//                }
//                else{
//                    throw new ApplicationException( "plm.common.error.menu.access" );
//                }
//            }
//
//            workflowService.txnUpdateApprovalLineStateList(
//                    approvalLineObid,
//                    createList,
//                    updateList,
//                    deleteList,
//                    Boolean.valueOf(isEssential),
//                    ApplicationSchemaConstants.RELCLASS_APPROVALLINE2STATE
//            );
//        }
//        catch( ApplicationException ge ){
//            throw ge;
//        }
//        catch( Exception e ){
//            e.printStackTrace();
//            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
//            message = "Error occurred.";
//        }
//        finally{
//            ResponseMapper rm = new ResponseMapper();
//            rm.setStatusCode( statusCode );
//            rm.setMessage( message );
//            rm.setModelMap( map );
//        }
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * Approval Line 수정
//     * @param approvalLineVO
//     * @param approvalLineObid
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/updateEssentialApproverStateList.do" )
//    public String updateEssentialApproverStateList(
//            @SCRequestDataset( "essentialApproverObid" ) String essentialApproverObid,
//            @SCRequestDataset( "createList" ) List<ApprovalLineStateVO> createList,
//            @SCRequestDataset( "updateList" ) List<ApprovalLineStateVO> updateList,
//            @SCRequestDataset( "deleteList" ) List<ApprovalLineStateVO> deleteList,
//            @SCRequestDataset( "isEssential" ) String isEssential,
//            ModelMap map) {
//        // Menu(Button) Access check 추가
//        if( !uiService.checkMenuAccess( PlmConstants.cmdSaveEssentialApprover, "" ) ){
//            throw new ApplicationException( "plm.common.error.menu.access" );
//        }
//
//        int statusCode = ResponseConstants.STATUS_SUCCESS;
//        String message = "";
//
//        try {
//            workflowService.txnUpdateApprovalLineStateList(
//                    essentialApproverObid,
//                    createList,
//                    updateList,
//                    deleteList,
//                    Boolean.valueOf(isEssential),
//                    ApplicationSchemaConstants.RELCLASS_ESSENTIALAPPROVER2STATE
//            );
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
//            message = "Error occurred.";
//        }
//        finally{
//            ResponseMapper rm = new ResponseMapper();
//            rm.setStatusCode( statusCode );
//            rm.setMessage( message );
//            rm.setModelMap( map );
//        }
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * My Approval Line 정보 조회
//     * @param approvalLineObid
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/retrieveApprovalLine.do" )
//    public String retrieveApprovalLine(String approvalLineObid, ModelMap map) {
//        ResponseMapper rm = new ResponseMapper();
//
//        ApprovalLine resultDom = new ApprovalLine(approvalLineObid);
//        ApprovalLineVO approvalLineVO = resultDom.getVo();
//        if( !StringUtils.isEmpty(approvalLineVO.getAppliedType()) ){
//            ///approvalLineVO.getOutData().put( "appliedTypeDesc", ClassInfoUtil.retrieveClassDisplayName(approvalLineVO.getAppliedType()) );
//        }
//
//        rm.setData( "approvalLineInfo", approvalLineVO );
//        rm.setData( "approvalLineStateList",
//                resultDom.getRelatedObjects(
//                        ApplicationSchemaConstants.RELCLASS_APPROVALLINE2STATE,
//                        GlobalConstants.FLAG_TYPE_ALL,
//                        GlobalConstants.FLAG_TYPE_TO,
//                        "SortBy@this.[descriptions]",
//                        null,
//                        null,
//                        false,
//                        false,
//                        0,
//                        1
//                )
//        );
//        rm.setModelMap( map );
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//

    /**
     * My Approval Line 목록 조회 (사용자기준)
     * @param userId
     * @param map
     * @return
     */
    @RequestMapping( value="/workflow/retrieveApprovalLineList.do" )
    public String retrieveApprovalLineList( String userId, String policyName, ModelMap map ) {
        ResponseMapper rm = new ResponseMapper();
        Users users = null;
        List<ApprovalLineVO> approvalLineList = null;

        // 조건이 없을 경우 session에서 사용자정보 조회
        if( StringUtils.isEmpty( userId ) ){
            users = new Users( userSession.getUserBizObid() );
            approvalLineList = users.retrieveApprovalLineList( policyName );
        }
        else{
            approvalLineList = Users.retrieveApprovalLineList( userId, policyName );
        }

        rm.setData( "approvalLineList", approvalLineList );
        rm.setModelMap( map );

        return "PlmConstants.AJAX_VIEW";
    }


//    /**
//     * My Approval Line State 목록 조회
//     * @param approvalLineObid
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/retrieveApprovalLineStateList.do" )
//    public String retrieveApprovalLineStateList( String approvalLineObid, ModelMap map ) {
//        ResponseMapper rm = new ResponseMapper();
//
//        ApprovalLine resultDom = new ApprovalLine( approvalLineObid );
//        rm.setData( "stateList", workflowService.retrieveApprovalStateList( resultDom.getVo().getAppliedPolicy() ) );
//        rm.setData( "distributionState", workflowService.retrieveDistributionState( resultDom.getVo().getAppliedPolicy() ) );
//        rm.setData( "approvalLineStateList", resultDom.retrieveApprovalLineStateList() );
//        rm.setModelMap( map );
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * Essential Approver State 목록 조회
//     * @param approvalLineObid
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/retrieveEssentialApproverStateList.do" )
//    public String retrieveEssentialApproverStateList( String essentialApproverObid, ModelMap map ) {
//        ResponseMapper rm = new ResponseMapper();
//
//        EssentialApprover resultDom = new EssentialApprover( essentialApproverObid );
//        rm.setData( "stateList", workflowService.retrieveApprovalStateList( ECO.lifeCycle_ECO() ) );
//        rm.setData( "distributionState", workflowService.retrieveDistributionState( ECO.lifeCycle_ECO() ) );
//        rm.setData( "approvalLineStateList", resultDom.retrieveEssentialApproverStateList() );
//        rm.setModelMap( map );
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * Essential Approver Master 검색
//     * @param approvalLineObid
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/retrieveEssentialApproverMasterList.do" )
//    public String retrieveEssentialApproverMasterList( EssentialApproverVO searchInfo, ModelMap map ) {
//        ResponseMapper rm = new ResponseMapper();
//
//        rm.setData( "masterList", EssentialApprover.retrieveEssentialApproverMasterList( searchInfo ) );
//        rm.setModelMap( map );
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * Change participant User
//     * @param approvalLineObid
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/changeParticipantUser.do" )
//    public String changeParticipantUser(
//            @SCRequestDataset( "beforeUserObid" ) String beforeUserObid,
//            @SCRequestDataset( "afterUserObid" ) String afterUserObid,
//            @SCRequestDataset( "targetEssentialApproverObid" ) String targetEssentialApproverObid,
//            ModelMap map ) {
//        // Menu(Button) Access check 추가
//        if( !uiService.checkMenuAccess( PlmConstants.cmdChangeParticipantUser, "" ) ){
//            throw new ApplicationException( "plm.common.error.menu.access" );
//        }
//
//        ResponseMapper rm = new ResponseMapper();
//        int statusCode = ResponseConstants.STATUS_SUCCESS;
//        String message = "";
//        List<ApprovalLineStateVO> failList = null;
//        StringBuffer resultMessage = new StringBuffer();
//
//        try {
//            failList = workflowService.txnChangeParticipantUser( beforeUserObid, afterUserObid, targetEssentialApproverObid );
//            if( failList != null && failList.size() > 0 ){
//                resultMessage.append( "But duplicate error occurred.\nPlease refer to below information.\n" );
//                resultMessage.append( "[Key(name) : Title : State]\n" );
//                for( int inx = 0; inx < failList.size(); inx++ ){
//                    resultMessage.append( "-" + failList.get(inx).getNames() + " : " + failList.get(inx).getTitles() + " : " + failList.get(inx).getRouteState() );
//                    if( inx < failList.size() - 1 ){
//                        resultMessage.append( "\n" );
//                    }
//                }
//            }
//            rm.setData( "resultMessage", resultMessage.toString() );
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
//            message = "Error occurred.";
//        }
//        finally{
//            rm.setStatusCode( statusCode );
//            rm.setMessage( message );
//            rm.setModelMap( map );
//        }
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * Default Approval Line 설정
//     * @param approvalLineObid
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/setDefaultApprovalLine.do" )
//    public String setDefaultApprovalLine( String approvalLineObid, ModelMap map ){
//        ResponseMapper rm = new ResponseMapper();
//        int statusCode = ResponseConstants.STATUS_SUCCESS;
//        String message = "";
//
//        try {
//            workflowService.txnUpdateDefaultApprovalLine( userSession.getUserBizObid(), approvalLineObid );
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
//            message = "Error occurred.";
//        }
//        finally{
//            rm.setStatusCode( statusCode );
//            rm.setMessage( message );
//            rm.setModelMap( map );
//        }
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * Approval Line 삭제
//     * @param approvalLineObid
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/deleteApprovalLine.do" )
//    public String deleteApprovalLine( String approvalLineObid, ModelMap map ){
//        // 권한체크
//        List<ApprovalLineVO> approvalLineVOList = Users.retrieveApprovalLineList(userSession.getUserId(), null);
//        if( approvalLineVOList != null && approvalLineVOList.size() > 0 ){
//            boolean isExist = false;
//            for( int inx = 0; inx < approvalLineVOList.size(); inx++ ){
//                if( approvalLineVOList.get(inx).getObid().equals(approvalLineObid) ){
//                    isExist = true;
//                    break;
//                }
//            }
//            if( !isExist ){
//                throw new ApplicationException( "plm.common.error.menu.access" );
//            }
//        }
//        else{
//            throw new ApplicationException( "plm.common.error.menu.access" );
//        }
//
//        ResponseMapper rm = new ResponseMapper();
//        int statusCode = ResponseConstants.STATUS_SUCCESS;
//        String message = "";
//
//        try {
//            workflowService.txnDeleteApprovalLine( approvalLineObid );
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
//            message = "Error occurred.";
//        }
//        finally{
//            rm.setStatusCode( statusCode );
//            rm.setMessage( message );
//            rm.setModelMap( map );
//        }
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
    /**
     * 사용자 수동 배포
     * @param assigneeList
     * @param targetObjectList
     * @param map
     * @return
     */
    @RequestMapping( value="/workflow/distributeItems.do" )
    public String distributeItemList(
            @SCRequestDataset( "actionType" ) String actionType,
            @SCRequestDataset( "assigneeList" ) List<UsersVO> assigneeList,
            @SCRequestDataset( "targetObjectList" ) List<BusinessObjectRootVO> targetObjectList,
            @SCRequestDataset( "comments" ) String comments,
            ModelMap map ){
        // 권한체크
        // Approval List에서 호출된 경우
        if( "workflow".equals(actionType) ){
            List<WorkflowInboxTaskVO> inboxTaskVOList = Users.retrieveApprovalList(userSession.getUserId());
            if( inboxTaskVOList != null && inboxTaskVOList.size() > 0 ){
                boolean isExist = false;
                for( int inx = 0; inx < targetObjectList.size(); inx++ ){
                    isExist = false;
                    for( int jnx = 0; jnx < inboxTaskVOList.size(); jnx++ ){
                        if( targetObjectList.get(inx).getObid().equals(inboxTaskVOList.get(jnx).getOutData().get("targetObid").toString()) ){
                            isExist = true;
                            break;
                        }
                    }
                    if( !isExist ){
                        throw new ApplicationException( "plm.common.error.menu.access" );
                    }
                }
            }
            else{
                throw new ApplicationException( "plm.common.error.menu.access" );
            }
        }
        // Detail 화면에서 호출된 경우
        else if( "detail".equals(actionType) ){
            if( !uiService.checkMenuAccess(CommandConstants.cmdDistributeForLifeCycle, targetObjectList.get(0).getObid()) ){
                throw new ApplicationException( "plm.common.error.menu.access" );
            }
        }
        else{
            throw new ApplicationException( "plm.common.error.menu.access" );
        }

        ResponseMapper rm = new ResponseMapper();
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        ApprovalVO approvalVO = null;
        List<ApprovalVO> approvalVOList = null;
        BusinessObjectRoot businessObject = null;
        String distributionState = null;

        try {

            if( assigneeList != null && assigneeList.size() > 0 ){
                // 배포대상 사용자 정보 설정
                approvalVOList = new ArrayList<ApprovalVO>();
                for( int inx = 0; inx < assigneeList.size(); inx++ ){
                    approvalVO = new ApprovalVO();
                    approvalVO.setAssigneeObid( assigneeList.get(inx).getObid() );
                    approvalVO.setParallelNodeProcessionRule( "All" );
                    approvalVO.setStep( 1 );
                    approvalVO.setRouteInstructions( WorkflowConstants.INSTRUCTION_TYPE_STANDARD );
                    approvalVO.setRecordMode( "C" );
                    approvalVO.setComments( comments );     // ymwon comment 관련 처리 필요 (저장필드 정해지면 distributionHistoryViewer.js 파일의 grid 목록에 적용 필요)
                    approvalVO.setAssigneeNames( assigneeList.get(inx).getNames() );
                    approvalVOList.add( approvalVO );
                }

                // 배포대상 Object 정보 설정 및 배포 수행
                if( targetObjectList != null && targetObjectList.size() > 0 ){
                    for( int inx = 0; inx < targetObjectList.size(); inx++ ){
                        businessObject = new BusinessObjectRoot( targetObjectList.get(inx).getObid() );
                        if( businessObject != null ){
                            // 대상 Object의 배포상태 정보 설정
                            distributionState = workflowService.retrieveDistributionState( businessObject.getVo().getLifeCycle() );
                            for( int jnx = 0; jnx < approvalVOList.size(); jnx++ ){
                                approvalVOList.get(jnx).setState( distributionState );
                            }

                            // 배포 진행
                            workflowService.txnAddDistribution( businessObject, approvalVOList );
                        }
                    }
                }
            }

        }
        catch(ApplicationException ex){
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = ex.getMessage();
        }
        catch(FoundationException ex){
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = ex.getMessage();
        }
        catch(Exception ex){
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = "Error occurred.";
        }
        finally{
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setModelMap( map );
        }

        return "PlmConstants.AJAX_VIEW";
    }

    /**
     * 사용자 수동 배포(Workflow 무관)
     * @param actionType
     * @param assigneeList
     * @param targetObjectList
     * @param comments
     * @param map
     * @return
     */
    @RequestMapping( value="/workflow/manualDistributeItemList.do" )
    public String manualDistributeItemList(
            @SCRequestDataset( "actionType" ) String actionType,
            @SCRequestDataset( "assigneeList" ) List<UsersVO> assigneeList,
            @SCRequestDataset( "targetObjectList" ) List<BusinessObjectRootVO> targetObjectList,
            @SCRequestDataset( "comments" ) String comments,
            ModelMap map ){
        
        ResponseMapper rm = new ResponseMapper();
        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";
        
        try {
            if ( "workflow".equals(actionType) ) {
                if ( assigneeList.size() == 0 ) {
                    statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                    message = "There is(are) no one to distribute.";
                }
                if ( targetObjectList.size() == 0 ) {
                    statusCode = ResponseConstants.STATUS_VALIDATION_ERROR;
                    message = "There is(are) no data to distribute.";
                }
                
                if ( "".equals(message) ) {
                    workflowService.txnManualDistribute(assigneeList, targetObjectList, comments);
                }
            } else if ( "detail".equals(actionType) ) {
                if ( !uiService.checkMenuAccess(CommandConstants.cmdDistributeForLifeCycle, targetObjectList.get(0).getObid()) ) {
                    throw new ApplicationException( "plm.common.error.menu.access" );
                }
            } else {
                throw new ApplicationException( "plm.common.error.menu.access" );
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = ex.getMessage();
            if ( "".equals(message) ) {
                message = "Error occurred.";
            }
        } finally {
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setModelMap( map );
        }
        return "PlmConstants.AJAX_VIEW";
    }
    /**
     * 배포이력 조회
     * @param targetObid
     * @param map
     * @return
     * @throws Exception 
     * @throws ParseException
     */
    @RequestMapping( value="/workflow/retrieveDistributionHistoryList.do" )
    public String retrieveDistributionHistoryList( String targetObid, ModelMap map ) throws Exception {
        ResponseMapper rm = new ResponseMapper();
        rm.setData( "distributionHistoryList", workflowService.retrieveDistributionHistoryList(targetObid) );
        rm.setModelMap( map );

        return "PlmConstants.AJAX_VIEW";
    }

    /**
     * obid에 해당하는 detail 페이지 url 정보 조회
     * @param obid
     * @param map
     * @return
     */
    @RequestMapping( value="/workflow/retrieveDetailInfo.do" )
    public String retrieveDetailInfo( String obid, ModelMap map ) {
        BusinessObjectRoot bo = new BusinessObjectRoot(obid);
        ResponseMapper rm = new ResponseMapper();
        rm.setData( "detailVO", bo == null ? null : bo.getVo() );
        rm.setData( "workflowDetailUrl", BusinessObjectRoot.getWorkflowUrl(obid) );

//        해당 Object의 BusinessUnitCode 조회 및 설정
//        String siteForData = BusinessUnitUtil.getSiteForData( bo );
//        String businessUnitCodeForData = BusinessUnitUtil.getBusinessUnit( siteForData );
//        rm.setData( "businessUnitCodeForData", businessUnitCodeForData );
//        rm.setData( "divisionUnitCodeForData", CommonUtil.getDivisionFromPlant( siteForData ) );
//        rm.setData( "isMCData", PlmConstants.BUSINESS_UNIT_MC.equals(businessUnitCodeForData) );
//        rm.setData( "isVCData", PlmConstants.BUSINESS_UNIT_VC.equals(businessUnitCodeForData) );

        rm.setModelMap( map );

        return "PlmConstants.AJAX_VIEW";
    }

    /**
     * policyName에 해당하는 State List 반환
     * @param policyName
     * @param map
     * @return
     */
    @RequestMapping( value="/workflow/retrieveStateListByPolicy.do" )
    public String retrieveStateListByPolicy( String policyName, ModelMap map ) {
        ResponseMapper rm = new ResponseMapper();
        List<String> stateList = lifeCycleService.getLifeCycleStateStringListByName(policyName);
        rm.setData( "stateList", stateList );
        rm.setModelMap( map );

        return "PlmConstants.AJAX_VIEW";
    }

    /**
     * 필수결재자 Validation
     * @param targetObid
     * @param copyCategoryObid
     * @param map
     * @return
     */
//    @RequestMapping( value="/workflow/checkExistEssentialApprover.do" )
//    public String checkExistEssentialApprover(
//            @SCRequestDataset( "approvalUserList" ) List<ApprovalVO> approvalUserList,
//            @SCRequestDataset( "className" ) String className,
//            @SCRequestDataset( "site" ) String site,
//            @SCRequestDataset( "policyName" ) String policyName,
//            ModelMap map ){
//        ResponseMapper rm = new ResponseMapper();
//        if( StringUtils.isEmpty( site ) ){
//            site = userSession.getPreferredSite();
//        }
//        rm.setData( "invalidStateList", workflowService.checkExistEssentialApprover( approvalUserList, className, site, policyName, false ) );
//        rm.setModelMap( map );
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * Mass approval (Approve / Reject)
//     * @param targetObjectList
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/massApproval.do" )
//    public String massApproval( @SCRequestDataset( "targetObjectList" ) List<ApprovalVO> targetObjectList,
//            @SCRequestDataset( "searchId" ) String searchId,
//            ModelMap map ){
//        // 권한체크
//        boolean isValid = false;
//        String userId = searchId.isEmpty() ? userSession.getUserId() : searchId;
//        List<WorkflowInboxTaskVO> inboxTaskVOList = Users.retrieveApprovalList(userId);
//        if( inboxTaskVOList != null && inboxTaskVOList.size() > 0 ){
//            for( int inx = 0; inx < targetObjectList.size(); inx++ ){
//                isValid = false;
//                for( int jnx = 0; jnx < inboxTaskVOList.size(); jnx++ ){
//                    if( inboxTaskVOList.get(jnx).getObid().equals(targetObjectList.get(inx).getWorkflowInboxTaskObid()) ){
//                        isValid = true;
//                        break;
//                    }
//                }
//                if( !isValid ){
//                    throw new ApplicationException( "plm.common.approval.error", new Object[] { targetObjectList.get(inx).getWorkflowInboxTaskObid() } );
//                }
//            }
//        }
//        else{
//            throw new ApplicationException( "plm.common.approval.error", new Object[] { "You don't have item to approval or reject." } );
//        }
//
//        int statusCode = ResponseConstants.STATUS_SUCCESS;
//        List<ApprovalVO> returnMsg = new ArrayList<ApprovalVO>();
//        String message = "";
//
//        try {
//            returnMsg = doMassApprove( targetObjectList );
//        }
//        catch( Exception ex ){
//            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
//            message = ex.getMessage();
//        }
//        finally{
//            ResponseMapper rm = new ResponseMapper();
//            rm.setStatusCode( statusCode );
//            rm.setMessage( message );
//            rm.setData( "returnMsg", returnMsg );
//            rm.setModelMap( map );
//        }
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
    /**
     * Mass Acknowledge
     * @param targetObjectList
     * @param map
     * @return
     */
    @RequestMapping( value="/workflow/massAcknowledge.do" )
    public String massAcknowledge( @SCRequestDataset( "targetObjectList" ) List<ApprovalVO> targetObjectList,
            @SCRequestDataset( "searchId" ) String searchId,
            ModelMap map ){
        // 권한체크
        boolean isValid = false;
        String userId = searchId.isEmpty() ? userSession.getUserId() : searchId;
        List<WorkflowInboxTaskVO> inboxTaskVOList = Users.retrieveDistributionList(userId);
        if( inboxTaskVOList != null && inboxTaskVOList.size() > 0 ){
            for( int inx = 0; inx < targetObjectList.size(); inx++ ){
                isValid = false;
                for( int jnx = 0; jnx < inboxTaskVOList.size(); jnx++ ){
                    if( inboxTaskVOList.get(jnx).getObid().equals(targetObjectList.get(inx).getWorkflowInboxTaskObid()) ){
                        isValid = true;
                        break;
                    }
                }
                if( !isValid ){
                    throw new ApplicationException( "plm.common.acknowledge.error", new Object[] { targetObjectList.get(inx).getWorkflowInboxTaskObid() } );
                }
            }
        }
        else{
            throw new ApplicationException( "plm.common.acknowledge.error", new Object[] { "You don't have item to acknowledge." } );
        }

        int statusCode = ResponseConstants.STATUS_SUCCESS;
        String message = "";

        try {
            workflowService.txnDoMassAcknowledge( targetObjectList );
        }
        catch( Exception ex ){
            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
            message = ex.getMessage();
        }
        finally{
            ResponseMapper rm = new ResponseMapper();
            rm.setStatusCode( statusCode );
            rm.setMessage( message );
            rm.setModelMap( map );
        }

        return "PlmConstants.AJAX_VIEW";
    }
//
//    /**
//     * Class Display Name 조회
//     * @param targetClassName
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/retrieveClassInfo.do" )
//    public String retrieveClassInfo( String targetClassName, ModelMap map ){
//        ResponseMapper rm = new ResponseMapper();
//        if( !StringUtils.isEmpty(targetClassName) ){
//            //rm.setData( "classDisplayName", ClassInfoUtil.retrieveClassDisplayName(targetClassName) );
//        }
//        rm.setModelMap( map );
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * ApprovalLine 정보 Excel Import
//     * @param files
//     * @param uploadId
//     * @param uploadType
//     * @param model
//     * @return
//     */
//    @RequestMapping("/workflow/excelImportApprovalLine.do")
//    public String excelImportApprovalLine(@RequestParam MultipartFile files,
//            @RequestParam String uploadId,
//            @RequestParam String uploadType,
//            Model model){
//        // Menu(Button) Access check 추가
//        if( !uiService.checkMenuAccess(PlmConstants.cmdAdminExcelUpload, "") ){
//            throw new ApplicationException( "plm.common.error.menu.access" );
//        }
//
//        long startTime = System.currentTimeMillis();
//        List<ApprovalLineExcelImportVO> dataList = new ArrayList<ApprovalLineExcelImportVO>();
//        HashMap<String, Object> detailData = new HashMap<String, Object>();
//        HashMap<String, Object> result = null;
//        int totalCount = 0;
//
//        try{
//            if( !files.isEmpty() ){
//                boolean isForRequest = "R".equals(uploadType) ? true : false;
//                InputStream in = files.getInputStream();
//                HSSFWorkbook workbook = new HSSFWorkbook(in);
//                HSSFSheet sheet = workbook.getSheetAt(0);
//                ApprovalLineExcelImportVO dataRow = null;
//                HSSFRow row = null;
//                if( isForRequest ){
//                    for( int inx = 1; inx <= sheet.getLastRowNum(); inx++ ){
//                        row = sheet.getRow(inx);
//                        dataRow = new ApprovalLineExcelImportVO();
//                        dataRow.setManageType( PlmConstants.APPROVAL_LINE_MANAGE_TYPE_REQUEST );
//                        dataRow.setEssential( true );
//                        dataRow.setAppliedPolicy( CommonUtil.getRequestPolicyNameForMigration( CommonUtil.getExcelCellValue(row.getCell( 1 )) ) );
//                        dataRow.setPlantName( CommonUtil.getExcelCellValue(row.getCell( 2 )) );
//                        dataRow.setRouteState( CommonUtil.getExcelCellValue(row.getCell( 3 )) );
//                        dataRow.setAssigneeId( CommonUtil.getExcelCellValue(row.getCell( 4 )) );
//                        dataRow.setStep( CommonUtil.getExcelCellValue(row.getCell( 5 )) );
//                        dataRow.setRequiredAction( CommonUtil.getExcelCellValue(row.getCell( 6 )) );
//                        dataRow.setDefault( false );
//
//                        dataList.add( dataRow );
//                    }
//
//                    totalCount = dataList.size() - 1;
//                }
//                else{
//                    for( int inx = 1; inx <= sheet.getLastRowNum(); inx++ ){
//                        row = sheet.getRow(inx);
//                        dataRow = new ApprovalLineExcelImportVO();
//                        dataRow.setUserId( CommonUtil.getExcelCellValue(row.getCell( 1 )) );
//                        dataRow.setApprovalLineId( CommonUtil.getExcelCellValue(row.getCell( 2 )) );
//                        dataRow.setApprovalLineName( CommonUtil.getExcelCellValue(row.getCell( 3 )) );
//                        dataRow.setAppliedPolicy( CommonUtil.getPolicyNameForMigration( CommonUtil.getExcelCellValue(row.getCell( 4 )), BusinessUnitUtil.getBusinessUnitSystemProperty()) );
//                        dataRow.setAppliedType( CommonUtil.getClassNameForMigration( CommonUtil.getExcelCellValue(row.getCell( 5 )), BusinessUnitUtil.getBusinessUnitSystemProperty()) );
//                        dataRow.setDefault( "Yes".equals(CommonUtil.getExcelCellValue(row.getCell( 6 ))) ? true : false );
//                        dataRow.setAssigneeId( CommonUtil.getExcelCellValue(row.getCell( 9 )) );
//                        dataRow.setRouteState( CommonUtil.getExcelCellValue(row.getCell( 10 )) );
//                        dataRow.setStep( CommonUtil.getExcelCellValue(row.getCell( 11 )) );
//                        dataRow.setSequences( ((Double)row.getCell( 12 ).getNumericCellValue()).intValue() );
//                        dataRow.setRequiredAction( CommonUtil.getExcelCellValue(row.getCell( 13 )) );
//
//                        dataList.add( dataRow );
//                    }
//
//                    totalCount = dataList.size();
//                }
//
//                result = workflowService.excelImportApprovalLine( dataList, isForRequest );
//            }
//
//            result.put( "totalCount", totalCount );
//
//            // 소요시간 표시
//            long endTime = System.currentTimeMillis();
//            String processingTime = Math.round((endTime - startTime) / 1000.0f) + " seconds";
//            result.put( "message", result.get( "message" ) + "\nProcessing Time : " + processingTime);
//        }
//        catch( Exception e ){
//            e.printStackTrace();
//            result = new HashMap<String, Object>();
//            result.put( "message", e.getClass() );
//        }
//        finally{
//            detailData.put( GlobalConstants.D_UPLOAD_ID, uploadId ); // 지우지 말 것.
//            detailData.put( "dataList", dataList ); // 지우지 말 것.
//            detailData.put( "result", result ); // 지우지 말 것.
//
//            HashMap<String, Object> resultData = new HashMap<String, Object>();
//            resultData.put( GlobalConstants.M_STATUS_CODE, ResponseConstants.STATUS_SUCCESS );
//            resultData.put( GlobalConstants.M_MESSAGE, "" );
//            resultData.put( GlobalConstants.M_DATA, detailData );
//
//            JSONObject js = JSONObject.fromObject( resultData );
//            model.addAttribute( "result", js );
//        }
//
//        return "common/file/uploadResponse";
//    }

    /**
     * EP용 결재페이지로 이동
     * @return
     */
    @RequestMapping("/epApprove.do")
    public String epApprove( String obid, String isApproved, ModelMap map ){
        map.addAttribute( "obid", obid );
        map.addAttribute( "isApproved", isApproved );
        return "workflow/epApprove";
    }

    /**
     * EP용 결재완료목록 페이지로 이동
     * @return
     */
    @RequestMapping("/epApprovedList.do")
    public String epApprovedList( ModelMap map ){
        return "workflow/epApprovedList";
    }

//    /**
//     * Input Task Obid 정보로 결재 Process 진행
//     * @param inputTaskObid
//     * @param approvalStatus
//     * @param comments
//     * @param model
//     * @return
//     */
//    @RequestMapping( value="/workflow/doApprovalInboxTask.do", method = RequestMethod.POST )
//    public String doApprovalInboxTask(
//            @SCRequestDataset( "inboxTaskObid" ) String inboxTaskObid,
//            @SCRequestDataset( "approvalStatus" ) String approvalStatus,
//            @SCRequestDataset( "comments" ) String comments,
//            ModelMap model) {
//
//        if(NullUtil.isNull(inboxTaskObid) || NullUtil.isNull(approvalStatus) || NullUtil.isNull(comments)){
//            throw new IllegalArgumentException();
//        }
//
//        workflowService.txnManualApprove(inboxTaskObid, approvalStatus, comments, true);
//
//        ResponseMapper rm = new ResponseMapper();
//        rm.setStatusCode(ResponseConstants.STATUS_SUCCESS);
//        rm.setMessage( "Action Completed!" );
//        rm.setModelMap(model);
//        return "PlmConstants.AJAX_VIEW";
//    }
//
    /**
     * Notification E-mail을 발송할 List 조회
     * @param businessObjectRootVO
     * @param model
     * @return
     */
    @RequestMapping( value="/workflow/emailNotificationList.do", method = RequestMethod.POST )
    public String emailNotificationList(@SCRequestDataset( "businessObjectRootVO" ) BusinessObjectRootVO businessObjectRootVO, ModelMap model) {
        if(NullUtil.isNull(businessObjectRootVO.getObid())){
            throw new ApplicationException("obid is null");
        }
        BusinessObjectRoot businessObjectRoot = DomUtil.toDom(businessObjectRootVO.getObid());
        List<WorkflowInboxTaskVO> emailNotificationList = workflowService.retrieveNotificationFromRoute(businessObjectRoot);
        ResponseMapper rm = new ResponseMapper();
        rm.setData("emailNotificationList", emailNotificationList);
        rm.setModelMap( model );
        return "PlmConstants.AJAX_VIEW";
    }
//
//
//    /**
//     * Awaiting Approval List 담당자에게 메일발송
//     * @param notificationVO
//     * @param emailNotificationList
//     * @param model
//     * @return
//     * @throws Exception
//     */
//    @SuppressWarnings("unchecked")
//    @RequestMapping( value="/workflow/emailNotification.do", method = RequestMethod.POST )
//    public String emailNotification(
//            @SCRequestDataset( "notificationVO" ) ApprovalVO notificationVO,
//            @SCRequestDataset( "emailNotificationList" ) List<ApprovalVO> emailNotificationList,
//            ModelMap model) throws Exception {
//
//        if(NullUtil.isNull(notificationVO.getObid())){
//            throw new ApplicationException("obid is null");
//        }
//
//        BusinessObjectRoot businessObjectRoot   = DomUtil.toDom(notificationVO.getObid());
//        ArrayList<Object> mdContents            = new ArrayList<Object>();
//        MailVO mailVO                           = new MailVO();
//        HashMap<String,Object> targetObjInfo    = mailSendService.getMailInfoByClass( businessObjectRoot.getVo() );
//        String subject                          = targetObjInfo.get("targetNo").toString() + " (Awaiting Approval) : " + targetObjInfo.get("targetTitle").toString();
//        UsersVO fromUserVO                      = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, userSession.getUserId(), false);
//        List<String> toEmailList                = new ArrayList<String>();
//        for(int i=0; i<emailNotificationList.size(); i++){
//            if(emailNotificationList.get(i).getMode() == null){
//                UsersVO toUserVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, emailNotificationList.get(i).getAssignee(), false);
//                toEmailList.add(toUserVO.getEmailAddress());
//            }
//        }
//
//        String[][] rejectInfoArr = {
//                { "taskMsg", "Contents", "", "3" }
//        };
//
//        HashMap<String,Object> approveValueInfo = new HashMap<String, Object>();
//        approveValueInfo.put("taskMsg", notificationVO.getComments());
//
//        // Message 설정
//        HashMap<String,Object> mRowData = new HashMap<String, Object>();
//        mRowData.put("type", "F");
//        mRowData.put("colsize", 4);
//        mRowData.put("title", "E-mail Contents");
//        mRowData.put("orderInfo", rejectInfoArr);
//        mRowData.put("resultData", approveValueInfo);
//        mdContents.add(mRowData);
//
//        // Common Properties 설정
//        HashMap<String,Object> dRowData = new HashMap<String, Object>();
//        dRowData.put("type", "F");
//        dRowData.put("colsize", 4);
//        dRowData.put("title", "Common Properties");
//        dRowData.put("orderInfo", targetObjInfo.get("titleInfoArr"));
//        dRowData.put("resultData", targetObjInfo.get("valueInfo"));
//        mdContents.add(dRowData);
//
//        // Form이 여러개인 경우 처리를 위한 코드 추가 (2015/10/27 youngmi.won)
//        List<HashMap<String,Object>> formList = (List<HashMap<String,Object>>)targetObjInfo.get( "formList" );
//        if( formList != null && formList.size() > 0 ){
//            HashMap<String,Object> mdRowData = null;
//            for( int inx = 0; inx < formList.size(); inx++ ){
//                mdRowData = new HashMap<String, Object>();
//                mdRowData.put( "type", "F" );
//                mdRowData.put( "colsize", 4 );
//                mdRowData.put( "title", formList.get(inx).get( "title" ) );
//                mdRowData.put( "orderInfo", formList.get(inx).get("titleInfoArr") );
//                mdRowData.put( "resultData", formList.get(inx).get("valueInfo") );
//                mdContents.add( mdRowData );
//            }
//        }
//
//        // Grid 데이터 설정
//        List<HashMap<String,Object>> gridList = (List<HashMap<String,Object>>)targetObjInfo.get("gridList");
//        if( gridList != null && gridList.size() > 0 ){
//            HashMap<String,Object> dgRowData = null;
//            for( int inx = 0; inx < gridList.size(); inx++ ){
//                String[][] keyInfo = (String[][])gridList.get(inx).get("gridKey");
//                dgRowData = new HashMap<String, Object>();
//                dgRowData.put("type", "G");
//                dgRowData.put("colsize", keyInfo.length);
//                dgRowData.put("title", gridList.get(inx).get("gridTitle"));
//                dgRowData.put("orderInfo", (String[][])gridList.get(inx).get("gridKey"));
//                dgRowData.put("gridHeader", (String[][][])gridList.get(inx).get("gridHeader"));
//                dgRowData.put("resultData", (List<HashMap<String,Object>>)gridList.get(inx).get("gridValueList"));
//                mdContents.add(dgRowData);
//            }
//        }
//
//        mailVO.setSubject( "[" + MailContentsUtil.getMailSubjectPrefix() + " : Approval] " + subject );   // 제목 설정
//        mailVO.setFromUserVO( fromUserVO );                                                         // 발신자 설정
//        mailVO.setToEmailList( toEmailList );                                                       // 수신자 설정
//        mailVO.setMailContents( MailContentsUtil.getHtmlGeneration(mdContents) );                   // 내용셋팅
//        mailService.sendMail(mailVO);                                                               // 메일발송
//
//        ResponseMapper rm = new ResponseMapper();
//        rm.setStatusCode(ResponseConstants.STATUS_SUCCESS);
//        rm.setMessage( "updated successfully." );
//        rm.setModelMap( model );
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * Migration된 Approval History 조회
//     * @param businessObjectRootVO
//     * @param model
//     * @return
//     */
//    @RequestMapping( value="/workflow/retrieveApprovalHistoryMig.do" )
//    public String retrieveApprovalHistoryMig( String obid, ModelMap map ) {
//        List<HashMap<String, Object>> resultList = workflowService.retrieveApprovalHistoryMig(obid);
//        ResponseMapper rm = new ResponseMapper();
//        rm.setData( "resultList",  resultList);
//        rm.setModelMap( map );
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * Pending Change 조회
//     * @param searchInfo
//     * @param userId
//     * @param map
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping( value="/workflow/retrievePendingChangeList.do" )
//    public String retrievePendingChangeList( @SCRequestDataset( "searchInfo" ) WorkflowInboxTaskSearchVO searchInfo, @SCRequestDataset( "userId" ) String userId, ModelMap map) throws Exception {
//        ResponseMapper rm = new ResponseMapper();
//        Users users = null;
//        List<WorkflowInboxTaskVO> pendingChange = null;
//
//        // 조건이 없을 경우 session에서 사용자정보 조회
//        if( StringUtils.isEmpty( userId ) ){
//            users = new Users( userSession.getUserBizObid() );
//            pendingChange = users.retrievePendingChangeList( searchInfo );
//        }
//        else{
//            pendingChange = Users.retrievePendingChangeList( searchInfo, userId );
//        }
//
//        int totalRow =0;
//        if ( pendingChange instanceof OmcPagingList ) {
//            OmcPagingList<WorkflowInboxTaskVO> pagingList = (OmcPagingList<WorkflowInboxTaskVO>)pendingChange;
//            for(int i=0; i<pagingList.size(); i++){
//                UsersVO usersVO = ObjectRoot.findObject( ApplicationSchemaConstants.BIZCLASS_USERS, pagingList.get(i).getTaskOwner(), true );
//                pagingList.get(i).setTaskOwner(usersVO.getDepartmentKor()); // Department(부서정보)
//            }
//            totalRow =  pagingList.getTotalCount();
//        }
//        rm.setData( "totRecCount", totalRow );
//        rm.setData( "pendingChange", pendingChange );
//        rm.setModelMap( map );
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    /**
//     * Mass Admin approval (Approve / Reject)
//     * @param targetObjectList
//     * @param map
//     * @return
//     */
//    @RequestMapping( value="/workflow/massAdminApproval.do" )
//    public String massAdminApproval( @SCRequestDataset( "targetObjectList" ) List<ApprovalVO> targetObjectList,
//            @SCRequestDataset( "searchId" ) String searchId,
//            ModelMap map ){
//
//        int statusCode = ResponseConstants.STATUS_SUCCESS;
//        List<ApprovalVO> returnMsg = new ArrayList<ApprovalVO>();
//        String message = "";
//
//        try {
//            returnMsg = doMassApprove( targetObjectList );
//        }
//        catch( Exception ex ){
//            statusCode = ResponseConstants.STATUS_BUSINESS_DEFAULT_ERROR;
//            message = ex.getMessage();
//        }
//        finally{
//            ResponseMapper rm = new ResponseMapper();
//            rm.setStatusCode( statusCode );
//            rm.setMessage( message );
//            rm.setData( "returnMsg", returnMsg );
//            rm.setModelMap( map );
//        }
//
//        return "PlmConstants.AJAX_VIEW";
//    }
//
//    private List<ApprovalVO> massApprove(List<ApprovalVO> targetObjectList){
//
//        ApprovalVO approvalVO = null;
//        BusinessObjectRoot boDom = null;
//        Map<String, String> inputParams = null;
//        List<ApprovalVO> returnMsg = new ArrayList<ApprovalVO>();
//
//        for( int inx = 0; inx < targetObjectList.size(); inx++ ){
//            approvalVO = targetObjectList.get(inx);
//            ApprovalVO newVO = new ApprovalVO();
//
//            try {
//                if( NullUtil.isNull(approvalVO.getObid()) ||
//                    NullUtil.isNull(approvalVO.getApprovalStatus()) ||
//                    NullUtil.isNull(approvalVO.getComments()) ||
//                    NullUtil.isNull(approvalVO.getWorkflowStepNodeUserObid()) ||
//                    NullUtil.isNull(approvalVO.getWorkflowRouteObid()) ||
//                    NullUtil.isNull(approvalVO.getStep()) ||
//                    NullUtil.isNull(approvalVO.getParallelNodeProcessionRule()) ){
//                    throw new IllegalArgumentException();
//                }
//
//                boDom = DomUtil.toDom( approvalVO.getObid(), true );
//                newVO.setObid(approvalVO.getObid());
//                newVO.setState(boDom.getVo().getClassName());
//                newVO.setTitles(boDom.getVo().getNames());
//                newVO.setComments(GlobalConstants.THREAD_LOG_SUCCESS);
//                inputParams = new HashMap<String, String>();
//                inputParams.put( "approvalStatus", approvalVO.getApprovalStatus() );
//                inputParams.put( "obidOfworkflowStepNodeUser", approvalVO.getWorkflowStepNodeUserObid() );
//                boDom.validateForApproval(inputParams);
//
//                Class<?>[] paramType = new Class<?>[1];
//                if( "Reject".equals(approvalVO.getApprovalStatus()) ){
//                    paramType[0] = String.class;
//                    Object[] sArguments = new Object[1];
//                    sArguments[0] = approvalVO.getApprovalStatus();
//                    try {
//                        Method method = boDom.getVo().getClass().getMethod( "setBranchTo", paramType );
//                        method.invoke( boDom.getVo(), approvalVO.getApprovalStatus() );
//                    } catch (NoSuchMethodException e) {
//                        throw new ApplicationException("api.object.error.workflow.noSuchMethod");
//                    } catch (SecurityException e) {
//                        throw new ApplicationException("api.object.error.workflow.security");
//                    } catch (IllegalAccessException e) {
//                        throw new ApplicationException("api.object.error.workflow.illegalAccess");
//                    } catch (IllegalArgumentException e) {
//                        throw new ApplicationException("api.object.error.workflow.illegalArgument");
//                    } catch (InvocationTargetException e) {
//                        throw new ApplicationException("api.object.error.workflow.invocationTarget");
//                    }
//                }
//
////            // ECO, Release의 경우 Reject 시 originator로 돌아가도록 설정
////            if( boDom.getVo().getClassName().equals(ApplicationSchemaConstants.BIZCLASS_ECO)
////                    || boDom.getVo().getClassName().equals(ApplicationSchemaConstants.BIZCLASS_RELEASE) ){
////                List<ApprovalVO> sendBackToList = new ArrayList<ApprovalVO>();
////                ApprovalVO originatorApprovalVO = null;
////                List<ApprovalVO> approvalList = retrieveSendBackToList( boDom );
////                if( approvalList != null && approvalList.size() > 0 ){
////                    for( int jnx = 0; jnx < approvalList.size(); jnx++ ){
////                        if( GpdmConstants.INSTRUCTION_TYPE_ORIGINATOR.equals(approvalList.get(jnx).getRouteInstructions()) ){
////                            originatorApprovalVO = approvalList.get(jnx);
////                            break;
////                        }
////                    }
////                }
////                if( originatorApprovalVO == null ) throw new GpdmException( "Not exist originator" );
////                sendBackToList.add( originatorApprovalVO );
////                boDom.getVo().getOutData().put("sendBackToList", sendBackToList);
////            }
//
//                workflowService.txnDoApproval(boDom, approvalVO);
//            } catch (Exception e) {
//                newVO.setComments(GlobalConstants.THREAD_LOG_ERROR +" : "+ e.toString().substring(0,100));
//            } finally{
//                returnMsg.add(newVO);
//            }
//        }
//
//        return returnMsg;
//    }
    
    /**
     * 결재대상 조회
     * @param userId
     * @param map
     * @return
     */
    @RequestMapping( value="/workflow/retrieveApprovalList.do" )
    public String retrieveApprovalList( @SCRequestDataset( "searchInfo" ) WorkflowInboxTaskVO searchInfo, @SCRequestDataset( "userId" ) String userId, ModelMap map) {
        ResponseMapper rm = new ResponseMapper();
        Users users = null;
        List<WorkflowInboxTaskVO> approvalList = null;

        // 조건이 없을 경우 session에서 사용자정보 조회
        if( StringUtils.isEmpty( userId ) ){
            users = new Users( userSession.getUserBizObid() );
            approvalList = users.retrieveApprovalList( searchInfo );
        }
        else{
            approvalList = Users.retrieveApprovalList( searchInfo, userId );
        }

        int totalRow =0;
        int currentPage=0;
        if ( approvalList instanceof PagingList ) {
            
            PagingList<WorkflowInboxTaskVO> pagingList = (PagingList<WorkflowInboxTaskVO>)approvalList;
            totalRow =  pagingList.getRows();
            currentPage = pagingList.getCurrentPage();
        }

        String loginUser = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, null);
        //rm.setData( "isSystemAdmin", AccessControl.hasRole(PlmConstants.ROLE_NAME_SYSTEM_ADMIN, loginUser));
        rm.setData( "systemAdminName", usersService.getUserInfo(loginUser).getTitles());
        rm.setData( "searchId", userId);
        rm.setData( "totRecCount", totalRow );
        rm.setData( "currentPage", currentPage );
        rm.setData( "approvalList", approvalList );
        rm.setModelMap( map );

        return "PlmConstants.AJAX_VIEW";
    }
 
    /**
     * Save Draft 조회
     * @param userId
     * @param map
     * @return
     */
    @RequestMapping( value="/workflow/retrieveSaveDraftList.do" )
    public String retrieveSaveDraftList( @SCRequestDataset( "searchInfo" ) WorkflowInboxTaskVO searchInfo, @SCRequestDataset( "userId" ) String userId, ModelMap map) {
        ResponseMapper rm = new ResponseMapper();
        Users users = null;
        List<WorkflowInboxTaskVO> approvalList = null;

        // 조건이 없을 경우 session에서 사용자정보 조회
        if( StringUtils.isEmpty( userId ) ){
            users = new Users( userSession.getUserBizObid() );
            approvalList = users.retrieveSaveDraftList( searchInfo );
        }
        else{
            approvalList = Users.retrieveSaveDraftList( searchInfo, userId );
        }

        int totalRow =0;
        int currentPage=0;
        if ( approvalList instanceof PagingList ) {
            
            PagingList<WorkflowInboxTaskVO> pagingList = (PagingList<WorkflowInboxTaskVO>)approvalList;
            totalRow =  pagingList.getRows();
            currentPage = pagingList.getCurrentPage();
        }

        String loginUser = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, null);
        //rm.setData( "isSystemAdmin", AccessControl.hasRole(PlmConstants.ROLE_NAME_SYSTEM_ADMIN, loginUser));
        rm.setData( "systemAdminName", usersService.getUserInfo(loginUser).getTitles());
        rm.setData( "searchId", userId);
        rm.setData( "totRecCount", totalRow );
        rm.setData( "currentPage", currentPage );
        rm.setData( "approvalList", approvalList );
        rm.setModelMap( map );

        return "PlmConstants.AJAX_VIEW";
    }

  /**
  * 로그인 한 사용자에게 배포된 목록 조회
  * @param userId
  * @param map
  * @return
  */
 @RequestMapping( value="/workflow/retrieveDistributionList.do" )
 public String retrieveDistributionList( @SCRequestDataset( "searchInfo" ) WorkflowInboxTaskVO searchInfo, @SCRequestDataset( "userId" ) String userId, ModelMap map) {
     ResponseMapper rm = new ResponseMapper();
     Users users = null;
     List<WorkflowInboxTaskVO> distributionList = null;

     // 조건이 없을 경우 session에서 사용자정보 조회
     if( StringUtils.isEmpty( userId ) ){
         users = new Users( userSession.getUserBizObid() );
         distributionList = users.retrieveDistributionList( searchInfo );
     }
     else{
         distributionList = Users.retrieveDistributionList( searchInfo, userId );
     }

     int totalRow =0;
     int currentPage=0;
     if ( distributionList instanceof PagingList ) {
         PagingList<WorkflowInboxTaskVO> pagingList = (PagingList<WorkflowInboxTaskVO>)distributionList;
         totalRow =  pagingList.getRows();
         currentPage = pagingList.getCurrentPage();     
     }

     rm.setData( "totRecCount", totalRow );
     rm.setData( "currentPage", currentPage );
     rm.setData( "distributionList", distributionList );
     rm.setModelMap( map );

     return "PlmConstants.AJAX_VIEW";
 }

 /**
  * 로그인 한 사용자의 요청목록 조회
  * @param userId
  * @param map
  * @return
  */
 @RequestMapping( value="/workflow/retrieveRequestedList.do" )
 public String retrieveRequestedList( @SCRequestDataset( "searchInfo" ) WorkflowInboxTaskVO searchInfo, @SCRequestDataset( "userId" ) String userId, ModelMap map) {
     ResponseMapper rm = new ResponseMapper();
     Users users = null;
     List<WorkflowInboxTaskVO> requestedList = null;

     // 조건이 없을 경우 session에서 사용자정보 조회
     if( StringUtils.isEmpty( userId ) ){
         users = new Users( userSession.getUserBizObid() );
         requestedList = users.retrieveRequestedList( searchInfo );
     }
     else{
         requestedList = Users.retrieveRequestedList( searchInfo, userId );
     }

     int totalRow =0;
     int currentPage=0;     
     if ( requestedList instanceof PagingList ) {
         PagingList<WorkflowInboxTaskVO> pagingList = (PagingList<WorkflowInboxTaskVO>)requestedList;
         totalRow =  pagingList.getRows();
         currentPage = pagingList.getCurrentPage();       
     }

     rm.setData( "totRecCount", totalRow );
     rm.setData( "currentPage", currentPage );
     rm.setData( "requestedList", requestedList );
     rm.setModelMap( map );

     return "PlmConstants.AJAX_VIEW";
 }

 /**
  * 로그인 한 사용자의 승인완료목록 조회
  * @param searchInfo
  * @param userId
  * @param map
  * @return
  */
 @RequestMapping( value="/workflow/retrieveApprovedList.do" )
 public String retrieveApprovedList( @SCRequestDataset( "searchInfo" ) WorkflowInboxTaskVO searchInfo, @SCRequestDataset( "userId" ) String userId, ModelMap map) {
     ResponseMapper rm = new ResponseMapper();
     Users users = null;
     List<WorkflowInboxTaskVO> approvedList = null;

     // 조건이 없을 경우 session에서 사용자정보 조회
     if( StringUtils.isEmpty( userId ) ){
         users = new Users( userSession.getUserBizObid() );
         approvedList = users.retrieveApprovedList( searchInfo );
     }
     else{
         approvedList = Users.retrieveApprovedList( searchInfo, userId );
     }

     int totalRow =0;
     int currentPage=0;   
     if ( approvedList instanceof PagingList ) {
         PagingList<WorkflowInboxTaskVO> pagingList = (PagingList<WorkflowInboxTaskVO>)approvedList;
         totalRow =  pagingList.getRows();
         currentPage = pagingList.getCurrentPage();    
     }
     
     String loginUser = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, null);
     rm.setData( "systemAdminName", usersService.getUserInfo(loginUser).getTitles());
     rm.setData( "searchId", userId);
     rm.setData( "totRecCount", totalRow );
     rm.setData( "currentPage", currentPage );
     rm.setData( "approvedList", approvedList );
     rm.setModelMap( map );

     return "PlmConstants.AJAX_VIEW";
 }
 
 @RequestMapping( value="/workflow/retrieveSaveDraftMyWorkingList.do" )
 public String retrieveSaveDraftWorkingList( 
         @SCRequestDataset( "searchInfo" ) BusinessObjectMasterVO searchInfo, 
         @SCRequestDataset( "userId" ) String userId, 
         ModelMap map) {
     ResponseMapper rm = new ResponseMapper();
     Users users = null;
     List<BusinessObjectMasterVO> result = null;
     
     //Map<String, Object> searchInfo = new HashMap<String, Object>();
     //searchInfo.put("titles", titles);
     //searchInfo.put("creator", userId);
     // 조건이 없을 경우 session에서 사용자정보 조회
//     if ( StringUtils.isEmpty( userId ) ) {
//         users = new Users( userSession.getUserBizObid() );
//         result = users.retrieveSaveDraftMyWorkingList( searchInfo );
//     }
//     else {
//         result = Users.retrieveSaveDraftMyWorkingList( searchInfo, userId );
//     }

     int totalRow = 0;
     int currentPage = 0;
     if ( result instanceof PagingList ) {
         PagingList<BusinessObjectMasterVO> pagingList = (PagingList<BusinessObjectMasterVO>)result;
         totalRow =  pagingList.getRows();
         currentPage = pagingList.getCurrentPage();
     }

     rm.setData( "totRecCount", totalRow );
     rm.setData( "currentPage", currentPage );
     rm.setData( "saveDraftWorkingList", result );
     rm.setModelMap( map );

     return "PlmConstants.AJAX_VIEW";
 }
 
 @RequestMapping("/pivotTest.do")
 public String epApprove( ModelMap map ){
     return "workflow/pivotTest";
 }
}